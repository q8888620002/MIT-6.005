package factors.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


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
    
	private  Socket clientSocket;
	private  BufferedReader in;
	private  PrintWriter out;
	private static int serverNum;
	private BufferedReader typeIn;
	private static List<String> results;
	
	/**
	 * Make connection to a Server running on
	 * @param hostname
	 * @param port
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public PrimeFactorsClient(String hostname,int port) throws UnknownHostException, IOException{
		clientSocket = new Socket(hostname, port);
		in = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintWriter(
				new OutputStreamWriter(clientSocket.getOutputStream()));
	}
	
	/**
	 * Send request to Server and distribute the low and high value according to current server number
	 * @param current index of the server
	 * @throws IOException 
	 * 
	 */

	public void find(int currentServer) throws IOException {
		typeIn = new BufferedReader(new InputStreamReader(System.in));
		String userType = typeIn.readLine();
		
		while(userType!=null){
			BigInteger inputs = new BigInteger(userType.trim());
			BigInteger serverNumber = BigInteger.valueOf(serverNum);
			BigInteger hiDivideN = BigMath.sqrt(inputs).divide(serverNumber);
			BigInteger currentNum  = BigInteger.valueOf(currentServer);
			BigInteger hi = hiDivideN.multiply(currentNum);
			BigInteger low = hiDivideN.multiply(currentNum.add(new BigInteger("1")));
				
			try {
					if(currentServer == 1){	low = new BigInteger("2");}
					String outLine = "factor "+ inputs+ " "+low+" "+ hi+"\n";
					System.err.println(currentServer+outLine);
					out.print(outLine);
					out.flush();
					
					String reply = in.readLine();
					while (reply!=null) {
						if(reply.startsWith("done")){
							break;
						}
						String[] primeFactor = reply.split(" ",3);
						results.add(primeFactor[2]);
						reply = in.readLine();
					}
					System.err.println(results.toString());
			} catch (Exception e) {
					e.printStackTrace();
				}
			userType = typeIn.readLine();
		}
		in.close();
		out.close();
		clientSocket.close();
	
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
    	results = new ArrayList<String>();
    	
    	
    	for(int i = 0; i< args.length ;i++){
    		String[] address = args[i].trim().split(":",2);
    		try {
    			int port = Integer.parseInt(address[1]);
				PrimeFactorsClient client  = new PrimeFactorsClient(address[0], port);
				client.find(i+1);	
				client.close();
    		}catch (Exception e) {
    			System.err.println("Couldn't get I/O for the connection" );
    			System.exit(1);
    		}
    	}
    	
    	
    }
    
    /**
     * Close the socket of the client
     * @throws IOException
     */
	public void close() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}




    
}
