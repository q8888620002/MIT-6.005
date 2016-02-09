package piwords;




public class BaseTranslator {
    /**
     * Converts an array where the ith digit corresponds to (1 / baseA)^(i + 1)
     * digits[i], return an array output of size precisionB where the ith digit
     * corresponds to (1 / baseB)^(i + 1) * output[i].
     * 
     * Stated in another way, digits is the fractional part of a number
     * expressed in baseA with the most significant digit first. The output is
     * the same number expressed in baseB with the most significant digit first.
     * 
     * To implement, logically, you're repeatedly multiplying the number by
     * baseB and chopping off the most significant digit at each iteration:
     * 
     * for (i < precisionB) {
     *   1. Keep a carry, initialize to 0.
     *   2. From RIGHT to LEFT
     *   	a. x = multiply the ith digit by baseB and add the carry
     *      b. the new ith digit is x % baseA
     *      c. carry = x / baseA
     *   3. output[i] = carry
     * 
     * If digits[i] < 0 or digits[i] >= baseA for any i, return null
     * If baseA < 2, baseB < 2, or precisionB < 1, return null
     * 
     * @param digits The input array to translate. This array is not mutated.
     * @param baseA The base that the input array is expressed in.
     * @param baseB The base to translate into.
     * @param precisionB The number of digits of precision the output should
     *                   have.
     * @return An array of size precisionB expressing digits in baseB.
     */
    public static int[] convertBase(int[] digits, int baseA,
                                    int baseB, int precisionB) {
        // TODO: Implement (Problem 2.b)
    	// get status value 
    	
       boolean status = inputCheck(digits,baseA,baseB,precisionB );
       
       // convert  digits[0]*(1/baseA)^1 + digits[1]*(1/baseA)^2 +....
       //... + digits[n]*(1/baseA)^(n+1)
       // to 
       // digits[0]*(1/baseB)^1 + digits[1]*(1/baseB)^2 +....
       //... + digits[m]*(1/baseB)^(m+1)
       // then get digit[0], ..., digit[precisionB]
       int[] result = new int[precisionB];
       int	digitLength = digits.length-1;
       
       if(status){
    	   //count the original fractional number in baseA
       	   double sum = 0;
       	   
           for(int index = 0; index <= digitLength ; index++){
        	   double value = digits[index]*(Math.pow(baseA, -(index+1) ));
        	   sum += value;
       		 }
           //convert sum to baseB with precisionB
         
          for (int newDigitTimes = 1; newDigitTimes <= precisionB; newDigitTimes++){
        	  int newDigit = (int) (sum / (Math.pow( baseB,-newDigitTimes )));
        	  sum = (float)(sum - newDigit * (Math.pow(baseB, -newDigitTimes)));
        	  result[ (newDigitTimes-1)] = newDigit;
              }
             return result;
       	    }else{
    	   return null;
       }
    }
    
    
    /*check if the inputs do not break the rules
     *  If digits[i] < 0 or digits[i] >= baseA for any i, return false
     *  If baseA < 2, baseB < 2, or precisionB < 1, return false
     *  
     * @param digits The input array to translate. This array is not mutated.
     * @param baseA The base that the input array is expressed in.
     * @param baseB The base to translate into.
     * @param precisionB The number of digits of precision the output should
     *                   have.
     * @return a boolean value
     */
    public static Boolean inputCheck(int[] digits, int baseA, int baseB, int precisionB){
    	 int digitLength = digits.length-1;
         boolean status = true;
         
         for (int index = 0; index <= digitLength; index++ ){
     		if ((digits[index]< 0 )|| digits[index] >= baseA ){
     		    status = false;	
     		}
     	}
         
    	if((baseA< 2 )||(baseB< 2 )||(precisionB < 1)){
    		status =false; 
    	}
    	
    	return status;
    }
}
