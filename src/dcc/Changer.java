package dcc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AnalyzerAdapter;

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
    Label frame1Label = new Label();

    public Changer(int api, String owner, int access, String name, String desc, MethodVisitor mv) {
        super(api, owner, access, name, desc, mv);
        if ((access & Opcodes.ACC_STATIC) == 0) {
            frame0.add(owner);
        }
        for (final Type t : Type.getArgumentTypes(desc)) {
            Object f = FrameT.fromType(t);
            frame0.add(f);
            if (f.equals(Opcodes.LONG) || f.equals(Opcodes.DOUBLE)) {
                frame0.add(Opcodes.TOP);
            }
        }
    }

    @Override
    public void visitCode() {
        super.visitCode();
        super.visitJumpInsn(Opcodes.GOTO, ccTableSwitch);
        super.visitLabel(frame1Label);
        super.visitFrame(Opcodes.F_NEW, frame0.size(), frame0.toArray(), 0, new Object[]{});
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        final int numLocals = locals.size();
        final List<Object> callLocals = new ArrayList<>(locals);
        final List<Object> callStack = new ArrayList<>(stack);
        callLocals.addAll(stack);

        final Label start = new Label();
        final Label end = new Label();
        final Label handler = new Label();
        super.visitTryCatchBlock(start, end, handler, dccException);

        super.visitJumpInsn(Opcodes.GOTO, start);
        super.visitLabel(start);
        // TODO Are local vars extendable without changing previous frame?
        // TODO What is the order of stack vars?
        super.visitFrame(Opcodes.F_NEW, callLocals.size(),
                callLocals.toArray(), callStack.size(), callStack.toArray());
        final List<Type> stackTypes
                = callStack.stream().map(FrameT::fromFrame).collect(Collectors.toList());

        // POPs to localvars.
        for (int i = 0; i < callStack.size(); ++i) {
            Type t = stackTypes.get(i);
            if (t != null) {
                super.visitVarInsn(t.getOpcode(Opcodes.ILOAD), numLocals + i);
            }
        }
        // PUSHs same values from local vars in reverse order.
        for (int i = callStack.size() - 1; i >= 0; --i) {
            Type t = stackTypes.get(i);
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
        tableswitch  -> insert label cc-tableswitch
                insert frame-0-map
                create a switch over pos
                ... each-pos -> Retrieve local-vars
                                push stack-vars in same order from local-vars
                                goto start-call-label

         */
        super.visitLabel(ccTableSwitch);
        super.visitFrame(Opcodes.F_NEW, frame0.size(), frame0.toArray(), 0, new Object[]{});
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
/*
    int ACC_PUBLIC = 0x0001; // class, field, method
    int ACC_PRIVATE = 0x0002; // class, field, method
    int ACC_PROTECTED = 0x0004; // class, field, method
    int ACC_STATIC = 0x0008; // field, method
    int ACC_FINAL = 0x0010; // class, field, method, parameter
    int ACC_SUPER = 0x0020; // class
    int ACC_SYNCHRONIZED = 0x0020; // method
    int ACC_VOLATILE = 0x0040; // field
    int ACC_BRIDGE = 0x0040; // method
    int ACC_VARARGS = 0x0080; // method
    int ACC_TRANSIENT = 0x0080; // field
    int ACC_NATIVE = 0x0100; // method
    int ACC_INTERFACE = 0x0200; // class
    int ACC_ABSTRACT = 0x0400; // class, method
    int ACC_STRICT = 0x0800; // method
    int ACC_SYNTHETIC = 0x1000; // class, field, method, parameter
    int ACC_ANNOTATION = 0x2000; // class
    int ACC_ENUM = 0x4000; // class(?) field inner
    int ACC_MANDATED = 0x8000; // parameter
 */
