package piwords;

import static org.junit.Assert.*;

import org.junit.Test;

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
    
    
    
    // base = 1
    // alphabet.length = 0 (<=> alphabet = {})    
    // digits[i] < 0
    @Test
    public void convertDigitsToStringTest001(){
    	int[] input = {-5, -3, -2, -1};
    	char[] alphabet = {};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 1, alphabet));
    }
    
    // 0 <= digits[i] < base
    @Test
    public void convertDigitsToStringTest002(){
    	int[] input = {0, 0, 0, 0};
    	char[] alphabet = {};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 1, alphabet));
    }
    
    // digits[i] >= base
    @Test
    public void convertDigitsToStringTest003(){
    	int[] input = {1, 2, 3, 4};
    	char[] alphabet = {};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 1, alphabet));
    }
    
       
    // alphabet.length = base (<=> alphabet = {x})   
    // digits[i] < 0
    @Test
    public void convertDigitsToStringTest004(){
    	int[] input = {-5, -3, -2, -1};
    	char[] alphabet = {'a'};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 1, alphabet));
    }
    
    // 0 <= digits[i] < base
    @Test
    public void convertDigitsToStringTest005(){
    	int[] input = {0, 0, 0, 0};
    	char[] alphabet = {'a'};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 1, alphabet));
    }
    
    // digits[i] >= base
    @Test
    public void convertDigitsToStringTest006(){
    	int[] input = {1, 2, 3, 4};
    	char[] alphabet = {'a'};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 1, alphabet));
    }
    
    
    // alphabet.length > base (e.g. alphabet = {'a', 'b'})   
    // digits[i] < 0
    @Test
    public void convertDigitsToStringTest007(){
    	int[] input = {-5, -3, -2, -1};
    	char[] alphabet = {'a', 'b'};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 1, alphabet));
    }
    
    // 0 <= digits[i] < base
    @Test
    public void convertDigitsToStringTest008(){
    	int[] input = {0, 0, 0, 0};
    	char[] alphabet = {'a', 'b'};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 1, alphabet));
    }
    
    // digits[i] >= base
    @Test
    public void convertDigitsToStringTest009(){
    	int[] input = {1, 2, 3, 4};
    	char[] alphabet = {'a', 'b'};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 1, alphabet));
    }
    
    
    
    // base = 4
    // alphabet.length = 0 (<=> alphabet = {})
    // digits[i] < 0
    @Test
    public void convertDigitsToStringTest010(){
    	int[] input = {-5, -3, -2, -1};
    	char[] alphabet = {};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 4, alphabet));
    }
    
    // 0 <= digits[i] < base
    @Test
    public void convertDigitsToStringTest011(){
    	int[] input = {0, 1, 2, 3};
    	char[] alphabet = {};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 4, alphabet));
    }
    
    // digits[i] >= base
    @Test
    public void convertDigitsToStringTest012(){
    	int[] input = {4, 9, 120, 256};
    	char[] alphabet = {};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 4, alphabet));
    } 
    
    
    // alphabet.length = base (e.g. alphabet = {'a', 'b', 'c', 'd'})
    // digits[i] < 0
    @Test
    public void convertDigitsToStringTest013(){
    	int[] input = {-5, -3, -2, -1};
    	char[] alphabet = {'a', 'b', 'c', 'd'};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 4, alphabet));
    }
    
    // 0 <= digits[i] < base
    @Test
    public void convertDigitsToStringTest014(){
    	char[] alphabet = {'a', 'b', 'c', 'd'};
    	
    	int[] input1 = {0, 1, 2, 3};
    	String expectedOutput1 = "abcd";
    	assertEquals(expectedOutput1,
    			DigitsToStringConverter.convertDigitsToString(
    					input1, 4, alphabet));
    	
    	int[] input2 = {2, 0, 1, 3};
    	String expectedOutput2 ="cabd";
    	assertEquals(expectedOutput2,
    			DigitsToStringConverter.convertDigitsToString(
    					input2, 4, alphabet));
    	
    	int[] input3 = {3, 1, 0, 2};
    	String expectedOutput3 ="dbac";
    	assertEquals(expectedOutput3,
    			DigitsToStringConverter.convertDigitsToString(
    					input3, 4, alphabet));
    }
    
    // digits[i] >= base
    @Test
    public void convertDigitsToStringTest015(){
    	int[] input = {4, 9, 120, 256};
    	char[] alphabet = {'a', 'b', 'c', 'd'};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 4, alphabet));
    }
    

    // alphabet.length > base (e.g. alphabet = {'a', 'b', 'c', 'd', 'e'})
    // digits[i] < 0
    @Test
    public void convertDigitsToStringTest016(){
    	int[] input = {-5, -3, -2, -1};
    	char[] alphabet = {'a', 'b', 'c', 'd', 'e'};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 4, alphabet));
    }
    
    // 0 <= digits[i] < base
    @Test
    public void convertDigitsToStringTest017(){
    	int[] input = {0, 1, 2, 3};
    	char[] alphabet = {'a', 'b', 'c', 'd', 'e'};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 4, alphabet));
    }
    
    // digits[i] >= base
    @Test
    public void convertDigitsToStringTest018(){
    	int[] input = {4, 9, 120, 256};
    	char[] alphabet = {'a', 'b', 'c', 'd', 'e'};
    	String expectedOutput = null;
    	assertEquals(expectedOutput,
    			DigitsToStringConverter.convertDigitsToString(
    					input, 4, alphabet));
    }
}
