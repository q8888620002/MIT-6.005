package factors.client;

import org.junit.Test;

public class ClientTest {
		
		@Test
		public void multipleClient(){
			String[] args = new String[2];
			args[1] = "localhost:1234";
			args[0] = "localhost:4444";
			
			PrimeFactorsClient.main(args);
		}
}
