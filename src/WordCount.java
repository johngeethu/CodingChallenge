import java.io.*;
import java.util.*;


public class WordCount {
	public static void main(String[] args)  {
		/*
		 * This method reads all the lines in the
		 * files contained in the directory in 
		 * alphabetical order
		 * It returns a string with all the appended line
		 */
		String sentence="";
		try {
			sentence = ReadAllfilesInDirectory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * This method reads the sentence and splits them
		 * into words and returns a list with all the words
		 */
		List<String> sortedList=CountWords(sentence);
		/*
		 * This method counts all the word by converting the
		 *  list to a TreeSet and then counts the word by an
		 *  inbuilt method called frequency in Collection and
		 *  then writes this to a file.
		 */
		WriteToFile(sortedList);
	}
	
	public static String ReadAllfilesInDirectory() throws IOException
	{
		File folder = new File("./wc_input/");
		File[] listOfFiles = folder.listFiles();
		Arrays.sort(listOfFiles);
		String wordsinfile="";
	
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		       // System.out.println(file.getName());
		        
		        FileReader inputFile = new FileReader(file);

		          //Instantiate the BufferedReader Class
		          BufferedReader bufferReader = new BufferedReader(inputFile);

		          //Variable to hold the one line data
		          String line;

		          // Read file line by line and print on the console
		          try {
					while ((line = bufferReader.readLine()) != null)   {
						//System.out.println(line);
						wordsinfile+= line;
					  }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          //Close the buffer reader
		          bufferReader.close();
		    }
		    
		}
		return wordsinfile;
	}
	
	public static List<String> CountWords(String sentence)
	{
		 //StringBuilder strBuilder = new StringBuilder();
		 String[] sortedwords=sentence.toLowerCase().split("\\s|,|\\.");
		 
		// Arrays.sort(sortedwords);
		 List<String> list = new ArrayList<String>();
		 for (String word : sortedwords	 )
		 {
	            list.add(word);
	      }
		return list;
	}
	
	public static void WriteToFile(List<String> list)
	{
		//Set<String> unique = new HashSet<String>(list);
		TreeSet<String> sortedSet= new TreeSet<String>(list);
	
		try {
			 
			File file = new File("./wc_output/wc_results.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			String newline = System.getProperty("line.separator");
			
			for (String key : sortedSet) {
				String padded = String.format("%-10s", key);
		
				bw.write(padded + "         " + Collections.frequency(list, key)+newline);
			}
			//bw.write(content);
			bw.close();
 
			System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
