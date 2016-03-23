package factors.server;

import org.junit.Test;

public class PrimeFactorServerTest {
	
	
	@Test
	/*
	 * Testing listening to port 0 
	 */
	public void ServerSocketTest() throws Exception{
		String[] port = new String[1];
		port[0] = "1234";
		PrimeFactorsServer.main(port);
	}
}
