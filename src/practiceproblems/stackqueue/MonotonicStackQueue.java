package practiceproblems.stackqueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class MonotonicStackQueue {

	public static void main(String[] args) {
//		System.out.println(findCelebrity1(new int[][] {{0,0},{1,0}}));
//		System.out.println(sumSubarrayMins(new int[] {92,80,9,62,49}));
//		System.out.println(largestRectangleArea(new int[] {2,1,5,6,2,3}));
//		System.out.println(largestRectangleArea(new int[] {2,4}));
//		System.out.println(largestRectangleArea(new int[] {1,2,3,4,5}));
		MonotonicStackQueue obj=new MonotonicStackQueue();
		System.out.println(obj.removeKdigits("112", 1));
		System.out.println(obj.removeKdigits("1432219", 3));
		System.out.println(obj.removeKdigits("100", 1));
	}
	
	//using stack -> O(n)
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		HashMap<Integer, Integer> map=new HashMap<>();
		Stack<Integer> s=new Stack<>();
		s.push(nums2[0]);
		for(int i=1;i<nums2.length;i++) {
			while(!s.isEmpty() && s.peek().intValue()<nums2[i]) {
				map.put(s.pop(), nums2[i]);
			}
			s.push(nums2[i]);
		}
		int[] ans=new int[nums1.length];
		for(int i=0;i<nums1.length;i++) {
			if(map.containsKey(nums1[i])) {
				ans[i]=map.get(nums1[i]);
			} else {
				ans[i]=-1;
			}
		}
        return ans;
    }
	
	//using stack-> O(n)
	public int[] nextGreaterElementsCircular(int[] nums) {
		int n=nums.length;
		int[] ans=new int[n];
		Arrays.fill(ans, -1);
		ArrayDeque<Integer> s=new ArrayDeque<>();
		s.addLast(0);
		for(int i=1;i<2*n;i++) {
			while(!s.isEmpty() && nums[s.peekLast().intValue()]<nums[i%n]) {
				ans[s.pollLast().intValue()]=nums[i%n];
			}
			if(i<n) {
				s.addLast(i);
			}
		}
		return ans;
    }
	
	//Stock Spanner
	ArrayList<Integer> list=new ArrayList<>();
	ArrayList<Integer> ilist=new ArrayList<>();
	public int next(int price) {
        if(list.isEmpty()) {
    		list.add(price);
    		ilist.add(-1);
    		return 1;
    	}
    	int l=1;
    	int n=list.size()-1;
    	while(n>=0 && list.get(n)<=price) {
    		l+=(n-ilist.get(n));
    		n=ilist.get(n);
    	}
    	list.add(price);
    	ilist.add(n);
        return l;
    }
	
	//Celebrity problem using 2-pointer candidate -> O(n)
	public static int findCelebrity(int[][] arr) {
		int n=arr.length;
		int i=0;
		for (int j=1;j<n;j++) {
		    if (arr[i][j]==1) {
		        i=j;
		    }
		}
		for (int j=0;j<n;j++) {
		    if (j!=i && (arr[i][j]==1 || arr[j][i]==0)) {
		        return -1;
		    }
		}
		return i;
    }
	
	//Celebrity problem using stack -> O(n)
	public static int findCelebrity1(int[][] arr) {
		int n=arr.length;
		ArrayDeque<Integer> s=new ArrayDeque<>();
		for(int i=0;i<n;i++) {
			s.push(i);
		}
		while(s.size()>1) {
			int i=s.pop().intValue();
			int j=s.pop().intValue();
			if(arr[i][j]==1) {
				s.push(j);
			} else {
				s.push(i);
			}
		}
		int c=s.pop().intValue();
		for(int i=0;i<n;i++) {
			if(i!=c && (arr[c][i]==1 || arr[i][c]==0)) {
				return -1;
			}
		}
		return c;
	}
	
	//Trust judge -> O(n+e)
	public int findJudge(int n, int[][] trust) {
		if(n==1) {
			return 1;
		}
		int[][] edges=new int[n][2];
		int e=trust.length;
		for(int i=0;i<e;i++) {
			int in=trust[i][1]-1;
			int out=trust[i][0]-1;
			edges[in][0]++;
			edges[out][1]++;
		}
		for(int i=0;i<n;i++) {
			if(edges[i][0]==n-1 && edges[i][1]==0) {
				return i;
			}
		}
		return -1;
	}
	
	//Using Stack -> O(n)
	public static int sumSubarrayMins(int[] arr) {
        int mod=1000000007;
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.push(new int[]{1,arr[0],arr[0]});
        int sum=arr[0]%mod;
        for(int i=1;i<arr.length;i++) {
        	int isum=arr[i]%mod;
        	int j=1;
        	while(!dq.isEmpty() && arr[i]<=dq.peekFirst()[1]) {
        		isum=(isum+(dq.peekFirst()[0]*arr[i]))%mod;
        		j+=dq.peekFirst()[0];
        		dq.pop();
        	}
        	if(!dq.isEmpty()) {
        		isum=(isum+dq.peekFirst()[2])%mod;
        	}
        	dq.push(new int[]{j,arr[i],isum});
        	sum=(sum+isum)%mod;
        }
        return sum;
    }
	
	//Monotonic stack -> O(n)
	public static int[] asteroidCollision(int[] asteroids) {
        ArrayDeque<Integer> dq=new ArrayDeque<>();
        dq.addLast(asteroids[0]);
        for(int i=1;i<asteroids.length;i++) {
        	boolean addI=true;
        	while(!dq.isEmpty() && doCollide(dq.peekLast().intValue(), asteroids[i])) {
        		if(Math.abs(asteroids[i])>dq.peekLast().intValue()) {
        			dq.removeLast();
        		} else if(Math.abs(asteroids[i])==dq.peekLast().intValue()) {
        			dq.removeLast();
        			addI=false;
        			break;
        		} else {
        			addI=false;
        			break;
        		}
        	}
        	if(addI) {
        		dq.addLast(asteroids[i]);
        	}
        }
        return dq.stream().mapToInt(Integer::intValue).toArray();
    }
	
	public static boolean doCollide(int top,int i) {
		return top>0 && i<0;
	}
	
	public static int largestRectangleArea(int[] heights) {
        ArrayDeque<Integer> dq=new ArrayDeque<>();
        int max=heights[0],min=heights[0];
        dq.push(heights[0]);
        for(int i=1;i<heights.length;i++) {
        	
        }
        return max;
	}
	
	//monotonic stack => time complexity -> O(n)
	public String removeKdigits(String num, int k) {
		if(num.length()<=k) {
            return "0";
        }
        ArrayDeque<Character> stack=new ArrayDeque<>();
        stack.addLast(num.charAt(0));
        int deleted=k;
        for(int i=1;i<num.length();i++) {
            int cur=num.charAt(i)-'0';
            while(!stack.isEmpty() && deleted>0 && (stack.peekLast()-'0')>cur) {
                stack.removeLast();
                deleted--;
            }
            stack.addLast(num.charAt(i));
        }
        //removing ending greater elements if k digits are still not removed
        while(!stack.isEmpty() && deleted>0) {
        	stack.removeLast();
        	deleted--;
        }
        //removing starting 0s
        while(!stack.isEmpty() && stack.peekFirst()=='0') {
        	stack.removeFirst();
        }
        if(stack.isEmpty()) {
            return "0";
        }
        StringBuilder res=new StringBuilder();
        while(!stack.isEmpty()) {
            res.append(stack.removeFirst());
        }
        return res.toString();
    }
}
