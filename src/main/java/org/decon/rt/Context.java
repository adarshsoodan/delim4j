package org.decon.rt;

import java.io.Serializable;
import java.util.Arrays;
import java.util.function.Function;
import org.objectweb.asm.Type;

public final class Context implements Cloneable, Serializable {

    public static final long serialVersionUID = 1;
    public static final String desc = Type.getInternalName(Context.class);
    public static final String argDesc = "(L" + desc + ";";

    public static Object start(Function<Context, Object> frames) {
        try {
            return frames.apply(null);
        } catch (DccException e) {
            Context context = e.getContext();
            Resumable resumable = new Resumable(context, frames);
            Function<Resumable, Object> receiver = context.receiver;
            return receiver.apply(resumable);
        }
    }

    public static Object capture(Context context,
                                       Function<Resumable, Object> receiver) {
        if (context == null) {
            // Start - capture the stack
            throw new DccException(new Context(receiver));
        } else {
            // Finish - resume the stack
            return context.getSubstitution();
        }
    }

    private static final int increment = 16;
    private final transient Function<Resumable, Object> receiver;
    private Object substitution;

    private int[] jumps;
    private int[] ints;
    private float[] floats;
    private long[] longs;
    private double[] doubles;
    private Object[] objects;

    private int posJump;

    private int posInt;
    private int posFloat;
    private int posLong;
    private int posDouble;
    private int posObject;

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

    public void invalidContext() {
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
