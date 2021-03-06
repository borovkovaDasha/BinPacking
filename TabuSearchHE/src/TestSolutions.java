import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TestSolutions {
	
	public static final int ALGORITHM_NUMBER = 7;
	public static final String DATA_PATH = "D:\\data_for_binpacking\\bin_training\\";
	public static final String RESULT_FILE = "D:\\data_for_binpacking\\solution\\solution8.txt";
	public static final int NUMBER_OF_FILES = 54;
	public FileWriter writer;
	
	public void start() throws Exception
	{
		int[] hyper_heuristic_arr = {5, 5, 6, 2, 5, 5, 5};
		ArrayList<Integer> hyper_heuristic = new ArrayList<Integer>();
		for (int i = 0; i < hyper_heuristic_arr.length; i++) {
			hyper_heuristic.add(hyper_heuristic_arr[i]);
		}
		TabuSearch ts = new TabuSearch();
	    writer = new FileWriter(RESULT_FILE); 
	    double sumOfBins = 0;
	    double sumOfBest = 0;
//		for (int j = 0; j <= ALGORITHM_NUMBER; j++)
	    for (int j = 7; j <= 7; j++)
		{
			System.out.println("algorithm " + j);
			for (int i = 1; i <= NUMBER_OF_FILES; i++) {
//				if ( i>=310 && i < 400 || i > 420) {
//					continue;
//				}
				System.out.println("file " + i);
				double t = ts.solve(hyper_heuristic, (DATA_PATH + Integer.toString(i) + ".txt"));
				//System.out.println("ts.resBins " + ts.resBins);
				sumOfBins = sumOfBins + t;
				sumOfBest = sumOfBest + ts.bestBins;
				writeFile(i, ts.bestBins, t, j);
			}
			writeResFile(sumOfBest,sumOfBins,j);
			sumOfBest = 0;
			sumOfBins = 0;
	    }
	    writer.flush();
	    writer.close();
	}
	
	public void writeFile(int filenum, double number_of_best_bins, double number_of_bins, int algorithm) throws IOException
	{
		String s = "File - " + filenum + " Number of best bins: " + Double.toString(number_of_best_bins) + " number of bins: " + Double.toString(number_of_bins) + " algorithm: " + algorithm + "\n";
	    writer.write(s); 
	}
	
	public void writeResFile(double sumOfBest, double sumOfBins, int algorithm) throws IOException
	{
		String s = "Result of - " + algorithm + " sum of best: " + Double.toString(sumOfBest) + " sum of bins: " + Double.toString(sumOfBins) + " coeff: " + Double.toString(sumOfBins/sumOfBest) + "\n";
	    writer.write(s); 
	}
}
