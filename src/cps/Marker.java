package cps;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.tree.ClassNode;

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
