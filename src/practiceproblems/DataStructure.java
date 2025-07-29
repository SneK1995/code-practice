package practiceproblems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DataStructure {

	public static void main(String[] args) {
//		maxSlidingWindow1(new int[] {1,3,1,2,0,5}, 3);
//		maxSlidingWindow1(new int[] {1,3,-1,-3,5,3,6,7}, 3);
//		ninjaAndSortedArrays(new int[]{10,20,30,0,0}, new int[]{1,2}, 3, 2);
//		System.out.println(maximumProduct(new int[] {1,2,3,2}));
		System.out.println(maxSubsetSum(new int[] {2,3,7,1,9}));
	}
	
	public static int[] ninjaAndSortedArrays(int arr1[], int arr2[], int m, int n) {
		int k=m+n-1,i=m-1,j=n-1;
		while(j>=0 && i>=0) {
			if(arr1[i]>=arr2[j]) {
				arr1[k--]=arr1[i--];
			} else if(arr1[i]<arr2[j]) {
				arr1[k--]=arr2[j--];
			}
		}
		while(j>=0) {
			arr1[k--]=arr2[j--];
		}
		for(int a=0;a<arr1.length;a++) System.out.println(arr1[a]);
		return arr1;
    }

	//time -> O(n)
	public static int maximumProduct(int[] nums) {
		int n=nums.length;
		int max1=Math.max(nums[0], Math.max(nums[1], nums[2]));
		int max3=Math.min(nums[0], Math.min(nums[1], nums[2]));
		int max2=(nums[0]+nums[1]+nums[2])-max1-max3;
		int min1=Math.min(nums[0], nums[1]);
		int min2=(nums[0]+nums[1])-min1;
		for(int i=3;i<n;i++) {
			if(nums[i]>max1) {
				max3=max2;
				max2=max1;
				max1=nums[i];
			} else if(nums[i]<=max1 && nums[i]>max2) {
				max3=max2;
				max2=nums[i];
			} else if(nums[i]<=max2 && nums[i]>max3) {
				max3=nums[i];
			}
		}
		for(int i=2;i<n;i++) {
			if(nums[i]<min1) {
				min2=min1;
				min1=nums[i];
			} else if(nums[i]>=min1 && nums[i]<min2) {
				min2=nums[i];
			}
		}
		return Math.max(max1*max2*max3, max1*min1*min2);
    }

	//time-> O(n)
	public static int maxSubsetSum(int[] arr) {
		int max=Arrays.stream(arr).sum();
		if(max<=0) {
			return max;
		}
		int sum=0;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]>0) {
				sum+=arr[i];
			}
		}
		return sum;
	}
}
