package practiceproblems.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BinarySearchAnsPattern {
	
	public static void main(String[] args) {
//		System.out.println(testcalculate(4, 7, new int[] {2,2,3,3,4,4,1}));
//		System.out.println(findLargestMinDistance(List.of(2, 1, 5, 6, 2, 3), 2));
//		System.out.println(aggressiveCows(new int[] {0,3,4,7,10,9}, 4));
//		System.out.println(median(new int[] {2,4,6}, new int[] {1,3}));
//		System.out.println(median(new int[] {2,4,6}, new int[] {1,3,5}));
//		System.out.println(median(new int[] {1,1,5,11,12,12,14,14,16,17}, new int[] {11,15,20,20,20,20}));
	}
	
//	public static double median(int[] a, int[] b) {
//		int alow=0;
//    	int ahigh=a.length-1;
//    	int blow=0;
//    	int bhigh=b.length-1;
//    	int amid=(alow+ahigh)/2;
//    	int bmid=(blow+bhigh)/2;
//    	int d=Math.abs(a[amid]-b[bmid]);
//    	int amidprev=-1;
//    	int bmidprev=-1;
//    	int dprev=Integer.MAX_VALUE;
//    	while(d<dprev) {
//    		dprev=d;
//    		amidprev=amid;
//    		bmidprev=bmid;
//    		if(a[amid]<=b[bmid]) {
//    			alow=amid+1;
//    			bhigh=bmid-1;
//    		} else {
//    			ahigh=amid-1;
//    			blow=bmid+1;
//    		}
//    		amid=(alow+ahigh)/2;
//    		bmid=(blow+bhigh)/2;
//    		d=Math.abs(a[amid]-b[bmid]);
//    	}
////    	while(alow<ahigh) {
////    		int amid=(alow+ahigh)/2;
////    		if(a[amid]<=b[blow]) {
////    			alow=amid+1;
////    		} else {
////    			ahigh=amid-1;
////    		}
////    	}
////    	while(blow<bhigh) {
////    		int bmid=(blow+bhigh)/2;
////    		if(b[bmid]<=a[alow]) {
////    			blow=bmid+1;
////    		} else {
////    			bhigh=bmid-1;
////    		}
////    	}
//    	List<Integer> alist = Arrays.stream(a).boxed().collect(Collectors.toList());
//    	alist.addAll(Arrays.stream(b).boxed().collect(Collectors.toList()));
//    	List<Integer> total = alist.stream().sorted().collect(Collectors.toList());
//    	System.out.println("total: " + total);
//    	
//    	System.out.println("diff prev: " + dprev);
//    	System.out.println(String.format("a[%d]: %d, b[%d]: %d", amidprev, a[amidprev], bmidprev, b[bmidprev]));
//    	int tl=(a.length+b.length);
//    	if(tl%2!=0) {
//    		return Math.max(a[amidprev], b[bmidprev]);
//    	}
//    	return (a[amidprev]+b[bmidprev])/2.0;
//    }
	
	public static long testcalculate(int n, int m, int[] time) {
		int maxans = Arrays.stream(time).sum();
		int minans = Arrays.stream(time).max().getAsInt();
		int ans = maxans;
		while(maxans >= minans) {
			int mid = (maxans+minans)/2;
			if(isPossible(mid, n, time)) {
				ans=mid;
				maxans=mid-1;
			} else {
				minans=mid+1;
			}
		}
		return ans;
    }
	
	private static boolean isPossible(int t, int n, int[] time) {
		int sumT = 0;
		int ndays = 1;
		int i=0;
		while(i<time.length && ndays<=n) {
			sumT+=time[i];
			if(sumT>t) {
				ndays++;
				sumT=0;
			} else {
				i++;
			}
		}
		return ndays<=n;
	}

	public static int findLargestMinDistance(List<Integer> boards, int k) {
		int maxans = boards.stream().mapToInt(Integer::intValue).sum();
		int minans = boards.stream().mapToInt(Integer::intValue).max().getAsInt();
		int ans = minans;
		while(maxans>=minans) {
			int mid=(maxans+minans)/2;
			if(isPaintPossible(mid, boards, k)) {
				ans = mid;
				maxans = mid-1;
			} else {
				minans = mid+1;
			}
		}
		return ans;
    }
	
	private static boolean isPaintPossible(int maxtime, List<Integer> boards, int maxpainters) {
		int total=0;
		int p=1;
		for(int t:boards) {
			total+=t;
			if(total > maxtime) {
				p++;
				total=t;
				if(p>maxpainters) {
					return false;
				}
			}
		}
		return true;
	}

	public static int aggressiveCows(int[] stalls, int k) {
		stalls = Arrays.stream(stalls).sorted().toArray();
		int maxans = stalls[stalls.length-1]-stalls[0];
		int minans = stalls[1]-stalls[0];
		int ans = maxans;
		while(maxans>=minans) {
			int mid=(maxans+minans)/2;
			if(isPossibleToSet(mid, stalls, k)) {
				ans=mid;
				minans=mid+1;
			} else {
				maxans=mid-1;
			}
		}
		return ans;
    }
	
	private static boolean isPossibleToSet(int mindist, int[] stalls, int maxcows) {
		int placed=stalls[0];
		int ncows=1;
		for(int i=1;i<stalls.length;i++) {
			int diff=stalls[i]-placed;
			if(diff>=mindist) {
				ncows++;
				placed=stalls[i];
				if(ncows==maxcows) {
					return true;
				}
			}
		}
		Arrays.binarySearch(stalls, 10);
		return false;
	}

	public static int largestSubarraySumMinimized(int[] a, int k) {
		int maxans=Arrays.stream(a).sum();
		int minans=Arrays.stream(a).min().getAsInt();
		int ans=maxans;
		while(maxans>=minans) {
			int mid=(maxans+minans)/2;
			if(isLargestSumPos(mid,a,k)) {
				ans=mid;
				maxans=mid-1;
			} else {
				minans=mid+1;
			}
		}
		return ans;
    }
	
	private static boolean isLargestSumPos(int maxsumpos, int[] a, int k) {
		int sum=0;
		int part=1;
		int i=0;
		while(i<a.length) {
			sum+=a[i];
			if(sum>maxsumpos) {
				sum=0;
				part++;
				if(part>k) {
					return false;
				}
			} else {
				i++;
			}
		}
		return true;
	}
}
