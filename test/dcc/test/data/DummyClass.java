package dcc.test.data;

import dcc.rt.Cont;

public class DummyClass {

    public int entry1(Cont cont, Runnable action) throws Exception {
        middle1(cont, action);
        return Integer.parseInt("0");
    }

    public int middle1(Cont cont, Runnable action) throws Exception {
        action.run();
        return Integer.parseInt("1");
    }

}
