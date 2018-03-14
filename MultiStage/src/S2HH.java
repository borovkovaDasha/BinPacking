import java.util.ArrayList;
import java.util.HashMap;

public class S2HH {
	double[] score;
	ArrayList<Integer> SInitial;
	ArrayList<Integer> SCurrent;
	ArrayList<Integer> SInputStage2;
	ArrayList<Integer> SBestOverAll;
	ArrayList<Integer> SBestStage;
	ArrayList<Integer> SBestStep;
	HashMap<Integer, Integer> paretoArchive;
	private static int LIMIT = 4;
	private int ALGORITHM_SIZE = 8;
	
	S2HH(double[] score, ArrayList<Integer> SInputStage2, ArrayList<Integer> SBestOverAll) {
		this.score = score;
		this.SInputStage2 = SInputStage2;
		this.SBestOverAll = SBestOverAll;
		SBestStage = new ArrayList<Integer>();
		SBestStep = new ArrayList<Integer>();
		SCurrent = new ArrayList<Integer>();
		paretoArchive = new HashMap<Integer,Integer>();
	}
	
	HashMap<Integer, Integer> solve(double c) {
		SBestStage = ListFunctions.listEquals(SInputStage2);
		double elipson = 0.0;
		elipson = updateElipson(c);
		SolveTask st = new SolveTask();
		double solutionCurrent = st.solveAllTasks(SCurrent);
		double solutionBestStage = st.solveAllTasks(SBestStage);
		for (int i = 0; i < ALGORITHM_SIZE+ALGORITHM_SIZE*ALGORITHM_SIZE; i++) {
			ArrayList<Integer> new_heuristic_list = ListFunctions.listEquals(SInputStage2);
//			int bestStep = 0;
			double solution = 0;
			for (int j = 0; j < LIMIT; j++) {
				new_heuristic_list = ListFunctions.getNewList(i, new_heuristic_list);
				double SNew = st.solveAllTasks(new_heuristic_list);
				if (SNew < solutionCurrent || SNew < (1 + elipson)*solutionBestStage) {
//					solution = SNew;
//					bestStep = j;
					solution = SNew;
				}
			}
			if (solution != 0) {
				paretoArchive.put(i, (int)solution);
			}
		}
		return paretoArchive;
	}
	
	double updateElipson(double c) {
		SolveTask st = new SolveTask();
		double solution = st.solveAllTasks(SBestStage);
		double elipson = (Math.log(solution) + c)/solution;
		return elipson;
	}	
}
