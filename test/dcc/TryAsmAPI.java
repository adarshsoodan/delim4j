package dcc;

import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

public class TryAsmAPI {

    @Test
    public void argTypes() throws Exception {
        final Method method = Method.getMethod(
                String.class.getMethod("format", String.class, (new Object[]{}).getClass()));
        final Type[] types = Type.getArgumentTypes(method.getDescriptor());
        /* Primitive types are represented by Opcodes.TOP, Opcodes.INTEGER
           , Opcodes.FLOAT, Opcodes.LONG, Opcodes.DOUBLE,Opcodes.NULL
           or Opcodes.UNINITIALIZED_THIS (long and double are represented by a single element).
          Reference types are represented by String objects (representing internal names)*/
        List<Object> l = new ArrayList<>();
        for (final Type t : types) {
            switch (t.getSort()) {
                case Type.INT:
                case Type.BOOLEAN:
                case Type.BYTE:
                case Type.CHAR:
                    l.add(Opcodes.INTEGER);
                    break;
                case Type.FLOAT:
                    l.add(Opcodes.FLOAT);
                    break;
                case Type.LONG:
                    l.add(Opcodes.LONG);
                    l.add(Opcodes.TOP);
                    break;
                case Type.DOUBLE:
                    l.add(Opcodes.DOUBLE);
                    l.add(Opcodes.TOP);
                    break;
                case Type.ARRAY:
                case Type.OBJECT:
                    l.add(t.getDescriptor());
                    break;
            }
        }
        l.forEach(x -> out.println(x));
    }

    @Test
    public void insnState() throws Exception {
        final String className = TryAsmAPI.class.getCanonicalName();
        final PrintWriter printer = new PrintWriter(System.out);
        if (Math.random() < 0.5) {
            ClassReader reader = new ClassReader(className);

            TraceClassVisitor tracer = new TraceClassVisitor(null, new Textifier(), printer);
            reader.accept(tracer, 0);
        } else {
            ClassReader reader = new ClassReader(className);

            TraceClassVisitor tracer = new TraceClassVisitor(null, new Textifier(), printer);
            reader.accept(tracer, 0);
        }
    }
}
