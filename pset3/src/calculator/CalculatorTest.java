package calculator;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import calculator.Lexer.TokenMismatchException;

public class CalculatorTest {

	// TODO write tests for MultiUnitCalculator.evaluate

	boolean approxEquals(String expr1, String expr2, boolean compareUnits) {
		return new Value(expr1).approxEquals(new Value(expr2), compareUnits);
	}

	static class Value {
		static float delta = 0.001f;
 
		enum Unit {
			POINT, INCH, SCALAR
		}

		Unit unit;
		// in points if a length
		float value;

		Value(String value) {
			value = value.trim();
			if (value.endsWith("pt")) {
				unit = Unit.POINT;
				this.value = Float.parseFloat(value.substring(0,
						value.length() - 2).trim());
			} else if (value.endsWith("in")) {
				unit = Unit.INCH;
				this.value = 72 * Float.parseFloat(value.substring(0,
						value.length() - 2).trim());
			} else {
				unit = Unit.SCALAR;
				this.value = Float.parseFloat(value);
			}
		}

		boolean approxEquals(Value that, boolean compareUnits) {
			return (this.unit == that.unit || !compareUnits)
					&& Math.abs(this.value - that.value) < delta;
		}
	}
	
	@Test
	// scalar test
	public void evaulateTest01() throws SyntaxErrorException{
		MultiUnitCalculator calculator;
		try {
			calculator = new MultiUnitCalculator();
			String expr1 = calculator.evaluate("5 + 3");
			String expr2 = "8.0";
			
			approxEquals(expr1, expr2, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	// inches/scalar = inches  test
	public void inchesScalarTest01() throws SyntaxErrorException, TokenMismatchException{
		MultiUnitCalculator calculator = new MultiUnitCalculator();
		String expr1 = calculator.evaluate("(5 + 3)in/4");
		String expr2 = "8.0in";
		
		approxEquals(expr1, expr2, true);
	}
	
	@Test
	//inches*scalar = inches
	
	public void inchesScalarTest02() throws SyntaxErrorException, TokenMismatchException{
		MultiUnitCalculator calculator = new MultiUnitCalculator();
		String expr1 = calculator.evaluate("3in*2");
		String expr2 = "6.0 in";
	
		approxEquals(expr1, expr2, true);
	}
	
	
	@Test
	//scalar/inches = inches
	public void inchesScalarTest03() throws SyntaxErrorException, TokenMismatchException{
		MultiUnitCalculator calculator = new MultiUnitCalculator();
		String expr1 = calculator.evaluate("4/2in");
		String expr2 = "2.0in";
	
		approxEquals(expr1, expr2, true);
	}
	
	@Test
	//inches/inches = scalar
	public void inchesInchesTest01() throws SyntaxErrorException, TokenMismatchException{
		MultiUnitCalculator calculator = new MultiUnitCalculator();
		String expr1 = calculator.evaluate("3in*2/6in");
		String expr2 = "1.0";
	
		approxEquals(expr1, expr2, true);
	}
	
	@Test
	//inches*inches = inches
	public void inchesInchesTest02() throws SyntaxErrorException, TokenMismatchException{
		MultiUnitCalculator calculator = new MultiUnitCalculator();
		String expr1 = calculator.evaluate("3in*3in");
		String expr2 = "9in";
	
		approxEquals(expr1, expr2, true);
	}
	
	
	@Test
	// inches/points = scalar
	public void inchesPointsTest01() throws SyntaxErrorException, TokenMismatchException{
		MultiUnitCalculator calculator = new MultiUnitCalculator();
		String expr1 = calculator.evaluate("1in/72pt");
		String expr2 = "1";
	
		approxEquals(expr1, expr2, true);
	}
	
	@Test
	//inches+points = inches
	public void inchesPointsTest02() throws SyntaxErrorException, TokenMismatchException{
		MultiUnitCalculator calculator = new MultiUnitCalculator();
		String expr1 = calculator.evaluate("1in+72pt");
		String expr2 = "2.0in";
	
		approxEquals(expr1, expr2, true);
	}
	
	
	@Test
	//scalar+inches = inches
	public void inchesPointsTest03() throws SyntaxErrorException, TokenMismatchException{
		MultiUnitCalculator calculator = new MultiUnitCalculator();
		String expr1 = calculator.evaluate("3in+2.4");
		String expr2 = "5.4in";
	
		approxEquals(expr1, expr2, true);
	}
	@Test
	public void MultiUnitCalculatorTest() throws SyntaxErrorException, TokenMismatchException {
		MultiUnitCalculator cal = new MultiUnitCalculator();
		
		//44.5 + 12.3 = 56.8
		String expected = "56.8";
		String result = cal.evaluate("44.5 + 12.3");
		
		assertEquals(expected, result);
		
		//44.5 - 11.9 = 32.6
		expected = "32.6";
		result = cal.evaluate("44.5 - 11.9");
		
		assertEquals(expected, result);
		
		//12.4 * 45.6 = 565.44
		expected = "565.44";
		result = cal.evaluate("12.4 * 45.6");
		
		assertEquals(expected, result);
		
		//34 / 2 = 17.0
		expected = "17.0";
		result = cal.evaluate("34 / 2");
		
		assertEquals(expected, result);
		
	}
	
	
	
}
