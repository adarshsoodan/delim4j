package in.neolog.delim4j.rt;

public class InvalidContextException extends RuntimeException {

    private static final long serialVersionUID = -6552988729299845946L;

    public static final String desc = InvalidContextException.class.getName().replace('.', '/');
    private final Context context;

    public InvalidContextException(final Context context) {
        super("Method called with invalid Context. Invalid jump location", null, true, true);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

}
