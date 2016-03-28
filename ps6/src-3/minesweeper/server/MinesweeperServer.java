package minesweeper.server;

import java.net.*;
import java.io.*;

public class MinesweeperServer {

	private final static int PORT = 4444;
	private ServerSocket serverSocket;
    /**
     * Make a MinesweeperServer that listens for connections on port.
     * @param port port number, requires 0 <= port <= 65535.
     */
    public MinesweeperServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
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
     */
    public static void main(String[] args) {
        try {
            MinesweeperServer server = new MinesweeperServer(PORT);
            server.serve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}