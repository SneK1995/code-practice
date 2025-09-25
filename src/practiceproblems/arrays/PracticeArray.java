package practiceproblems.arrays;

import java.util.HashMap;

public class PracticeArray {
	public static void main(String[] args) {
		PracticeArray ans=new PracticeArray();
//		System.out.println(ans.maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
//		System.out.println(ans.getLongestSubarray(new int[] {-1,0,1,1,-1,-1,0}, 0));
//		System.out.println(ans.subarraySum(new int[] {1,2,3},3));
		System.out.println(ans.subarraySum(new int[] {0,0,0,0,0},0));
	}
	
	//Kadane's Algorithm -> O(n)
	public int maxSubArray(int[] nums) {
        int maxsum=nums[0],sum=nums[0];
        for(int i=1;i<nums.length;i++) {
            sum=Math.max(nums[i],sum+nums[i]);
            maxsum=Math.max(maxsum,sum);
        }
        return maxsum;
    }
	
	//(including positive and negative numbers) => prefix sum and hashmap => O(n)
	public int getLongestSubarray(int[] nums, int k) {
		int n=nums.length;
		HashMap<Integer, Integer> map=new HashMap<>();
		int s=0,max=0;
		for(int i=0;i<n;i++) {
			s+=nums[i];
			if(s==k) {
				max=Math.max(max,i+1);
			} 
			if(map.containsKey(s-k)) {
				//check if prefix sum s-k exists in the map or not
				//if yes, then there exists a subarray inside [0,i] where sum is k, calculate its length
				max=Math.max(max,i-map.get(s-k));
			}
			if(!map.containsKey(s)) {
				map.put(s,i);
			}
		}
		return max;
	}
	
	//extension of the above problem
	public int subarraySum(int[] nums, int k) {
        int n=nums.length;
        int c=0,s=0;
        HashMap<Integer, Integer> map=new HashMap<>();
        for(int i=0;i<n;i++) {
        	s+=nums[i];
        	if(s==k) {
        		c++;
        	}
        	if(map.containsKey(s-k)) {
        		c+=map.get(s-k);
        	}
        	map.put(s,map.getOrDefault(s,0)+1);
        }
        System.out.println("map: "+map);
        return c;
    }
}
