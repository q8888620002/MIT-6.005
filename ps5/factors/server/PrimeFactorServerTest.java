package factors.server;

import org.junit.Test;

public class PrimeFactorServerTest {
	
	
	@Test
	/*
	 * Testing listening to port 1234 
	 */
	public void ServerSocketTest() throws Exception{
		String[] port = new String[1];
		port[0] = "1234";
		PrimeFactorsServer.main(port);
		String[] ports = new String[1];
		ports[0] = "0";
		PrimeFactorsServer.main(ports);
		
	}
	

	/*
	 * Testing listening to port 1234 
	 */
	public void ServerSocketTest2() throws Exception{
		String[] port = new String[1];
		port[0] = "0";
		PrimeFactorsServer.main(port);
	}
}
