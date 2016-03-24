package factors.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.net.UnknownHostException;

import com.sun.jndi.cosnaming.IiopUrl.Address;
import com.sun.xml.internal.bind.v2.model.core.TypeInfo;

import util.BigMath;

/**
 *  PrimeFactorsClient class for PrimeFactorsServer.  
 *  
 *  Your PrimeFactorsClient class should take in Program arguments space-delimited
 *  indicating which PrimeFactorsServers it will connect to.
 *      ex. args of "localhost:4444 localhost:4445 localhost:4446"
 *          will connect the client to PrimeFactorsServers running on
 *          localhost:4444, localhost:4445, localhost:4446 
 *
 *  Your client should take user input from standard input.  The appropriate input
 *  that can be processed is a number.  If your input is not of the correct format,
 *  you should ignore it and continue to the next one.
 *  
 *  Your client should distribute to each server the appropriate range of values
 *  to look for prime factors through.
 */
public class PrimeFactorsClient {
    
	private  Socket client;
	private  BufferedReader in;
	private  PrintWriter out;
	private static int serverNum;
	private BufferedReader typeIn;
	/**
	 * Make connection to a Server running on
	 * @param hostname
	 * @param port
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public PrimeFactorsClient(String hostname,int port) throws UnknownHostException, IOException{
		client = new Socket(hostname, port);
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream());
	}
	
	/**
	 * Send request to Server and distribute the low and high value according to current server number
	 * @param current index of the server
	 * @throws IOException 
	 * 
	 */

	private void find(int currentServer) throws IOException {
		typeIn = new BufferedReader(new InputStreamReader(System.in));
		String userType = typeIn.readLine();
		
		BigInteger inputs = new BigInteger(userType.trim());
		//BigInteger hi = BigMath.sqrt(inputs).divide(serverNum);
		System.err.println(inputs);
		while(userType!=null){
				try {
		//			out.print("factor "+inputs+" "+hi);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
		}
		
	}
	
    /**
     * @param args String array containing Program arguments.  Each String indicates a 
     *      PrimeFactorsServer location in the form "host:port"
     *      If no program arguments are inputted, this Client will terminate.
     */
    public static void main(String[] args) {
        // TODO complete this implementation.
    	if(args.length == 0){
    		System.err.println("please enter a propiate  host:port");
    		System.exit(1);
    	}
    
    	serverNum = args.length;
    	try {
    	for(int i = 0; i< args.length ;i++){
    		int index = i;
    		String[] address = args[index].trim().split(":",2);
    	
    		int port = Integer.parseInt(address[1]);
    		
				PrimeFactorsClient client  = new PrimeFactorsClient(address[0], port);
				//client.find(i);	
    		} 
    	}catch (Exception e) {
			System.err.println("Couldn't get I/O for the connection" );
			System.exit(1);
		}
    	
    }




    
}
