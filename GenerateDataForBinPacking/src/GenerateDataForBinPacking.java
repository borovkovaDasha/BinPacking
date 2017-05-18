import java.io.FileWriter;
import java.io.IOException;

public class GenerateDataForBinPacking {

	public static final String FILE_PATH = "C:\\data_for_binpacking\\giant_data\\";
	public static final int GAP_OF_ITEMS = 1000;
	public static final int MIN_NUM_OF_ITEMS = 10000;
	public static final int GAP_OF_BIN_SIZE = 200;
	public static final int MIN_NUM_OF_BIN_SIZE = 100;
	public static final int NUM_OF_FILES = 100;
	
	public static void main(String[] args) throws IOException {
		writeFile();
	}
	
	public static void writeFile() throws IOException
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
	}

}
