package piwords;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class AlphabetGenerator {
    /**
     * Given a numeric base, return a char[] that maps every digit that is
     * representable in that base to a lower-case char.
     * 
     * This method will try to weight each character of the alphabet
     * proportional to their occurrence in words in a training set.
     * 
     * This method should do the following to generate an alphabet:
     *   1. Count the occurrence of each character a-z in trainingData.
     *   2. Compute the probability of each character a-z by taking
     *      (occurrence / total_num_characters).
     *   3. The output generated in step (2) is a PDF of the characters in the
     *      training set. Convert this PDF into a CDF for each character.
     *   4. Multiply the CDF value of each character by the base we are
     *      converting into.
     *   5. For each index 0 <= i < base,
     *      output[i] = (the first character whose CDF * base is > i)
     * 
     * A concrete example:
     * 	 0. Input = {"aaaaa..." (302 "a"s), "bbbbb..." (500 "b"s),
     *               "ccccc..." (198 "c"s)}, base = 93
     *   1. Count(a) = 302, Count(b) = 500, Count(c) = 193
     *   2. Pr(a) = 302 / 1000 = .302, Pr(b) = 500 / 1000 = .5,
     *      Pr(c) = 198 / 1000 = .198
     *   3. CDF(a) = .302, CDF(b) = .802, CDF(c) = 1
     *   4. CDF(a) * base = 28.086, CDF(b) * base = 74.586, CDF(c) * base = 93
     *   5. Output = {"a", "a", ... (28 As, indexes 0-27),
     *                "b", "b", ... (47 Bs, indexes 28-74),
     *                "c", "c", ... (18 Cs, indexes 75-92)}
     * 
     * The letters should occur in lexicographically ascending order in the
     * returned array.
     *   - {"a", "b", "c", "c", "d"} is a valid output.
     *   - {"b", "c", "c", "d", "a"} is not.
     *   
     * If base >= 0, the returned array should have length equal to the size of
     * the base.
     * 
     * If base < 0, return null.
     * 
     * If a String of trainingData has any characters outside the range a-z,
     * ignore those characters and continue.
     * 
     * @param base A numeric base to get an alphabet for.
     * @param trainingData The training data from which to generate frequency
     *                     counts. This array is not mutated.
     * @return A char[] that maps every digit of the base to a char that the
     *         digit should be translated into.
     */
    public static char[] generateFrequencyAlphabet(int base,
                                                   String[] trainingData) {
    	if (base < 2) {
    		return null;
    	} else {
    		int trainingSetSize = trainingData.length;
    		if (trainingSetSize == 0) { // trainingData is an empty set {}, return null
    			return null;
    		} else {
    			Map<Character, Integer> alphabetOccurrenceMap = new TreeMap<Character, Integer>();
    			alphabetOccurrenceMap = countAlphabetOccurrenceInStringArray(trainingData);
    			
    			Map<Character, Double> alphabetPDFMap = new TreeMap<Character, Double>();
    			alphabetPDFMap = computeAlphabetPDFMap(alphabetOccurrenceMap);
    			
    			Map<Character, Double> alphabetCDFMap = new TreeMap<Character, Double>();
    			alphabetCDFMap = computeAlphabetCDFMap(alphabetPDFMap);
    			
    			Map<Character, Integer> alphabetDistribution =
    					computeAlphabetCumulativeDistributionMap(base, alphabetCDFMap);
    			
    			char[] outputAlphabets = new char[base];
    			outputAlphabets = generateOutputAlphabets(base, alphabetDistribution);
    			Arrays.sort(outputAlphabets);
    			
    			return outputAlphabets;
    		}
    	}
    }
    
    /**
     * Given an array of strings, return a Map<Character, Integer> that
     * associate each alphabet with its occurrence this array.
     * 
     * Alphabets should be counted despite their cases.
     * For example, 'E' and 'e' should be regarded as the same alphabet,
     * and the occurrence of alphabet 'e' in string "Middle East" should be 2,
     * rather than 1.
     * 
     * Besides, all the alphabets should occur in lower-case and int lexicographically
     * ascending order in the return map.
     * 
     * If a String in the array has any characters outside the range a-z,
     * ignore those characters and continue.
     * 
     * A sample application:
     * 1. input: stringArray = {"People@Mars", "Egypt", "Middle East", "2 eggplants"}
     * 2. output: strArrAlphOccurMap = <<'a', 3>,
     * 									<'d', 2>,
     * 									<'e', 6>,
     * 									<'g', 3>,
     * 									<'i', 1>,
     * 									<'l', 3>,
     * 									<'m', 2>,
     * 									<'n', 1>,
     * 									<'o', 1>,
     * 									<'p', 4>,
     * 									<'r', 1>
     * 									<'s', 2>,
     * 									<'t', 3>,
     * 									<'y',1>>
     * 
     * 
     * @param stringArray The array of strings to count for alphabet occurrence.
     * @return A map that associates each alphabet with its occurrence in this array.
     */
    private static Map<Character, Integer> countAlphabetOccurrenceInStringArray(String[] stringArray) {
    	Map<Character, Integer> stringArrayAlphabetOccurenceMap = new TreeMap<Character, Integer>();
    	int stringArrayLength = stringArray.length;
    	
    	Map<Character, Integer> stringAlphabetOccurenceMap = new TreeMap<Character, Integer>();
    	
    	for (int i = 0; i < stringArrayLength; i = i + 1) {
    		stringAlphabetOccurenceMap = countAlphabetOccurrenceInString(stringArray[i]);
    		stringArrayAlphabetOccurenceMap = augmentMap(stringArrayAlphabetOccurenceMap,
    				stringAlphabetOccurenceMap);
    	}
    	return stringArrayAlphabetOccurenceMap;
    }
    
    /**
     * Given a strings, return a Map<Character, Integer> that associate each
     * alphabet with its occurrence this string.
     * 
     * Alphabets should be counted despite their cases.
     * For example, 'E' and 'e' should be regarded as the same alphabet,
     * and the occurrence of alphabet 'e' in string "Middle East" should be 2,
     * rather than 1.
     * 
     * Besides, all the alphabets should occur in lower-case and in lexicographically
     * ascending order in the return map.
     * 
     * If a String in the array has any characters outside the range a-z,
     * ignore those characters and continue.
     * 
     * A sample application:
     * 1. input: mixedCaseString = "People@Venus"
     * 2. output: strAlphOccurMap = <<'e', 3>,
     * 								 <'l', 1>,
     * 								 <'n', 1>,
     * 								 <'o', 1>,
     * 								 <'p', 2>,
     * 								 <'s', 1>,
     * 								 <'v', 1>>
     * 
     * @param mixedCaseString The strings of mixed-case characters to count for
     * 		  alphabet occurrence.
     * @return A map that associates each alphabet with its occurrence in this string.
     */
    private static Map<Character, Integer> countAlphabetOccurrenceInString(String mixedCaseString) {
    	Map<Character, Integer> alphaOccurMap = new TreeMap<Character, Integer>();
    	int strLen = mixedCaseString.length();
    	String lowerCaseStr = mixedCaseString.toLowerCase();
    	for (int i = 0; i < strLen; i = i + 1) {
    		char checkChar = lowerCaseStr.charAt(i);
    		if (Character.isAlphabetic(checkChar)) {
    			if (alphaOccurMap.containsKey(checkChar)) {
    				alphaOccurMap.put(checkChar, alphaOccurMap.get(checkChar) + 1);
    			} else {
    				alphaOccurMap.put(checkChar, 1);
    			}
    		}
    	}
    	return alphaOccurMap;
    }
    
    /**
     * Given a base map (Map<Character, Integer> baseMap) and a supplement map
     * (Map<Character, Integer> supplementMap), return the baseMap augmented by
     * entries in the supplementMap.
     * 
     * If an entry of key K from supplementMap does not appears in baseMap,
     * then put that entry into baseMap. Otherwise, increase the value of entry K
     * in baseMap by the value of entry K in supplementMap.
     * 
     * @param baseMap The base map to be augmented.
     * @param supplementMap The supplement map which is used to augmented the baseMap.
     * @return The new baseMap augmented by entries in the supplementMap.
     */
    private static Map<Character, Integer> augmentMap(Map<Character, Integer> baseMap,
    		Map<Character, Integer> supplementMap) {
    	Iterator<Entry<Character, Integer>> iter = supplementMap.entrySet().iterator();
    	while (iter.hasNext()) {
    		Map.Entry<Character, Integer> entry = (Map.Entry<Character, Integer>) iter.next();
    		char addKey = entry.getKey();
    		int addVal = entry.getValue();
    		if (baseMap.containsKey(addKey)) {
    			baseMap.put(addKey, baseMap.get(addKey) + addVal);
    		} else {
    			baseMap.put(addKey, addVal);
    		}
    	}
    	return baseMap;
    }
    
    /**
     * Given a map, return the sum of values in all its entries.
     * 
     * If there is no entry in the map, return 0.
     * 
     * @param map The map to count for entry value sum.
     * @return Sum of values in all the map's entries.
     */
    private static int mapSumValue(Map<Character, Integer> map) {
    	int sumVal = 0;
    	Iterator<Entry<Character, Integer>> iter = map.entrySet().iterator();
    	while (iter.hasNext()) {
    		Map.Entry<Character, Integer> entry = (Map.Entry<Character, Integer>) iter.next();
    		int val = entry.getValue();
    		sumVal = sumVal + val;
    	}
    	return sumVal;
    }
    
    /**
     * Given a map of alphabet occurrence, return a map of probability of each
     * character a-z, which is a Probability Distribution Function (PDF) of all
     * the alphabets.
     * 
     * @param alphaOccurMap A map of alphabet occurrence.
     * @return A map of probability of each alphabet.
     */
    private static Map<Character, Double> computeAlphabetPDFMap(Map<Character, Integer> alphaOccurMap){
    	
    	Map<Character, Double> alphabetPDFMap = new TreeMap<Character, Double>();
    	int totalAlphabetAmount = mapSumValue(alphaOccurMap);
    	Iterator<Entry<Character, Integer>> iter = alphaOccurMap.entrySet().iterator();
    	while (iter.hasNext()) {
    		Map.Entry<Character, Integer> entry = (Map.Entry<Character, Integer>) iter.next();
    		char alphabet = entry.getKey();
    		int alphabetOccurrence = entry.getValue();
    		// WARNING: Don't forget to cast both the numerator and denominator to floating-point
    		//			value before division. Otherwise, the alphabetFreq will be zero.
    		double alphabetFreq = (double)alphabetOccurrence / (double)totalAlphabetAmount;
    		alphabetPDFMap.put(alphabet, alphabetFreq);
    	}
    	return alphabetPDFMap;
    }
    
    /**
     * Given a map of alphabet Probability Distribution Function (PDF), return a map
     * of cumulative probability of each character a-z, which is a Cumulative Distribution
     * Function (CDF) of all the alphabet.
     * 
     * @param alphabetPDFMap A map of probability of each alphabet.
     * @return A map of cumulative probability of each alphabet.
     */
    private static Map<Character, Double> computeAlphabetCDFMap(Map<Character, Double> alphabetPDFMap){
    	Map<Character, Double> alphabetCDFMap = new TreeMap<Character, Double>();
    	Iterator<Entry<Character, Double>> iter = alphabetPDFMap.entrySet().iterator();
    	double CDF = 0.0;
    	while (iter.hasNext()) {
    		Map.Entry<Character, Double> entry = (Map.Entry<Character, Double>) iter.next();
    		char alphabet = entry.getKey();
    		double alphabetPDF = entry.getValue();
    		CDF = alphabetPDF + CDF;
    		alphabetCDFMap.put(alphabet, CDF);
    	}
    	return alphabetCDFMap;
    }
    
    /**
     * Given a base and a map of alphabet Cumulative Distribution Function (CDF), return a map
     * of cumulative distribution of each character a-z.
     * 
     * @param base A numeric base to get an alphabet for.
     * @param alphabetCDFMap A map of cumulative probability of each alphabet.
     * @return A map of cumulative distribution of each alphabet.
     */
    private static Map<Character, Integer> computeAlphabetCumulativeDistributionMap(int base,
    	Map<Character, Double> alphabetCDFMap) {
    	
    	Map<Character, Integer> alphabetCumulativeDistributionMap = new TreeMap<Character, Integer>();
    	Iterator<Entry<Character, Double>> iter = alphabetCDFMap.entrySet().iterator();
    	while (iter.hasNext()) {
    		Map.Entry<Character, Double> entry = (Map.Entry<Character, Double>) iter.next();
    		char alphabet = entry.getKey();
    		double alphabetCDF = entry.getValue();
    		int alphabetDistribution = (int) Math.round(alphabetCDF * base);
    		alphabetCumulativeDistributionMap.put(alphabet, alphabetDistribution);
    	}
    	return alphabetCumulativeDistributionMap;
    }
    
    /**
     * Given a base and a map of cumulative distribution of each alphabet, return an char[]
     * where alphabets are distributed by the alphabet cumulative distribution map.
     * 
     * @param base A numeric base to get an alphabet for.
     * @param alphabetDistributionMap A map of cumulative distribution of each alphabet.
     * @return A char[] in which alphabets are distributed by the alphabet cumulative
     * 		   distribution map.
     */
    private static char[] generateOutputAlphabets(int base, Map<Character, Integer> alphabetDistributionMap){
    	
    	char[] outputAlphabetArray = new char[base];
    	Iterator<Entry<Character, Integer>> iter = alphabetDistributionMap.entrySet().iterator();
    	int i = 0;
    	while (iter.hasNext()) {
    		Map.Entry<Character, Integer> entry = (Map.Entry<Character, Integer>) iter.next();
    		char alphabet = entry.getKey();
    		int alphabetDistribEndpoint = entry.getValue();
    		while (i < alphabetDistribEndpoint) {
    			outputAlphabetArray[i] = alphabet;
    			i = i + 1;
    		}
    	}
    	return outputAlphabetArray;
    }
}
