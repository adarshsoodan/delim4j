package dcc;

import dcc.rt.Cont;
import dcc.util.BytesClassLoader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

public class TryAsmAPI {

    public int dummyMethod(Cont cont) throws Exception {
        final String className = TryAsmAPI.class.getCanonicalName();
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ClassVisitor(Opcodes.ASM5, cw) {
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
        {
            Class<?> c = (new BytesClassLoader()).fromBytes("dcc.TryAsmAPI", b);
            Object o = c.newInstance();
//            Map<String, Method> ms = Arrays.stream(c.getDeclaredMethods())
//                    .collect(Collectors.toMap(m -> m.getName(), m -> m));
//            Method m = ms.get("dummyMethod");
//            m.invoke(o);
        }
        {
            ClassReader reader = new ClassReader(b);
            PrintWriter printer = new PrintWriter(System.out);
            TraceClassVisitor tracer = new TraceClassVisitor(null, new Textifier(), printer);
            reader.accept(tracer, ClassReader.EXPAND_FRAMES | ClassReader.SKIP_DEBUG);
        }
        return Integer.parseInt("0");
    }

    @Test
    public void verifyNonCont() throws Exception {
        final String className = TryAsmAPI.class.getCanonicalName();
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ClassVisitor(Opcodes.ASM5, cw) {
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
        {
            Class<?> c = (new BytesClassLoader()).fromBytes("dcc.TryAsmAPI", b);
            Object o = c.newInstance();
            Map<String, Method> ms = Arrays.stream(c.getDeclaredMethods())
                    .collect(Collectors.toMap(m -> m.getName(), m -> m));
            Method m = ms.get("dummyMethod");
            m.invoke(o, (Object) null);
        }
        {
            ClassReader reader = new ClassReader(b);
            PrintWriter printer = new PrintWriter(System.out);
            TraceClassVisitor tracer = new TraceClassVisitor(null, new Textifier(), printer);
            reader.accept(tracer, ClassReader.EXPAND_FRAMES | ClassReader.SKIP_DEBUG);
        }
    }

}
