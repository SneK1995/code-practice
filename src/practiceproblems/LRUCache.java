package practiceproblems;

import java.util.LinkedHashMap;

public class LRUCache {

	LinkedHashMap<Integer, Integer> cache;
	int capacity;
	
	public LRUCache(int capacity) {
        this.capacity=capacity;
        cache=new LinkedHashMap<>(capacity);
	}
	
	private void updateAccess(int key) {
		int val=cache.get(key);
		cache.remove(key);
		cache.putLast(key, val);
	}
    
    public int get(int key) {
    	if(cache.containsKey(key)) {
    		updateAccess(key);
    		return cache.get(key);
    	}
    	return -1;
    }
    
    public void put(int key, int value) {
    	if(cache.size()==this.capacity && !cache.containsKey(key)) {
    		cache.pollFirstEntry();
    	}
    	cache.put(key, value);
    	updateAccess(key);
    }
    
    public static void main(String[] args) {
		LRUCache cache=new LRUCache(2);
		cache.put(2, 6);
		System.out.println(cache.cache);
		cache.put(1, 5);
		System.out.println(cache.cache);
		cache.put(1, 2);
		System.out.println(cache.cache);
		cache.get(1);
		System.out.println(cache.cache);
		cache.get(2);
		System.out.println(cache.cache);
	}
}
