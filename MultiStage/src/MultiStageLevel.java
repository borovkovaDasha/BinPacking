import java.util.ArrayList;
import java.util.HashMap;

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
		int termination_criteria1 = 0;
		int termination_criteria2 = 0;
		initializeHeuristic();
		int timeImproved = 0;
		//firstStage
		while (termination_criteria1 < TERMINATION_CRITERIA_S1HH) {
			S1HH s1hh = new S1HH(score, SInputStage1, SBestOverAll, SBestStage);
			timeImproved = s1hh.solve(timeImproved, TIME_IMPOVED, C[counter]);
			SCurrent = ListFunctions.listEquals(s1hh.SCurrent);
			ListFunctions.printList(SCurrent);
			SBestOverAll = ListFunctions.listEquals(s1hh.SBestOverAll);
			ListFunctions.printList(SBestOverAll);
			SBestStage = ListFunctions.listEquals(s1hh.SBestStage);
			ListFunctions.printList(SBestStage);
			termination_criteria1++;
		}
		System.exit(1);
		if (counter == C.length - 1) {
			SBestStage = ListFunctions.listEquals(SCurrent);
		}
		termination_criteria1 = 0;
		
		//secondStage
		if (Math.random() < PS2HH) {
			SolveTask st = new SolveTask();
			if (st.solveAllTasks(SBestStage) <= st.solveAllTasks(SInputStage2)) {
				SInputStage2 = ListFunctions.listEquals(SBestStage);
				counter++;
			}
			else {
				counter = 0;
				SInputStage2 = ListFunctions.listEquals(SBestStage);
			}
			HashMap<Integer, Integer> paretoArchive = new HashMap<Integer,Integer>();
			while (termination_criteria2 < TERMINATION_CRITERIA_S2HH) {
				S2HH s2hh = new S2HH(score, SInputStage2, SBestOverAll);
				paretoArchive = s2hh.solve(C[counter]);
			}
			computeScores(paretoArchive);
			termination_criteria2 = 0;
		}
		else {
			cleanScores();
		}
		SInputStage1 = ListFunctions.listEquals(SBestStage);
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
//			SInputStage1.add(alg);
			SInputStage2.add(alg);
			SBestOverAll.add(alg);	
			SBestStage.add(alg);
		}
		score = new double[ALGORITHM_SIZE];
		cleanScores();
	}
	
	void computeScores(HashMap<Integer, Integer> paretoArchive) {
		for (int i = 0; i < score.length; i++) {
			score[i] = 0;
		}
		ArrayList values = (ArrayList) paretoArchive.values();
		ArrayList keys = (ArrayList) paretoArchive.keySet();
		int minimum = (int)values.get(0);
		int minimum_num = 0;
		int number = 0;
		for (int i = 1; i < values.size(); i++) {
			if ((int)values.get(i) < minimum) {
				minimum = (int)values.get(i);
				minimum_num = (int)keys.get(i);
				number = i;
			}			
		}
		setScore(minimum_num);
		values.remove(number);
		keys.remove(number);
		minimum = (int)values.get(0);
		minimum_num = 0;
		number = 0;
		for (int i = 1; i < values.size(); i++) {
			if ((int)values.get(i) < minimum) {
				minimum = (int)values.get(i);
				minimum_num = (int)keys.get(i);
				number = i;
			}			
		}
		setScore(minimum_num);
		values.remove(number);
		keys.remove(number);
		minimum = (int)values.get(0);
		minimum_num = 0;
		number = 0;
		for (int i = 1; i < values.size(); i++) {
			if ((int)values.get(i) < minimum) {
				minimum = (int)values.get(i);
				minimum_num = (int)keys.get(i);
				number = i;
			}			
		}
		setScore(minimum_num);
		values.remove(number);
		keys.remove(number);		
	}
	
	void cleanScores() {
		for (int i = 0; i < score.length; i++) {
			score[i] = 1;
		}
	}
	
	void setScore(int num) {
		switch(num) {
			case 0:
				score[num]++;
				break;
			case 1:
				score[num]++;
				break;
			case 2:
				score[num]++;
				break;
			case 3:
				score[num]++;
				break;
			case 4:
				score[num]++;
				break;
			case 5:
				score[num]++;
				break;
			case 6:
				score[num]++;
				break;
			case 7:
				score[num]++;
				break;
			case 8:
				score[0]++;
				score[0]++;
				break;
			case 9:
				score[0]++;
				score[1]++;
				break;
			case 10:
				score[0]++;
				score[2]++;
				break;
			case 11:
				score[0]++;
				score[3]++;
				break;
			case 12:
				score[0]++;
				score[4]++;
				break;
			case 13:
				score[0]++;
				score[5]++;
				break;
			case 14:
				score[0]++;
				score[6]++;
				break;
			case 15:
				score[0]++;
				score[7]++;
				break;
			case 16:
				score[1]++;
				score[0]++;
				break;
			case 17:
				score[1]++;
				score[1]++;
				break;
			case 18:
				score[1]++;
				score[2]++;
				break;
			case 19:
				score[1]++;
				score[3]++;
				break;
			case 20:
				score[1]++;
				score[4]++;
				break;
			case 21:
				score[1]++;
				score[5]++;
				break;
			case 22:
				score[1]++;
				score[6]++;
				break;
			case 23:
				score[1]++;
				score[7]++;
				break;
			case 24:
				score[2]++;
				score[0]++;
				break;
			case 25:
				score[2]++;
				score[1]++;
				break;
			case 26:
				score[2]++;
				score[2]++;
				break;
			case 27:
				score[2]++;
				score[3]++;
				break;
			case 28:
				score[2]++;
				score[4]++;
				break;
			case 29:
				score[2]++;
				score[5]++;
				break;
			case 30:
				score[2]++;
				score[6]++;
				break;
			case 31:
				score[2]++;
				score[7]++;
				break;
			case 32:
				score[3]++;
				score[0]++;
				break;
			case 33:
				score[3]++;
				score[1]++;
				break;
			case 34:
				score[3]++;
				score[2]++;
				break;
			case 35:
				score[3]++;
				score[3]++;
				break;
			case 36:
				score[3]++;
				score[4]++;
				break;
			case 37:
				score[3]++;
				score[5]++;
				break;
			case 38:
				score[3]++;
				score[6]++;
				break;
			case 39:
				score[3]++;
				score[7]++;
				break;
			case 40:
				score[4]++;
				score[0]++;
				break;
			case 41:
				score[4]++;
				score[1]++;
				break;
			case 42:
				score[4]++;
				score[2]++;
				break;
			case 43:
				score[4]++;
				score[3]++;
				break;
			case 44:
				score[4]++;
				score[4]++;
				break;
			case 45:
				score[4]++;
				score[5]++;
				break;
			case 46:
				score[4]++;
				score[6]++;
				break;
			case 47:
				score[4]++;
				score[7]++;
				break;
			case 48:
				score[5]++;
				score[0]++;
				break;
			case 49:
				score[5]++;
				score[1]++;
				break;
			case 50:
				score[5]++;
				score[2]++;
				break;
			case 51:
				score[5]++;
				score[3]++;
				break;
			case 52:
				score[5]++;
				score[4]++;
				break;
			case 53:
				score[5]++;
				score[5]++;
				break;
			case 54:
				score[5]++;
				score[6]++;
				break;
			case 55:
				score[5]++;
				score[7]++;
				break;
			case 56:
				score[6]++;
				score[0]++;
				break;
			case 57:
				score[6]++;
				score[1]++;
				break;
			case 58:
				score[6]++;
				score[2]++;
				break;
			case 59:
				score[6]++;
				score[3]++;
				break;
			case 60:
				score[6]++;
				score[4]++;
				break;
			case 61:
				score[6]++;
				score[5]++;
				break;
			case 62:
				score[6]++;
				score[6]++;
				break;
			case 63:
				score[6]++;
				score[7]++;
				break;
			case 64:
				score[7]++;
				score[0]++;
				break;
			case 65:
				score[7]++;
				score[1]++;
				break;
			case 66:
				score[7]++;
				score[2]++;
				break;
			case 67:
				score[7]++;
				score[3]++;
				break;
			case 68:
				score[7]++;
				score[4]++;
				break;
			case 69:
				score[7]++;
				score[5]++;
				break;
			case 70:
				score[7]++;
				score[6]++;
				break;
			case 71:
				score[7]++;
				score[7]++;
				break;
			default:
				System.out.println("default??");
				break;
		}
	}

}
