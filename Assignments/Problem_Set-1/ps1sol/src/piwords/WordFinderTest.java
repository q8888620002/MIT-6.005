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
    
    
    // TEST001 - TEST011
    // haystack takes NONE of needles' element as its substring
    
    // Test001 ~ Test005
	// needles' element is shorter than haystack
    @Test
    public void getSubStringTest001(){
    	String haystack = "";
    	String[] needles = {};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
    
    @Test
    public void getSubStringTest002(){
    	String haystack = "a";
    	String[] needles = {};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
    
    @Test
    public void getSubStringTest003(){
    	String haystack = "abcde";
    	String[] needles = {};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
    
    @Test
    public void getSubStringTest004(){
    	String haystack = "abcde";
    	String[] needles = {"ef"};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
    
    @Test
    public void getSubStringTest005(){
    	String haystack = "abcde";
    	String[] needles = {"egg", "as", "is"};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
    
    
    // Test006 ~ Test0011
 	// needles' element is expansion of haystack
    @Test
    public void getSubStringTest006(){
    	String haystack = "";
    	String[] needles = {"i"};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
    
    @Test
    public void getSubStringTest007(){
    	String haystack = "";
    	String[] needles = {"i", "pen"};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
    
    @Test
    public void getSubStringTest008(){
    	String haystack = "a";
    	String[] needles = {"app"};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
    
    @Test
    public void getSubStringTest009(){
    	String haystack = "a";
    	String[] needles = {"app", "apple"};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
    
    @Test
    public void getSubStringTest010(){
    	String haystack = "abcde";
    	String[] needles = {"abcdef"};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
    
    @Test
    public void getSubStringTest011(){
    	String haystack = "abcde";
    	String[] needles = {"abcdef", "sabcdef", "abcdefg", "sabcdefg"};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
    
    
    
    // TEST012 - TEST017
    // haystack takes needles' element as its substring
    
    // Test012 ~ Test013
 	// needles' element is shorter than haystack   
    @Test
    public void getSubStringTest012(){
    	String haystack = "abcde";
    	String[] needles = {"abcd"};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	expectedOutput.put("abcd", 0);
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
    
    @Test
    public void getSubStringTest013(){
    	String haystack = "abcde";
    	String[] needles = {"a", "ab", "ad", "abc", "abd", "acd", "acef","ade", "adef",
    			"abcd", "abde", "b", "bc", "bd", "bcd", "bce", "bcf","bcde", "c", "cde",
    			"dce", "de", "def"};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	expectedOutput.put("a", 0);
    	expectedOutput.put("ab", 0);
    	expectedOutput.put("abc", 0);
    	expectedOutput.put("abcd", 0);
    	expectedOutput.put("b", 1);
    	expectedOutput.put("bc", 1);
    	expectedOutput.put("bcd", 1);
    	expectedOutput.put("bcde", 1);
    	expectedOutput.put("c", 2);
    	expectedOutput.put("cde", 2);
    	expectedOutput.put("de", 3);
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
    
    
    // Test014 ~ Test017
  	// needles' element has the same length to haystack
    @Test
    public void getSubStringTest014(){
    	String haystack = "a";
    	String[] needles = {"a"};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	expectedOutput.put("a", 0);
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
    
    @Test
    public void getSubStringTest015(){
    	String haystack = "a";
    	String[] needles = {"a", "b", "a", "c", "a", "d"};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	expectedOutput.put("a", 0);
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
    
    @Test
    public void getSubStringTest016(){
    	String haystack = "abcde";
    	String[] needles = {"abcde"};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	expectedOutput.put("abcde", 0);
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
    
    @Test
    public void getSubStringTest017(){
    	String haystack = "abcde";
    	String[] needles = {"abcde", "abdef", "acdef", "adefg"};
    	
    	Map<String, Integer> expectedOutput = new HashMap<String, Integer>();
    	expectedOutput.put("abcde", 0);
    	
    	assertEquals(expectedOutput, WordFinder.getSubstrings(haystack,
                needles));
    }
}
