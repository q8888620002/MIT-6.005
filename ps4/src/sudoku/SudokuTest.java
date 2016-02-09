package sudoku;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import sudoku.Sudoku.ParseException;


public class SudokuTest {
    

    // make sure assertions are turned on!  
    // we don't want to run test cases without assertions too.
    // see the handout to find out how to turn them on.
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }

    // TODO: put your test cases here
    
    
    
    @Test(expected=AssertionError.class)
    public void sudokuConsTest(){
    	
    	Sudoku sudoku = new Sudoku(0);
    	sudoku.checkRep();
    }
    
    // init sudoku with dimension 2
    
    @Test
    public void sudokuConsTest01(){
    	Sudoku sudoku = new Sudoku(2);
    	sudoku.checkRep();
    	String expected = 
    			"....\n"
    			+ "....\n"
    			+"....\n"
    			+"....\n";
    	String actual = sudoku.toString();
    	assertEquals(expected, actual);
    }
    
    // init sudoku with dimension 3  
    @Test
    public void sudokuConsTest02(){
    	Sudoku sudoku = new Sudoku(3);
    	sudoku.checkRep();
    	
    	String expected = 
    			".........\n"
    			+ ".........\n"
    			+".........\n"
    			+".........\n"
    			+ ".........\n"
    			+".........\n"
    			+ ".........\n"
    			+".........\n"
    			+ ".........\n";
    	String actual = sudoku.toString();
    	assertEquals(expected, actual);
    }
    
    // init sudoku with dimension 4
    @Test(expected=AssertionError.class)
    public void sudokuConsTest03(){
    	Sudoku sudoku = new Sudoku(4);
    	sudoku.checkRep();
    }
    
    
    @Test
    // init sudoku with dimension 2
    //            .... 
    //            .31. 
    //            ...1
    //            1.2.
    public void sudokuDim2Test1(){
    	Sudoku sudoku = new Sudoku(2, new int [][]{
    								  new int [] {0,0,0,0},
					    		      new int [] {0,3,1,0},
					    			  new int [] {0,0,0,1},
					    			  new int [] {1,0,2,0},
					    			});
    	sudoku.checkRep();
    	
    	String expected = "....\n"+
    					".31.\n"+
    					"...1\n"+
    					"1.2.\n";
    	String actual = sudoku.toString();
    	assertEquals(expected, actual);
    	
    }
    
    
    @Test(expected=AssertionError.class)
    // init sudoku with dimension 2 
    //  digit is not within 0-size
    //            ..20. 
    //            .31. 
    //            ...1
    //            1.2.
    public void sudokuRITest1(){
    	Sudoku sudoku = new Sudoku(2, new int [][]{
    								  new int [] {0,0,20,0},
					    		      new int [] {0,3,1,0},
					    			  new int [] {0,0,0,1},
					    			  new int [] {1,0,2,0},
					    			});
    	sudoku.checkRep();
    	
    }
    
    @Test(expected=AssertionError.class)
    // init sudoku with dimension 2 
    //  digit is not within 0-size
    //            ..-1. 
    //            .31. 
    //            ...1
    //            1.2.
    public void sudokuRITest2(){
    	Sudoku sudoku = new Sudoku(2, new int [][]{
    								  new int [] {0,0,-1,0},
					    		      new int [] {0,3,1,0},
					    			  new int [] {0,0,0,1},
					    			  new int [] {1,0,2,0},
					    			});
    	sudoku.checkRep();
    }
    
    
    @Test(expected=AssertionError.class)
    // init sudoku with dimension 2 
    //  one of the digit is null
    // size is not equal to dim*dim
    //            ..-1. 
    //            .31. 
    //            ...1
    //            1.2.
    public void sudokuRITest3(){
    	Sudoku sudoku = new Sudoku(2, new int [][]{
    								  new int [] {0,0,0,9},
					    		      new int [] {0,3,1,0},
					    			  new int [] {0,0,0,1},
					    			  new int [] {1,0,2,0},
					    			});
    	sudoku.checkRep();
    }
    
    @Test(expected=ArrayIndexOutOfBoundsException.class)
    // init sudoku with dimension 3 but with size 4 
    //  size is not equal to dim*dim
    //            ..3. 
    //            .31. 
    //            ...1
    //            1.2.
    public void sudokuRITest4(){
    	Sudoku sudoku = new Sudoku(3, new int [][]{
    								  new int [] {0,0,3,0},
					    		      new int [] {0,3,1,0},
					    			  new int [] {0,0,0,1},
					    			  new int [] {1,0,2,0},
					    			});
    	sudoku.checkRep();
    }
    
    
    
    
    @Test
    // init sudoku with dimension 3
    //            ..7.345.. 
    //            .31.....5 
    //            ...1792..
    //            1.2.86354
    //            ..5.....7
    //            .13......
    //            .96.....1
    //            .45..2..6
    //            .5.......
    
    public void sudokuDim3Test1(){
    	Sudoku sudoku = new Sudoku(3, new int [][]{
    								  new int [] {0,0,7,0,3,4,5,0,0},
					    		      new int [] {0,3,1,0,0,0,0,0,5},
					    			  new int [] {0,0,0,1,7,9,2,0,0},
					    			  new int [] {1,0,2,0,8,6,3,5,4},
					    			  new int [] {0,0,5,0,0,0,0,0,7},
					    			  new int [] {0,1,3,0,0,0,0,0,0},
					    			  new int [] {0,9,6,0,0,0,0,0,1},
					    			  new int [] {0,4,5,0,0,2,0,0,6},
					    			  new int [] {0,5,0,0,0,0,0,0,0},
					    			});
    	sudoku.checkRep();
    	String expected = 
				"..7.345..\n"+
				".31.....5\n"+
				"...1792..\n"+
				"1.2.86354\n"+
				"..5.....7\n"+
				".13......\n"+
				".96.....1\n"+
				".45..2..6\n"+
				".5.......\n";
				
    	String actual = sudoku.toString();
    	assertEquals(expected, actual);
    }
    
    
    @Test(expected=AssertionError.class)
    // init sudoku with dimension 3
    //  digit out of range
    //            ..10.345.. 
    //            .31.....5 
    //            ...1-192..
    //            1.2.86354
    //            ..5.....7
    //            .13......
    //            .96.....1
    //            .45..2..6
    //            .5.......
    
    public void sudokuDim3Test2(){
    	Sudoku sudoku = new Sudoku(3, new int [][]{
    								  new int [] {0,0,10,0,3,4,5,0,0},
					    		      new int [] {0,3,1,0,0,0,0,0,5},
					    			  new int [] {0,0,0,1,-1,9,2,0,0},
					    			  new int [] {1,0,2,0,8,6,3,5,4},
					    			  new int [] {0,0,5,0,0,0,0,0,7},
					    			  new int [] {0,1,3,0,0,0,0,0,0},
					    			  new int [] {0,9,6,0,0,0,0,0,1},
					    			  new int [] {0,4,5,0,0,2,0,0,6},
					    			  new int [] {0,5,0,0,0,0,0,0,0},
					    			});
    	sudoku.checkRep();
    }
    
    // read content from 2x2 dimension txt file
    @Test
    public void FromFileTest1() throws IOException, ParseException{
    	
    	Sudoku sudoku = Sudoku.fromFile(2, "sudoku_4x4.txt");
    	String expected = ".234\n"
    			          +"341.\n"
    			          +"214.\n"
    			          +".321\n";
    	String actual = sudoku.toString();
    	assertEquals(expected, actual);
    	
    }
    
    @Test
    public void FormFileTest2() throws IOException, ParseException {
    	
    	Sudoku sudoku = Sudoku.fromFile(3, "sudoku_easy.txt");
    	String expected = "2..1.5..3\n"
						+".54...71.\n"
						+".1.2.3.8.\n"
						+"6.28.73.4\n"
						+".........\n"
						+"1.53.98.6\n"
						+".2.7.1.6.\n"
						+".81...24.\n"
						+"7..4.2..1\n";
    	String actual = sudoku.toString();

    	assertEquals(expected, actual);
    }
    
    @Test
    public void FormFileTest3() throws IOException, ParseException {
    	
    	Sudoku sudoku =  Sudoku.fromFile(3, "sudoku_easy2.txt");
    	
    	String expected = "713254968\n"
						+"9623875.1\n"
						+"84.961723\n"
						+"358612497\n"
						+"621479835\n"
						+"479538612\n"
						+"59472.186\n"
						+"28.196354\n"
						+"1368452.9\n";
    	String actual = sudoku.toString();
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testEmptyTwoDimFromFile() throws IOException,
    	ParseException {
    	String filename = "sudoku_4x4_empty.txt";
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
    	String filename = "sudoku_4x4.txt";
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
    	String filename = "sudoku_9x9_empty.txt";
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
    	String filename = "sudoku_hard2.txt";
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