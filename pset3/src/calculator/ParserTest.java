package calculator;

import static org.junit.Assert.*;

import org.junit.Test;

import calculator.Lexer.TokenMismatchException;
import calculator.Parser.Value;
import calculator.Parser.ValueType;



public class ParserTest {

	public void parserOperatorTest01() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("2+3"));
		
		Value actuals = par.evaluate();
		
		assertEquals(ValueType.SCALAR, actuals.type);
		assertEquals("5", actuals.toString());
	}
	
	@Test
	public void parserTest01() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("3 + 4"));
		Value actual = par.evaluate();
		
		
		assertEquals(ValueType.SCALAR, actual.type);
		assertEquals("7.0", actual.toString());
	}
	
	@Test
	public void parserTest02() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("3in+72pt"));
		Value result =  par.evaluate();
		
		assertEquals(ValueType.INCHES, result.type);
		assertEquals("4.0 in", result.toString());
		
	}
	
	@Test
	public void parserTest03() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("3 - 4.5"));
		Value actual = par.evaluate();
		
		assertEquals(ValueType.SCALAR, actual.type);
		assertEquals("-1.5", actual.toString());
	}
	
	@Test
	public void parserTest04() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("3pt - 4.5"));
		Value actual = par.evaluate();
			
		assertEquals("-1.5 pt", actual.toString());
	}
	
	@Test
	public void parserTest05() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("(3+4)*5"));
		Value actual = par.evaluate();
			
		assertEquals(ValueType.SCALAR, actual.type);
		assertEquals("35.0", actual.toString());
	}
	
	
	@Test
	public void parserTest06() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("3pt * 4.5"));
		Value actual = par.evaluate();
			
		assertEquals("13.5 pt", actual.toString());
	}
	
	@Test
	public void parserTest07() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("(3in * 4.5)+72pt"));
		Value actual = par.evaluate();
			
		assertEquals("14.5 in", actual.toString());
	}
	
	@Test
	public void parenthesisTest01() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("4pt+(3in*2.4)"));
		Value actual = par.evaluate();
			
		assertEquals("522.4 pt", actual.toString());
	}
	
	@Test
	public void parenthesisTest02() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("4pt+((3in*2.4))"));
		Value actual = par.evaluate();
			
		assertEquals("522.4 pt", actual.toString());
	}
	
	@Test
	public void unitConvertionTest01() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("(3+2.4)in"));
		Value actual = par.evaluate();
			
		assertEquals("5.4 in", actual.toString());
	}
	
	@Test
	public void unitConvertionTest02() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("(3in * 2.4)pt"));
		Value actual = par.evaluate();
			
		assertEquals("518.4 pt", actual.toString());
	}
	
	
	
	@Test
	//negative number in input
	public void NegNumnerTest01() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("-2+(3+4)"));
		Value actual = par.evaluate();
		
			
		assertEquals(TokenMismatchException.class, actual);
	}
	
	@Test
	//negative number in input
	public void NegNumberTest02() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("3+-5"));
		Value actual = par.evaluate();
			
		assertEquals(TokenMismatchException.class, actual);
	}
	
	@Test
	//order still not explicit
	public void orderNotExplicit01() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("2+(3+4)+2"));
		Value actual = par.evaluate();
			
		assertEquals(TokenMismatchException.class, actual);
	}
	@Test
	//order not explicit
	public void orderNotExplicit02() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("2+3in pt"));
		Value actual = par.evaluate();
			
		assertEquals(TokenMismatchException.class, actual);
	}
	
	@Test
	//order of operations not made explicit
	public void orderNotExplicit03() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("3+2+1"));
		Value actual = par.evaluate();
			
		assertEquals(TokenMismatchException.class, actual);
	}
	
	@Test
	//do not support ^
	public void syntaxErrorTest() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("(3+4)^2"));
		Value actual = par.evaluate();
			
		assertEquals(SyntaxErrorException.class, actual);
	}
	
	@Test
	//extraneous parens
	public void extraneousParens() throws  TokenMismatchException{
		Parser par = new Parser(new Lexer("2()+3in"));
		Value actual = par.evaluate();
			
		assertEquals(TokenMismatchException.class, actual);
	}
	
	@Test
	public void caseTestTwo() throws TokenMismatchException, SyntaxErrorException{
		//3 + 2.4in = 5.4in
		
		/*Parser p1 = new Parser(new Lexer("3in * 2.4"));
		Value value1 = p1.evaluate();
		assertEquals(value1.toString(), "7.2 in");
		
		Parser p2 = new Parser(new Lexer("3pt * 2.4in"));
		Value value2 = p2.evaluate();
		assertEquals(value2.toString(), "518.4 pt");
		
		*/
		Parser p = new Parser(new Lexer("3 + 2.4in"));
		Value value;
		value = p.evaluate();
		
		assertEquals(value.toString(), "5.4 in");
		
		//3pt * 2.4in = 518.4pt
		
		
		//3in * 2.4 = 7.2in
		
	}
	
	@Test
	public void caseTestThree() throws TokenMismatchException, SyntaxErrorException{
		
		Value value;
		//(3 + 2.4) in = 5.4in
		Parser p = new Parser(new Lexer("(3 + 2.4) in"));
		value = p.evaluate();
		assertEquals(value.toString(), "5.4 in");
		
		//(3in * 2.4) pt = 518.4pt
		Parser p2 = new Parser(new Lexer("(3in * 2.4) pt"));
		value = p2.evaluate();
		assertEquals(value.type, ValueType.POINTS);
		assertEquals(value.value+"", "518.4");
	}
}
