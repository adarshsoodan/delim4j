package in.neolog.delim4j;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import in.neolog.delim4j.rt.Context;

public class ContextClassVisitor extends ClassVisitor {

    String className;

    public ContextClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.className = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        // TODO skip (access & ACC_NATIVE) methods
        if ("<init>".equals(name) || "<clinit>".equals(name) || !desc.startsWith(Context.argDesc)) {
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
        return new CodeTransformer(this.className, access, name, desc,
                super.visitMethod(access, name, desc, signature, exceptions));
    }

}
