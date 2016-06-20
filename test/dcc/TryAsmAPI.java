package dcc;

import dcc.util.BytesClassLoader;
import java.io.PrintWriter;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

public class TryAsmAPI {

    @Test
    public void verifyNonCont() throws Exception {
        final String className = TryAsmAPI.class.getCanonicalName();
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ClassVisitor(Opcodes.ASM5, cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature,
                    String[] exceptions) {
                if ("<init>".equals(name) || "<clinit>".equals(name)) {
                    return super.visitMethod(access, name, desc, signature, exceptions);
                } else {
                    return new Changer("dcc/TryAsmAPI", access, name, desc,
                            super.visitMethod(access, name, desc, signature, exceptions));
                }
            }
        };
        {
            ClassReader reader = new ClassReader(className);
            reader.accept(cv, ClassReader.EXPAND_FRAMES);
        }
        byte[] b = cw.toByteArray();
        Class<?> c = (new BytesClassLoader()).defineClass("dcc.TryAsmAPI", b);
        {
            ClassReader reader = new ClassReader(b);
            PrintWriter printer = new PrintWriter(System.out);
            TraceClassVisitor tracer = new TraceClassVisitor(null, new Textifier(), printer);
            reader.accept(tracer, ClassReader.EXPAND_FRAMES | ClassReader.SKIP_DEBUG);
        }
    }

}
