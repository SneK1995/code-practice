package practiceproblems.string;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

public class StringProblems {

	public static void main(String[] args) {
		StringProblems prob = new StringProblems();
//		System.out.println(prob.frequencySort("raaeaedere"));
		System.out.println(prob.frequencySort1("raaeaedere"));
	}
	
	//Max-heap -> O(n+k*log(k))
	public String frequencySort(String s) {
		HashMap<Character, Integer> hash=new HashMap<>();
		Comparator<Character> comparator=(a,b)-> hash.getOrDefault(b,0).compareTo(hash.getOrDefault(a,0));
		PriorityQueue<Character> heap=new PriorityQueue<>(comparator);
		for(char c:s.toCharArray()) {
			hash.put(c,hash.getOrDefault(c,0)+1);
		}
		for(char c:hash.keySet()) {
			heap.add(c);
		}
		StringBuilder b=new StringBuilder();
		while(!heap.isEmpty()) {
			char c=heap.poll().charValue();
			b.append(String.valueOf(c).repeat(hash.get(c).intValue()));
		}
		return b.toString();
    }
	
	//bucket-sort -> O(n+k) => Asymptotically this is better than previous one,
	//but for small k both are linear almost O(n+klogk) ~ O(n+k) ~ O(n)
	public String frequencySort1(String s) {
		HashMap<Character, Integer> hash=new HashMap<>();
		int max=0;
		for(char c:s.toCharArray()) {
			int count=hash.getOrDefault(c,0)+1;
			hash.put(c,count);
			max=Math.max(max,count);
		}
		ArrayList<HashSet<Character>> buckets=new ArrayList<>(max);
		for(int i=0;i<max;i++) {
			buckets.add(new HashSet<>());
		}
		for(Map.Entry<Character,Integer> entry:hash.entrySet()) {
			int key=entry.getValue().intValue()-1;
			buckets.get(key).add(entry.getKey());
		}
		StringBuilder b=new StringBuilder();
		for(int i=max-1;i>=0;i--) {
			for(char c:buckets.get(i)) {
				b.append(String.valueOf(c).repeat(i+1));
			}
		}
		return b.toString();
	}
}
