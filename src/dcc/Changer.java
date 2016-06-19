package dcc;

import dcc.rt.Cont;
import dcc.rt.DccException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;
import java.util.stream.Stream;
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

    public static final String dccException = "Ldcc/rt/DccException;";
    public static final String contDesc = "Ldcc/rt/Cont;";

    List<Object> frame0 = new ArrayList<>();
    Label ccTableSwitch = new Label();
    List<Label> jumpLabels = new ArrayList<>();
    List<Type[]> jumpStacks = new ArrayList<>();
    List<Type[]> jumpLocals = new ArrayList<>();
    boolean hasThis;

    static AtomicBoolean methodsLoaded = new AtomicBoolean(false);
    Map<String, Method> contFields = new HashMap<>();
    Method getCont;
    Method initException;

    public Changer(int api, String owner, int access, String name, String desc, MethodVisitor mv) {
        super(api, owner, access, name, desc, mv);
        frame0.addAll(locals);
        hasThis = ((access & Opcodes.ACC_STATIC) == 0);
        if (methodsLoaded.get() == false) {
            Stream.of("popJump", "popInt", "popFLoat", "popLong", "popDouble", "popObject",
                    "pushJump", "pushInt", "pushFLoat", "pushLong", "pushDouble", "pushObject",
                    "invalidCont")
                    .forEach(s -> {
                        try {
                            contFields.put(s, Method.getMethod(Cont.class.getMethod(s)));
                        } catch (NoSuchMethodException | SecurityException ex) {
                            throw new RuntimeException(ex);
                        }
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

        jumpLabels.add(frame1Label);
        jumpStacks.add(new Type[]{});
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        final int numLocals = locals.size();
        final List<Object> callLocals = new ArrayList<>(locals);
        // callLocals.addAll(stack);         // TODO Is extension of local vars needed?
        // TODO Are local vars extendable without changing previous frame?
        // TODO Order of Stack : Last poppable value at index 0. First poppable value at end of array.
        final List<Object> callStack = new ArrayList<>(stack);

        final Label start = new Label();
        final Label end = new Label();
        final Label handler = new Label();
        super.visitTryCatchBlock(start, end, handler, dccException);

        super.visitJumpInsn(Opcodes.GOTO, start);
        super.visitLabel(start);
        super.visitFrame(Opcodes.F_NEW, callLocals.size(),
                callLocals.toArray(), callStack.size(), callStack.toArray());

        final Type[] localTypes = FrameT.fromFrame(callLocals);
        final Type[] stackTypes = FrameT.fromFrame(callStack);
        jumpLabels.add(start);
        jumpLocals.add(localTypes);
        jumpStacks.add(stackTypes);

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
        super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
        super.visitLabel(end);

        super.visitLabel(handler);
        callLocals.addAll(callStack);
        super.visitFrame(Opcodes.F_NEW, callLocals.size(),
                callLocals.toArray(), 1, new Object[]{dccException});
        // TODO ... create code ... Push local vars and then stack vars
        // Get Cont from exception.
        super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, dccException,
                getCont.getName(), getCont.getDescriptor(), false);
        // TODO Order of local vars. Push index 0 last.
        for (int i = localTypes.length; i >= 0; --i) {
            Type t = localTypes[i];
            if (t != null) {
                super.visitInsn(Opcodes.DUP);
                super.visitVarInsn(t.getOpcode(Opcodes.ILOAD), i);
                Method push = getPushMethod(localTypes[i].getSort());
                super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, contDesc,
                        push.getName(), push.getDescriptor(), false);
            }
        }
        // TODO Order of stack. Push end of array into Cont first. Index 0 gets pushed last.
        for (int i = stackTypes.length; i >= 0; --i) {
            Type t = stackTypes[i];
            if (t != null) {
                super.visitInsn(Opcodes.DUP);
                super.visitVarInsn(t.getOpcode(Opcodes.ILOAD), i + numLocals);
                Method push = getPushMethod(stackTypes[i].getSort());
                super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, contDesc,
                        push.getName(), push.getDescriptor(), false);
            }
        }
        super.visitInsn(Opcodes.DUP);
        super.visitLdcInsn(jumpLabels.size() - 1);
        super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, contDesc,
                contFields.get("pushJump").getName(),
                contFields.get("pushJump").getDescriptor(), false);
        // TODO Create new exception. Cont object is on stack.
        super.visitInsn(Opcodes.ATHROW);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        super.visitLabel(ccTableSwitch);
        super.visitFrame(Opcodes.F_NEW, frame0.size(), frame0.toArray(), 0, new Object[]{});
        int contVar = (hasThis ? 1 : 0);

        Label defaultLabel = new Label();
        Label[] tableLabels = new Label[jumpLabels.size()];
        IntStream.range(0, tableLabels.length).forEach(i -> tableLabels[i] = new Label());

        super.visitVarInsn(Opcodes.ALOAD, contVar);
        super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, contDesc,
                contFields.get("popJump").getName(),
                contFields.get("popJump").getDescriptor(), false);
        super.visitTableSwitchInsn(0, tableLabels.length - 1, defaultLabel, tableLabels);
        for (int i = 0; i < tableLabels.length; i++) {
            super.visitLabel(tableLabels[i]);
            Label start = jumpLabels.get(i);
            Type[] jumpStack = jumpStacks.get(i);
            Type[] jumpVars = jumpLocals.get(i);
            for (Type t : jumpStack) {
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
            for (int j = 0; j < jumpVars.length; ++j) {
                Type t = jumpVars[j];
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
        }

        super.visitLabel(defaultLabel);
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
