package minesweeper;

/*
 * A square interface represents a square in a board.
 */
public interface Square {
	
	/*
	 * Return a string representation of a Square
	 */
	public String toString();
	/*
	 * Return a new Square after the client touched it  
	 */
	public Square dug();
	/*
	 * Return the state of square
	 */
	public SquareState getState();
	/*
	 * flagged a square
	 */
	public Square flagged();
	
	public int getNearByBomb();
	/*
	 * check if a square is a bomb
	 */
	public Boolean isABomb();
}
