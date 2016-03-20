package echo.client;

import java.io.IOException;

import org.junit.Test;

public class EchoClientTest {
		/*
		 * Test client connection to EchoServer
		 */
	
		public void ClientTest() throws IOException{
			
			String[] strings= new String[2];
			strings[0] = "localhost";
			strings[1] = "4444";
			
			EchoClient.main(strings);
		}
		
		/*
		 * Test client connection to EchoServer
		 */
		@Test
		public void ClientTest1() throws IOException{
			
			String[] strings= new String[2];
			strings[0] = "";
			strings[1] = "4444";
			
			EchoClient.main(strings);
		}
}
