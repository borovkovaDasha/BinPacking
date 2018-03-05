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
	private final int TERMINATION_CRITERIA = 10;
	private final int TERMINATION_CRITERIA_S1HH = 10;
	private final int TERMINATION_CRITERIA_S2HH = 10;
	private final int TIME_IMPOVED = 100;
	
	void start () {
		int termination_criteria = 0;
		initializeHeuristic();
		while (termination_criteria < TERMINATION_CRITERIA) {
			S1HH s1hh = new S1HH(score, SInputStage1, SBestOverAll, SBestStage);
			s1hh.solve(0, C[counter]);
			termination_criteria++;
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
			SInitial.add(alg);
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
}
