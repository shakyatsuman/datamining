/*
 * ss0113FileUtil.java
 * Suman Shakya
 * 09/09/2013
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Utility class that provide functions for writing text data into file
 */
public class ss0113FileUtil {

	private BufferedWriter writer = null;

	/*
	 * Class constructor that starts connection to the BufferedWriter
	 * to write text into file.
	 */
	public ss0113FileUtil(String filename) {
		try {
			if (writer == null)
				writer = new BufferedWriter(new FileWriter(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Writes given string in a single line of file
	 */
	public void writeLine(String line){
		try {
			writer.write(line);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Writes a blank line in file
	 */
	public void writeBlankLine(){
		try {
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Closes connection to the writer
	 */
	public void close(){
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
