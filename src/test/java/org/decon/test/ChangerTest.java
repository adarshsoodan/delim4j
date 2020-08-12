/*
 * Copyright Adarsh Soodan, 2016
 * Licensed under http://www.apache.org/licenses/LICENSE-2.0
 */
package org.decon.test;

import java.io.IOException;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;

import in.neolog.delim4j.ContextClassVisitor;

public class ChangerTest {

    // @Test
    public void test() {
        Type t = Type.getType("null");
        Type ot = Type.getType(Type.class);

        System.out.println(t);
        System.out.println(t.getOpcode(Opcodes.ISTORE));

        System.out.println(ot);
        System.out.println(ot.getOpcode(Opcodes.ISTORE));

    }

    @Test
    public void printChangedCode() {
        String className = DelimCCTest.class.getCanonicalName();
        ClassReader reader;
        try {
            reader = new ClassReader(className);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Printer printer = new Textifier();
        TraceClassVisitor tracer = new TraceClassVisitor(null, printer, null);
        ClassVisitor cv = new ContextClassVisitor(tracer);
        reader.accept(cv, ClassReader.EXPAND_FRAMES);
        System.out.println(printer.getText());
    }

    // @Test
    public void printUnchangedCode() {
        String className = DelimCCTest.class.getCanonicalName();
        ClassReader reader;
        try {
            reader = new ClassReader(className);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Printer printer = new Textifier();
        TraceClassVisitor tracer = new TraceClassVisitor(null, printer, null);
        reader.accept(tracer, ClassReader.EXPAND_FRAMES);
        System.out.println(printer.getText());
    }

}
