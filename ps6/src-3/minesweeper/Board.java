package minesweeper;
/*
 * A board represents the top layer of minesweeper. 
 * It is composed of N*N squares(bomb or non-bomb).
 * It is immutable.
 * Rep :
 * 		squares is final.
 * 		integer of dimension is final.
 * 		square is not null.
 */
public class Board {
		private final Square[][] squares;
		private final int dim;
		
		/**
		 * Constructor of the Board
		 * @param dim of the Board 
		 */
		public Board(int dim, Square[][] squares){
			this.dim = dim;
			this.squares = squares;
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
		 * Observer of a board
		 * @return a string representation of Board
		 */
		public String toString(){
			StringBuilder stringBuilder = new StringBuilder();
			for(int i=0;i < dim ;i++){
				for(int j=0; j< dim ;j++){
					int nearByBomb = squares[i][j].getNearByBomb();
					switch (squares[i][j].getState()) {
					case DUG:
						if (nearByBomb == 0) {
							stringBuilder.append(" ");
						}else{
							stringBuilder.append(nearByBomb);
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
