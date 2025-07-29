package practiceproblems;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PracticeThree {
	
	public static void main(String[] args) {
//		pairSum(new int[] {1,2,3,4,5}, 5);
//		pairSum(new int[] {2,-3,3,3,-2}, 0);
//		System.out.println(firstNonRepeating("geeksforgeeks"));
		PracticeThree obj=new PracticeThree();
		System.out.println(obj.countGoodNumbers(50));
	}
	
	public static List<int[]> pairSum(int[] arr, int s) {
		Arrays.sort(arr);
		List<int[]> list = new ArrayList<>();
		for(int i=0;i<arr.length;i++) {
			for(int j=i+1;j<arr.length;j++) {
				if(arr[i]+arr[j]==s) {
					list.add(new int[] {arr[i], arr[j]});
				}
			}
		}
		list = list.stream()
			.sorted((a,b) -> {
				if(a[0] == b[0]) {
					return Integer.compare(a[1], b[1]);
				}
				return Integer.compare(a[0], b[0]);
			})
			.collect(Collectors.toList());
		for(int[] a:list) {
			System.out.println("[" + a[0] + "," + a[1] + "]");
		}
		return list;
    }

	public static char firstNonRepeating(String s) {
		LinkedHashMap<Character, Integer> freq = new LinkedHashMap<>();
		ArrayList<?> obj = null;
		for(int i=0;i<s.length();i++) {
			freq.put(s.charAt(i), 0);
		}
		for(int i=0;i<s.length();i++) {
			freq.put(s.charAt(i), freq.get(s.charAt(i))+1);
		}
		char ch = '$';
		for(Map.Entry<Character, Integer> e:freq.entrySet()) {
			if(e.getValue()==1) {
				ch=e.getKey();
				break;
			}
		}
		return ch;
    }
	
	
	//time-> O(n)
	public static String reverseString(String str) {
		String[] arr = str.split(" ");
		String[] rev = new String[arr.length];
		for(int i=arr.length-1, j=0;i>=0;i--) {
			rev[j++] = arr[i];
		}
		return Arrays.stream(rev).collect(Collectors.joining(" "));
	}
	
	public int countGoodNumbers(long n) {
		BigInteger mod=BigInteger.valueOf(1000000007);
		BigInteger base5=BigInteger.valueOf(5l);
		BigInteger base4=BigInteger.valueOf(4l);
		BigInteger epower=BigInteger.valueOf((n%2==0) ? (n/2l):((n/2l)+1l));
		BigInteger opower=BigInteger.valueOf(n/2l);
		return base5.modPow(epower,mod).multiply(base4.modPow(opower,mod)).mod(mod).intValue();
	}
}
