import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
	static String fileContents[];
	static String[] saveArray = new String[25];
	static int shift = 1;
	public static void main(String[] args) {
		getFileContents("cypher.txt");
		writeArrayToFile(fileContents,saveArray);
	}//main
	
	public static String[] getFileContents(String fileName) {

		String[] contents = null;
		int length = 0;

		try {

			// input
			String folderName = "/subFolder/"; // if the file is contained in .class filemake
			// this equal to the empty string
			String resource = fileName;

			// this is the path within the jar file
			InputStream input = Main.class.getResourceAsStream(folderName + resource);
			if (input == null) {
				// this is how we load file within editor (eg eclipse)
				input = new FileInputStream(resource);
			} // end of if
			BufferedReader in = new BufferedReader(new InputStreamReader(input));

			in.mark(Short.MAX_VALUE); // see api

			while (in.readLine() != null) {
				length++;
			}

			in.reset(); // rewind the reader to the start of file
			contents = new String[length]; // give size to contents array

			// read in contents of file and print to screen
			// --------------------------------------------
			for (int i = 0; i < length; i++) {
				contents[i] = in.readLine().toLowerCase();
			} // end of for
			in.close();

			// catch any exception
			// -------------------
		} catch (Exception e) {
			System.out.println("File Input Error");
		} // end of try/catch
		for (int i = 0; i < contents.length; i++) {
			System.out.println(contents[i]);
		}
		return contents;

	} // getFileContents
	public static void writeArrayToFile(String [] contents,String [] saveArray) {
        try {

           // output file pointer
       	 String temp = "";
           // write array to file
           for (String str : contents) {
           	temp = "";
           	for(int i = 0; i < str.length(); i++) {
           		char letter = str.charAt(i);
           		if((int)letter < 123 && (int)letter > 96) {
	            		int AfterShift = letter - 'a' + shift;
	            		AfterShift = AfterShift % ('z' - 'a' + 1) + 'a';
	            		temp += (char)AfterShift;
           		}//if
           		else {
           			temp += letter;
           		}//else
           		saveArray[i] = temp;
           	}//for
           	System.out.println(saveArray);
           } // for
           
       } catch (Exception e) {
           System.out.println("File Output Error");
       }

    } // writeArrayToFile
}//Main
