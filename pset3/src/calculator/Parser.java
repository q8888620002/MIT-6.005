package calculator;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Stack;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Operators;

import calculator.Lexer;
import calculator.Lexer.Token;
import calculator.Lexer.TokenMismatchException;


/*
 * TODO define your grammar from problem 1 here
 * 
 * Expression ::= operand | combination
 * Combination ::= Calculation | ExpressionWithUnit | Expression with parenthesis
 * Calculation ::= Expression Operator Expression
 * ExpressionWithUnit ::= Expression unit
 * Expression with parenthesis ::= ("Expression")
 * operand ::= [\d+]
 * unit ::= in | pt
 * Operator ::= + | - | * | /
 * 
 * 
 *  Expression ::= (Primitive | Combination) EOF
 * 	Primitive ::= Number (Unit | )
 * 	Number ::= [0-9]+((.[0-9]+) | )
 * 	Combination ::= Arithmetic
 * 				  | Unit_Conversion
 * 				  | Left_paren Expression Right_paren
 * 	Arithmetic ::= Expression Operator Expression
 * 	Operator ::= '+' | '-' | '*' | '/'
 * 	Unit_Conversion ::= Left_paren Expression Right_paren Unit	
 *  Left_paren ::= '('
 *  Right_paren ::= ')'
 *  Unit ::= 'in' | 'pt'
 *  
 */

/**
 * Calculator parser. All values are measured in pt.
 */
class Parser {
	
	private static ArrayList<Token> tokens = new ArrayList<Token>(); 
	private int i;
	private int parenLevel ;
	
	@SuppressWarnings("serial")
	static class ParserException extends RuntimeException {
	}

	/**
	 * Type of values.
	 */
	public enum ValueType {
		POINTS, INCHES, SCALAR
	};

	/**
	 * Internal value is always in points.
	 */
	public  class Value {
		final double value;
		final ValueType type;
	    Type tokenType;
	    
	    DecimalFormat df = new DecimalFormat("##.0");
	    
		Value(double value, ValueType type, Type tokenType) {
			this.value = value;
			this.type = type;
			this.tokenType = tokenType;
		}
		
		Value(double value, ValueType type){
			this.value = value;
			this.type = type;
		}

		@Override
		public String toString() {
			switch (type) {
			// points to inches 
			case INCHES:
				return Double.parseDouble(df.format(value / PT_PER_IN)) + " in";
			case POINTS:
				return value + " pt";
			default:
				return "" + value;
			}
		}
	}

	private static final double PT_PER_IN = 72;
	private final Lexer lexer;

	
	/**
	 * interpreting a stream of tokens such as operators, operands, parenthesis,or units 
	 * from the a Lexer object, 
	 * knowing the relationships or sequence between tokens
	 * @param  lexer, an mutable object containing Token objects
	 * @throws TokenMismatchException 
	 */
    public Parser(Lexer lexer) throws TokenMismatchException {
    	// TODO implement for Problem 3
    	tokens.clear();
    	this.lexer = lexer;
    	this.i = 0;
    	this.parenLevel = 0;
    	Token tok1 = lexer.next();
    	
    	while(tok1.getType() != Type.EOF){
    		tokens.add(tok1);
    		tok1 = lexer.next();
    	}
	}
    
    
    
    
   private Value calculate(Stack<Token> s, ArrayList<String> a) throws TokenMismatchException{
	   int index = 0;
	   
	   ValueType type = ValueType.SCALAR;
	   Type tokenType = Type.operand;
	   int leftIndex = a.size()-2;
	   int rightIndex = a.size() -1;
	   
	   
	   double leftOperand =  Double.parseDouble(a.get(leftIndex));
	   double rightOperand =  Double.parseDouble(a.get(rightIndex));
	   double result = rightOperand; 
	   boolean operationChecker = false;
	   
	   
	   
	   Token top = s.pop();
	  
	   while(top != null){
		   Type operation = top.getType(); 
		   int sizeOfStack = s.size();
		   
		   if(operationChecker){
			   rightOperand = result;
			   leftIndex--;
			   if(leftIndex < 0){
				   leftIndex = 0;
			   }
			   
			   leftOperand = Double.parseDouble(a.get(leftIndex));
			   operationChecker = false;
		   }
		   
		   
		   switch(operation){
	       case plus:
	    	   if(a.size() == 1){
	    		   throw new TokenMismatchException("do not need to support"+a.get(index)+"\\+" );
	    	   }else{
	    		   int operatorsIndex = sizeOfStack-1;
	    		   if((operatorsIndex < 0)){
	    			   
	    			   if(type == ValueType.INCHES){
	    				   leftOperand = leftOperand * PT_PER_IN;
	    			   }
	    			   
	    		   }else{
	    			  
	    			   type =  unitConvert( s, type);
	    			  
	    		   }
	    		   
	    		   result = leftOperand + rightOperand;
	    		   operationChecker = true;
	    	   }
	    	   break;
	       case multiply:
	    	   if(a.size() == 1){
	    		throw new TokenMismatchException("do not support");   
	    	   }else{
	    		   result =  leftOperand * rightOperand;
	    		   operationChecker = true;
	    	   }
	    	   break;
	       case subtract:
	    	   if(a.size() == 1){
		    		throw new TokenMismatchException("do not support");   
		    	   }else{
		    		   if((sizeOfStack-1 < 0)){
		    			   if(type == ValueType.INCHES){
		    				   leftOperand = leftOperand * PT_PER_IN;
		    			   }
		    		   }else{
		    			 type =  unitConvert( s, type);
		    		   }
		    		   result =  leftOperand - rightOperand;
		    		   operationChecker = true;
		    	   }
	    	   break;
	       case divide:
	    	   if(a.size() == 1){
		    		throw new TokenMismatchException("do not support");   
		    	   }else{
		    		   if(sizeOfStack-1 > 0){
		    			   if((type == ValueType.POINTS) || (type == ValueType.INCHES)){
			    			   type = ValueType.SCALAR;
			    		   }
		    		   }
		    		   result =  leftOperand / rightOperand;
		    		   operationChecker = true;
		    	   }
	    	   break;
	       case inch:
	    	   
	    	   if(a.size() == 1){
	    		   return new Value(leftOperand*PT_PER_IN, type, Type.inch);
	    	   }else{
	    		   if(parenLevel == 0 ){
	    			   type = ValueType.INCHES;
		    		   tokenType = Type.inch;  
	    		  }else{
	    			  if(type != ValueType.SCALAR){
	    				  
	    				  break;
	    			  }else{
	    				  type = ValueType.INCHES;
			    		   tokenType = Type.inch; 
	    			  }
	    		   }
	    	   }
	    	   break;
	       case point:
	    	   if(a.size() == 1){
	    		   return new Value(leftOperand, type, Type.point);
	    	   }else{
	    		  
	    		  if(parenLevel == 0 ){
	    			   type = ValueType.POINTS;
		    		   tokenType = Type.point; 
		    		  
	    		  }else{
	    			  if(type != ValueType.SCALAR){  
	    				  break;
	    			  }else{
	    				  type = ValueType.POINTS;
			    		   tokenType = Type.point; 
	    			  }
	    		  } 
	    	   }
	    	   break;
	       case openparenthesis:
	    	   if(parenLevel > 0){
	    		   parenLevel--;

	    	   }else{
	    		   parenLevel = 0;
	    	   }
	    	   
	    	   break;
	       case closeparenthesis:
	    	   parenLevel++;
		   default:
			   break;
	     }
		   if(s.empty() ){
			   
//			   result = unit1 rightOperand operation unit2  leftOperand;
			   return new Value(result, type, tokenType);
		   }
		   top = s.pop(); 
		   
	   }
	return new Value(rightOperand, type, tokenType);
   }
    
