package calculator;

import static org.junit.Assert.*;

import org.junit.Test;

import calculator.Lexer.Token;
import calculator.Lexer.TokenMismatchException;

public class LexerTest {

	@Test
	// tokenize a digit
	public void LexerDigitTest() throws TokenMismatchException {
		Lexer lexer = new Lexer("1");
		Token token = lexer.next();
		
		assertEquals(Type.operand, token.getType());
		
		Lexer lexer1 = new Lexer("22");
		Token token1 = lexer1.next();
		assertEquals(Type.operand, token1.getType());
		assertEquals("22", token1.getValue());
		
		Lexer lexer2 = new Lexer("1.789");
		Token token2 = lexer2.next();
		assertEquals(Type.operand, token2.getType());
		assertEquals("1.789", token2.getValue());
		
		
		Lexer lexer3 = new Lexer("12.789");
		Token token3 = lexer3.next();
		assertEquals(Type.operand, token3.getType());
		assertEquals("12.789", token3.getValue());
	}
	@Test
	// tokenize a symbol
	public void LexerOperatorTest() throws TokenMismatchException{
		Lexer lexer = new Lexer("-");
		Token token = lexer.next();
		
		assertEquals(Type.subtract, token.getType());getClass();
		assertEquals(token.getValue(), "-");
		
		Lexer lexer1 = new Lexer("+");
		Token token1 = lexer1.next();
		
		assertEquals(Type.plus, token1.getType());
		assertEquals(token1.getValue(), "+");
		
		
		Lexer lexer2 = new Lexer("/");
		Token token2 = lexer2.next();
		
		assertEquals(Type.divide, token2.getType());
		assertEquals(token2.getValue(), "/");
		
		
		Lexer lexer3 = new Lexer("*");
		Token token3 = lexer3.next();
		
		assertEquals(Type.multiply, token3.getType());
		assertEquals(token3.getValue(), "*");
	}
	@Test
	// parenthesis tokenization
	public void LexerParenthhesisTets() throws TokenMismatchException{
		Lexer lexer = new Lexer("(");
		Token tokenClose = lexer.next();
		
		assertEquals(Type.openparenthesis, tokenClose.getType());
		assertEquals(tokenClose.getValue(), "(");
		
		
		Lexer lexer1 = new Lexer(")");
		Token tokenStart = lexer1.next();
		
		assertEquals(Type.closeparenthesis, tokenStart.getType());
		assertEquals(tokenStart.getValue(), ")");
	}
	@Test
	public void LexerUnitTest() throws TokenMismatchException{
		Lexer lexer = new Lexer("in");
		Token tokenInches = lexer.next();
		
		assertEquals(Type.inch, tokenInches.getType());
		assertEquals(tokenInches.getValue(),"in" );
		
		
		Lexer lexer1 = new Lexer("pt"); 
		Token tokenPoints = lexer1.next();
		
		assertEquals(Type.point, tokenPoints.getType());
		assertEquals(tokenPoints.getValue(),"pt" );
	}
	@Test
	// tokenization of digits and unit
	public void LexerDigitUnitTest01() throws TokenMismatchException{
		Lexer lexer = new Lexer("6in");
		Token token1 = lexer.next();
		
		assertEquals(Type.operand, token1.getType());
		assertEquals(token1.getValue(),"6" );
		Token token2 = lexer.next();
		
		assertEquals(Type.inch, token2.getType());
		assertEquals(token2.getValue(),"in" );
	}
	@Test
	// tokenization of digits and unit
	public void LexerDigitUnitTest02() throws TokenMismatchException{
		Lexer lexer = new Lexer("22pt");
		Token token1 = lexer.next();
		
		assertEquals(Type.operand, token1.getType());
		assertEquals(token1.getValue(),"22" );
		Token token2 = lexer.next();
		
		assertEquals(Type.point, token2.getType());
		assertEquals(token2.getValue(),"pt" );
	}
	@Test
	// tokenization of digits and unit
		public void LexerDigitUnitTest03() throws TokenMismatchException{
			Lexer lexer = new Lexer("6.321in");
			Token token1 = lexer.next();
			
			assertEquals(Type.operand, token1.getType());
			assertEquals(token1.getValue(),"6.321" );
			Token token2 = lexer.next();
			
			assertEquals(Type.inch, token2.getType());
			assertEquals(token2.getValue(),"in" );
		}
		
