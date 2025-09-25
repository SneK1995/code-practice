package practiceproblems.binarysearch;

import java.util.Arrays;

public class BSPractice2 {
	
	public static void main(String[] args) {
		BSPractice2 ans=new BSPractice2();
		// System.out.println(ans.minEatingSpeed(new int[]{30,11,23,4,20},5));
		// System.out.println(ans.minEatingSpeed(new int[]{3,6,7,11},8));
		// System.out.println(ans.shipWithinDays(new int[]{1,2,3,1,1},4));
		System.out.println(ans.splitArray(new int[]{1,4,4},3));
	}

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
	
	public int minEatingSpeed(int[] piles, int h) {
		int min=1;
		int max=Arrays.stream(piles).max().getAsInt();
		int ans=max;
		while(min<=max) {
			int mid=(max+min)/2;
			if(isEatingPossible(piles,h,mid)) {
				ans=mid;
				max=mid-1;
			} else {
				min=mid+1;
			}
		}
        return ans;
    }
	private boolean isEatingPossible(int[] piles, int h, int k) {
		int n=piles.length;
		int remainH=h;
		for(int i=0;i<n;i++) {
			int reduce=1;
			if(piles[i]%k==0) {
				reduce=(piles[i]/k);
			} else if(piles[i]>k) {
				reduce=(piles[i]/k)+1;
			}
			remainH-=reduce;
			if(remainH<0) {
				return false;
			}
		}
		return true;
	}

	public int shipWithinDays(int[] weights, int days) {
		int min=Arrays.stream(weights).max().getAsInt();
		int max=Arrays.stream(weights).sum();
		int ans=max;
		while(min<=max) {
			int mid=(max+min)/2;
			if(isShippingPossible(weights,days,mid)) {
				ans=mid;
				max=mid-1;
			} else {
				min=mid+1;
			}
		}
        return ans;
    }
	private boolean isShippingPossible(int[] weights, int days, int w) {
		int d=1,wsum=0;
		for(int i=0;i<weights.length;i++) {
			wsum+=weights[i];
			if(wsum>w) {
				d++;
				if(d>days) {
					return false;
				}
				wsum=weights[i];
			}
		}
		return true;
	}

	public int splitArray(int[] nums, int k) {
		int min=Arrays.stream(nums).max().getAsInt();
		int max=Arrays.stream(nums).sum();
		int ans=max;
		while(min<=max) {
			int mid=(max+min)/2;
			System.out.println(String.format("checking for min:%s max%s mid:%s", min,max,mid));
			if(isSplitPossible(nums,k,mid)) {
				ans=mid;
				max=mid-1;
			} else {
				min=mid+1;
			}
			System.out.println("ans: "+ans);
		}
        return ans;
    }
	private boolean isSplitPossible(int[] nums, int k, int lsum) {
		int sum=0,sub=1;
		for(int i=0;i<nums.length;i++) {
			sum+=nums[i];
			if(sum>lsum) {
				sum=nums[i];
				sub++;
				if(sub>k) {
					return false;
				}
			}
		}
		return true;
	}
}
