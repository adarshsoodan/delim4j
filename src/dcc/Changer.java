package dcc;

import dcc.rt.Cont;
import dcc.rt.DccException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.commons.Method;

public class Changer extends AnalyzerAdapter {

    static final String dccException = "dcc/rt/DccException";
    static final String contDesc = "dcc/rt/Cont";
    static final String annotation = "Ldcc/rt/Contify;";

    boolean annotationPresent = false;

    static final AtomicBoolean methodsLoaded = new AtomicBoolean(false);
    static Map<String, Method> contMethods = new HashMap<>();
    static Method getCont;
    static Method initException;

    Object[] frame0;
    Label frame1 = new Label();
    Label ccTableSwitch = new Label();

    List<CallWrapInfo> callWrapInfo = new ArrayList<>();
    boolean hasThis;

    public Changer(String owner, int access, String name, String desc, MethodVisitor mv) {
        super(Opcodes.ASM5, owner, access, name, desc, mv);
        frame0 = locals.toArray();
        hasThis = ((access & Opcodes.ACC_STATIC) == 0);
        if (methodsLoaded.get() == false) {
            Arrays.stream(Cont.class.getDeclaredMethods()).forEach(m -> {
                contMethods.put(m.getName(), Method.getMethod(m));
            });
            try {
                getCont = Method.getMethod(DccException.class.getMethod("getCont"));
                initException = Method.getMethod(DccException.class.getConstructor(Cont.class));
            } catch (NoSuchMethodException | SecurityException ex) {
                throw new RuntimeException(ex);
            }
            methodsLoaded.set(true);
        }
    }

