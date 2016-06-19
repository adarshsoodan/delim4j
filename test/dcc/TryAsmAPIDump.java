package dcc;

import org.objectweb.asm.*;

public class TryAsmAPIDump implements Opcodes {

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(52, ACC_PUBLIC + ACC_SUPER, "dcc/TryAsmAPI", null, "java/lang/Object", null);

        cw.visitInnerClass("dcc/TryAsmAPI$1", null, null, 0);

        cw.
                visitInnerClass("java/lang/invoke/MethodHandles$Lookup", "java/lang/invoke/MethodHandles", "Lookup", ACC_PUBLIC + ACC_FINAL + ACC_STATIC);

        {
            mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.
                    visitMethod(ACC_PUBLIC, "verifyNonCont", "()V", null, new String[]{"java/lang/Exception"});
            {
                av0 = mv.visitAnnotation("Lorg/junit/Test;", true);
                av0.visitEnd();
            }
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, "dcc/rt/DccException");
            Label l3 = new Label();
            Label l4 = new Label();
            Label l5 = new Label();
            mv.visitTryCatchBlock(l3, l4, l5, "dcc/rt/DccException");
            Label l6 = new Label();
            Label l7 = new Label();
            Label l8 = new Label();
            mv.visitTryCatchBlock(l6, l7, l8, "dcc/rt/DccException");
            Label l9 = new Label();
            Label l10 = new Label();
            Label l11 = new Label();
            mv.visitTryCatchBlock(l9, l10, l11, "dcc/rt/DccException");
            Label l12 = new Label();
            Label l13 = new Label();
            Label l14 = new Label();
            mv.visitTryCatchBlock(l12, l13, l14, "dcc/rt/DccException");
            Label l15 = new Label();
            mv.visitJumpInsn(GOTO, l15);
            Label l16 = new Label();
            mv.visitLabel(l16);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"verifyNonCont"}, 0, new Object[]{});
            mv.visitLdcInsn(Type.getType("Ldcc/TryAsmAPI;"));
            mv.visitJumpInsn(GOTO, l0);
            mv.visitLabel(l0);
            mv.
                    visitFrame(Opcodes.F_NEW, 1, new Object[]{"verifyNonCont"}, 1, new Object[]{"java/lang/Class"});
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getCanonicalName", "()Ljava/lang/String;", false);
            mv.visitLabel(l1);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitTypeInsn(NEW, "org/objectweb/asm/ClassWriter");
            mv.visitInsn(DUP);
            mv.visitInsn(ICONST_0);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "org/objectweb/asm/ClassWriter", "<init>", "(I)V", false);
            mv.visitVarInsn(ASTORE, 2);
            mv.visitTypeInsn(NEW, "dcc/TryAsmAPI$1");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitLdcInsn(new Integer(327680));
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/TryAsmAPI$1", "<init>", "(Ldcc/TryAsmAPI;ILorg/objectweb/asm/ClassVisitor;)V", false);
            mv.visitVarInsn(ASTORE, 3);
            mv.visitTypeInsn(NEW, "org/objectweb/asm/ClassReader");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "org/objectweb/asm/ClassReader", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitIntInsn(BIPUSH, 8);
            mv.visitJumpInsn(GOTO, l3);
            mv.visitLabel(l3);
            mv.
                    visitFrame(Opcodes.F_NEW, 5, new Object[]{"verifyNonCont", "java/lang/String", "org/objectweb/asm/ClassWriter", "dcc/TryAsmAPI$1", "org/objectweb/asm/ClassReader"}, 3, new Object[]{"org/objectweb/asm/ClassReader", "dcc/TryAsmAPI$1", Opcodes.INTEGER});
            mv.visitVarInsn(ISTORE, 7);
            mv.visitVarInsn(ASTORE, 6);
            mv.visitVarInsn(ASTORE, 5);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitVarInsn(ILOAD, 7);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "org/objectweb/asm/ClassReader", "accept", "(Lorg/objectweb/asm/ClassVisitor;I)V", false);
            mv.visitLabel(l4);
            mv.visitLdcInsn("C:\\Users\\user\\Desktop\\workspaces\\tmp\\dcc\\TryAsmAPI.class");
            mv.visitInsn(ICONST_0);
            mv.visitTypeInsn(ANEWARRAY, "java/lang/String");
            mv.visitJumpInsn(GOTO, l6);
            mv.visitLabel(l6);
            mv.
                    visitFrame(Opcodes.F_NEW, 8, new Object[]{"verifyNonCont", "java/lang/String", "org/objectweb/asm/ClassWriter", "dcc/TryAsmAPI$1", "org/objectweb/asm/ClassReader", "org/objectweb/asm/ClassReader", "dcc/TryAsmAPI$1", Opcodes.INTEGER}, 2, new Object[]{"java/lang/String", "[Ljava/lang/String;"});
            mv.visitVarInsn(ASTORE, 9);
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKESTATIC, "java/nio/file/Paths", "get", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/nio/file/Path;", false);
            mv.visitLabel(l7);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitJumpInsn(GOTO, l9);
            mv.visitLabel(l9);
            mv.
                    visitFrame(Opcodes.F_NEW, 10, new Object[]{"verifyNonCont", "java/lang/String", "org/objectweb/asm/ClassWriter", "dcc/TryAsmAPI$1", "org/objectweb/asm/ClassReader", "org/objectweb/asm/ClassReader", "dcc/TryAsmAPI$1", Opcodes.INTEGER, "java/lang/String", "[Ljava/lang/String;"}, 2, new Object[]{"java/nio/file/Path", "org/objectweb/asm/ClassWriter"});
            mv.visitVarInsn(ASTORE, 11);
            mv.visitVarInsn(ASTORE, 10);
            mv.visitVarInsn(ALOAD, 10);
            mv.visitVarInsn(ALOAD, 11);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "org/objectweb/asm/ClassWriter", "toByteArray", "()[B", false);
            mv.visitLabel(l10);
            mv.visitInsn(ICONST_1);
            mv.visitTypeInsn(ANEWARRAY, "java/nio/file/OpenOption");
            mv.visitInsn(DUP);
            mv.visitInsn(ICONST_0);
            mv.
                    visitFieldInsn(GETSTATIC, "java/nio/file/StandardOpenOption", "CREATE_NEW", "Ljava/nio/file/StandardOpenOption;");
            mv.visitInsn(AASTORE);
            mv.visitJumpInsn(GOTO, l12);
            mv.visitLabel(l12);
            mv.
                    visitFrame(Opcodes.F_NEW, 12, new Object[]{"verifyNonCont", "java/lang/String", "org/objectweb/asm/ClassWriter", "dcc/TryAsmAPI$1", "org/objectweb/asm/ClassReader", "org/objectweb/asm/ClassReader", "dcc/TryAsmAPI$1", Opcodes.INTEGER, "java/lang/String", "[Ljava/lang/String;", "java/nio/file/Path", "org/objectweb/asm/ClassWriter"}, 3, new Object[]{"java/nio/file/Path", "[B", "[Ljava/nio/file/OpenOption;"});
            mv.visitVarInsn(ASTORE, 14);
            mv.visitVarInsn(ASTORE, 13);
            mv.visitVarInsn(ASTORE, 12);
            mv.visitVarInsn(ALOAD, 12);
            mv.visitVarInsn(ALOAD, 13);
            mv.visitVarInsn(ALOAD, 14);
            mv.
                    visitMethodInsn(INVOKESTATIC, "java/nio/file/Files", "write", "(Ljava/nio/file/Path;[B[Ljava/nio/file/OpenOption;)Ljava/nio/file/Path;", false);
            mv.visitLabel(l13);
            mv.visitInsn(POP);
            mv.visitInsn(RETURN);
            mv.visitLabel(l15);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"verifyNonCont"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.visitJumpInsn(IFNULL, l16);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popJump", "()I", false);
            Label l17 = new Label();
            Label l18 = new Label();
            Label l19 = new Label();
            Label l20 = new Label();
            Label l21 = new Label();
            Label l22 = new Label();
            mv.visitTableSwitchInsn(0, 4, l22, new Label[]{l17, l18, l19, l20, l21});
            mv.visitLabel(l17);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"verifyNonCont"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Class;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "LverifyNonCont;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitJumpInsn(GOTO, l0);
            mv.visitLabel(l2);
            mv.
                    visitFrame(Opcodes.F_NEW, 2, new Object[]{"verifyNonCont", "java/lang/Class"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(0));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l18);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"verifyNonCont"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassReader;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI$1;");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "LverifyNonCont;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassWriter;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI$1;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassReader;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitJumpInsn(GOTO, l3);
            mv.visitLabel(l5);
            mv.
                    visitFrame(Opcodes.F_NEW, 8, new Object[]{"verifyNonCont", "java/lang/String", "org/objectweb/asm/ClassWriter", "dcc/TryAsmAPI$1", "org/objectweb/asm/ClassReader", "org/objectweb/asm/ClassReader", "dcc/TryAsmAPI$1", Opcodes.INTEGER}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 7);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 6);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 5);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(1));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l19);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"verifyNonCont"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/String;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "LverifyNonCont;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassWriter;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI$1;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassReader;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassReader;");
            mv.visitVarInsn(ASTORE, 5);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI$1;");
            mv.visitVarInsn(ASTORE, 6);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 7);
            mv.visitJumpInsn(GOTO, l6);
            mv.visitLabel(l8);
            mv.
                    visitFrame(Opcodes.F_NEW, 10, new Object[]{"verifyNonCont", "java/lang/String", "org/objectweb/asm/ClassWriter", "dcc/TryAsmAPI$1", "org/objectweb/asm/ClassReader", "org/objectweb/asm/ClassReader", "dcc/TryAsmAPI$1", Opcodes.INTEGER, "java/lang/String", "[Ljava/lang/String;"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 7);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 6);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 5);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(2));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l20);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"verifyNonCont"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/nio/file/Path;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassWriter;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "LverifyNonCont;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassWriter;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI$1;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassReader;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassReader;");
            mv.visitVarInsn(ASTORE, 5);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI$1;");
            mv.visitVarInsn(ASTORE, 6);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 7);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 9);
            mv.visitJumpInsn(GOTO, l9);
            mv.visitLabel(l11);
            mv.
                    visitFrame(Opcodes.F_NEW, 12, new Object[]{"verifyNonCont", "java/lang/String", "org/objectweb/asm/ClassWriter", "dcc/TryAsmAPI$1", "org/objectweb/asm/ClassReader", "org/objectweb/asm/ClassReader", "dcc/TryAsmAPI$1", Opcodes.INTEGER, "java/lang/String", "[Ljava/lang/String;", "java/nio/file/Path", "org/objectweb/asm/ClassWriter"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 7);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 6);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 5);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 11);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 10);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(3));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l21);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"verifyNonCont"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/nio/file/Path;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[B");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/nio/file/OpenOption;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "LverifyNonCont;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassWriter;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI$1;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassReader;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassReader;");
            mv.visitVarInsn(ASTORE, 5);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI$1;");
            mv.visitVarInsn(ASTORE, 6);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 7);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 9);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/nio/file/Path;");
            mv.visitVarInsn(ASTORE, 10);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassWriter;");
            mv.visitVarInsn(ASTORE, 11);
            mv.visitJumpInsn(GOTO, l12);
            mv.visitLabel(l14);
            mv.
                    visitFrame(Opcodes.F_NEW, 15, new Object[]{"verifyNonCont", "java/lang/String", "org/objectweb/asm/ClassWriter", "dcc/TryAsmAPI$1", "org/objectweb/asm/ClassReader", "org/objectweb/asm/ClassReader", "dcc/TryAsmAPI$1", Opcodes.INTEGER, "java/lang/String", "[Ljava/lang/String;", "java/nio/file/Path", "org/objectweb/asm/ClassWriter", "java/nio/file/Path", "[B", "[Ljava/nio/file/OpenOption;"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 11);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 10);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 7);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 6);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 5);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 14);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 13);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 12);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(4));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l22);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"verifyNonCont"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "Ldcc/rt/Cont;", "invalidCont", "()V", false);
            mv.visitMaxs(6, 15);
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
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, "dcc/rt/DccException");
            Label l3 = new Label();
            Label l4 = new Label();
            Label l5 = new Label();
            mv.visitTryCatchBlock(l3, l4, l5, "dcc/rt/DccException");
            Label l6 = new Label();
            Label l7 = new Label();
            Label l8 = new Label();
            mv.visitTryCatchBlock(l6, l7, l8, "dcc/rt/DccException");
            Label l9 = new Label();
            Label l10 = new Label();
            Label l11 = new Label();
            mv.visitTryCatchBlock(l9, l10, l11, "dcc/rt/DccException");
            Label l12 = new Label();
            Label l13 = new Label();
            Label l14 = new Label();
            mv.visitTryCatchBlock(l12, l13, l14, "dcc/rt/DccException");
            Label l15 = new Label();
            Label l16 = new Label();
            Label l17 = new Label();
            mv.visitTryCatchBlock(l15, l16, l17, "dcc/rt/DccException");
            Label l18 = new Label();
            Label l19 = new Label();
            Label l20 = new Label();
            mv.visitTryCatchBlock(l18, l19, l20, "dcc/rt/DccException");
            Label l21 = new Label();
            Label l22 = new Label();
            Label l23 = new Label();
            mv.visitTryCatchBlock(l21, l22, l23, "dcc/rt/DccException");
            Label l24 = new Label();
            Label l25 = new Label();
            Label l26 = new Label();
            mv.visitTryCatchBlock(l24, l25, l26, "dcc/rt/DccException");
            Label l27 = new Label();
            Label l28 = new Label();
            Label l29 = new Label();
            mv.visitTryCatchBlock(l27, l28, l29, "dcc/rt/DccException");
            Label l30 = new Label();
            Label l31 = new Label();
            Label l32 = new Label();
            mv.visitTryCatchBlock(l30, l31, l32, "dcc/rt/DccException");
            Label l33 = new Label();
            Label l34 = new Label();
            Label l35 = new Label();
            mv.visitTryCatchBlock(l33, l34, l35, "dcc/rt/DccException");
            Label l36 = new Label();
            Label l37 = new Label();
            Label l38 = new Label();
            mv.visitTryCatchBlock(l36, l37, l38, "dcc/rt/DccException");
            Label l39 = new Label();
            Label l40 = new Label();
            Label l41 = new Label();
            mv.visitTryCatchBlock(l39, l40, l41, "dcc/rt/DccException");
            Label l42 = new Label();
            Label l43 = new Label();
            Label l44 = new Label();
            mv.visitTryCatchBlock(l42, l43, l44, "dcc/rt/DccException");
            Label l45 = new Label();
            Label l46 = new Label();
            Label l47 = new Label();
            mv.visitTryCatchBlock(l45, l46, l47, "dcc/rt/DccException");
            Label l48 = new Label();
            mv.visitJumpInsn(GOTO, l48);
            Label l49 = new Label();
            mv.visitLabel(l49);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
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
            mv.visitJumpInsn(GOTO, l0);
            mv.visitLabel(l0);
            mv.
                    visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 6, new Object[]{"java/lang/Class", "java/lang/String", "[Ljava/lang/Class;", "[Ljava/lang/Class;", Opcodes.INTEGER, "[Ljava/lang/Object;"});
            mv.visitVarInsn(ASTORE, 6);
            mv.visitVarInsn(ISTORE, 5);
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            mv.visitLabel(l1);
            mv.visitInsn(AASTORE);
            mv.visitJumpInsn(GOTO, l3);
            mv.visitLabel(l3);
            mv.
                    visitFrame(Opcodes.F_NEW, 7, new Object[]{"argTypes", "java/lang/Class", "java/lang/String", "[Ljava/lang/Class;", "[Ljava/lang/Class;", Opcodes.INTEGER, "[Ljava/lang/Object;"}, 3, new Object[]{"java/lang/Class", "java/lang/String", "[Ljava/lang/Class;"});
            mv.visitVarInsn(ASTORE, 9);
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ASTORE, 7);
            mv.visitVarInsn(ALOAD, 7);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getMethod", "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", false);
            mv.visitLabel(l4);
            mv.visitJumpInsn(GOTO, l6);
            mv.visitLabel(l6);
            mv.
                    visitFrame(Opcodes.F_NEW, 10, new Object[]{"argTypes", "java/lang/Class", "java/lang/String", "[Ljava/lang/Class;", "[Ljava/lang/Class;", Opcodes.INTEGER, "[Ljava/lang/Object;", "java/lang/Class", "java/lang/String", "[Ljava/lang/Class;"}, 1, new Object[]{"java/lang/reflect/Method"});
            mv.visitVarInsn(ASTORE, 10);
            mv.visitVarInsn(ALOAD, 10);
            mv.
                    visitMethodInsn(INVOKESTATIC, "org/objectweb/asm/commons/Method", "getMethod", "(Ljava/lang/reflect/Method;)Lorg/objectweb/asm/commons/Method;", false);
            mv.visitLabel(l7);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitJumpInsn(GOTO, l9);
            mv.visitLabel(l9);
            mv.
                    visitFrame(Opcodes.F_NEW, 11, new Object[]{"argTypes", "org/objectweb/asm/commons/Method", "java/lang/String", "[Ljava/lang/Class;", "[Ljava/lang/Class;", Opcodes.INTEGER, "[Ljava/lang/Object;", "java/lang/Class", "java/lang/String", "[Ljava/lang/Class;", "java/lang/reflect/Method"}, 1, new Object[]{"org/objectweb/asm/commons/Method"});
            mv.visitVarInsn(ASTORE, 11);
            mv.visitVarInsn(ALOAD, 11);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "org/objectweb/asm/commons/Method", "getDescriptor", "()Ljava/lang/String;", false);
            mv.visitLabel(l10);
            mv.visitJumpInsn(GOTO, l12);
            mv.visitLabel(l12);
            mv.
                    visitFrame(Opcodes.F_NEW, 12, new Object[]{"argTypes", "org/objectweb/asm/commons/Method", "java/lang/String", "[Ljava/lang/Class;", "[Ljava/lang/Class;", Opcodes.INTEGER, "[Ljava/lang/Object;", "java/lang/Class", "java/lang/String", "[Ljava/lang/Class;", "java/lang/reflect/Method", "org/objectweb/asm/commons/Method"}, 1, new Object[]{"java/lang/String"});
            mv.visitVarInsn(ASTORE, 12);
            mv.visitVarInsn(ALOAD, 12);
            mv.
                    visitMethodInsn(INVOKESTATIC, "org/objectweb/asm/Type", "getArgumentTypes", "(Ljava/lang/String;)[Lorg/objectweb/asm/Type;", false);
            mv.visitLabel(l13);
            mv.visitVarInsn(ASTORE, 2);
            mv.visitTypeInsn(NEW, "java/util/ArrayList");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitInsn(ARRAYLENGTH);
            mv.visitVarInsn(ISTORE, 5);
            mv.visitInsn(ICONST_0);
            mv.visitVarInsn(ISTORE, 6);
            Label l50 = new Label();
            mv.visitLabel(l50);
            mv.
                    visitFrame(Opcodes.F_NEW, 7, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER}, 0, new Object[]{});
            mv.visitVarInsn(ILOAD, 6);
            mv.visitVarInsn(ILOAD, 5);
            Label l51 = new Label();
            mv.visitJumpInsn(IF_ICMPGE, l51);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitVarInsn(ILOAD, 6);
            mv.visitInsn(AALOAD);
            mv.visitVarInsn(ASTORE, 7);
            mv.visitVarInsn(ALOAD, 7);
            mv.visitJumpInsn(GOTO, l15);
            mv.visitLabel(l15);
            mv.
                    visitFrame(Opcodes.F_NEW, 8, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type"}, 1, new Object[]{"org/objectweb/asm/Type"});
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/objectweb/asm/Type", "getSort", "()I", false);
            mv.visitLabel(l16);
            Label l52 = new Label();
            Label l53 = new Label();
            Label l54 = new Label();
            Label l55 = new Label();
            Label l56 = new Label();
            Label l57 = new Label();
            mv.
                    visitTableSwitchInsn(1, 10, l53, new Label[]{l52, l52, l52, l53, l52, l54, l55, l56, l57, l57});
            mv.visitLabel(l52);
            mv.
                    visitFrame(Opcodes.F_NEW, 8, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitFieldInsn(GETSTATIC, "org/objectweb/asm/Opcodes", "INTEGER", "Ljava/lang/Integer;");
            mv.visitJumpInsn(GOTO, l18);
            mv.visitLabel(l18);
            mv.
                    visitFrame(Opcodes.F_NEW, 8, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type"}, 2, new Object[]{"java/util/List", "java/lang/Integer"});
            mv.visitVarInsn(ASTORE, 9);
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitLabel(l19);
            mv.visitInsn(POP);
            mv.visitJumpInsn(GOTO, l53);
            mv.visitLabel(l54);
            mv.
                    visitFrame(Opcodes.F_NEW, 8, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitFieldInsn(GETSTATIC, "org/objectweb/asm/Opcodes", "FLOAT", "Ljava/lang/Integer;");
            mv.visitJumpInsn(GOTO, l21);
            mv.visitLabel(l21);
            mv.
                    visitFrame(Opcodes.F_NEW, 8, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type"}, 2, new Object[]{"java/util/List", "java/lang/Integer"});
            mv.visitVarInsn(ASTORE, 9);
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitLabel(l22);
            mv.visitInsn(POP);
            mv.visitJumpInsn(GOTO, l53);
            mv.visitLabel(l55);
            mv.
                    visitFrame(Opcodes.F_NEW, 8, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 3);
            mv.visitFieldInsn(GETSTATIC, "org/objectweb/asm/Opcodes", "LONG", "Ljava/lang/Integer;");
            mv.visitJumpInsn(GOTO, l24);
            mv.visitLabel(l24);
            mv.
                    visitFrame(Opcodes.F_NEW, 8, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type"}, 2, new Object[]{"java/util/List", "java/lang/Integer"});
            mv.visitVarInsn(ASTORE, 9);
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitLabel(l25);
            mv.visitInsn(POP);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitFieldInsn(GETSTATIC, "org/objectweb/asm/Opcodes", "TOP", "Ljava/lang/Integer;");
            mv.visitJumpInsn(GOTO, l27);
            mv.visitLabel(l27);
            mv.
                    visitFrame(Opcodes.F_NEW, 10, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type", "java/util/List", "java/lang/Integer"}, 2, new Object[]{"java/util/List", "java/lang/Integer"});
            mv.visitVarInsn(ASTORE, 11);
            mv.visitVarInsn(ASTORE, 10);
            mv.visitVarInsn(ALOAD, 10);
            mv.visitVarInsn(ALOAD, 11);
            mv.
                    visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitLabel(l28);
            mv.visitInsn(POP);
            mv.visitJumpInsn(GOTO, l53);
            mv.visitLabel(l56);
            mv.
                    visitFrame(Opcodes.F_NEW, 8, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitFieldInsn(GETSTATIC, "org/objectweb/asm/Opcodes", "DOUBLE", "Ljava/lang/Integer;");
            mv.visitJumpInsn(GOTO, l30);
            mv.visitLabel(l30);
            mv.
                    visitFrame(Opcodes.F_NEW, 8, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type"}, 2, new Object[]{"java/util/List", "java/lang/Integer"});
            mv.visitVarInsn(ASTORE, 9);
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitLabel(l31);
            mv.visitInsn(POP);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitFieldInsn(GETSTATIC, "org/objectweb/asm/Opcodes", "TOP", "Ljava/lang/Integer;");
            mv.visitJumpInsn(GOTO, l33);
            mv.visitLabel(l33);
            mv.
                    visitFrame(Opcodes.F_NEW, 10, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type", "java/util/List", "java/lang/Integer"}, 2, new Object[]{"java/util/List", "java/lang/Integer"});
            mv.visitVarInsn(ASTORE, 11);
            mv.visitVarInsn(ASTORE, 10);
            mv.visitVarInsn(ALOAD, 10);
            mv.visitVarInsn(ALOAD, 11);
            mv.
                    visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitLabel(l34);
            mv.visitInsn(POP);
            mv.visitJumpInsn(GOTO, l53);
            mv.visitLabel(l57);
            mv.
                    visitFrame(Opcodes.F_NEW, 8, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 7);
            mv.visitJumpInsn(GOTO, l36);
            mv.visitLabel(l36);
            mv.
                    visitFrame(Opcodes.F_NEW, 8, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type"}, 2, new Object[]{"java/util/List", "org/objectweb/asm/Type"});
            mv.visitVarInsn(ASTORE, 9);
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ALOAD, 8);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "org/objectweb/asm/Type", "getDescriptor", "()Ljava/lang/String;", false);
            mv.visitLabel(l37);
            mv.visitJumpInsn(GOTO, l39);
            mv.visitLabel(l39);
            mv.
                    visitFrame(Opcodes.F_NEW, 10, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type", "java/util/List", "org/objectweb/asm/Type"}, 2, new Object[]{"java/util/List", "java/lang/String"});
            mv.visitVarInsn(ASTORE, 11);
            mv.visitVarInsn(ASTORE, 10);
            mv.visitVarInsn(ALOAD, 10);
            mv.visitVarInsn(ALOAD, 11);
            mv.
                    visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitLabel(l40);
            mv.visitInsn(POP);
            mv.visitLabel(l53);
            mv.
                    visitFrame(Opcodes.F_NEW, 7, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER}, 0, new Object[]{});
            mv.visitIincInsn(6, 1);
            mv.visitJumpInsn(GOTO, l50);
            mv.visitLabel(l51);
            mv.
                    visitFrame(Opcodes.F_NEW, 4, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 3);
            mv.visitJumpInsn(GOTO, l42);
            mv.visitLabel(l42);
            mv.
                    visitFrame(Opcodes.F_NEW, 4, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List"}, 1, new Object[]{"java/util/List"});
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitInvokeDynamicInsn("accept", "()Ljava/util/function/Consumer;", new Handle(Opcodes.H_INVOKESTATIC, "java/lang/invoke/LambdaMetafactory", "metafactory", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"), new Object[]{Type.
                getType("(Ljava/lang/Object;)V"), new Handle(Opcodes.H_INVOKESTATIC, "dcc/TryAsmAPI", "lambda$argTypes$0", "(Ljava/lang/Object;)V"), Type.
                getType("(Ljava/lang/Object;)V")});
            mv.visitLabel(l43);
            mv.visitJumpInsn(GOTO, l45);
            mv.visitLabel(l45);
            mv.
                    visitFrame(Opcodes.F_NEW, 5, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "java/util/List"}, 2, new Object[]{"java/util/List", "java/util/function/Consumer"});
            mv.visitVarInsn(ASTORE, 6);
            mv.visitVarInsn(ASTORE, 5);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.
                    visitMethodInsn(INVOKEINTERFACE, "java/util/List", "forEach", "(Ljava/util/function/Consumer;)V", true);
            mv.visitLabel(l46);
            mv.visitInsn(RETURN);
            mv.visitLabel(l48);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.visitJumpInsn(IFNULL, l49);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popJump", "()I", false);
            Label l58 = new Label();
            Label l59 = new Label();
            Label l60 = new Label();
            Label l61 = new Label();
            Label l62 = new Label();
            Label l63 = new Label();
            Label l64 = new Label();
            Label l65 = new Label();
            Label l66 = new Label();
            Label l67 = new Label();
            Label l68 = new Label();
            Label l69 = new Label();
            Label l70 = new Label();
            Label l71 = new Label();
            Label l72 = new Label();
            Label l73 = new Label();
            Label l74 = new Label();
            mv.
                    visitTableSwitchInsn(0, 15, l74, new Label[]{l58, l59, l60, l61, l62, l63, l64, l65, l66, l67, l68, l69, l70, l71, l72, l73});
            mv.visitLabel(l58);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Class;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Class;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Class;");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Object;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "LargTypes;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitJumpInsn(GOTO, l0);
            mv.visitLabel(l2);
            mv.
                    visitFrame(Opcodes.F_NEW, 7, new Object[]{"argTypes", "java/lang/Class", "java/lang/String", "[Ljava/lang/Class;", "[Ljava/lang/Class;", Opcodes.INTEGER, "[Ljava/lang/Object;"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 6);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(0));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l59);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Class;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Class;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "LargTypes;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Class;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Class;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Class;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 5);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Object;");
            mv.visitVarInsn(ASTORE, 6);
            mv.visitJumpInsn(GOTO, l3);
            mv.visitLabel(l5);
            mv.
                    visitFrame(Opcodes.F_NEW, 10, new Object[]{"argTypes", "java/lang/Class", "java/lang/String", "[Ljava/lang/Class;", "[Ljava/lang/Class;", Opcodes.INTEGER, "[Ljava/lang/Object;", "java/lang/Class", "java/lang/String", "[Ljava/lang/Class;"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 6);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 7);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(1));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l60);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/reflect/Method;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "LargTypes;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Class;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Class;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Class;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 5);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Object;");
            mv.visitVarInsn(ASTORE, 6);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Class;");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Class;");
            mv.visitVarInsn(ASTORE, 9);
            mv.visitJumpInsn(GOTO, l6);
            mv.visitLabel(l8);
            mv.
                    visitFrame(Opcodes.F_NEW, 11, new Object[]{"argTypes", "java/lang/Class", "java/lang/String", "[Ljava/lang/Class;", "[Ljava/lang/Class;", Opcodes.INTEGER, "[Ljava/lang/Object;", "java/lang/Class", "java/lang/String", "[Ljava/lang/Class;", "java/lang/reflect/Method"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 7);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 6);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 10);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(2));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l61);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/commons/Method;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "LargTypes;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/commons/Method;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Class;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Class;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 5);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Object;");
            mv.visitVarInsn(ASTORE, 6);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Class;");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Class;");
            mv.visitVarInsn(ASTORE, 9);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/reflect/Method;");
            mv.visitVarInsn(ASTORE, 10);
            mv.visitJumpInsn(GOTO, l9);
            mv.visitLabel(l11);
            mv.
                    visitFrame(Opcodes.F_NEW, 12, new Object[]{"argTypes", "org/objectweb/asm/commons/Method", "java/lang/String", "[Ljava/lang/Class;", "[Ljava/lang/Class;", Opcodes.INTEGER, "[Ljava/lang/Object;", "java/lang/Class", "java/lang/String", "[Ljava/lang/Class;", "java/lang/reflect/Method", "org/objectweb/asm/commons/Method"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 10);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 7);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 6);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 11);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(3));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l62);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "LargTypes;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/commons/Method;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Class;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Class;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 5);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Object;");
            mv.visitVarInsn(ASTORE, 6);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Class;");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Ljava/lang/Class;");
            mv.visitVarInsn(ASTORE, 9);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/reflect/Method;");
            mv.visitVarInsn(ASTORE, 10);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/commons/Method;");
            mv.visitVarInsn(ASTORE, 11);
            mv.visitJumpInsn(GOTO, l12);
            mv.visitLabel(l14);
            mv.
                    visitFrame(Opcodes.F_NEW, 13, new Object[]{"argTypes", "org/objectweb/asm/commons/Method", "java/lang/String", "[Ljava/lang/Class;", "[Ljava/lang/Class;", Opcodes.INTEGER, "[Ljava/lang/Object;", "java/lang/Class", "java/lang/String", "[Ljava/lang/Class;", "java/lang/reflect/Method", "org/objectweb/asm/commons/Method", "java/lang/String"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 11);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 10);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 7);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 6);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 12);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(4));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l63);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/commons/Method;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 5);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 6);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitJumpInsn(GOTO, l15);
            mv.visitLabel(l17);
            mv.
                    visitFrame(Opcodes.F_NEW, 9, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type", "org/objectweb/asm/Type"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 7);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(5));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l64);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Integer;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/commons/Method;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 5);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 6);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitJumpInsn(GOTO, l18);
            mv.visitLabel(l20);
            mv.
                    visitFrame(Opcodes.F_NEW, 10, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type", "java/util/List", "java/lang/Integer"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 7);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(6));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l65);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Integer;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/commons/Method;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 5);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 6);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitJumpInsn(GOTO, l21);
            mv.visitLabel(l23);
            mv.
                    visitFrame(Opcodes.F_NEW, 10, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type", "java/util/List", "java/lang/Integer"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 7);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(7));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l66);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Integer;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/commons/Method;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 5);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 6);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitJumpInsn(GOTO, l24);
            mv.visitLabel(l26);
            mv.
                    visitFrame(Opcodes.F_NEW, 10, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type", "java/util/List", "java/lang/Integer"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 7);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(8));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l67);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Integer;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/commons/Method;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 5);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 6);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Integer;");
            mv.visitVarInsn(ASTORE, 9);
            mv.visitJumpInsn(GOTO, l27);
            mv.visitLabel(l29);
            mv.
                    visitFrame(Opcodes.F_NEW, 12, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type", "java/util/List", "java/lang/Integer", "java/util/List", "java/lang/Integer"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 7);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 11);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 10);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(9));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l68);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Integer;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/commons/Method;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 5);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 6);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitJumpInsn(GOTO, l30);
            mv.visitLabel(l32);
            mv.
                    visitFrame(Opcodes.F_NEW, 10, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type", "java/util/List", "java/lang/Integer"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 7);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(10));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l69);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Integer;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/commons/Method;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 5);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 6);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Integer;");
            mv.visitVarInsn(ASTORE, 9);
            mv.visitJumpInsn(GOTO, l33);
            mv.visitLabel(l35);
            mv.
                    visitFrame(Opcodes.F_NEW, 12, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type", "java/util/List", "java/lang/Integer", "java/util/List", "java/lang/Integer"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 7);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 11);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 10);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(11));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l70);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/commons/Method;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 5);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 6);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitJumpInsn(GOTO, l36);
            mv.visitLabel(l38);
            mv.
                    visitFrame(Opcodes.F_NEW, 10, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type", "java/util/List", "org/objectweb/asm/Type"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 7);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(12));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l71);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/commons/Method;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 5);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ISTORE, 6);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 7);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 8);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 9);
            mv.visitJumpInsn(GOTO, l39);
            mv.visitLabel(l41);
            mv.
                    visitFrame(Opcodes.F_NEW, 12, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "[Lorg/objectweb/asm/Type;", Opcodes.INTEGER, Opcodes.INTEGER, "org/objectweb/asm/Type", "java/util/List", "org/objectweb/asm/Type", "java/util/List", "java/lang/String"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 9);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 8);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 7);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 6);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 5);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 11);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 10);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(13));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l72);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/commons/Method;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitJumpInsn(GOTO, l42);
            mv.visitLabel(l44);
            mv.
                    visitFrame(Opcodes.F_NEW, 5, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "java/util/List"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(14));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l73);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/function/Consumer;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/commons/Method;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "[Lorg/objectweb/asm/Type;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitJumpInsn(GOTO, l45);
            mv.visitLabel(l47);
            mv.
                    visitFrame(Opcodes.F_NEW, 7, new Object[]{"dcc/TryAsmAPI", "org/objectweb/asm/commons/Method", "[Lorg/objectweb/asm/Type;", "java/util/List", "java/util/List", "java/util/List", "java/util/function/Consumer"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 6);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 5);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(15));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l74);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"argTypes"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "Ldcc/rt/Cont;", "invalidCont", "()V", false);
            mv.visitMaxs(7, 13);
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
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, "dcc/rt/DccException");
            Label l3 = new Label();
            Label l4 = new Label();
            Label l5 = new Label();
            mv.visitTryCatchBlock(l3, l4, l5, "dcc/rt/DccException");
            Label l6 = new Label();
            Label l7 = new Label();
            Label l8 = new Label();
            mv.visitTryCatchBlock(l6, l7, l8, "dcc/rt/DccException");
            Label l9 = new Label();
            Label l10 = new Label();
            Label l11 = new Label();
            mv.visitTryCatchBlock(l9, l10, l11, "dcc/rt/DccException");
            Label l12 = new Label();
            mv.visitJumpInsn(GOTO, l12);
            Label l13 = new Label();
            mv.visitLabel(l13);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"insnState"}, 0, new Object[]{});
            mv.visitLdcInsn(Type.getType("Ldcc/TryAsmAPI;"));
            mv.visitJumpInsn(GOTO, l0);
            mv.visitLabel(l0);
            mv.
                    visitFrame(Opcodes.F_NEW, 1, new Object[]{"insnState"}, 1, new Object[]{"java/lang/Class"});
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getCanonicalName", "()Ljava/lang/String;", false);
            mv.visitLabel(l1);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitTypeInsn(NEW, "java/io/PrintWriter");
            mv.visitInsn(DUP);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.
                    visitMethodInsn(INVOKESPECIAL, "java/io/PrintWriter", "<init>", "(Ljava/io/OutputStream;)V", false);
            mv.visitVarInsn(ASTORE, 2);
            mv.visitJumpInsn(GOTO, l3);
            mv.visitLabel(l3);
            mv.
                    visitFrame(Opcodes.F_NEW, 3, new Object[]{"insnState", "java/lang/String", "java/io/PrintWriter"}, 0, new Object[]{});
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "random", "()D", false);
            mv.visitLabel(l4);
            mv.visitLdcInsn(new Double("0.5"));
            mv.visitInsn(DCMPG);
            Label l14 = new Label();
            mv.visitJumpInsn(IFGE, l14);
            mv.visitTypeInsn(NEW, "org/objectweb/asm/ClassReader");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "org/objectweb/asm/ClassReader", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitVarInsn(ASTORE, 3);
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
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(GOTO, l6);
            mv.visitLabel(l6);
            mv.
                    visitFrame(Opcodes.F_NEW, 5, new Object[]{"insnState", "java/lang/String", "java/io/PrintWriter", "org/objectweb/asm/ClassReader", "org/objectweb/asm/util/TraceClassVisitor"}, 3, new Object[]{"org/objectweb/asm/ClassReader", "org/objectweb/asm/util/TraceClassVisitor", Opcodes.INTEGER});
            mv.visitVarInsn(ISTORE, 7);
            mv.visitVarInsn(ASTORE, 6);
            mv.visitVarInsn(ASTORE, 5);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitVarInsn(ILOAD, 7);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "org/objectweb/asm/ClassReader", "accept", "(Lorg/objectweb/asm/ClassVisitor;I)V", false);
            mv.visitLabel(l7);
            mv.visitJumpInsn(GOTO, l10);
            mv.visitLabel(l14);
            mv.
                    visitFrame(Opcodes.F_NEW, 3, new Object[]{"dcc/TryAsmAPI", "java/lang/String", "java/io/PrintWriter"}, 0, new Object[]{});
            mv.visitTypeInsn(NEW, "org/objectweb/asm/ClassReader");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "org/objectweb/asm/ClassReader", "<init>", "(Ljava/lang/String;)V", false);
            mv.visitVarInsn(ASTORE, 3);
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
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitInsn(ICONST_0);
            mv.visitJumpInsn(GOTO, l9);
            mv.visitLabel(l9);
            mv.
                    visitFrame(Opcodes.F_NEW, 5, new Object[]{"dcc/TryAsmAPI", "java/lang/String", "java/io/PrintWriter", "org/objectweb/asm/ClassReader", "org/objectweb/asm/util/TraceClassVisitor"}, 3, new Object[]{"org/objectweb/asm/ClassReader", "org/objectweb/asm/util/TraceClassVisitor", Opcodes.INTEGER});
            mv.visitVarInsn(ISTORE, 7);
            mv.visitVarInsn(ASTORE, 6);
            mv.visitVarInsn(ASTORE, 5);
            mv.visitVarInsn(ALOAD, 5);
            mv.visitVarInsn(ALOAD, 6);
            mv.visitVarInsn(ILOAD, 7);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "org/objectweb/asm/ClassReader", "accept", "(Lorg/objectweb/asm/ClassVisitor;I)V", false);
            mv.visitLabel(l10);
            mv.
                    visitFrame(Opcodes.F_NEW, 3, new Object[]{"dcc/TryAsmAPI", "java/lang/String", "java/io/PrintWriter"}, 0, new Object[]{});
            mv.visitInsn(RETURN);
            mv.visitLabel(l12);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"insnState"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.visitJumpInsn(IFNULL, l13);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popJump", "()I", false);
            Label l15 = new Label();
            Label l16 = new Label();
            Label l17 = new Label();
            Label l18 = new Label();
            Label l19 = new Label();
            mv.visitTableSwitchInsn(0, 3, l19, new Label[]{l15, l16, l17, l18});
            mv.visitLabel(l15);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"insnState"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Class;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "LinsnState;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitJumpInsn(GOTO, l0);
            mv.visitLabel(l2);
            mv.
                    visitFrame(Opcodes.F_NEW, 2, new Object[]{"insnState", "java/lang/Class"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(0));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l16);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"insnState"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "LinsnState;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/io/PrintWriter;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitJumpInsn(GOTO, l3);
            mv.visitLabel(l5);
            mv.
                    visitFrame(Opcodes.F_NEW, 3, new Object[]{"insnState", "java/lang/String", "java/io/PrintWriter"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(1));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l17);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"insnState"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassReader;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/util/TraceClassVisitor;");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "LinsnState;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/io/PrintWriter;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassReader;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/util/TraceClassVisitor;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitJumpInsn(GOTO, l6);
            mv.visitLabel(l8);
            mv.
                    visitFrame(Opcodes.F_NEW, 8, new Object[]{"insnState", "java/lang/String", "java/io/PrintWriter", "org/objectweb/asm/ClassReader", "org/objectweb/asm/util/TraceClassVisitor", "org/objectweb/asm/ClassReader", "org/objectweb/asm/util/TraceClassVisitor", Opcodes.INTEGER}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 7);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 6);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 5);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(2));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l18);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"insnState"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassReader;");
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/util/TraceClassVisitor;");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popInt", "()I", false);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ldcc/TryAsmAPI;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/String;");
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/io/PrintWriter;");
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/ClassReader;");
            mv.visitVarInsn(ASTORE, 3);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Lorg/objectweb/asm/util/TraceClassVisitor;");
            mv.visitVarInsn(ASTORE, 4);
            mv.visitJumpInsn(GOTO, l9);
            mv.visitLabel(l11);
            mv.
                    visitFrame(Opcodes.F_NEW, 8, new Object[]{"dcc/TryAsmAPI", "java/lang/String", "java/io/PrintWriter", "org/objectweb/asm/ClassReader", "org/objectweb/asm/util/TraceClassVisitor", "org/objectweb/asm/ClassReader", "org/objectweb/asm/util/TraceClassVisitor", Opcodes.INTEGER}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 4);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 3);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 7);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushInt", "(I)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 6);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 5);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(3));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l19);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"insnState"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "Ldcc/rt/Cont;", "invalidCont", "()V", false);
            mv.visitMaxs(5, 8);
            mv.visitEnd();
        }
        {
            mv = cw.
                    visitMethod(ACC_PRIVATE + ACC_STATIC + ACC_SYNTHETIC, "lambda$argTypes$0", "(Ljava/lang/Object;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, "dcc/rt/DccException");
            Label l3 = new Label();
            mv.visitJumpInsn(GOTO, l3);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"java/lang/Object"}, 0, new Object[]{});
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(ALOAD, 0);
            mv.visitJumpInsn(GOTO, l0);
            mv.visitLabel(l0);
            mv.
                    visitFrame(Opcodes.F_NEW, 1, new Object[]{"java/lang/Object"}, 2, new Object[]{"java/io/PrintStream", "java/lang/Object"});
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/Object;)V", false);
            mv.visitLabel(l1);
            mv.visitInsn(RETURN);
            mv.visitLabel(l3);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"java/lang/Object"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 0);
            mv.visitJumpInsn(IFNULL, l4);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popJump", "()I", false);
            Label l5 = new Label();
            Label l6 = new Label();
            mv.visitTableSwitchInsn(0, 0, l6, new Label[]{l5});
            mv.visitLabel(l5);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"java/lang/Object"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/io/PrintStream;");
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Object;");
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "popObject", "()Ljava/lang/Object;", false);
            mv.visitTypeInsn(CHECKCAST, "Ljava/lang/Object;");
            mv.visitVarInsn(ASTORE, 0);
            mv.visitJumpInsn(GOTO, l0);
            mv.visitLabel(l2);
            mv.
                    visitFrame(Opcodes.F_NEW, 3, new Object[]{"java/lang/Object", "java/io/PrintStream", "java/lang/Object"}, 1, new Object[]{"dcc/rt/DccException"});
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/DccException", "getCont", "()Ldcc/rt/Cont;", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 0);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 2);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitVarInsn(ALOAD, 1);
            mv.
                    visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushObject", "(Ljava/lang/Object;)V", false);
            mv.visitInsn(DUP);
            mv.visitLdcInsn(new Integer(0));
            mv.visitMethodInsn(INVOKEVIRTUAL, "dcc/rt/Cont", "pushJump", "(I)V", false);
            mv.visitTypeInsn(NEW, "dcc/rt/DccException");
            mv.visitInsn(DUP_X1);
            mv.visitInsn(SWAP);
            mv.
                    visitMethodInsn(INVOKESPECIAL, "dcc/rt/DccException", "<init>", "(Ldcc/rt/Cont;)V", false);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l6);
            mv.visitFrame(Opcodes.F_NEW, 1, new Object[]{"java/lang/Object"}, 0, new Object[]{});
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "Ldcc/rt/Cont;", "invalidCont", "()V", false);
            mv.visitMaxs(3, 3);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}
