package org.decon.rt;

import java.util.function.Function;

public class Resumable {

    private final Context                   context;
    private final Function<Context, Object> frames;

    public Object resume(Object o) {
        Context cloned = context.clone();
        cloned.setSubstitution(o);
        cloned.startResumption();
        return frames.apply(cloned);
    }

    public Resumable(Context context, Function<Context, Object> frames) {
        this.context = context;
        this.frames = frames;
    }

}
