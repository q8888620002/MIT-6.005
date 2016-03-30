package minesweeper;

/*
 *  A Bomb represents a bomb square in a board
 *  It is immutable.
 *  Rep :   row , col, state , and isABomb is final 
 *  	    squareState is not null
 *  		
 *  	
 *  
 */
public class Bomb implements Square{

	private final int row;
	private final int col;
	private final SquareState state;
	private final Boolean IsABomb;
	
	/**
	 * Constructor method for non-bomb Square
	 * @param row of the Bomb
	 * @param column of the Bomb
	 * @param boolean value of the touch state 
	 * @param whether this square is a bomb
	 */
	public Bomb(int row,int col, SquareState state){
		this.row = row;
		this.col= col;
		this.state = state;
		this.IsABomb = true;
		checkRep();
		}
		
		/**
		 * Check rep of the Bomb square
		 */
		private void checkRep() {
			assert row >= 0:"row should be >= 0";
			assert col >= 0:"col should be >= 0";
		}
		
		/**
		 * Producer method of a bomb square
		 * @return a new Bomb square after being dug
		 */
		public Bomb dug() {
				return new Bomb(row,col, SquareState.DUG);
		}
		
		/**
		 * Producer method of a bomb square
		 * @return a new Bomb square after being flagged
		 */
		public Bomb flagged() {
			return new Bomb(row,col, SquareState.FLAGGED);
		}
		
		/**
		 * Observer of the state of square
		 * @return the state of the square
		 */
		public SquareState getState(){
			return state;
		}
		
		/**
		 * Validation of a bomb
		 * @return Boolean value  
		 */
		@Override
		public Boolean isABomb() {
			return IsABomb;
		}
		
		@Override
		public int getNearByBomb() {
			return 0;
		}

		
		/**
		 * Observer method of the bomb 
		 *@return the string representation of the bomb.
		 */
		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("I am a bomb at["+row+","+col+"]");
			return stringBuilder.toString();
		}

	

	

	
	
}