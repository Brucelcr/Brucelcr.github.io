import java.io.*;

public class CodeMaker {
	static int shift =  (int) (Math.random() * 25) + 1;;
	static String fileContents[];
    public static void main(String[] args) {

    	fileContents = getFileContents("message.txt");
    	
    	 shift =  (int) (Math.random() * 25) + 1;
    	 writeArrayToFile("cipher.txt",fileContents);
               
       

     } // main
  
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

     // writes the array a to fileName, one array element per line in the file
     public static void writeArrayToFile(String fileName, String [] contents) {
         try {

            // output file pointer
        	 String temp = "";
            // write array to file
            for (String str : contents) {
            	
            	for(int i = 0; i < str.length(); i++) {
            		char letter = str.charAt(i);
            		if((int)letter < 123 && (int)letter > 96) {
	            		int AfterShift = letter - 'a' + shift;
	            		AfterShift = AfterShift % ('z' - 'a' + 1) + 'a';
	            		temp += (char)AfterShift;
            		}
            		else {
            			temp += letter;
            		}
            	}               
            } // for
            System.out.println(temp);
        } catch (Exception e) {
            System.out.println("File Output Error");
        }

     } // writeArrayToFile


}  // CodeMaker





