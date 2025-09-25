package practiceproblems.twopointer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class SlidingWindow {
	
	public static void main(String[] args) {
		//System.out.println(lengthOfLongestNonRepeatSubstring("abcad"));
//		System.out.println(minWindow("ADOBECODEBANC", "ABC"));
//		System.out.println(kDistinctChars(3, "abcddefg"));
//		System.out.println(longestOnes(List.of(0,0,1,1), 1));
//		System.out.println(totalFruit(new int[] {0,1,1,4,3}));
//		System.out.println(numSubarraysWithSum(new int[] {1,0,1,0,1}, 2));
		SlidingWindow ans=new SlidingWindow();
//		System.out.println(ans.numberOfSubarrays(new int[] {2,2,2,1,2,2,1,2,2,2}, 2));
		System.out.println(longestSubarrayWithSumK(new int[] {1, 2, 3, 1, 1, 1, 1},3));
	}

	//time-> O(n)
	public static int lengthOfLongestNonRepeatSubstring(String s) {
        int max=0;
        HashSet<Integer> charset=new HashSet<>();
        int i=-1, j=0;
        while(j<s.length()) {
        	int key=(int)s.charAt(j);
        	if(!charset.contains(key)) {
        		j++;
        		charset.add(key);
        	} else {
        		max=Math.max(max, j-1-i);
        		while(charset.contains(key)) {
        			i++;
            		charset.remove((int)s.charAt(i));
        		}
        	}
        }
        return Math.max(max, j-1-i);
    }
	
	//MaxHeap -> O(nlog(n))3
	public static int[] maxSlidingWindow1(int[] nums, int k) {
		Comparator<Integer> comparator = (ind1, ind2) -> {
			if(nums[ind1]>nums[ind2]) {
				return -1;
			}
			if(nums[ind1]<nums[ind2]) {
				return 1;
			}
			return 0;
		};
		PriorityQueue<Integer> window = new PriorityQueue<>(comparator);
		ArrayList<Integer> maxList = new ArrayList<>();
		int i=0;
		while(i<k) {
			window.add(i);
			i++;
		}
		maxList.add(nums[window.peek()]);
		while(i<nums.length) {
			int e=i;
			int s=i-k+1;
			while(!window.isEmpty() && (window.peek()<s || window.peek()>e)) {
				window.poll();
			}
			window.add(i);
			maxList.add(nums[window.peek()]);
			i++;
		}
		System.out.println(maxList);
        return maxList.stream().mapToInt(Integer::intValue).toArray();
    }
	
	//Deque -> O(n)
	public static int[] maxSlidingWindow(int[] nums, int k) {
		ArrayDeque<Integer> dq=new ArrayDeque<>();
		ArrayList<Integer> mlist=new ArrayList<>();
		for(int i=0;i<nums.length;i++) {
			int s=(i-k+1)>=0 ? (i-k+1):0;
			if(!dq.isEmpty() && dq.peekFirst()<s) {
				dq.pollFirst();
			}
			while(!dq.isEmpty() && nums[dq.peekLast()]<nums[i]) {
				dq.pollLast();
			}
			dq.addLast(i);
			if(i>=k-1) {
				mlist.add(nums[dq.peekFirst()]);
			}
		}
		System.out.println("mlist: " + mlist);
		return mlist.stream().mapToInt(Integer::intValue).toArray();
	}
	
	//Min window substring -> O(s+t)
	public static String minWindow(String s, String t) {
		if(t.length()>s.length()) {
			return "";
		}
		int[][] freq=new int[58][2];
		for(int k=0;k<58;k++) {
			freq[k][0]=-1; freq[k][1]=-1;
		}
		for(char c:t.toCharArray()) {
			if(freq[c-'A'][0]==-1) {
				freq[c-'A'][0]=0;
				freq[c-'A'][1]=0;
			}
			freq[c-'A'][0]++; freq[c-'A'][1]++;
		}
		int i=0;
		int j=0;
		int l=0;
		int st=0;
		int ed=0;
		int minlen=Integer.MAX_VALUE;
		while(j<s.length()) {
			char c=s.charAt(j);
			if(freq[c-'A'][1]!=-1) { 
				freq[c-'A'][0]--;
				if(freq[c-'A'][0]>=0) {
					l++;
				}
			}
			while(l==t.length() || (i<s.length() && freq[s.charAt(i)-'A'][1]==-1)) {
				if(freq[s.charAt(i)-'A'][1]!=-1) {
					if((j-i+1)<minlen) {
						minlen=j-i+1;
						st=i;
						ed=j+1;
					}
					freq[s.charAt(i)-'A'][0]++;
					if(freq[s.charAt(i)-'A'][0]>0) {
						l--;
					}
				}
				i++;
			}
			j++;
		}
		return (st==ed) ? "" : s.substring(st, ed);
    }
	
	//Sliding window -> O(n)
	public static int kDistinctChars(int k, String str) {
		int max=Integer.MIN_VALUE;
		int[] f=new int[26];
		char[] s=str.toCharArray();
		int n=s.length;
		int i=0,j=0;
		int dc=0;
		while(j<n) {
			if(f[s[j]-'a']==0) {
				dc++;
			}
			f[s[j]-'a']++;
			while(i<n && dc>k) {
				f[s[i]-'a']--;
				if(f[s[i]-'a']==0) {
					dc--;
				}
				i++;
			}
			if(dc==k) {
				max=Math.max(max, (j-i+1));
			}
			j++;
		}
		return max==Integer.MIN_VALUE ? n:max;
	}
	
	//same pattern => atmost(k)-atmost(k-1)=exactly(k) => O(n)
	//same as above to find atmost(k) distinct chars/integers
	public int subarraysWithKDistinct(int[] nums, int k) {
        return subarraysAtmostKDistinct(nums, k)-subarraysAtmostKDistinct(nums, k-1);
    }
	public int subarraysAtmostKDistinct(int[] nums, int k) {
        int n=nums.length;
        int i=0,j=0,no=0,di=0;
        int max=Arrays.stream(nums).max().getAsInt();
        int[] freq=new int[max+1];
        while(j<n) {
        	if(freq[nums[j]]==0) {
        		di++;
        	}
        	freq[nums[j]]++;
        	while(di>k) {
        		freq[nums[i]]--;
        		if(freq[nums[i]]==0) {
        			di--;
        		}
        		i++;
        	}
        	no+=(j-i+1);
        	j++;
        }
        return no;
    }
	
	public static int longestOnes(List<Integer> list, int k) {
		int[] nums=list.stream().mapToInt(Integer::intValue).toArray();
        int lmax=Integer.MIN_VALUE;
        int i=0,j=0,ck=k,n=nums.length;
        while(j<n) {
        	if(ck>0 && nums[j]==0) {
        		ck--;
        	} else if(ck==0 && nums[j]==0) {
        		lmax=Math.max(lmax, j-i);
        		while(nums[i]==1) {
        			i++;
        		}
        		i++;
        	}
        	j++;
        }
        return Math.max(lmax, j-i);
    }
	
	public static int totalFruit(int[] fruits) {
        int i=0,j=0,nf=0;
        HashMap<Integer, Integer> basket=new HashMap<>();
        while(j<fruits.length) {
        	basket.put(fruits[j],basket.getOrDefault(fruits[j],0)+1);
        	if(basket.entrySet().size()>2) {
        		nf=Math.max(nf,j-i);
        		while(basket.get(fruits[i])>1) {
        			basket.put(fruits[i],basket.get(fruits[i])-1);
        			i++;
        		}
        		basket.remove(fruits[i]);
        		i++;
        	}
        	j++;
        }
        return Math.max(nf,j-i);
    }
	
	//pattern => atmost(k)-atmost(k-1)=exactly(k) => O(n)
	public static int numSubarraysWithSum(int[] nums, int goal) {
        return numSubArrayAtmostK(nums,goal)-numSubArrayAtmostK(nums,goal-1);
    }
	private static int numSubArrayAtmostK(int[] nums, int k) {
		if(k<0) {
			return 0;
		}
		int n=nums.length;
		int i=0,j=0,s=0,no=0;
		while(j<n) {
			s+=nums[j];
			if(s<=k) {
				no+=(j-i+1);
			} else {
				while(s>k) {
					s-=nums[i];
					i++;
				}
				no+=(j-i+1);
			}
			j++;
		}
		return no;	
	}
	
	//same as above => pattern => atmost(k)-atmost(k-1)=exactly(k) => O(n)
	public int numberOfSubarrays(int[] nums, int k) {
        return numberOfAtmostk(nums,k)-numberOfAtmostk(nums,k-1);
    }
    private int numberOfAtmostk(int [] nums, int k) {
        int n=nums.length;
        int i=0,j=0,s=0,no=0;
        while(j<n) {
            if(nums[j]%2!=0) {
                s++;
            }
            if(s<=k) {
                no+=(j-i+1);
            } else {
                while(s>k) {
                    if(nums[i]%2!=0) {
                    	s--;
                    }
                    i++;
                }
                no+=(j-i+1);
            }
            j++;
        }
        return no;
    }
    
    //same as kDistinctChars -> O(n) (only positive integers)
    public static int longestSubarrayWithSumK(int[] a, long k) {
    	int i=0,j=0,max=0,n=a.length;
    	long s=0;
    	while(j<n) {
    		s+=(long)a[j];
    		while(s>k) {
    			s-=a[i];
    			i++;
    		}
    		if(s==k) {
    			max=Math.max(max,j-i+1);
    		}
    		j++;
    	}
    	return max;
    }
}
