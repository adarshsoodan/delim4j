package org.decon.test;

import org.decon.rt.Context;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.decon.rt.Cc;

public class DummyClass implements BiFunction<Context, Function<Context, Object>, Object> {

    public Object entry1(@Cc Context cont, Function<Context, Object> action) {
        middle1(cont, action);
        return Integer.parseInt("-1");
    }

    public Object middle1(@Cc Context cont, Function<Context, Object> action) {
        return action.apply(cont);
    }

    @Override
    public Object apply(@Cc Context cont, Function<Context, Object> action) {
        return entry1(cont, action);
    }

}
