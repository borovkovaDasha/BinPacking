import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestChromosome {
	
	public static final int ALGORITHM_NUMBER = 8;
	public static final String DATA_PATH = "D:\\data_for_binpacking\\bin_training\\";
	public static final String RESULT_FILE = "D:\\data_for_binpacking\\solution\\solution1test1cluster.txt";
	public static final int NUMBER_OF_FILES = 80;
	public List<Chromosome> cleverchromosome;
	public GeneticAlgorithm GA;
	public int sumOfBest;
	public double sumOfBins;
	
	public FileWriter writer;
	 
	public void start() throws Exception//String result_file, String chromosome_file) throws Exception
	{
		GA = new GeneticAlgorithm();
		cleverchromosome = new ArrayList<>();
		parseFile("D:\\data_for_binpacking\\solution\\ClusterSolution1.txt");//result_file);
//		parseFile("D:\\data_for_binpacking\\solution1\\ClusterSolution5_2.txt");
//		parseFile("D:\\data_for_binpacking\\solution1\\ClusterSolution5_3.txt");
//		parseFile("D:\\data_for_binpacking\\solution1\\ClusterSolution5_4.txt");
//		parseFile("D:\\data_for_binpacking\\solution1\\ClusterSolution5_5.txt");
	    writer = new FileWriter(RESULT_FILE);//chromosome_file); 
	    for (int j = 0; j <= 0; j++)
		{
			System.out.println("algorithm " + j);
			double p = 0;
			for (int i = 1; i <= NUMBER_OF_FILES; i++)
			{
				double bestSol = 100500;
				double chromNum = 0;
				for (int k = 0; k < cleverchromosome.size(); k++) {
					int [][] elements = readFile(DATA_PATH + Integer.toString(i) + ".txt");
					System.out.println("file " + i);
					System.out.println("GA.size " + GA.size);
					System.out.println("GA.bestBinNums " + GA.bestBinNums);
					double t = cleverchromosome.get(k).solveProblem(elements, GA.size, j, GA.bestBinNums);
					if (t < bestSol) {
						bestSol = t;
						chromNum = k;
					}
				}
				sumOfBest = sumOfBest + GA.bestBinNums;
				sumOfBins = sumOfBins + bestSol;
				writeFile(i, GA.bestBinNums, bestSol, j, chromNum);
			}
			writeResFile(sumOfBest,sumOfBins,j);
			sumOfBest = 0;
			sumOfBins = 0;
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
//		cleverchromosome = new Chromosome(0);
		BufferedReader br = null;
		FileReader fr = null;
		int n = 0;
		Chromosome chrom = new Chromosome(0);
		
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
				gen.prevAlgorithm = 0;
				gen.algorithmNumber = Integer.parseInt(s[5]);
				chrom.chromosome.add(gen);
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
		cleverchromosome.add(chrom);
		return;
	}
	
	public void writeFile(int filenum, double number_of_best_bins, double number_of_bins, int algorithm, double chromNum) throws IOException
	{
		String s = "File - " + filenum + " Number of best bins: " + Double.toString(number_of_best_bins) + " number of bins: " + Double.toString(number_of_bins) + " best bins: " + GA.bestBinNums + " algorithm: " + algorithm + " chromosome: " + chromNum + "\n";
	    //System.out.println("s - " + s);
	    writer.write(s); 
		//writer.write("sumOfBest " + sumOfBest + " sumOfBins " + sumOfBins + "\n");
	}
	
	public void writeResFile(double sum_of_best_bins, double sum_of_sol_bins, int algorithm) throws IOException
	{
		String s = "Result of - " + algorithm + " sum of best: " + Double.toString(sum_of_best_bins) + " sum of bins: " + Double.toString(sum_of_sol_bins) + " coeff: " + Double.toString(sum_of_sol_bins/sum_of_best_bins) + "\n";
	    //System.out.println("s - " + s);
	    writer.write(s); 
		writer.write("sumOfBest " + sumOfBest + " sumOfBins " + sumOfBins + "\n");
	}
}
