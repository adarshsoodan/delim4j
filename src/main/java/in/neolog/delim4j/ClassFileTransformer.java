package in.neolog.delim4j;

import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class ClassFileTransformer implements java.lang.instrument.ClassFileTransformer {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer(), false);
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ContextClassVisitor(cw);
        ClassReader reader = new ClassReader(classfileBuffer);
        reader.accept(cv, ClassReader.EXPAND_FRAMES);
        byte[] b = cw.toByteArray();
        return b;
    }

}
