/**
 * Author: dnj, Hank Huang
 * Date: March 7, 2009
 * 6.005 Elements of Software Construction
 * (c) 2007-2009, MIT 6.005 Staff
 */
package sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import sat.env.Bool;
import sat.env.Environment;
import sat.env.Variable;
import sat.formula.Clause;
import sat.formula.Formula;
import sat.formula.Literal;
import sat.formula.NegLiteral;
import sat.formula.PosLiteral;

/**
 * Sudoku is an immutable abstract datatype representing instances of Sudoku.
 * Each object is a partially completed Sudoku puzzle.
 */
public class Sudoku {
    // dimension: standard puzzle has dim 3
    private final int dim;
    // number of rows and columns: standard puzzle has size 9
    private final int size;
    // known values: square[i][j] represents the square in the ith row and jth
    // column,
    // contains -1 if the digit is not present, else i>=0 to represent the digit
    // i+1
    // (digits are indexed from 0 and not 1 so that we can take the number k
    // from square[i][j] and
    // use it to index into occupies[i][j][k])
    private final int[][] square;
    // occupies [i,j,k] means that kth symbol occupies entry in row i, column j
    private final Variable[][][] occupies;
    
    // Rep invariant of Sudoku
    //      dim:
    //        dim * dim == size
    //            2 <= dim <= 3
    //      size: 
    //           size is equals to dim* dim
    //           size == 9 or 4
    //      square: 
    //          square != null
    //          the puzzle is either filled with digits 0~size 
    //          
    //      occupies:
    //          occupies != null
    
    
    // TODO: write your rep invariant here
     void checkRep() {
       
    	 try {
    		 assert false;
    		 checkRep( this.dim, this.size, this.square, this.occupies);
    		 
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    // check the rep invariant of Sudoku
    
    private void checkRep(int dim, int size, int[][] square, Variable[][][] occupies){
    	
    	assert 2 <= dim && dim <= 3: "Sudoku, Rep invariant: dimension should be between 2 to 3";
    	assert size == dim*dim: "Sudoku, Rep invariant: size of sudoku should be equal to"
    	                         + dim+"multiply"+dim;
    	assert square != null :"Sudoku, Rep invariant: square should be non-null";
    	
    	for(int i= 0; i< size ; i++){
    		for (int j= 0; j < size; j++){
    			
    			assert 0 <= square[i][j] && square[i][j] <= size 
    				:"Sudoku, Rep invariant: digits of Sudoku is neither within range of 0-"
    					+size+"or equal to -1"; 
    		}
    	}	
    	assert occupies != null:"Sudoku, Rep invariant: occupies should be non-null";
    }

    /**
     * create an empty Sudoku puzzle of dimension dim.
     * 
     * @param dim
     *            size of one block of the puzzle. For example, new Sudoku(3)
     *            makes a standard Sudoku puzzle with a 9x9 grid.
     */
    public Sudoku(int dim) {
    	 // problem 1b.
    	this.dim = dim;
    	this.size = dim*dim;
    	this.square = new int[size][size];
    	this.occupies = initOccupies(size);
    	checkRep();
    }

    /**
     * create Sudoku puzzle
     * 
     * @param square
     *            digits or blanks of the Sudoku grid. square[i][j] represents
     *            the square in the ith row and jth column, contains 0 for a
     *            blank, else i to represent the digit i. So { { 0, 0, 0, 1 }, {
     *            2, 3, 0, 4 }, { 0, 0, 0, 3 }, { 4, 1, 0, 2 } } represents the
     *            dimension-2 Sudoku grid: 
     *            
     *            ...1 
     *            23.4 
     *            ...3
     *            41.2
     * 
     * @param dim
     *            dimension of puzzle Requires that dim*dim == square.length ==
     *            square[i].length for 0<=i<dim.
     */
    public Sudoku(int dim, int[][] square) {
    	 // problem 1b.

    	this.dim = dim;
    	this.size = dim * dim;
    	this.square = square;
    	this.occupies = initOccupies(size);
    	checkRep();
    }
    
    // 
    
    private Variable[][][] initOccupies(int size){
    	Variable[][][] var = new Variable[size][size][size+1];
    	for(int i=0; i< size;i++){
    		for(int j = 0; j< size; j++ ){
    			for(int k = 1 ; k < size + 1 ; k++){
    				var[i][j][k] = 
    						new Variable(k+" occupies entry in row "+ i + ", column "+ j);
    			}
    		}
    	}
    	return var;
    }
    /**
     * Reads in a file containing a Sudoku puzzle.
     * 
     * @param dim
     *            Dimension of puzzle. Requires: at most dim of 3, because
     *            otherwise need different file format
     * @param filename
     *            of file containing puzzle. The file should contain one line
     *            per row, with each square in the row represented by a digit,
     *            if known, and a period otherwise. With dimension dim, the file
     *            should contain dim*dim rows, and each row should contain
     *            dim*dim characters.
     * @return Sudoku object corresponding to file contents
     * @throws IOException
     *             if file reading encounters an error
     * @throws ParseException
     *             if file has error in its format
     */
    public static Sudoku fromFile(int dim, String filename) throws IOException,
            ParseException {
       // problem.1c
    	File newFile = new File("samples/", filename );
    	BufferedReader bReader = new BufferedReader(new FileReader(newFile));
    	ArrayList<String> lines = new ArrayList<String>();
    	
    		int size = dim*dim; 
    		int[][] square = new int[size][size];

    		String line;
    		while((line = bReader.readLine()) != null){
    			lines.add(line);
    			}
    		bReader.close();
    		
    		for(int i = 0; i< dim*dim; i++){
    			for(int j = 0; j < dim*dim ;j++){
    				char charDigit = lines.get(i).charAt(j);
    				if(charDigit !='.'){
    					square[i][j] = Character.getNumericValue(charDigit);
    				}else{
    					square[i][j] = 0;
    				}
    			}
    		}
    		
    		return new Sudoku(dim, square);
    }

    /**
     * Exception used for signaling grammatical errors in Sudoku puzzle files
     */
    @SuppressWarnings("serial")
    public static class ParseException extends Exception {
        public ParseException(String msg) {
            super(msg);
        }
    }

    /**
     * Produce readable string representation of this Sukoku grid, e.g. for a 4
     * x 4 sudoku problem: 
     *   12.4 
     *   3412 
     *   2.43 
     *   4321
     * 
     * @return a string corresponding to this grid
     */
    public String toString() {
        // problem 1b.
    	StringBuilder s = new StringBuilder(); 
    	
    	for(int i = 0; i< this.size; i++){
    		for(int j = 0; j< this.size; j++){
    			int k = this.square[i][j];
    			if(k==0){
    				s.append(".");
    			}else{
    				s.append(Integer.toString(k));
    			}
    		}
    		s.append("\n");
    	}
    	return s.toString();
    }

    /**
     * @return a SAT problem corresponding to the puzzle, using variables with
     *         names of the form occupies(i,j,k) to indicate that the kth symbol
     *         occupies the entry in row i, column j
     */
    public Formula getProblem() {
    	
    	Formula originalf = new Formula();
    	for(int i =0;i<size; i++){
    		for(int j =0; j< size; j++){
    			if(square[i][j]>0){
    				Literal l = PosLiteral.make(occupies[i][j][square[i][j]]);
    				Clause c = new Clause(l);
    				originalf = originalf.addClause(c);
    			}
    		}
    	}
    	
    	
    	Formula checkOverlapDigit = new Formula();
    	for(int i=0; i< size;i++){
    		for(int j =0; j< size; j++){
    			for(int k =1; k< size+1 ;k++){
        			for(int digit = k+1 ;digit < size+1;digit++){
        					Clause c = new Clause(PosLiteral.make(occupies[i][j][digit]));
        					Formula f = new Formula(PosLiteral.make(occupies[i][j][k]));
            				f = f.addClause(c).not();
            				checkOverlapDigit= checkOverlapDigit.and(f);
        			}
    			}
    		}
    	}
    	
    	Formula RowAtLeastOnce = new Formula();
    	Formula RowAtmostOnce = new Formula();
    	for(int i =0; i< size;i++){
    		for(int j =0; j< size;j++){
    			for(int n =1; n<size+1;n++){
    			
    			Clause checkRowNum = new Clause();
    			for(int k = 0;k < size; k++){
    
    				
    				Literal squareDigit = PosLiteral.make(occupies[i][k][n]);
    				
    				checkRowNum = checkRowNum.add(squareDigit);
    				
    				for(int index = k ;index+1< size;index++){
    					
    					Clause overlapLiteral = new Clause(PosLiteral.make(occupies[i][index+1][n]));
        				Formula f = new Formula(squareDigit);
        				f = f.addClause(overlapLiteral).not();
        				RowAtmostOnce = RowAtmostOnce.and(f);
    				}
    			}
    			RowAtLeastOnce= RowAtLeastOnce.addClause(checkRowNum);
    		}
    	}
    }
    	
    	
    	
    	Formula ColAtLeastOnce = new Formula();
    	Formula ColAtmostOnce = new Formula();
    	for(int i =0; i< size;i++){
    		for(int j =0; j< size;j++){
    			for(int d =1;d<size+1;d++){
    			
    			Clause checkColNum = new Clause();
    			
    			for(int k = 0;k < size; k++){
    			
    				Literal squareDigit = PosLiteral.make(occupies[k][j][d]);
    				
    				
    				checkColNum = checkColNum.add(squareDigit);
    				
    				for(int index = k ;index+1< size;index++){
    					Clause overlapLiteral = new Clause(PosLiteral.make(occupies[index+1][j][d]));
    					
        				Formula f = new Formula(squareDigit);
        				f = f.addClause(overlapLiteral).not();
        				ColAtmostOnce = ColAtmostOnce.and(f);
    				}
    			}
    			ColAtLeastOnce= ColAtLeastOnce.addClause(checkColNum);
    		}
    			}
    	}
    	
    	return ColAtLeastOnce.and(ColAtmostOnce).and(RowAtmostOnce).and(RowAtLeastOnce).and(checkOverlapDigit);
        // TODO: implement this.
    }

    /**
     * Interpret the solved SAT problem as a filled-in grid.
     * 
     * @param e
     *            Assignment of variables to values that solves this puzzle.
     *            Requires that e came from a solution to this.getProblem().
     * @return a new Sudoku grid containing the solution to the puzzle, with no
     *         blank entries.
     */
    public Sudoku interpretSolution(Environment e) {
    	
    	int[][] newSquare  = new int[size][size];
    	
    	for(int i=0; i<size;i++){
    		for(int j =0; j< size; j++){
    			for(int k =1;k<size+1;k++){
    				if(e.get(occupies[i][j][k]) == Bool.TRUE){
    					newSquare[i][j] = k;
    				}
    			}
    		}
    	}
    	
    	return new Sudoku(dim,newSquare);

        // TODO: implement this.
    }

}
