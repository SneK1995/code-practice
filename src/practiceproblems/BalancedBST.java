package practiceproblems;

import java.util.List;

public class BalancedBST {

	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode() {}
		TreeNode(int val) { this.val=val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val=val;
			this.left=left;
			this.right=right;
		}
	}
	
	public static void main(String[] args) {
		BalancedBST start = new BalancedBST();
		TreeNode root = start.sortedArrayToBST(new int[] {1,2,3,4,5});
		start.inorder(root);
	}
	
	private TreeNode sortedArrayToBST(int[] nums) {
		return makeNode(nums, 0, nums.length-1);
    }
	
	private TreeNode makeNode(int[] nums, int start, int end) {
		TreeNode node = this.new TreeNode();
		if(start==end) {
			node.val=nums[start];
			return node;
		}
		int mid=(start+end)/2;
		node.val=nums[mid];
		if(start<=mid-1) {
			node.left=makeNode(nums, start, mid-1);
		}
		if(mid+1<=end) {
			node.right=makeNode(nums, mid+1, end);
		}
		return node;
	}
	
	private void inorder(TreeNode node) {
		if(node==null) {
			return;
		}
		inorder(node.left);
		System.out.println(node.val);
		inorder(node.right);
	}

	public static int maxpath;
	public int maxPathSum(TreeNode root) {
		maxpath=Integer.MIN_VALUE;
		maxPathNode(root);
		return maxpath;
    }
	public int maxPathNode(TreeNode node) {
		if(node==null) {
			return 0;
		}
		int lmaxpath=maxPathNode(node.left);
		int rmaxpath=maxPathNode(node.right);
		int sum=node.val+lmaxpath+rmaxpath;
		int maxpathsum=Math.max(node.val, Math.max(lmaxpath, rmaxpath)+node.val);
		maxpath=Math.max(maxpath, Math.max(sum, maxpathsum));
		return maxpathsum;
	}
}
