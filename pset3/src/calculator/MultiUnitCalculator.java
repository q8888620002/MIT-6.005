package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import calculator.CalculatorTest.Value;
import calculator.Lexer.TokenMismatchException;

/**
 * Multi-unit calculator.
 */
public class MultiUnitCalculator {

	/**
	 * @param expression
	 *            a String representing a multi-unit expression, as defined in
	 *            the problem set
	 * @return the value of the expression, as a number possibly followed by
	 *         units, e.g. "72pt", "3", or "4.882in"
	 * @throws TokenMismatchException 
	 * @throws TokenMismatchException 
	 */
	public String evaluate(String expression) throws  TokenMismatchException {
		// TODO implement for Problem 4
		Parser par = new Parser(new Lexer(expression));
		Parser.Value result = par.evaluate();
		
		return result.toString();
	}

	/**
	 * Repeatedly reads expressions from the console, and outputs the results of
	 * evaluating them. Inputting an empty line will terminate the program.
	 * 
	 * @param args
	 *            unused
	 * @throws TokenMismatchException 
	 * @throws TokenMismatchException 
	 */
	public static void main(String[] args) throws IOException, TokenMismatchException {
		MultiUnitCalculator calculator;
		String result;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String expression;
		while (true) {
			// display prompt
			System.out.print("> ");
			// read input
			expression = in.readLine();
			// terminate if input empty
			if (expression.equals(""))
				break;

			// evaluate
			calculator = new MultiUnitCalculator();
			result = calculator.evaluate(expression);
			// display result
			System.out.println(result);
		}
	}
}
