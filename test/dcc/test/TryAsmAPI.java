package dcc.test;

import dcc.ContClassVisitor;
import dcc.rt.Context;
import dcc.test.data.DummyClass;
import dcc.util.BytesClassLoader;
import java.io.PrintWriter;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;
import dcc.rt.Cc;

public class TryAsmAPI {

    @Test
    public void profile() throws Exception {
        String className = DummyClass.class.getCanonicalName();
        ClassReader reader = new ClassReader(className);
        for (int i = 0; i < 100000; ++i) {
            ClassWriter cw = new ClassWriter(0);
            ClassVisitor cv = new ContClassVisitor(cw);
            reader.accept(cv, ClassReader.EXPAND_FRAMES);
            cw.toByteArray();
        }
    }

    @Test
    public void verifyNonCont() throws Exception {
        String className = DummyClass.class.getCanonicalName();
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ContClassVisitor(cw);
        {
            ClassReader reader = new ClassReader(className);
            reader.accept(cv, ClassReader.EXPAND_FRAMES);
        }
        byte[] b = cw.toByteArray();
        {
            Runnable invoker = () -> {
                try {
                    Class<?> c = (new BytesClassLoader()).fromBytes(className, b);
                    Object o = c.newInstance();
                    BiFunction<Context, Function<Context, Object>, Object> entry1
                            = (BiFunction<Context, Function<Context, Object>, Object>) o;
                    System.out.println(
                            Context.start(
                                    (@Cc Context cont)
                                    -> entry1.apply(cont,
                                                    (@Cc Context k)
                                                    -> Context.capture(k, r -> r.resume(6)))));
                } catch (ClassNotFoundException |
                         InstantiationException |
                         IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            };
            invoker.run();
        }
//        Files.write(
//                Paths.get("C:\\Users\\user\\Desktop\\workspaces\\tmp\\DummyClass.class"),
//                b, StandardOpenOption.CREATE_NEW);
        {
            Runnable display = () -> {
                ClassReader reader = new ClassReader(b);
                PrintWriter printer = new PrintWriter(System.out);
                TraceClassVisitor tracer = new TraceClassVisitor(null, new Textifier(), printer);
                reader.accept(tracer, ClassReader.EXPAND_FRAMES);
            };
//            display.run();
        }
    }
}
