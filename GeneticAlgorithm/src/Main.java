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
		GeneticAlgorithm GA = new GeneticAlgorithm();
		GA.go();
		//MachineLearning ML = new MachineLearning();
		//ML.start("C:\\data_for_binpacking\\10000test.txt");
		//TestChromosome TC = new TestChromosome();
		//TC.start();
	}
}

