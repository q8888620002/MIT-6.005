package calculator;

public class SyntaxErrorException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SyntaxErrorException(String message) {
		super(message);
	}
}