	@Test
		// tokenization of digits and unit
		public void LexerDigitUnitTest04() throws TokenMismatchException{
			Lexer lexer = new Lexer("22.21313pt");
			Token token1 = lexer.next();
			
			assertEquals(Type.operand, token1.getType());
			assertEquals(token1.getValue(),"22.21313" );
			Token token2 = lexer.next();
			
			assertEquals(Type.point, token2.getType());
			assertEquals(token2.getValue(),"pt" );
		}
		
		// tokenization an expression
	@Test
		public void LexerExpressionTest1() throws TokenMismatchException{
			Lexer lexer = new Lexer("3+2");
			Token token1 = lexer.next();
			
			assertEquals(Type.operand, token1.getType());
			assertEquals(token1.getValue(),"3" );
			
			Token token2 = lexer.next();
			assertEquals(Type.plus, token2.getType());
			assertEquals(token2.getValue(),"+" );
			
			
			Token token3 = lexer.next();
			assertEquals(Type.operand, token3.getType());
			assertEquals(token3.getValue(),"2" );
			
			Token token4 = lexer.next();
			assertEquals(Type.EOF, token4.getType());
		}
		
		// tokenization an expression 
	@Test
				public void LexerExpressionTest2() throws TokenMismatchException{
					Lexer lexer = new Lexer("3+25.4");
					Token token1 = lexer.next();
					
					assertEquals(Type.operand, token1.getType());
					assertEquals(token1.getValue(),"3" );
					
					Token token2 = lexer.next();
					assertEquals(Type.plus, token2.getType());
					assertEquals(token2.getValue(),"+" );
					
					Token token3 = lexer.next();
					assertEquals(Type.operand, token3.getType());
					assertEquals(token3.getValue(),"25.4" );
					
					Lexer lex = new Lexer("3 + 25.4");
					Token tok = lex.next();
					
					assertEquals(Type.operand, tok.getType());
					assertEquals(tok.getValue(),"3" );
					
					Token tok2 = lex.next();
					assertEquals(tok2.getValue(),"+" );
					assertEquals(Type.plus, tok2.getType());
					
					
					Token tok3 = lex.next();
					assertEquals(Type.operand, tok3.getType());
					assertEquals(tok3.getValue(),"25.4" );
					
					Lexer lexer2 = new Lexer("1-2.4");
					Token tk = lexer2.next();
					Token tk1 = lexer2.next();
					Token tk2 = lexer2.next();
					
					assertEquals(Type.operand, tk.getType());
					assertEquals(tk.getValue(),"1" );
					assertEquals(Type.subtract, tk1.getType());
					assertEquals(tk1.getValue(),"-" );
					assertEquals(Type.operand, tk2.getType());
					assertEquals(tk2.getValue(),"2.4" );
				}
				
		// tokenization an expression 
	@Test
			public void LexerExpressionTest3() throws TokenMismatchException{
					Lexer lexer = new Lexer("3 + 25.4in");
					Token token1 = lexer.next();
					
					assertEquals(Type.operand, token1.getType());
					assertEquals(token1.getValue(),"3" );
					
					Token token2 = lexer.next();
					assertEquals(Type.plus, token2.getType());
					assertEquals(token2.getValue(),"+" );
				
					Token token3 = lexer.next();
					assertEquals(Type.operand, token3.getType());
					assertEquals(token3.getValue(),"25.4" );
					
					Token token4 = lexer.next();
					assertEquals(Type.inch, token4.getType());
					assertEquals(token4.getValue(),"in" );
				}
	@Test
			public void LexerExpressionTest31() throws TokenMismatchException{
				Lexer lexer = new Lexer("3 * 25.4in");
				Token token1 = lexer.next();
				
				assertEquals(Type.operand, token1.getType());
				assertEquals(token1.getValue(),"3" );
				Token token2 = lexer.next();
				assertEquals(Type.multiply, token2.getType());
				assertEquals(token2.getValue(),"*" );
				
				Token token3 = lexer.next();
				assertEquals(Type.operand, token3.getType());
				assertEquals(token3.getValue(),"25.4" );
				
				Token token4 = lexer.next();
				assertEquals(Type.inch, token4.getType());
				assertEquals(token4.getValue(),"in" );
			}
			
