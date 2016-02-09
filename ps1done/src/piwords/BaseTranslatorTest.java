package piwords;

import static org.junit.Assert.*;

import org.junit.Test;

public class BaseTranslatorTest {
    @Test
    public void basicBaseTranslatorTest() {
        // Expect that .01 in base-2 is .25 in base-10
        // (0 * 1/2^1 + 1 * 1/2^2 = .25)
        int[] input = {0, 1};
        int[] expectedOutput = {2, 5};
        assertArrayEquals(expectedOutput,
                          BaseTranslator.convertBase(input, 2, 10, 2));
    }

    @Test
    // TODO: Write more tests (Problem 2.a)
    // If digits[i] < 0 or digits[i] >= baseA for any i, return null
    
    // If baseA < 2, baseB < 2, or precisionB < 1, return null
    public void BaseTranslatorSingleDigitTest1(){
    	// digit [-1] ,baseA = 16 , base B =26, precision = 0
        int [] input = {-1};
        int [] expectedOutput = null;
        assertArrayEquals(expectedOutput, BaseTranslator.convertBase(input, 2, 10,0));
    }
    
    @Test
    
    public void BaseTranslatorSingleDigitTest2(){
    	// digit [1] ,baseA = 2 , base B =10, precision = 1
        int [] input = {1};
        int [] expectedOutput = {5};
        assertArrayEquals(expectedOutput, BaseTranslator.convertBase(input, 2, 10,1));
    }
    
    @Test
    
    public void BaseTranslatorSingleDigitTest3(){
    	// digit [0] ,baseA = 2 , base B =10, precision = 0
        int [] input = {0};
        int [] expectedOutput = {0};
        assertArrayEquals(expectedOutput, BaseTranslator.convertBase(input, 2, 10,1));
    }
    
    @Test
    
    public void BaseTranslatorSingleDigitTest4(){
    	// digit [3] ,baseA = 2 , base B =10, precision = 1
        int [] input = {3};
        int [] expectedOutput = null;
        assertArrayEquals(expectedOutput, BaseTranslator.convertBase(input, 2, 10,1));
    }
    
 // If baseA < 2, baseB < 2, or precisionB < 1, return null
    
    @Test
    
    public void BaseTranslatorSingleDigitTest5(){
    	// digit [1] ,baseA = 1 , base B =10, precision = 1
        int [] input = {1};
        int [] expectedOutput = null;
        assertArrayEquals(expectedOutput, BaseTranslator.convertBase(input, 1, 10,1));
    }
    
    @Test
    public void BaseTranslatorSingleDigitTest6(){
    	// digit [3] ,baseA = 1 , base B =10, precision = 1
        int [] input = {1};
        int [] expectedOutput = null;
        assertArrayEquals(expectedOutput, BaseTranslator.convertBase(input, 1, 10,1));
    }
    
    @Test
    public void BaseTranslatorSingleDigitTest7(){
    	// digit [3] ,baseA = 2 , base B =1, precision = 1
        int [] input = {1};
        int [] expectedOutput = null;
        assertArrayEquals(expectedOutput, BaseTranslator.convertBase(input, 2, 1,1));
    }
    
    @Test
    public void BaseTranslatorSingleDigitTest100(){
    	// digit [3] ,baseA = 2 , base B =1, precision = 0
        int [] input = {1};
        int [] expectedOutput = null;
        assertArrayEquals(expectedOutput, BaseTranslator.convertBase(input, 2, 10,0));
    }
    
    @Test
    public void BaseTranslatorSingleDigitTest8(){
    	// digit [3] ,baseA = 2 , base B =1, precision = 1
        int [] input = {1};
        int [] expectedOutput = null;
        assertArrayEquals(expectedOutput, BaseTranslator.convertBase(input, 2, 1,1));
    }
    
    
    @Test
    // digit[-1,2] , baseA = 16 , baseB = 26 , precision = 0
    public void BaseTranslatorTest3(){
    	int [] input = {-1, 2 };
    	int [] expect = null ;
    	assertArrayEquals(expect, BaseTranslator.convertBase(input, 16, 26, 0));
    }
    
    @Test
    // digit[1,-2] , baseA = 16 , baseB = 26 , precision = 0
    public void BaseTranslatorTest4(){
    	int [] input = {1, -2 };
    	int [] expect = null ;
    	assertArrayEquals(expect, BaseTranslator.convertBase(input, 16, 26, 0));
    }
    
    @Test
    // digit[-1,-2] , baseA = 16 , baseB = 26 , precision = 0
    public void BaseTranslatorTest5(){
    	int [] input = {-1, -2 };
    	int [] expect = null ;
    	assertArrayEquals(expect, BaseTranslator.convertBase(input, 16, 26, 0));
    }
    
    @Test
    // digit[-1,2,3] , baseA = 16 , baseB = 26 , precision = 0
    public void BaseTranslatorTest6(){
    	int [] input = {-1, 2, 3 };
    	int [] expect = null ;
    	assertArrayEquals(expect, BaseTranslator.convertBase(input, 16, 26, 0));
    }
    
    @Test
    // digit[-1,2,3,4] , baseA = 16 , baseB = 26 , precision = 0
    public void BaseTranslatorTest7(){
    	int [] input = {-1, 2,3,4 };
    	int [] expect = null ;
    	assertArrayEquals(expect, BaseTranslator.convertBase(input, 16, 26, 0));
    }
    
   
    
    @Test
    // digit[-1,2,3] , baseA = 16 , baseB = 26 , precision = 0
    public void BaseTranslatorTest8(){
    	int [] input = {-1, 2, 3 };
    	int [] expect = null ;
    	assertArrayEquals(expect, BaseTranslator.convertBase(input, 16, 26, 0));
    }
    
    @Test
    // digit[-1,2,3,4] , baseA = 16 , baseB = 26 , precision = 0
    public void BaseTranslatorTest9(){
    	int [] input = {-1, 2,3,4 };
    	int [] expect = null ;
    	assertArrayEquals(expect, BaseTranslator.convertBase(input, 16, 26, 0));
    }
    
}
