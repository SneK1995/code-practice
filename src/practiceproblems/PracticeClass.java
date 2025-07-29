package practiceproblems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PracticeClass {
	
	public static void main(String[] args) {
//		System.out.println(longestPalindrome("babad"));
//		System.out.println(longestPalindrome("cbbd"));
//		System.out.println(findLongPalindrome("cbbd"));
//		System.out.println(findLongPalindromeTwo("ac"));
//		System.out.println(checkSubstrPalindrome("ac".toCharArray(), 0, 2));
//		System.out.println(removeDuplicates(new int[] {0,0,1,1,1,1,2,3,3}));
//		rotate(new int[] {1,2,3,4,5,6,7}, 1);
//		System.out.println(reverse(" hello this      reverse"));
//		System.out.println(calculateExecutionTime(() -> {
//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}));
		System.out.println(isAnagram("abbcc", "bbaac"));
//		System.out.println(pairs(2, List.of(1,5,3,4,2)));
	}
	
	private static String findLongPalindrome(String s) {
		if(s.length()==1) {
			return s;
		}
		final char[] arr=s.toCharArray();
		if(checkSubstrPalindrome(arr, 0, arr.length)) {
			return s;
		}
		String str1=findLongPalindrome(s.substring(0, arr.length-1));
		String str2=findLongPalindrome(s.substring(1, arr.length));
		if(str1.length()==str2.length() || str1.length()>str2.length()) {
			return str1;
		}
		return str2;
	}
	
	private static String findLongPalindromeTwo(String s) {
		if(s.length()==1) {
			return s;
		}
		final char[] arr=s.toCharArray();
		int len=arr.length;
		while(len>=1) {
			int i=0;
			while((i+len-1)<arr.length) {
				System.out.println("cur : (i,len): (" + i + "," + len + ")");
				System.out.println(s.substring(i, i+len));
				if(checkSubstrPalindrome(arr, i, i+len)) {
					return s.substring(i, i+len);
				}
				i++;
			}
			len--;
		}
		return s;
	}
	
	private static String longestPalindrome(String s) {
		if(s.length()==1) {
			return s;
		}
		final char[] arr = s.toCharArray();
		int start=0,end=0,len=0;
		for(int i=0;i<arr.length;i++) {
			for(int j=arr.length; j>i; j--) {
				if(checkSubstrPalindrome(arr, i, j) && len<(j-i)) {
					len=j-i;
					start=i;
					end=j;
					break;
				}
			}
			if(arr.length-i==len) {
				break;
			}
		}
		if(start==0 && end==0) {
			return s.substring(0, 1);
		}
		return s.substring(start, end);
    }
	
	/**
	 * @param arr Character Array
	 * @param beg Inclusive index
	 * @param end Exclusive index
	 * @return true if palindrome, false for all other cases
	 */
	private static boolean checkSubstrPalindrome(final char[] arr, int beg, int end) {
		int n=end-beg;
		if(n<1) {
			return false;
		}
		if(n==1) {
			return true;
		}
		int i=beg,j=end-1;
		while(i<j) {
			if(arr[i++]!=arr[j--]) {
				return false;
			}
		}
		return true;
	}
	
	private static int removeDuplicates(final int[] nums) {
		int k=0,f=1;
		for(int i=0;i<nums.length-1;i++) {
			if(f<=2) {
				if(nums[i]!=nums[k]) {
					nums[k]=nums[i];
				}
				k++;
			}
			if(nums[i]==nums[i+1]) {
				f++;
			} else {
				f=1;
			}
		}
		if(f<=2) {
			if(nums[k]!=nums[nums.length-1]) {
				nums[k]=nums[nums.length-1];
			}
			k++;
		}
		return k;
	}
	
	private static void rotate(int[] nums, int k) {
		if(k>=nums.length) {
			k=k%nums.length;
		}
        if(k==0) {
        	return;
        }
        int[] queue = new int[k];
        int front=0,rear=0,i=nums.length-1;
        while(i>nums.length-1-k) {
        	queue[rear++]=nums[i--];
        }
        for(int m=0;m<rear;m++) {
        	System.out.print(queue[m] + " ");
        }
        System.out.println();
        int j=nums.length-1;
        while(i>=0) {
        	nums[j--]=nums[i--];
        }
        for(int m=0;m<nums.length;m++) {
        	System.out.print(nums[m] + " ");
        }
        System.out.println();
        while(front<rear) {
        	nums[j--]=queue[front++];
        }
        for(int m=0;m<nums.length;m++) {
        	System.out.print(nums[m] + " ");
        }
        System.out.println();
    }
	
	private static String reverse(String input) {
		String[] inpArr = input.trim().split(" ");
		StringBuilder reverse = new StringBuilder("");
		for(int i=inpArr.length-1;i>-1;i--) {
			if(inpArr[i].length()>0) {
				reverse.append(inpArr[i]).append(" ");
			}
		}
		return reverse.toString().trim();
		
//		return Arrays.stream(input.trim().split(" "))
//			.filter(str -> str.length()>0)
//			.reduce("", (prev, cur) -> {
//				return cur + " " + prev;
//			}).trim();
	}
	
	private static long calculateExecutionTime(ExecuteFI function) {
		long start = System.currentTimeMillis();
		function.execute();
		long end = System.currentTimeMillis();
		return end-start;
	}
	
	private static boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) {
            return false;
        }
        int[] freq=new int[26];
        int startIndex=97;
        for(int i=0;i<s.length();i++) {
            int index=(int)s.charAt(i)-startIndex;
            freq[index]++;
        }
        for(int i=0;i<t.length();i++) {
        	int index=(int)t.charAt(i)-startIndex;
        	if(freq[index]==0) {
        		return false;
        	}
        	freq[index]--;
        }
        for(int i=0;i<s.length();i++) {
        	int index=(int)s.charAt(i)-startIndex;
        	if(freq[index]>0) {
        		return false;
        	}
        }
        return true;
    }
	
	public static int pairs(int k, List<Integer> arr) {
        int count = 0;
        Map<Integer,Boolean> diffMap = arr.stream()
            .collect(Collectors.toMap(a -> a, a -> Boolean.FALSE));
        System.out.println(diffMap);
        for(Integer i:arr) {
            int a = i.intValue()-k;
            int b = i.intValue()+k;
            System.out.println("For i: " + i);
            System.out.println(String.format("a:%d , map.a:%s, a>0:%s", a, diffMap.get(a), (a>0)));
            System.out.println(String.format("b:%d , map.b:%s", b, diffMap.get(b)));
            if(a>0 && diffMap.containsKey(a) && diffMap.get(a).booleanValue()) {
                count++;
            }
            if(diffMap.containsKey(b) && diffMap.get(b).booleanValue()) {
                count++;
            }
            diffMap.put(i, Boolean.TRUE);
        }
        return count;
    }
	
	public static ArrayList<Integer> getMagicNumbers(int n) {
		ArrayList<Integer> l=new ArrayList<>();
		ArrayList<Integer> digits=new ArrayList<>();
		int r=n;
		while(r>0) {
			digits.add(r%10);
			r/=10;
		}
		
		return l;
	}
	
	private static void fillNumbers(int n, int d, ArrayList<Integer> l) {
		
	}
}
