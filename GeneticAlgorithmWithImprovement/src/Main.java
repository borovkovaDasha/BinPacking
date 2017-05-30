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
		//GeneticAlgorithm GA = new GeneticAlgorithm();
		//GA.go();
		for (int i = 11; i < 21; i++)
		{
			System.out.println("!!!!!GA " + i);
			GeneticAlgorithm GA = new GeneticAlgorithm();
			GA.go(i);
		}
		//MachineLearning ML = new MachineLearning();
		//ML.start("C:\\data_for_binpacking\\HARD6.BPP");
		//TestChromosome TC = new TestChromosome();
		//TC.start();
	}
}