    @Override
    public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
        if (parameter == 0 && annotation.equals(desc)) {
            annotationPresent = true;
        }
        return super.visitParameterAnnotation(parameter, desc, visible);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        if (annotationPresent) {
            super.visitJumpInsn(Opcodes.GOTO, ccTableSwitch);
            super.visitLabel(frame1);
            super.visitFrame(Opcodes.F_NEW, frame0.length, frame0, 0, new Object[]{});
        }
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        if (notContify(name, desc)) {
            super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
        } else {
            int contIndexOnStack = stack.size() - Type.getMethodType(desc).getArgumentTypes().length;
            emitCallWrapper(() -> super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs),
                    contIndexOnStack);
        }
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        if (notContify(name, desc)) {
            super.visitMethodInsn(opcode, owner, name, desc, itf);
        } else {
            int contIndexOnStack = stack.size() - Type.getMethodType(desc).getArgumentTypes().length;
            emitCallWrapper(() -> super.visitMethodInsn(opcode, owner, name, desc, itf),
                    contIndexOnStack);
        }
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        if (annotationPresent) {
            emitCodeEpilogue();
        }
        super.visitMaxs(maxStack, maxLocals);
    }

    boolean notContify(String name, String desc) {
        boolean ret = !annotationPresent;
        ret = ret || !(desc.startsWith("(Ldcc/rt/Cont;"));
        ret = ret || "<init>".equals(name) || "<clinit>".equals(name);
        ret = ret || stack.stream().anyMatch(
                v -> Opcodes.UNINITIALIZED_THIS.equals(v) || (v instanceof Label));
        ret = ret || locals.stream().anyMatch(
                v -> Opcodes.UNINITIALIZED_THIS.equals(v) || (v instanceof Label));
        return ret;
    }

    void emitCallWrapper(Runnable action, int contIndexOnStack) {
        Object[] callLocals = locals.toArray();
        // Order of Stack - Last poppable value at index 0. First poppable value at end of array.
        Object[] callStack = stack.toArray();

        Object[] handlerFrame = Arrays.copyOf(callLocals, callLocals.length + callStack.length);
        System.arraycopy(callStack, 0, handlerFrame, callLocals.length, callStack.length);
        Type[] localTypes = FrameT.fromFrame(callLocals);
        Type[] stackTypes = FrameT.fromFrame(callStack);

        Label start = new Label();
        Label end = new Label();
        Label handler = new Label();
        super.visitTryCatchBlock(start, end, handler, dccException);

        // POPs to localvars.
        for (int i = stackTypes.length - 1; i >= 0; --i) {
            Type t = stackTypes[i];
            if (t != null) {
                super.visitVarInsn(t.getOpcode(Opcodes.ISTORE), callLocals.length + i);
            }
        }
        // PUSHs from local vars in reverse order.
        for (int i = 0; i < stackTypes.length; ++i) {
            Type t = stackTypes[i];
            if (t != null) {
                super.visitVarInsn(t.getOpcode(Opcodes.ILOAD), callLocals.length + i);
            }
        }
        super.visitLabel(start);
        super.visitFrame(Opcodes.F_NEW,
                handlerFrame.length, handlerFrame, callStack.length, callStack);
        action.run();
        super.visitLabel(end);

        callWrapInfo.add(new CallWrapInfo(start, stackTypes, localTypes,
                handler, handlerFrame, contIndexOnStack));
    }

    void emitCodeEpilogue() {
        int contArg = (hasThis ? 1 : 0);
        super.visitLabel(ccTableSwitch);
        super.visitFrame(Opcodes.F_NEW, frame0.length, frame0, 0, new Object[]{});
        if (callWrapInfo.isEmpty()) {
            super.visitInsn(Opcodes.ACONST_NULL);
            super.visitVarInsn(Opcodes.ASTORE, contArg);
            super.visitJumpInsn(Opcodes.GOTO, frame1);
            return;
        }
        super.visitVarInsn(Opcodes.ALOAD, contArg);
        super.visitJumpInsn(Opcodes.IFNULL, frame1);
        Label defaultLabel = new Label();
        Label[] tableLabels = new Label[callWrapInfo.size()];
        Arrays.setAll(tableLabels, (i) -> new Label());

        super.visitVarInsn(Opcodes.ALOAD, contArg);
        super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, contDesc,
                contMethods.get("popJump").getName(),
                contMethods.get("popJump").getDescriptor(), false);
        super.visitTableSwitchInsn(0, tableLabels.length - 1, defaultLabel, tableLabels);
        for (int i = 0; i < tableLabels.length; i++) {
            super.visitLabel(tableLabels[i]);
            super.visitFrame(Opcodes.F_NEW, frame0.length, frame0, 0, new Object[]{});
            CallWrapInfo cwi = callWrapInfo.get(i);
            Label start = cwi.callStart;
            Type[] stackVars = cwi.getStack();
            Type[] localVars = cwi.getLocals();
            Label handler = cwi.getHandler();
            Object[] handlerFrame = cwi.getHandlerFrame();
            int contIndexOnStack = cwi.getContIndexOnStack();
            // Copy cont reference as contArg is set null during restoration.
            int contCopy = localVars.length + stackVars.length;
            super.visitVarInsn(Opcodes.ALOAD, contArg);
            super.visitVarInsn(Opcodes.ASTORE, contCopy);
            for (int j = 0; j < stackVars.length; ++j) {
                Type t = stackVars[j];
                if (t != null) {
                    Method pop = getPopMethod(t.getSort());
                    super.visitVarInsn(Opcodes.ALOAD, contCopy);
                    if (j == contIndexOnStack) {
                        // DUP current cont bcoz popObject is compulsarily called.
                        super.visitInsn(Opcodes.DUP);
                        super.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                                contDesc, pop.getName(), pop.getDescriptor(), false);
                        super.visitInsn(Opcodes.POP);
                        super.visitInsn(Opcodes.ACONST_NULL);
                        super.visitVarInsn(Opcodes.ASTORE, localVars.length + j);
                    } else {
                        super.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                                contDesc, pop.getName(), pop.getDescriptor(), false);
                        if (t.getSort() == Type.OBJECT || t.getSort() == Type.ARRAY) {
                            super.visitTypeInsn(Opcodes.CHECKCAST, t.getInternalName());
                        }
                        super.visitInsn(Opcodes.DUP);
                        super.visitVarInsn(t.getOpcode(Opcodes.ISTORE), localVars.length + j);
                    }
                }
            }
            for (int j = 0; j < localVars.length; ++j) {
                Type t = localVars[j];
                if (t != null) {
                    Method pop = getPopMethod(t.getSort());
                    super.visitVarInsn(Opcodes.ALOAD, contCopy);
                    super.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                            contDesc, pop.getName(), pop.getDescriptor(), false);
                    if (t.getSort() == Type.OBJECT || t.getSort() == Type.ARRAY) {
                        super.visitTypeInsn(Opcodes.CHECKCAST, t.getInternalName());
                    }
                    super.visitVarInsn(t.getOpcode(Opcodes.ISTORE), j);
                }
            }
            // Set current cont arg as null to avoid capturing it in next continuation.
            super.visitInsn(Opcodes.ACONST_NULL);
            super.visitVarInsn(Opcodes.ASTORE, contArg);

            super.visitJumpInsn(Opcodes.GOTO, start);
            // Handler
            super.visitLabel(handler);
            super.visitFrame(Opcodes.F_NEW,
                    handlerFrame.length, handlerFrame, 1, new Object[]{dccException});
            // Get Cont from exception.
            super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, dccException,
                    getCont.getName(), getCont.getDescriptor(), false);
            // Order of local vars - Push index 0 last.
            for (int j = localVars.length - 1; j >= 0; --j) {
                Type t = localVars[j];
                if (t != null) {
                    super.visitInsn(Opcodes.DUP);
                    super.visitVarInsn(t.getOpcode(Opcodes.ILOAD), j);
                    Method push = getPushMethod(localVars[j].getSort());
                    super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, contDesc,
                            push.getName(), push.getDescriptor(), false);
                }
            }
            // Order of stack - Push end of array into Cont first. Index 0 gets pushed last.
            for (int j = stackVars.length - 1; j >= 0; --j) {
                Type t = stackVars[j];
                if (t != null) {
                    super.visitInsn(Opcodes.DUP);
                    super.visitVarInsn(t.getOpcode(Opcodes.ILOAD), localVars.length + j);
                    Method push = getPushMethod(stackVars[j].getSort());
                    super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, contDesc,
                            push.getName(), push.getDescriptor(), false);
                }
            }
            super.visitInsn(Opcodes.DUP);
            super.visitLdcInsn(i);
            super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, contDesc,
                    contMethods.get("pushJump").getName(),
                    contMethods.get("pushJump").getDescriptor(), false);
            // Create new exception. Cont object is on stack.
            mv.visitTypeInsn(Opcodes.NEW, dccException);
            mv.visitInsn(Opcodes.DUP_X1);
            mv.visitInsn(Opcodes.SWAP);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, dccException,
                    initException.getName(), initException.getDescriptor(), false);
            super.visitInsn(Opcodes.ATHROW);
        }

        super.visitLabel(defaultLabel);

        super.visitFrame(Opcodes.F_NEW, frame0.length, frame0, 0, new Object[]{});
        super.visitVarInsn(Opcodes.ALOAD, contArg);

        super.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                contDesc, contMethods.get("invalidCont").getName(),
                contMethods.get("invalidCont").getDescriptor(), false);
        // Dummy athrow of null. Control never reaches here.
        super.visitInsn(Opcodes.ACONST_NULL);
        super.visitInsn(Opcodes.ATHROW);
    }

    Method getPopMethod(int sort) {
        String name;
        switch (sort) {
            case Type.INT:
                name = "popInt";
                break;
            case Type.FLOAT:
                name = "popFloat";
                break;
            case Type.LONG:
                name = "popLong";
                break;
            case Type.DOUBLE:
                name = "popDouble";
                break;
            case Type.OBJECT:
            case Type.ARRAY:
                name = "popObject";
                break;
            default:
                throw new RuntimeException("Unknown Sort -> " + sort);
        }
        return contMethods.get(name);
    }

    Method getPushMethod(int sort) {
        String name;
        switch (sort) {
            case Type.INT:
                name = "pushInt";
                break;
            case Type.FLOAT:
                name = "pushFloat";
                break;
            case Type.LONG:
                name = "pushLong";
                break;
            case Type.DOUBLE:
                name = "pushDouble";
                break;
            case Type.OBJECT:
            case Type.ARRAY:
                name = "pushObject";
                break;
            default:
                throw new RuntimeException("Unknown Sort -> " + sort);
        }
        return contMethods.get(name);
    }

}
