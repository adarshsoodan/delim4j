/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcc;

import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 *
 * @author user
 */
public class FrameT {

    public static Object fromType(Type t) {
        switch (t.getSort()) {
            case Type.INT:
            case Type.BOOLEAN:
            case Type.BYTE:
            case Type.CHAR:
                return Opcodes.INTEGER;
            case Type.FLOAT:
                return Opcodes.FLOAT;
            case Type.LONG:
                return Opcodes.LONG;
            case Type.DOUBLE:
                return Opcodes.DOUBLE;
            case Type.ARRAY:
            case Type.OBJECT:
                return t.getDescriptor();
            default:
                throw new RuntimeException("Unknown or uninitialized type" + t.getDescriptor());
        }
    }

    public static Type fromFrame(Object f) {
        if (f.equals(Opcodes.INTEGER)) {
            return Type.INT_TYPE;
        } else if (f.equals(Opcodes.FLOAT)) {
            return Type.FLOAT_TYPE;
        } else if (f.equals(Opcodes.LONG)) {
            return Type.LONG_TYPE;
        } else if (f.equals(Opcodes.DOUBLE)) {
            return Type.DOUBLE_TYPE;
        } else if (f.equals(Opcodes.TOP)) {
            return null;
        } else if (f instanceof String) {
            return Type.getObjectType((String) f);
        } else {
            throw new RuntimeException("Unknown or uninitialized type" + f);
        }
    }
}
