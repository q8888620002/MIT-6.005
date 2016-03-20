package echo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * A simple client that will interact with an EchoServer.
 */
public class EchoClient {
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	private BufferedReader typeIn;
	/**
	 * @param args String array containing Program arguments.  It should only 
	 *      contain exactly one String indicating which server to connect to.
	 *      We require that this string be in the form hostname:portnumber.
	 */
	public static void main(String[] args) throws IOException {
	    // TODO Complete this implementation. 
		
		if(args.length!=2){
			System.err.println( "Usage: java EchoClient <host name>:<port number>");
			System.exit(1);
		}
		
		String hostname = args[0];
		int port = Integer.parseInt(args[1]);
		
		  try {
			  EchoClient client = new EchoClient(hostname,port);
			  client.sendRequest();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Send request to Server and print messages out while receiving feedback from server
	 * @throws IOException
	 */
	private void sendRequest() throws IOException {
		// TODO Auto-generated method stub
		typeIn = new BufferedReader(new InputStreamReader(
				System.in));
		
		String userSay = typeIn.readLine();
		while((userSay)!=null){
			out.print(userSay+"\n");
			out.flush();
			System.out.println(">>> "+in.readLine());
			userSay = typeIn.readLine();
		}
		
		out.close();
		in.close();
		client.close();
	}
	
	/**
	 * Make a connection to Sever
	 * @param hostname
	 * @param port number
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public EchoClient(String hostname, int port) throws UnknownHostException, IOException{
		try {
			client = new Socket(hostname, port);
			in = new  BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream());
			
		}catch(UnknownHostException e){
			System.err.println("Don't know about host " + hostname+":"+port);
            System.exit(1);
		}catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " +
	                hostname + port);
	        System.exit(1);
		}
		
	}
	
	
	
	

}
