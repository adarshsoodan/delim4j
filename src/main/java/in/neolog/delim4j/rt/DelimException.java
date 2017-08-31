package in.neolog.delim4j.rt;

public class DelimException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static final String desc = DelimException.class.getName()
                                                          .replace('.', '/');

    private final Context context;

    public DelimException(final Context context) {
        super(null, null, false, false); // For performance last flag = false
        this.context = context;
    }

    public Context getContext() {
        return context;
    }
}
