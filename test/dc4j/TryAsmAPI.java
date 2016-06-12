package dc4j;

import java.io.IOException;
import java.io.PrintWriter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

public class TryAsmAPI {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void insnState() throws Exception {
        if (Math.random() < 0.5) {
            ClassReader reader = new ClassReader(this.getClass().getCanonicalName());

            TraceClassVisitor tracer = new TraceClassVisitor(null, new ASMifier(), new PrintWriter(System.out));
            reader.accept(tracer, 0);
        } else {
            ClassReader reader = new ClassReader(this.getClass().getCanonicalName());

            TraceClassVisitor tracer = new TraceClassVisitor(null, new ASMifier(), new PrintWriter(System.out));
            reader.accept(tracer, 0);
        }
    }
}
