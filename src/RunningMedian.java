import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class RunningMedian {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ReadAllfilesInDirectory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void ReadAllfilesInDirectory() throws IOException
	{
		File folder = new File("./wc_input/");
		File[] listOfFiles = folder.listFiles();
		Arrays.sort(listOfFiles);
		List<Double> NoOfwordsinLine = new ArrayList<Double>();
		//List<Double> medianList = new ArrayList<Double>();
	
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
							 String[] sortedwords=line.toLowerCase().split("\\s|,|\\.");
							NoOfwordsinLine.add((double)sortedwords.length);
						}
					
					
					//
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		          //Close the buffer reader
		          bufferReader.close();
		    }
		    
		}
		List<Double> median=MedianCalculation(NoOfwordsinLine);
		 WriteToFileMedianList(median);
		//return NoOfwordsinLine;
	}
	
	public static List<Double> MedianCalculation(List<Double> NoOfwordsinLine)
	{
		List<Double> number= new ArrayList<Double>();
		 List<Double> medianList= new ArrayList<Double>();
		for(int i=0;i<NoOfwordsinLine.size();i++)
		{
			if(i==0)
			{
				medianList.add(NoOfwordsinLine.get(i));
			}
			else
			{
				number.clear();
				for(int j=0;j<=i;j++)
				{
					
					number.add(NoOfwordsinLine.get(j));
					//System.out.println("NoOFwordsinj"+NoOfwordsinLine.get(j));
				}
				Collections.sort(number);
				int middle = (i+1)/2;
				 if ((i+1)%2 == 1) {
				    	medianList.add(number.get(middle));
				    				    	
				    } else {
				       // return (m[middle-1] + m[middle]) / 2.0;
				    	int median1=middle-1;
				    	int median2=middle;
				    	
				    	double mean=(number.get(median1)+number.get(median2))/2.0;
				    	medianList.add(mean);
				    }                           
			}
		}
		
		return medianList;
	}
	
		
	
	public static void  WriteToFileMedianList(List<Double> medianList)
	{
								
		try {
			 
			File file = new File("./wc_output/med_result.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			String newline = System.getProperty("line.separator");
			
	
	for (int j=0; j<medianList.size(); j++) {
		String strMedian = String.format("%-10s", medianList.get(j));
		bw.write(strMedian+newline);
		//System.out.println("medianList"+medianList.get(j));
	
	}
	
	bw.close();
	 
	System.out.println("Done");

	} catch (IOException e) {
		e.printStackTrace();
	}		//bw.write(content);
					}


}
