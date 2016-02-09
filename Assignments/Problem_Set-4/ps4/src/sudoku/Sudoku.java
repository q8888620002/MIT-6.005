/**
 * Author: dnj, Hank Huang
 * Date: March 7, 2009
 * 6.005 Elements of Software Construction
 * (c) 2007-2009, MIT 6.005 Staff
 */
package sudoku;

import java.io.IOException;
import sat.env.Bool;
import sat.env.Environment;
import sat.env.Variable;
import sat.formula.Clause;
import sat.formula.Formula;
import sat.formula.Literal;
import sat.formula.NegLiteral;
import sat.formula.PosLiteral;
import utils.FileUtils;

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
    // contains 0 if the digit is not present, else k (1 <= k <= size) to represent
    // the digit k
    private final int[][] square;
    // occupies [i,j,k] means that kth (1 <= k <= size) symbol occupies entry in
    // row i, column j
    private final Variable[][][] occupies;

    // Rep invariants:
    //      (1) dim*dim == size
    //          the number of rows and columns are: dim*dim
    //      (2) 2 <= dim <= 3
    //          the puzzle has at least dim of 2 and at most 3
    //      (3) square != null
    //      (4) occupies != null
    //      (5) 0 <= square[i][j] <= size
    //          the puzzle is either filled with digits 1~size or
    //          left blank
    //
    // Abstract Function:
    //      The slot square[i][j] represents the ith row and jth column in the
    //      sudoku puzzle,
    //          square[i][j] == 0 if the slot is empty, else
    //          square[i][j] == k (1 <= k <= 9) to represent the digit k
    //
    //      For example, if the sudoku puzzle contains
    //
    //              +---+---+---+---+
    //              |   |   |   |   |
    //              +---+---+---+---+
    //              | 3 |   |   |   |
    //              +---+---+---+---+
    //              |   |   | 4 |   |
    //              +---+---+---+---+
    //              |   | 2 | 3 |   |
    //              +---+---+---+---+
    //
    //      then, the corresponding two dimensional array is
    //
    //            { { 0, 0, 0, 0 },
    //              { 3, 0, 0, 0 },
    //              { 0, 0, 4, 0 },
    //              { 0, 2, 3, 0 } }
    //      
    private void checkRep() {
        assert (this.dim * this.dim) == size :
            "Sudoku, Rep invariant: the number of rows and columns "
            + "should be: "+ dim + "*" + dim;
        assert ((this.dim >= 2) && (this.dim <= 3)) :
            "Sudoku, Rep invariant: the puzzle has at least dim of 2 "
            + "and at most dim of 3";
        assert square != null : "Sudoku, Rep invariant: square non-null";
        assert occupies != null : "Sudoku, Rep invariant: occupies non-null";
        for (int i = 0; i < size; i = i + 1) {
            for (i
            }nt j = 0; j < size; j = j + 1) {
                assert ((square[i][j] >= 0) && (square[i][j] <= size)) :
                    "Sudoku, Rep invariant: the slots are either filled "
                    + "with digits 0-" + size;
        }
    }

    /**
     * create an empty Sudoku puzzle of dimension dim.
     * 
     * @param dim
     *            size of one block of the puzzle. For example, new Sudoku(3)
     *            makes a standard Sudoku puzzle with a 9x9 grid.
     */
    public Sudoku(int dim) {
        this.dim = dim;
        this.size = dim * dim;
        // create a size*size Sudoku grid where each square is unoccupied
        this.square = new int[size][size];
        for (int i = 0; i < size; i = i + 1) {
            for (int j = 0; j < size; j = j + 1) {
                this.square[i][j] = 0;
            }
        }
        this.occupies = initializeVariables(size);
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
     *            square[i].length for 0<=i<(dim^2 - 1).
     */
    public Sudoku(int dim, int[][] square) {
        this.dim = dim;
        this.size = dim * dim;
        this.square = square;
        this.occupies = initializeVariables(size);
        checkRep();
    }
    
    // Create a variable for each square in the Sudoku grid
    private Variable[][][] initializeVariables(int size) {
        Variable[][][] vars = new Variable[size][size][size+1];
        for (int i = 0; i < size; i = i + 1) {
            for (int j = 0; j < size; j = j + 1) {
                for (int k = 1; k < size+1; k = k + 1) {
                    Variable var = new Variable(
                            "occupies(" + i + "," + j + "," + k + ")");
                    vars[i][j][k] = var;
                }
            }
        }
        return vars;
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
        String fileContents = FileUtils.fetch(filename);
        int size = dim * dim;
        char[][] charMatrix = convertStringToCharMatrix(fileContents);
        int[][] digitMatrix = new int[size][size];
        for (int i = 0; i < size; i = i + 1) {
            for (int j = 0; j < size; j = j + 1) {
                if (charMatrix[i][j] == '.') {
                    digitMatrix[i][j] = 0;
                } else {
                    assert (Character.getNumericValue(charMatrix[i][j]) >= 1) &&
                        (Character.getNumericValue(charMatrix[i][j]) <= 9) :
                            "fromFile: only digits 1-9, '.' and '\\n' are legal"
                            + " in the text representation of Sudoku puzzle.";
                    digitMatrix[i][j] = Character.getNumericValue(charMatrix[i][j]);
                }
            }
        }
        return new Sudoku(dim, digitMatrix);
    }
    
    // Convert a string into a matrix of characters,
    // each row in the matrix corresponds to a substring partitioned
    // by the character '\n'
    private static char[][] convertStringToCharMatrix(String text) {
        String[] lines = text.split("\n");
        int size = lines.length;
        char[][] charMatrix = new char[size][size];
        for (int i = 0; i < size; i = i + 1) {
            for (int j = 0; j < size; j = j + 1) {
                charMatrix[i][j] = lines[i].charAt(j);
            }
        }
        return charMatrix;
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
     * Produce readable string representation of this sudoku grid, e.g. for a 4
     * x 4 sudoku problem: 
     *   12.4 
     *   3412 
     *   2.43 
     *   4321
     * 
     * @return a string corresponding to this grid
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i = i + 1) {
            for (int j = 0; j < size; j = j + 1) {
                if (square[i][j] == 0) {
                    sb.append('.');
                } else {
                    sb.append(square[i][j]);
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * @return a SAT problem corresponding to the puzzle, using variables with
     *         names of the form occupies(i,j,k) to indicate that the kth symbol
     *         occupies the entry in row i, column j
     */
    public Formula getProblem() {
        Formula foc = new Formula();    // formula of the 5 constrains
        
        
        // solution must be consistent with the starting grid
        Formula foc1 = new Formula();
        for (int i = 0; i < size; i = i + 1) {
            for (int j = 0; j < size; j = j + 1) {
                int k = square[i][j];
                if (k > 0) { // a known entry found, create a clause
                    Variable v_ijk = occupies[i][j][k];
                    Literal l = PosLiteral.make(v_ijk);
                    Clause c = new Clause(l);
                    foc1 = foc1.addClause(c);
                }
            }
        }
        
        
        // at most one digit per square
        Formula foc2 = new Formula();
        for (int i = 0; i < size; i = i + 1) {
            for (int j = 0; j < size; j = j + 1) {
                for (int k = 1; k < size+1; k = k + 1) {
                    Variable v_ijk = occupies[i][j][k];
                    Literal l1 = NegLiteral.make(v_ijk);
                    for (int k2 = k + 1; k2 < size+1; k2 = k2 + 1) {
                        Variable v_ijk2 = occupies[i][j][k2];
                        Literal l2 = NegLiteral.make(v_ijk2);
                        Clause c = new Clause().add(l1).add(l2);
                        foc2 = foc2.addClause(c);
                    }
                }
            }
        }
        
        
        // in each row, each digit must appear exactly once
        Formula foc3 = new Formula();
        
        for (int i = 0; i < size; i = i + 1) {
            for (int k = 1; k < size+1; k = k + 1) {
                
                // digit k appears AT LEAST ONCE in row i
                Clause c1 = new Clause();
                for (int j = 0; j < size; j = j + 1) {
                    Variable v_ijk = occupies[i][j][k];
                    Literal l = PosLiteral.make(v_ijk);
                    c1 = c1.add(l);
                }
                foc3 = foc3.addClause(c1);
                
                // digit k appears AT MOST ONCE in row i
                for (int j = 0; j < size; j = j + 1) {
                    Variable v_ijk = occupies[i][j][k];
                    Literal l1 = NegLiteral.make(v_ijk);
                    for (int j2 = j + 1; j2 < size; j2 = j2 + 1) {
                        Variable v_ij2k = occupies[i][j2][k];
                        Literal l2 = NegLiteral.make(v_ij2k);
                        Clause c2 = new Clause(l1).add(l2);
                        foc3 = foc3.addClause(c2);
                    }
                }
            }
        }
        
        
        // in each column, each digit must appear exactly once
        Formula foc4 = new Formula();
        
        for (int j = 0; j < size; j = j + 1) {
            for (int k = 1; k < size+1; k = k + 1) {
                
                // digit k appears AT LEAST ONCE in column j
                Clause c1 = new Clause();
                for (int i = 0; i < size; i = i + 1) {
                    Variable v_ijk = occupies[i][j][k];
                    Literal l = PosLiteral.make(v_ijk);
                    c1 = c1.add(l);
                }
                foc4 = foc4.addClause(c1);
                
                // digit k appears AT MOST ONCE in column j
                for (int i = 0; i < size; i = i + 1) {
                    Variable v_ijk = occupies[i][j][k];
                    Literal l1 = NegLiteral.make(v_ijk);
                    for (int i2 = i + 1; i2 < size; i2 = i2 + 1) {
                        Variable v_i2jk = occupies[i2][j][k];
                        Literal l2 = NegLiteral.make(v_i2jk);
                        Clause c2 = new Clause(l1).add(l2);
                        foc4 = foc4.addClause(c2);
                    }
                }
            }
        }
        
        
        // in each block, each digit must appear exactly once
        Formula foc5 = new Formula();
        
        for (int br = 0; br < dim; br = br + 1) {   // br: block rows
            for (int bc = 0; bc < dim; bc = bc + 1) {   // bc: block columns
                for (int k = 1; k < size+1; k = k + 1) {
                    
                    // digit k appears AT LEAST ONCE in this block
                    Clause c1 = new Clause();
                    for (int i = 0; i < dim; i = i + 1) {
                        for (int j = 0; j < dim; j = j + 1) {
                            Variable v_ijk = occupies[br*dim + i][bc*dim + j][k];
                            Literal l = PosLiteral.make(v_ijk);
                            c1 = c1.add(l);
                        }
                    }
                    foc5 = foc5.addClause(c1);
                    
                    // digit k appears AT MOST ONCE in this block
                    for (int i = 0; i < dim; i = i + 1) {
                        for (int j = 0; j < dim; j = j + 1) {
                            Variable v_ijk = occupies[br*dim + i][bc*dim + j][k];
                            Literal l1 = NegLiteral.make(v_ijk);
                            for (int i2 = i; i2 < dim; i2 = i2 + 1) {
                                for (int j2 = j + 1; j2 < dim; j2 = j2 + 1) {
                                    Variable v_i2j2k =
                                            occupies[br*dim + i2][bc*dim + j2][k];
                                    Literal l2 = NegLiteral.make(v_i2j2k);
                                    Clause c2 = new Clause();
                                    c2 = c2.add(l1).add(l2);
                                    foc5 = foc5.addClause(c2);
                                }
                            }
                        }
                    }
                }
            }
        }

        // take conjunction (f1 and f2 and f3 and f4 and f5) to produce a
        // propositional formula that SAT solver is able to solve
        foc = foc1.and(foc2).and(foc3).and(foc4).and(foc5);
        return foc;
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
        Sudoku solution = new Sudoku(dim);
        for (int i = 0; i < size; i = i + 1) {
            for (int j = 0; j < size; j = j + 1) {
                for (int k = 1; k < size+1; k = k + 1) {
                    Variable v_ijk = occupies[i][j][k];
                    if (e.get(v_ijk) == Bool.TRUE) {
                        solution.square[i][j] = k;
                    }
                }
            }
        }
        return solution;
    }

}
