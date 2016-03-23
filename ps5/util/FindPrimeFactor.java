package util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FindPrimeFactor {
	
    private final static int PRIME_CERTAINTY = 10;

	 /**
     * 	 finds all prime BigIntegers x such that low <= x <= hi AND x divides N evenly.
     *   Repeated prime factors will be found multiple times.
     *
     * @param BigInteger N. such that 2 <= N
     * @param BigInteger low the lower bound , hi the higher bound. such that 1 <= low <= hi
     * @return Prime factors of N, an array of BigInteger
     * @throws Exception 
     */
    
    
    
    public static List<BigInteger> findPrimeFactor(BigInteger N,BigInteger hi, BigInteger low) 
    		throws Exception{
    	/**
    	 * Check repo
    	 *   N >=2
    	 *   low <= high
    	 */
    	
    	if(hi.compareTo(low) == -1){
    		throw new ArithmeticException("high should be greater than low");
    	}else if(N.compareTo( new BigInteger("2")) == -1){
    		throw new ArithmeticException("N should be greater than 2");
    	}
    	
    	BigInteger x = low;
    	BigInteger n = N;
    	BigInteger one = new BigInteger("1");
    	BigInteger zero = new BigInteger("0");
    	List<BigInteger> results = new ArrayList<BigInteger>();
    	
    		if(x.isProbablePrime(PRIME_CERTAINTY)){
    			while(n.compareTo(one) == 1){
    				while(n.remainder(x).equals(zero)){
        				results.add(x);
        				n = n.divide(x);
        			}if(x.compareTo(hi) == 0){
        				return results;
        			}
    				x = x.add(one);
    			}
    			 return results;	
    		}

    	return results;
    }
    
}
