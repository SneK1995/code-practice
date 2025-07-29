package practiceproblems.dp;

import java.util.Arrays;

public class DPOnPartition {
	
	public static void main(String[] args) {
		System.out.println(maxCoins(new int[] {3,1,5,8}));
	}

	public static int maxCoins(int[] nums) {
		return coinsRecur(nums);
    }
	private static int coinsRecur(int[] nums) {
		int n=nums.length;
		if(n==1) {
			return nums[0];
		}
		int max=Integer.MIN_VALUE;
		for(int i=0;i<n;i++) {
			int[] newnums=new int[n-1];
			for(int j=0,k=0;j<n;j++) {
				if(i!=j) {
					newnums[k++]=nums[j];
				}
			}
			int l=i==0? 1:nums[i-1];
			int r=i==n-1? 1:nums[i+1];
			int cost=l*nums[i]*r;
			max=Math.max(max, cost+coinsRecur(newnums));
		}
		return max;
	}
	
	//stick cutting -> time complexity => O(c^3) optimal time, very inefficient space => O(n^2)
	public int minCost(int n, int[] cuts) {
		int[][] dp=new int[n+1][n+1];
		for(int[] rows:dp) {
			Arrays.fill(rows,-1);
		}
        return stickRecur(cuts,0,n,dp);
    }
	private int stickRecur(int[] cuts, int m, int n, int[][] dp) {
		int l=n-m;
		if(l==1) {
			return 0;
		}
		if(dp[m][n]!=-1) {
			return dp[m][n];
		}
		int min=Integer.MAX_VALUE;
		for(int i=0;i<cuts.length;i++) {
			if(m<cuts[i] && cuts[i]<n) {
				min=Math.min(min, l+stickRecur(cuts,m,cuts[i],dp)+stickRecur(cuts,cuts[i],n,dp));
			}
		}
		dp[m][n]=min==Integer.MAX_VALUE? 0:min;
		return dp[m][n];
	}
	
	//stick cutting -> time complexity => O(c^3) optimal time, space complexity => O(c^2)
	public int minCost2(int n, int[] cuts) {
		int cl=cuts.length;
		int[] newcuts=new int[cl+2];
		newcuts[0]=0; newcuts[cl+1]=n;
		for(int i=1,j=0;j<cl;i++,j++) {
			newcuts[i]=cuts[j];
		}
		Arrays.sort(newcuts); //for strictly checking in b/w the partition
		int[][] dp=new int[cl+2][cl+2];
		for(int[] rows:dp) {
			Arrays.fill(rows,-1);
		}
        return stickRecur2(newcuts,0,cl+1,dp);
    }
	private int stickRecur2(int[] cuts, int m, int n, int[][] dp) {
		int l=cuts[n]-cuts[m];
		if(l==1) {
			return 0;
		}
		if(dp[m][n]!=-1) {
			return dp[m][n];
		}
		int min=Integer.MAX_VALUE;
		for(int i=m+1;i<=n-1;i++) { //strict checking b/w m+1 and n-1 because newcut is sorted
			min=Math.min(min, l+stickRecur(cuts,m,i,dp)+stickRecur(cuts,i,n,dp));
		}
		dp[m][n]=min==Integer.MAX_VALUE? 0:min;
		return dp[m][n];
	}

	//Matrix-chain multiplication => O(n^3), space complexity => O(n^2)
	public static int mcm(int[] p){
		int n=p.length;
		int[][] dp=new int[n][n];
		for(int[] rows:dp) {
			Arrays.fill(rows,-1);
		}
		return mcmRecur(p,0,n-1,dp);
	}
	private static int mcmRecur(int[] p, int i, int j, int[][] dp) {
		if((j-i)==1) {
			return 0;
		}
		if(dp[i][j]!=-1) {
			return dp[i][j];
		}
		int min=Integer.MAX_VALUE;
		for(int k=i+1;k<j;k++) {
			int cost=p[i]*p[k]*p[j];
			min=Math.min(min, cost+mcmRecur(p,i,k,dp)+mcmRecur(p,k,j,dp));
		}
		dp[i][j]=min;
		return min;
	}
}
