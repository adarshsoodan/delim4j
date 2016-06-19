package dcc;

import java.util.*;

import org.objectweb.asm.*;

public class TryAsmAPIDump implements Opcodes {

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(52, ACC_PUBLIC + ACC_SUPER, "dcc/TryAsmAPI", null, "java/lang/Object", null);

        cw.visitSource("TryAsmAPI.java", null);

        cw.
                visitInnerClass("java/lang/invoke/MethodHandles$Lookup", "java/lang/invoke/MethodHandles", "Lookup", ACC_PUBLIC + ACC_FINAL + ACC_STATIC);

        {
            fv = cw.
                    visitField(ACC_FINAL + ACC_STATIC + ACC_SYNTHETIC, "$assertionsDisabled", "Z", null, null);
            fv.visitEnd();
        }
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
            mv.visitLocalVariable("this", "Ldcc/TryAsmAPI;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.
                    visitMethod(ACC_PUBLIC, "argTypes", "()V", null, new String[]{"java/lang/Exception"});
            {
                av0 = mv.visitAnnotation("Lorg/junit/Test;", true);
                av0.visitEnd();
            }
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(20, l0);
            mv.visitLdcInsn(new Integer(123491));
            mv.
                    visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "toString", "(I)Ljava/lang/String;", false);
            mv.visitVarInsn(ASTORE, 1);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(21, l1);
            mv.visitFieldInsn(GETSTATIC, "dcc/TryAsmAPI", "$assertionsDisabled", "Z");
            Label l2 = new Label();
            mv.visitJumpInsn(IFNE, l2);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitJumpInsn(IFNONNULL, l2);
            mv.visitTypeInsn(NEW, "java/lang/AssertionError");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/AssertionError", "<init>", "()V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l2);
            mv.visitLineNumber(22, l2);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/lang/String"}, 0, null);
            mv.visitLdcInsn(Type.getType("Ljava/lang/String;"));
            mv.visitLdcInsn("format");
            mv.visitInsn(ICONST_2);
            mv.visitTypeInsn(ANEWARRAY, "java/lang/Class");
            mv.visitInsn(DUP);
            mv.visitInsn(ICONST_0);
            mv.visitLdcInsn(Type.getType("Ljava/lang/String;"));
            mv.visitInsn(AASTORE);
            mv.visitInsn(DUP);
            mv.visitInsn(ICONST_1);
            mv.visitInsn(ICONST_0);
            mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(23, l3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            mv.visitInsn(AASTORE);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getMethod", "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", false);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLineNumber(22, l4);
            mv.
                    visitMethodInsn(INVOKESTATIC, "org/objectweb/asm/commons/Method", "getMethod", "(Ljava/lang/reflect/Method;)Lorg/objectweb/asm/commons/Method;", false);
            mv.visitVarInsn(ASTORE, 2);
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitLineNumber(24, l5);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "org/objectweb/asm/commons/Method", "getDescriptor", "()Ljava/lang/String;", false);
            mv.
                    visitMethodInsn(INVOKESTATIC, "org/objectweb/asm/Type", "getArgumentTypes", "(Ljava/lang/String;)[Lorg/objectweb/asm/Type;", false);
            mv.visitVarInsn(ASTORE, 3);
            Label l6 = new Label();
            mv.visitLabel(l6);
            mv.visitLineNumber(29, l6);
            mv.visitTypeInsn(NEW, "java/util/ArrayList");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
            mv.visitVarInsn(ASTORE, 4);
            Label l7 = new Label();
            mv.visitLabel(l7);
            mv.visitLineNumber(30, l7);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ASTORE, 5);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitInsn(ARRAYLENGTH);
            mv.visitVarInsn(ISTORE, 6);
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ISTORE, 7);
            Label l8 = new Label();
            mv.visitLabel(l8);
            mv.
                    visitFrame(Opcodes.F_FULL, 8, new Object[]{"dcc/TryAsmAPI", "java/lang/String", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER}, 0, new Object[]{});
            mv.visitVarInsn(ILOAD, 7);
            mv.visitVarInsn(ILOAD, 6);
            Label l9 = new Label();
            mv.visitJumpInsn(IF_ICMPGE, l9);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ILOAD, 7);
            mv.visitInsn(AALOAD);
            mv.visitVarInsn(ASTORE, 8);
            Label l10 = new Label();
            mv.visitLabel(l10);
            mv.visitLineNumber(31, l10);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/objectweb/asm/Type", "getSort", "()I", false);
            Label l11 = new Label();
            Label l12 = new Label();
            Label l13 = new Label();
            Label l14 = new Label();
            Label l15 = new Label();
            Label l16 = new Label();
            mv.
                    visitTableSwitchInsn(1, 10, l12, new Label[]{l11, l11, l11, l12, l11, l13, l14, l15, l16, l16});
            mv.visitLabel(l11);
            mv.visitLineNumber(36, l11);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"org/objectweb/asm/Type"}, 0, null);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitFieldInsn(GETSTATIC, "org/objectweb/asm/Opcodes", "INTEGER", "Ljava/lang/Integer;");
            mv.
                    visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
            Label l17 = new Label();
            mv.visitLabel(l17);
            mv.visitLineNumber(37, l17);
            mv.visitJumpInsn(GOTO, l12);
            mv.visitLabel(l13);
            mv.visitLineNumber(39, l13);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitFieldInsn(GETSTATIC, "org/objectweb/asm/Opcodes", "FLOAT", "Ljava/lang/Integer;");
            mv.
                    visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
            Label l18 = new Label();
            mv.visitLabel(l18);
            mv.visitLineNumber(40, l18);
            mv.visitJumpInsn(GOTO, l12);
            mv.visitLabel(l14);
            mv.visitLineNumber(42, l14);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitFieldInsn(GETSTATIC, "org/objectweb/asm/Opcodes", "LONG", "Ljava/lang/Integer;");
            mv.
                    visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
            Label l19 = new Label();
            mv.visitLabel(l19);
            mv.visitLineNumber(43, l19);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitFieldInsn(GETSTATIC, "org/objectweb/asm/Opcodes", "TOP", "Ljava/lang/Integer;");
            mv.
                    visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
            Label l20 = new Label();
            mv.visitLabel(l20);
            mv.visitLineNumber(44, l20);
            mv.visitJumpInsn(GOTO, l12);
            mv.visitLabel(l15);
            mv.visitLineNumber(46, l15);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitFieldInsn(GETSTATIC, "org/objectweb/asm/Opcodes", "DOUBLE", "Ljava/lang/Integer;");
            mv.
                    visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
            Label l21 = new Label();
            mv.visitLabel(l21);
            mv.visitLineNumber(47, l21);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitFieldInsn(GETSTATIC, "org/objectweb/asm/Opcodes", "TOP", "Ljava/lang/Integer;");
            mv.
                    visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
            Label l22 = new Label();
            mv.visitLabel(l22);
            mv.visitLineNumber(48, l22);
            mv.visitJumpInsn(GOTO, l12);
            mv.visitLabel(l16);
            mv.visitLineNumber(51, l16);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "org/objectweb/asm/Type", "getDescriptor", "()Ljava/lang/String;", false);
            mv.
                    visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
            mv.visitLabel(l12);
            mv.visitLineNumber(30, l12);
            mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
            mv.visitIincInsn(7, 1);
            mv.visitJumpInsn(GOTO, l8);
            mv.visitLabel(l9);
            mv.visitLineNumber(55, l9);
            mv.visitFrame(Opcodes.F_CHOP, 3, null, 0, null);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitInvokeDynamicInsn("accept", "()Ljava/util/function/Consumer;", new Handle(Opcodes.H_INVOKESTATIC, "java/lang/invoke/LambdaMetafactory", "metafactory", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"), new Object[]{Type.
                getType("(Ljava/lang/Object;)V"), new Handle(Opcodes.H_INVOKESTATIC, "dcc/TryAsmAPI", "lambda$argTypes$0", "(Ljava/lang/Object;)V"), Type.
                getType("(Ljava/lang/Object;)V")});
            mv.
                    visitMethodInsn(INVOKEINTERFACE, "java/util/List", "forEach", "(Ljava/util/function/Consumer;)V", true);
            Label l23 = new Label();
            mv.visitLabel(l23);
            mv.visitLineNumber(56, l23);
            mv.visitInsn(RETURN);
            Label l24 = new Label();
            mv.visitLabel(l24);
            mv.visitLocalVariable("t", "Lorg/objectweb/asm/Type;", null, l10, l12, 8);
            mv.visitLocalVariable("this", "Ldcc/TryAsmAPI;", null, l0, l24, 0);
            mv.visitLocalVariable("s", "Ljava/lang/String;", null, l1, l24, 1);
            mv.visitLocalVariable("method", "Lorg/objectweb/asm/commons/Method;", null, l5, l24, 2);
            mv.visitLocalVariable("types", "[Lorg/objectweb/asm/Type;", null, l6, l24, 3);
            mv.
                    visitLocalVariable("l", "Ljava/util/List;", "Ljava/util/List<Ljava/lang/Object;>;", l7, l24, 4);
            mv.visitMaxs(6, 9);
            mv.visitEnd();
        }
        {
            mv = cw.
                    visitMethod(ACC_PUBLIC, "insnState", "()V", null, new String[]{"java/lang/Exception"});
            {
                av0 = mv.visitAnnotation("Lorg/junit/Test;", true);
                av0.visitEnd();
            }
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(60, l0);
            mv.visitLdcInsn(Type.getType("Ldcc/TryAsmAPI;"));
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getCanonicalName", "()Ljava/lang/String;", false);
            mv.visitVarInsn(ASTORE, 1);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(61, l1);
            mv.visitTypeInsn(NEW, "java/io/PrintWriter");
            mv.visitInsn(DUP);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.
                    visitMethodInsn(INVOKESPECIAL, "java/io/PrintWriter", "<init>", "(Ljava/io/OutputStream;)V", false);
            mv.visitVarInsn(ASTORE, 2);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(62, l2);
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "random", "()D", false);
            mv.visitLdcInsn(new Double("0.5"));
            mv.visitInsn(DCMPG);
            Label l3 = new Label();
            mv.visitJumpInsn(IFGE, l3);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLineNumber(63, l4);
            mv.visitTypeInsn(NEW, "org/objectweb/asm/ClassReader");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "org/objectweb/asm/ClassReader", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitVarInsn(ASTORE, 3);
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitLineNumber(65, l5);
            mv.visitTypeInsn(NEW, "org/objectweb/asm/util/TraceClassVisitor");
            mv.visitInsn(DUP);
            mv.visitInsn(ACONST_NULL);
            mv.visitTypeInsn(NEW, "org/objectweb/asm/util/ASMifier");
            mv.visitInsn(DUP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "org/objectweb/asm/util/ASMifier", "<init>", "()V", false);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "org/objectweb/asm/util/TraceClassVisitor", "<init>", "(Lorg/objectweb/asm/ClassVisitor;Lorg/objectweb/asm/util/Printer;Ljava/io/PrintWriter;)V", false);
            mv.visitVarInsn(ASTORE, 4);
            Label l6 = new Label();
            mv.visitLabel(l6);
            mv.visitLineNumber(66, l6);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitInsn(ICONST_0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "org/objectweb/asm/ClassReader", "accept", "(Lorg/objectweb/asm/ClassVisitor;I)V", false);
            Label l7 = new Label();
            mv.visitLabel(l7);
            mv.visitLineNumber(67, l7);
            Label l8 = new Label();
            mv.visitJumpInsn(GOTO, l8);
            mv.visitLabel(l3);
            mv.visitLineNumber(68, l3);
            mv.
                    visitFrame(Opcodes.F_APPEND, 2, new Object[]{"java/lang/String", "java/io/PrintWriter"}, 0, null);
            mv.visitTypeInsn(NEW, "org/objectweb/asm/ClassReader");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "org/objectweb/asm/ClassReader", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitVarInsn(ASTORE, 3);
            Label l9 = new Label();
            mv.visitLabel(l9);
            mv.visitLineNumber(70, l9);
            mv.visitTypeInsn(NEW, "org/objectweb/asm/util/TraceClassVisitor");
            mv.visitInsn(DUP);
            mv.visitInsn(ACONST_NULL);
            mv.visitTypeInsn(NEW, "org/objectweb/asm/util/ASMifier");
            mv.visitInsn(DUP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "org/objectweb/asm/util/ASMifier", "<init>", "()V", false);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "org/objectweb/asm/util/TraceClassVisitor", "<init>", "(Lorg/objectweb/asm/ClassVisitor;Lorg/objectweb/asm/util/Printer;Ljava/io/PrintWriter;)V", false);
            mv.visitVarInsn(ASTORE, 4);
            Label l10 = new Label();
            mv.visitLabel(l10);
            mv.visitLineNumber(71, l10);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitInsn(ICONST_0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "org/objectweb/asm/ClassReader", "accept", "(Lorg/objectweb/asm/ClassVisitor;I)V", false);
            mv.visitLabel(l8);
            mv.visitLineNumber(73, l8);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            Label l11 = new Label();
            mv.visitLabel(l11);
            mv.visitLocalVariable("reader", "Lorg/objectweb/asm/ClassReader;", null, l5, l7, 3);
            mv.
                    visitLocalVariable("tracer", "Lorg/objectweb/asm/util/TraceClassVisitor;", null, l6, l7, 4);
            mv.visitLocalVariable("reader", "Lorg/objectweb/asm/ClassReader;", null, l9, l8, 3);
            mv.
                    visitLocalVariable("tracer", "Lorg/objectweb/asm/util/TraceClassVisitor;", null, l10, l8, 4);
            mv.visitLocalVariable("this", "Ldcc/TryAsmAPI;", null, l0, l11, 0);
            mv.visitLocalVariable("className", "Ljava/lang/String;", null, l1, l11, 1);
            mv.visitLocalVariable("printer", "Ljava/io/PrintWriter;", null, l2, l11, 2);
            mv.visitMaxs(5, 5);
            mv.visitEnd();
        }
        {
            mv = cw.
                    visitMethod(ACC_PRIVATE + ACC_STATIC + ACC_SYNTHETIC, "lambda$argTypes$0", "(Ljava/lang/Object;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(55, l0);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(RETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("x", "Ljava/lang/Object;", null, l0, l1, 0);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(16, l0);
            mv.visitLdcInsn(Type.getType("Ldcc/TryAsmAPI;"));
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "desiredAssertionStatus", "()Z", false);
            Label l1 = new Label();
            mv.visitJumpInsn(IFNE, l1);
            mv.visitInsn(ICONST_1);
            Label l2 = new Label();
            mv.visitJumpInsn(GOTO, l2);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(ICONST_0);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{Opcodes.INTEGER});
            mv.visitFieldInsn(PUTSTATIC, "dcc/TryAsmAPI", "$assertionsDisabled", "Z");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 0);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
