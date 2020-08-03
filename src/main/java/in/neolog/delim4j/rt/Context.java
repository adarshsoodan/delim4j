package in.neolog.delim4j.rt;

import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class Context<T, S> implements Cloneable, Serializable {

    public static final long   serialVersionUID = 1;
    public static final String desc             = Context.class.getName()
                                                               .replace('.', '/');
    public static final String objectDesc       = Object.class.getName()
                                                              .replace('.', '/');
    public static final String argDesc          = "(L" + desc + ";";

    public enum State {
        Capturing, Captured, Cloned, Resuming
    };

    @SuppressWarnings({ "unchecked" })
    public static <T, S> T start(Context<T, S> cc, Function<Context<T, S>, T> frames) {
        if (cc == null) {
            try {
                return frames.apply(null);
            } catch (DelimException e) {
                Context<T, S> context = e.getContext();
                context.finishCapture();
                Resumable<T, S> resumable = new Resumable<>(context, frames);
                BiFunction<Context<T, S>, Resumable<T, S>, T> shift = context.shift;
                try {
                    return shift.apply(null, resumable);
                } catch (DelimException ee) {
                    Context<T, S> otherCc = ee.getContext();
                    otherCc.pushObject(shift);
                    throw ee;
                }
            }
        } else {
            BiFunction<Context<T, S>, Resumable<T, S>, T> shift =
                                                                (BiFunction<Context<T, S>, Resumable<T, S>, T>) cc.popObject();
            try {
                return shift.apply(cc, null);
            } catch (DelimException ee) {
                Context<T, S> otherCc = ee.getContext();
                otherCc.pushObject(shift);
                throw ee;
            }
        }
    }

    public static <T, S> S capture(Context<T, S> context, BiFunction<Context<T, S>, Resumable<T, S>, T> shift) {
        if (context == null) {
            // Start - capture the stack
            throw new DelimException(new Context<T, S>(shift));
        }
        // Finish - resume the stack if context is Resuming
        if (context.getState() == State.Resuming) {
            return context.getSubstitution();
        }
        throw new RuntimeException("Context is not in state Resuming.\n Current State = " + context.getState());
    }

    private final transient SoftReference<Context<T, S>> clonedFrom;

    private final transient BiFunction<Context<T, S>, Resumable<T, S>, T> shift;

    private S     substitution;
    private State state;

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

    public Context(BiFunction<Context<T, S>, Resumable<T, S>, T> shift) {
        this.shift = shift;
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

    public Context<T, S> getClonedFrom() {
        return clonedFrom.get();
    }

    public State getState() {
        return state;
    }

    // TODO trim array sizes to minimum possible size
    public void finishCapture() {
        if (getState() == State.Captured) {
            return;
        }
        if (posJump <= 0) {
            throw new IllegalStateException("Cannot mark a Context as Captured, which has an empty jump table."
                                            + " Perhaps byte code is not instrumented?.\n posJump = " + posJump);
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

    public S getSubstitution() {
        return substitution;
    }

    public void setSubstitution(S substitution) {
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

    public void pushObject(final Object obj) {
        if (objects == null) {
            objects = new Object[increment];
        } else if (posObject == objects.length) {
            objects = Arrays.copyOf(objects, objects.length + increment);
        }
        if (obj instanceof Context) {
            objects[posObject] = null;
        } else {
            objects[posObject] = obj;
        }
        ++posObject;
    }

    @Override
    public Context<T, S> clone() {
        if (getState() != State.Captured) {
            throw new IllegalStateException(
                    "Cannot clone a Context which is not in state Captured.\n Current State = " + getState());
        }
        Context<T, S> ret = new Context<T, S>(shift, substitution, State.Cloned, new SoftReference<Context<T, S>>(this),
                jumps, ints, floats, longs, doubles, objects, posJump, posInt, posFloat, posLong, posDouble, posObject);
        return ret;
    }

    private Context(BiFunction<Context<T, S>, Resumable<T, S>, T> shift, S substitution, State state,
            SoftReference<Context<T, S>> clonedFrom, int[] jumps, int[] ints, float[] floats, long[] longs,
            double[] doubles, Object[] objects, int posJump, int posInt, int posFloat, int posLong, int posDouble,
            int posObject) {
        this.shift = shift;
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
