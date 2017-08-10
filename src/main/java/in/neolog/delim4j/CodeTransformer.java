package in.neolog.delim4j;

import static org.objectweb.asm.Opcodes.F_NEW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.commons.InstructionAdapter;
import org.objectweb.asm.commons.Method;

import in.neolog.delim4j.rt.Cc;
import in.neolog.delim4j.rt.Context;
import in.neolog.delim4j.rt.DelimException;
import in.neolog.delim4j.rt.InvalidContextException;

public class CodeTransformer extends InstructionAdapter {

    private boolean annotationPresent = false;

    private static final Map<String, Method> methods = new HashMap<>();
    private static Method                    getContext;
    private static Method                    initException;
    private static Method                    initInvalidException;

    private final Object[] frame0;
    private final Label    frame1        = new Label();
    private final Label    ccTableSwitch = new Label();

    private final List<CallWrapInfo> callWrapInfo = new ArrayList<>();
    private final boolean            hasThis;
    private final int                contArgIndex;

    protected AnalyzerAdapter getMv() {
        return (AnalyzerAdapter) super.mv;
    }

    public CodeTransformer(String owner, int access, String name, String desc, MethodVisitor mv) {
        super(Opcodes.ASM5, new AnalyzerAdapter(owner, access, name, desc, mv));
        frame0 = getMv().locals.toArray();
        hasThis = ((access & Opcodes.ACC_STATIC) == 0);
        contArgIndex = (hasThis ? 1 : 0);
        initStatic();
    }

