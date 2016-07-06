package dcc.rt;

public class DccException extends RuntimeException {

    public static final String desc = "dcc/rt/DccException";

    Context context;

    public DccException(final Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
