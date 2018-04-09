import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Cluster {

	List<Integer> files;
	Double fitness;
	List<Genome> genes;
	HashMap<Integer, List<Genome>> fileAndSolution;
	public static String DATA_PATH = "C:\\data_for_binpacking\\bin_testing\\";
	
	public Cluster() {
		files = new ArrayList<>();
		genes = new ArrayList<>();
		fileAndSolution = new HashMap<Integer, List<Genome>>();
	}
	
	public String toString() {
		/*files = new ArrayList<>();
		genes = new ArrayList<>();
		String debugString = "files ";
		for (int i = 0; i < files.size(); i++) {
			debugString += files.get(i) + " ";
		}
		debugString += " " + "fitness " + fitness + " ";
		debugString += "solutionSeq " + genes.size();
		for (int i = 0; i < genes.size(); i++) {
			debugString += genes.get(i).algorithmNumber + " ";
		}
		debugString += " ";*/
//		System.out.println("toString() fileAndSolution.size() " + fileAndSolution.size());
		String debugString = "";
		Set <Integer> keys = fileAndSolution.keySet();
		debugString += "fitness " + fitness + "\n";
		for (int i = 0; i < fileAndSolution.size(); i++) {
			int filenum = (int)keys.toArray()[i];
			int realFileNumber = files.get(filenum);
//			System.out.println("filenum " + realFileNumber);
			debugString += "file " + realFileNumber + "\n";
			for (int j = 0; j < fileAndSolution.get(filenum).size(); j++) {
				debugString += fileAndSolution.get(filenum).get(j).algorithmNumber + " ";
			}
			debugString += "\n";
		}
		return debugString;
	}
	
	public void setFileAndSolution() {
		double fit = 0;
//		System.out.println("setFileAndSolution()");
		for (int i = 0; i < files.size(); i++) {
//			System.out.println("setFileAndSolution() file " + files.get(i));
			Chromosome chromosome = new Chromosome(0);
			GeneticAlgorithm ga = new GeneticAlgorithm();
			int [][] elements = ga.readFile(DATA_PATH + files.get(i) + ".txt");
			chromosome.chromosome = genes;
			chromosome.solveProblem(elements, ga.size, 0, ga.bestBinNums);
			ArrayList solution = new ArrayList<>();
//			System.out.println("setFileAndSolution() solved");
			for (int j = 0; j < chromosome.solveSeq.size(); j++) {
				solution.add(chromosome.solveSeq.get(j));
			}
			fileAndSolution.put(i, solution);
			fit += chromosome.fitness;
		}
		fitness = fit/files.size();
	}
	
}
