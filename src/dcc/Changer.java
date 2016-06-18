package dcc;

import dcc.rt.Cont;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    List<Object> frame0 = new ArrayList<>();
    Label ccTableSwitch = new Label();
    List<Type[]> jumpStacks = new ArrayList<>();
    List<Label> jumpLabels = new ArrayList<>();
    boolean hasThis;
    final Method popJump;

    public Changer(int api, String owner, int access, String name, String desc, MethodVisitor mv) {
        super(api, owner, access, name, desc, mv);
        frame0.addAll(locals);
        hasThis = ((access & Opcodes.ACC_STATIC) == 0);
        try {
            popJump = Method.getMethod(Cont.class.getMethod("popJump"));
        } catch (NoSuchMethodException | SecurityException ex) {
            throw new RuntimeException(ex);
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
        final List<Object> callStack = new ArrayList<>(stack);

        final Label start = new Label();
        final Label end = new Label();
        final Label handler = new Label();
        super.visitTryCatchBlock(start, end, handler, dccException);

        super.visitJumpInsn(Opcodes.GOTO, start);
        super.visitLabel(start);
        // TODO What is the order of stack vars?
        super.visitFrame(Opcodes.F_NEW, callLocals.size(),
                callLocals.toArray(), callStack.size(), callStack.toArray());

        final Type[] stackTypes = FrameT.fromFrame(callStack);
        jumpLabels.add(start);
        jumpStacks.add(stackTypes);

        // POPs to localvars.
        for (int i = 0; i < callStack.size(); ++i) {
            Type t = stackTypes[i];
            if (t != null) {
                super.visitVarInsn(t.getOpcode(Opcodes.ILOAD), numLocals + i);
            }
        }
        // PUSHs to local vars in reverse order.
        for (int i = callStack.size() - 1; i >= 0; --i) {
            Type t = stackTypes[i];
            if (t != null) {
                super.visitVarInsn(t.getOpcode(Opcodes.ISTORE), numLocals + i);
            }
        }
        super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
        super.visitLabel(end);

        super.visitLabel(handler);
        super.visitFrame(Opcodes.F_NEW, callLocals.size(),
                callLocals.toArray(), 1, new Object[]{dccException});
        // TODO ... create code ...

        super.visitInsn(Opcodes.ATHROW);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        /*
        TODO tableswitch  -> insert label cc-tableswitch
                insert frame-0-map
                create a switch over pos
                ... each-pos -> Retrieve local-vars
                                push stack-vars in same order from local-vars
                                goto start-call-label

         */
        super.visitLabel(ccTableSwitch);
        super.visitFrame(Opcodes.F_NEW, frame0.size(), frame0.toArray(), 0, new Object[]{});
        super.visitVarInsn(Opcodes.ALOAD, (hasThis ? 1 : 0));
        super.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                "Ldcc/rt/Cont;", popJump.getName(), popJump.getDescriptor(), false);

        Label defaultLabel = new Label();
        super.visitTableSwitchInsn(0, jumpLabels.size() - 1, defaultLabel,
                jumpLabels.toArray(new Label[]{}));
        for (Type[] ts : jumpStacks) {
            for (Type t : ts) {
                switch (t.getSort()) {

                }
            }
        }
        /*
        public void visitTableSwitchInsn(int min,
                                 int max,
                                 Label dflt,
                                 Label... labels)

         Visits a TABLESWITCH instruction.

         Parameters:
            min - the minimum key value.
            max - the maximum key value.
            dflt - beginning of the default handler block.
            labels - beginnings of the handler blocks.
                     labels[i] is the beginning of the handler block for the min + i key. 
         */
        super.visitMaxs(maxStack, maxLocals);
    }
}

/* Primitive types are represented by Opcodes.TOP, Opcodes.INTEGER
   , Opcodes.FLOAT, Opcodes.LONG, Opcodes.DOUBLE,Opcodes.NULL
   or Opcodes.UNINITIALIZED_THIS (long and double are represented by a single element).
   Reference types are represented by String objects (representing internal names)
 */
//  visitTryCatchBlock must be called before the labels passed as arguments have been visited
