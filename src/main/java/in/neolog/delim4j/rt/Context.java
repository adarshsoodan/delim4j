package in.neolog.delim4j.rt;

import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.function.Function;

public final class Context implements Cloneable, Serializable {

    public static final long   serialVersionUID = 1;
    public static final String desc             = Context.class.getName()
                                                               .replace('.', '/');
    public static final String argDesc          = "(L" + desc + ";";

    public enum State {
        Capturing, Captured, Cloned, Resuming
    };

    public static Object start(Function<Context, Object> frames) {
        try {
            return frames.apply(null);
        } catch (DelimException e) {
            Context context = e.getContext();
            context.finishCapture();
            Resumable resumable = new Resumable(context, frames);
            Function<Resumable, Object> receiver = context.receiver;
            return receiver.apply(resumable);
        }
    }

    public static Object capture(Context context, Function<Resumable, Object> receiver) {
        if (context == null) {
            // Start - capture the stack
            throw new DelimException(new Context(receiver));
        }
        // Finish - resume the stack if context is Resuming
        if (context.getState() == State.Resuming) {
            return context.getSubstitution();
        }
        throw new RuntimeException("Context is not in state Resuming.\n Current State = " + context.getState());
    }

    private final transient Function<Resumable, Object> receiver;
    private Object                                      substitution;
    private State                                       state;
    private final SoftReference<Context>                clonedFrom;

    private static final int increment = 8;

    private int[]    jumps;
    private int[]    ints;
    private float[]  floats;
    private long[]   longs;
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
        state = State.Capturing;
        clonedFrom = null;
        posJump = posInt = posFloat = posLong = posDouble = posObject = 0;
        jumps = null;
        ints = null;
        floats = null;
        longs = null;
        doubles = null;
        objects = null;
    }

    public Context getClonedFrom() {
        return clonedFrom.get();
    }

    public State getState() {
        return state;
    }

    public void finishCapture() {
        if (getState() == State.Captured) {
            return;
        }
        if (posJump <= 0) {
            throw new IllegalStateException(
                    "Cannot mark a Context as Captured, which has an empty jump table." + "\n posJump = " + posJump);
        }
        if (getState() != State.Capturing) {
            throw new IllegalStateException(
                    "Cannot mark a Context as Captured which is not in state Capturing.\n Current State = "
                            + getState());
        }
        state = State.Captured;
    }

    public void startResumption() {
        if (getState() != State.Cloned) {
            throw new IllegalStateException(
                    "Cannot mark a Context as Resuming which is not in state Cloned.\n Current State = " + getState());
        }
        if (getState() == State.Cloned) {
            state = State.Resuming;
        }
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
        if (jumps == null) {
            jumps = new int[increment];
        } else if (posJump == jumps.length) {
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
        if (ints == null) {
            ints = new int[increment];
        } else if (posInt == ints.length) {
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
        if (floats == null) {
            floats = new float[increment];
        } else if (posFloat == floats.length) {
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
        if (longs == null) {
            longs = new long[increment];
        } else if (posLong == longs.length) {
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
        if (doubles == null) {
            doubles = new double[increment];
        } else if (posDouble == doubles.length) {
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
        if (objects == null) {
            objects = new Object[increment];
        } else if (posObject == objects.length) {
            objects = Arrays.copyOf(objects, objects.length + increment);
        }
        objects[posObject] = o;
        ++posObject;
    }

    @Override
    public Context clone() {
        if (getState() != State.Captured) {
            throw new IllegalStateException(
                    "Cannot clone a Context which is not in state Captured.\n Current State = " + getState());
        }
        Context ret = new Context(receiver, substitution, State.Cloned, new SoftReference<Context>(this), jumps, ints,
                floats, longs, doubles, objects, posJump, posInt, posFloat, posLong, posDouble, posObject);
        return ret;
    }

    private Context(Function<Resumable, Object> receiver, Object substitution, State state,
            SoftReference<Context> clonedFrom, int[] jumps, int[] ints, float[] floats, long[] longs, double[] doubles,
            Object[] objects, int posJump, int posInt, int posFloat, int posLong, int posDouble, int posObject) {
        this.receiver = receiver;
        this.substitution = substitution;
        this.state = state;
        this.clonedFrom = clonedFrom;
        this.jumps = jumps;
        this.ints = ints;
        this.floats = floats;
        this.longs = longs;
        this.doubles = doubles;
        this.objects = objects;
        this.posJump = posJump;
        this.posInt = posInt;
        this.posFloat = posFloat;
        this.posLong = posLong;
        this.posDouble = posDouble;
        this.posObject = posObject;
    }

}
