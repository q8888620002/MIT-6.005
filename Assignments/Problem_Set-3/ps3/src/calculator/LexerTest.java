package calculator;

import static org.junit.Assert.*;

import org.junit.Test;

import calculator.Lexer.Token;

public class LexerTest  {

	@Test
	public void testNumber001() throws SyntaxErrorException {
		Lexer lex = new Lexer("102");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "102");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	
	@Test
	public void testNumber002() throws SyntaxErrorException {
		Lexer lex = new Lexer("1.732");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "1.732");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	
	@Test
	public void testInch001() throws SyntaxErrorException {
		Lexer lex = new Lexer("6in");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "6");
		
		tok = lex.next();
		assertEquals(tok.type, Type.INCH);
		assertEquals(tok.text, "in");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	
	@Test
	public void testInch002() throws SyntaxErrorException {
		Lexer lex = new Lexer("3.14in");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "3.14");
		
		tok = lex.next();
		assertEquals(tok.type, Type.INCH);
		assertEquals(tok.text, "in");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	
	@Test
	public void testPoint001() throws SyntaxErrorException {
		Lexer lex = new Lexer("48pt");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "48");
		
		tok = lex.next();
		assertEquals(tok.type, Type.POINT);
		assertEquals(tok.text, "pt");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	
	@Test
	public void testPoint002() throws SyntaxErrorException {
		Lexer lex = new Lexer("4.96pt");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "4.96");
		
		tok = lex.next();
		assertEquals(tok.type, Type.POINT);
		assertEquals(tok.text, "pt");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	
	@Test
	public void testSum001() throws SyntaxErrorException {
		Lexer lex = new Lexer("7.12+286pt");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "7.12");
		
