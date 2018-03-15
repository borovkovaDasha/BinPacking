import java.util.ArrayList;

public class S1HH {
	double[] score;
	ArrayList<Integer> SInitial;
	ArrayList<Integer> SCurrent;
	ArrayList<Integer> SInputStage1;
	ArrayList<Integer> SBestOverAll;
	ArrayList<Integer> SBestStage;
	private static int LIMIT = 5;
	
	S1HH(double[] score, ArrayList<Integer> SInputStage1, ArrayList<Integer> SBestOverAll, ArrayList<Integer> SBestStage) {
		this.score = score;
		this.SInputStage1 = SInputStage1;
		this.SBestOverAll = SBestOverAll;
		this.SCurrent = SInputStage1;
		this.SBestStage = SBestStage;
	}
	
	int solve(int timeImproved, int TIME_IMPROVED_MAXIMUM, double c) {
		int hIndex = rouletteWheelSelection();
		System.out.println("hIndex = " + hIndex);
		int counter = 0;
		double elipson = 0.0;
		if (timeImproved >= TIME_IMPROVED_MAXIMUM) {
			System.out.println("timeImproved = " + timeImproved);
			SCurrent = ListFunctions.listEquals(SBestStage);
			elipson = updateElipson(c);
			System.out.println("elipson = " + elipson);
			timeImproved = 0;
		}
		System.out.println("SCurrent");
		ListFunctions.printList(SCurrent);
		while(counter < LIMIT) {
			double SNew = 0.0;
			ArrayList<Integer> new_heuristic_list = ListFunctions.getNewList(hIndex, SCurrent);
			SolveTask st = new SolveTask();
			SNew = st.solveAllTasks(new_heuristic_list);
			if (SCurrent.isEmpty()) {
				SCurrent = ListFunctions.listEquals(new_heuristic_list);
			}
			System.out.println("SNew + " + SNew);
			double solutionBestStage = st.solveAllTasks(SBestStage);
			System.out.println("solutionBestStage + " + solutionBestStage);
			ListFunctions.printList(SBestStage);
			double solutionCurrent = st.solveAllTasks(SCurrent);
			System.out.println("solutionCurrent + " + solutionCurrent);
			ListFunctions.printList(SCurrent);
			double solutionBestOverAll = st.solveAllTasks(SBestOverAll);
			System.out.println("solutionBestOverAll + " + solutionBestOverAll);
			ListFunctions.printList(SBestOverAll);
			if (SNew < solutionCurrent || SNew < (1 + elipson)*solutionBestStage) {
				System.out.println("SCurrent.add + " + hIndex);
				SCurrent.add(hIndex);
				ListFunctions.printList(SCurrent);
				solutionCurrent = SNew;
			}
			if (solutionCurrent < solutionBestStage) {
				SBestStage = ListFunctions.listEquals(SCurrent);
				timeImproved = -1;
				System.out.println("solutionCurrent < solutionBestStage timeImproved = " + timeImproved);
				ListFunctions.printList(SBestStage);
			}
			if (solutionCurrent < solutionBestOverAll) {
				System.out.println("solutionCurrent < solutionBestOverAll ");
				SBestOverAll = ListFunctions.listEquals(SCurrent);
				ListFunctions.printList(SBestOverAll);
			}
			counter ++;
			timeImproved++;
		}
		SInputStage1 = ListFunctions.listEquals(SCurrent);
		//return SCurrent, SBestOverAll, SBestStage
		return timeImproved;
	}
	
	int rouletteWheelSelection() {
		double scoreSum = 0;
		for (int i = 0; i < score.length; i++) {
			scoreSum += score[i];
		}
		double index = Math.random();
		double prevScore = 0;
		double nextScore = 0;
		double currentScoreSum = 0;
		System.out.println(index);
		for (int i = 0; i < score.length; i++) {
			currentScoreSum +=score[i];
//			System.out.println("currentScoreSum " + currentScoreSum);
			nextScore = currentScoreSum/scoreSum;
//			System.out.println("nextScore " + nextScore);
//			System.out.println("prevScore " + prevScore);
			if (prevScore <= index && index <= nextScore) {
				return i;
			}
			prevScore = nextScore;
		}
		System.out.println("error");
		return 0;
	}

	double updateElipson(double c) {
		SolveTask st = new SolveTask();
		double solution = st.solveAllTasks(SBestStage);
		double elipson = (Math.log(solution) + c)/solution;
		return elipson;
	}
}
