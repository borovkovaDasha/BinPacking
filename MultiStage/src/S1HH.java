import java.util.ArrayList;

public class S1HH {
	double[] score;
	ArrayList<Integer> SInitial;
	ArrayList<Integer> SCurrent;
	ArrayList<Integer> SInputStage1;
	ArrayList<Integer> SBestOverAll;
	ArrayList<Integer> SBestStage;
	private static int TIME_ELAPSED = 10;
	private static int LIMIT = 10;
	String fileName = "C:\\data_for_binpacking\\bin1out\\1.txt";
	
	S1HH(double[] score, ArrayList<Integer> SInputStage1, ArrayList<Integer> SBestOverAll, ArrayList<Integer> SBestStage) {
		this.score = score;
		this.SInputStage1 = SInputStage1;
		this.SBestOverAll = SBestOverAll;
		this.SCurrent = SInputStage1;
		this.SBestStage = SBestStage;
	}
	
	void solve(int timeImproved, double c) {
		int hIndex = rouletteWheelSelection();
		System.out.println("hIndex = " + hIndex);
		int counter = 0;
		double elipson = 0.0;
		if (timeImproved >= TIME_ELAPSED) {
			System.out.println("timeImproved = " + timeImproved);
			SCurrent = SBestStage;
			elipson = updateElipson(c);
			System.out.println("elipson = " + elipson);
			timeImproved++;
		}
		while(counter < LIMIT) {
			double SNew = 0.0;
			SolveTask st = new SolveTask();
			SNew = st.solve(hIndex, SCurrent, fileName);
			System.out.println("SNew " + SNew);
			if (SNew == -1) {
				System.out.println("SOLVED!!!");
				System.exit(0);
			}
			double solutionBestStage = st.solve(-1, SBestStage, fileName);
			System.out.println("solutionBestStage + " + solutionBestStage);
			double solutionCurrent = st.solve(-1, SCurrent, fileName);
			System.out.println("solutionCurrent + " + solutionCurrent);
			double solutionBestOverAll = st.solve(-1, SBestOverAll, fileName);
			System.out.println("solutionBestOverAll + " + solutionBestOverAll);
			if (SNew <= solutionCurrent || SNew <= (1 + elipson)*solutionBestStage) {
				System.out.println("SCurrent.add + " + hIndex);
				SCurrent.add(hIndex);
				solutionCurrent = SNew;
			}
			if (solutionCurrent <= solutionBestStage) {
				SBestStage = SCurrent;
				timeImproved++;
				System.out.println("solutionCurrent <= solutionBestStage timeImproved = " + timeImproved);
			}
			if (solutionCurrent <= solutionBestOverAll) {
				System.out.println("solutionCurrent <= solutionBestOverAll ");
				SBestOverAll = SCurrent;
			}
			counter ++;
		}
		SInputStage1 = SCurrent;
		//return SCurrent, SBestOverAll, SBestStage
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
		for (int i = 1; i < score.length; i++) {
			currentScoreSum +=score[i];
			nextScore = currentScoreSum/scoreSum;
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
		double solution = st.solve(-1, SBestStage, fileName);
		double elipson = (Math.log(solution) + c)/solution;
		return elipson;
	}
}
