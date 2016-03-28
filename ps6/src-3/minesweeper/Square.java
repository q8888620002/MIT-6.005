package minesweeper;
/*
 *  Square is a immutable object represents a single square in a N*N board.
 *  It contains information such as dug or not, numbers of near by bomb,and flagged or untouched state.
 *  Rep : 
 *  	It is immutable.
 *  	nearByBomb and location are final.
 *  
 */
public class Square {
	private final int nearByBomb;
	private final Boolean touched;
	
	/**
	 * Constructor method for Square
	 * @param integer numbers of bomb near by
	 * @param boolean value of the touch state 
	 */
	public Square(int BombNumber, boolean dig){
		this.nearByBomb = BombNumber;
		this.touched = dig;
		checkRep();
	}
	
	
	
	/*
	 * Check the rep invariant of Square
	 */
	private void checkRep() {
		try {
			
			assert false;
			checkRep();
		} catch (Exception e) {
			
		}
	}

	private void checkRep(int BombNumber, boolean dig) {
		
	}



	/**
	 * Observer for the square 
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("with"+nearByBomb+" nearby");
		return stringBuilder.toString();
		
	}
	
}
