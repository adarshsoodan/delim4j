package org.decon.test;

import org.decon.ContClassVisitor;
import org.decon.rt.Context;
import org.decon.util.BytesClassLoader;
import java.io.PrintWriter;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;
import org.decon.rt.Cc;
import org.decon.rt.Resumable;
import org.junit.Assert;

public class BasicTest {

//    @Test
    public void profile() throws Exception {
        String className = DummyClass.class.getCanonicalName();
        ClassReader reader = new ClassReader(className);
        for (int i = 0; i < 10 * 1000; ++i) {
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
            BiFunction<Function<Resumable, Object>, Object, Object> invoker
                    = (f, expected) -> {
                        try {
                            Class<?> c = (new BytesClassLoader()).fromBytes(className, b);
                            Object o = c.newInstance();
                            BiFunction<Context, Function<Context, Object>, Object> entry1
                            = (BiFunction<Context, Function<Context, Object>, Object>) o;

                            Object ret
                            = Context.start(
                                    (@Cc Context cont)
                                    -> entry1.apply(
                                            cont,
                                            (@Cc Context k)
                                            -> Context.capture(k, f)));
                            Assert.assertEquals(expected, ret);
                            return ret;
                        } catch (ClassNotFoundException |
                                 InstantiationException |
                                 IllegalAccessException ex) {
                            throw new RuntimeException(ex);
                        }
                    };
            invoker.apply(r -> 6, 6);
            invoker.apply(r -> r.resume("r.resume()"), -1);
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
