import java.util.ArrayList;

public class TabuSearch {
	
	private static int HEURISTICS_SIZE = 7;//8;
	private static int NUMBER_OF_ITERATIONS = 1000;
	private static int HEURISTICS_LIST_SIZE = 300;
	private static int SHAKE_SIZE = 10;
	private static int SHAKE_COUNT = 0;
	private static int FILES_SIZE = 720;
	private static int MAX_NUMBER = 10000;
	private static int REPEATE = 3;
	private static String FOLDER = "C:\\data_for_binpacking\\bin1out\\";
	private ArrayList<Integer> heuristic_list_best;
	private double sol_best_final;
	private ArrayList<Integer> heuristic_list_best_ls;
	private double sol_best_ls;
	private double sol_best_ls_size;
	private double sol_ls;
	double bestBins;
	double resBins;
	int numberOfSolved;
	
	public void tabuSearch() {
		heuristic_list_best = new ArrayList<Integer>();
		heuristic_list_best_ls = new ArrayList<Integer>();
		numberOfSolved = 0;
		//List<int[]> tabuList = new ArrayList();
		ArrayList<Integer> heuristic_list_new = new ArrayList<Integer>();
		//sol_best = 1000.0;
		for (int i = 0; i < HEURISTICS_LIST_SIZE; i++) {
			//heuristic_list_best.add((int)5);//(int)(Math.random()*HEURISTICS_SIZE));
			heuristic_list_best.add((int)(Math.random()*HEURISTICS_SIZE));
		}
		sol_best_final = solve_all_tasks(heuristic_list_best);
		heuristic_list_best_ls.add(5);
		sol_best_ls = solve_all_tasks(heuristic_list_best_ls);
		sol_best_ls_size = 1;
		int max_number = 0;
		for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
			// shaking
			while (numberOfSolved != FILES_SIZE || max_number == MAX_NUMBER) {
				ArrayList<Integer> heuristic_for_shaking = new ArrayList<Integer>();
				for (int j = 0; j < heuristic_list_best_ls.size(); j++)
					heuristic_for_shaking.add(heuristic_list_best_ls.get(j));
				heuristic_list_new = shaking(heuristic_for_shaking);
				// if heuristic_list_new is not in tabulist
				// localsearch
				localSearch(heuristic_list_new);
				max_number++;
				//solve only part of task
				double sol_best = solve_all_tasks(new ArrayList(heuristic_list_best.subList(0, heuristic_list_new.size())));
				if (sol_ls <= sol_best ) {
					//System.out.println("heuristic_list_new.size() " + heuristic_list_new.size());
						sol_best_ls = sol_ls;
						//System.out.println("new is better " + sol_best_ls);
						sol_best_ls_size = heuristic_list_new.size();
						//add to tabulist
						//remove the first from tabu
						for (int j = 0; j < heuristic_list_new.size(); j++) {
							if (j >= heuristic_list_best_ls.size())
								heuristic_list_best_ls.add(heuristic_list_new.get(j));
							else
								heuristic_list_best_ls.set(j, heuristic_list_new.get(j));
						//}
					}
					//for (int j = 0; j < heuristic_list_best.size(); j++) {
					//	System.out.print(heuristic_list_best.get(j) + " ");
					//}
					//System.out.println();
				}
				//else
				//	System.out.println("else! ");
			}
			if (numberOfSolved == FILES_SIZE) {
				System.out.println("All solved!!!");
				if (sol_best_ls <= sol_best_final) {
					System.out.println("new solution is the best " + sol_best_ls);
					for (int j = 0; j < heuristic_list_best_ls.size(); j++) {
						System.out.print(heuristic_list_best_ls.get(j) + " ");
					}
					System.out.println();
				}
				heuristic_list_best_ls.clear();
				heuristic_list_best_ls.add(5);
				numberOfSolved = 0;
			}
		}
	}
	
	public void localSearch(ArrayList<Integer> heuristic_list_orig) {
		ArrayList<Integer> list = heuristic_list_orig;
		heuristic_list_best_ls = heuristic_list_orig;
		sol_ls = solve_all_tasks(heuristic_list_orig);
		//int i = (int)(Math.random()*(heuristic_list_orig.size()));
		//int j = (int)(Math.random()*(heuristic_list_orig.size()));
		for (int i = 0; i < heuristic_list_orig.size() - 1; i++) {
	           for (int k = i + 1; k < heuristic_list_orig.size(); k++) {
	        	   list = opt2Swap(list, i, k);
	        	   double sol_cur = solve_all_tasks(list);
	               if (sol_cur < sol_ls) {
	            	   heuristic_list_best_ls = list;
	            	   sol_ls = sol_cur;
	            	   i = 0;
	            	   k = i + 1;
	               }
	           }
	       }
		
	}
	
	public ArrayList<Integer> opt2Swap(ArrayList<Integer> heuristic_list, int i, int k) {
		// 1. take route[0] to route[i-1] and add them in order to new_route
	    // 2. take route[i] to route[k] and add them in reverse order to new_route
	    // 3. take route[k+1] to end and add them in order to new_route
	    // return new_route;
		ArrayList<Integer> heuristic_list_new = new ArrayList<Integer>();
		for (int j = 0; j <= i - 1; j++)
			heuristic_list_new.add(heuristic_list.get(j));
		for (int j = k; j >= i; j--)
			heuristic_list_new.add(heuristic_list.get(j));
		for (int j = k + 1; j < heuristic_list.size(); j++)
			heuristic_list_new.add(heuristic_list.get(j));
		return heuristic_list_new;
	}
	
	/* primitive
	 * public void localSearch(ArrayList<Integer> heuristic_list_orig) {
		ArrayList<Integer> list = heuristic_list_orig;
		heuristic_list_best_ls = heuristic_list_orig;
		sol_best_ls = solve_all_tasks(heuristic_list_orig);
		int i = (int)(Math.random()*(heuristic_list_orig.size()));
		int j = (int)(Math.random()*(heuristic_list_orig.size()));
		if (i > j) {
			int tmp = i;
			i = j;
			j = tmp;
		}
		for (; i < list.size() - 1; i++) {
			for (; j < list.size(); j++) {
				int heuristic_1 = (int)list.get(i);
				int heuristic_2 = (int)list.get(j);
				list.set(i, heuristic_2);
				list.set(j, heuristic_1);
				double sol_cur = solve_all_tasks(list);
				if (sol_cur < sol_best_ls) {
					sol_best_ls = sol_cur;
					heuristic_list_best_ls = list;
				}
			}
		}
		
	}*/
	
	public ArrayList<Integer> shaking(ArrayList<Integer> heuristic_list) {
		int shake_count = 0;//= (int)(Math.random()*SHAKE_COUNT);
		for (int i = 0; i <= shake_count; i++) {
			int shake = (int)(Math.random()*SHAKE_SIZE);
			switch (shake) {
				case 0: heuristic_list = shaking_swap(heuristic_list); 
				break;
				case 3: case 4:  heuristic_list = shaking_change_random(heuristic_list);
				break;
				case 8: case 1: case 2: case 6: case 5: case 7:  heuristic_list = shaking_add(heuristic_list);
				break;
				case 9:  if (heuristic_list.size() > HEURISTICS_LIST_SIZE/2) heuristic_list = shaking_delete(heuristic_list);
				else i--;
				break;
				default:
				break;
			}	
		}
		return heuristic_list;
	}
	
	public ArrayList<Integer> shaking_add(ArrayList<Integer> heuristic_list) {
		int new_heuristic = (int)(Math.random()*HEURISTICS_SIZE);
		heuristic_list.add(new_heuristic);
		return heuristic_list;
	}
	
	public ArrayList<Integer> shaking_delete(ArrayList<Integer> heuristic_list) {
		int heuristic_num = (int)(Math.random()*heuristic_list.size());
		heuristic_list.remove(heuristic_num);
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
		
		//while (flag == 0)
		//{
		for (int i = 0; i < heuristic_list.size(); i++) {
			if (isSolved(elements))
			{
				numberOfSolved++;
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
			//if (i == heuristic_list.size() - 1)
			//	i = 0;
			//else i++;
			
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
}
