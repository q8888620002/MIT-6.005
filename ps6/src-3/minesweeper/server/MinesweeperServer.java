package minesweeper.server;

import java.net.*;

import minesweeper.Board;
import minesweeper.SquareState;

import java.io.*;
/*
 * - MinesweeperServer is thread safe because all method that accessor or producer of the board data-type
 * 		or numbers of player are all synchronized.  
 * - Every ConnectionHandler is assigned to a client. Since all the 
 *       ConnectionHandlers work on a single instance of the Board, as
 *    	long as the Board is thread-safe, the whole server is thread-safe. 
 */
public class MinesweeperServer {

	private static int PORT = 4444;
	private ServerSocket serverSocket;
	private final boolean DEBUG;
	private Board board;
	private Integer clietNum = 0;
	
	
    /**
     * Make a MinesweeperServer that listens for connections on port.
     * @param port port number, requires 0 <= port <= 65535.
     */
    public MinesweeperServer(int port,boolean debug, Board board) throws IOException {
        serverSocket = new ServerSocket(port);
        this.DEBUG = debug;
        this.board = board;
    }
    
    /**
     * Run the server, listening for client connections and handling them.  
     * Never returns unless an exception is thrown.
     * @throws IOException if the main server socket is broken
     * (IOExceptions from individual clients do *not* terminate serve()).
     */
    public void serve() throws IOException {
        while (true) {
            // block until a client connects
            final Socket socket = serverSocket.accept();
            // sending out hello message
            
            // handle the client
            // start a new thread to handle the connection
            
            
            new Thread(new Runnable() {	
				@Override
				public void run() {
					try {
						incrementPlayer();
						handleConnection(socket);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();;
        }
    }
 
    
    /**
     * Handle a single client connection in this thread.  
     * Returns when client disconnects.
     * 
     * @param socket where client is connected
     * @throws IOException if connection has an error or terminates unexpectedly
     */
    private void handleConnection(Socket clientSocket) throws IOException {
    
    	BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    	PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
    	try {
    		// print out the welcome message to client.
    		out.print("Welcome to Minesweeper. " + clietNum + 
	        		" people are playing including you. Type 'help' for help.");
    		out.flush();
    		
        	for (String line = in.readLine(); line != null; line = in.readLine()) {
        		String output = handleRequest(line,in, out, clientSocket);
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
	 *  Make requested mutations on game state if applicable, 
	 * 		then return appropriate message to the user.
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
     * @param A BuffeRreader of client input stream
     * @param A PrintWriter of client
     * @param clientSocket   
	 * @return a string representation of Response
     * @throws IOException 
	 */
	private String handleRequest(String input,
								BufferedReader in, 
								PrintWriter out, 
								Socket clientSocket) throws IOException {

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
			out.print("-- look -- to check current board\n"
					+ "-- dig x y -- \n"
					+ "-- flag x y -- \n"
					+ "-- deflag x y --\n"
					+ "-- bye -- to check\n"
					+ "Do you need help? \n"
					+ "Please go to http://en.wikipedia.org/wiki/Minesweeper_(video_game)\n");
			// close the client socket
			out.flush();
			
		} else if(tokens[0].equals("bye")) {
			// 'bye' request
			//TODO Question 5
			out.print("See you next time\n");
			// close the client socket
			out.flush();
			out.close();
			in.close();
			clientSocket.close();
			decrementPlayer();
		} else {
			int x = Integer.parseInt(tokens[1]);
			int y = Integer.parseInt(tokens[2]);
			if(tokens[0].equals("dig")) {
				// 'dig x y' request
				//TODO Question 5
				if((x>=0 && x< board.getDim())&&(y >=0 && y< board.getDim())){
					
					// if a square is a bomb
					
					if(board.getState(x, y) == SquareState.UNTOUCHED){
						if(board.isABomb(x, y)){
							out.print("BOOM!!");
							out.flush();
							board = board.Dug(x, y);
							if(!DEBUG){
								out.close();
								in.close();
								clientSocket.close();
								decrementPlayer();
							}
						}else{
							// if a square is not a bomb
							
							board = board.Dug(x, y);
							
							return board.toString();
						}
					}else{
						// return a board if the state of square is FLAGGED or DUG
						
						return board.toString();
					}
					
				}else{
					out.print("("+x+","+y+") is not a valid coordination.\n ");
					out.flush();
					return board.toString();
				}
				
			} else if(tokens[0].equals("flag")) {
				// 'flag x y' request
				//TODO Question 5
				
				if((x>=0 && x< board.getDim())&&(y >=0 && y< board.getDim())){
						if(board.getState(x, y)== SquareState.UNTOUCHED){
							board = board.flag(x, y);
						}else{
							out.print("("+x+","+y+") can not be flagged.\n");
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
				
				if((x>=0 && x< board.getDim())&&(y >=0 && y< board.getDim())){
					if(board.getState(x, y) == SquareState.FLAGGED){
						board = board.deflag(x, y);
					}else{
						out.print("("+x+","+y+") is not flagged.\n");
					}
					return board.toString();
				}else{
					out.println("invalid coordination of ("+x+","+y+")");
					out.flush();
					return board.toString();
				}
				
			}
		}
		//should never get here
		return "";
	}
	
	/**
	 * Increment player number by 1
	 */
	private synchronized void incrementPlayer(){
		clietNum++;
	}
    

	/**
	 * Decrease player number by 1
	 */
	private synchronized void decrementPlayer(){
		clietNum--;
	}
    
    /**
     * Start a MinesweeperServer running on the default port.
     * Handle the input of command line to set the server settings.
     * Arguments might look like :
     * 			./server true
     * 			./server false -s 30
     * 			./server true -f ../testBoard
     * 
     * Protocol grammar of command line:
     *   	ARGS :== DEBUG SPACE ( SIZE | FILE )?
     * 		DEBUG :== "true" | "false"
     * 		SIZE :== SIZE_FLAG SPACE X
     * 		SIZE_FLAG :== "-s"
     * 		X :== INT
     * 		FILE :== FILE_FLAG SPACE PATH
     * 		FILE_FLAG :== "-f"
     * 		PATH :== .+
     * 		INT :== [0-9]+
     * 		SPACE :== "
     * @throws Exception 
     * @throws FileNotFoundException 
     * 
     */

    public static void main(String[] args) throws FileNotFoundException, Exception {
    	
    	Boolean debug = false;
    	Board board = null;
    	Integer size = 10; // default size
       
    	try {
    		if(args!=null){
    			try {
    				PORT = Integer.parseInt(args[0]);
	       			 if (PORT < 0 || PORT > 65535) {
	                        throw new IllegalArgumentException("port " + PORT + " out of range");
	                    }
				} catch (ArithmeticException e) {
					System.err.println("port " + PORT + " is illegal.");				
					}
    			
    			try {
    				if(args[1].equals("true")){
    					debug = true;
    				}
				} catch (SecurityException e) {
					System.err.println("debug setting " + debug + " is illegal.");
				}
        		
        		if(args.length == 2){
        			
        			// Init with a default size
        			
    	    		board = new Board(size);

    	    	}else if(args.length == 4){
    	    		if (args[2].equals("-s")) {
    	    			// init a board with provided size
    	    			try {
    	    				size = Integer.parseInt(args[3]);
    	    				if (size > 0) {
        						board = new Board(size);
        					}else{
        						throw new Exception("size should be greater than 0");
        					}
						} catch (ArithmeticException e) {
							System.err.println("given size " + size + " is illegal.");
						}
    					
    				
    				} else if (args[2].equals("-f")) {
    					
    					// init a board with provided filename
    					
    					String filename = args[3];
    					try {
    						board = new Board(filename);
						} catch (FileNotFoundException e) {
							System.err.println("file "+ filename+ " doesn't exist");
						}
    				}else{
    					throw new IllegalArgumentException("illegal arguments "+args[2]+" "+args[3]);
    				}
    	    	}
    		}
		} catch (IllegalArgumentException e) {
			System.err.println("please enter the correct argument format:"
    				+ "usage: MinesweeperServer [--debug] [--port PORT] [--size SIZE | --file FILE]");
			}
	        try {
	            MinesweeperServer server = new MinesweeperServer(PORT,debug,board);
	            server.serve();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
    }

}