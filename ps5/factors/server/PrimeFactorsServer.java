package factors.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import util.FindPrimeFactor;



/**
 *  PrimeFactorsServer performs the "server-side" algorithm 
 *  for counting prime factors.
 *
 *  Your PrimeFactorsServer should take in a single Program Argument 
 *  indicating which port your Server will be listening on.
 *      ex. arg of "4444" will make your Server listen on 4444.
 *      
 *  Your server will only need to handle one client at a time.  If the 
 *  connected client disconnects, your server should go back to listening for
 *  future clients to connect to.
 *  
 *  The client messages that come in will indicate the value that is being
 *  factored and the range of values this server will be processing over.  
 *  Your server will take this in and message back all factors for our value.
 *  
 *  
 *  Server-to-Client Message Protocol 
 *  Protocol := Message* 
 *  Message := Found Space N Space Factor NewLine |
 *   			Done Space N Space LowBound Space HighBound NewLine |
 *   			Invalid NewLine 
 *  Found := found
 *  Done := done 
 *  Invalid := invalid 
 *  N := Number 
 *  Factor := Number 
 *  LowBound := Number 
 *  HighBound := Number 
 *  Number := [0-9]+ 
 *  Space := " " // " " is a single space character 
 *  NewLine := \n
 */
public class PrimeFactorsServer {
        
    
    
    public static final int defaultPortNum = 4444;
    
    private static int portNumber;
    private ServerSocket primeServerSocket;
    
    /**
     * Make a server socket listen to the connection on the port
     * @param a port number that server listen to 
     */
    
    
    public PrimeFactorsServer(int port){
    	try {
    		primeServerSocket = new ServerSocket(port);
		} catch (Exception e) {
			System.err.println("cannot connect to port"+ port);
			System.err.println(e);

		
		}
    }
    
    /**
     * check the number of arguments 
     * @param argus, an string array of port number 
     *
     */
    private static void check(String[] args){
    	assert args.length <= 1:"multiple port number error";
    }
    
    /**
     * Server will connect to the socket and handle the client.
     * @throws IOException 
     */
    public void serve() throws IOException{
		Socket cSocket = primeServerSocket.accept();
	    	try{
	    		System.err.println("client connected");
	    		find(cSocket);
	    	}catch(Exception exception){
	            exception.printStackTrace();
	    	}finally {
				cSocket.close();
				System.err.println("socket closed");
			}
    	
    }
    /**
     * Handle the client connection. 
     * Response to the prime factor of the input stream N.
     * Returns when client disconnect.
     * @param client
     * @throws IOException
     */
    
    private void find(Socket client) throws IOException {
		
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			
			PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
			
		try {
			String input = in.readLine();
    		while (input != null) {
    			
				String[] inputs = input.replaceAll("[A-Za-z]", "").split("[\\s+]",4);
				// find prime factor of N or send Invalid to client 
				try {
					BigInteger N = new BigInteger(inputs[1]);
					BigInteger low = new BigInteger(inputs[2]);
					BigInteger hi = new BigInteger(inputs[3]);
					
					List<BigInteger> result = FindPrimeFactor.findPrimeFactor(N, hi, low);
						for(int i = 0; i < result.size();i++){
							out.print("found "+N+" "+result.get(i)+"\n");
							out.flush();
						}
					out.print("done "+N+" "+low+" "+hi+"\n");
					out.flush();
				} catch (Exception e) {
					out.print("Invalid\n");
					out.flush();
					e.printStackTrace();
					}
				
				input = in.readLine();
    		   }
			} finally{
				in.close();
				out.close();
			}	
	}
    

	/**
     * @param args String array containing Program arguments.  It should only 
     *      contain one String indicating the port it should connect to.
     *      Defaults to port 4444 if no Program argument is present.
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        // TODO Complete this implementation.
    	check(args); 
    	
    	if (args.length == 1){
	      		portNumber = (Integer.parseInt(args[0]));
	  		}else if(args.length == 0){
	  			portNumber = defaultPortNum;
	  		}else{
	  			throw new Exception("port number known error");
	  		}
    	
    	try {
    		PrimeFactorsServer primeServerSocket = new  PrimeFactorsServer(portNumber);
    		primeServerSocket.serve();
		} catch (Exception e) {
			 e.printStackTrace();
		}
    }
    
    
   
    
    
}
