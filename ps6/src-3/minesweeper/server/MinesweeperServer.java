package minesweeper.server;

import java.net.*;

import minesweeper.Board;

import java.io.*;

public class MinesweeperServer {

	private static int PORT = 4444;
	private ServerSocket serverSocket;
	private final boolean DEBUG;
	private final Board board;
	
	
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
          final  Socket socket = serverSocket.accept();
            
            // handle the client
            // start a new thread to handle the connection
            new Thread(new ConnectionHandler(socket)).start(); 				
        }
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
     * @throws IOException 
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
    				debug = Boolean.getBoolean(args[1].toLowerCase());
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