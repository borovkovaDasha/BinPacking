import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Clustering {
	
	List<Cluster> clusters;
	HashMap filesIterations;
	int firstSeq;
	int secondSeq;
	public static String RESULT_PATH = "D:\\data_for_binpacking\\solution\\solution.txt";
	
	void start_clustering(int fileSize) {
		initialize_clusters(fileSize);
		for (int i = 0; i < fileSize - 1; i++) {
			System.out.println("iteration " + i);
			find_closest();
			System.out.println("closest " + firstSeq + " " + secondSeq);
			GeneticAlgorithm GA = new GeneticAlgorithm();
			Chromosome chromosome = null;
			Cluster cluster = new Cluster();
			for (int j = 0; j < clusters.get(firstSeq).files.size(); j++) {
				cluster.files.add(clusters.get(firstSeq).files.get(j));
			}
			for (int j = 0; j < clusters.get(secondSeq).files.size(); j++) {
				cluster.files.add(clusters.get(secondSeq).files.get(j));
			}
			clusters.remove(secondSeq);
			clusters.remove(firstSeq);
			try {
				System.out.println("cluster.files " + cluster.files);
				System.out.println("(Integer)filesIterations.get(cluster.files.size()) " + (Integer)filesIterations.get(cluster.files.size()));
				chromosome = GA.go(cluster.files, (Integer)filesIterations.get(cluster.files.size()));
				for (int j = 0; j < chromosome.solveSeq.size(); j++) {
					Genome gen = new Genome(0);
					gen.hugeItems = chromosome.solveSeq.get(j).hugeItems;
					gen.largeItems = chromosome.solveSeq.get(j).largeItems;
					gen.mediumItems = chromosome.solveSeq.get(j).mediumItems;
					gen.smallItems = chromosome.solveSeq.get(j).smallItems;
					gen.remainingItems = chromosome.solveSeq.get(j).remainingItems;
					gen.prevAlgorithm = chromosome.solveSeq.get(j).prevAlgorithm;
					gen.algorithmNumber = chromosome.solveSeq.get(j).algorithmNumber;
					cluster.genes.add(gen);
					System.out.print(chromosome.solveSeq.get(j));
				}
				cluster.setFileAndSolution();
				System.out.println();
			} catch (IOException e) {
				e.printStackTrace();
			}
			clusters.add(cluster);
			printClusters();
		}
	}
	
	void initialize_clusters(int fileSize) {
		System.out.println("initialize_clusters");
		initialize_hashMap(fileSize);
		clusters = new ArrayList<>();
		for (int i = 1; i <= fileSize; i++) {
			Cluster cluster = new Cluster();
			cluster.files.add(i);
			System.out.println("i " + i);
			GeneticAlgorithm GA = new GeneticAlgorithm();
			Chromosome chromosome = null;
			try {
				chromosome = GA.go(cluster.files, (Integer)filesIterations.get(cluster.files.size()));
				for (int j = 0; j < chromosome.solveSeq.size(); j++) {
					Genome gen = new Genome(0);
					gen.hugeItems = chromosome.solveSeq.get(j).hugeItems;
					gen.largeItems = chromosome.solveSeq.get(j).largeItems;
					gen.mediumItems = chromosome.solveSeq.get(j).mediumItems;
					gen.smallItems = chromosome.solveSeq.get(j).smallItems;
					gen.remainingItems = chromosome.solveSeq.get(j).remainingItems;
					gen.prevAlgorithm = chromosome.solveSeq.get(j).prevAlgorithm;
					gen.algorithmNumber = chromosome.solveSeq.get(j).algorithmNumber;
					cluster.genes.add(gen);
					System.out.print(cluster.genes.get(j).toStringAlg());
				}
				System.out.println();
				System.out.println("cluster.solutionSeq " + cluster.genes.size());
				cluster.setFileAndSolution();
				System.out.println("fitness = " + cluster.fitness);
			} catch (IOException e) {
				e.printStackTrace();
			}
			clusters.add(cluster);
		}
		printClusters();
	}
	
	void initialize_hashMap(int fileSize) {
		filesIterations = new HashMap<Integer,Integer>();
		int iter = 100;
		for (int i = 1; i < fileSize + 1; i++) {
			if (fileSize%50 == 0) {
				iter += 100;
			}
			filesIterations.put(i, iter);
		}
	}
	
	void find_closest() { //change clusters to find in hashmap
		int x = 0;
		int y = 0;
		int lenSubSeq = 0;
		System.out.println("find_closest()");
        for (int i = 0; i < clusters.size() - 1; i++) { //choose first cluster
			for (int j = i + 1; j < clusters.size(); j++) { //choose second cluster
				
				for (int k = 0; k < clusters.get(i).fileAndSolution.size(); k++) { //choose in first cluster sequence
					int num1 = (int)clusters.get(i).fileAndSolution.keySet().toArray()[k];
					char[] first = new char[clusters.get(i).fileAndSolution.get(num1).size()];
					
					for (int p = 0; p < clusters.get(j).fileAndSolution.size(); p++) { //choose in second cluster sequence
						int num2 = (int)clusters.get(j).fileAndSolution.keySet().toArray()[p];
						char[] second = new char[clusters.get(j).fileAndSolution.get(num2).size()];
						
						for (int l = 0; l < first.length; l++) {
							first[l] = (char) clusters.get(i).fileAndSolution.get(num1).get(l).algorithmNumber;
						}
						for (int l = 0; l < second.length; l++) {
							second[l] = (char) clusters.get(j).fileAndSolution.get(num2).get(l).algorithmNumber;
						}
						int longestSubSeq = longestCommonSubsequence(first, second);
						if (longestSubSeq >= lenSubSeq) {
							x = i;
							y = j;
							lenSubSeq = longestSubSeq;
							System.out.println("find_closest() x " + x);
							System.out.println("find_closest() y " + y);
							System.out.println("find_closest() lenSubSeq " + lenSubSeq);
						}
					}
				}
			}
        }
		firstSeq = x;
		secondSeq = y;
		System.out.println("find_closest() firstSeq " + firstSeq);
		System.out.println("find_closest() secondSeq " + secondSeq);
		//System.exit(1);
	}
	
    public static int longestCommonSubsequence(char[] firstWord, char[] secondWord) {
        int[][] lcsMatrix = new int[firstWord.length + 1][secondWord.length + 1];
        for(int i = 1; i <= firstWord.length; i++) {
            for(int j = 1; j <= secondWord.length ; j++) {
                lcsMatrix[i][j] = (firstWord[i - 1] == secondWord[j - 1]) 
                        ? lcsMatrix[i - 1][j - 1] + 1 
                        : Math.max(lcsMatrix[i][j - 1],lcsMatrix[i - 1][j]);
            }
        }
        return lcsMatrix[firstWord.length][secondWord.length];
    }
	
	public void writeFile()
	{
		String s = "";
		for (int i = 0; i < clusters.size(); i++) {
			s+= "cluster - " + i;
			s+= "\n";
			s+= clusters.get(i).toString();
			s+= "\n";
		}
		String path = RESULT_PATH;
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(path, true));
		    writer.write(s); 
		    writer.flush();
		    writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void printClusters() {
		System.out.println("clusters.size - " + clusters.size());
		for (int i = 0; i < clusters.size(); i++) {
			System.out.println("cluster - " + i);
			System.out.println(clusters.get(i).toString());
		}
		writeFile();
	}

}
