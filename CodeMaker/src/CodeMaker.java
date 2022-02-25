
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CodeMaker {

	static String[] fileContents;

	public static void main(String[] args) {
		int cipherCode =  (int) (Math.random() * 25) + 1;

		fileContents = getFileContents("message.txt");
		writeFileContents(fileContents, cipherCode);
	}

	public static String[] getFileContents(String fileName) {

		String[] contents = null;
		int length = 0;

		try {

			// input
			String folderName = "/subFolder/"; // if the file is contained in .class filemake
			// this equal to the empty string
			String resource = fileName;

			// this is the path within the jar file
			InputStream input = CodeMaker.class.getResourceAsStream(folderName + resource);
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

	public static void writeFileContents(String[] contents, int cipher) {
		FileWriter myWriter;
		try {
			myWriter = new FileWriter("cipher.txt");

			// read in contents
			for (String str : contents) {
				
				// adds ciphertext
				String temp = "";
				for (int i = 0; i < str.length(); i++) {
					// for each character in the string
					char c = str.charAt(i);

					// cipher if it is letter
					if (Character.isLetter(c)) {
						int intAfterCipher = c - 'a' + cipher;
						intAfterCipher = intAfterCipher % ('z' - 'a' + 1) + 'a';
						temp += (char) intAfterCipher;
					} else { // Keep spaces and punctuation as is
						temp += c;
					}
				}
				myWriter.write(temp);
				System.out.println(temp);
				myWriter.write(System.getProperty("line.separator"));
			}
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}



