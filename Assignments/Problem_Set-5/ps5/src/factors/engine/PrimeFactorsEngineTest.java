package factors.engine;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class PrimeFactorsEngineTest {
	
	private final static int PRIME_CERTAINTY = 10;

	@Test
	public void primeFactorsEngineTest001() {
		BigInteger[] expected = new BigInteger[] {
				BigInteger.valueOf(5), BigInteger.valueOf(17)};
		BigInteger[] actual = PrimeFactorsEngine.findPrimeFactors(BigInteger.valueOf(85),
				BigInteger.valueOf(2), BigInteger.valueOf(17), PRIME_CERTAINTY);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void primeFactorsEngineTest002() {
		BigInteger[] expected = new BigInteger[] {BigInteger.valueOf(5)};
		BigInteger[] actual = PrimeFactorsEngine.findPrimeFactors(BigInteger.valueOf(85),
				BigInteger.valueOf(2), BigInteger.valueOf(16), PRIME_CERTAINTY);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void primeFactorsEngineTest003() {
		BigInteger[] expected = new BigInteger[] {};
		BigInteger[] actual = PrimeFactorsEngine.findPrimeFactors(BigInteger.valueOf(85),
				BigInteger.valueOf(2), BigInteger.valueOf(4), PRIME_CERTAINTY);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void primeFactorsEngineTest004() {
		BigInteger[] expected = new BigInteger[] {
				BigInteger.valueOf(2), BigInteger.valueOf(2),
				BigInteger.valueOf(2), BigInteger.valueOf(3),
				BigInteger.valueOf(11)};
		BigInteger[] actual = PrimeFactorsEngine.findPrimeFactors(BigInteger.valueOf(264),
				BigInteger.valueOf(2), BigInteger.valueOf(17), PRIME_CERTAINTY);
		assertArrayEquals(expected, actual);
	}
}
