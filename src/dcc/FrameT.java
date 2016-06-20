package dcc;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class FrameT {

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

    public static Type[] fromFrame(Object[] fs) {
        Type[] ret = new Type[fs.length];
        for (int i = 0; i < ret.length; ++i) {
            ret[i] = fromFrame(fs[i]);
        }
        return ret;
    }

}