			// tokenization an expression 
	@Test
			public void LexerExpressionTest4() throws TokenMismatchException{
					Lexer lexer = new Lexer("3pt + 25.4in");
					Token token1 = lexer.next();
					
					assertEquals(Type.operand, token1.getType());
					assertEquals(token1.getValue(),"3" );
					
					Token token2 = lexer.next();
					assertEquals(Type.point, token2.getType());
					assertEquals(token2.getValue(),"pt" );
					
					Token token3 = lexer.next();
					assertEquals(Type.plus, token3.getType());
					assertEquals(token3.getValue(),"+" );
					
					Token token4 = lexer.next();
					assertEquals(Type.operand, token4.getType());
					assertEquals(token4.getValue(),"25.4" );
					
					Token token5 = lexer.next();
					assertEquals(Type.inch, token5.getType());
					assertEquals(token5.getValue(),"in" );
				}
	@Test
			public void LexerExpressionTest41() throws TokenMismatchException{
				Lexer lexer = new Lexer("3pt * 25.4in");
				Token token1 = lexer.next();
				
				assertEquals(Type.operand, token1.getType());
				assertEquals(token1.getValue(),"3" );
				
				Token token2 = lexer.next();
				assertEquals(Type.point, token2.getType());
				assertEquals(token2.getValue(),"pt" );
				
				Token token3 = lexer.next();
				assertEquals(Type.multiply, token3.getType());
				assertEquals(token3.getValue(),"*" );
				
				Token token4 = lexer.next();
				assertEquals(Type.operand, token4.getType());
				assertEquals(token4.getValue(),"25.4" );
				
				Token token5 = lexer.next();
				assertEquals(Type.inch, token5.getType());
				assertEquals(token5.getValue(),"in" );
			}
	@Test
			public void LexerExpressionTest42() throws TokenMismatchException{
				Lexer lexer = new Lexer("(3pt + 25.4in)");
				
				Token token = lexer.next();
				assertEquals(Type.openparenthesis, token.getType());
				assertEquals(token.getValue(),"(" );
				Token token1 = lexer.next();
				
				assertEquals(Type.operand, token1.getType());
				assertEquals(token1.getValue(),"3" );
				
				Token token2 = lexer.next();
				assertEquals(Type.point, token2.getType());
				assertEquals(token2.getValue(),"pt" );
				
				Token token3 = lexer.next();
				assertEquals(Type.plus, token3.getType());
				assertEquals(token3.getValue(),"+" );
				
				Token token4 = lexer.next();
				assertEquals(Type.operand, token4.getType());
				assertEquals(token4.getValue(),"25.4" );
				
				Token token5 = lexer.next();
				assertEquals(Type.inch, token5.getType());
				assertEquals(token5.getValue(),"in" );
				
				Token token6 = lexer.next();
				assertEquals(Type.closeparenthesis, token6.getType());
				assertEquals(token6.getValue(),")" );
			}
	@Test
			public void LexerExpressionTest43() throws TokenMismatchException{
				Lexer lexer = new Lexer("4in + (3pt + 25.4in)");
				Token pretoken1 = lexer.next();
				assertEquals(Type.operand, pretoken1.getType());
				assertEquals(pretoken1.getValue(),"4" );
				
				Token pretoken2 = lexer.next();
				assertEquals(Type.inch, pretoken2.getType());
				assertEquals(pretoken2.getValue(),"in" );
				
				Token pretoken3 = lexer.next();
				assertEquals(Type.plus, pretoken3.getType());
				assertEquals(pretoken3.getValue(),"+" );
				
				Token token = lexer.next();
				assertEquals(Type.openparenthesis, token.getType());
				assertEquals(token.getValue(),"(" );
				
				Token token1 = lexer.next();
				
				assertEquals(Type.operand, token1.getType());
				assertEquals(token1.getValue(),"3" );
				
				Token token2 = lexer.next();
				assertEquals(Type.point, token2.getType());
				assertEquals(token2.getValue(),"pt" );
				
				Token token3 = lexer.next();
				assertEquals(Type.plus, token3.getType());
				assertEquals(token3.getValue(),"+" );
				
				Token token4 = lexer.next();
				assertEquals(Type.operand, token4.getType());
				assertEquals(token4.getValue(),"25.4" );
				
				Token token5 = lexer.next();
				assertEquals(Type.inch, token5.getType());
				assertEquals(token5.getValue(),"in" );
				
				Token token6 = lexer.next();
				assertEquals(Type.closeparenthesis, token6.getType());
				assertEquals(token6.getValue(),")" );
			}
			
