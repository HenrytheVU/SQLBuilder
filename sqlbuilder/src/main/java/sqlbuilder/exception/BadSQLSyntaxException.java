package sqlbuilder.exception;

public class BadSQLSyntaxException extends RuntimeException {
	private static final long serialVersionUID = 1338100450662411771L;

	public BadSQLSyntaxException(String message) {
		super(message);
	}
}
