package dcc.test.data;

import dcc.rt.Cont;
import dcc.rt.Contify;
import java.util.function.BiFunction;
import java.util.function.Function;

public class DummyClass implements BiFunction<Cont, Function<Cont, Object>, Object> {

    public Object entry1(@Contify Cont cont, Function<Cont, Object> action) {
        middle1(cont, action);
        return Integer.parseInt("-1");
    }

    public Object middle1(@Contify Cont cont, Function<Cont, Object> action) {
        return action.apply(cont);
    }

    @Override
    public Object apply(@Contify Cont cont, Function<Cont, Object> action) {
        return entry1(cont, action);
    }

}
