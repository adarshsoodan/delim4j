package dc4j;

import java.io.IOException;
import java.io.PrintWriter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
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
    public void insnState() throws IOException {
        final Marker marker = new Marker();

        ClassReader reader = new ClassReader(this.getClass().getCanonicalName());
        reader.accept(marker, ClassReader.EXPAND_FRAMES);
 
        TraceClassVisitor tracer = new TraceClassVisitor(null, new Textifier(), new PrintWriter(System.out));
        marker.accept(tracer);
    }
}
