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

import minesweeper.Board;
import minesweeper.SquareState;

public class ConnectionHandler implements  Runnable {
	private final Socket clientSocket;
	private Board board;
	private BufferedReader in;
    private PrintWriter out;
    
		/**
		 * constructor for connectionHandler
		 * @param clientsocket 
		 * @param 
		 * 
		 */
		 public ConnectionHandler(Socket clientSocket, Board board) {
			this.clientSocket = clientSocket;
			this.board = board;
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
	     * Handle a single client connection in this thread.  
	     * Returns when client disconnects.
	     * 
	     * @param socket where client is connected
	     * @throws IOException if connection has an error or terminates unexpectedly
	     */
	    private void handleConnection() throws IOException {
	    
	    	in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	    	out = new PrintWriter(clientSocket.getOutputStream(), true);
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
		 * make requested mutations on game state if applicable, 
		 * then return appropriate message to the user.
		 * Grammar of server to user message:
	     * 		MESSAGE :== BOARD | BOOM | HELP | HELLO
	     * 		BOARD :== LINE+
	     * 		LINE :== (SQUARE SPACE)* SQUARE NEWLINE
	     * 		SQUARE :== “-” | “F” | COUNT | SPACE
	     * 		SPACE :== “ “NEWLINE :== “\n”
	     * 		COUNT :== [1-8]BOOM :== "BOOM!" NEWLINE
	     * 		HELP :== [^NewLine]+ NEWLINE
	     * 		HELLO :== "Welcome to Minesweeper. " N " people are playing including you. Type 'help' for help."
	     * 		NEWLINEN :== INT
	     * 		INT :== [0-9]+
		 * 
		 * @param client's input 
		 * @return a string representation of Response
	     * @throws IOException 
		 */
		private String handleRequest(String input) throws IOException {

			String regex = "(look)|(dig \\d+ \\d+)|(flag \\d+ \\d+)|(deflag \\d+ \\d+)|(help)|(bye)";
			if(!input.matches(regex)) {
				//invalid input
				return null;
			}
			String[] tokens = input.split(" ");
			if(tokens[0].equals("look")) {
				// 'look' request
				//TODO Question 5
				
				return board.toString();
				
			} else if(tokens[0].equals("help")) {
				// 'help' request
				//TODO Question 5
				
				
			} else if(tokens[0].equals("bye")) {
				// 'bye' request
				//TODO Question 5
				out.print("See you next time\n");
				
				out.flush();
				out.close();
				in.close();
				clientSocket.close();
				
			} else {
				int x = Integer.parseInt(tokens[1]);
				int y = Integer.parseInt(tokens[2]);
				if(tokens[0].equals("dig")) {
					// 'dig x y' request
					//TODO Question 5
					if((x>=0 && x< board.getDim())&&(y >=0 && y< board.getDim())){
						
					}else{
						return board.toString();
					}
					
				} else if(tokens[0].equals("flag")) {
					// 'flag x y' request
					//TODO Question 5
					
					if((x>=0 && x< board.getDim())&&(y >=0 && y< board.getDim())){
							if(board.getState(x, y)==SquareState.UNTOUCHED){
								board = board.flag(x, y);
							}
							return board.toString();
						}else{
							out.println("invalid coordination of ("+x+","+y+")");
							out.flush();
							return board.toString();
						}
				
				} else if(tokens[0].equals("deflag")) {
					// 'deflag x y' request
					//TODO Question 5
					
				}
			}
			//should never get here
			return "";
		}
	
		
	
}
