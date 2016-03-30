package minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {
	
	// make sure assertions are turned on!  
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
    
    @Test
    /*
     * Construct a Board with untouched square state 
     */
    public void BoardTest1(){
    	Bomb b01 = new Bomb(0, 1, SquareState.UNTOUCHED);
    	Bomb b11 = new Bomb(1, 1, SquareState.UNTOUCHED);
    	NotBomb nb10 = new NotBomb(1, 0, 0, SquareState.UNTOUCHED);
    	NotBomb nb00 = new NotBomb(1, 0, 0, SquareState.UNTOUCHED);
    	
    	Square[][] squares = new Square[2][2];
    	squares[0][0] = nb00;
    	squares[1][0] = nb10;
    	squares[1][1] = b11;
    	squares[0][1] = b01;
    	
    	Board board = new Board(2, squares);
    	assertEquals(board.toString(),"--\n"
    			+ "--\n");
    	
    }
    
    
    
    @Test
    /*
     * Construct a Board with a non-bomb dug 
     */
    public void BoardTest2(){
    	Bomb b01 = new Bomb(0, 1, SquareState.UNTOUCHED);
    	Bomb b11 = new Bomb(1, 1, SquareState.UNTOUCHED);
    	NotBomb nb10 = new NotBomb(1, 0, 0, SquareState.DUG);
    	NotBomb nb00 = new NotBomb(1, 0, 0, SquareState.UNTOUCHED);
    	
    	Square[][] squares = new Square[2][2];
    	squares[0][0] = nb00;
    	squares[1][0] = nb10;
    	squares[1][1] = b11;
    	squares[0][1] = b01;
    	
    	Board board = new Board(2, squares);
    	board.Dug(1, 0);
    	assertEquals(board.toString(),"--\n"
    			+ "2-\n");
    	
    }
    
    
    @Test
    /*
     * Construct a Board with a non-bomb dug 
     */
    public void BoardTest3(){
    	Bomb b01 = new Bomb(0, 1, SquareState.UNTOUCHED);
    	Bomb b11 = new Bomb(1, 1, SquareState.UNTOUCHED);
    	NotBomb nb10 = new NotBomb(1, 0, 0, SquareState.UNTOUCHED);
    	NotBomb nb00 = new NotBomb(1, 0, 0, SquareState.UNTOUCHED);
    	Bomb b02 = new Bomb(0, 1, SquareState.UNTOUCHED);
    	Bomb b12 = new Bomb(1, 1, SquareState.UNTOUCHED);
    	NotBomb nb20 = new NotBomb(1, 0, 0, SquareState.UNTOUCHED);
    	NotBomb nb22 = new NotBomb(1, 0, 0, SquareState.UNTOUCHED);
    	Bomb b21 = new Bomb(0, 1, SquareState.UNTOUCHED);
    	
    	
    	Square[][] squares = new Square[3][3];
    	squares[0][0] = nb00;
    	squares[1][0] = nb10;
    	squares[1][1] = b11;
    	squares[0][1] = b01;
    	squares[0][2] = b02;
    	squares[2][1] = b21;
    	squares[2][0] = nb20;
    	squares[2][2] = nb22;
    	squares[1][2] = b12; 
    	
    	Board board = new Board(3, squares);
    	Board newBoard  = 	board.Dug(1, 0).Dug(2, 0).Dug(2, 2);
    	
    	
    	assertEquals("---\n"
    			+"3--\n"
    			+ "2-3\n",newBoard.toString());
    	
    }
    
    
    @Test
    /*
     * Construct a Board with flagged or dug square 
     */
    public void BoardTest4(){
    	Bomb b01 = new Bomb(0, 1, SquareState.UNTOUCHED);
    	Bomb b11 = new Bomb(1, 1, SquareState.UNTOUCHED);
    	NotBomb nb10 = new NotBomb(1, 0, 0, SquareState.UNTOUCHED);
    	NotBomb nb00 = new NotBomb(1, 0, 0, SquareState.UNTOUCHED);
    	Bomb b02 = new Bomb(0, 1, SquareState.UNTOUCHED);
    	Bomb b12 = new Bomb(1, 1, SquareState.UNTOUCHED);
    	NotBomb nb20 = new NotBomb(1, 0, 0, SquareState.UNTOUCHED);
    	NotBomb nb22 = new NotBomb(1, 0, 0, SquareState.UNTOUCHED);
    	Bomb b21 = new Bomb(0, 1, SquareState.UNTOUCHED);
    	
    	
    	Square[][] squares = new Square[3][3];
    	squares[0][0] = nb00;
    	squares[1][0] = nb10;
    	squares[1][1] = b11;
    	squares[0][1] = b01;
    	squares[0][2] = b02;
    	squares[2][1] = b21;
    	squares[2][0] = nb20;
    	squares[2][2] = nb22;
    	squares[1][2] = b12; 
    	
    	Board board = new Board(3, squares);
    	Board newBoard  = 	board.Dug(1, 0).Dug(2, 0).Dug(2, 2).flag(2, 1);

    	assertEquals("---\n"
    			+"3--\n"
    			+ "2F3\n",newBoard.toString());
    	
    }
    
    /*
     * Test a board constructor without provided squares 
     */
    @Test
    public void BoardWithDimOnly(){
    	Board board = new Board(3);
    	assertEquals("---\n"
    			+"---\n"
    			+ "---\n", board.toString());
    }
    
    
    /*
     * Test a board constructor with a provided file name boardtest1
     */
    @Test
    public void BoardWithFile1() throws Exception{
    	Board board = new Board("boardtest1.txt");
    	assertEquals("----\n"
    			+"----\n"
    			+"----\n"
    			+ "----\n", board.toString());
    }
   
    
    /*
     * Test a board constructor with a provided file name boardtest2
     */
    @Test
    public void BoardWithFile2() throws Exception{
    	Board board = new Board("boardtest2.txt");
    	assertEquals("-------\n"
    			+"-------\n"
    			+"-------\n"
    			+"-------\n"
    			+"-------\n"
    			+"-------\n"
    			+"-------\n", board.toString());
    }
}
