package dcc.test;

import dcc.ContClassVisitor;
import dcc.rt.Cont;
import dcc.rt.Contify;
import dcc.test.data.DummyClass;
import dcc.util.BytesClassLoader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

public class TryAsmAPI {

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
                    BiFunction<Cont, Function<Cont, Object>, Object> entry1
                            = (BiFunction<Cont, Function<Cont, Object>, Object>) o;
                    System.out.println(
                            Cont.startContext((@Contify Cont cont)
                                    -> entry1.apply(cont,
                                                    (@Contify Cont k)
                                                    -> Cont.captureContext(k, r -> r.resume(6)))));
                } catch (Exception ex) {
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
