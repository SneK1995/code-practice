package practiceproblems.twopointer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TwoPointer {
	
	public static void main(String[] args) {
		TwoPointer ans=new TwoPointer();
//		System.out.println("sliding: "+ans.maxScore1(new int[] {96,90,41,82,39,74,64,50,30},8));
//		System.out.println(ans.numberOfSubstrings("aaacb"));
//		System.out.println(ans.lengthOfLongestSubstring("bba"));
		System.out.println(ans.characterReplacement("KRSCDCSONAJNHLBMDQGIFCPEKPOHQIHLTDIQGEKLRLCQNBOHNDQGHJPNDQPERNFSSSRDEQLFPCCCARFMDLHADJADAGNNSBNCJQOF", 4));
//		System.out.println(ans.characterReplacement("ABAB", 2));
//		System.out.println(ans.characterReplacement("AABABBA", 1));
	}

	//dp approach -> top down memoization, time => O(n*n*k)
	public int maxScore(int[] cardPoints, int k) {
		int n=cardPoints.length;
		int[][][] dp=new int[n][n][k+1];
		for(int[][] dim:dp) {
			for(int[] row:dim) {
				Arrays.fill(row,-1);
			}
		}
        return maxScoreRecur(cardPoints,0,n-1,k,dp);
    }
	private int maxScoreRecur(int[] cardPoints, int i, int j, int k, int[][][] dp) {
		if(k==0) {
			return 0;
		}
		if(dp[i][j][k]!=-1) {
			return dp[i][j][k];
		}
		dp[i][j][k]=Math.max(cardPoints[i]+maxScoreRecur(cardPoints,i+1,j,k-1,dp),
				cardPoints[j]+maxScoreRecur(cardPoints,i,j-1,k-1,dp));
		return dp[i][j][k];
	}

	//Rolling sliding window through the two endpoints, time => O(k)
	public int maxScore1(int[] cardPoints, int k) {
		int n=cardPoints.length;
		int max=0,wsum=0;
		int i=n-k,j=n-k;
		while(j<n) {
			wsum+=cardPoints[j];
			j++;
		}
		j=-1;
		while(i<n) {
			max=Math.max(max, wsum);
			wsum-=cardPoints[i];
			i++;
			j++;
			wsum+=cardPoints[j];
		}
		return Math.max(max, wsum);
	}
	
	//Sliding window approach -> O(n) time
	public int numberOfSubstrings(String s) {
		int[] count=new int[3];
        int i=0,j=0,ns=0,n=s.length();
        while(j<n) {
        	count[s.charAt(j)-'a']++;
        	while(checkIfPresent(count) && i<(j-1)) {
        		ns+=(n-j);
        		count[s.charAt(i)-'a']--;
        		i++;
        	}
        	j++;
        }
        return ns;
    }
	private boolean checkIfPresent(int[] count) {
		return count[0]>0 && count[1]>0 && count[2]>0;
	}
	
	//longest non repeating substring using sliding window -> O(n)
	public int lengthOfLongestSubstring(String s) {
		int max=0;
		HashSet<Character> charset=new HashSet<>();
		int i=0,j=0,n=s.length();
		while(j<n) {
			char c=s.charAt(j);
            if(charset.contains(c)) {
                max=Math.max(max,j-i);
                while(charset.contains(c)) {
                    charset.remove(s.charAt(i));
                    i++;
                }
            }
            charset.add(c);
			j++;
		}
		return Math.max(max,j-i);
    }
	
	//time => O(26n)~O(n)
	public int characterReplacement(String s, int k) {
		HashSet<Character> charset=new HashSet<>();
		for(char c:s.toCharArray()) {
			charset.add(c);
		}
		int max=0;
		for(char c:charset) {
			int i=0,j=0,m=0,n=s.length();
			while(j<n) {
				if(s.charAt(j)!=c) {
					m++;
				}
				if(m>k) {
					max=Math.max(max,j-i);
					while(s.charAt(i)==c) {
						i++;
					}
					i++;
					m--;
				}
				j++;
			}
			max=Math.max(max,j-i);
		}
		return max;
    }
}
