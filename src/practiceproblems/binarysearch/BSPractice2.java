package practiceproblems.binarysearch;

import java.util.Arrays;

public class BSPractice2 {

	//Binary search on ans space
	public int minCapability(int[] nums, int k) {
        int min=Arrays.stream(nums).min().getAsInt();
        int max=Arrays.stream(nums).max().getAsInt();
        int ans=max;
        while(max>=min) {
        	int mid=(max+min)/2;
        	if(isRobPossible2(nums,k,mid)) {
        		ans=mid;
        		max=mid-1;
        	} else {
        		min=mid+1;
        	}
        }
        return ans;
    }
	//Greedy decision => O(log M) * O(n), since our aim here is to rob at-least k houses, not maximizing amount stolen.
	//Hence greedy decision to choose alternate houses to rob at-least k houses works.
	private boolean isRobPossible2(int[] nums, int k, int cap) {
		int n=nums.length;
		int stolen=0,prev=-2;
		for(int i=0;i<n;i++) {
			if(nums[i]<=cap && (i-prev)>=2) {
				prev=i;
				stolen++;
			}
			if(stolen==k) {
				return true;
			}
		}
		stolen=0; prev=-1;
		for(int i=0;i<n;i++) {
			if(nums[i]<=cap && (i-prev)>=2) {
				prev=i;
				stolen++;
			}
			if(stolen==k) {
				return true;
			}
		}
		return false;
	}
	//DP 2D decision => O(log M) * O(nk), finding all the possibilities of robbing at-least k houses
	private boolean isRobPossible(int[] nums, int k, int cap) {
		int n=nums.length;
		int[][] dp=new int[n][k+1];
		for(int[] rows:dp) {
			Arrays.fill(rows,-1);
		}
		return robPossibleRecur(nums,k,cap,0,0,dp);
	}
	private boolean robPossibleRecur(int[] nums, int k, int cap, int stolen, int i, int[][] dp) {
		int n=nums.length;
		if(i>=n && stolen<k) {
			return false;
		}
		if(stolen==k) {
			return true;
		}
		if(dp[i][stolen]!=-1) {
			return dp[i][stolen]==1;
		}
		boolean val1=robPossibleRecur(nums,k,cap,stolen,i+1,dp);
		if(val1) {
			dp[i][stolen]=1;
			return true;
		}
		boolean val2=nums[i]<=cap && robPossibleRecur(nums,k,cap,stolen+1,i+2,dp);
		if(val2) {
			dp[i][stolen]=1;
			return true;
		}
		dp[i][stolen]=0;
		return false;
	}
}
