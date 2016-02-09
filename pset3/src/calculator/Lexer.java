package calculator;



import calculator.Type;
import java.util.regex.Pattern;


import java.util.regex.Matcher;

/**
 * Calculator lexical analyzer.
 */
public class Lexer {
	
	private String input;
	private int i;
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
		/**
		 * @return return type, an enum, of a token 
		 */
		public Type getType(){
			return type;
		}
		
		/**
		 * 
		 * @return value of a token
		 */
		public String getValue(){
			return text;
		}
	}

	@SuppressWarnings("serial")
	static class TokenMismatchException extends Exception {
		public TokenMismatchException(String message) {
			super(message);
		}
	}

	// TODO write method spec
	/**
	 * generates lexer, an object, from a stream of characters and symbols
	 * @param input, a nonempty string. 
	 */
	public Lexer(String input) {
		// TODO implement for Problem 2
		this.input = input;
		this.i = 0;
	}
	
	
	/**
	 * Modifies this object by consuming a token 
     * from the input stream.
     * @return next token on the stream, or EOF token
     *   if there are no more tokens in the stream.
	 */
	public Token next() throws TokenMismatchException{
		if(i >= input.length()){
			return new Token(Type.EOF);
		}else{
			  if(Character.isDigit(input.charAt(i))){
			    	 
			    	 String regex = "\\d+(\\.\\d+)?";
			    	 Pattern pattern = Pattern.compile(regex);
			    	 Matcher matcher = pattern.matcher(input.substring(i));
			    	 
			    	 if(matcher.find()){
			    		 
			    		 String value = matcher.group();
			    		
			    		 i += value.length();
			    		
				    	 return new Token(Type.operand, value);
			    	 }else{
			    		throw new TokenMismatchException("something's wrong");
			    	 }
			     }else{
			    		switch (input.charAt(i)) {
						case ' ':
							i++;
							return next();
						case '+':
							i++;
							return new Token(Type.plus, "+");
						case'-':
							i++;
							return new Token(Type.subtract,"-");
						case'/':
							i++;
							return new Token(Type.divide,"/");
						case'*':
							i++;
							return new Token(Type.multiply,"*");
						case'(':
							i++;
							return new Token(Type.openparenthesis,"(");
						case')':
							i++;
							return new Token(Type.closeparenthesis,")");
						case'i':
							if(input.charAt(i+1) =='n'){
								i+=2;
								return new Token(Type.inch,"in");
							}else{
								throw new TokenMismatchException("syntax error at " + input.substring(i));
							}
						case'p':
							if(this.input.charAt(i+1)=='t'){
								i+=2;
								return new Token(Type.point,"pt");	
							}else{
								throw new TokenMismatchException("syntax error at " + input.substring(i));
							}
						default:
							
								throw new TokenMismatchException("syntax error at " + input.substring(i));	
							
						}
			     }
		}
	   
		
	}
}
