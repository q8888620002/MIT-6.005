package piwords;

public class DigitsToStringConverter {
    /**
     * Given a list of digits, a base, and an mapping of digits of that base to
     * chars, convert the list of digits into a character string by applying the
     * mapping to each digit in the input.
     * 
     * If digits[i] >= base or digits[i] < 0 for any i, consider the input
     * invalid, and return null.
     * If alphabet.length != base, consider the input invalid, and return null.
     *
     * @param digits A list of digits to encode. This object is not mutated.
     * @param base The base the digits are encoded in.
     * @param alphabet The mapping of digits to chars. This object is not
     *                 mutated.
     * @return A String encoding the input digits with alphabet.
     */
    public static String convertDigitsToString(int[] digits, int base,
                                               char[] alphabet) {
    	if (base < 2) {
    		return null;
    	} else if (alphabet.length != base) {
    		return null;
    	} else {
    		int digitSeqLength = digits.length;
    		for (int i = 0; i < digitSeqLength; i = i + 1) {
    			if ((digits[i] >= base) || (digits[i] < 0)) {
    				return null;
    			}
    		}
    		char[] encodedAlphabetSeq = new char[digitSeqLength];
    		for (int j = 0; j < digitSeqLength; j = j + 1) {
    			encodedAlphabetSeq[j] = alphabet[digits[j]];
    		}
    		
    		// Be careful!
    		// Don't write it as "return encodedAlphabetSeq.toString();",
    		// which returns a string in format "[x, y, z ...]", rather than
    		// "xyz..." we want.
    		return new String(encodedAlphabetSeq);
    	}
    }
}
