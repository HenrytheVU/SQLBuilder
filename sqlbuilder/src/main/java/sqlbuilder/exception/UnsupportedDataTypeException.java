package sqlbuilder.exception;

public class UnsupportedDataTypeException extends RuntimeException {

    private static final long serialVersionUID = -1552259696740194671L;

    public UnsupportedDataTypeException(String message) {
        super(message);
    }
}
