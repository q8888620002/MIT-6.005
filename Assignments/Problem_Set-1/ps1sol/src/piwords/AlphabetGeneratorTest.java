package piwords;

import static org.junit.Assert.*;

import org.junit.Test;

public class AlphabetGeneratorTest {
	
    @Test
    public void generateFrequencyAlphabetTest() {
        // Expect in the training data that Pr(a) = 2/5, Pr(b) = 2/5,
        // Pr(c) = 1/5.
        String[] trainingData = {"aa", "bbc"};
        // In the output for base 10, they should be in the same proportion.
        char[] expectedOutput = {'a', 'a', 'a', 'a',
                                 'b', 'b', 'b', 'b',
                                 'c', 'c'};
        assertArrayEquals(expectedOutput,
                AlphabetGenerator.generateFrequencyAlphabet(
                        10, trainingData));
    }
    
    
    
    // trainningData = {}, generateFrequencyAlphabet should return null.   
    @Test
    public void generateFrequencyAlphabetTest001() {
    	String[] trainingData = {};
    	char[] expectedOutput = null;
    	// base = -1
    	assertArrayEquals(expectedOutput,
                AlphabetGenerator.generateFrequencyAlphabet(
                        -1, trainingData));
    }
    
    @Test
    public void generateFrequencyAlphabetTest002() {
    	String[] trainingData = {};
    	char[] expectedOutput = null;
    	// base = 1
    	assertArrayEquals(expectedOutput,
                AlphabetGenerator.generateFrequencyAlphabet(
                        1, trainingData));
    }
    
    @Test
    public void generateFrequencyAlphabetTest003() {
    	String[] trainingData = {};
    	char[] expectedOutput = null;
    	// base = 64
    	assertArrayEquals(expectedOutput,
                AlphabetGenerator.generateFrequencyAlphabet(
                        64, trainingData));
    }

    // trainningData = {<single-string>}    
    @Test
    public void generateFrequencyAlphabetTest004() {
    	String[] trainingData = {"a single string"};
    	char[] expectedOutput = null;
    	// base = -1, generateFrequencyAlphabet should return null.
    	assertArrayEquals(expectedOutput,
                AlphabetGenerator.generateFrequencyAlphabet(
                        -1, trainingData));
    }
    
    @Test
    public void generateFrequencyAlphabetTest005() {
    	String[] trainingData = {"a single string"};
    	char[] expectedOutput = null;
    	// base = 1, generateFrequencyAlphabet should return null.
    	assertArrayEquals(expectedOutput,
                AlphabetGenerator.generateFrequencyAlphabet(
                        1, trainingData));
    }
    
    @Test
    public void generateFrequencyAlphabetTest006() {
    	// Expect in the training data that Pr(a) = 1/13, Pr(e) = 1/13,
    	// Pr(g) = 2/13, Pr(i) = 2/13, Pr(l) = 1/13, Pr(n) = 2/13,
    	// Pr(r) = 1/13, Pr(s) = 2/13, Pr(t) = 1/13.
    	String[] trainingData = {"a single string"};
    	
    	/*
    	 * expectedOutput = {"a", "a", ... (5 As, indexes 0-4),
    	 * 					 "e", "e", ... (5 Es, indexes 5-9),
    	 *					 "g", "g", ... (10 Gs, indexes 10-19),
    	 *					 "i", "i", ... (10 Is, indexes 20-29),
    	 *					 "l", "l", ... (4 Ls, indexes 30-33),
    	 *					 "n", "n", ... (10 Is, indexes 34-43),
    	 *					 "r", "r", ... (5 Is, indexes 44-48),
    	 *					 "s", "s", ... (10 Ss, indexes 49-58),
    	 *					 "t", "t", ... (5 Ts, indexes 59-63)}
    	 * */
    	char[] expectedOutput = {'a','a','a','a','a',
    							 'e','e','e','e','e',
    							 'g','g','g','g','g','g','g','g','g','g',
    							 'i','i','i','i','i','i','i','i','i','i',
    							 'l','l','l','l',
    							 'n','n','n','n','n','n','n','n','n','n',
    							 'r','r','r','r','r',
    							 's','s','s','s','s','s','s','s','s','s',
    							 't','t','t','t','t'};
    	
    	// base = 64
    	assertArrayEquals(expectedOutput,
                AlphabetGenerator.generateFrequencyAlphabet(
                        64, trainingData));
    }
    
    
    
    // trainningData = {<s1>, <s2>, ...}
    @Test
    public void generateFrequencyAlphabetTest007() {
    	String[] trainingData = {"1+1=2","aabb","CcDd","E@@E"};
    	char[] expectedOutput = null;
    	// base = -1, generateFrequencyAlphabet should return null.
    	assertArrayEquals(expectedOutput,
                AlphabetGenerator.generateFrequencyAlphabet(
                        -1, trainingData));
    }
    
    @Test
    public void generateFrequencyAlphabetTest008() {
    	String[] trainingData = {"1+1=2","aabb","CcDd","E@@E"};
    	char[] expectedOutput = null;
    	// base = 1, generateFrequencyAlphabet should return null.
    	assertArrayEquals(expectedOutput,
                AlphabetGenerator.generateFrequencyAlphabet(
                        1, trainingData));
    }

    @Test
    public void generateFrequencyAlphabetTest009() {
    	// Expect in the training data that Pr(a) = 1/5, Pr(b) = 1/5,
    	// Pr(c) = 1/5, Pr(d) = 1/5, Pr(e) = 1/5.
    	String[] trainingData = {"1+1=2","aabb","CcDd","E@@E"};
    	
    	/*
    	 * expectedOutput = {"a", "a", ... (13 As, indexes 0-12),
    	 * 					 "b", "b", ... (13 Bs, indexes 13-25),
    	 * 					 "c", "c", ... (12 Cs, indexes 26-37),
    	 * 					 "d", "d", ... (13 Ds, indexes 38-50),
    	 * 					 "e", "e", ... (13 Bs, indexes 51-63)}
    	 * */
    	
    	char[] expectedOutput = {'a','a','a','a','a','a','a','a','a','a','a','a','a',
    							 'b','b','b','b','b','b','b','b','b','b','b','b','b',
    							 'c','c','c','c','c','c','c','c','c','c','c','c',
    							 'd','d','d','d','d','d','d','d','d','d','d','d','d',
    							 'e','e','e','e','e','e','e','e','e','e','e','e','e'};
    	// base = 64
    	assertArrayEquals(expectedOutput,
                AlphabetGenerator.generateFrequencyAlphabet(
                        64, trainingData));
    }
}
