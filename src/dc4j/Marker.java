package dc4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.tree.ClassNode;

/*
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
public class Marker extends ClassNode {

    public Marker() {
        super(Opcodes.ASM5);
    }

    final List<Object> methods = new ArrayList<Object>();

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        final AnalyzerAdapter mv
                = new AnalyzerAdapter(Opcodes.ASM5, this.name, access, name, desc, null) {

            void printState() {
                locals.stream().map(x -> x.toString()).collect(Collectors.joining(", "));
                stack.stream().map(x -> x.toString()).collect(Collectors.joining(", "));
            }

            @Override
            public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
                printState();
            }

            @Override
            public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                printState();
            }

        };
        return mv;
    }

}
