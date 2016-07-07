package org.decon.rt;

import java.util.function.Function;

public class Resumable {

    Context cont;
    Function<Context, Object> context;

    public Object resume(Object o) {
        Context cloned = cont.clone();
        cloned.setSubstitution(o);
        return context.apply(cloned);
    }

    public Resumable(Context cont, Function<Context, Object> context) {
        this.cont = cont;
        this.context = context;
    }

}
