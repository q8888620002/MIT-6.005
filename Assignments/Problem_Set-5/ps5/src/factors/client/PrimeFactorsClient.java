package factors.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	// Rep invariant: clientSocket, in, out != null
	
	/**
	 * Make a prime factors client and connect it to a server running on
	 * hostname at the specified port.
	 * @param hostname the host on which a server runs
	 * @param port port number, requires 0 <= port <= 65535
	 */
	public PrimeFactorsClient(String hostname, int port) throws IOException {
		clientSocket = new Socket(hostname, port);
		in = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintWriter(
				new OutputStreamWriter(clientSocket.getOutputStream()));
	}
    
	/**
	 * Establish connection between this client and multiple servers.
	 * @param servers a list of server addresses, e.g. {"localhost:4444", "localhost:4445"}
	 * @return a list of clients connected to those specified servers
	 * @throws IOException on server or network failure
	 */
	private static PrimeFactorsClient[] establishConnection(String[] servers)
			throws IOException {
		int serverAmount = servers.length;
    	PrimeFactorsClient[] clients = new PrimeFactorsClient[serverAmount];
    	for (int i = 0; i < serverAmount; i = i + 1) {
    		clients[i] = new PrimeFactorsClient(
    				getHostname(servers[i]), getPort(servers[i]));
    	}
    	return clients;
	}
	
	/**
	 * Given a server address, extract its hostname.
	 * @return the hostname of a server address
	 */
	private static String getHostname(String server) {
		return server.substring(0, server.indexOf(':'));
	}
	
	/**
	 * Given a server address, extract its port number.
	 * @return the port number of a server address
	 */
	private static int getPort(String server) {
		return Integer.parseInt(
				server.substring(server.indexOf(':') + 1));
	}
	
	/**
	 * Handle the user's request, in Read-Evaluate-Print Loop (REPL) way.
	 * @param clients sub-clients in connection with different servers
	 * @throws IOException on server or network failure
	 */
	private static void handleUserRequests(PrimeFactorsClient[] clients) 
			throws IOException {
		BufferedReader stdIn = new BufferedReader(
				new InputStreamReader(System.in));
		displayPrelude();
		String userInput = stdIn.readLine();
		while (userInput != null) {
			if (userInput.matches("\\s*quit\\s*")) {
				// user want to quit this conversation
				for (int i = 0; i < clients.length; i = i + 1) {
					clients[i].close();
				}
				System.exit(0);
			} else {
				// STEP1: Validate the user's request
				if (validateUserRequest(userInput) == false) {
					// warn the user of invalid input
					displayResult("invalid");
				}
				
				// STEP2: Dispatch the computation over different servers
				BigInteger N = new BigInteger(userInput.trim());
				dispatch(N, clients);
				
				// STEP3: Accumulate results from different servers
				BigInteger[] primeFactors = accumulate(N, clients);
				
				// STEP4: Show the user the final result
				StringBuilder evaluationResult = new StringBuilder(
						N.toString() + "=");
				int i = 0;
				while (i < primeFactors.length - 1) {
					evaluationResult.append(
							new String(primeFactors[i].toByteArray()) + "*");
					i = i + 1;
				}
				evaluationResult.append(new String(
						primeFactors[primeFactors.length - 1].toByteArray()));
				displayResult(evaluationResult.toString());
				
				userInput = stdIn.readLine();
			}
		}
	}
	
	private static void displayPrelude() {
		System.out.print("Welcome to the Prime Factoring System.\n");
		System.out.print("The primary purpose of this system is to help find out\n");
		System.out.print("all the prime factors of an arbitrarily large positive\n");
		System.out.print("integer N (N >= 2) you provide.");
		System.out.print("A sample conversation is shown:\n");
		System.out.print("\n");
		System.out.print("264[RETURN]\n");
		System.out.print(">>> 264=2*2*2*3*11\n");
		System.out.print("\n");
		System.out.print("[RETURN] stands for the return key on your keyboard.\n");
		System.out.print("To quit this system, just type in:\n");
		System.out.print("quit[RETURN]\n");
		System.out.print("\n");
	}
	
	/**
     * Validate if the client's request is legal, according to Client-to-Server
     * message protocol.
     * @return true if the client's request is in a legitimate form, otherwise
     * 		   return false.	   
     */
	private static boolean validateUserRequest(String request) {
    	Pattern protocol = Pattern.compile("\\s*\\d+\\s*");
    	Matcher protocolMatcher = protocol.matcher(request);
    	if (protocolMatcher.find()) {
    		String req = request.trim();
    		BigInteger reqVal = new BigInteger(req);
    		if (! (reqVal.compareTo(BigInteger.valueOf(2)) == -1)) {
    			// N should be >= 2
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return false;
    	}
    }
	
	/**
	 * Partition and dispatch the prime factor searching over servers.
	 * @param N the number to be factored in to primes, assumed to be valid
	 * @param clients sub-clients in connection with different servers
	 * @throws IOException on server or network failure
	 */
	private static void dispatch(BigInteger N, PrimeFactorsClient[] clients) 
			throws IOException {
		int m = clients.length;		// amount of servers
		BigInteger low;
		BigInteger high;
		BigInteger averageWorkload = BigMath.sqrt(N).divide(
				BigInteger.valueOf(m));	// average workload for a server
		for (int i = 0; i < m; i = i + 1) {
			// for simplicity, we denote serverAmount as m
			if (i == 0) {	// searching interval [2, sqrt(N)/m]
				low = BigInteger.valueOf(2);
				high = averageWorkload;
			} else if (i == m-1) {
				// searching interval [((m-1)*sqrt(N)/m)+1, sqrt(N)]
				low = averageWorkload.multiply(BigInteger.valueOf(i)).add(
						BigInteger.ONE);
				high = BigMath.sqrt(N);
			} else {
				// searching interval [(i*sqrt(N)/m)+1, (i+1)*sqrt(N)/m], where
				// 0 < i < m-1
				low = averageWorkload.multiply(BigInteger.valueOf(i)).add(
						BigInteger.ONE);
				high = averageWorkload.multiply(BigInteger.valueOf(i+1));
			}
			// send computation request (N, low, high)
			clients[i].sendRequest(N, low, high);
		}
	}
	
	/**
	 * Send the prime factor searching request to the server. Require this is "open"
	 * @param N the number to be factored in to primes
	 * @param low the lower bound of prime factors
	 * @param high the upper bound of prime factors
	 * @throws IOException on server or network failure
	 */
	private void sendRequest(BigInteger N, BigInteger low, BigInteger high) 
			throws IOException {
		String requestMessage;
		String nStr = N.toString();
		String lowStr = low.toString();
		String highStr = high.toString();
		requestMessage = "factor" + " " + nStr + " " + lowStr + " " + highStr;
		out.print(requestMessage + "\n");
		out.flush();	// important! make sure message actually gets sent
	}
	
	/**
	 * Accumulate the prime factors return from distributed servers.
	 * @param N the number to be factored in to primes, assumed to be valid
	 * @param clients sub-clients in connection with different servers
	 * @return an array that contains prime factors accumulated from different
	 * 		   servers.
	 * @throws IOException on server or network failure
	 */
	private static BigInteger[] accumulate(BigInteger N, PrimeFactorsClient[] clients)
			throws IOException {
		int m = clients.length;	// amount of servers
		List<BigInteger> primeFactors = new ArrayList<BigInteger>();
		for (int i = 0; i < m; i = i + 1) {
			// accumulate prime factors this server found into prime factors collection
			List<BigInteger> factors = clients[i].getPrimeFactors();
			Iterator<BigInteger> factorIter = factors.iterator();
			while (factorIter.hasNext()) {
				primeFactors.add(factorIter.next());
			}
		}
		Iterator<BigInteger> iter = primeFactors.iterator();
		while (iter.hasNext()) {
			N = N.divide(iter.next());
		}
		if (N.compareTo(BigInteger.ONE) == 1) {
			primeFactors.add(N);
		}
		return primeFactors.toArray(new BigInteger[0]);
	}
	
	/**
	 * Return all the prime factors a server found in its searching interval.
	 * @throws IOException on server or network failure
	 */
	private List<BigInteger> getPrimeFactors() throws IOException {
		List<BigInteger> primeFactors = new ArrayList<BigInteger>();
		String line = in.readLine();
		if (line == null) {
			// CASE1: no reply has been sent to this client yet, thus
			// 		  the server is still in searching. Try again!
			return getPrimeFactors();
		}
		while (line != null) {
			if (line.startsWith("done")) {
				// CASE2: the server has finished in searching for prime factors
				break;
			} else {
				// CASE3: the server found a prime factor
				String[] foundSignal = line.split("\\s");
				String primeFactorStr = foundSignal[2];
				BigInteger primeFactor = new BigInteger(primeFactorStr.getBytes());
				primeFactors.add(primeFactor);
			}
			line = in.readLine();
		}
		return primeFactors;
	}
	
	/**
	 * Show user the result of evaluation, which is either a decomposition of
	 * the request number or a warning of invalid input. Requires this is "open"
	 */
	private static void displayResult(String result) {
		System.out.print(">>> " + result  + "\n");
	}
	
	/**
	 * Closes the client's connection to the server.
	 * This client is now closed. Requires this is "open"
	 * @throws IOException if close fails
	 */
	public void close() throws IOException {
		out.close();
		in.close();
		clientSocket.close();
	}
	
    /**
     * @param args String array containing Program arguments.  Each String indicates a 
     *      PrimeFactorsServer location in the form "host:port"
     *      If no program arguments are inputted, this Client will terminate.
     * @throws IOException on server or network failure
     */
    public static void main(String[] args) throws IOException {
    	String[] servers = args;
    	PrimeFactorsClient[] clients = establishConnection(servers);
    	handleUserRequests(clients);
    }
}
