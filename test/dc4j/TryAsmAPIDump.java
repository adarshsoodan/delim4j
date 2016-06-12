package dc4j;

import org.objectweb.asm.*;

public class TryAsmAPIDump implements Opcodes {

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(52, ACC_PUBLIC + ACC_SUPER, "dc4j/TryAsmAPI", null, "java/lang/Object", null);

        cw.visitSource("TryAsmAPI.java", null);

        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(16, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Ldc4j/TryAsmAPI;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "setUpClass", "()V", null, null);
            {
                av0 = mv.visitAnnotation("Lorg/junit/BeforeClass;", true);
                av0.visitEnd();
            }
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(20, l0);
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "tearDownClass", "()V", null, null);
            {
                av0 = mv.visitAnnotation("Lorg/junit/AfterClass;", true);
                av0.visitEnd();
            }
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(24, l0);
            mv.visitInsn(RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "setUp", "()V", null, null);
            {
                av0 = mv.visitAnnotation("Lorg/junit/Before;", true);
                av0.visitEnd();
            }
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(28, l0);
            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Ldc4j/TryAsmAPI;", null, l0, l1, 0);
            mv.visitMaxs(0, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "tearDown", "()V", null, null);
            {
                av0 = mv.visitAnnotation("Lorg/junit/After;", true);
                av0.visitEnd();
            }
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(32, l0);
            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Ldc4j/TryAsmAPI;", null, l0, l1, 0);
            mv.visitMaxs(0, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "insnState", "()V", null, new String[]{"java/lang/Exception"});
            {
                av0 = mv.visitAnnotation("Lorg/junit/Test;", true);
                av0.visitEnd();
            }
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(36, l0);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "random", "()D", false);
            mv.visitLdcInsn(new Double("0.5"));
            mv.visitInsn(DCMPG);
            Label l1 = new Label();
            mv.visitJumpInsn(IFGE, l1);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(37, l2);
            mv.visitTypeInsn(NEW, "org/objectweb/asm/ClassReader");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getCanonicalName", "()Ljava/lang/String;", false);
            mv.visitMethodInsn(INVOKESPECIAL, "org/objectweb/asm/ClassReader", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitVarInsn(ASTORE, 1);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(39, l3);
            mv.visitTypeInsn(NEW, "org/objectweb/asm/util/TraceClassVisitor");
            mv.visitInsn(DUP);
            mv.visitInsn(ACONST_NULL);
            mv.visitTypeInsn(NEW, "org/objectweb/asm/util/ASMifier");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "org/objectweb/asm/util/ASMifier", "<init>", "()V", false);
            mv.visitTypeInsn(NEW, "java/io/PrintWriter");
            mv.visitInsn(DUP);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitMethodInsn(INVOKESPECIAL, "java/io/PrintWriter", "<init>", "(Ljava/io/OutputStream;)V", false);
            mv.visitMethodInsn(INVOKESPECIAL, "org/objectweb/asm/util/TraceClassVisitor", "<init>", "(Lorg/objectweb/asm/ClassVisitor;Lorg/objectweb/asm/util/Printer;Ljava/io/PrintWriter;)V", false);
            mv.visitVarInsn(ASTORE, 2);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLineNumber(40, l4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(ICONST_0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/objectweb/asm/ClassReader", "accept", "(Lorg/objectweb/asm/ClassVisitor;I)V", false);
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitLineNumber(41, l5);
            Label l6 = new Label();
            mv.visitJumpInsn(GOTO, l6);
            mv.visitLabel(l1);
            mv.visitLineNumber(42, l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitTypeInsn(NEW, "org/objectweb/asm/ClassReader");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getCanonicalName", "()Ljava/lang/String;", false);
            mv.visitMethodInsn(INVOKESPECIAL, "org/objectweb/asm/ClassReader", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitVarInsn(ASTORE, 1);
            Label l7 = new Label();
            mv.visitLabel(l7);
            mv.visitLineNumber(44, l7);
            mv.visitTypeInsn(NEW, "org/objectweb/asm/util/TraceClassVisitor");
            mv.visitInsn(DUP);
            mv.visitInsn(ACONST_NULL);
            mv.visitTypeInsn(NEW, "org/objectweb/asm/util/ASMifier");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "org/objectweb/asm/util/ASMifier", "<init>", "()V", false);
            mv.visitTypeInsn(NEW, "java/io/PrintWriter");
            mv.visitInsn(DUP);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitMethodInsn(INVOKESPECIAL, "java/io/PrintWriter", "<init>", "(Ljava/io/OutputStream;)V", false);
            mv.visitMethodInsn(INVOKESPECIAL, "org/objectweb/asm/util/TraceClassVisitor", "<init>", "(Lorg/objectweb/asm/ClassVisitor;Lorg/objectweb/asm/util/Printer;Ljava/io/PrintWriter;)V", false);
            mv.visitVarInsn(ASTORE, 2);
            Label l8 = new Label();
            mv.visitLabel(l8);
            mv.visitLineNumber(45, l8);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(ICONST_0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/objectweb/asm/ClassReader", "accept", "(Lorg/objectweb/asm/ClassVisitor;I)V", false);
            mv.visitLabel(l6);
            mv.visitLineNumber(58, l6);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            Label l9 = new Label();
            mv.visitLabel(l9);
            mv.visitLocalVariable("reader", "Lorg/objectweb/asm/ClassReader;", null, l3, l5, 1);
            mv.visitLocalVariable("tracer", "Lorg/objectweb/asm/util/TraceClassVisitor;", null, l4, l5, 2);
            mv.visitLocalVariable("reader", "Lorg/objectweb/asm/ClassReader;", null, l7, l6, 1);
            mv.visitLocalVariable("tracer", "Lorg/objectweb/asm/util/TraceClassVisitor;", null, l8, l6, 2);
            mv.visitLocalVariable("this", "Ldc4j/TryAsmAPI;", null, l0, l9, 0);
            mv.visitMaxs(7, 3);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
