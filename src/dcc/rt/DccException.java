package dcc.rt;

public class DccException extends RuntimeException {

    Cont cont;

    public DccException(final Cont cont) {
        this.cont = cont;
    }

    public Cont getCont() {
        return cont;
    }
}
