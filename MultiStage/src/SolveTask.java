import java.util.ArrayList;

public class SolveTask {
	
	private static int REPEATE = 3;
	double bestBins;
	double resBins;
	private static int FILES_SIZE = 720;
	private static String FOLDER = "D:\\data_for_binpacking\\bin1out\\";
	
	public double solveAllTasks(ArrayList<Integer> heuristic_list) {
		double solution = 0;
		for (int i = 1; i <= FILES_SIZE; i++) {
			bestBins = 0;
			resBins = 0;
			SolveTask st = new SolveTask();
			solution += st.solve(heuristic_list, (FOLDER + i + ".txt"));
		}
		return solution;
	}
	
	public double solve(ArrayList<Integer> heuristic_list, String fileName) {
		ReadingFile rf = new ReadingFile();
		int[][] elements = rf.readFile(fileName);
		int[] bins = new int[elements.length];
		final ArrayList<Integer> solel = new ArrayList<Integer>();
		final ArrayList<Integer> solbin = new ArrayList<Integer>();
		//create array of baskets
		for (int i = 0; i < bins.length; i++)
		{
			bins[i] = rf.binSize;
		}
		elements = rf.sort(elements);
		int currentBin = 0;
		int alg = 0;
		int i = 0;
		while(!isSolved(elements))
		{
			alg = (int)heuristic_list.get(i);
			System.out.println("alg " + alg);
			switch (alg) {
				case 0:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				break;
				case 1:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 2:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 3:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 4:	 for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 5:	 for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 6:	 for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 7:	 for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 8:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				break;
				case 9:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 10:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 11:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 12:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 13:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 14:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 15:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 16:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				break;
				case 17:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 18:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 19:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 20:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 21:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 22:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 23:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 24:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				break;
				case 25:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 26:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 27:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 28:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 29:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 30:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 31:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 32:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				break;
				case 33:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 34:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 35:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 36:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 37:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 38:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 39:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 40:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				break;
				case 41:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 42:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 43:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 44:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 45:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 46:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 47:  for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 48:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				break;
				case 49:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 50:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 51:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 52:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 53:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 54:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 55:  for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 56:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				break;
				case 57:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 58:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 59:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 60:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 61:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 62:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 63:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 64:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				break;
				case 65:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 66:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 67:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 68:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 69:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 70:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 71:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				default:
					break;
			}
			if (i == heuristic_list.size() - 1) {
				i = 0;
			}
			else {
				i++;
			}
			
		}
		double notEmptyBins = 0;
		for (int j = 0; j < bins.length; j++) {
			if (bins[j] < rf.binSize) {
				notEmptyBins++;
			}
		}
		return (double)notEmptyBins;
	}
	
	public boolean isSolved(int[][] elements) {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i][1] == 0) {
				return false;
			}
		}
		return true;
	}
}
