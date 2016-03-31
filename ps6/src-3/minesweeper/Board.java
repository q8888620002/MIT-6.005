package minesweeper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

/*
 * A board represents the top layer of minesweeper. 
 * It is composed of N*N squares(bomb or non-bomb).
 * It is immutable.
 * 
 * Rep invariants:
 * 		Integer of dimension is final.
 * 		Squares is final.
 * 		A square is not null.
 * 		A square state should be DUG, FLAGGED, UNTOUCHED
 * 		
 * Thread Safety:
 * 		
 * 		- There is no mutator method in Board object; 
 * 	      besides, The accessor methods and producer methods need to be synchronized.
 *        getNeibor and getSquare don't need to be synchronized because they are
 *        only called when constructing a board.   
 * 		- All variable are final and immutable.
 * 		- Every ConnectionHandler is assigned to a client. Since all the 
 *      ConnectionHandlers work on a single instance of the Board, as
 *      long as the Board is threadsafe, the whole server is threadsafe. 
 * 		
 * 
 */
public class Board {
		private final Square[][] squares;
		private final int dim;
		
		/**
		 * Constructor of the Board
		 * @param dim of the Board 
		 * @param squares 
		 */
		public Board(int dim, Square[][] squares){
			this.dim = dim;
			this.squares = getNewSquare(squares);
			checkRep();
		}
		
		/**
		 * Constructor of Board without provided squares 
		 * @param dimension of the board
		 */
		public Board(int dim){
			this.dim = dim;
			/*
			 * Randomly assign a bomb to a square 
			 */
			Random rand = new Random();
			Square[][] squares = new Square[dim][dim];
			
			for(int i = 0;i < dim; i++ ){
				for(int j = 0; j< dim; j++){
						if(rand.nextInt(5)==0){
							squares[i][j] = new Bomb(i, j, SquareState.UNTOUCHED);
						}else {
							squares[i][j] = new NotBomb(i, j, SquareState.UNTOUCHED);
						}
				}
			}
			
			this.squares = squares;
		}
		
		
		/**
		 * Constructor of Board with a given file name 
		 * @param string path of the file
		 * @throws Exception 
		 */
		public Board(String fileName) throws Exception, FileNotFoundException {
			File File = new File("samples/", fileName );
			BufferedReader bufferedReader = new BufferedReader(new FileReader(File));
			
			String s = bufferedReader.readLine().replaceAll("\\s+","");
			Square[][] squares = new Square[s.length()][s.length()];
			this.dim = s.length();
			int row = 0;
		
			while(s!=null){
				 	s= s.replaceAll("\\s+","");
					for(int i = 0; i< s.length(); i++ ){
					
						switch (s.charAt(i)) {
						case '0':
							squares[row][i] = new NotBomb(row, i, SquareState.UNTOUCHED);
							break;
						case '1':
							squares[row][i] = new Bomb(row, i, SquareState.UNTOUCHED);
							break;
						default:
							throw new Exception("file content illegal");
						}
						
					}
					s = bufferedReader.readLine();
					row++;
				}
				
				this.squares = squares;
		}
		
