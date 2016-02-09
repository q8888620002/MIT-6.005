package piwords;

import static org.junit.Assert.*;

import org.junit.Test;

public class PiGeneratorTest {
    @Test
    public void basicPowerModTest() {
        // 5^7 mod 23 = 17
        
        assertEquals(17, PiGenerator.powerMod(5, 7, 23));
    }
    @Test
    public void advancedPowerModTest(){
    
    	// TODO: Write more tests (Problem 1.a, 1.c)
    	
    	/*
    	 * a^b > m  
    	 * m <= a^b 
    	 * (int) a : Neg, 0 , Pos  
    	 * (int) b : Neg, 0 , Pos 
    	 * (int) m : Neg, Pos 
    	 */
    	
    	// 1^1 mod 1 = 0
    	assertEquals(0 ,PiGenerator.powerMod(1 , 1 , 1));
    	// 0^1 mod 1 = 0
    	assertEquals(0, PiGenerator.powerMod(0, 1 ,1));
    	 // 1^0 mod 1 = 0
    	assertEquals(0, PiGenerator.powerMod(1, 0 , 1 ));
    	// -1 ^ -1 mod 1 = -1
    	assertEquals(-1, PiGenerator.powerMod(-1, -1 , 1 ));
    	// -1^-1 mod -1 = -1
    	assertEquals(-1, PiGenerator.powerMod(-1, -1 , -1 ));
    	//  -1 ^ 0 mod 1 = -1
    	assertEquals(-1, PiGenerator.powerMod(-1, 0 , 1 ));
    	// 0^-1 mod 1 = -1
    	assertEquals(-1, PiGenerator.powerMod(0 , -1 , 1 ));
    	// -1^0 mod -1 = -1
    	assertEquals(-1, PiGenerator.powerMod(-1, 0 , -1 ));
    	// 0^-1 mod -1 = -1
    	assertEquals(-1, PiGenerator.powerMod(0, -1 , -1 ));
    	
    	assertEquals(-1, PiGenerator.powerMod(-1, -1 , 16 ));
    	
    	assertEquals(-1, PiGenerator.powerMod(-1, -2 , 16 ));
    	
    	assertEquals(-1, PiGenerator.powerMod(-2, -1 , 16 ));
    	
    	
    	
    	assertEquals(-1, PiGenerator.powerMod(0, -1 , 16 ));
    	
    	assertEquals(-1, PiGenerator.powerMod(0, 1 , -16 ));
    	
    	assertEquals(-1, PiGenerator.powerMod(0, -1 , -16 ));
    	
    	assertEquals(-1, PiGenerator.powerMod(-1, 0 , 1 ));
    	
    	assertEquals(-1, PiGenerator.powerMod(-1, 0 , 16 ));
    	
    	assertEquals(-1, PiGenerator.powerMod(0, 0 , -1 ));
    	
    	assertEquals(-1, PiGenerator.powerMod(-1, 0 , 16 ));
    	
    	assertEquals(-1, PiGenerator.powerMod(-1, 0 , 1 ));
    	
    	assertEquals(-1, PiGenerator.powerMod(-1, 1 , 16 ));
    	
    	assertEquals(-1, PiGenerator.powerMod( 1, -1 , 16 ));
    	
    	assertEquals(-1, PiGenerator.powerMod(0, -1 , 16 ));
    	
    	assertEquals(-1, PiGenerator.powerMod(8, -1, -1));
    	// 8^(-1) mod 1 = -1
    	assertEquals(-1, PiGenerator.powerMod(8, -1, 1));
    	// 8^(-1) mod 16 = -1
    	assertEquals(-1, PiGenerator.powerMod(8, -1, 16));
    	
    	// 8^0 mod (-1) = -1
    	assertEquals(-1, PiGenerator.powerMod(8, 0, -1));
    	// 8^0 mod 1 = 0
    	assertEquals(0, PiGenerator.powerMod(8, 0, 1));
    	// 8^0 mod 16 = 1
    	assertEquals(1, PiGenerator.powerMod(8, 0, 16));
    	
    	// 8^1 mod (-1) = -1
    	assertEquals(-1, PiGenerator.powerMod(8, 1, -1));
    	// 8^1 mod 1 = 0
    	assertEquals(0, PiGenerator.powerMod(8, 1, 1));
    	// 8^1 mod 16 = 8
    	assertEquals(8, PiGenerator.powerMod(8, 1, 16));
    	
    	// 8^5 mod (-1) = -1
    	assertEquals(-1, PiGenerator.powerMod(8, 5, -1));
    	// 8^5 mod 1 = 0
    	assertEquals(0, PiGenerator.powerMod(8, 5, 1));
    	// 8^5 mod 16 = 0
    	assertEquals(0, PiGenerator.powerMod(8, 5, 16));
    }
    @Test
    public void piDigitTest(){
    	// n = -1 , return -5
    	assertEquals(-1, PiGenerator.piDigit(-5));
    	// n = 1 , return 2
    	assertEquals(2, PiGenerator.piDigit(1));
    	// n = 0, return 0x03
    	assertEquals(0x03, PiGenerator.piDigit(0));
    	// n = 1, return 0x02
    	assertEquals(0x02, PiGenerator.piDigit(1));
    	// n = 8, return 0x08
    	assertEquals(0x08, PiGenerator.piDigit(8));
    }
    
    @Test
    public void computePiInHexTest(){
    	// precision = -10, return null
    	assertArrayEquals(null, PiGenerator.computePiInHex(-10));
    	// precision = -1, return null
    	assertArrayEquals(null, PiGenerator.computePiInHex(-1));
    	// precision = 0, return null
    	assertArrayEquals(null, PiGenerator.computePiInHex(0));
    	
    	// precision = 1, return  2
    	assertArrayEquals(new int[]{2}, PiGenerator.computePiInHex(1));
    	//precision = 5 return 2,4,3,F,6
    	assertArrayEquals(new int[]{2,4,3,0x0F,6}, PiGenerator.computePiInHex(5));
    	
    	
    }
}
