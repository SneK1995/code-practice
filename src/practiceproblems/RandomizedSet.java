package practiceproblems;

import java.util.ArrayList;
import java.util.HashMap;

public class RandomizedSet {
	
	private HashMap<Integer, Boolean> map;
	private ArrayList<Integer> presentList;

	public RandomizedSet() {
		map = new HashMap<>();
		presentList = new ArrayList<>();
	}
	
	private boolean insert(int val) {
		Integer intVal = Integer.valueOf(val);
		if(map.containsKey(intVal)) {
			return false;
		}
		map.put(intVal, Boolean.TRUE);
		presentList.add(intVal);
		return true;
	}
	
	private boolean remove(int val) {
		Integer intVal = Integer.valueOf(val);
		if(!map.containsKey(intVal)) {
			return false;
		}
		map.remove(intVal);
		presentList.remove(intVal);
		return true;
	}
	
	private int getRandom() {
		double randomNo = Math.random();
		int total = presentList.size();
		int randomIndex = (int)((double)total*randomNo);
		return presentList.get(randomIndex).intValue();
	}
	
	public static void main(String[] args) {
		RandomizedSet set = new RandomizedSet();
		boolean inserted = set.insert(1);
		set.insert(2);
		set.insert(3);
		set.insert(4);
		boolean removed = set.remove(0);
		System.out.println("" + inserted + "," + set.getRandom() + "," + removed);
	}
}
