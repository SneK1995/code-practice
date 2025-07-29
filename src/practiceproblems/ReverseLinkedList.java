package practiceproblems;

import java.util.Stack;

public class ReverseLinkedList {

	private static class Node {
		int i;
		Node next;
	}
	
	private static Node reverseLinkedList(Node root) {
		Stack<Node> stack = new Stack<>();
		Node cur=root;
		while(cur!=null) {
			stack.push(cur);
			cur=cur.next;
		}
		if(stack.isEmpty()) {
			return root;
		}
		root=stack.pop();
		cur=root;
		while(!stack.isEmpty()) {
			cur.next=stack.pop();
			cur=cur.next;
		}
		cur.next=null;
		return root;
	}
	
	private static void printLinkedList(Node root) {
		Node curNode=root;
		while(curNode!=null) {
			System.out.println(curNode.i);
			curNode=curNode.next;
		}
	}
	
	public static void main(String[] args) {
		int n=10;
		Node root=null;
		Node prevNode=null;
		for(int i=0;i<n;i++) {
			Node node=new Node();
			node.i=i;
			if(i==0) {
				root=node;
			}
			if(prevNode!=null) {
				prevNode.next=node;
			}
			prevNode=node;
		}
		System.out.println("Linked list => ");
		printLinkedList(root);
		root=reverseLinkedList(root);
		System.out.println("Reversed linked list => ");
		printLinkedList(root);
		
	}
}
