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
        // TODO: Implement (Problem 3.b)
    	
    	for (int i = 1 ; i <= digits.length; i++ ){
    		if((digits[i-1]< 0 )|| (digits[i-1] >= base)){
    			return null;
    		}
    	}
    	if ((alphabet.length != base )||(base<2)){
    		return null;
    	}
    	
    	char[] output = new char[digits.length];
    	for(int index = 0;index <= digits.length-1; index++ ){
    		output[index] = alphabet[digits[index]]; 
    	}
    	// convert char[] to string 
        return new String(output);
    }
}
