package dc4j;

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

Visit calls  -> create start-call-label
                insert goto start-call-label
                insert start-call-label
                retrieve stack-var-types and count
                Append count local-var-types with stack-var-types vals
                insert call-frame-map with new local-var-types and same stack-var-types
                pop corresponding stack-vars and store in local-vars
                push stack-vars in same order from local-vars
                create and insert end-call-label
Catch block  -> create and insert handler-label
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

    List<Object> frame0 = new ArrayList<>();
    Label ccTableSwitch = new Label();
    Label frame1Label = new Label();

    public Changer(int api, String owner, int access, String name, String desc, MethodVisitor mv) {
        super(api, owner, access, name, desc, mv);
        final Type[] types = Type.getArgumentTypes(desc);
        /* Primitive types are represented by Opcodes.TOP, Opcodes.INTEGER
           , Opcodes.FLOAT, Opcodes.LONG, Opcodes.DOUBLE,Opcodes.NULL
           or Opcodes.UNINITIALIZED_THIS (long and double are represented by a single element).
          Reference types are represented by String objects (representing internal names)*/
        // TBD 'this' type is first local var in non-static methods.
        for (final Type t : types) {
            switch (t.getSort()) {
                case Type.INT:
                case Type.BOOLEAN:
                case Type.BYTE:
                case Type.CHAR:
                    frame0.add(Opcodes.INTEGER);
                    break;
                case Type.FLOAT:
                    frame0.add(Opcodes.FLOAT);
                    break;
                case Type.LONG:
                    frame0.add(Opcodes.LONG);
                    frame0.add(Opcodes.TOP);
                    break;
                case Type.DOUBLE:
                    frame0.add(Opcodes.DOUBLE);
                    frame0.add(Opcodes.TOP);
                    break;
                case Type.ARRAY:
                case Type.OBJECT:
                    frame0.add(t.getDescriptor());
                    break;
            }
        }
    }

    void printState() {
        locals.stream().map(x -> x.toString()).collect(Collectors.joining(", "));
        stack.stream().map(x -> x.toString()).collect(Collectors.joining(", "));
    }

    @Override
    public void visitCode() {
        super.visitCode();
        super.visitJumpInsn(Opcodes.GOTO, ccTableSwitch);
        super.visitLabel(frame1Label);
        super.visitFrame(Opcodes.F_NEW, frame0.size(), frame0.toArray(), 0, new Object[]{});
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
        super.visitInvokeDynamicInsn(name, desc, bsm, bsmArgs);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }

}
