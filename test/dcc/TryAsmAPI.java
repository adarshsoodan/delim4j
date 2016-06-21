package dcc;

import dcc.rt.Cont;
import dcc.rt.DccException;
import dcc.util.BytesClassLoader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

public class TryAsmAPI {

    @Test
    public void methodDesc() {
        Map<String, Method> methods = new HashMap<>();
        Arrays.stream(String.class.getDeclaredMethods()).
                filter(m -> Modifier.isPublic(m.getModifiers())).
                forEach(m -> {
                    methods.put(m.getName(), m);
                });
        Method m = methods.get("replaceFirst");
        String desc = Type.getMethodDescriptor(m);
        Type[] args = Type.getMethodType(desc).getArgumentTypes();
        System.out.println(desc);
        System.out.println(Arrays.toString(args));
        System.out.println(args[0].getInternalName());

    }

    @Test
    public void verifyNonCont() throws Exception {
        final String className = TryAsmAPI.class.getCanonicalName();
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
                    Class<?> c = (new BytesClassLoader()).fromBytes("dcc.TryAsmAPI", b);
                    Object o = c.newInstance();
                    Map<String, Method> ms = Arrays.stream(c.getDeclaredMethods())
                            .collect(Collectors.toMap(m -> m.getName(), m -> m));
                    Method m = ms.get("dummyMethod");
                    try {
                        m.invoke(o, null, (Runnable) () -> {
                            Cont cont = new Cont(obj -> obj);
                            throw (new DccException(cont));
                        });
                    } catch (InvocationTargetException ite) {
                        DccException de = (DccException) ite.getCause();
                        Cont cont = de.getCont();
                        Object ret = m.invoke(o, cont, null);
                        System.out.println(ret);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            };
            invoker.run();
        }
        {
            Runnable display = () -> {
                ClassReader reader = new ClassReader(b);
                PrintWriter printer = new PrintWriter(System.out);
                TraceClassVisitor tracer = new TraceClassVisitor(null, new Textifier(), printer);
                reader.accept(tracer, ClassReader.EXPAND_FRAMES | ClassReader.SKIP_DEBUG);
            };
//            display.run();
        }
    }

    public int dummyMethod(Cont cont, Runnable action) throws Exception {
        action.run();
        return Integer.parseInt("0");
    }
}
