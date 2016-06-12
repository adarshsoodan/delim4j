package cps;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AnalyzerAdapter;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

/**
 *
 * @author user
 */
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
        final PrintWriter printWriter = new PrintWriter(System.out);
        ClassVisitor cv = new ClassVisitor(Opcodes.ASM5) {
            private String className = null;
            
            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                className = name;
            }
            
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                final AnalyzerAdapter statePrinter
                        = new AnalyzerAdapter(Opcodes.ASM5, className, access, name, desc, null) {
                    
                    void printState() {
                        final StringBuilder str = new StringBuilder();
                        str.append(locals.stream().map(x -> x.toString()).collect(Collectors.joining(", "))).append("\n");
                        str.append(stack.stream().map(x -> x.toString()).collect(Collectors.joining(", "))).append("\n");
                        uninitializedTypes.forEach((k, v) -> str.append(k).append("->").append(v));
                        printWriter.println(str.toString());
                    }
                    
                    @Override
                    public void visitMultiANewArrayInsn(String desc, int dims) {
                        printState();
                    }
                    
                    @Override
                    public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
                        printState();
                    }
                    
                    @Override
                    public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
                        printState();
                    }
                    
                    @Override
                    public void visitIincInsn(int var, int increment) {
                        printState();
                    }
                    
                    @Override
                    public void visitLdcInsn(Object cst) {
                        printState();
                    }
                    
                    @Override
                    public void visitLabel(Label label) {
                        printState();
                    }
                    
                    @Override
                    public void visitJumpInsn(int opcode, Label label) {
                        printState();
                    }
                    
                    @Override
                    public void visitInvokeDynamicInsn(String name, String desc, Handle bsm, Object... bsmArgs) {
                        printState();
                    }
                    
                    @Override
                    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
                        printState();
                    }
                    
                    @Override
                    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
                        printState();
                    }
                    
                    @Override
                    public void visitTypeInsn(int opcode, String type) {
                        printState();
                    }
                    
                    @Override
                    public void visitVarInsn(int opcode, int var) {
                        printState();
                    }
                    
                    @Override
                    public void visitIntInsn(int opcode, int operand) {
                        printState();
                    }
                    
                    @Override
                    public void visitInsn(int opcode) {
                        printState();
                    }
                    
                    @Override
                    public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
                        printState();
                    }
                    
                };
                return statePrinter;
            }
            
        };
        
        TraceClassVisitor tracer = new TraceClassVisitor(cv, new Textifier(), new PrintWriter(System.out));
        ClassReader reader = new ClassReader(this.getClass().getCanonicalName());
        reader.accept(tracer, ClassReader.EXPAND_FRAMES);
    }
}
