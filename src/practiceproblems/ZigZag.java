package practiceproblems;

public class ZigZag {

	public static void main(String[] args) {
		System.out.println(convert("PAYPALISHIRING", 6));
		System.out.println(converttwo("PAYPALISHIRING", 6));
	}
	
	public static String convert(String s, int numRows) {
		if(s.length()==1 || numRows==1) {
			return s;
		}
		final char[] arr=s.toCharArray();
		boolean topdown=true;
		int numCol=1,chgPt=numRows-1;
		for(int i=0;i<arr.length;i+=chgPt,topdown=!topdown) {
			if(!topdown) {
				if((i+chgPt)<arr.length) {
					numCol+=chgPt;
				} else {
					numCol+=(arr.length-i-1);
				}
			}
		}
//		System.out.println("numCol => " + numCol);
		topdown=true;
		final char[][] arrMat=new char[numRows][numCol];
		for(int i=0,j=0,k=0;i<arr.length;i++) {
			if(j==0) {
				topdown=true;
			} else if(j==numRows-1) {
				topdown=false;
			}
//			System.out.println("topdown =>"+topdown+" ("+j+","+k+")="+arr[i]);
			if(topdown) {
				arrMat[j++][k]=arr[i];
			} else {
				arrMat[j--][k++]=arr[i];
			}
		}
		final char[] farr=new char[arr.length];
		int k=0;
//		System.out.println("numRows="+numRows+", numCol="+numCol);
		for(int i=0;i<numRows;i++) {
			for(int j=0;j<numCol;j++) {
//				System.out.print("("+i+","+j+") ");
				if(arrMat[i][j]!=Character.MIN_VALUE) {
					farr[k++]=arrMat[i][j];
				}
			}
//			System.out.println();
		}
        return String.valueOf(farr);
    }
	
	public static String converttwo(String s, int numRows) {
		if(s.length()==1 || numRows==1) {
			return s;
		}
		final char[] arr=s.toCharArray();
		final char[][] maxArr=new char[numRows][s.length()];
		final int[] indArr=new int[numRows];
		boolean topdown=true;
		for(int i=0,j=0;i<arr.length;i++) {
			maxArr[j][indArr[j]++]=arr[i];
			if(topdown) {
				j++;
			} else {
				j--;
			}
			if(j==numRows-1) {
				topdown=false;
			} else if(j==0) {
				topdown=true;
			}
		}
		int k=0;
		final char[] resArr=new char[arr.length];
		for(int i=0;i<maxArr.length;i++) {
			for(int j=0;maxArr[i][j]!=Character.MIN_VALUE;j++) {
				resArr[k++]=maxArr[i][j];
			}
		}
		return String.valueOf(resArr);
	}
}
