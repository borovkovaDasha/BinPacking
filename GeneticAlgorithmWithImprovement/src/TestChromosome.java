import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TestChromosome {
	
	public static final int ALGORITHM_NUMBER = 8;
	public static final String RESULT_FILE = "C:\\data_for_binpacking\\improve_results\\test5.txt";
	public static final String CHROMOSOME_TEST = "C:\\data_for_binpacking\\improve_results\\testing5bin3.txt";
	public static final String DATA_PATH = "C:\\data_for_binpacking\\bin3data\\";
	public Chromosome cleverchromosome;
	public GeneticAlgorithm GA;
	public int sumOfBest;
	public double sumOfBins;
	
	public FileWriter writer;
	 
	public void start() throws Exception
	{
		GA = new GeneticAlgorithm();
		parseFile(RESULT_FILE);
	    writer = new FileWriter(CHROMOSOME_TEST);  
	    for (int j = 0; j <= 0; j++)
	    {
	    	sumOfBest = 0;
	    	sumOfBins = 0;
		for (int i = 1; i <= 10; i++)//GA.NUMBER_OF_FILES; i++)
		{
			int [][] elements = readFile(DATA_PATH + Integer.toString(i) + ".txt");
			//int [][] elements = readFile(GA.DATA_PATH + Integer.toString(i) + ".txt");
			System.out.println("file " + i);
			double p = 0;
			//for (int j = 0; j <= ALGORITHM_NUMBER; j++)
			//{
				System.out.println("algorithm " + j);
				System.out.println("GA.size " + GA.size);
				System.out.println("GA.bestBinNums " + GA.bestBinNums);
				sumOfBest = sumOfBest + GA.bestBinNums;
				//cleverchromosome.solveProblem(elements, GA.size, j);
				double t = cleverchromosome.solveProblem(elements, GA.size, j);
				if (GA.sum != cleverchromosome.remainbunssize)
				{
					System.out.println("!!!");
					System.exit(1);
				}
				sumOfBins = sumOfBins + t;
				//double t = 0;
				//writeFile(i, elements.length, t, j);
				if (j == 0)
					p = t;
				if (j == 1)
				{
					if (p < t)
					{
						writeFile(i, elements.length, t, j,1);
					}
					else if (p == t)
					{
						writeFile(i, elements.length, t, j,2);
					}
					else
					{
						writeFile(i, elements.length, t, j,3);
					}
				}
				else
					writeFile(i, elements.length, t, j,0);
						//System.out.println("!!! 0 is better");
				for (int k = 0; k < elements.length; k++)
				{
					elements[k][1] = 0;
				}
			//}			
		}
		writeFile(0, 0, 0, 0,4);
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
				gen.prevAlgorithm = Integer.parseInt(s[5]);
				gen.algorithmNumber = Integer.parseInt(s[6]);
				//System.out.println(gen.hugeItems + " " + gen.largeItems + " " + gen.mediumItems + " " + gen.smallItems + " " +
				//		gen.remainingItems + " " + " " + gen.prevAlgorithm + " " + gen.algorithmNumber);
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
	
	public void writeFile(int filenum, int length, double number_of_bins, int algorithm, int flag) throws IOException
	{
		String s = "File - " + filenum + " Number of elements: " + Integer.toString(length) + " number of bins: " + Double.toString(number_of_bins) + " best bins: " + GA.bestBinNums + " algorithm: " + algorithm + "\n";
	    //System.out.println("s - " + s);
	    writer.write(s); 
		/*if (flag == 1)
		{
			writer.write("!!!better\n");
		}
		if (flag == 2)
		{
			writer.write("!!!equal\n");
		}
		if (flag == 3)
		{
			writer.write("!!!worse\n");
		}*/
		if (flag == 4)
		{
			writer.write("sumOfBest " + sumOfBest + " sumOfBins " + sumOfBins + "\n");
		}
	}
}
