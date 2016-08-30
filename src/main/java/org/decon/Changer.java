package org.decon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

import org.decon.rt.Cc;
import org.decon.rt.Context;
import org.decon.rt.DccException;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.commons.Method;

public class Changer extends AnalyzerAdapter {

    private boolean annotationPresent = false;

    private static final AtomicBoolean       methodsLoaded = new AtomicBoolean(false);
    private static final Map<String, Method> methods       = new HashMap<>();
    private static Method                    getContext;
    private static Method                    initException;

    private final Object[] frame0;
    private final Label    frame1        = new Label();
    private final Label    ccTableSwitch = new Label();

    private final List<CallWrapInfo> callWrapInfo = new ArrayList<>();
    private final boolean            hasThis;
    private final int                contArgIndex;

    public Changer(String owner, int access, String name, String desc, MethodVisitor mv) {
        super(Opcodes.ASM5, owner, access, name, desc, mv);
        frame0 = locals.toArray();
        hasThis = ((access & Opcodes.ACC_STATIC) == 0);
        contArgIndex = (hasThis ? 1 : 0);
        initStatic();
    }

    @Override
    public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
        if (parameter == 0 && Cc.annotationDesc.equals(desc)) {
            annotationPresent = true;
            return null; // Removes the annotation.
        }
        return super.visitParameterAnnotation(parameter, desc, visible);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        if (annotationPresent) {
            super.visitJumpInsn(Opcodes.GOTO, ccTableSwitch);
            super.visitLabel(frame1);
            super.visitFrame(Opcodes.F_NEW, frame0.length, frame0, 0, new Object[] {});
        }
    }

    private void emitCall(String name, String desc, Runnable callInsn) {
        if (doNotCc(name, desc)) {
            callInsn.run();
        } else {
            int contIndexOnStack = stack.size() - Type.getMethodType(desc).getArgumentTypes().length;
            emitCallWrapper(callInsn, contIndexOnStack);
        }
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        emitCall(name, desc, () -> super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs));
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
        ret = ret || notInited.test(stack);
        ret = ret || notInited.test(locals);
        return ret;
    }

    void emitCallWrapper(Runnable visitCallInsn, int contIndexOnStack) {
        Object[] callLocals = locals.toArray();
        // Order of Stack - Last poppable value at index 0 and first poppable value at end of array.
        Object[] callStack = stack.toArray();

        Object[] handlerFrame = Arrays.copyOf(callLocals, callLocals.length + callStack.length);
        System.arraycopy(callStack, 0, handlerFrame, callLocals.length, callStack.length);

        Label start = new Label();
        Label end = new Label();
        Label handler = new Label();
        super.visitTryCatchBlock(start, end, handler, DccException.desc);

        // POPs to localvars.
        for (int i = callStack.length - 1; i >= 0; --i) {
            Object t = callStack[i];
            if (Opcodes.TOP.equals(t)) {
                continue;
            }
            super.visitVarInsn(storeOpcode(t), callLocals.length + i);
        }
        // PUSHs from local vars in reverse order.
        for (int i = 0; i < callStack.length; ++i) {
            Object t = callStack[i];
            if (Opcodes.TOP.equals(t)) {
                continue;
            }
            super.visitVarInsn(loadOpcode(t), callLocals.length + i);
        }
        // TODO No need to preserve all stack arguments. Store only 'this' pointer, or nothing for invokeStatic.
        // The called function frame will be preserved anyways, so storing its data in this frame is pointless.
        super.visitLabel(start);
        super.visitFrame(Opcodes.F_NEW, handlerFrame.length, handlerFrame, callStack.length, callStack);
        visitCallInsn.run();
        super.visitLabel(end);

        callWrapInfo.add(new CallWrapInfo(start, callStack, callLocals, handler, handlerFrame, contIndexOnStack));
    }

    void emitCodeEpilogue() {
        super.visitLabel(ccTableSwitch);
        super.visitFrame(Opcodes.F_NEW, frame0.length, frame0, 0, new Object[] {});
        if (callWrapInfo.isEmpty()) {
            super.visitInsn(Opcodes.ACONST_NULL);
            super.visitVarInsn(Opcodes.ASTORE, contArgIndex);
            super.visitJumpInsn(Opcodes.GOTO, frame1);
            return;
        }
        super.visitVarInsn(Opcodes.ALOAD, contArgIndex);
        super.visitJumpInsn(Opcodes.IFNULL, frame1);

        Label defaultLabel = new Label();
        Label[] caseLabels = new Label[callWrapInfo.size()];
        Arrays.setAll(caseLabels, (i) -> new Label());

        super.visitVarInsn(Opcodes.ALOAD, contArgIndex);
        super.visitTypeInsn(Opcodes.CHECKCAST, Context.desc);
        super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Context.desc, methods.get("popJump").getName(),
                methods.get("popJump").getDescriptor(), false);
        super.visitTableSwitchInsn(0, caseLabels.length - 1, defaultLabel, caseLabels);
        for (int i = 0; i < caseLabels.length; i++) {
            CallWrapInfo cwi = callWrapInfo.get(i);
            emitSwitchCase(cwi, caseLabels[i]);
            emitHandler(cwi, i);
        }

        super.visitLabel(defaultLabel);

        super.visitFrame(Opcodes.F_NEW, frame0.length, frame0, 0, new Object[] {});
        super.visitVarInsn(Opcodes.ALOAD, contArgIndex);
        super.visitTypeInsn(Opcodes.CHECKCAST, Context.desc);

        super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Context.desc, methods.get("invalidContext").getName(),
                methods.get("invalidContext").getDescriptor(), false);
        // TODO inline invalidContext method call above and remove this dummy athrow of null.
        // Control never reaches here but verifier does not know because of invalidContext call above.
        super.visitInsn(Opcodes.ACONST_NULL);
        super.visitInsn(Opcodes.ATHROW);
    }

    private void emitSwitchCase(CallWrapInfo cwi, Label caseLabel) {
        super.visitLabel(caseLabel);
        super.visitFrame(Opcodes.F_NEW, frame0.length, frame0, 0, new Object[] {});

        Object[] stackVars = cwi.getStack();
        Object[] localVars = cwi.getLocals();

        // Copy cont reference as contArg is set null during restoration.
        int contCopy = localVars.length + stackVars.length;
        super.visitVarInsn(Opcodes.ALOAD, contArgIndex);
        super.visitTypeInsn(Opcodes.CHECKCAST, Context.desc);
        super.visitVarInsn(Opcodes.ASTORE, contCopy);
        for (int j = 0; j < stackVars.length; ++j) {
            Object t = stackVars[j];
            if (Opcodes.TOP.equals(t)) {
                continue;
            }
            Method pop = popMethod(t);
            super.visitVarInsn(Opcodes.ALOAD, contCopy);
            super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Context.desc, pop.getName(), pop.getDescriptor(), false);
            if (j == cwi.getContIndexOnStack()) {
                super.visitInsn(Opcodes.POP);
                super.visitVarInsn(Opcodes.ALOAD, contCopy);
                super.visitInsn(Opcodes.ACONST_NULL);
                super.visitVarInsn(Opcodes.ASTORE, localVars.length + j);
            } else {
                if (t instanceof String) {
                    super.visitTypeInsn(Opcodes.CHECKCAST, (String) t);
                }
                super.visitInsn(Opcodes.DUP);
                super.visitVarInsn(storeOpcode(t), localVars.length + j);
            }
        }
        for (int j = 0; j < localVars.length; ++j) {
            Object t = localVars[j];
            if (Opcodes.TOP.equals(t)) {
                continue;
            }
            Method pop = popMethod(t);
            super.visitVarInsn(Opcodes.ALOAD, contCopy);
            super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Context.desc, pop.getName(), pop.getDescriptor(), false);
            if (t instanceof String) {
                super.visitTypeInsn(Opcodes.CHECKCAST, (String) t);
            }
            super.visitVarInsn(storeOpcode(t), j);
        }
        // Set current cont arg as null to avoid capturing it in next continuation.
        super.visitInsn(Opcodes.ACONST_NULL);
        super.visitVarInsn(Opcodes.ASTORE, contArgIndex);

        super.visitJumpInsn(Opcodes.GOTO, cwi.getCallStart());
    }

    private void emitHandler(CallWrapInfo cwi, int jump) {
        Object[] stackVars = cwi.getStack();
        Object[] localVars = cwi.getLocals();

        super.visitLabel(cwi.getHandler());
        super.visitFrame(Opcodes.F_NEW, cwi.getHandlerFrame().length, cwi.getHandlerFrame(), 1,
                new Object[] { DccException.desc });
        // Get Cont from exception.
        super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, DccException.desc, getContext.getName(),
                getContext.getDescriptor(), false);
        // Order of local vars - Push index 0 last.
        for (int j = localVars.length - 1; j >= 0; --j) {
            Object t = localVars[j];
            if (Opcodes.TOP.equals(t)) {
                continue;
            }
            super.visitInsn(Opcodes.DUP);
            super.visitVarInsn(loadOpcode(t), j);
            Method push = pushMethod(t);
            super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Context.desc, push.getName(), push.getDescriptor(), false);
        }
        // Order of stack - Push end of array into Cont first.
        // Index 0 gets pushed last.
        for (int j = stackVars.length - 1; j >= 0; --j) {
            Object t = stackVars[j];
            if (Opcodes.TOP.equals(t)) {
                continue;
            }
            super.visitInsn(Opcodes.DUP);
            super.visitVarInsn(loadOpcode(t), localVars.length + j);
            Method push = pushMethod(t);
            super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Context.desc, push.getName(), push.getDescriptor(), false);
        }
        super.visitInsn(Opcodes.DUP);
        super.visitLdcInsn(Integer.valueOf(jump));
        super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, Context.desc, methods.get("pushJump").getName(),
                methods.get("pushJump").getDescriptor(), false);

        // Create new exception. Cont object is on stack.
        mv.visitTypeInsn(Opcodes.NEW, DccException.desc);
        mv.visitInsn(Opcodes.DUP_X1);
        mv.visitInsn(Opcodes.SWAP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, DccException.desc, initException.getName(),
                initException.getDescriptor(), false);
        super.visitInsn(Opcodes.ATHROW);
    }

    Method popMethod(Object t) {
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

    Method pushMethod(Object t) {
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

    int storeOpcode(Object t) {
        if (t.equals(Opcodes.INTEGER)) {
            return Opcodes.ISTORE;
        } else if (t.equals(Opcodes.FLOAT)) {
            return Opcodes.FSTORE;
        } else if (t.equals(Opcodes.LONG)) {
            return Opcodes.LSTORE;
        } else if (t.equals(Opcodes.DOUBLE)) {
            return Opcodes.DSTORE;
        } else if (t instanceof String) {
            return Opcodes.ASTORE;
        } else {
            throw new RuntimeException("Unknown, top or uninitialized type " + t);
        }
    }

    int loadOpcode(Object t) {
        if (t.equals(Opcodes.INTEGER)) {
            return Opcodes.ILOAD;
        } else if (t.equals(Opcodes.FLOAT)) {
            return Opcodes.FLOAD;
        } else if (t.equals(Opcodes.LONG)) {
            return Opcodes.LLOAD;
        } else if (t.equals(Opcodes.DOUBLE)) {
            return Opcodes.DLOAD;
        } else if (t instanceof String) {
            return Opcodes.ALOAD;
        } else {
            throw new RuntimeException("Unknown, top or uninitialized type " + t);
        }
    }

    static void initStatic() {
        if (methodsLoaded.get() == false) {
            Arrays.stream(Context.class.getDeclaredMethods())
                    .forEach(m -> methods.put(m.getName(), Method.getMethod(m)));
            try {
                getContext = Method.getMethod(DccException.class.getMethod("getContext"));
                initException = Method.getMethod(DccException.class.getConstructor(Context.class));
            } catch (NoSuchMethodException | SecurityException ex) {
                throw new RuntimeException(ex);
            }
            methodsLoaded.set(true);
        }
    }
}
