package minesweeper;
/*
 *  NotBomb is a immutable object represents a single square in a N*N board.
 *  It contains information such as dug or not, numbers of near by bomb,and flagged or untouched state.
 *  Rep : 
 *  	It is immutable.
 *  	nearByBomb, row , col,and state of square are final.
 *  	nearByBomb should be within 1-9
 *  	row, col, nearByBomb, squareState is not null
 *  	
 */
public class NotBomb implements Square{
	private final int row;
	private final int col;
	private final int nearByBomb;
	private final SquareState state;
	private final Boolean isAbomb;

	
	/**
	 * Constructor method for non-bomb Square
	 * @param row of the Non bomb 
	 * @param col of the non bomb
	 * @param integer numbers of bomb near by
	 * @param boolean value of the touch state 
	 */
	public NotBomb(int row,int col, int BombNumber,SquareState state){
		this.row = row;
		this.col = col;
		this.nearByBomb = BombNumber;
		this.state = state;
		this.isAbomb = false;
		checkRep(nearByBomb);
	}
	
	/**
	 * Constructor method for non-bomb Square without provided near by bomb number
	 * @param row of the Non bomb 
	 * @param col of the non bomb
	 * @param boolean value of the touch state 
	 */
	public NotBomb(int row,int col,SquareState state){
		this.row = row;
		this.col = col;
		this.nearByBomb = 0;
		this.state = state;
		this.isAbomb = false;
		checkRep(nearByBomb);
	}
	
	
	/**
	 * Check rep of the non-bomb Square
	 * @param BombNumber
	 */
	private void checkRep(int BombNumber) {
		assert 0<=BombNumber && BombNumber <= 8: " bombs number error";
		assert row >=0:"row should be >= 0";
		assert col >=0:"col should be >= 0";
	}
	
	
	/**
	 * Producer method of a non-bomb square 
	 * @return a new non-bomb Square after being dug
	 */
	public NotBomb dug() {
			return new NotBomb(row,col,nearByBomb, SquareState.DUG);
	}
	
	/**
	 * Producer method of a non-bomb square 
	 * @return a new non-bomb Square after being flagged
	 */
	public Square flagged() {
		return new NotBomb(row,col,nearByBomb, SquareState.FLAGGED);
	}
	
	/**
	 * Observer of the state of square
	 * @return the state of the square
	 */
	public SquareState getState(){
		return state;
	}
	
	/**
	 * Observer of near by bomb
	 * @return integer of near by bomb
	 */
	public int getNearByBomb() {
		return nearByBomb;
	}
	
	/**
	 * Validation of a bomb
	 * @return Boolean value  
	 */
	@Override
	public Boolean isABomb() {
		
		return isAbomb;
	}
	
	/**
	 * Observer method of the non-bomb square 
	 *@return the string representation of the non-bomb Square.
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("["+row+","+col+"]"+" with "+nearByBomb+" bombs nearby");
		return stringBuilder.toString();
		
	}



}
