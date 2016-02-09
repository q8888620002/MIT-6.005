package piwords;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class WordFinderTest {
    @Test
    public void basicGetSubstringsTest() {
        String haystack = "abcde";
        String[] needles = {"ab", "abc", "de", "fg"};

        Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
        expectedOutput.put("ab", 0);
        expectedOutput.put("abc", 0);
        expectedOutput.put("de", 3);

        assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                                                              needles));
    }

    // TODO: Write more tests (Problem 4.a)
    
    @Test
    // haystack as empty  
    public void GetSubstringsTest0() {
        String haystack = "";
        String[] needles = {"ab", "abc", "e", "fg"};

        Map<String, Integer> expectedOutput = new HashMap<String, Integer>();

        assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                                                              needles));
    }
    
    @Test
    // haystack and neeldes are both empty 
    public void GetSubstringsTest04() {
        String haystack = "";
        String[] needles = {};

        Map<String, Integer> expectedOutput = new HashMap<String, Integer>();

        assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                                                              needles));
    }
    
    @Test
    // haystack takes none of elements of needles  
    public void GetSubstringsTest01() {
        String haystack = "bcdes";
        String[] needles = {"ab", "abc", "e", "fg"};

        Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
          expectedOutput.put("e", 3);
        assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                                                              needles));
    }
    
    @Test
    // haystack takes none of elements of needles  
    public void GetSubstringsTest02() {
        String haystack = "bcdes";
        String[] needles = {"bcdesf"};

        Map<String, Integer> expectedOutput = new HashMap<String, Integer>();

        assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                                                              needles));
    }
    
    @Test
    // haystack takes none of elements of needles  
    public void GetSubstringsTest05() {
        String haystack = "bcdes";
        String[] needles = {"bcdesf","abcdesf","abcdes"};

        Map<String, Integer> expectedOutput = new HashMap<String, Integer>();

        assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                                                              needles));
    }
    
    @Test
    // last index  as the  lowest index for e  
    public void GetSubstringsTest1() {
        String haystack = "abcde";
        String[] needles = {"ab", "abc", "e", "fg"};

        Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
        expectedOutput.put("ab", 0);
        expectedOutput.put("abc", 0);
        expectedOutput.put("e", 4);
        
        assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                                                              needles));
    }
    
    @Test
    public void GetSubstringsTest2() {
        String haystack = "acdea";
        String[] needles = {"ab", "abc", "ea", "fg"};

        Map<String, Integer> expectedOutput = new HashMap<String, Integer>();


        expectedOutput.put("ea", 3);


        assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                                                              needles));
    }

    @Test
    public void GetSubstringsTest3() {
        String haystack = "acdea";
        String[] needles = {};

        Map<String, Integer> expectedOutput = new HashMap<String, Integer>();

        assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                                                              needles));
    }

    
}
