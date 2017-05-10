//import javax.lang.model.util.Elements;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {
		//String fileName = "D:\\test.txt";
		//GeneticAlgorithm GA = new GeneticAlgorithm();
		//GA.writeFile();
		//GA.go();
		/*int[][]elements = GA.readFile(fileName);
		List<Genome> population = GA.initializePopulation();
		for (int i = 0; i < 10; i++)
		{
			System.out.println("h = " + population.get(i).hugeItems + "l = " + population.get(i).largeItems + "m = " + population.get(i).mediumItems + "s = " + population.get(i).smallItems + "r = " + population.get(i).remainingItems + "a = " + population.get(i).algorithmNumber);
		}*/
		//testWeka();
		MachineLearning ML = new MachineLearning();
		ML.start("C:\\data_for_binpacking\\10000test.txt");
		//ML.start("C:\\data_for_binpacking\\testxxx.txt");
	}
	
	public static void testWeka() throws Exception
	{
		BufferedReader br = null;
		br = new BufferedReader(new FileReader("C:\\data_for_binpacking\\result.txt"));
		Instances trainData = new Instances(br);
		trainData.setClassIndex(trainData.numAttributes() - 1);
		br.close();
		//RandomForest rf = new RandomForest();
		//rf.setNumTrees(100);
		//rf.buildClassifier(trainData);
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
			//double pred = cModel.classifyInstance(testData.instance(i));
			System.out.println("x " + testData.classAttribute().value((int)pred));
		}
	}
}