		tok = lex.next();
		assertEquals(tok.type, Type.PLUS);
		assertEquals(tok.text, "+");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "286");
		
		tok = lex.next();
		assertEquals(tok.type, Type.POINT);
		assertEquals(tok.text, "pt");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	
	@Test
	public void testSum002() throws SyntaxErrorException {
		Lexer lex = new Lexer("8.33in + 915");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "8.33");
		
		tok = lex.next();
		assertEquals(tok.type, Type.INCH);
		assertEquals(tok.text, "in");
		
		tok = lex.next();
		assertEquals(tok.type, Type.PLUS);
		assertEquals(tok.text, "+");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "915");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	
	@Test
	public void testSubtraction001() throws SyntaxErrorException {
		Lexer lex = new Lexer("478.66-368pt");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "478.66");
		
		tok = lex.next();
		assertEquals(tok.type, Type.MINUS);
		assertEquals(tok.text, "-");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "368");
		
		tok = lex.next();
		assertEquals(tok.type, Type.POINT);
		assertEquals(tok.text, "pt");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	
	@Test
	public void testSubtraction002() throws SyntaxErrorException {
		Lexer lex = new Lexer("2.33in - 165");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "2.33");
		
		tok = lex.next();
		assertEquals(tok.type, Type.INCH);
		assertEquals(tok.text, "in");
		
		tok = lex.next();
		assertEquals(tok.type, Type.MINUS);
		assertEquals(tok.text, "-");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "165");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	

	@Test
	public void testMultiplication001() throws SyntaxErrorException {
		Lexer lex = new Lexer("256.88*602pt");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "256.88");
		
		tok = lex.next();
		assertEquals(tok.type, Type.MULTIPLY);
		assertEquals(tok.text, "*");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "602");
		
		tok = lex.next();
		assertEquals(tok.type, Type.POINT);
		assertEquals(tok.text, "pt");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	
	@Test
	public void testMultiplication002() throws SyntaxErrorException {
		Lexer lex = new Lexer("5.24in * 255");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "5.24");
		
		tok = lex.next();
		assertEquals(tok.type, Type.INCH);
		assertEquals(tok.text, "in");
		
		tok = lex.next();
		assertEquals(tok.type, Type.MULTIPLY);
		assertEquals(tok.text, "*");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "255");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	
	@Test
	public void testDivision001() throws SyntaxErrorException {
		Lexer lex = new Lexer("667.20pt/105.6");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "667.20");

		tok = lex.next();
		assertEquals(tok.type, Type.POINT);
		assertEquals(tok.text, "pt");
		
		tok = lex.next();
		assertEquals(tok.type, Type.DIVIDE);
		assertEquals(tok.text, "/");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "105.6");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	
	@Test
	public void testDivision002() throws SyntaxErrorException {
		Lexer lex = new Lexer("388/102.6in");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "388");
		
		tok = lex.next();
		assertEquals(tok.type, Type.DIVIDE);
		assertEquals(tok.text, "/");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "102.6");
		
		tok = lex.next();
		assertEquals(tok.type, Type.INCH);
		assertEquals(tok.text, "in");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	
	@Test
	public void testParentheses001() throws SyntaxErrorException {
		Lexer lex = new Lexer("4.14pt+(3in*2.4)");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "4.14");
		
		tok = lex.next();
		assertEquals(tok.type, Type.POINT);
		assertEquals(tok.text, "pt");
		
		tok = lex.next();
		assertEquals(tok.type, Type.PLUS);
		assertEquals(tok.text, "+");
		
		tok = lex.next();
		assertEquals(tok.type, Type.OPEN_PAREN);
		assertEquals(tok.text, "(");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "3");
		
		tok = lex.next();
		assertEquals(tok.type, Type.INCH);
		assertEquals(tok.text, "in");
		
		tok = lex.next();
		assertEquals(tok.type, Type.MULTIPLY);
		assertEquals(tok.text, "*");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "2.4");
		
		tok = lex.next();
		assertEquals(tok.type, Type.CLOSE_PAREN);
		assertEquals(tok.text, ")");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	
	@Test
	public void testParentheses002() throws SyntaxErrorException {
		Lexer lex = new Lexer("(((8.88pt*2.99)))-100");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.OPEN_PAREN);
		assertEquals(tok.text, "(");
		
		tok = lex.next();
		assertEquals(tok.type, Type.OPEN_PAREN);
		assertEquals(tok.text, "(");
		
		tok = lex.next();
		assertEquals(tok.type, Type.OPEN_PAREN);
		assertEquals(tok.text, "(");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "8.88");

		tok = lex.next();
		assertEquals(tok.type, Type.POINT);
		assertEquals(tok.text, "pt");
		
		tok = lex.next();
		assertEquals(tok.type, Type.MULTIPLY);
		assertEquals(tok.text, "*");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "2.99");
		
		tok = lex.next();
		assertEquals(tok.type, Type.CLOSE_PAREN);
		assertEquals(tok.text, ")");
		
		tok = lex.next();
		assertEquals(tok.type, Type.CLOSE_PAREN);
		assertEquals(tok.text, ")");
		
		tok = lex.next();
		assertEquals(tok.type, Type.CLOSE_PAREN);
		assertEquals(tok.text, ")");
		
		tok = lex.next();
		assertEquals(tok.type, Type.MINUS);
		assertEquals(tok.text, "-");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "100");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	
	@Test
	public void testConversion001() throws SyntaxErrorException {
		Lexer lex = new Lexer("(3 + 2.4) in");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.OPEN_PAREN);
		assertEquals(tok.text, "(");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "3");
		
		tok = lex.next();
		assertEquals(tok.type, Type.PLUS);
		assertEquals(tok.text, "+");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "2.4");
		
		tok = lex.next();
		assertEquals(tok.type, Type.CLOSE_PAREN);
		assertEquals(tok.text, ")");
		
		tok = lex.next();
		assertEquals(tok.type, Type.INCH);
		assertEquals(tok.text, "in");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
	
	@Test
	public void testConversion002() throws SyntaxErrorException {
		Lexer lex = new Lexer("(8.16in + (3.33pt * 4.64)) pt");
		Token tok;
		
		tok = lex.next();
		assertEquals(tok.type, Type.OPEN_PAREN);
		assertEquals(tok.text, "(");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "8.16");
		
		tok = lex.next();
		assertEquals(tok.type, Type.INCH);
		assertEquals(tok.text, "in");
		
		tok = lex.next();
		assertEquals(tok.type, Type.PLUS);
		assertEquals(tok.text, "+");
		
		tok = lex.next();
		assertEquals(tok.type, Type.OPEN_PAREN);
		assertEquals(tok.text, "(");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "3.33");
		
		tok = lex.next();
		assertEquals(tok.type, Type.POINT);
		assertEquals(tok.text, "pt");
		
		tok = lex.next();
		assertEquals(tok.type, Type.MULTIPLY);
		assertEquals(tok.text, "*");
		
		tok = lex.next();
		assertEquals(tok.type, Type.NUMBER);
		assertEquals(tok.text, "4.64");
		
		tok = lex.next();
		assertEquals(tok.type, Type.CLOSE_PAREN);
		assertEquals(tok.text, ")");
		
		tok = lex.next();
		assertEquals(tok.type, Type.CLOSE_PAREN);
		assertEquals(tok.text, ")");
		
		tok = lex.next();
		assertEquals(tok.type, Type.POINT);
		assertEquals(tok.text, "pt");
		
		tok = lex.next();
        assertEquals(tok.type, Type.EOF);
	}
}
