package utils;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class FileUtils {
	private static final int BUFF_SIZE = 10000;
	
	/**
	 * Return the contents of the file identified by 
	 * the file name, "fileName" must be a valid file path.
	 */
	public static String fetch(String filename) throws IOException {
		FileReader fr = new FileReader(filename);
		
		// open a stream from the file
		BufferedReader reader = new BufferedReader(fr);
		
		// create a stream that appends data together into a string
		StringWriter writer = new StringWriter();
		
		// copy from the file reader stream into the string stream
		copyStream(reader, writer);
		
		// return the string we create
		return writer.toString();
	}
	
	/**
	 * Copies all data from the "from" stream to the
	 * "to" stream, then closes both streams.
	 * Throws IOException if any error occurs.
	 */
	public static void copyStream(Reader from, Writer to)
		throws IOException {
		try {
			char[] buff = new char[BUFF_SIZE];
			// any size buffer would work, but bigger
			// performs better
			int n = from.read(buff);
			while (n != -1) {
				to.write(buff, 0, n);
				n = from.read(buff);
			}
		} finally {
			from.close();
			to.close();
		}
	}
}
