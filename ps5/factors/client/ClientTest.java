package factors.client;

import java.io.IOException;

import org.junit.Test;

public class ClientTest {
		
		@Test
		public void multipleClient() throws IOException{
			String[] args = new String[3];
			args[1] = "localhost:1234";
			args[0] = "localhost:4444";
			args[2] = "localhost:4747";
			PrimeFactorsClient.main(args);
		}
}
