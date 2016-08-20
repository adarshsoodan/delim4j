package org.decon.test;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.decon.ContClassVisitor;
import org.decon.rt.Cc;
import org.decon.rt.Context;
import org.decon.rt.Resumable;
import org.decon.util.BytesClassLoader;
import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

public class ChangerTest {

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
            BiFunction<Function<Resumable, Object>, Object, Object> invoker = createInvoker(className, b);
            invoker.apply(r -> 6, 6);
            invoker.apply(r -> r.resume(-1), -1);
        }

        {
            Runnable display = () -> {
                ClassReader reader = new ClassReader(b);
                Printer printer = new Textifier();
                TraceClassVisitor tracer = new TraceClassVisitor(null, printer, null);
                reader.accept(tracer, ClassReader.EXPAND_FRAMES);
                // System.out.println(printer.getText());
            };
            display.run();
        }
    }

    private BiFunction<Function<Resumable, Object>, Object, Object> createInvoker(String className, byte[] b) {
        return (receiver, expected) -> {
            try {
                Class<?> c = (new BytesClassLoader()).fromBytes(className, b);
                Object o = c.newInstance();
                // o = new DummyClass();
                BiFunction<Context, Function<Context, Object>, Object> entry1 =
                        (BiFunction<Context, Function<Context, Object>, Object>) o;

                Function<Context, Object> action = (@Cc Context context) -> Context.capture(context, receiver);

                Object ret = Context.start((@Cc Context context) -> entry1.apply(context, action));
                Assert.assertEquals(expected, ret);
                return ret;
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        };

    }
}
