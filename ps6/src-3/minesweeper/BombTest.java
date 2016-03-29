package minesweeper;

import static org.junit.Assert.*;

import org.junit.Test;

public class BombTest {
	
	
		// make sure assertions are turned on!  
	    @Test(expected=AssertionError.class)
	    public void testAssertionsEnabled() {
	        assert false;
	    }

		
		@Test
		public void BombSetTest1(){
			Bomb bomb = new Bomb(1,1, SquareState.UNTOUCHED);
			assertEquals("I am a bomb at[1,1]",bomb.toString());
		}
		
		
		
		/*
		 * Test with illegal location [0,-1]
		 */
		@Test(expected=AssertionError.class)
		public void BombSetTest2(){
			Bomb bomb = new Bomb(0,-1, SquareState.UNTOUCHED);
			assertEquals("I am a bomb at[1,1]",bomb.toString());
		}
		
		
		/*
		 * Test with illegal location [-1,0]
		 */
		@Test(expected=AssertionError.class)
		public void BombSetTest3(){
			Bomb bomb = new Bomb(-1,0, SquareState.UNTOUCHED);
			assertEquals("I am a bomb at[1,1]",bomb.toString());
		}
		

		/*
		 * Test with illegal location [-1,-1]
		 */
		@Test(expected=AssertionError.class)
		public void BombSetTest4(){
			Bomb bomb = new Bomb(-1,-1, SquareState.UNTOUCHED);
			assertEquals("I am a bomb at[1,1]",bomb.toString());
		}
		
		
		@Test
		public void BombSetTest15(){
			Bomb bomb = new Bomb(10,2, SquareState.UNTOUCHED);
			assertEquals("I am a bomb at[10,2]",bomb.toString());
		}
		
		@Test
		public void BombDugTest1(){
			Bomb bomb = new Bomb(1,1, SquareState.UNTOUCHED);
			assertEquals("untouched",bomb.getState());
			assertEquals("dug",bomb.dug().getState());
		}
		
}
