import java.util.ArrayList;

public class ListFunctions {
	public static ArrayList<Integer> getNewList(int hIndex, ArrayList<Integer> heuristic_list) {
		ArrayList<Integer> new_heuristic_list = new ArrayList<Integer>();
		for (int i = 0; i < heuristic_list.size(); i++) {
			new_heuristic_list.add(heuristic_list.get(i));
		}
		new_heuristic_list.add(hIndex);
		return new_heuristic_list;
	}
	
	public static ArrayList<Integer> listEquals(ArrayList<Integer> heuristic_list) {
		ArrayList<Integer> new_heuristic_list = new ArrayList<Integer>();
		for (int i = 0; i < heuristic_list.size(); i++) {
			new_heuristic_list.add(heuristic_list.get(i));
		}
		return new_heuristic_list;
	}
}
