package practiceproblems;

public class PracticeClassTwo {
	
	public static void main(String[] args) {
//		System.out.println(maxProfit(new int[] {1,9,6,9,1,7,1,1,5,9,9,9}));
//		System.out.println(canJump(new int[] {2,5,0,0}));
		System.out.println(numIslands(new char[][] {
			{'1','1','1'},{'1','1','1'},{'1','1','1'}
		}));
	}

	private static int maxProfit(int[] prices) {
		int profit=0,start=0,end=0;
		for(int i=0;i<prices.length;i++) {
			if(prices[i]>=prices[end]) {
				end=i;
			} else if(prices[i]<prices[end]) {
				profit+=prices[end]-prices[start];
				start=i;
				end=i;
			}
		}
		if(end==prices.length-1) {
			profit+=prices[end]-prices[start];
		}
		return profit;
	}
	
	private static boolean canJump(int[] nums) {
        int jpos=0;
        boolean[] vis=new boolean[nums.length];
        while(jpos<nums.length && !vis[jpos]) {
        	vis[jpos]=true;
        	jpos=jpos+nums[jpos]>nums.length-1?nums.length-1:jpos+nums[jpos];
        }
        return jpos==nums.length-1;
    }
	
	private static int numIslands(char[][] grid) {
		int num=0;
		boolean[] visited=new boolean[grid.length*grid[0].length];
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[i].length;j++) {
				if(grid[i][j]=='1' && !visited[getAbsoluteIndex(grid[0].length, i, j)]) {
					num++;
					traverse(grid, visited, i, j);
				}
			}
		}
        return num;
    }
	
	private static void traverse(char[][] grid, boolean[] visited, int vx, int vy) {
//		System.out.println("traverse(vx,vy) = " + vx + "," + vy);
		int[] queue=new int[visited.length];
		int front=0,rear=0;
		int start=getAbsoluteIndex(grid[0].length, vx, vy);
		queue[rear++]=start;
		visited[start]=true;
		while(front<rear) {
//			System.out.print("queue = [ ");
//			for(int m=0;m<queue.length;m++) System.out.print(queue[m] + " ");
//			System.out.println(" ]");
			int abInd=queue[front++];
//			System.out.println("dequeued = " + abInd);
			int i=getX(grid[0].length, abInd);
			int j=getY(grid[0].length, abInd);
			
			int up=getAbsoluteIndex(grid[0].length, i-1, j);
			if(i-1>=0 && grid[i-1][j]=='1' && !visited[up]) {
				queue[rear++]=up;
				visited[up]=true;
			}
			int down=getAbsoluteIndex(grid[0].length, i+1, j);
			if(i+1<grid.length && grid[i+1][j]=='1' && !visited[down]) {
				queue[rear++]=down;
				visited[down]=true;
			}
			int left=getAbsoluteIndex(grid[0].length, i, j-1);
			if(j-1>=0 && grid[i][j-1]=='1' && !visited[left]) {
				queue[rear++]=left;
				visited[left]=true;
			}
			int right=getAbsoluteIndex(grid[0].length, i, j+1);
			if(j+1<grid[0].length && grid[i][j+1]=='1' && !visited[right]) {
				queue[rear++]=right;
				visited[right]=true;
			}
		}
	}
	
	private static int getAbsoluteIndex(int size, int i, int j) {
		return i*size+j;
	}
	
	private static int getX(int size, int abInd) {
		return abInd/size;
	}
	
	private static int getY(int size, int abInd) {
		return abInd%size;
	}
}