		/**
		 * Check the rep of Board
		 * 
		 */
		private synchronized void checkRep() {
			assert dim > 0:"dimension should be greater than 0";
			assert squares!= null:"squares should not be null";
			for(int i = 0; i< dim;i++ ){
				for(int j = 0; j< dim;j++ ){
					
					assert squares[i][j]!=null:"a square should not be null";
					
					assert (squares[i][j].getState() == SquareState.DUG)||
					(squares[i][j].getState() == SquareState.UNTOUCHED)||
					(squares[i][j].getState() == SquareState.FLAGGED):
						"Illegal state of square["+i+"]"+"["+j+"]";
				}
			}
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
						newSquare[i][j] = new NotBomb(i, j, nearByBomb, squares[i][j].getState());
					}
				}
			}
			return newSquare;
		}
		
		/**
		 * get the number of bomb near by 
		 * @param current row of square, 0 <= row <= dim-1
		 * @param current col of square ,0 <= col <= dim-1
		 * @return the number of bomb
		 */
		private int getNeighbor(int row, int col,Square[][] squares) {
			assert (0 <=row )&& (row < dim):"row should be within 0 to "+ dim;
			assert (0 <=col )&& (col < dim):"row should be within 0 to "+ dim;
			
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
		 * @param row of the square ,0 <= row <= dim-1
		 * @param current col of square ,0 <= col <= dim-1
		 * @return a new board with square[row][col] dug
		 */
		public synchronized Board Dug(int row, int col){
			assert (0 <=row )&& (row < dim):"row should be within 0 to "+ dim;
			assert (0 <=col )&& (col < dim):"row should be within 0 to "+ dim;
			
			Square[][] newSquare = new Square[dim][dim];
			
			for(int i=0;i < dim; i++){
				for(int j=0;j < dim; j++){
					if((i == row) && (j == col) && (!squares[row][col].isABomb() )){
						newSquare[row][col] = squares[row][col].dug();
					}else if((i == row) && (j == col) && (squares[row][col].isABomb() )){
						newSquare[i][j] = new NotBomb(row, col, SquareState.DUG);
					}else{
						newSquare[i][j] = squares[i][j];
					}
				}
			}
			
			return new Board(dim, newSquare);
		}
		
		/**
		 * Changing state of a square after a client flagged it. 
		 * @param row of the square, 0 <= row <= dim-1
		 * @param current col of square ,0 <= col <= dim-1
		 * @return a new board with square[row][col] flagged
		 */
		public synchronized Board flag(int row, int col){
			Square[][] newSquare = new Square[dim][dim];
			
			for(int i=0;i < dim; i++){
				for(int j=0;j < dim; j++){
					if(i == row && j == col ){
						newSquare[row][col] = squares[row][col].flagged();
					}else{
						newSquare[i][j] = squares[i][j];
					}
				}
			}
			return new Board(dim, newSquare);
		}
		
		
		/**
		 * Changing state of a square after a client deflagged it. 
		 * @param row of the square, 0 <= row <= dim-1
		 * @param current col of square ,0 <= col <= dim-1
		 * @return a new board with square[row][col] flagged
		 */
		public synchronized Board deflag(int row, int col){
			Square[][] newSquare = new Square[dim][dim];
			
			for(int i=0;i < dim; i++){
				for(int j=0;j < dim; j++){
					if(i == row && j == col && squares[row][col].getState() == SquareState.FLAGGED){
						newSquare[row][col] = squares[row][col].deflagged();
					}else{
						newSquare[i][j] = squares[i][j];
					}
				}
			}
			return new Board(dim, newSquare);
		}
		
		
		/**
		 * Get the state of given coordination 
		 * @param row of the square , 0 =< x < dim
		 * @param col of the square , 0 =< y , dim
		 * @return state of the square or print an error message if x or y out of range
		 */
		public synchronized SquareState getState(int x, int y){
				if(( x>=0 && x< dim)&&( y >=0 && y< dim)){
					return squares[x][y].getState();
				}else{
					System.err.println("Invalid coordination of("+x+","+y+")");
					return null;
				}
			
		}
		
		/**
		 * Return if is a bomb of a square
		 * @param row of the square , 0 =< x < dim
		 * @param col of the square , 0 =< y , dim
		 * @return Boolean value of weather a square if it's a bomb
		 */
		
		public synchronized Boolean isABomb(int x, int y) {
			return squares[x][y].isABomb();
		}
		
		/**
		 * Return numbers of nearby bomb of a square
		 * @param row of the square , 0 =< x < dim
		 * @param col of the square , 0 =< y , dim
		 * @return numbers of bomb near by
		 */
		
		public synchronized Integer bombNumber(int x, int y) {
			return squares[x][y].getNearByBomb();
		}
		
		/**
		 * Return the size of the board
		 * @return the size of the board
		 */
		public synchronized Integer getDim(){
			return dim;
		}
		
		/**
		 * Observer of a board
		 *  '-' represents UNTOUCHED squares, ' ' represents dug squares
		 * '[1-8]' represents a square with that number of neighboring bombs
		 * @return a string representation of Board
		 */
		public  synchronized String toString(){
			StringBuilder stringBuilder = new StringBuilder();
			for(int i=0;i < dim ;i++){
				for(int j=0; j< dim ;j++){
					switch (squares[i][j].getState()) {
					case DUG:
						if (!squares[i][j].isABomb()) {
							if(squares[i][j].getNearByBomb()==0){
								stringBuilder.append("  ");
							}else{
								stringBuilder.append(squares[i][j].getNearByBomb()+" ");
							}
						}
						break;
					case FLAGGED:
						stringBuilder.append("F ");
						break;
					case UNTOUCHED:
						stringBuilder.append("- ");
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
