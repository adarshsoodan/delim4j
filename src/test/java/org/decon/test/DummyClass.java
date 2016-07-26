package org.decon.test;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.decon.rt.Cc;
import org.decon.rt.Context;

public class DummyClass implements BiFunction<Context, Function<Context, Object>, Object> {

    public Object entry1(@Cc Context cont, Function<Context, Object> action) {
        return middle1(cont, action);
    }

    public Object middle1(@Cc Context cont, Function<Context, Object> action) {
        return action.apply(cont);
    }

    @Override
    public Object apply(@Cc Context cont, Function<Context, Object> action) {
        return entry1(cont, action);
    }

}
