package dcc;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ContClassVisitor extends ClassVisitor {

    String name;

    public ContClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName,
            String[] interfaces) {
        this.name = name;
        super.visit(version, access, name, signature, superName, interfaces); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature,
            String[] exceptions) {
        Type[] argTypes = Type.getArgumentTypes(desc);
        if ("<init>".equals(name) || "<clinit>".equals(name)
                || argTypes.length == 0
                || argTypes[0].getSort() != Type.OBJECT
                || !argTypes[0].getInternalName().equals("dcc/rt/Cont")) {
            return super.visitMethod(access, name, desc, signature, exceptions);
        } else {
            return new Changer(this.name, access, name, desc,
                    super.visitMethod(access, name, desc, signature, exceptions));
        }
    }

}