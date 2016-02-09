package piwords;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.corba.se.spi.orbutil.fsm.Input;

public class DigitsToStringConverterTest {
    @Test
    public void basicNumberSerializerTest() {
        // Input is a 4 digit number, 0.123 represented in base 4
        int[] input = {0, 1, 2, 3};

        // Want to map 0 -> "d", 1 -> "c", 2 -> "b", 3 -> "a"
        char[] alphabet = {'d', 'c', 'b', 'a'};

        String expectedOutput = "dcba";
        assertEquals(expectedOutput,
                     DigitsToStringConverter.convertDigitsToString(
                             input, 4, alphabet));
    }

    // TODO: Write more tests (Problem 3.a)
    
    /*
     * * If digits[i] >= base or digits[i] < 0 for any i, consider the input
     * invalid, and return null.
     * If alphabet.length != base, consider the input invalid, and return null.
     */
    
    @Test 
    // digit[i] = 0 
    public  void nullNumberSerializeTest0(){
    	int[] input ={0,0,0,0};
    	char[] alphabet = {'a','b','c','d'};
    	
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 2, alphabet));
    	
    }
    
    @Test 
    // digit[i] = 0 , alphabet > base
    public  void nullNumberSerializeTest01(){
    	int[] input ={0,0,0,0};
    	char[] alphabet = {'a','b','c','d'};
    	
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 1, alphabet));
    	
    }
    
    @Test 
    // digit[i] = 0 , alphabet < base
    public  void nullNumberSerializeTest02(){
    	int[] input ={0,0,0,0};
    	char[] alphabet = {'a','b'};
    	
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 3, alphabet));
    	
    }
    
    @Test 
    // digit[i] = 0 , alphabet < base
    public  void nullNumberSerializeTest03(){
    	int[] input ={0,0,0,0};
    	char[] alphabet = {'a'};
    	
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 2, alphabet));
    	
    }
    
    @Test 
    // digit[i] = 0 , alphabet = null
    public  void nullNumberSerializeTest04(){
    	int[] input ={0,0,0,0};
    	char[] alphabet = {};
    	
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 2, alphabet));
    	
    }
    
    //  digit[i] > base 
    @Test
    public  void nullNumberSerializeTest1(){
    	int[] input ={1,2,3,5};
    	char[] alphabet = {'a','b','c','d'};
    	
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 4, alphabet));
    	
    }
    @Test
    //  digit[i] > base
    public  void nullNumberSerializeTest2(){
    	int[] input ={0,2,10,5};
    	char[] alphabet = {'a','b','c','d'};
    	
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 4, alphabet));
    	
    }
    
    @Test
    // digit[i] > base and alphabet.lenght < base
    public  void nullNumberSerializeTest3(){
    	int[] input ={1,2,10,5};
    	char[] alphabet = {'a','b','c','d'};
    	
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 5, alphabet));
    	
    }
    
    @Test
    // digit[i] > base and alphabet.lenght > base
    public  void nullNumberSerializeTest31(){
    	int[] input ={1,2,10,5};
    	char[] alphabet = {'a','b','c','d','e','f'};
    	
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 5, alphabet));
    	
    }
    
    @Test
    // alphabet < base
    public  void nullNumberSerializeTest4(){
    	int[] input ={1,2,10,5};
    	char[] alphabet = {'a','b','c','d'};
    	
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 10, alphabet));
    	
    }
    
    @Test
    // digit[i] < 0 
    public  void nullNumberSerializeTest5(){
    	int[] input ={-1,-2,-10,-5};
    	char[] alphabet = {'a','b','c','d'};
    	
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 4, alphabet));
    	
    }
    
    @Test
    // digit[i] < 0 and alphabet > base
    public  void nullNumberSerializeTest6(){
    	int[] input ={-1,-2,-10,-5};
    	char[] alphabet = {'a','b','c','d','e'};
    	
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 4, alphabet));
    	
    }
    
    @Test
    // digit[i] < 0 and alphabet= null
    public  void nullNumberSerializeTest7(){
    	int[] input ={1,-2,-10,-5};
    	char[] alphabet = {};
    	
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 4, alphabet));
    	
    }
    
    @Test
    //   digit[i] < 0 and alphabet < base
    public  void nullNumberSerializeTest8(){
    	int[] input ={-1,-2,-10,-5};
    	char[] alphabet = {'a','b','c','d','e'};
    	
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 10, alphabet));
    	
    }
    @Test
    // 0< digit[i] < base , alphabet = base
    public void correctConvertDigitToStringTest(){
    	char [] alphabet = {'a','b','c','d'};
    	
    	int[] input1 = {0,1,2,3};
    	String expectedOutput = "abcd";
    	assertEquals(expectedOutput, DigitsToStringConverter.convertDigitsToString(
    			input1, 4, alphabet));
    	
    	int[] input2 = {2,3,0,1};
    	String expectedOutput1 = "cdab";
    	assertEquals(expectedOutput1, DigitsToStringConverter.convertDigitsToString(
    			input2, 4, alphabet));
    	
    	int[] input3 = {3, 1, 0, 2};
    	String expectedOutput3 ="dbac";
    	assertEquals(expectedOutput3,
    			DigitsToStringConverter.convertDigitsToString(
    					input3, 4, alphabet));
    }
    
}