	@Test
			public void LexerExpressionTest44() throws TokenMismatchException{
				Lexer lexer = new Lexer("4in * (3pt + 25.4in)");
				Token pretoken1 = lexer.next();
				assertEquals(Type.operand, pretoken1.getType());
				assertEquals(pretoken1.getValue(),"4" );
				
				Token pretoken2 = lexer.next();
				assertEquals(Type.inch, pretoken2.getType());
				assertEquals(pretoken2.getValue(),"in" );
				
				Token pretoken3 = lexer.next();
				assertEquals(Type.multiply, pretoken3.getType());
				assertEquals(pretoken3.getValue(),"*" );
				
				Token token = lexer.next();
				assertEquals(Type.openparenthesis, token.getType());
				assertEquals(token.getValue(),"(" );
				
				Token token1 = lexer.next();
				
				assertEquals(Type.operand, token1.getType());
				assertEquals(token1.getValue(),"3" );
				
				Token token2 = lexer.next();
				assertEquals(Type.point, token2.getType());
				assertEquals(token2.getValue(),"pt" );
				
				Token token3 = lexer.next();
				assertEquals(Type.plus, token3.getType());
				assertEquals(token3.getValue(),"+" );
				
				Token token4 = lexer.next();
				assertEquals(Type.operand, token4.getType());
				assertEquals(token4.getValue(),"25.4" );
				
				Token token5 = lexer.next();
				assertEquals(Type.inch, token5.getType());
				assertEquals(token5.getValue(),"in" );
				
				Token token6 = lexer.next();
				assertEquals(Type.closeparenthesis, token6.getType());
				assertEquals(token6.getValue(),")" );
			}
	@Test
			public void LexerExpressionTest45() throws TokenMismatchException{
				Lexer lexer = new Lexer("4.3in * ((3.3pt + 25.4in))");
				Token pretoken1 = lexer.next();
				assertEquals(Type.operand, pretoken1.getType());
				assertEquals(pretoken1.getValue(), "4.3");
				
				Token pretoken2 = lexer.next();
				assertEquals(Type.inch, pretoken2.getType());
				assertEquals(pretoken2.getValue(), "in");

				
				Token pretoken3 = lexer.next();
				assertEquals(Type.multiply, pretoken3.getType());
				assertEquals(pretoken3.getValue(), "*");

				Token token = lexer.next();
				assertEquals(Type.openparenthesis, token.getType());
				assertEquals(token.getValue(),"(");
				
				Token token1 = lexer.next();
				
				assertEquals(Type.openparenthesis, token1.getType());
				assertEquals(token1.getValue(),"(");
				
				
				Token token2 = lexer.next();
				assertEquals(Type.operand, token2.getType());
				assertEquals(token2.getValue(),"3.3");
				
				Token token3 = lexer.next();
				assertEquals(Type.point, token3.getType());
				assertEquals(token3.getValue(),"pt");
				
				Token token4 = lexer.next();
				assertEquals(Type.plus, token4.getType());
				assertEquals(token4.getValue(),"+");
				
				Token token5 = lexer.next();
				assertEquals(Type.operand, token5.getType());
				assertEquals(token5.getValue(),"25.4");
				
				Token token6 = lexer.next();
				assertEquals(Type.inch, token6.getType());
				assertEquals(token6.getValue(),"in");
				
				Token token7 = lexer.next();
				assertEquals(Type.closeparenthesis, token7.getType());
				assertEquals(token7.getValue(),")");
				Token token8 = lexer.next();
				assertEquals(Type.closeparenthesis, token8.getType());
				assertEquals(token8.getValue(),")");
			}
			
