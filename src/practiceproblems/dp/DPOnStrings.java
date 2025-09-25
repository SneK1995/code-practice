package practiceproblems.dp;

import java.util.Arrays;

public class DPOnStrings {
	public static void main(String[] args) {
		DPOnStrings s=new DPOnStrings();
		System.out.println(s.longestPalindrome("babad"));
		System.out.println(s.longestPalindrome("cbbd"));
	}
	
	//time -> O(n^2), optimal -> O(n) manacher's algorithm
	public String longestPalindrome(String s) {
		int n=s.length();
		int[][] dp=new int[n][n];
		for(int[] rows:dp) {
			Arrays.fill(rows,-1);
		}
		lPalinRecur(s,0,n-1,dp);
		int b=-1,e=-1,lmax=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(dp[i][j]==1 && lmax<(j-i+1)) {
					lmax=j-i+1;
					b=i; e=j;
				}
			}
		}
		System.out.println();
		return s.substring(b,e+1);
    }
	private boolean lPalinRecur(String s, int i, int j, int[][] dp) {
		if(i>j) {
			dp[i][j]=0;
			return false;
		}
		if(i==j) {
			dp[i][j]=1;
			return true;
		}
		if(dp[i][j]!=-1) {
			return dp[i][j]==1;
		}
		if(s.charAt(i)==s.charAt(j) &&
			((j-i)==1 || lPalinRecur(s,i+1,j-1,dp))) {
			dp[i][j]=1;
			return true;
		}
		lPalinRecur(s,i,j-1,dp);
		lPalinRecur(s,i+1,j,dp);
		dp[i][j]=0;
		return false;
	}
}
