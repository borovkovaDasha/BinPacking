import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class main {

	static String[] results;
	
	public static void listFilesForFolder(final File folderInput, final File folderOut) {
		int i = 0;
	    for (final File fileEntry : folderInput.listFiles()) {
	    	String data = readFile(folderInput.getAbsolutePath() + "//" + fileEntry.getName(), i);
	    	writeFile(folderOut, i, data);
	    	i++;
	    }
	}
	
	public static void main(String[] args) {
		final File folderInput = new File("D://data_for_binpacking//bin3//");
		final File folderOutput = new File("D://data_for_binpacking//bin3out//");
		readResults("D://data_for_binpacking//resultsbin3.txt");
		listFilesForFolder(folderInput, folderOutput);

	}
	
	public static String readFile(String name, int num) {
		BufferedReader br = null;
		FileReader fr = null;
		String data = null;
		
		try {
			fr = new FileReader(name);
			br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader(name));
			int i = 0;
			
			while ((sCurrentLine = br.readLine()) != null) {
				if (i == 0) {
					data = data + "Number of elements: " + sCurrentLine + "\n";
				}
				else if (i == 1) {
					data = data + "Bin size: " + sCurrentLine + "\n";
					data = data + "Num of Bins: " + results[num] + "\n";
				}
				/*else if (i == 2) {
					data = data + "Num of Bins: " + results[num] + "\n";
				}*/
				else {
					data = data + sCurrentLine + "\n";
				}
				i++;
			}
		} catch (IOException e) {
				e.printStackTrace();
				
			} finally {
				try {
					if (br != null)
						br.close();
					
					if (fr != null)
						fr.close();

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		return data;
	}
	
	public static void writeFile(final File folderOut, int num, String data) {
	    String path = folderOut.getAbsolutePath() + "//" + (num+1) + ".txt";
	    FileWriter writer;
		try {
			writer = new FileWriter(path);
		    writer.write(data); 
		    writer.flush();
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void readResults(String path) {
		BufferedReader br = null;
		FileReader fr = null;
		int i = 0;
		results = new String[780];
		
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path));
			
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				results[i] = sCurrentLine;
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (br != null)
					br.close();
				
				if (fr != null)
					fr.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
