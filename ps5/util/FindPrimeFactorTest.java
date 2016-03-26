package util;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;


public class FindPrimeFactorTest {
		
	@Test
	// test the smallest prime number 2 
	
	public void FindPrimeTest1() throws Exception{
		BigInteger N = new BigInteger("2");
		BigInteger low = new BigInteger("2");
		BigInteger hi = new BigInteger("5");

		
		assertEquals("[2]", FindPrimeFactor.findPrimeFactor(N, hi, low).toString());		
		
	}
	
	@Test(expected=ArithmeticException.class)
	// test with N < 2
	public void FindPrimeError1() throws Exception{
		BigInteger N = new BigInteger("1");
		BigInteger low = new BigInteger("2");
		BigInteger hi = new BigInteger("5");

		
		assertEquals("[2]",FindPrimeFactor.findPrimeFactor(N, hi, low).toString());		
	}
	
	@Test(expected=ArithmeticException.class)
	// test with N < 2
	
	public void FindPrimeError2() throws Exception{
		BigInteger N = new BigInteger("0");
		BigInteger low = new BigInteger("2");
		BigInteger hi = new BigInteger("5");
		assertEquals("[2]", FindPrimeFactor.findPrimeFactor(N, hi, low).toString());		
		
	
	}
	
	@Test(expected=ArithmeticException.class)
	// test with N with negative value
	public void FindPrimeError3() throws Exception{
		BigInteger N = new BigInteger("-2");
		BigInteger low = new BigInteger("2");
		BigInteger hi = new BigInteger("5");
		assertEquals("[2]",FindPrimeFactor.findPrimeFactor(N, hi, low).toString());		
	}
	
	
	@Test
	// Find Prime factor of 4
	public void FindPrime1() throws Exception{
		BigInteger N = new BigInteger("4");
		BigInteger low = new BigInteger("2");
		BigInteger hi = new BigInteger("5");
		
		assertEquals("[2, 2]", FindPrimeFactor.findPrimeFactor(N, hi, low).toString());
	}
	
	
	@Test
	// Find Prime factor of 85 with low 2 and high 17
	public void FindPrimeOf851() throws Exception{
		BigInteger N = new BigInteger("85");
		BigInteger low = new BigInteger("2");
		BigInteger hi = new BigInteger("17");
		
		assertEquals("[5, 17]", FindPrimeFactor.findPrimeFactor(N, hi, low).toString());
	}
	
	
	@Test
	// Find Prime factor of 85 with low 2 and high 16
	public void FindPrimeof852() throws Exception{
		BigInteger N = new BigInteger("85");
		BigInteger low = new BigInteger("2");
		BigInteger hi = new BigInteger("16");
		
		assertEquals("[5]", FindPrimeFactor.findPrimeFactor(N, hi, low).toString());
	}
	
	
	@Test
	// Find Prime factor of 85 with low 2 and high 16
	public void FindPrimeOf853() throws Exception{
		BigInteger N = new BigInteger("85");
		BigInteger low = new BigInteger("2");
		BigInteger hi = new BigInteger("4");
		
		assertEquals("[]", FindPrimeFactor.findPrimeFactor(N, hi, low).toString());
	}
	
	
	@Test
	// Find Prime factor of 15 with low 2, high 7
	public void FindPrimeFactor() throws Exception{
		BigInteger N = new BigInteger("15");
		BigInteger low = new BigInteger("2");
		BigInteger hi = new BigInteger("7");
		
		assertEquals("[3, 5]", FindPrimeFactor.findPrimeFactor(N, hi, low).toString());
	}
	
	
	@Test
	// Find Prime factor of 264 with low 2, high 17
	public void FindPrimeFactor264() throws Exception{
		BigInteger N = new BigInteger("264");
		BigInteger low = new BigInteger("2");
		BigInteger hi = new BigInteger("17");
		
		assertEquals("[2, 2, 2, 3, 11]", FindPrimeFactor.findPrimeFactor(N, hi, low).toString());
	}
	
	@Test
	public void FindPrimeFactor1000() throws Exception{
		BigInteger N = new BigInteger("1000");
		BigInteger low = new BigInteger("2");
		BigInteger hi = new BigInteger("200");
		assertEquals("[2, 2, 2, 5, 5, 5]", FindPrimeFactor.findPrimeFactor(N, hi, low).toString());
	}
	
	@Test
	public void FindPrimeFactor1231123123() throws Exception{
		BigInteger N = new BigInteger("1231123123");
		BigInteger low = new BigInteger("2");
		BigInteger hi = new BigInteger("1500");
		assertEquals("[47, 79, 251, 1321]", FindPrimeFactor.findPrimeFactor(N, hi, low).toString());
	}
}
