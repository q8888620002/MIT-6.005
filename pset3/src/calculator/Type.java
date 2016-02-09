package calculator;

/*
 * TODO define your symbols and groups from problem 1 here
 */

/**
 * Token type.
 * 
 * "6", "22" , any positive number
 * "in" inches
 * "pt" points
 * "(" open parenthesis
 * ")" close parenthesis
 * "+" add
 * "-" subtract
 * "*" multiply
 * "/" divide
 * 
 */
enum Type {
	// TODO define for problem 1
	operand,
	plus,
	subtract,
	divide,
	multiply,
	inch,
	point,
	openparenthesis,
	closeparenthesis,
	EOF
}