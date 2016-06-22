package dcc.test.data;

import dcc.rt.Cont;
import dcc.rt.Contify;

public class DummyClass {

    public int entry1(@Contify Cont cont, Runnable action) throws Exception {
        middle1(cont, action);
        return Integer.parseInt("0");
    }

    public int middle1(@Contify Cont cont, Runnable action) throws Exception {
        action.run();
        return Integer.parseInt("1");
    }

}
