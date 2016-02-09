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
    public void advancedPowerModTest() {
    	
    	// (-1)^(-1) mod (-1) = -1
    	assertEquals(-1, PiGenerator.powerMod(-1, -1, -1));
    	// (-1)^(-1) mod 1 = -1
    	assertEquals(-1, PiGenerator.powerMod(-1, -1, 1));
    	// (-1)^(-1) mod 16 = -1
    	assertEquals(-1, PiGenerator.powerMod(-1, -1, 16));
    	
    	// (-1)^0 mod (-1) = -1
    	assertEquals(-1, PiGenerator.powerMod(-1, 0, -1));
    	// (-1)^0 mod 1 = -1
    	assertEquals(-1, PiGenerator.powerMod(-1, 0, 1));
    	// (-1)^0 mod 16 = -1
    	assertEquals(-1, PiGenerator.powerMod(-1, 0, 16));
    	
    	// (-1)^1 mod (-1) = -1
    	assertEquals(-1, PiGenerator.powerMod(-1, 1, -1));
    	// (-1)^1 mod 1 = -1
    	assertEquals(-1, PiGenerator.powerMod(-1, 1, 1));
    	// (-1)^1 mod 16 = -1
    	assertEquals(-1, PiGenerator.powerMod(-1, 1, 16));
    	
    	// (-1)^5 mod (-1) = -1
    	assertEquals(-1, PiGenerator.powerMod(-1, 5, -1));
    	// (-1)^5 mod 1 = -1
    	assertEquals(-1, PiGenerator.powerMod(-1, 5, 1));
    	// (-1)^5 mod 16 = -1
    	assertEquals(-1, PiGenerator.powerMod(-1, 5, 16));
    	
    	
    	
    	// 0^(-1) mod (-1) = -1
    	assertEquals(-1, PiGenerator.powerMod(0, -1, -1));
    	// 0^(-1) mod 1 = -1
    	assertEquals(-1, PiGenerator.powerMod(0, -1, 1));
    	// 0^(-1) mod 16 = -1
    	assertEquals(-1, PiGenerator.powerMod(0, -1, 16));
    	
    	// 0^0 mod (-1) = -1
    	assertEquals(-1, PiGenerator.powerMod(0, 0, -1));
    	// 0^0 mod 1 = 0
    	assertEquals(0, PiGenerator.powerMod(0, 0, 1));
    	// 0^0 mod 16 = 0
    	assertEquals(0, PiGenerator.powerMod(0, 0, 16));
    	
    	// 0^1 mod (-1) = -1
    	assertEquals(-1, PiGenerator.powerMod(0, 1, -1));
    	// 0^1 mod 1 = 0
    	assertEquals(0, PiGenerator.powerMod(0, 1, 1));
    	// 0^1 mod 16 = 0
    	assertEquals(0, PiGenerator.powerMod(0, 1, 16));
    	
    	// 0^5 mod (-1) = -1
    	assertEquals(-1, PiGenerator.powerMod(0, 5, -1));
    	// 0^5 mod 1 = 0
    	assertEquals(0, PiGenerator.powerMod(0, 5, 1));
    	// 0^5 mod 16 = 0
    	assertEquals(0, PiGenerator.powerMod(0, 5, 16));
    	
    	
    	
    	// 1^(-1) mod (-1) = -1
    	assertEquals(-1, PiGenerator.powerMod(1, -1, -1));
    	// 1^(-1) mod 1 = -1
    	assertEquals(-1, PiGenerator.powerMod(1, -1, 1));
    	// 1^(-1) mod 16 = -1
    	assertEquals(-1, PiGenerator.powerMod(1, -1, 16));
    	
    	// 1^0 mod (-1) = -1
    	assertEquals(-1, PiGenerator.powerMod(1, 0, -1));
    	// 1^0 mod 1 = 0
    	assertEquals(0, PiGenerator.powerMod(1, 0, 1));
    	// 1^0 mod 16 = 1
    	assertEquals(1, PiGenerator.powerMod(1, 0, 16));
    	
    	// 1^1 mod (-1) = -1
    	assertEquals(-1, PiGenerator.powerMod(1, 1, -1));
    	// 1^1 mod 1 = 0
    	assertEquals(0, PiGenerator.powerMod(1, 1, 1));
    	// 1^1 mod 16 = 1
    	assertEquals(1, PiGenerator.powerMod(1, 1, 16));
    	
    	// 1^5 mod (-1) = -1
    	assertEquals(-1, PiGenerator.powerMod(1, 5, -1));
    	// 1^5 mod 1 = 0
    	assertEquals(0, PiGenerator.powerMod(1, 5, 1));
    	// 1^5 mod 16 = 1
    	assertEquals(1, PiGenerator.powerMod(1, 5, 16));
    	
    	
    	
    	// 8^(-1) mod (-1) = -1
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
    	// n = -5, return -0x01
    	assertEquals(-0x01, PiGenerator.piDigit(-5));
    	// n = 0, return 0x03
    	assertEquals(0x03, PiGenerator.piDigit(0));
    	// n = 1, return 0x02
    	assertEquals(0x02, PiGenerator.piDigit(1));
    	// n = 8, return 0x08
    	assertEquals(0x08, PiGenerator.piDigit(8));
    }
    
    @Test
    public void computePiInHexTest(){
    	
    	// precision = -5, return null
    	assertNull(PiGenerator.computePiInHex(-5));
    	// precision = -1, return null
    	assertNull(PiGenerator.computePiInHex(-1));
    	// precision = 0, return null
    	assertNull(PiGenerator.computePiInHex(0));
    	// precision = 1, return [0x02]
    	assertArrayEquals(new int[]{0x02}, PiGenerator.computePiInHex(1));
    	// precision = 3, return [0x02, 0x04, 0x03]
    	assertArrayEquals(new int[]{0x02, 0x04, 0x03}, PiGenerator.computePiInHex(3));
    	// precision = 20, return [0x02, 0x04, 0x03, 0x0F, 0x06, 0x0A, 0x08, 0x08, 0x08, 0x05,
    	//						   0x0A, 0x03, 0x00, 0x08, 0x0D, 0x03, 0x01, 0x03, 0x01, 0x09]
    	assertArrayEquals(new int[]{0x02, 0x04, 0x03, 0x0F, 0x06, 0x0A, 0x08, 0x08, 0x08, 0x05,
    								0x0A, 0x03, 0x00, 0x08, 0x0D, 0x03, 0x01, 0x03, 0x01, 0x09},
    					  PiGenerator.computePiInHex(20));
    }
}
