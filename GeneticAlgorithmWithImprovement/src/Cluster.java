import java.util.ArrayList;
import java.util.List;

public class Cluster {

	List<Integer> files;
	Double fitness;
	List<Genome> solutionSeq;
	
	public Cluster() {
		files = new ArrayList<>();
		solutionSeq = new ArrayList<>();
	}
	
	public String toString() {
		files = new ArrayList<>();
		solutionSeq = new ArrayList<>();
		String debugString = "files ";
		for (int i = 0; i < files.size(); i++) {
			debugString += files.get(i) + " ";
		}
		debugString += " " + "fitness " + fitness + " ";
		debugString += "solutionSeq " + solutionSeq.size();
		for (int i = 0; i < solutionSeq.size(); i++) {
			debugString += solutionSeq.get(i).algorithmNumber + " ";
		}
		debugString += " ";
		return debugString;
	}
	
}
