package practiceproblems;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class LFUCache {
	
	class Node {
		int val;
		int count;
		public Node(int val, int count) {
			this.val=val;
			this.count=count;
		}
		@Override
		public String toString() {
			return String.format("{val: %d, count: %d}", this.val, this.count);
		}
	}
	
	HashMap<Integer, Node> cache;
	LinkedHashMap<Integer, LinkedHashSet<Integer>> countMap;
	int capacity;
	
	public LFUCache(int capacity) {
		this.cache=new HashMap<>(capacity);
		this.countMap=new LinkedHashMap<>();
		this.capacity=capacity;
    }
	
	private void adjustCountMap(int key) {
		int curcount=cache.get(key).count;
		int prevcount=curcount-1;
		if(countMap.containsKey(prevcount)) {
			countMap.get(prevcount).remove(key);
			if(countMap.get(prevcount).isEmpty()) {
				countMap.remove(prevcount);
			}
		}
		if(countMap.containsKey(curcount)) {
			countMap.get(curcount).addLast(key);
		} else {
			LinkedHashSet<Integer> linkedSet=new LinkedHashSet<>();
			linkedSet.addLast(key);
			if(countMap.isEmpty()) {
				countMap.putLast(curcount, linkedSet);
			} else {
				int topKey=countMap.firstEntry().getKey().intValue();
				if(curcount<topKey) {
					countMap.putFirst(curcount, linkedSet);
				} else if(curcount>topKey) {
					countMap.putLast(curcount, linkedSet);
				}
			}
		}
	}
	
	private void removeKey() {
		int countkey=countMap.firstEntry().getKey().intValue();
		int key=countMap.get(countkey).removeFirst().intValue();
		cache.remove(key);
		if(countMap.get(countkey).isEmpty()) {
			countMap.pollFirstEntry();
		}
	}
	
    public int get(int key) {
    	if(cache.containsKey(key)) {
    		cache.get(key).count++;
    		adjustCountMap(key);
    		return cache.get(key).val;
    	}
        return -1;
    }
    
    public void put(int key, int value) {
    	if(cache.containsKey(key)) {
    		cache.get(key).count++;
    		cache.get(key).val=value;
    	} else {
    		if(this.capacity==cache.size()) {
    			removeKey();
    		}
    		Node node=new Node(value, 1);
    		cache.put(key, node);
    	}
    	adjustCountMap(key);
    }
    
    public void display() {
    	System.out.println("cache: "+cache);
    	System.out.println("countmap: "+countMap);
    }
    
    public static void main(String[] args) {
		LFUCache cache=new LFUCache(2);
		cache.put(1, 1);
		cache.display();
		cache.put(2, 2);
		cache.display();
		cache.get(1);
		cache.display();
		cache.put(3, 3);
		cache.display();
		cache.get(2);
		cache.display();
		cache.get(3);
		cache.display();
		cache.put(4, 4);
		cache.display();
		cache.get(1);
		cache.display();
		cache.get(3);
		cache.display();
		cache.get(4);
		cache.display();
	}
}
