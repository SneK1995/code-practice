package practiceproblems.dp;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DPOnLinear {
	
	public static void main(String[] args) {
//		System.out.println(abbreviation("daBcd", "ABC"));
//		System.out.println(fibonacciModified(0, 1, 6));
//		System.out.println(cost(List.of(100,2,100,2,100)));
//		equal(List.of(1,1,5));
//		System.out.println(equal(List.of(2,2,3,7)));
//		System.out.println(stockmax(List.of(1,2,100)));
//		System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
		System.out.println(minDistance("horse", "ros"));
	}

	public static String abbreviation(String a, String b) {
		int m=a.length(),n=b.length();
		boolean[][] pos=new boolean[m+1][n+1];
		pos[0][0]=true;
		for(int i=0;i<m;i++) {
			int ac=(int)a.charAt(i);
			for(int j=0;j<=n;j++) {
				if(pos[i][j]) {
					if(j<n) {
						int bc=(int)b.charAt(j);
						if((ac==bc)||((ac-32)==bc)) {
							pos[i+1][j+1]=true;
						}
					}
					if(ac>=97) {
						pos[i+1][j]=true;
					} 
				}
			}
		}
		System.out.println("The pos: ");
		for(int i=0;i<=m;i++) {
			System.out.println();
			for(int j=0;j<=n;j++) {
				System.out.print(pos[i][j] + " ");
			}
		}
		System.out.println();
		return pos[m][n]?"YES":"NO";
    }

	public static BigInteger fibonacciModified(int t1, int t2, int n) {
		BigInteger[] arr=new BigInteger[n];
		arr[0]=BigInteger.valueOf(t1);
		arr[1]=BigInteger.valueOf(t2);
		for(int i=2;i<n;i++) {
			arr[i]=arr[i-2].add(arr[i-1].multiply(arr[i-1]));
		}
		return arr[n-1];
    }
	
	public static int equal(List<Integer> arr) {
		int sum=arr.stream().mapToInt(Integer::intValue).sum();
		return recequal(arr.size(),sum);
    }
	
	private static int recequal(int n, int sum) {
		System.out.println("h");
		if(sum%n==0) {
			System.out.println("sum: "+sum);
			return 0;
		}
		int min=Integer.MAX_VALUE;
		min=Math.min(min, 1+recequal(n,sum+n-1));
//		min=Math.min(min, 1+recequal(n, sum+(2*n-2)));
//		min=Math.min(min, 1+recequal(n, sum+(5*n-5)));
		return min;
	}
	
	public static long stockmax(List<Integer> prices) {
		HashMap<String, Long> dp=new HashMap<>();
		return profitinfo(prices, 0, 0, dp);
    }
	
	private static long profitinfo(List<Integer> prices, int n, int units, HashMap<String, Long> dp) {
		if(n==prices.size()) {
			return 0l;
		}
		String key=n+"_"+units;
		if(dp.containsKey(key)) {
			return dp.get(key);
		}
		long pr=Long.MIN_VALUE;
		pr=Math.max(pr, profitinfo(prices, n+1, units, dp));
		if(n<prices.size()-1) {
			pr=Math.max(pr, profitinfo(prices, n+1, units+1, dp)-prices.get(n).longValue());
		}
		if(n>0) {
			pr=Math.max(pr, profitinfo(prices, n+1, 0, dp)+(units*prices.get(n).longValue()));
		}
		dp.put(key, pr);
		return pr;
	}
	
	public static boolean isInterleave(String s1, String s2, String s3) {
		HashMap<String, Boolean> dp = new HashMap<>();
        return recurInterleave(s1, 0, s2, 0, s3, 0, dp);
    }
	
	private static boolean recurInterleave(String s1, int i, String s2, int j, String s3, int k, HashMap<String, Boolean> dp) {
		if(k==s3.length() && i<s1.length()) {
			return false;
		}
		if(k==s3.length() && j<s2.length()) {
			return false;
		}
		if(k==s3.length() && (i+j)==k) {
			return true;
		}
		String key = new StringBuilder().append((char)i+48).append('_').append((char)j+48).append('_').append((char)k+48).toString();
		if(dp.containsKey(key)) {
			return dp.get(key);
		}
		if(i<s1.length() && j<s2.length() && k<s3.length()) {
			if(s1.charAt(i)==s3.charAt(k) && s2.charAt(j)!=s3.charAt(k)) {
				dp.put(key, recurInterleave(s1, i+1, s2, j, s3, k+1, dp));
				return dp.get(key);
			} else if(s1.charAt(i)!=s3.charAt(k) && s2.charAt(j)==s3.charAt(k)) {
				dp.put(key, recurInterleave(s1, i, s2, j+1, s3, k+1, dp));
				return dp.get(key);
			} else if(s1.charAt(i)==s3.charAt(k) && s2.charAt(j)==s3.charAt(k)) {
				dp.put(key, recurInterleave(s1, i+1, s2, j, s3, k+1, dp) || recurInterleave(s1, i, s2, j+1, s3, k+1, dp));
				return dp.get(key);
			}
		}
		if(i<s1.length() && s1.charAt(i)==s3.charAt(k)) {
			dp.put(key, recurInterleave(s1, i+1, s2, j, s3, k+1, dp));
			return dp.get(key);
		}
		if(j<s2.length() && s2.charAt(j)==s3.charAt(k)) {
			dp.put(key, recurInterleave(s1, i, s2, j+1, s3, k+1, dp));
			return dp.get(key);
		}
		dp.put(key, false);
		return false;
	}

	public static int longestCommonSubsequence(String text1, String text2) {
		int[][] dp=new int[text1.length()][text2.length()];
		for(int[] rows:dp) {
			Arrays.fill(rows, -1);
		}
        return lcsRecur(text1, text2, text1.length()-1, text2.length()-1, dp);
    }
	
	private static int lcsRecur(String s1, String s2, int i, int j, int[][] dp) {
		if(i==-1 || j==-1) {
			return 0;
		}
		if(dp[i][j]!=-1) {
			return dp[i][j];
		}
		if(s1.charAt(i)==s2.charAt(j)) {
			dp[i][j]=1+lcsRecur(s1, s2, i-1, j-1, dp);
			return dp[i][j];
		}
		dp[i][j]=Math.max(lcsRecur(s1, s2, i, j-1, dp), lcsRecur(s1, s2, i-1, j, dp));
		return dp[i][j];
	}

	public static boolean subsetSumToK(int n, int k, int arr[]){
		int[][] dp=new int[k+1][arr.length+1];
		for(int[] rows:dp) {
			Arrays.fill(rows, -1);
		}
		return subsetSumRec(arr, k, 0, dp)==1;
    }
	
	private static int subsetSumRec(int[] arr, int x, int i, int[][] dp) {
		if(i==arr.length) {
			dp[x][i]=0;
			return dp[x][i];
		}
		if(dp[x][i]!=-1) {
			return dp[x][i];
		}
		if(arr[i]==x) {
			dp[x][i]=1;
			return dp[x][i];
		}
		if(arr[i]>x) {
			dp[x][i]=subsetSumRec(arr, x, i+1, dp);
			return dp[x][i];
		}
		dp[x][i]=subsetSumRec(arr, x-arr[i], i+1, dp);
		if(dp[x][i]==1) {
			return dp[x][i];
		}
		dp[x][i]=subsetSumRec(arr, x, i+1, dp);
		return dp[x][i];
	}
	
	//edit distance
	public static int minDistance(String word1, String word2) {
		int[][] dp=new int[word1.length()+1][word2.length()+1];
		for(int[] rows:dp) {
			Arrays.fill(rows, -1);
		}
        return minDisRecur(word1, word2, 0, 0, dp);
    }

    public static int minDisRecur(String word1, String word2, int i, int j, int[][] dp) {
    	if(i==word1.length() && j==word2.length()) {
    		return 0;
    	}
    	if(dp[i][j]!=-1) {
    		return dp[i][j];
    	}
    	if(i==word1.length() && j<word2.length()) {
    		dp[i][j]=word2.length()-j;
    		return word2.length()-j;
    	}
    	if(i<word1.length() && j==word2.length()) {
    		dp[i][j]=word1.length()-i;
    		return word1.length()-i;
    	}
    	if(word1.charAt(i)==word2.charAt(j)) {
    		dp[i][j]=minDisRecur(word1, word2, i+1, j+1, dp);
    		return dp[i][j];
    	}
    	int ins=1+minDisRecur(word1, word2, i, j+1, dp);
    	int del=1+minDisRecur(word1, word2, i+1, j, dp);
    	int rep=1+minDisRecur(word1, word2, i+1, j+1, dp);
    	dp[i][j]=Math.min(ins, Math.min(del, rep));
    	return dp[i][j];
    }
}
