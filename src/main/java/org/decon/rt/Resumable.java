package org.decon.rt;

import java.util.function.Function;

public class Resumable {

    private final Context context;
    private final Function<Context, Object> replacement;

    public Object resume(Object o) {
        Context cloned = context.clone();
        cloned.setSubstitution(o);
        return replacement.apply(cloned);
    }

    public Resumable(Context context, Function<Context, Object> replacement) {
        this.context = context;
        this.replacement = replacement;
    }

}
