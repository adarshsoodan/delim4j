package dcc.rt;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Cont implements Cloneable {

    private Function<Object, Object> f;
    
//    private final List<int> ints;
//    private final List<float> floats;
//    private final List<long> longs;
//    private final List<double> doubles;
    private final List<Object> objects;

    private int posInt;
    private int posFloat;
    private int posLong;
    private int posDouble;
    private int posObject;

    public Cont(final Function<Object, Object> f) {
        this.f = f;
        posInt = posFloat = posLong = posDouble = posObject = 0;
        objects = new ArrayList<>();
    }

    public Object popObject() {
        --posObject;
        final Object ret = objects.get(posObject);
        return ret;
    }

    public void pushObject(final Object o) {
        objects.add(o);
        ++posObject;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
