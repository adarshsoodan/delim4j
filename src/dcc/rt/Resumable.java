package dcc.rt;

import java.util.function.Function;

public class Resumable {

    Cont cont;
    Function<Cont, Object> context;

    public Object resume(Object o) {
        Cont cloned = cont.clone();
        cloned.setSubstitution(o);
        return context.apply(cloned);
    }

    public Resumable(Cont cont, Function<Cont, Object> context) {
        this.cont = cont;
        this.context = context;
    }

}
