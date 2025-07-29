package practiceproblems.company;

import java.util.List;

public class CompanyPractice {

	public static void main(String[] args) {
		System.out.println(getSteps("momoz", 'm'));
		System.out.println(getSteps("amabcdmem", 'm'));
	}
	
	public static int getSteps(String s, char c) {
		StringBuilder b=new StringBuilder(s);
		int steps=0;
		while(true) {
			int n=b.length();
			boolean[] remove=new boolean[n];
			boolean isremove=false;
			for(int i=1;i<n;i++) {
				if(b.charAt(i)==c) {
					remove[i-1]=true;
					isremove=true;
				}
			}
			if(!isremove) {
				break;
			}
			StringBuilder newb=new StringBuilder();
			for(int i=0;i<n;i++) {
				if(!remove[i]) {
					newb.append(b.charAt(i));
				}
			}
			b=newb;
			steps++;
		}
		return steps;
	}
	
	public static int getMaxServers(List<Integer> powers) {
		
		return 0;
	}
}
