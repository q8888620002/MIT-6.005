package factors.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    
	private  final Socket clientSocket;
	private  final BufferedReader in;
	private  final PrintWriter out;
	
	
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

	private List<String> find(BigInteger inputs,BigInteger low, BigInteger high) throws IOException {
		List<String> results= new ArrayList<String>() ;
		
		try {
					String outLine = "factor "+ inputs+ " "+low+" "+ high+"\n";
			
					out.print(outLine);
					out.flush();
					String reply = in.readLine();
					while (reply!=null) {
						if(reply.startsWith("done")){
							break;
						}else{
							String[] primeFactor = reply.split(" ",3);
							results.add(primeFactor[2]);
							reply = in.readLine();
						}
					}
					
			} catch (Exception e) {
					System.err.println("error occured while looking for prime factor within"+low+"~"+high);
					e.printStackTrace();
				}
		return results;
			
	}
	
	

	
	
    /**
     * @param args String array containing Program arguments.  Each String indicates a 
     *      PrimeFactorsServer location in the form "host:port"
     *      If no program arguments are inputted, this Client will terminate.
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        // TODO complete this implementation.
    	if(args.length == 0){
    		System.err.println("please enter a propiate  host:port");
    		System.exit(1);
    	}
    
    	ArrayList<PrimeFactorsClient> clients = new ArrayList<PrimeFactorsClient>();
    	/*
    	 * make connection to the server
    	 */
    	for(int i = 0; i< args.length ;i++){
    		String[] address = args[i].trim().split(":",2);
    		try {
    			int port = Integer.parseInt(address[1]);
				PrimeFactorsClient client  = new PrimeFactorsClient(address[0], port);
				clients.add(client);
    		}catch (Exception e) {
    			System.err.println("Couldn't get I/O for the connection" );
    			System.exit(1);
    		}
    	}
    	Handle(clients);
    }
    
    private static void Handle(ArrayList<PrimeFactorsClient> clients) throws IOException {
    	BufferedReader typeIn = new BufferedReader(new InputStreamReader(System.in));
		String userType = typeIn.readLine();
		
	
		try {
			while(userType!=null){
				List<String> results = new ArrayList<String>();
				BigInteger inputs = new BigInteger(userType.trim());
				BigInteger serverNumber = BigInteger.valueOf(clients.size());
				BigInteger fraction = BigMath.sqrt(inputs).divide(serverNumber);
				BigInteger low; 
				BigInteger high; 
				
				
				for(int i = 1;i< clients.size()+1;i++){
					if(i== 1){
						low = new BigInteger("2"); 
						high = fraction;
					}else{
						low = fraction.multiply(BigInteger.valueOf(i-1)).add(new BigInteger("1"));
						high = fraction.multiply(BigInteger.valueOf(i));
					}
					
					results.addAll(clients.get(i-1).find(inputs,low,high));
				}
				
				StringBuilder stringBuilder = new StringBuilder(">>> ");
				for(String s:results){
					if(results.indexOf(s)==results.size()-1){
						stringBuilder.append(s);
					}else{
						stringBuilder.append(s+"*");
					}
				}
				System.out.println(stringBuilder.toString());
				userType = typeIn.readLine();
			}
		} catch (Exception e) {
			System.out.println(">>>Invaild");
		//	e.printStackTrace();
		}finally {
			for(PrimeFactorsClient c:clients){
				c.close();
			}
		}
		
    }
    
	/**
     * Close the socket, inputStream , and outputStream of the client
     * @throws IOException
     */
	public void close() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
	}




    
}
