package dcc.rt;

public class DccException extends RuntimeException {

    Context cont;

    public DccException(final Context cont) {
        this.cont = cont;
    }

    public Context getCont() {
        return cont;
    }
}
