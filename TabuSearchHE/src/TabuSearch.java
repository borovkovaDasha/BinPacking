import java.util.ArrayList;

public class TabuSearch {
	
	private static int HEURISTICS_SIZE = 7;//8;
	private static int NUMBER_OF_ITERATIONS = 1000;
	private static int HEURISTICS_LIST_SIZE = 20;
	private static int FILES_SIZE = 720;
	private static int MAX_NUMBER = 100;
	private static int REPEATE = 5;
	private static String FOLDER = "C:\\data_for_binpacking\\bin1out\\";
	private ArrayList<Integer> heuristic_list_best;
	private ArrayList<Integer> heuristic_list_best_ls;
	private ArrayList<Integer> heuristic_list_current_ls;
	private double sol_best_final;
	private double sol_best_ls;
	private double sol_ls;
	double bestBins;
	double resBins;
	int num_of_shaking;
	
	public void tabuSearch() {
		heuristic_list_best = new ArrayList<Integer>();
		heuristic_list_best_ls = new ArrayList<Integer>();
		heuristic_list_current_ls = new ArrayList<Integer>();
		ArrayList<Integer> heuristic_list_new = new ArrayList<Integer>();
		for (int i = 0; i < HEURISTICS_LIST_SIZE; i++) {
			heuristic_list_best_ls.add((int)(Math.random()*HEURISTICS_SIZE));
			heuristic_list_best.add(5);
		}
		sol_best_final = solve_all_tasks(heuristic_list_best);
		sol_best_ls = solve_all_tasks(heuristic_list_best_ls);
		System.out.println("sol_best_ls " + sol_best_ls);
		print_solution(heuristic_list_best_ls);
		sol_ls = sol_best_ls;
		int max_number = 0;
		num_of_shaking = 1;
		System.out.println("num_of_shaking " + num_of_shaking);
		for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
			max_number = 0;
			while (max_number != MAX_NUMBER) {
				ArrayList<Integer> heuristic_for_shaking = new ArrayList<Integer>();
				for (int j = 0; j < heuristic_list_best_ls.size(); j++)
					heuristic_for_shaking.add(heuristic_list_best_ls.get(j));
				heuristic_list_new = shaking(heuristic_for_shaking);
				localSearch(heuristic_list_new);
				max_number++;
				if (sol_ls < sol_best_ls ) {
//					System.out.println("heuristic_list_current_ls.size() " + heuristic_list_current_ls.size());
					sol_best_ls = sol_ls;
					System.out.println("new is better " + sol_best_ls);
					heuristic_list_best_ls.clear();
					for (int j = 0; j < heuristic_list_current_ls.size(); j++) {
						if (j >= heuristic_list_best_ls.size())
							heuristic_list_best_ls.add(heuristic_list_current_ls.get(j));
						else
							heuristic_list_best_ls.set(j, heuristic_list_current_ls.get(j));
					}
					print_solution(heuristic_list_best_ls);
				}
			}
			System.out.println("All solved!!! " + sol_best_ls);
			num_of_shaking++;
			print_solution(heuristic_list_best_ls);
			System.out.println("num_of_shaking " + num_of_shaking);
			if (sol_best_ls <= sol_best_final) {
				System.out.println("new solution is the best " + sol_best_ls);
				for (int j = 0; j < heuristic_list_best_ls.size(); j++) {
					System.out.print(heuristic_list_best_ls.get(j) + " ");
				}
				System.out.println();
				heuristic_list_best_ls.clear();
				for (int j = 0; j < HEURISTICS_LIST_SIZE; j++) {
					heuristic_list_best_ls.add((int)(Math.random()*HEURISTICS_SIZE));
				}
			}
		}
	}
	
	public void localSearch(ArrayList<Integer> heuristic_list_orig) {
//		System.out.println("local search");
		ArrayList<Integer> list = new ArrayList<Integer>();
//		System.out.println("add");
		for (int k = 0; k < HEURISTICS_SIZE; k++) {
			for (int i = 0; i < heuristic_list_orig.size(); i++) {
				list = new ArrayList<Integer>();
				for (int j = 0; j < heuristic_list_orig.size(); j++) {
					if (i == j)
						list.add(k);
					list.add(heuristic_list_orig.get(j));
				}
				double solution = solve_all_tasks(list);
//				System.out.println("solution " + solution);
				if (solution < sol_ls) {
//					System.out.println("add solution is better");
//					System.out.println("solution " + solution);
					sol_ls = solution;
					heuristic_list_current_ls = list;
//					print_solution(heuristic_list_current_ls);
//					return;
				}
			}
		}
//		System.out.println("remove");
		for (int i = 0; i < heuristic_list_orig.size(); i++) {
			list.clear();
			for (int j =0; j < heuristic_list_orig.size(); j++) {
				list.add(heuristic_list_orig.get(j));
			}
			list.remove(i);
			double solution = solve_all_tasks(list);
//			System.out.println("solution " + solution);
			if (solution < sol_ls) {
//				System.out.println("remove is better");
//				System.out.println("solution " + solution);
				sol_ls = solution;
				heuristic_list_current_ls = list;
//				print_solution(heuristic_list_current_ls);
//				return;
			}
		}
//		System.out.println("swap");
		for (int i = 0; i < heuristic_list_orig.size() - 1; i++) {
			list.clear();
			for (int j =0; j < heuristic_list_orig.size(); j++) {
				list.add(heuristic_list_orig.get(j));
			}
			for (int j = i + 1; j < heuristic_list_orig.size(); j++) {
				int num1 = list.get(i);
				int num2 = list.get(j);
				list.set(i, num2);
				list.set(j, num1);
				double solution = solve_all_tasks(list);
//				System.out.println("solution " + solution);
				if (solution < sol_ls) {
//					System.out.println("solution " + solution);
//					System.out.println("swap is better");
					sol_ls = solution;
					heuristic_list_current_ls = list;
//					print_solution(heuristic_list_current_ls);
//					return;
				}
			}
		}
//		System.out.println("reverse");
		for (int i = 0; i < heuristic_list_orig.size(); i++) {
			list.clear();
			for (int j =0; j < heuristic_list_orig.size(); j++) {
				list.add(heuristic_list_orig.get(j));
			}
			list.set(heuristic_list_orig.size() - i - 1, heuristic_list_orig.get(i));
			double solution = solve_all_tasks(list);
//			System.out.println("solution " + solution);
			if (solution < sol_ls) {
//				System.out.println("solution " + solution);
//				System.out.println("reverse is better");
				sol_ls = solution;
				heuristic_list_current_ls = list;
//				print_solution(heuristic_list_current_ls);
//				return;
			}
		}
	}
	
	public ArrayList<Integer> shaking(ArrayList<Integer> heuristic_list) {
		ArrayList<Integer> list = heuristic_list;
		for (int i = 0; i < num_of_shaking; i++) {
			int probability = (int)(Math.random()*2);
			if (probability == 0) {
				list = shaking_delete(heuristic_list);
			}
			else {
				list = shaking_add(heuristic_list);
			}
		}
		return list;
	}
	
	public ArrayList<Integer> shaking_add(ArrayList<Integer> heuristic_list) {
//		System.out.println("random add");
		int new_heuristic = (int)(Math.random()*HEURISTICS_SIZE);
		int new_heuristic_pos = (int)(Math.random()*heuristic_list.size());
		ArrayList<Integer> return_heuristic = new ArrayList<Integer>();
		for (int i = 0; i < heuristic_list.size(); i++) {
			if (i == new_heuristic_pos) {
				return_heuristic.add(new_heuristic);
			}
			return_heuristic.add(heuristic_list.get(i));
		}
		return heuristic_list;
	}
	
	public ArrayList<Integer> shaking_delete(ArrayList<Integer> heuristic_list) {
//		System.out.println("random delete");
		int heuristic_num = (int)(Math.random()*heuristic_list.size());
		heuristic_list.remove(heuristic_num);
//		System.out.println("remove " + heuristic_num);
		return heuristic_list;
	}
	
	public ArrayList<Integer> shaking_swap(ArrayList<Integer> heuristic_list) {
		int heuristic_num_1 = (int)(Math.random()*heuristic_list.size());
		int heuristic_num_2 = (int)(Math.random()*heuristic_list.size());
		int heuristic_1 = (int)heuristic_list.get(heuristic_num_1);
		int heuristic_2 = (int)heuristic_list.get(heuristic_num_2);
		heuristic_list.set(heuristic_num_1, heuristic_2);
		heuristic_list.set(heuristic_num_2, heuristic_1);
		return heuristic_list;
	}
	
	public ArrayList<Integer> shaking_change_random(ArrayList<Integer> heuristic_list) {
		int heuristic_num = (int)(Math.random()*heuristic_list.size());
		int heuristic = (int)(Math.random()*(HEURISTICS_SIZE));
		heuristic_list.set(heuristic_num, heuristic);
		return heuristic_list;
	}
	
	public double solve_all_tasks(ArrayList<Integer> heuristic_list) {
		double solution = 0;
		for (int i = 1; i <= FILES_SIZE; i++) {
			solution += solve(heuristic_list, (FOLDER + i + ".txt"));
		}
		return solution;
	}
	
	public double solve(ArrayList<Integer> heuristic_list, String fileName) {
		ReadingFile rf = new ReadingFile();
		int[][] elements = rf.readFile(fileName);
		int[] bins = new int[elements.length];
		final ArrayList<Integer> solel = new ArrayList<Integer>();
		final ArrayList<Integer> solbin = new ArrayList<Integer>();
		//create array of baskets
		for (int i = 0; i < bins.length; i++)
		{
			bins[i] = rf.binSize;
		}
		elements = rf.sort(elements);
		int flag = 0;
		int currentBin = 0;
		int i = 0;
		while (flag == 0)
		{
			if (isSolved(elements))
			{
				flag = 1;
				break;
			}
			int alg = (int)heuristic_list.get(i);
			switch (alg) {
				case 0:  
					for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasing.startPacking(elements, bins, rf.binSize, solel, solbin);
				break;
				//case 1:  currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				//currentBin = NextFitDecreasing.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				//break;
				case 1:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinch.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 2:  for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuples.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 3:	 for (int j = 0; j < REPEATE; j++) currentBin = LargestFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 4:	 for (int j = 0; j < REPEATE; j++) currentBin = NextFitDecreasingFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 5:	 for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				case 6:	 for (int j = 0; j < REPEATE; j++) currentBin = DjangAndFinchTuplesFilter.startPacking(elements, bins, currentBin, rf.binSize, solel, solbin);
				break;
				default:
					break;
			}
			if (i == heuristic_list.size() - 1)
				i = 0;
			else i++;
			
		}
		double notEmptyBins = 0;
		for (int j = 0; j < bins.length; j++) {
			if (bins[j] < rf.binSize) {
				notEmptyBins++;
			}
			//if (bins[j] < 0) {
			//	System.out.println("!!!");
			//	System.exit(1);
			//}
		}
		//if (notEmptyBins/rf.bestResult < 1) {
		//	System.out.println("!!!error " + notEmptyBins/rf.bestResult);
		//	System.exit(1);
		//}
		//System.out.println("notEmptyBins " + notEmptyBins);
		//System.out.println("elements.length " + elements.length);
		//bestBins = (double)rf.bestResult;
		//resBins = (double)notEmptyBins;
		return (double)notEmptyBins;
	}
	
	public boolean isSolved(int[][] elements) {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i][1] == 0) {
				return false;
			}
		}
		return true;
	}
	
	public void print_solution(ArrayList<Integer> heuristic_list) {
		System.out.println("heuristic_list.size() " + heuristic_list.size());
		for (int i = 0; i < heuristic_list.size(); i++)
			System.out.print(heuristic_list.get(i) + " ");
		System.out.println();
	}
}
