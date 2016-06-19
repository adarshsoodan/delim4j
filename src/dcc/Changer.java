package dcc;

import dcc.rt.Cont;
import dcc.rt.DccException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.commons.Method;

/*
All frames are in expanded form(i.e. F_NEW) due to requirement of AnalyzerAdapter.

Begin method -> Store frame-0-map of args for cc-tableswitch.
                create label cc-tableswitch
                insert goto cc-tableswitch after frame-0-map
                create and insert label for frame 1
                copy and insert frame-0-map to frame-1-map

Visit calls  -> retrieve stack-var-types and count
                Append count local-var-types with stack-var-types vals
                create start-call-label
                insert goto start-call-label
                insert start-call-label
                insert call-frame-map with new local-var-types and same stack-var-types
                pop corresponding stack-vars and store in local-vars
                push stack-vars in same order from local-vars
                create and insert end-call-label
Catch block  -> create and insert handler-label with correct exception type ...
                insert call-frame-map with same local-var-types and stack-var-types with lone exception object
                associate with start-call-label and end-call-label
                ... create code ...
                throw cc-exception with local-vars and pos data.
tableswitch  -> insert label cc-tableswitch
                insert frame-0-map
                create a switch over pos
                ... each-pos -> Retrieve local-vars
                                push stack-vars in same order from local-vars
                                goto start-call-label
 */
public class Changer extends AnalyzerAdapter {

    static String dccException = "dcc/rt/DccException";
    static String contDesc = "dcc/rt/Cont";

    List<Object> frame0 = new ArrayList<>();
    Label ccTableSwitch = new Label();

    List<CallWrapInfo> callWrapInfo = new ArrayList<>();
    boolean hasThis;

    static AtomicBoolean methodsLoaded = new AtomicBoolean(false);
    static Map<String, Method> contFields = new HashMap<>();
    static Method getCont;
    static Method initException;

