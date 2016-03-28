package minesweeper.server;
/*
 * This is a runnable class that handle the client connection  while a new connection establish.
 * Rep : 
 *  	the client socket is immutable.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler implements  Runnable {
	private final Socket clientSocket;
	
		/**
		 * constructor for connectionHandler
		 * 
		 */
		 public ConnectionHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}
		 
	 /**
		 * Start to handle the client request while the thread start.
		 */
		@Override
		public void run() {
				try {
					// handle the client request
					handleConnection();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		/**
	     * Handle a single client connection in this thread.  Returns when client disconnects.
	     * @param socket  socket where client is connected
	     * @throws IOException if connection has an error or terminates unexpectedly
	     */
	    private void handleConnection() throws IOException {
	    
	        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

	        try {
	        	for (String line = in.readLine(); line != null; line = in.readLine()) {
	        		String output = handleRequest(line);
	        		if(output != null) {
	        			out.println(output);
	        		}
	        	}
	        } finally {        
	        	out.close();
	        	in.close();
	        }
	    }

	    /**
		 * handler for client input
		 * 
		 * make requested mutations on game state if applicable, 
		 * then return appropriate message to the user.
		 * 
		 * @param input
		 * @return
		 */
		private static String handleRequest(String input) {

			String regex = "(look)|(dig \\d+ \\d+)|(flag \\d+ \\d+)|(deflag \\d+ \\d+)|(help)|(bye)";
			if(!input.matches(regex)) {
				//invalid input
				return null;
			}
			String[] tokens = input.split(" ");
			if(tokens[0].equals("look")) {
				// 'look' request
				//TODO Question 5
			} else if(tokens[0].equals("help")) {
				// 'help' request
				//TODO Question 5
			} else if(tokens[0].equals("bye")) {
				// 'bye' request
				//TODO Question 5
			} else {
				int x = Integer.parseInt(tokens[1]);
				int y = Integer.parseInt(tokens[2]);
				if(tokens[0].equals("dig")) {
					// 'dig x y' request
					//TODO Question 5
				} else if(tokens[0].equals("flag")) {
					// 'flag x y' request
					//TODO Question 5
				} else if(tokens[0].equals("deflag")) {
					// 'deflag x y' request
					//TODO Question 5
				}
			}
			//should never get here
			return "";
		}
	
		
	
}
