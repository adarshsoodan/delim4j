package dcc;

import dcc.rt.Cont;
import dcc.rt.DccException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.commons.Method;

public class Changer extends AnalyzerAdapter {

    static String dccException = "dcc/rt/DccException";
    static String contDesc = "dcc/rt/Cont";

    static AtomicBoolean methodsLoaded = new AtomicBoolean(false);
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
    public void visitCode() {
        super.visitCode();
        super.visitJumpInsn(Opcodes.GOTO, ccTableSwitch);
        super.visitLabel(frame1);
        super.visitFrame(Opcodes.F_NEW, frame0.length, frame0, 0, new Object[]{});
    }

    void visitCall(Runnable action, int contIndexOnStack) {
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

    boolean notContify(String name, String desc) {
        boolean ret = !(desc.startsWith("(Ldcc/rt/Cont;"));
        ret = ret || "<init>".equals(name) || "<clinit>".equals(name);
        ret = ret || locals.stream().anyMatch(
                v -> Opcodes.UNINITIALIZED_THIS.equals(v) || (v instanceof Label));
        ret = ret || stack.stream().anyMatch(
                v -> Opcodes.UNINITIALIZED_THIS.equals(v) || (v instanceof Label));
        return ret;
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        if (notContify(name, desc)) {
            super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
        } else {
            int contIndexOnStack = stack.size() - Type.getMethodType(desc).getArgumentTypes().length;
            visitCall(() -> super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs),
                    contIndexOnStack);
        }
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        if (notContify(name, desc)) {
            super.visitMethodInsn(opcode, owner, name, desc, itf);
        } else {
            int contIndexOnStack = stack.size() - Type.getMethodType(desc).getArgumentTypes().length;
            visitCall(() -> super.visitMethodInsn(opcode, owner, name, desc, itf),
                    contIndexOnStack);
        }
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        int contArg = (hasThis ? 1 : 0);
        super.visitLabel(ccTableSwitch);
        super.visitFrame(Opcodes.F_NEW, frame0.length, frame0, 0, new Object[]{});
        if (callWrapInfo.isEmpty()) {
            super.visitInsn(Opcodes.ACONST_NULL);
            super.visitVarInsn(Opcodes.ASTORE, contArg);
            super.visitJumpInsn(Opcodes.GOTO, frame1);
            super.visitMaxs(maxStack, maxLocals);
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
            // TODO Locate cont position on stack for method called after label start.
            // TODO After putting current cont on stack, make localVars[contArg and contIndexOnStack] null.
            for (int j = 0; j < stackVars.length; ++j) {
                if (j == contIndexOnStack) {
                    super.visitVarInsn(Opcodes.ALOAD, contArg);
                    super.visitInsn(Opcodes.ACONST_NULL);
                    super.visitVarInsn(Opcodes.ASTORE, localVars.length + j);
                    super.visitInsn(Opcodes.ACONST_NULL);
                    super.visitVarInsn(Opcodes.ASTORE, contArg);
                } else {
                    Type t = stackVars[j];
                    if (t != null) {
                        Method pop = getPopMethod(t.getSort());
                        super.visitVarInsn(Opcodes.ALOAD, contArg);
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
                    super.visitVarInsn(Opcodes.ALOAD, contArg);
                    super.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                            contDesc, pop.getName(), pop.getDescriptor(), false);
                    if (t.getSort() == Type.OBJECT || t.getSort() == Type.ARRAY) {
                        super.visitTypeInsn(Opcodes.CHECKCAST, t.getInternalName());
                    }
                    super.visitVarInsn(t.getOpcode(Opcodes.ISTORE), j);
                }
            }
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
        super.visitMaxs(maxStack, maxLocals);
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
