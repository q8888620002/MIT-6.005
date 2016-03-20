package echo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * A simple server that will echo client inputs.
 * 
 */
public class EchoServer {
    	private static ServerSocket serverSocket;
        // default port number where the server listens for connections
    	public static final int defaultPortNum = 4444;
    	
    	
    	   /**
         * @param args String array containing Program arguments.  It should only 
         *      contain at most one String indicating the port it should connect to.
         *      The String should be parseable into an int.  
         *      
         */
    	public static void main(String[] args) throws IOException {
    	    //TODO complete this implementation.
    		int PortNum = 0;
    		/*
    		 * If there is no Program Argument, default server to listen to port 4444
    		 */
    			if(args.length >1){
    				System.err.println("Usage: EchoServer <port number>");
    				System.exit(1);
    				}else if(args.length == 0){
    					PortNum = defaultPortNum;
    				}else{
    					PortNum = Integer.parseInt(args[0]);
    				}
    			
    			 try {
    		            EchoServer server = new EchoServer(PortNum);
    		            server.serve();
    		        } catch (IOException e) {
    		            e.printStackTrace();
    		        }
    		}
    	
    	/**
    	 * If no arguments, we default to port 4444.
    	 * @throws IOException
    	 */
    	public static void main() throws IOException {
    	    //TODO complete this implementation.
    		int PortNum = defaultPortNum;
    		/*
    		 * If there is no Program Argument, default server to listen to port 4444
    		 */		
    			 try {
    		            EchoServer server = new EchoServer(PortNum);
    		            server.serve();
    		        } catch (IOException e) {
    		            e.printStackTrace();
    		        }
    		}
    	
    	
    	/**
    	 * Constructor for ServerSocket
    	 * @param port number 
    	 */
    	public EchoServer(int port) throws IOException {
    		try {
    			 serverSocket = new ServerSocket(port);
			} catch (Exception e) {
				/*
				 * If server fails to listen on the assigned port, terminate the server
				 */
					System.out.println("Exception caught when trying to listen on port "
		                + port + " or listening for a connection");
		            System.out.println(e.getMessage()); 
		            System.exit(port);
			}
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
            Socket socket = serverSocket.accept();
            try {
                echo(socket);
            } catch (IOException e) {
                e.printStackTrace(); // but don't terminate serve()
            } finally {
                socket.close();
            }
        }
    }
    
    /**
     * 	Handle a single client connection. Echo message from client. 
     *  Returns when client disconnects.
     * @param socket  socket where client is connected
     * @throws IOException if connection has an error or terminates unexpectedly
     */
	private void echo(Socket socket) throws IOException{
		System.err.println("client connected");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		
			try {
				String outLine = in.readLine();
				
				while(outLine!=null){
					/*
					 * echo to client
					 */
					System.out.print("from client:" + outLine+"\n");
					out.print(outLine+"\n");
					out.flush();
					outLine = in.readLine();
				}
			} finally {
				out.close();
				in.close();
			}
		
	}
	
	
		
}
