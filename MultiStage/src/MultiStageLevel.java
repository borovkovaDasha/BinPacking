import java.util.ArrayList;

public class MultiStageLevel {
	double[] score;
	private int[] C = {0, 3, 6, 9};
	private int ALGORITHM_SIZE = 8;
	private int HEURISTIC_SIZE = 10;
	private double PS2HH = 0.3;
	ArrayList<Integer> SInitial;
	ArrayList<Integer> SCurrent;
	ArrayList<Integer> SInputStage1;
	ArrayList<Integer> SInputStage2;
	ArrayList<Integer> SBestOverAll;
	ArrayList<Integer> SBestStage;
	int counter;
	private final int TERMINATION_CRITERIA = 20;
	private final int TERMINATION_CRITERIA_S1HH = 20;
	private final int TERMINATION_CRITERIA_S2HH = 10;
	private final int TIME_IMPOVED = 100;
	
	void start () {
		int termination_criteria = 0;
		initializeHeuristic();
		int timeImproved = 0;
		//firstStage
		while (termination_criteria < TERMINATION_CRITERIA_S1HH) {
			S1HH s1hh = new S1HH(score, SInputStage1, SBestOverAll, SBestStage);
			s1hh.solve(timeImproved, TIME_IMPOVED, C[counter]);
			termination_criteria++;
		}
		if (counter == C.length - 1) {
			SBestStage = listEquals(SCurrent);
		}
		
		//secondStage
		if (Math.random() < PS2HH) {
			SolveTask st = new SolveTask();
			if (st.solveAllTasks(SBestStage) <= st.solveAllTasks(SInputStage2)) {
				
			}
		}
		
	}
	
	void initializeHeuristic() {
		SInitial = new ArrayList<Integer>();
		SCurrent = new ArrayList<Integer>();
		SInputStage1 = new ArrayList<Integer>();
		SInputStage2 = new ArrayList<Integer>();
		SBestOverAll = new ArrayList<Integer>();
		SBestStage = new ArrayList<Integer>();
		for (int i = 0; i < HEURISTIC_SIZE; i++) {
			int alg = (int)(Math.random()*ALGORITHM_SIZE);
			SCurrent.add(alg);
			SInputStage1.add(alg);
			SInputStage2.add(alg);
			SBestOverAll.add(alg);
			SBestStage.add(alg);	
		}
		score = new double[ALGORITHM_SIZE+ALGORITHM_SIZE*ALGORITHM_SIZE];
		for (int i = 0; i < score.length; i++) {
			if (i < ALGORITHM_SIZE)
				score[i] = 1;
			else score[i] = 0;
		}
	}
	
	public ArrayList<Integer> listEquals(ArrayList<Integer> heuristic_list) {
		ArrayList<Integer> new_heuristic_list = new ArrayList<Integer>();
		for (int i = 0; i < heuristic_list.size(); i++) {
			new_heuristic_list.add(heuristic_list.get(i));
		}
		return new_heuristic_list;
	}
}
