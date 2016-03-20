package echo.server;

import java.io.IOException;

import org.junit.Test;

public class EchoSecrverTest {
		/*
		 * Connect server with port 4949
		 */
		
		public void  TestServer() throws IOException {
			String[] strings = new String[1] ;
			strings[0] = "4949";
			EchoServer.main(strings);
		}
		
		/*
		 * Connect server without an argument
		 */
		@Test
		public void  TestServer1() throws IOException {
			EchoServer.main();
		}
		
}
