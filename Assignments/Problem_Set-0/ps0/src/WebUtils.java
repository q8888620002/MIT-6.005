import java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Reader;
import java.io.Writer;


public class WebUtils {
	private static final int BUFF_SIZE = 10000;
	/**
	 * Return the contents of the web page identified
	 * by the urlString, "urlString" must be a valid URL.
	 * e.g. fetch("http://www.mit.edu")
	 * returns the MIT home page as a string of HTML.
	 */
	public static String fetch(String urlString)
		throws MalformedURLException, IOException {
		URL url = new URL(urlString);
		
		// open a stream from the web server
		InputStream input = url.openStream();
		InputStreamReader reader = new InputStreamReader(input);
		
		// create a stream that appends data together into a string
		StringWriter writer = new StringWriter();
		
		// copy from the web server stream into the string stream
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
