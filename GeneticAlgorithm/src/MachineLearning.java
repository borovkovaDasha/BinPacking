import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class MachineLearning {
	Instances trainData;
	Classifier cModel;
	String constString = "@RELATION ga\n\n@ATTRIBUTE hugeItems	NUMERIC\n@ATTRIBUTE largeItems	NUMERIC\n@ATTRIBUTE mediumItems	NUMERIC\n@ATTRIBUTE smallItems	NUMERIC\n@ATTRIBUTE remainingItems	NUMERIC\n@ATTRIBUTE class	{0,1,2,3,4,5,6,7}\n\n@DATA\n";
	List<Genome> cleverchromosome;
	
	public MachineLearning() throws Exception
	{
		/*BufferedReader br = null;
		br = new BufferedReader(new FileReader("C:\\data_for_binpacking\\result.txt"));
		trainData = new Instances(br);
		trainData.setClassIndex(trainData.numAttributes() - 1);
		cModel = (Classifier)new RandomForest();
		cModel.buildClassifier(trainData);
		br.close();*/
		/*System.out.println(constString);
		Genome gen = new Genome(1);
		String str = new String("");
		str = constString + gen.hugeItems + "," + gen.largeItems + "," + gen.mediumItems + "," + gen.smallItems + "," + gen.remainingItems;
		System.out.println(str);*/
	}
	
	public void start(String fileName) throws Exception
	{
		GeneticAlgorithm GA = new GeneticAlgorithm();
		int[][]elements = GA.readFile(fileName);
		Chromosome chrom = new Chromosome(0);	
		parseFile("C:\\data_for_binpacking\\results\\result100.txt");
		solveProblem(elements,GA.size, chrom);
	}
	
	public void parseFile(String fileName) throws Exception
	{
		cleverchromosome = new ArrayList<Genome>();
		BufferedReader br = null;
		FileReader fr = null;
		int n = 0;
		int[][] elements = null;
		
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
				cleverchromosome.add(gen);
					//size = Integer.parseInt(str);
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
	
	public double solveProblem(int[][] elements, int size, Chromosome chrom) throws Exception //List<Genome> chromosome, int size)
	{
		int[] bins = new int[elements.length];
		int lastalg = 0;
		//create array of baskets
		for (int i = 0; i < bins.length; i++)
		{
			bins[i] = size;
		}
		elements = chrom.sort(elements);
		//System.out.println("elements.length " + elements.length);
		int currentBin = 0;
		for (int i = 0; i < elements.length; i++)
		{
			System.out.println("i = " + i);
			//String state = getCurrentState(elements, size);
			Genome state = getCurrentState(elements, size);
			//Reader targetReader = new StringReader(state);
			//if (state == "")
			//{
			//	break;
			//}
			//String alg = findAlgoritm((targetReader));//chromosome, state);
			//alg = 
			//String alg = findCloser(gen);
			int alg = findCloser(state);
			if (i == 0)
			{
				lastalg = alg;
				System.out.println("first " + lastalg);
			}
			//alg = findAlgoritm((targetReader));
			//int alg = findCloser(state);
			if (alg != lastalg)
			{
				System.out.println("!findCloser " + alg);
				lastalg = alg;
			}
			//if (i == 0)
			//{
			//	lastalg = alg;
			//	System.out.println("first " + lastalg);
			//}
			//alg = findAlgoritm((targetReader));
			//if (!lastalg.equalsIgnoreCase(alg))
			//{
			//	System.out.println("!findCloser " + alg);
			//	lastalg = alg;
			//}
			//targetReader.close();
			//System.out.println("findCloser " + alg);
			//System.out.println("findCloser " + alg);
			//alg = 1;
			switch (String.valueOf(alg)) {
				case "1":  LargestFitDecreasing LFD = new LargestFitDecreasing();
				currentBin = LFD.startPacking(elements, bins, size);
				break;
				case "2":  NextFitDecreasing NFD = new NextFitDecreasing();
				currentBin = NFD.startPacking(elements, bins, currentBin, size);
				break;
				case "3":  DjangAndFinch DAF = new DjangAndFinch();
				currentBin = DAF.startPacking(elements, bins, currentBin, size);
				break;
				case "4":  DjangAndFinchTuples DAFT = new DjangAndFinchTuples();
				currentBin = DAFT.startPacking(elements, bins, currentBin, size);
				break;
				case "5":	LargestFitDecreasingFilter LFDF = new LargestFitDecreasingFilter();
				currentBin = LFDF.startPacking(elements, bins, currentBin, size);
				break;
				case "6":	NextFitDecreasingFilter NFDF = new NextFitDecreasingFilter();
				currentBin = NFDF.startPacking(elements, bins, currentBin, size);
				break;
				case "7":	DjangAndFinchFilter DAFF = new DjangAndFinchFilter();
				currentBin = DAFF.startPacking(elements, bins, currentBin, size);
				break;
				case "8":	DjangAndFinchTuplesFilter DAFTF = new DjangAndFinchTuplesFilter();
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
		double fitness = numberOfBins/elements.length;
		System.out.println("fitness = " + fitness);
		System.out.println("numberOfBins = " + numberOfBins);
		//return fitness;
		return numberOfBins;
	}
	
	public int findCloser(Genome gen){//List<Genome> chromosome, Genome gen){
		int algorithm = 0;
		double tmpres = 100;
		for (int i = 0; i < cleverchromosome.size(); i++)
		{
			Genome tmp = cleverchromosome.get(i);
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
		////System.out.println("the closet algorithm is " + algorithm);
		return algorithm;
	}
	
	//public String getCurrentState(int[][]a, int size)
	public Genome getCurrentState(int[][]a, int size)
	{
		Genome gen = new Genome(0);
		 for (int i = 0; i < a.length; i++)
	        {
	        	if (a[i][0] > size/2)
	        	{
	        		////System.out.println("huge");
	        		gen.hugeItems++;
	        	}
	        	else if (a[i][0] > size/3)
	        	{
	        		////System.out.println("large");
	        		gen.largeItems++;
	        	}
	        	else if (a[i][0] > size/4)
	        	{
	        		////System.out.println("medium");
	        		gen.mediumItems++;
	        	}
	        	else
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
		 //else 
		 //{
		//	 return ;
		 //}
		 //String str = new String("");
		 //str = constString + gen.hugeItems + "," + gen.largeItems + "," + gen.mediumItems + "," + gen.smallItems + "," + gen.remainingItems + ",0";
		 ////System.out.println("current state: h " + gen.hugeItems + " l " + gen.largeItems + " m " + gen.mediumItems + " s " + gen.smallItems + " r " + gen.remainingItems);
		 //return str;
		 return gen;
	}
	
	public String findAlgoritm(Reader str) throws Exception
	{
		/*BufferedReader br = null;
		br = new BufferedReader(new FileReader("C:\\data_for_binpacking\\result.txt"));
		trainData = new Instances(br);
		trainData.setClassIndex(trainData.numAttributes() - 1);
		cModel = (Classifier)new RandomForest();
		cModel.buildClassifier(trainData);
		br.close();
		for (int i = 0; i < trainData.numInstances(); i++) 
		{
			double pred = cModel.classifyInstance(trainData.instance(i));
			System.out.println(pred);
		}
		br = new BufferedReader(new FileReader("C:\\data_for_binpacking\\test.txt"));
		Instances testData = new Instances(br);
		br.close();
		Classifier cModel = (Classifier)new RandomForest();
		cModel.buildClassifier(trainData);
		testData.setClassIndex(testData.numAttributes() - 1);
		Evaluation eTest = new Evaluation(testData);
		eTest.evaluateModel(cModel, testData);
		String strSummary = eTest.toSummaryString();
		System.out.println(strSummary);
		cModel.distributionForInstance(testData.instance(0));
		for (int i = 0; i < testData.numInstances(); i++) 
		{
			double pred = cModel.classifyInstance(testData.instance(i));
			System.out.println(pred);
		}*/
		Instances testData = new Instances(str);
		testData.setClassIndex(testData.numAttributes() - 1);
		Evaluation eTest = new Evaluation(testData);
		eTest.evaluateModel(cModel, testData);
		//cModel.distributionForInstance(testData.instance(0));
		//System.out.println(testData.instance(0));
		//double pred = cModel.classifyInstance(testData.instance(i));
		//cModel.distributionForInstance(testData.instance(0));
		int tmp = (int) cModel.classifyInstance(testData.instance(0));
		//System.out.println("x " + testData.classAttribute().value((int)tmp));
		String pred = testData.classAttribute().value((int)tmp);
		//System.out.println(pred);
		return pred;
	}
}
