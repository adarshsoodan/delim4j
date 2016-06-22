package dcc.rt;

import java.util.Arrays;
import java.util.function.Function;

public final class Cont implements Cloneable {

    static final int increment = 16;
    // TODO Semantics of shift operator.
    Function<Object, Object> shifted;

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

    public Cont(Function<Object, Object> shifted) {
        this.shifted = shifted;
        posJump = posInt = posFloat = posLong = posDouble = posObject = 0;
        jumps = new int[increment];
        ints = new int[increment];
        floats = new float[increment];
        longs = new long[increment];
        doubles = new double[increment];
        objects = new Object[increment];
    }

    final public int popJump() {
        --posJump;
        return jumps[posJump];
    }

    final public void pushJump(final int x) {
        if (posJump == jumps.length) {
            jumps = Arrays.copyOf(jumps, jumps.length + increment);
        }
        jumps[posJump] = x;
        ++posJump;
    }

    final public int popInt() {
        --posInt;
        return ints[posInt];
    }

    final public void pushInt(final int x) {
        if (posInt == ints.length) {
            ints = Arrays.copyOf(ints, ints.length + increment);
        }
        ints[posInt] = x;
        ++posInt;
    }

    final public float popFloat() {
        --posFloat;
        return floats[posFloat];
    }

    final public void pushFloat(final float x) {
        if (posFloat == floats.length) {
            floats = Arrays.copyOf(floats, floats.length + increment);
        }
        floats[posFloat] = x;
        ++posFloat;
    }

    final public long popLong() {
        --posLong;
        return longs[posLong];
    }

    final public void pushLong(final long x) {
        if (posLong == longs.length) {
            longs = Arrays.copyOf(longs, longs.length + increment);
        }
        longs[posLong] = x;
        ++posLong;
    }

    final public double popDouble() {
        --posDouble;
        return doubles[posDouble];
    }

    final public void pushDouble(final double x) {
        if (posDouble == doubles.length) {
            doubles = Arrays.copyOf(doubles, doubles.length + increment);
        }
        doubles[posDouble] = x;
        ++posDouble;
    }

    final public Object popObject() {
        --posObject;
        return objects[posObject];
    }

    final public void pushObject(final Object o) {
        if (posObject == objects.length) {
            objects = Arrays.copyOf(objects, objects.length + increment);
        }
        objects[posObject] = o;
        ++posObject;
    }

    final public void invalidCont() {
        throw new RuntimeException("This is an invalid Cont - " + this);
    }

    @Override
    final public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
