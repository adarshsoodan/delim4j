package dcc.rt;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Cont implements Cloneable {

    Function<Object, Object> f;

//    final List<int> jumps;
//    final List<int> ints;
//    final List<float> floats;
//    final List<long> longs;
//    final List<double> doubles;
    List<Object> objects;

    int posJump;

    int posInt;
    int posFloat;
    int posLong;
    int posDouble;
    int posObject;

    public Cont(final Function<Object, Object> f) {
        this.f = f;
        posJump = posInt = posFloat = posLong = posDouble = posObject = 0;
        objects = new ArrayList<>();
    }

    final public int popJump() {
        --posJump;
        return -1;
    }

    final public void pushJump(final int jump) {
        // jumps.add(jump);
        ++posJump;
    }

    final public int popInt() {
        --posInt;
        return -1;
    }

    final public void pushInt(final int x) {
        // ints.add(x);
        ++posInt;
    }

    final public float popFloat() {
        --posFloat;
        return -1.0f;
    }

    final public void pushFloat(final float x) {
        // floats.add(x);
        ++posFloat;
    }

    final public long popLong() {
        --posLong;
        return -1L;
    }

    final public void pushLong(final long x) {
        // longs.add(x);
        ++posLong;
    }

    final public double popDouble() {
        --posDouble;
        return -1.0;
    }

    final public void pushDouble(final double x) {
        // doubles.add(x);
        ++posDouble;
    }

    final public Object popObject() {
        --posObject;
        final Object ret = objects.get(posObject);
        return ret;
    }

    final public void pushObject(final Object o) {
        objects.add(o);
        ++posObject;
    }

    final void invalidCont() {
        throw new RuntimeException("This is an invalid Cont - " + this);
    }

    @Override
    final public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
