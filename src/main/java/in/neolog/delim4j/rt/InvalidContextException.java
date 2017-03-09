package in.neolog.delim4j.rt;

public class InvalidContextException extends RuntimeException {

    private static final long serialVersionUID = -6552988729299845946L;

    public static final String desc = InvalidContextException.class.getName().replace('.', '/');

    public InvalidContextException() {
        super("Method called with invalid Context. Invalid jump location");
    }

}
