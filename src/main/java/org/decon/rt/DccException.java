package org.decon.rt;

import org.objectweb.asm.Type;

public class DccException extends RuntimeException {

    public static final String desc = Type.getInternalName(DccException.class);

    private final Context context;

    public DccException(final Context context) {
        super(null, null, false, false);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