   /**
    * convert unit after plus and subtract
    * @param stack, tokens; v, value type before plus and subtract
    * @return value type 
    */
   
    private ValueType unitConvert(Stack<Token> stack, ValueType v){
    	   int operatorsIndex = stack.size()-1;
			Type  operation = stack.get(operatorsIndex).getType();
			  
			switch (operation) {
			case closeparenthesis:
				 stack.pop();
				return unitConvert( stack ,v);
			case inch:
				return ValueType.INCHES;
			case point:
				return ValueType.POINTS;
			case multiply:
				
			default:
				break;
			}
			return v;
    }
    
    /**
     * evaluate the result and value type of expression, POINTS, INCHES, or SCALAR 
     * @return the value, a non-empty string, of an expression after calculation
     * @throws TokenMismatchException 
     */
	public Value evaluate() throws TokenMismatchException{
		// TODO implement for Problem 3
		
		Stack<Token> stack = new Stack<Token>();
		ArrayList<String> postFix = new ArrayList<String>(); 
		Value result; 
	
		
		Token t = tokens.get(i);
		
		while(t.getType() != Type.EOF){
			Type tokenType = t.getType();
			
			String text = t.getValue();
			
			switch (tokenType) {
			case operand:
				postFix.add(text);

				i++;
				break;
			case plus:
				stack.push(t);
				i++;
				break;
			case subtract:
				stack.push(t);
				i++;
				break;
			case multiply:
				stack.push(t);
				i++;
				break;
			case divide:
				stack.push(t);
				i++;
				break;
			case inch:
				stack.push(t);
				int lastEle = postFix.size()-1;
				Double newValue = Double.parseDouble(postFix.get(lastEle))*72;
				postFix.remove(lastEle);
				postFix.add(String.valueOf(newValue));
				i++;
				break;
			case point:
				stack.push(t);
				i++;
				
				break;
			case openparenthesis:
				stack.push(t);
				i++;
				break;
			case closeparenthesis:
				
				parenLevel++;
				
				result = calculate(stack, postFix);
				postFix.add(String.valueOf(result.value));
				if((result.type == ValueType.INCHES) || ( result.type == ValueType.POINTS )){
					
					if(result.type == ValueType.INCHES){
						stack.push(new Token(Type.inch));
				
					}else{
						stack.push(new Token(Type.point));
					}
				}
				stack.push(t);
				i++;
				
				break;
			default:
				
				break;
			}
			

			if(i >= tokens.size()){

				result = calculate(stack, postFix);
				return result;
			}

				t = tokens.get(i);
		}
		return calculate(stack, postFix);
	}
}
