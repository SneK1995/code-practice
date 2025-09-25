package practiceproblems.greedy;

import java.util.ArrayDeque;
import java.util.Arrays;

public class GreedyPractice {
	public static void main(String[] args) {
		minMovesToCaptureTheQueen(6,7,5,6,7,4);
	}
	public int findContentChildren(int[] g, int[] s) {
		Arrays.sort(g);
		Arrays.sort(s);
		int i=0,j=0,c=0;
		while(i<g.length && j<s.length) {
			if(s[j]>=g[i]) {
				i++;
				j++;
				c++;
			} else {
				j++;
			}
		}
		return c;
    }
	
	//Capture queen with rook and bishop
	public static int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
		int rs=countForRook(a, b, c, d, e, f);
		int bs=countForBishop(a, b, c, d, e, f);
		System.out.println("rs: "+rs+", bs: "+bs);
        return Math.min(rs, bs);
    }
	private static int countForRook(int a, int b, int c, int d, int e, int f) {
		if(a!=e && b!=f) {
			return 2;
		}
		if(a==e && a==c && b!=f && ((b<d && d<f) || (b>d && d>f))) {
			//Bishop is present in the path
			return 2;
		}
		if(a!=e && b==d && b==f && ((a<c && c<e) || (a>c && c>e))) {
			//Bishop is present in the path
			return 2;
		}
		return 1;
	}
	private static int countForBishop(int a, int b, int c, int d, int e, int f) {
		int qsum=e+f;
		int bsum=c+d;
		if((qsum%2==0 && bsum%2!=0) || (qsum%2!=0 && bsum%2==0)) {
			//Queen not reachable
			return Integer.MAX_VALUE;
		}
		int[] qdiff=new int[] {e-c,f-d};
		int[] rdiff=new int[] {a-c,b-d};
		System.out.println(String.format("q:[%d,%d]", qdiff[0],qdiff[1]));
		System.out.println(String.format("r:[%d,%d]", rdiff[0],rdiff[1]));
		if(Math.abs(qdiff[0])==Math.abs(qdiff[1])) {
			System.out.println("res: "+ (qdiff[0]>0 && qdiff[1]<0 && (qdiff[0]>rdiff[0] && rdiff[0]>0)));
			if(Math.abs(rdiff[0])==Math.abs(rdiff[1]) 
				&& ((qdiff[0]<0 && qdiff[1]<0 && (qdiff[0]<rdiff[0] && rdiff[0]<0 && qdiff[1]<rdiff[1] && rdiff[0]<0))
				|| (qdiff[0]<0 && qdiff[1]>0 && (qdiff[0]<rdiff[0] && rdiff[0]<0 && qdiff[1]>rdiff[1] && rdiff[1]>0))
				|| (qdiff[0]>0 && qdiff[1]<0 && (qdiff[0]>rdiff[0] && rdiff[0]>0 && qdiff[1]<rdiff[1] && rdiff[1]<0))
				|| (qdiff[0]>0 && qdiff[1]>0 && (qdiff[0]>rdiff[0] && rdiff[0]>0 && qdiff[1]>rdiff[1] && rdiff[0]>0)))) {
				//Rook in the path
				return 2;
			}
			return 1;
		}
		return 2;
	}

	public boolean checkValidString(String s) {
        ArrayDeque<Character> stack=new ArrayDeque<>();
        int a=0;
        for(char c:s.toCharArray()) {
        	if(c=='(' || c=='*') {
        		stack.push(c);
        	
        	} else {
        		if(!stack.isEmpty() && stack.peek()=='(' || stack.peek()=='*') {
        			stack.pop();
        		}
        	}
        }
        return stack.isEmpty() || a>0;
    }
}