	@Test
			public void LexerExpressionTest5() throws TokenMismatchException{
				Lexer lexer = new Lexer("(3.2+2.3)*6.32");
				Token token1 = lexer.next();
				
				assertEquals(Type.openparenthesis, token1.getType());
				assertEquals(token1.getValue(),"(");
				
				Token token2 = lexer.next();
				assertEquals(Type.operand, token2.getType());
				assertEquals(token2.getValue(),"3.2");
				
				Token token3 = lexer.next();
				assertEquals(Type.plus, token3.getType());
				assertEquals(token3.getValue(),"+");
				
				Token token4 = lexer.next();
				assertEquals(Type.operand, token4.getType());
				assertEquals(token4.getValue(),"2.3");
				
				Token token5 = lexer.next();
				assertEquals(Type.closeparenthesis, token5.getType());
				assertEquals(token5.getValue(),")");
				
				Token token6 = lexer.next();
				assertEquals(Type.multiply, token6.getType());
				assertEquals(token6.getValue(),"*");
				
				Token token7 = lexer.next();
				assertEquals(Type.operand, token7.getType());
				assertEquals(token7.getValue(),"6.32");
			}
			
	@Test
			
			public void LexerExpressionTest6() throws TokenMismatchException{
				Lexer lexer = new Lexer("(3.2+2.3)in");
				Token token1 = lexer.next();
				
				assertEquals(Type.openparenthesis, token1.getType());
				assertEquals(token1.getValue(),"(");
				
				Token token2 = lexer.next();
				assertEquals(Type.operand, token2.getType());
				assertEquals(token2.getValue(),"3.2");
				
				Token token3 = lexer.next();
				assertEquals(Type.plus, token3.getType());
				assertEquals(token3.getValue(),"+");
				
				Token token4 = lexer.next();
				assertEquals(Type.operand, token4.getType());
				assertEquals(token4.getValue(),"2.3");
				
				Token token5 = lexer.next();
				assertEquals(Type.closeparenthesis, token5.getType());
				assertEquals(token5.getValue(),")");
				
				Token token6 = lexer.next();
				assertEquals(Type.inch, token6.getType());
				assertEquals(token6.getValue(),"in");
				
			}
			
	@Test
			public void LexerExpressionTest7() throws TokenMismatchException{
				Lexer lexer = new Lexer("(3.2pt+2.3)in");
				Token token1 = lexer.next();
				
				assertEquals(Type.openparenthesis, token1.getType());
				assertEquals(token1.getValue(),"(");
				Token token2 = lexer.next();
				assertEquals(Type.operand, token2.getType());
				assertEquals(token2.getValue(),"3.2");
				
				Token token3 = lexer.next();
				assertEquals(Type.point, token3.getType());
				assertEquals(token3.getValue(),"pt");
				
				Token token4 = lexer.next();
				assertEquals(Type.plus, token4.getType());
				assertEquals(token4.getValue(),"+");
				
				Token token5 = lexer.next();
				assertEquals(Type.operand, token5.getType());
				assertEquals(token5.getValue(),"2.3");
				
				Token token6 = lexer.next();
				assertEquals(Type.closeparenthesis, token6.getType());
				assertEquals(token6.getValue(),")");
				
				Token token7 = lexer.next();
				assertEquals(Type.inch, token7.getType());
				assertEquals(token7.getValue(),"in");
			}
			
	@Test
			public void LexerExpressionTest8() throws TokenMismatchException{
				Lexer lexer = new Lexer("(3.2pt+2.3213in)in");
				Token token1 = lexer.next();
				
				assertEquals(Type.openparenthesis, token1.getType());
				assertEquals(token1.getValue(),"(");
				
				Token token2 = lexer.next();
				assertEquals(Type.operand, token2.getType());
				assertEquals(token2.getValue(),"3.2");
				
				Token token3 = lexer.next();
				assertEquals(Type.point, token3.getType());
				assertEquals(token3.getValue(),"pt");
				
				Token token4 = lexer.next();
				assertEquals(Type.plus, token4.getType());
				assertEquals(token4.getValue(),"+");
				
				Token token5 = lexer.next();
				assertEquals(Type.operand, token5.getType());
				assertEquals(token5.getValue(),"2.3213");
				
				Token token6 = lexer.next();
				assertEquals(Type.inch, token6.getType());
				assertEquals(token6.getValue(),"in");
				
				Token token7 = lexer.next();
				assertEquals(Type.closeparenthesis, token7.getType());
				assertEquals(token7.getValue(),")");
				
				Token token8 = lexer.next();
				assertEquals(Type.inch, token8.getType());
				assertEquals(token8.getValue(),"in");
			}
}
