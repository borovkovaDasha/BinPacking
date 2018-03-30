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
	
	void start_clustering(int fileSize) {
		initialize_clusters(fileSize);
		for (int i = 0; i < fileSize; i++) {
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
					cluster.solutionSeq.add(gen);
					System.out.print(chromosome.solveSeq.get(j));
				}
				System.out.println();
				cluster.fitness = chromosome.fitness;
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
		for (int i = 0; i < fileSize; i++) {
			Cluster cluster = new Cluster();
			cluster.files.add(i);
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
					cluster.solutionSeq.add(gen);
					System.out.print(cluster.solutionSeq.get(j).toStringAlg());
				}
				System.out.println();
				System.out.println("cluster.solutionSeq " + cluster.solutionSeq.size());
				cluster.fitness = chromosome.fitness;
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
		for (int i = 0; i < fileSize; i++) {
			if (fileSize%50 == 0) {
				iter += 100;
			}
			filesIterations.put(i, iter);
		}
	}
	
	void find_closest() {
		int x = 0;
		int y = 0;
		int lenSubSeq = 0;
		for (int i = 0; i < clusters.size() - 1; i++) {
			for (int j = i + 1; j < clusters.size(); j++) {
				char[] first = new char[clusters.get(i).solutionSeq.size()];
				char[] second = new char[clusters.get(j).solutionSeq.size()];
				for (int k = 0; k < first.length; k++) {
					first[k] = (char) clusters.get(i).solutionSeq.get(k).algorithmNumber;
				}
				for (int k = 0; k < first.length; k++) {
					second[k] = (char) clusters.get(j).solutionSeq.get(k).algorithmNumber;
				}
				int longestSubSeq = longestCommonSubsequence(first, second);
				if (longestSubSeq >= lenSubSeq) {
					x = i;
					y = j;
					lenSubSeq = longestSubSeq;
				}
			}
		}
		firstSeq = x;
		secondSeq = y;
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
	
	public void writeFile(int num, int chromosomeNum) throws IOException
	{
		/*String s = "";
	    for (int i = 0; i < population.size(); i++)
	    {
	    	s = s + "population " + i + " fitness " + population.get(i).fitness + " years " +  population.get(i).years + " length " + population.get(i).chromosome.size() + "\n"; 
	    }
	    s = s + "\n";
	    for (int i = 0; i < population.size(); i++)
	    {
	    	for (int j = 0; j < population.get(i).chromosome.size(); j++)
	    	{
	    		s = s + population.get(i).chromosome.get(j).hugeItems + " " + population.get(i).chromosome.get(j).largeItems + " " + population.get(i).chromosome.get(j).mediumItems + " " + population.get(i).chromosome.get(j).smallItems + " " + population.get(i).chromosome.get(j).remainingItems + " " +population.get(i).chromosome.get(j).prevAlgorithm + " " + population.get(i).chromosome.get(j).algorithmNumber + "\n"; 
	    	}
	    	System.out.println("years - " + population.get(i).years);
	    }*/
		/*String s = "fitness = " + population.get(chromosomeNum).fitness + "\n";
		for (int i = 0; i < population.get(chromosomeNum).solveSeq.size(); i++) {
			s = s + population.get(chromosomeNum).solveSeq.get(i) + " ";
		}
		System.out.println("file " + num + " fitness " + population.get(chromosomeNum).fitness);
	    String path = RESULT_PATH + num + ".txt";
	    FileWriter writer = new FileWriter(path); 
	    writer.write(s); 
	    writer.flush();
	    writer.close();*/
	}
	
	public void printClusters() {
		for (int i = 0; i < clusters.size(); i++) {
			System.out.println("cluster - " + i);
			System.out.println(clusters.get(i).toString());
		}
	}

}
