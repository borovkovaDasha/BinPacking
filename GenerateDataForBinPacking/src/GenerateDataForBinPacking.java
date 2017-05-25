import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerateDataForBinPacking {

	public static final String FILE_PATH = "C:\\data_for_binpacking\\bin2data\\";
	public static final String FILE_PATH_RESULTS = "C:\\data_for_binpacking\\bin2data\\results.txt";
	public static final int GAP_OF_ITEMS = 300;
	public static final int MIN_NUM_OF_ITEMS = 500;
	public static final int GAP_OF_BIN_SIZE = 200;
	public static final int MIN_NUM_OF_BIN_SIZE = 100;
	public static final int NUM_OF_FILES = 1000;
	public static final int NUM_OF_FILES_REWRITE = 640;
	public static List<String> results;
	public static List<String> files;
	public static final int A = 20;
	
	public static void main(String[] args) throws IOException {
		//writeFile();
		rewriteFile();
	}
	
	/*public static void writeFile() throws IOException
	{
		for (int i = 1; i <= NUM_OF_FILES; i++)
		{
			String filename = FILE_PATH + Integer.toString(i) + ".txt";
		    FileWriter writer = new FileWriter(filename); 
			int itemsNum = (int)(Math.random() * (GAP_OF_BIN_SIZE)) + MIN_NUM_OF_ITEMS;
			int binSize = (int)(Math.random() * (GAP_OF_BIN_SIZE)) +  MIN_NUM_OF_BIN_SIZE;
			String s = "";
			s = "Bin size: " + Integer.toString(binSize) + "\n";
		    writer.write(s); 
			s = "Number of elements: " + Integer.toString(itemsNum) + "\n";
			writer.write(s); 
			for (int j = 0; j < itemsNum; j++)
			{
				s = "";
				int newItem = (int)(Math.random() * (binSize)) + 1;
				s = Integer.toString(newItem) + "\n";
			    writer.write(s); 
			}
		    writer.flush();
		    writer.close();
		    System.out.println("file " + i + " was created!");
		}
	}*/
	public static void rewriteFile() throws IOException
	{
		int alpha = 0;
		int b = 1;
		int w = 1;
		int n = 1;
		int r = 1;
		readFile("", 0);
		int index = 1;
		for (int i = 1; i <= NUM_OF_FILES_REWRITE; i++)
		{
			/*String alp = new String("");
			if (alpha == 1)
				alp = "A";
			if (alpha == 2)
				alp = "B";
			if (alpha == 3)
				alp = "C";
			if (alpha == 4)
				alp = "D";
			if (alpha == 5)
				alp = "E";
			if (alpha == 6)
				alp = "F";
			if (alpha == 7)
				alp = "G";
			if (alpha == 8)
				alp = "H";
			if (alpha == 9)
				alp = "I";
			if (alpha == 10)
				alp = "J";
			if (alpha == 11)
				alp = "K";
			if (alpha == 12)
				alp = "L";
			if (alpha == 13)
				alp = "M";
			if (alpha == 14)
				alp = "N";
			if (alpha == 15)
				alp = "O";
			if (alpha == 16)
				alp = "P";
			if (alpha == 17)
				alp = "Q";
			if (alpha == 18)
				alp = "R";
			if (alpha == 19)
				alp = "S";
			if (alpha == 20)
				alp = "T";
			if (alpha == 20)
			{
				w++;
				alpha = 1;
			}
			else 
			{
				if (alpha == 0)
					alp = "A";
				alpha++;
			}*/
			if (r == 10)
			{
				b++;
				r = 1;
			}
			if (b == 5)
			{
				w++;
				b = 1;
			}
			if (w == 5)
			{
				n++;
				w = 1;
			}
			String fileName = FILE_PATH + "\\N" + Integer.toString(n) + "W" + Integer.toString(w) + "B" + Integer.toString(b) + "R" + Integer.toString(r) + ".BPP";
			System.out.println(fileName);
			index = readFile(fileName, index);
		}
	}
	
	public static void writeFile(int index) throws IOException
	{
		FileWriter writer = new FileWriter(FILE_PATH + Integer.toString(index) + ".txt");  
		for (int i = 0; i < files.size(); i++)
		{
			String s = files.get(i) + "\n";
		    writer.write(s); 		
		}
		System.out.println("write " + FILE_PATH + Integer.toString(index) + ".txt");
	    writer.flush();
	    writer.close();
	}
	
	public static int readFile(String fileName, int index) throws IOException{
		BufferedReader br = null;
		FileReader fr = null;
		System.out.println("read");
		if (fileName.equals(""))
		{
			results = new ArrayList<String>();
			try {
				fr = new FileReader(FILE_PATH_RESULTS);
				br = new BufferedReader(fr);
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) {
					results.add(sCurrentLine);
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
			return 0;
		}
		else 
		{
			files = new ArrayList<String>();
			int i = 0;
			try {
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) 
				{
					if (i == 1)
					{
						String tmp = new String("Bin size: " + sCurrentLine);
						files.add(tmp);
						System.out.println("index " + index);
						System.out.println(results.get(index));
						tmp = "Num of Bins: " + results.get(index - 1);//.substring(10, sCurrentLine.length());
						System.out.println("tmp " + tmp);
						files.add(tmp);
					}
					if (i == 0)
					{
						String tmp = new String("Number of elements: " + sCurrentLine);
						files.add(tmp);
					}
					else
						files.add(sCurrentLine);
					i++;
				}
				System.out.println("! ");
				writeFile(index);
				files.clear();
				index++;
			} 
			catch (IOException e) {
				e.printStackTrace();
				
			} 
			finally {
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
		return index;		
	}
}
