package echo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A simple client that will interact with an EchoServer.
 */
public class EchoClient {
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	// Rep invariant: clientSocket, in, out != null
	
	/**
	 * Make a EchoClient and connect it to a server running on
	 * hostname at the specified port.
	 * @throws IOException if can't connect
	 */
	public EchoClient(String hostname, int port) throws IOException {
		clientSocket = new Socket(hostname, port);
		in = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintWriter(
				new OutputStreamWriter(clientSocket.getOutputStream()));
	}
	
	/**
	 * Run the client, listening for user requests and send them to server
	 * @throws IOException if the main client socket is broken
	 */
	public void request() throws IOException {
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		// blocks until the user inputs
		String userInput = stdIn.readLine();
		while (userInput != null) {
			sendUserRequest(userInput);
			showServerReply();
			userInput = stdIn.readLine();
		}
		// user's standard input stream is closed, terminate the client
		close();
	}
	
	/**
	 * Send a user request message to the server. Require this is "open"
	 * @param message message to echo
	 * @throws IOException on network or server failure
	 */
	public void sendUserRequest(String message) throws IOException {
		out.print(message + "\n");
		out.flush();	// important! make sure message actually gets sent
	}
	
	/**
	 * Show user the server's reply. Requires this is "open"
	 * @throws IOException on server or network failure
	 */
	public void showServerReply() throws IOException {
		String serverReply = getReply();
		System.out.print(">>> " + serverReply + "\n");
	}
	
	/**
	 * Get a reply from the next request that was submitted.
	 * Requires this is "open"
	 * @return echo of the requested message
	 * @throws IOException on server or network failure
	 */
	public String getReply() throws IOException {
		String reply = in.readLine();
		if (reply == null) {
			throw new IOException("connection terminated unexpectedly");
		}
		return reply;
	}
	
	/**
	 * Closes the client's connection to the server.
	 * This client is now closed. Requires this is "open"
	 * @throws IOException if close fails
	 */
	public void close() throws IOException {
		out.close();
		in.close();
		clientSocket.close();
	}

	/**
	 * @param args String array containing Program arguments.  It should only 
	 *      contain exactly one String indicating which server to connect to.
	 *      We require that this string be in the form hostname:portnumber.
	 */
	public static void main(String[] args) throws IOException {
	    if (args.length != 2) {
	    	System.err.println("Usage: java EchoClient <hostname> <port>");
	    	System.exit(1);
	    }
	    String hostname = args[0];
	    try {
	    	int port = Integer.parseInt(args[1]);
	    	EchoClient client = new EchoClient(hostname, port);
		    client.request();
	    } catch (NumberFormatException nfe) {
	    	// complain about ill-formatted argument
			System.err.println("EchoClient: ill-formatted port: " + args[1]);
			nfe.printStackTrace();
	    }
	}
}
