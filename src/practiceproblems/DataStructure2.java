package practiceproblems;

import java.util.HashMap;
import java.util.HashSet;

public class DataStructure2 {
	
	public static void main(String[] args) {
		System.out.println(longestConsecutive2(new int[] {0,3,7,2,5,8,4,6,0,1}));
	}

	//Disjoint Set variation -> O(n)
	public static int longestConsecutive(int[] nums) {
		HashMap<Integer, Integer> table=new HashMap<>();
		HashMap<Integer, Integer> parMap=new HashMap<>();
		int max=0;
		int nparents=0;
		for(int i=0;i<nums.length;i++) {
			int val=0;
			if(table.containsKey(nums[i])) {
				continue;
			
			} else if(table.containsKey(nums[i]-1)) {
				table.put(nums[i], table.get(nums[i]-1));
				int increase=1+updateRight(table, nums[i]+1, table.get(nums[i]-1));
				val=updateCount(parMap, table.get(nums[i]-1), increase);
			
			} else if(table.containsKey(nums[i]+1)) {
				table.put(nums[i], table.get(nums[i]+1));
				val=updateCount(parMap, table.get(nums[i]+1), 1);
			
			} else {
				val=updateCount(parMap, nparents, 1);
				table.put(nums[i], nparents);
				nparents++;
			}
			max=Math.max(max, val);
		}
		return max;
    }
	public static int updateCount(HashMap<Integer, Integer> parMap, int parent, int increase) {
		int val=parMap.getOrDefault(parent, 0)+increase;
		parMap.put(parent, val);
		return val;
	}
	public static int updateRight(HashMap<Integer, Integer> table, int key, int par) {
		int val=0;
		while(table.containsKey(key)) {
			table.put(key, par);
			key++;
			val++;
		}
		return val;
	}
	
	//Disjoint set union by size -> O(n)
	public static int maxlen=0;
	public static HashMap<Integer, Integer> par=new HashMap<>();
	public static HashMap<Integer, Integer> size=new HashMap<>();
	public static int longestConsecutive2(int[] nums) {
		maxlen=0;
		int n=nums.length;
		par.clear();
		size.clear();
		for(int i=0;i<n;i++) {
			par.put(nums[i], nums[i]);
			size.put(nums[i], 1);
		}
		for(int i=0;i<n;i++) {
			maxlen=Math.max(maxlen, size.get(nums[i]));
			int prev=nums[i]-1;
			int next=nums[i]+1;
			if(par.containsKey(prev)) {
				unionBySize(nums[i], prev);
			}
			if(par.containsKey(next)) {
				unionBySize(nums[i], next);
			}
		}
		System.out.println("parent map: "+par);
		System.out.println("size map: "+size);
		return maxlen;
	}
	private static int find(int e) {
		if(par.get(e).intValue()==e) {
			return e;
		}
		par.put(e, find(par.get(e).intValue()));
		return par.get(e);
	}
	private static void unionBySize(int i, int j) {
		int uli=find(i);
		int ulj=find(j);
		if(uli==ulj) {
			return;
		}
		int si=size.get(uli).intValue();
		int sj=size.get(ulj).intValue();
		int total=si+sj;
		if(si<sj) {
			par.put(uli, ulj);
			size.put(ulj, total);
		} else {
			par.put(ulj, uli);
			size.put(uli, total);
		}
		maxlen=Math.max(maxlen, total);
	}
	
	//Greedy+set -> O(n)
	public static int longestConsecutive3(int[] nums) {
		HashSet<Integer> set=new HashSet<>();
		for(int i:nums) {
			set.add(i);
		}
		int maxlen=0;
		for(int i:set) {
			if(!set.contains(i-1)) {
				int len=1;
				while(set.contains(i+1)) {
					i++;
					len++;
				}
				maxlen=Math.max(maxlen, len);
			}
		}
		return maxlen;
	}
}
