package sudoku;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import sudoku.Sudoku.ParseException;


public class SudokuTest {
    

    // make sure assertions are turned on!  
    // we don't want to run test cases without assertions too.
    // see the handout to find out how to turn them on.
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
    
    @Test
    public void testTwoDimEmptySudoku() {
    	Sudoku s = new Sudoku(2);
    	String expected = "....\n" +
    					  "....\n" +
    					  "....\n" +
    					  "....\n";
    	assertEquals(s.toString(), expected);
    }
    
    @Test
    public void testTwoDimParCompletedSudoku() {
    	Sudoku s = new Sudoku(2, new int[][] {
    			new int[] { 0, 1, 0, 4 },
    			new int[] { 0, 0, 0, 0 },
    			new int[] { 2, 0, 3, 0 },
    			new int[] { 0, 0, 0, 0 },
    	});
    	String expected = ".1.4\n" +
    					  "....\n" +
    					  "2.3.\n" +
    					  "....\n";
    	assertEquals(s.toString(), expected);
    }
    
    @Test
    public void testThreeDimEmptySudoku() {
    	Sudoku s = new Sudoku(3);
    	String expected = ".........\n" +
    					  ".........\n" +
    					  ".........\n" +
    					  ".........\n" +
    					  ".........\n" +
    					  ".........\n" +
    					  ".........\n" +
    					  ".........\n" +
    					  ".........\n";
    	assertEquals(s.toString(), expected);
    }
    
    @Test
    public void testThreeDimParCompletedSudoku() {
    	Sudoku s = new Sudoku(3, new int[][] {
    			new int[] { 7, 1, 3, 2, 5, 4, 9, 6, 8 },
    			new int[] { 0, 0, 0, 0, 8, 7, 5, 4, 1 },
    			new int[] { 8, 4, 5, 0, 6, 1, 7, 0, 0 },
    			new int[] { 0, 5, 0, 6, 1, 2, 4, 9, 7 },
    			new int[] { 0, 0, 0, 4, 7, 9, 8, 0, 0 },
    			new int[] { 4, 0, 9, 5, 3, 8, 0, 1, 0 },
    			new int[] { 0, 0, 0, 7, 2, 0, 1, 8, 6 },
    			new int[] { 0, 0, 0, 1, 9, 6, 0, 5, 4 },
    			new int[] { 1, 0, 6, 8, 4, 5, 2, 7, 9 },
    	});
    	String expected = "713254968\n" +
    					  "....87541\n" +
    					  "845.617..\n" +
    					  ".5.612497\n" +
    					  "...4798..\n" +
    					  "4.9538.1.\n" +
    					  "...72.186\n" +
    					  "...196.54\n" +
    					  "1.6845279\n";
    	assertEquals(s.toString(), expected);
    }
    
    @Test
    public void testEmptyTwoDimFromFile() throws IOException,
    	ParseException {
    	String filename = "samples/sudoku_4x4_empty.txt";
    	Sudoku expected = new Sudoku(2, new int[][] {
    			new int[] { 0, 0, 0, 0 },
    			new int[] { 0, 0, 0, 0 },
    			new int[] { 0, 0, 0, 0 },
    			new int[] { 0, 0, 0, 0 },
    	});
    	assertEquals(Sudoku.fromFile(2, filename).toString(), expected.toString());
    }
    
    @Test
    public void testParCompletedTwoDimFromFile() throws IOException,
    	ParseException {
    	String filename = "samples/sudoku_4x4.txt";
    	Sudoku expected = new Sudoku(2, new int[][] {
    			new int[] { 0, 2, 3, 4 },
    			new int[] { 3, 4, 1, 0 },
    			new int[] { 2, 1, 4, 0 },
    			new int[] { 0, 3, 2, 1 },
    	});
    	assertEquals(Sudoku.fromFile(2, filename).toString(), expected.toString());
    }
    
    @Test
    public void testEmptyThreeDimFromFile() throws IOException,
    	ParseException {
    	String filename = "samples/sudoku_9x9_empty.txt";
    	Sudoku expected = new Sudoku(3, new int[][] {
    			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    			new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    	});
    	assertEquals(Sudoku.fromFile(3, filename).toString(), expected.toString());
    }
    
    @Test
    public void testParCompletedThreeDimFromFile() throws IOException,
    	ParseException {
    	String filename = "samples/sudoku_hard2.txt";
    	Sudoku expected = new Sudoku(3, new int[][] {
    			new int[] { 7, 1, 3, 2, 5, 4, 9, 6, 8 },
    			new int[] { 0, 0, 0, 0, 8, 7, 5, 4, 1 },
    			new int[] { 8, 4, 5, 0, 6, 1, 7, 0, 0 },
    			new int[] { 0, 5, 0, 6, 1, 2, 4, 9, 7 },
    			new int[] { 0, 0, 0, 4, 7, 9, 8, 0, 0 },
    			new int[] { 4, 0, 9, 5, 3, 8, 0, 1, 0 },
    			new int[] { 0, 0, 0, 7, 2, 0, 1, 8, 6 },
    			new int[] { 0, 0, 0, 1, 9, 6, 0, 5, 4 },
    			new int[] { 1, 0, 6, 8, 4, 5, 2, 7, 9 },
    	});
    	assertEquals(Sudoku.fromFile(3, filename).toString(), expected.toString());
    }
}