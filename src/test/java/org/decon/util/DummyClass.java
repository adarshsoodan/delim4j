/*
 * Copyright Adarsh Soodan, 2016
 * Licensed under http://www.apache.org/licenses/LICENSE-2.0
 */
package org.decon.util;

import java.util.function.BiFunction;
import java.util.function.Function;

import in.neolog.delim4j.rt.Cc;
import in.neolog.delim4j.rt.Context;

public class DummyClass
        implements BiFunction<Context<String, String>, Function<Context<String, String>, String>, String> {

    public String entry1(@Cc Context<String, String> cont, Function<Context<String, String>, String> action) {
        return middle1(cont, action);
    }

    public String middle1(@Cc Context<String, String> cont, Function<Context<String, String>, String> action) {
        return action.apply(cont);
    }
    
    @Override
    public String apply(@Cc Context<String, String> cont, Function<Context<String, String>, String> action) {
        return entry1(cont, action);
    }
    
}
