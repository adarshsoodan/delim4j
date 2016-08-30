package org.decon.test;

import java.io.IOException;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.decon.ContextClassVisitor;
import org.decon.rt.Cc;
import org.decon.rt.Context;
import org.decon.rt.Resumable;
import org.decon.util.BytesClassLoader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

public class ChangerTest {

    private static BytesClassLoader loader;

    @BeforeClass
    public static void setupClass() {
        loader = new BytesClassLoader();
    }

    @Test
    public void verifyChanged() {
        String className = DummyClass.class.getCanonicalName();
        BiFunction<Function<Resumable, Object>, Object, Object> invoker = createInvoker(changed(className));
        invoker.apply(r -> 6, 6);
        invoker.apply(r -> r.resume(-1), -1);
    }

    @Test(expected = IllegalStateException.class)
    public void verifyUnchanged() {
        String className = DummyClass.class.getCanonicalName();
        BiFunction<Function<Resumable, Object>, Object, Object> invoker = createInvoker(unchanged(className));
        invoker.apply(r -> 6, 6);
        invoker.apply(r -> r.resume(-1), -1);
    }

    public void printCode() {
        String className = DummyClass.class.getCanonicalName();
        ClassReader reader;
        try {
            reader = new ClassReader(className);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Printer printer = new Textifier();
        TraceClassVisitor tracer = new TraceClassVisitor(null, printer, null);
        reader.accept(tracer, ClassReader.EXPAND_FRAMES);
        // System.out.println(printer.getText());
    }

    private BiFunction<Context, Function<Context, Object>, Object> changed(String className) {
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ContextClassVisitor(cw);
        try {
            ClassReader reader = new ClassReader(className);
            reader.accept(cv, ClassReader.EXPAND_FRAMES);
            byte[] b = cw.toByteArray();
            Class<?> c;
            c = loader.fromBytes(className, b);
            Object o = c.newInstance();
            return (BiFunction<Context, Function<Context, Object>, Object>) o;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BiFunction<Context, Function<Context, Object>, Object> unchanged(String className) {
        try {
            Object o = Class.forName(className).newInstance();
            return (BiFunction<Context, Function<Context, Object>, Object>) o;
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private BiFunction<Function<Resumable, Object>, Object, Object>
            createInvoker(BiFunction<Context, Function<Context, Object>, Object> o) {
        return (receiver, expected) -> {
            Function<Context, Object> action = (@Cc Context context) -> Context.capture(context, receiver);

            Object ret = Context.start((@Cc Context context) -> o.apply(context, action));
            Assert.assertEquals(expected, ret);
            return ret;
        };

    }
}
