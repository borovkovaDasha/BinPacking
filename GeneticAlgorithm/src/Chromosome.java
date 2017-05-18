import java.util.ArrayList;
import java.util.List;

public class Chromosome {
	public int CHROMOSOME_SIZE = 16;
	public double fitness = 0;
	public double fitnessSum = 0;
	
	public List<Genome> chromosome;
	public double years;
	
	public Chromosome(int flag)
	{
		years = 0;
		//List<Genome> 
		if (flag == 1)
		{
			chromosome = new ArrayList<Genome>();
			for (int i = 0; i < CHROMOSOME_SIZE; i++)
			{
				Genome genome = new Genome(1);
				chromosome.add(genome);
			}
			/*for (int i = 0; i < CHROMOSOME_SIZE; i++)
			{
				//System.out.println("h = " + chromosome.get(i).hugeItems + " l = " + chromosome.get(i).largeItems + " m = " + chromosome.get(i).mediumItems + " s = " + chromosome.get(i).smallItems + " r = " + chromosome.get(i).remainingItems + " a = " + chromosome.get(i).algorithmNumber);
			}
			return;*/
		}
		else 
		{
			chromosome = new ArrayList<Genome>();
			return;
		}
	}
	
	public double solveProblem(int[][] elements, int size, int algnum) //List<Genome> chromosome, int size)
	{
		int[] bins = new int[elements.length];
		years = years + 1;
		//create array of baskets
		for (int i = 0; i < bins.length; i++)
		{
			bins[i] = size;
		}
		int alg = 0;
		elements = sort(elements);
		int flag = 0;
		//System.out.println("elements.length " + elements.length);
		int currentBin = 0;
		for (int i = 0; i < elements.length && flag == 0; i++)
		{
			Genome state = getCurrentState(elements, size);
			if (state.remainingItems == 0)
			{
				flag = 1;
				break;
			}
			//if (alg == 0)
			//{
				alg = findCloser(state);//chromosome, state);
			//}
			//System.out.println("findCloser " + alg);
			//alg = 7;
			switch (alg) {
				case 1:  LargestFitDecreasing LFD = new LargestFitDecreasing();
				currentBin = LFD.startPacking(elements, bins, size);
				break;
				case 2:  NextFitDecreasing NFD = new NextFitDecreasing();
				currentBin = NFD.startPacking(elements, bins, currentBin, size);
				break;
				case 3:  DjangAndFinch DAF = new DjangAndFinch();
				currentBin = DAF.startPacking(elements, bins, currentBin, size);
				break;
				case 4:  DjangAndFinchTuples DAFT = new DjangAndFinchTuples();
				currentBin = DAFT.startPacking(elements, bins, currentBin, size);
				break;
				case 5:	LargestFitDecreasingFilter LFDF = new LargestFitDecreasingFilter();
				currentBin = LFDF.startPacking(elements, bins, currentBin, size);
				break;
				case 6:	NextFitDecreasingFilter NFDF = new NextFitDecreasingFilter();
				currentBin = NFDF.startPacking(elements, bins, currentBin, size);
				break;
				case 7:	DjangAndFinchFilter DAFF = new DjangAndFinchFilter();
				currentBin = DAFF.startPacking(elements, bins, currentBin, size);
				break;
				case 8:	DjangAndFinchTuplesFilter DAFTF = new DjangAndFinchTuplesFilter();
				currentBin = DAFTF.startPacking(elements, bins, currentBin, size);
				break;
				default:
					break;
			}
		}
		double numberOfBins = 0;
		for (int i = 0; i < bins.length; i++)
		{
			//System.out.println("binsize = " + bins[i]);
			if (bins[i] < size)
			{
				////System.out.println("binsize = " + bins[i]);
				numberOfBins++;
			}
		}
		//System.out.println("elements.length = " + elements.length);
		//System.out.println("numberOfBins = " + numberOfBins);
		//return fitness;
		System.out.println("numberOfBins = " + numberOfBins);
		fitnessSum = fitnessSum + numberOfBins/elements.length;
		fitness = fitnessSum/years;
		if (numberOfBins < 50)
		{
			System.out.println("!!! stop ");
			System.exit(1);
		}
		return numberOfBins;
	}
	
	public int findCloser(Genome gen){//List<Genome> chromosome, Genome gen){
		int algorithm = 0;
		double tmpres = 100;
		for (int i = 0; i < chromosome.size(); i++)
		{
			Genome tmp = chromosome.get(i);
			////System.out.println("distance " + Math.sqrt(Math.pow(tmp.hugeItems-gen.hugeItems,2) + Math.pow(tmp.largeItems-gen.largeItems,2)
			//+ Math.pow(tmp.smallItems-gen.smallItems,2) + Math.pow(tmp.remainingItems-gen.remainingItems,2)));
			if(Math.sqrt(Math.pow(tmp.hugeItems-gen.hugeItems,2) + Math.pow(tmp.largeItems-gen.largeItems,2)
			+ Math.pow(tmp.smallItems-gen.smallItems,2) + Math.pow(tmp.remainingItems-gen.remainingItems,2)) < tmpres)
			{
				algorithm = tmp.algorithmNumber;
				////System.out.println("new closet algorithm is " + algorithm);
				tmpres = Math.sqrt(Math.pow(tmp.hugeItems-gen.hugeItems,2) + Math.pow(tmp.largeItems-gen.largeItems,2)
				+ Math.pow(tmp.smallItems-gen.smallItems,2) + Math.pow(tmp.remainingItems-gen.remainingItems,2));
			}
		}
		//System.out.println("the closet algorithm is " + algorithm);
		return algorithm;
	}
	
	public Genome getCurrentState(int[][]a, int size){
		Genome gen = new Genome(0);
		 for (int i = 0; i < a.length; i++)
	        {
	        	if (a[i][0] > size/2 && a[i][1] == 0)
	        	{
	        		////System.out.println("huge");
	        		gen.hugeItems++;
	        	}
	        	else if (a[i][0] > size/3 && a[i][1] == 0)
	        	{
	        		////System.out.println("large");
	        		gen.largeItems++;
	        	}
	        	else if (a[i][0] > size/4 && a[i][1] == 0)
	        	{
	        		////System.out.println("medium");
	        		gen.mediumItems++;
	        	}
	        	else if (a[i][1] == 0)
	        	{
	        		////System.out.println("small");
	        		gen.smallItems++;
	        	}
	        	if (a[i][1] != 1)
	        	{
	        		////System.out.println("remaining");
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
		 //System.out.println("current state: h " + gen.hugeItems + " l " + gen.largeItems + " m " + gen.mediumItems + " s " + gen.smallItems + " r " + gen.remainingItems);
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
