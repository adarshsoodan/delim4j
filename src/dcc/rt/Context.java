package dcc.rt;

import java.util.Arrays;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Context implements Cloneable {

    public static final Object start(Function<Context, Object> context) {
        try {
            return context.apply(null);
        } catch (DccException e) {
            Context cont = e.getCont();
            Resumable resumable = new Resumable(cont, context);
            Function<Resumable, Object> receiver = cont.receiver;
            return receiver.apply(resumable);
        }
    }

    public static final Object capture(Context cont,
                                       Function<Resumable, Object> receiver) {
        if (cont == null) {
            // Start - capture the stack
            throw new DccException(new Context(receiver));
        } else {
            // Finish - resume the stack
            return cont.getSubstitution();
        }
    }

    static final int increment = 16;
    Function<Resumable, Object> receiver;
    Object substitution;

    int[] jumps;
    int[] ints;
    float[] floats;
    long[] longs;
    double[] doubles;
    Object[] objects;

    int posJump;

    int posInt;
    int posFloat;
    int posLong;
    int posDouble;
    int posObject;

    public Context(Function<Resumable, Object> receiver) {
        this.receiver = receiver;
        posJump = posInt = posFloat = posLong = posDouble = posObject = 0;
        jumps = new int[increment];
        ints = new int[increment];
        floats = new float[increment];
        longs = new long[increment];
        doubles = new double[increment];
        objects = new Object[increment];
    }

    public Object getSubstitution() {
        return substitution;
    }

    public void setSubstitution(Object substitution) {
        this.substitution = substitution;
    }

    public int popJump() {
        --posJump;
        return jumps[posJump];
    }

    public void pushJump(final int x) {
        if (posJump == jumps.length) {
            jumps = Arrays.copyOf(jumps, jumps.length + increment);
        }
        jumps[posJump] = x;
        ++posJump;
    }

    public int popInt() {
        --posInt;
        return ints[posInt];
    }

    public void pushInt(final int x) {
        if (posInt == ints.length) {
            ints = Arrays.copyOf(ints, ints.length + increment);
        }
        ints[posInt] = x;
        ++posInt;
    }

    public float popFloat() {
        --posFloat;
        return floats[posFloat];
    }

    public void pushFloat(final float x) {
        if (posFloat == floats.length) {
            floats = Arrays.copyOf(floats, floats.length + increment);
        }
        floats[posFloat] = x;
        ++posFloat;
    }

    public long popLong() {
        --posLong;
        return longs[posLong];
    }

    public void pushLong(final long x) {
        if (posLong == longs.length) {
            longs = Arrays.copyOf(longs, longs.length + increment);
        }
        longs[posLong] = x;
        ++posLong;
    }

    public double popDouble() {
        --posDouble;
        return doubles[posDouble];
    }

    public void pushDouble(final double x) {
        if (posDouble == doubles.length) {
            doubles = Arrays.copyOf(doubles, doubles.length + increment);
        }
        doubles[posDouble] = x;
        ++posDouble;
    }

    public Object popObject() {
        --posObject;
        return objects[posObject];
    }

    public void pushObject(final Object o) {
        if (posObject == objects.length) {
            objects = Arrays.copyOf(objects, objects.length + increment);
        }
        objects[posObject] = o;
        ++posObject;
    }

    public void invalidCont() {
        throw new RuntimeException("This is an invalid Cont - " + this);
    }

    @Override
    public Context clone() {
        try {
            return (Context) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
