package sqlbuilder;

public class BadSQLSyntaxException extends RuntimeException {
    public BadSQLSyntaxException(String message) {
        super(message);
    }
}
