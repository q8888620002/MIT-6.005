package echo.server;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * A simple server that will echo client inputs.
 */
public class EchoServer {
	
	/** default port number where the echo server listen for connection */
	private static final int ECHO_PORT = 4444;
	
	private ServerSocket serverSocket;
	
	/**
	 * Make a EchoServer that listens for connections on port.
	 * @param port port number, requires 0 <= port <= 65535
	 */
	public EchoServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}
    
	/**
	 * Run the server, listening for connections and handling them.
	 * @throws IOException if the main server socket is broken
	 */
	private void serve() throws IOException {
		while (true) {
			// blocks until a client connects
			Socket clientSocket = serverSocket.accept();
			try {
				handle(clientSocket);
			} catch (IOException ioe) {
				ioe.printStackTrace();	// but don't terminate serve()
			} finally {
				clientSocket.close();
			}
		}
	}
	
	/**
	 * Handle one client connection. Return when client disconnect.
	 * @param clientSocket socket for a connected client to handle
	 * @throws IOException if connection encounters an error
	 */
	private void handle(Socket clientSocket) throws IOException {
		BufferedReader in = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream()));
		PrintWriter out = new PrintWriter(
				new OutputStreamWriter(clientSocket.getOutputStream()));
		try {
			// each request is a single line of message
			String request = in.readLine();
			while (request != null) {
				sendReply(out, request);
				request = in.readLine();
			}
		} finally {
			out.close();
			in.close();
		}
	}
	
	/**
	 * Send a reply message to the client. Require this is "open"
	 * @param out the output stream for a client socket
	 * @param message message to reply the client
	 * @throws IOException on network or server failure
	 */
	public void sendReply(PrintWriter out, String message) throws IOException {
		out.print(message + "\n");
		out.flush();	// important! flush out buffer so the reply gets sent
	}
	
    /**
     * @param args String array containing Program arguments.  It should only 
     *      contain at most one String indicating the port it should connect to.
     *      The String should be parseable into an int.  
     *      If no arguments, we default to port 4444.
     */
	public static void main(String[] args) throws IOException {
		if (args.length > 1) {
			System.err.println("Usage: java EchoServer [port], where port is an optional\n"
					+ " numerical argument within range 0-65535.");
			System.exit(1);
		}
		int port = 0;
		if (args.length == 0) {
			port = ECHO_PORT;
		} else if (args.length == 1) {
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException nfe) {
				// complain about ill-formatted argument
				System.err.println("EchoServer: illegal port, " + args[0]);
				nfe.printStackTrace();
			}
		}
		try {
			EchoServer server = new EchoServer(port);
			server.serve();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
    }
}