package minesweeper;


/*
 * A board represents the top layer of minesweeper. 
 * It is composed of N*N squares(bomb or non-bomb).
 * It is immutable.
 * Rep :
 * 		Integer of dimension is final.
 * 		Square is not null.
 */
public class Board {
		private  Square[][] squares;
		private final int dim;
		
		/**
		 * Constructor of the Board
		 * @param dim of the Board 
		 */
		public Board(int dim, Square[][] squares){
			this.dim = dim;
			this.squares = getNewSquare(squares);
			checkRep();
			
		}
		
		/**
		 * Check the rep of Board
		 */
		private void checkRep() {
			assert dim > 0:"dimension should be greater than 0";
			assert squares!= null:"squares should not be null";
		}
		
		/**
		 * Calculate nearby Bomb number
		 * @return a new Board with correct nearby bomb numbers within squares
		 */
		private Square[][] getNewSquare(Square[][] squares){
			Square[][] newSquare = new Square[dim][dim];
		for(int i = 0; i< dim;i++ ){
			for(int j = 0; j< dim;j++ ){
				int nearByBomb = getNeighbor(i,j, squares);
					if(squares[i][j].isABomb()){
						newSquare[i][j] = squares[i][j];
					}else{
						newSquare[i][j] = new NotBomb(i, j, nearByBomb, SquareState.UNTOUCHED);
					}
				}
			}
			return newSquare;
		}
		
		/**
		 * get the number of bomb near by 
		 * @param current row of square
		 * @param current col of square 
		 * @return the number of bomb
		 */
		private int getNeighbor(int row, int col,Square[][] squares) {
			int Bomb = 0;
			for(int i= row-1 ; i <= row+1; i++){
				if(!((i < 0)||(i > dim - 1 ))){
					for(int j = col-1 ; j <= col+1;j++){
						if(!((j < 0)||(j > dim-1)||(j == col && i == row))){
							
							if(squares[i][j].isABomb()){
								Bomb++;
							}
							
						}
					}
				}
			}
			return Bomb;	
		}
		
		/**
		 * Changing state of a square after a client dug it. 
		 * @param row of the square
		 * @param col of the square
		 */
		public void Dug(int row, int col){
			Square box = squares[row][col];
			if(!box.isABomb()){
				squares[row][col] = squares[row][col].dug();
			}
		}
		
		
		/**
		 * Observer of a board
		 * @return a string representation of Board
		 */
		public String toString(){
			StringBuilder stringBuilder = new StringBuilder();
			for(int i=0;i < dim ;i++){
				for(int j=0; j< dim ;j++){
					switch (squares[i][j].getState()) {
					case DUG:
						if (!squares[i][j].isABomb()) {
							if(squares[i][j].getNearByBomb()==0){
								stringBuilder.append(" ");
							}else{
								stringBuilder.append(squares[i][j].getNearByBomb());
							}
						}
						break;
					case FLAGGED:
						stringBuilder.append("F");
						break;
					case UNTOUCHED:
						stringBuilder.append("-");
						break;
					default:
						break;
					}
				}
				stringBuilder.append("\n");
			}
		return stringBuilder.toString();
		}
}
