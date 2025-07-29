package practiceproblems.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import practiceproblems.trees.TreeNode;

public class DPOnLinear2 {

	public static void main(String[] args) {
//		System.out.println(cost(List.of(100,2,100,2,100)));
//		List<Integer> l=Arrays.stream("11 63 15 3 34 89 43 30 39 92 81 25 47 56 10 44 60 82".split(" "))
//				.map(Integer::valueOf).toList();
//			System.out.println(cost2(l));
		System.out.println(rob(new int[] {100,1,20,500,200}));
	}
	
	//Longest increasing subsequence -> O(n^2)
	public static int lengthOfLIS(int[] nums) {
		int n=nums.length;
		int[][] dp=new int[n][n+1];
		for(int[] rows:dp) {
			Arrays.fill(rows, -1);
		}
        return lisRecur(nums,0,-1,dp);
    }
	private static int lisRecur(int[] nums, int i, int j, int[][] dp) {
		if(i==nums.length) {
			return 0;
		}
		if(dp[i][j+1]!=-1) {
			return dp[i][j+1];
		}
		int lastVal= j>-1 ? nums[j]:Integer.MIN_VALUE;
		if(lastVal>=nums[i]) {
			dp[i][j+1]=lisRecur(nums,i+1,j,dp);
		} else {
			dp[i][j+1]=Math.max(1+lisRecur(nums,i+1,i,dp), lisRecur(nums,i+1,j,dp));
		}
		return dp[i][j+1];
	}
	
	//Time -> O(n), Space optimized -> O(n*M), M=>max val of B
	public static int cost(List<Integer> B) {
		int n=B.size();
		int maxVal=B.stream().mapToInt(Integer::intValue).max().getAsInt();
		int[][] dp=new int[n][maxVal+1];
		for(int[] rows:dp) {
			Arrays.fill(rows, -1);
		}
		return Math.max(costRecur(B,1,1,dp),costRecur(B,1,B.get(0),dp));
	}
	private static int costRecur(List<Integer> B, int i, int le, int[][] dp) {
		if(i==B.size()) {
			return 0;
		}
		if(dp[i][le]!=-1) {
			return dp[i][le];
		}
		int val1=Math.abs(le-B.get(i))+costRecur(B,i+1,B.get(i),dp);
		int val2=Math.abs(le-1)+costRecur(B,i+1,1,dp);
		dp[i][le]=Math.max(val1, val2);
		return dp[i][le];
	}
	
	//Space optimized => time -> O(n), space -> O(n*2)~O(n)
	public static int cost2(List<Integer> B) {
		int n=B.size();
		int[][] dp=new int[n][2];
		for(int[] rows:dp) {
			Arrays.fill(rows, -1);
		}
		return Math.max(costRecur2(B,1,0,dp),costRecur2(B,1,1,dp));
	}
	private static int costRecur2(List<Integer> B, int i, int leindex, int[][] dp) {
		if(i==B.size()) {
			return 0;
		}
		if(dp[i][leindex]!=-1) {
			return dp[i][leindex];
		}
		int le=(leindex==0)?1:B.get(i-1);
		int val1=Math.abs(le-1)+costRecur2(B,i+1,0,dp);
		int val2=Math.abs(le-B.get(i))+costRecur2(B,i+1,1,dp);
		dp[i][leindex]=Math.max(val1, val2);
		return dp[i][leindex];
	}
	
	//1-D DP => time complexity -> O(n)
	public static int rob(int[] nums) {
		int n=nums.length;
		int[][] dp=new int[n][2];
		for(int i=0;i<n;i++) {
			dp[i][0]=-1; dp[i][1]=-1;
		}
		return Math.max(nums[0]+recurRob(nums,2,1,dp), recurRob(nums,1,0,dp));
    }
	public static int recurRob(int[] nums, int i, int firstTaken, int[][] dp) {
		int n=nums.length;
		if(i>=n || (firstTaken==1 && i==n-1)) {
			return 0;
		}
		if(dp[i][firstTaken]!=-1) {
			return dp[i][firstTaken];
		}
		dp[i][firstTaken]=Math.max(nums[i]+recurRob(nums,i+2,firstTaken,dp), recurRob(nums,i+1,firstTaken,dp));
		return dp[i][firstTaken];
	}
	
	//DP on tree -> time complexity -> O(2*n) = O(n)
	public int rob(TreeNode root) {
		HashMap<TreeNode, int[]> dp=new HashMap<>();
		return robRecur(root,0,dp);
    }
	public int robRecur(TreeNode node, int parentTaken, HashMap<TreeNode, int[]> dp) {
		if(node==null) {
			return 0;
		}
		if(dp.containsKey(node) && dp.get(node)[parentTaken]!=-1) {
			return dp.get(node)[parentTaken];
		}
		dp.put(node, new int[] {-1,-1});
		int max=robRecur(node.left,0,dp)+robRecur(node.right,0,dp);
		if(parentTaken==0) {
			max=Math.max(max, node.val+robRecur(node.left,1,dp)+robRecur(node.right,1,dp));
		}
		dp.get(node)[parentTaken]=max;
		return max;
	}
	
	public int findTargetSumWays(int[] nums, int target) {
		int n=nums.length;
		int[][] dp=new int[n+1][5000];
		dp[n][2000]=1;
		int s=0;
		for(int i=n-1;i>=0;i--) {
			for(int j=-(s+nums[i]);j<=(s+nums[i]);j++) {
				dp[i][j+2000]=dp[i+1][j-nums[i]+2000]+dp[i+1][j+nums[i]+2000];
			}
			s+=nums[i];
		}
		return dp[0][target+2000];
//		return findTargetRecur(nums,0,target,dp);
    }
	private int findTargetRecur(int[] nums, int i, int s, int[][] dp) {
		int n=nums.length;
		if(i==n && s==0) {
			return 1;
		} else if(i==n && s!=0) {
			return 0;
		}
		if(dp[i][s+1500]!=-1) {
			return dp[i][s+1500];
		}
		dp[i][s+1500]=findTargetRecur(nums,i+1,s-nums[i],dp)+findTargetRecur(nums,i+1,s+nums[i],dp);
		return dp[i][s+1500];
	}
	
	public int coinChange(int[] coins, int amount) {
		int n=coins.length;
		int[][] dp=new int[amount+1][n+1];
//		for(int[] rows:dp) {
//			Arrays.fill(rows,-1);
//		}
		for(int i=1;i<=amount;i++) {
			dp[i][n]=Integer.MAX_VALUE-20;
		}
		for(int i=0;i<=amount;i++) {
			for(int j=n-1;j>=0;j--) {
				dp[i][j]=dp[i][j+1];
				if(i>=coins[j]) {
					dp[i][j]=Math.min(dp[i][j], 1+dp[i-coins[j]][j]);
				}
			}
		}
//		int noOfcoins=coinChangeRecur(coins,amount,0,dp);
		int noOfcoins=dp[amount][0];
        return noOfcoins<Integer.MAX_VALUE-20? noOfcoins:-1;
    }
	private int coinChangeRecur(int[] coins, int amt, int i, int[][] dp) {
		int n=coins.length;
		if(i==n) {
			return amt==0? 0:Integer.MAX_VALUE-20;
		}
		if(dp[amt][i]!=-1) {
			return dp[amt][i];
		}
		int min=Integer.MAX_VALUE;
		min=Math.min(min, coinChangeRecur(coins,amt,i+1,dp));
		if(amt>=coins[i]) {
			min=Math.min(min, 1+coinChangeRecur(coins,amt-coins[i],i,dp));
		}
		dp[amt][i]=min;
		return min;
	}
}
