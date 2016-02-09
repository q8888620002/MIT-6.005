package calculator;

import calculator.Type;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Calculator lexical analyzer.
 */
public class Lexer {
	
	private final String s;
	private int i = 0;
	// s is the string of input expression that we're parsing,
	// and s[i] is the start of the next token to return, or
	// i == s.length means we're at the end of parsing.

	/**
	 * Token in the stream.
	 */
	public static class Token {
		final Type type;
		final String text;

		Token(Type type, String text) {
			this.type = type;
			this.text = text;
		}

		Token(Type type) {
			this(type, null);
		}
	}
	
	@SuppressWarnings("serial")
	static class TokenMismatchException extends Exception {
	}
	
	/**
	 * Make a Lexer, which is a mutable object generates a stream of
	 * calculation tokens from a string.
	 * @param input string to convert into calculation tokens
	 */
	public Lexer(String input) {
		this.s = input;
	}
	
	/**
	 * Modifies this object by consuming a token from the input stream.
	 * @return next token on the stream, or
	 * 		   EOF token if there are no more tokens in the stream.
	 */
	public Token next() throws SyntaxErrorException {
		if (i >= s.length()) {
			return new Token(Type.EOF, "");
		} else {
			if (Character.isDigit(s.charAt(i))) {
				// the token starts with a numerical character
				// find where it ends
				
				// Regex matching the next numerical token.
				
				Pattern NUMERICAL_TOKEN_REGEX =
						Pattern.compile("(\\d+(\\.\\d+)?)");
				Matcher numericalMatcher =
						NUMERICAL_TOKEN_REGEX.matcher(s.substring(i));
				if (numericalMatcher.find()) {
					// Get the part of the string that the TOKEN_REGEX matched,
					// and advance the Lexer's state
					String text = numericalMatcher.group();
					i = numericalMatcher.end();
					return new Token(Type.NUMBER, text);
				} else {
					// if we got here, there's a bug in the regex -- Matcher said we matched the 
			        // expression, but it didn't match any of the parens
			        throw new AssertionError("shouldn't get here");
				}
			} else {
				switch (s.charAt(i)) {
				case ' ':
					// white space, consume it
					i = i + 1;
					return next();
				case 'p':
					if ((i < (s.length()-1)) && (s.charAt(i+1) == 't')) {
						i = i + 2;
						return new Token(Type.POINT, "pt");
					} else {
						throw new SyntaxErrorException("syntax error at " + s.substring(i));
					}
				case 'i':
					if ((i < (s.length()-1)) && (s.charAt(i+1) == 'n')) {
						i = i + 2;
						return new Token(Type.POINT, "in");
					} else {
						throw new SyntaxErrorException("syntax error at " + s.substring(i));
					}
				case '+':
					i = i + 1;
					return new Token(Type.PLUS, "+");
				case '-':
					i = i + 1;
					return new Token(Type.MINUS, "-");
				case '*':
					i = i + 1;
					return new Token(Type.MULTIPLY, "*");
				case '/':
					i = i + 1;
					return new Token(Type.DIVIDE, "/");
				case '(':
					i = i + 1;
					return new Token(Type.OPEN_PAREN, "(");
				case ')':
					i = i + 1;
					return new Token(Type.CLOSE_PAREN, ")");
				default:
					// no token found
					throw new SyntaxErrorException("syntax error at " + s.substring(i));
				}
			}
		}
	}
}
