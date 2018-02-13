import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chromosome {
	public int CHROMOSOME_SIZE = 16;
	public double fitness = 0;
	public double fitnessSum = 0;
	
	public List<Genome> chromosome;
	public double years;
	
	public Chromosome(int flag)
	{
		years = 0;
		// create random chromosome
		if (flag == 1)
		{
			chromosome = new ArrayList<Genome>();
			for (int i = 0; i < CHROMOSOME_SIZE; i++)
			{
				Genome genome = new Genome(1);
				chromosome.add(genome);
			}
		}
		// create chromosome initialized by null
		else 
		{
			chromosome = new ArrayList<Genome>();
			return;
		}
	}
	
	public double solveProblem(int[][] elements, int size, int algnum, int bestResult)
	{
		int[] bins = new int[elements.length];
		final ArrayList<Integer> solel = new ArrayList<Integer>();
		final ArrayList<Integer> solbin = new ArrayList<Integer>();
		final HashMap<Integer, Integer> solution = new HashMap<Integer, Integer>();
		years = years + 1;
		//create array of baskets
		for (int i = 0; i < bins.length; i++)
		{
			bins[i] = size;
		}
		int alg = 0;
		elements = sort(elements);
		int flag = 0;
		int currentBin = 0;
		while (flag == 0)
		{
			Genome state = getCurrentState(elements, size);
			if (state.remainingItems == 0)
			{
				flag = 1;
				break;
			}
			if (algnum == 0)
			{
				alg = findCloser(state);
			}
			else
			{
				alg = algnum;
			}
			switch (alg) {
				case 1:  LargestFitDecreasing LFD = new LargestFitDecreasing();
				currentBin = LFD.startPacking(elements, bins, size, solel, solbin);
				break;
				case 2:  NextFitDecreasing NFD = new NextFitDecreasing();
				currentBin = NFD.startPacking(elements, bins, currentBin, size, solel, solbin);
				break;
				case 3:  DjangAndFinch DAF = new DjangAndFinch();
				currentBin = DAF.startPacking(elements, bins, currentBin, size, solel, solbin);
				break;
				case 4:  DjangAndFinchTuples DAFT = new DjangAndFinchTuples();
				currentBin = DAFT.startPacking(elements, bins, currentBin, size, solel, solbin);
				break;
				case 5:	LargestFitDecreasingFilter LFDF = new LargestFitDecreasingFilter();
				currentBin = LFDF.startPacking(elements, bins, currentBin, size, solel, solbin);
				break;
				case 6:	NextFitDecreasingFilter NFDF = new NextFitDecreasingFilter();
				currentBin = NFDF.startPacking(elements, bins, currentBin, size, solel, solbin);
				break;
				case 7:	DjangAndFinchFilter DAFF = new DjangAndFinchFilter();
				currentBin = DAFF.startPacking(elements, bins, currentBin, size, solel, solbin);
				break;
				case 8:	DjangAndFinchTuplesFilter DAFTF = new DjangAndFinchTuplesFilter();
				currentBin = DAFTF.startPacking(elements, bins, currentBin, size, solel, solbin);
				break;
				default:
					break;
			}
			
		}
		double notEmptyBins = 0;
		int sumOfElements = 0;
		int sumOfElements1 = 0;
		for (int i = 0; i < bins.length; i++) {
			if (bins[i] < size) {
				notEmptyBins++;
				sumOfElements = sumOfElements + size-bins[i]; 
			}
			if (bins[i] < 0) {
				System.out.println("!!!");
				System.exit(1);
			}
		}
		for (int i = 0; i < elements.length; i++) {
			sumOfElements1 = sumOfElements1 + elements[i][0];
		}
		if (notEmptyBins/bestResult < 1) {
			System.out.println("!!!error " + notEmptyBins/bestResult);
			/*for (int i =0; i<solel.size(); i++) {
				System.out.println(solel.get(i) + " " + solbin.get(i));
			}*/
			System.exit(1);
		}
		System.out.println("notEmptyBins " + notEmptyBins);
		System.out.println("elements.length " + elements.length);
		fitnessSum = fitnessSum + notEmptyBins/bestResult;
		fitness = fitnessSum/years;
		return notEmptyBins;
	}
	
	public int findCloser(Genome gen){
		int algorithm = 0;
		double tmpres = 100;
		for (int i = 0; i < chromosome.size(); i++)
		{
			Genome tmp = chromosome.get(i);
			if(Math.sqrt(Math.pow(tmp.hugeItems-gen.hugeItems,2) + Math.pow(tmp.largeItems-gen.largeItems,2)
			+ Math.pow(tmp.smallItems-gen.smallItems,2) + Math.pow(tmp.remainingItems-gen.remainingItems,2)) < tmpres)
			{
				algorithm = tmp.algorithmNumber;
				tmpres = Math.sqrt(Math.pow(tmp.hugeItems-gen.hugeItems,2) + Math.pow(tmp.largeItems-gen.largeItems,2)
				+ Math.pow(tmp.smallItems-gen.smallItems,2) + Math.pow(tmp.remainingItems-gen.remainingItems,2));
			}
		}
		return algorithm;
	}
	
	public Genome getCurrentState(int[][]a, int size){
		Genome gen = new Genome(0);
		 for (int i = 0; i < a.length; i++)
	        {
	        	if (a[i][0] > size/2 && a[i][1] == 0)
	        	{
	        		gen.hugeItems++;
	        	}
	        	else if (a[i][0] > size/3 && a[i][1] == 0)
	        	{
	        		gen.largeItems++;
	        	}
	        	else if (a[i][0] > size/4 && a[i][1] == 0)
	        	{
	        		gen.mediumItems++;
	        	}
	        	else if (a[i][1] == 0)
	        	{
	        		gen.smallItems++;
	        	}
	        	if (a[i][1] != 1)
	        	{
	        		gen.remainingItems++;
	        	}
	        }
		 if (gen.hugeItems != 0) 
		 {
			 gen.hugeItems = gen.hugeItems/a.length;
		 }
		 if (gen.largeItems != 0)
		 {
			 gen.largeItems = gen.largeItems/a.length;
		 }
		 if (gen.mediumItems != 0)
		 {
			 gen.mediumItems = gen.mediumItems/a.length;
		 }
		 if (gen.smallItems != 0)
		 {
			 gen.smallItems = gen.smallItems/a.length;
		 }
		 if (gen.remainingItems != 0)
		 {
			 gen.remainingItems = gen.remainingItems/a.length;
		 }
		 return gen;
	}
	
	static int[][] sort(int[][] sequence)
	{
		// Bubble Sort descending order
		for (int i = 0; i < sequence.length; i++)
			for (int j = 0; j < sequence.length - 1; j++)
				if (sequence[j][0] < sequence[j + 1][0])
				{
					sequence[j][0] = sequence[j][0] + sequence[j + 1][0];
					sequence[j + 1][0] = sequence[j][0] - sequence[j + 1][0];
					sequence[j][0] = sequence[j][0] - sequence[j + 1][0];
					sequence[j][1] = sequence[j][1] + sequence[j + 1][1];
					sequence[j + 1][1] = sequence[j][1] - sequence[j + 1][1];
					sequence[j][1] = sequence[j][1] - sequence[j + 1][1];
				}

		return sequence;
	}
}
