package org.decon.rt;

public class DccException extends RuntimeException {

    public static final String desc = "dcc/rt/DccException";

    Context context;

    public DccException(final Context context) {
        super(null, null, false, false);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