    @Override
    public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
        if (parameter == 0 && Cc.annotationDesc.equals(desc)) {
            annotationPresent = true;
            return null; // Removes the @Cc annotation.
        }
        return super.visitParameterAnnotation(parameter, desc, visible);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        if (annotationPresent) {
            goTo(ccTableSwitch);
            mark(frame1);
            super.visitFrame(F_NEW, frame0.length, frame0, 0, new Object[] {});
        }
    }

    private void emitCall(String name, String desc, Runnable callInsn) {
        if (doNotCc(name, desc)) {
            callInsn.run();
        } else {
            int contIndexOnStack = getMv().stack.size() - Type.getMethodType(desc)
                                                              .getArgumentTypes().length;
            emitCallWrapper(callInsn, contIndexOnStack);
        }
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        emitCall(name, desc, () -> invokedynamic(name, desc, bsm, bsmArgs));
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        emitCall(name, desc, () -> super.visitMethodInsn(opcode, owner, name, desc, itf));
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        if (annotationPresent) {
            emitCodeEpilogue();
        }
        super.visitMaxs(maxStack, maxLocals);
    }

    boolean doNotCc(String name, String desc) {
        boolean ret = !annotationPresent;
        ret = ret || !(desc.startsWith(Context.argDesc) || desc.startsWith("(Ljava/lang/Object;"));
        ret = ret || "<init>".equals(name) || "<clinit>".equals(name);
        Predicate<List<?>> notInited = l -> {
            for (Object e : l) {
                if (Opcodes.UNINITIALIZED_THIS.equals(e) || (e instanceof Label)) {
                    return true;
                }
            }
            return false;
        };
        ret = ret || notInited.test(getMv().stack);
        ret = ret || notInited.test(getMv().locals);
        return ret;
    }

    void emitCallWrapper(Runnable visitCallInsn, int contIndexOnStack) {
        Object[] callLocals = getMv().locals.toArray();
        // Order of Stack - Last poppable value at index 0 and first poppable value at end of array.
        Object[] callStack = getMv().stack.toArray();

        Object[] handlerFrame = Arrays.copyOf(callLocals, callLocals.length + callStack.length);
        System.arraycopy(callStack, 0, handlerFrame, callLocals.length, callStack.length);

        Label start = new Label();
        Label end = new Label();
        Label handler = new Label();
        super.visitTryCatchBlock(start, end, handler, DelimException.desc);

        // POPs to localvars.
        for (int i = callStack.length - 1; i >= 0; --i) {
            Object t = callStack[i];
            if (Opcodes.TOP.equals(t)) {
                continue;
            }
            store(callLocals.length + i, stackMapToType(t));
        }
        // PUSHs from local vars in reverse order.
        for (int i = 0; i < callStack.length; ++i) {
            Object t = callStack[i];
            if (Opcodes.TOP.equals(t)) {
                continue;
            }
            load(callLocals.length + i, stackMapToType(t));
        }
        // TODO No need to preserve all stack arguments. Store only 'this' pointer, or nothing for invokeStatic.
        // The called function frame will be preserved anyways, so storing its data in this frame is pointless.
        mark(start);
        super.visitFrame(F_NEW, handlerFrame.length, handlerFrame, callStack.length, callStack);
        visitCallInsn.run();
        mark(end);

        callWrapInfo.add(new CallWrapInfo(start, callStack, callLocals, handler, handlerFrame, contIndexOnStack));
    }

    void emitCodeEpilogue() {
        mark(ccTableSwitch);
        super.visitFrame(F_NEW, frame0.length, frame0, 0, new Object[] {});
        if (callWrapInfo.isEmpty()) {
            aconst(null);
            store(contArgIndex, stackMapToType(Context.desc));
            goTo(frame1);
            return;
        }
        load(contArgIndex, stackMapToType(Context.desc));
        ifnull(frame1);

        Label defaultLabel = new Label();
        Label[] caseLabels = new Label[callWrapInfo.size()];
        Arrays.setAll(caseLabels, (i) -> new Label());

        load(contArgIndex, stackMapToType(Context.desc));
        checkcast(stackMapToType(Context.desc));
        Method popJump = methods.get("popJump");
        invokevirtual(Context.desc, popJump.getName(), popJump.getDescriptor(), false);
        tableswitch(0, caseLabels.length - 1, defaultLabel, caseLabels);
        for (int i = 0; i < caseLabels.length; i++) {
            CallWrapInfo cwi = callWrapInfo.get(i);
            emitSwitchCase(cwi, caseLabels[i]);
            emitHandler(cwi, i);
        }

        mark(defaultLabel);

        super.visitFrame(F_NEW, frame0.length, frame0, 0, new Object[] {});

        load(contArgIndex, stackMapToType(Context.desc));
        checkcast(stackMapToType(Context.desc));
        anew(stackMapToType(InvalidContextException.desc));
        dupX1();
        swap();
        invokespecial(InvalidContextException.desc, initInvalidException.getName(),
                initInvalidException.getDescriptor(), false);
        athrow();
    }

    private void emitSwitchCase(CallWrapInfo cwi, Label caseLabel) {
        mark(caseLabel);
        super.visitFrame(F_NEW, frame0.length, frame0, 0, new Object[] {});

        Object[] stackVars = cwi.getStack();
        Object[] localVars = cwi.getLocals();

        // Copy cont reference as contArg is set null during restoration.
        int contCopy = localVars.length + stackVars.length;
        load(contArgIndex, stackMapToType(Context.desc));
        checkcast(stackMapToType(Context.desc));
        store(contCopy, stackMapToType(Context.desc));
        for (int j = 0; j < stackVars.length; ++j) {
            Object t = stackVars[j];
            if (Opcodes.TOP.equals(t)) {
                continue;
            }
            Method pop = popMethod(t);
            load(contCopy, stackMapToType(Context.desc));
            invokevirtual(Context.desc, pop.getName(), pop.getDescriptor(), false);
            if (j == cwi.getContIndexOnStack()) {
                pop();
                load(contCopy, stackMapToType(Context.desc));
                aconst(null);
                store(localVars.length + j, stackMapToType(Context.desc));
            } else {
                if (t instanceof String) {
                    checkcast(stackMapToType(t));
                }
                dup();
                store(localVars.length + j, stackMapToType(t));
            }
        }
        for (int j = 0; j < localVars.length; ++j) {
            Object t = localVars[j];
            if (Opcodes.TOP.equals(t)) {
                continue;
            }
            Method pop = popMethod(t);
            load(contCopy, stackMapToType(Context.desc));
            invokevirtual(Context.desc, pop.getName(), pop.getDescriptor(), false);
            if (t instanceof String) {
                checkcast(stackMapToType(t));
            }
            store(j, stackMapToType(t));
        }
        // Set current cont arg as null to avoid capturing it in next continuation.
        aconst(null);
        store(contArgIndex, stackMapToType(Context.desc));

        goTo(cwi.getCallStart());
    }

    private void emitHandler(CallWrapInfo cwi, int jump) {
        Object[] stackVars = cwi.getStack();
        Object[] localVars = cwi.getLocals();

        mark(cwi.getHandler());
        super.visitFrame(F_NEW, cwi.getHandlerFrame().length, cwi.getHandlerFrame(), 1,
                new Object[] { DelimException.desc });
        // Get Cont from exception.
        invokevirtual(DelimException.desc, getContext.getName(), getContext.getDescriptor(), false);
        // Order of local vars - Push index 0 last.
        for (int j = localVars.length - 1; j >= 0; --j) {
            Object t = localVars[j];
            if (Opcodes.TOP.equals(t)) {
                continue;
            }
            dup();
            load(j, stackMapToType(t));
            Method push = pushMethod(t);
            invokevirtual(Context.desc, push.getName(), push.getDescriptor(), false);
        }
        // Order of stack - Push end of array into Cont first.
        // Index 0 gets pushed last.
        for (int j = stackVars.length - 1; j >= 0; --j) {
            Object t = stackVars[j];
            if (Opcodes.TOP.equals(t)) {
                continue;
            }
            dup();
            load(localVars.length + j, stackMapToType(t));
            Method push = pushMethod(t);
            invokevirtual(Context.desc, push.getName(), push.getDescriptor(), false);
        }
        dup();
        visitLdcInsn(Integer.valueOf(jump));
        Method pushJump = methods.get("pushJump");
        invokevirtual(Context.desc, pushJump.getName(), pushJump.getDescriptor(), false);

        // Create new exception. Cont object is on stack.
        anew(stackMapToType(DelimException.desc));
        dupX1();
        swap();
        invokespecial(DelimException.desc, initException.getName(), initException.getDescriptor(), false);
        athrow();
    }

    private Method popMethod(Object t) {
        String name;
        if (t.equals(Opcodes.INTEGER)) {
            name = "popInt";
        } else if (t.equals(Opcodes.FLOAT)) {
            name = "popFloat";
        } else if (t.equals(Opcodes.LONG)) {
            name = "popLong";
        } else if (t.equals(Opcodes.DOUBLE)) {
            name = "popDouble";
        } else if (t instanceof String) {
            name = "popObject";
        } else {
            throw new RuntimeException("Unknown, top or uninitialized type " + t);
        }
        return methods.get(name);
    }

    private Method pushMethod(Object t) {
        String name;
        if (t.equals(Opcodes.INTEGER)) {
            name = "pushInt";
        } else if (t.equals(Opcodes.FLOAT)) {
            name = "pushFloat";
        } else if (t.equals(Opcodes.LONG)) {
            name = "pushLong";
        } else if (t.equals(Opcodes.DOUBLE)) {
            name = "pushDouble";
        } else if (t instanceof String) {
            name = "pushObject";
        } else {
            throw new RuntimeException("Unknown, top or uninitialized type " + t);
        }
        return methods.get(name);
    }

    private Map<Object, Type> typeCache = new HashMap<>();

    private Type stackMapToType(Object t) {
        Type cached = typeCache.get(t);
        if (cached != null) {
            return cached;
        }
        if (t.equals(Opcodes.INTEGER)) {
            typeCache.put(Opcodes.INTEGER, Type.INT_TYPE);
            return Type.INT_TYPE;
        } else if (t.equals(Opcodes.FLOAT)) {
            typeCache.put(Opcodes.FLOAT, Type.FLOAT_TYPE);
            return Type.FLOAT_TYPE;
        } else if (t.equals(Opcodes.LONG)) {
            typeCache.put(Opcodes.LONG, Type.LONG_TYPE);
            return Type.LONG_TYPE;
        } else if (t.equals(Opcodes.DOUBLE)) {
            typeCache.put(Opcodes.DOUBLE, Type.DOUBLE_TYPE);
            return Type.DOUBLE_TYPE;
        } else if (t instanceof String) {
            typeCache.put(t, Type.getObjectType(t.toString()));
            return typeCache.get(t);
        } else {
            throw new RuntimeException("Unknown, top or uninitialized type " + t);
        }
    }

    static void initStatic() {
        synchronized (methods) {
            if (methods.isEmpty()) {
                Arrays.stream(Context.class.getDeclaredMethods())
                      .forEach(m -> methods.put(m.getName(), Method.getMethod(m)));
                try {
                    getContext = Method.getMethod(DelimException.class.getMethod("getContext"));
                    initException = Method.getMethod(DelimException.class.getConstructor(Context.class));
                    initInvalidException =
                            Method.getMethod(InvalidContextException.class.getConstructor(Context.class));
                } catch (NoSuchMethodException | SecurityException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
