import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TestChromosome {
	
	public static final int ALGORITHM_NUMBER = 8;
	public static final String RESULT_FILE = "C:\\data_for_binpacking\\results\\test2.txt";
	public static final String CHROMOSOME_TEST = "C:\\data_for_binpacking\\results\\test_chromosome8.txt";
	public Chromosome cleverchromosome;
	
	public FileWriter writer;
	 
	public void start() throws Exception
	{
		GeneticAlgorithm GA = new GeneticAlgorithm();
		parseFile(RESULT_FILE);
	    writer = new FileWriter(CHROMOSOME_TEST);  
		for (int i = 1; i <= 750; i++)//GA.NUMBER_OF_FILES; i++)
		{
			int [][] elements = readFile(GA.DATA_PATH + Integer.toString(i) + ".txt");
			System.out.println("file " + i);
			for (int j = 0; j <= ALGORITHM_NUMBER; j++)
			{
				System.out.println("algorithm " + j);
				System.out.println("GA.size " + GA.size);
				//cleverchromosome.solveProblem(elements, GA.size, j);
				writeFile(i, elements.length, cleverchromosome.solveProblem(elements, GA.size, j), j);
				for (int k = 0; k < elements.length; k++)
				{
					elements[k][1] = 0;
				}
			}			
		}
	    writer.flush();
	    writer.close();
	}
	
	public int[][] readFile(String fileName){
		GeneticAlgorithm GA = new GeneticAlgorithm();
		return GA.readFile(fileName);
		
	}
	public void parseFile(String fileName) throws Exception
	{
		cleverchromosome = new Chromosome(0);
		BufferedReader br = null;
		FileReader fr = null;
		int n = 0;
		
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader(fileName));
			int i = 0;
			String str = null;
			
			int tmp = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				String[] s = sCurrentLine.split(" ");
				Genome gen = new Genome(0);
				gen.hugeItems = Double.parseDouble(s[0]);
				gen.largeItems = Double.parseDouble(s[1]);
				gen.mediumItems = Double.parseDouble(s[2]);
				gen.smallItems = Double.parseDouble(s[3]);
				gen.remainingItems = Double.parseDouble(s[4]);
				gen.algorithmNumber = Integer.parseInt(s[5]);
				cleverchromosome.chromosome.add(gen);
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
		return;
	}
	
	public void writeFile(int filenum, int length, double number_of_bins, int algorithm) throws IOException
	{
		String s = "File - " + filenum + "Number of elements: " + Integer.toString(length) + " number of bins: " + Double.toString(number_of_bins) + " algorithm: " + algorithm + "\n";
	    //System.out.println("s - " + s);
	    writer.write(s); 
	}
}