    public Changer(String owner, int access, String name, String desc, MethodVisitor mv) {
        super(Opcodes.ASM5, owner, access, name, desc, mv);
        frame0.addAll(locals);
        hasThis = ((access & Opcodes.ACC_STATIC) == 0);
        if (methodsLoaded.get() == false) {
            Arrays.stream(Cont.class.getDeclaredMethods()).forEach(m -> {
                contFields.put(m.getName(), Method.getMethod(m));
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
        Label frame1Label = new Label();
        super.visitLabel(frame1Label);
        super.visitFrame(Opcodes.F_NEW, frame0.size(), frame0.toArray(), 0, new Object[]{});

        Type[] localTypes = FrameT.fromFrame(frame0);
        callWrapInfo.add(new CallWrapInfo(frame1Label, new Type[]{}, localTypes, null, null));
    }

    void visitCall(Runnable action) {
        final int numLocals = locals.size();
        final List<Object> callLocals = new ArrayList<>(locals);
        // callLocals.addAll(stack);         // TODO Is extension of local vars needed?
        // TODO Are local vars extendable without changing previous frame?
        // Order of Stack - Last poppable value at index 0. First poppable value at end of array.
        final List<Object> callStack = new ArrayList<>(stack);

        final Label start = new Label();
        final Label end = new Label();
        final Label handler = new Label();
        super.visitTryCatchBlock(start, end, handler, dccException);

        super.visitJumpInsn(Opcodes.GOTO, start);
        super.visitLabel(start);
        super.visitFrame(Opcodes.F_NEW, callLocals.size(),
                callLocals.toArray(), callStack.size(), callStack.toArray());

        Type[] localTypes = FrameT.fromFrame(callLocals);
        Type[] stackTypes = FrameT.fromFrame(callStack);

        // POPs to localvars.
        for (int i = stackTypes.length - 1; i >= 0; --i) {
            Type t = stackTypes[i];
            if (t != null) {
                super.visitVarInsn(t.getOpcode(Opcodes.ISTORE), numLocals + i);
            }
        }
        // PUSHs from local vars in reverse order.
        for (int i = 0; i < stackTypes.length; ++i) {
            Type t = stackTypes[i];
            if (t != null) {
                super.visitVarInsn(t.getOpcode(Opcodes.ILOAD), numLocals + i);
            }
        }
        action.run();
        super.visitLabel(end);

        callLocals.addAll(callStack);
        callWrapInfo.add(new CallWrapInfo(start, stackTypes, localTypes, handler, callLocals));
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        if ("<init>".equals(name) || "<clinit>".equals(name)) {
            super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
        } else {
            visitCall(() -> super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs));
        }
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        if ("<init>".equals(name) || "<clinit>".equals(name)) {
            super.visitMethodInsn(opcode, owner, name, desc, itf);
        } else {
            visitCall(() -> super.visitMethodInsn(opcode, owner, name, desc, itf));
        }
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitLabel(ccTableSwitch);
        super.visitFrame(Opcodes.F_NEW, frame0.size(), frame0.toArray(), 0, new Object[]{});
        int contVar = (hasThis ? 1 : 0);

        Label defaultLabel = new Label();
        Label[] tableLabels = new Label[callWrapInfo.size()];
        IntStream.range(0, tableLabels.length).forEach(i -> tableLabels[i] = new Label());

        super.visitVarInsn(Opcodes.ALOAD, contVar);
        super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, contDesc,
                contFields.get("popJump").getName(),
                contFields.get("popJump").getDescriptor(), false);
        super.visitTableSwitchInsn(0, tableLabels.length - 1, defaultLabel, tableLabels);
        for (int i = 0; i < tableLabels.length; i++) {
            super.visitLabel(tableLabels[i]);
            super.visitFrame(Opcodes.F_NEW, frame0.size(), frame0.toArray(), 0, new Object[]{});
            Label start = callWrapInfo.get(i).callStart;
            Type[] stackVars = callWrapInfo.get(i).getStack();
            Type[] localVars = callWrapInfo.get(i).getLocals();
            Label handler = callWrapInfo.get(i).getHandler();
            List<Object> handlerFrame = callWrapInfo.get(i).getHandlerFrame();
            for (Type t : stackVars) {
                if (t != null) {
                    Method pop = getPopMethod(t.getSort());
                    super.visitVarInsn(Opcodes.ALOAD, contVar);
                    super.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                            contDesc, pop.getName(), pop.getDescriptor(), false);
                    if (t.getSort() == Type.OBJECT || t.getSort() == Type.ARRAY) {
                        super.visitTypeInsn(Opcodes.CHECKCAST, t.getDescriptor());
                    }
                }
            }
            for (int j = 0; j < localVars.length; ++j) {
                Type t = localVars[j];
                if (t != null) {
                    Method pop = getPopMethod(t.getSort());
                    super.visitVarInsn(Opcodes.ALOAD, contVar);
                    super.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                            contDesc, pop.getName(), pop.getDescriptor(), false);
                    if (t.getSort() == Type.OBJECT || t.getSort() == Type.ARRAY) {
                        super.visitTypeInsn(Opcodes.CHECKCAST, t.getDescriptor());
                    }
                    super.visitVarInsn(t.getOpcode(Opcodes.ISTORE), j);
                }
            }
            super.visitJumpInsn(Opcodes.GOTO, start);
            // Handler
            super.visitLabel(handler);
            super.visitFrame(Opcodes.F_NEW, handlerFrame.size(),
                    handlerFrame.toArray(), 1, new Object[]{dccException});
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
                    super.visitVarInsn(t.getOpcode(Opcodes.ILOAD), j + localVars.length);
                    Method push = getPushMethod(stackVars[j].getSort());
                    super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, contDesc,
                            push.getName(), push.getDescriptor(), false);
                }
            }
            super.visitInsn(Opcodes.DUP);
            super.visitLdcInsn(i);
            super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, contDesc,
                    contFields.get("pushJump").getName(),
                    contFields.get("pushJump").getDescriptor(), false);
            // Create new exception. Cont object is on stack.
            mv.visitTypeInsn(Opcodes.NEW, dccException);
            mv.visitInsn(Opcodes.DUP_X1);
            mv.visitInsn(Opcodes.SWAP);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, dccException,
                    initException.getName(), initException.getDescriptor(), false);
            super.visitInsn(Opcodes.ATHROW);
        }

        super.visitLabel(defaultLabel);

        super.visitFrame(Opcodes.F_NEW, frame0.size(), frame0.toArray(), 0, new Object[]{});
        super.visitVarInsn(Opcodes.ALOAD, contVar);

        super.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                "Ldcc/rt/Cont;", contFields.get("invalidCont").getName(),
                contFields.get("invalidCont").getDescriptor(), false);

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
        return contFields.get(name);
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
        return contFields.get(name);
    }

}

/* Primitive types are represented by Opcodes.TOP, Opcodes.INTEGER
   , Opcodes.FLOAT, Opcodes.LONG, Opcodes.DOUBLE, Opcodes.NULL
   or Opcodes.UNINITIALIZED_THIS (long and double are represented by a single element).
   Reference types are represented by String objects (representing internal names)
 */
//  visitTryCatchBlock must be called before the labels passed as arguments have been visited
