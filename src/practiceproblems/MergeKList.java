package practiceproblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

public class MergeKList {

	private static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
	
	public static ListNode mergeKLists(ListNode[] lists) {
        final ListNode root=new ListNode();
        ListNode cur=root;
        final int[] indArr=new int[lists.length];
        
        return null;
    }
	
	//find intersection of two linked list
	public static ListNode findIntersection(ListNode firstHead, ListNode secondHead) {
		HashMap<Integer, Boolean> visitedA = new HashMap<>();
		HashMap<Integer, Boolean> visitedB = new HashMap<>();
		while(firstHead != null && secondHead != null) {
			if(firstHead == secondHead) {
				return firstHead;
			}
			if(visitedB.get(firstHead.hashCode()) != null) {
				return firstHead;
			}
			if(visitedA.get(secondHead.hashCode()) != null) {
				return secondHead;
			}
			visitedA.put(firstHead.hashCode(), true);
			visitedB.put(secondHead.hashCode(), true);
			firstHead = firstHead.next;
			secondHead = secondHead.next;
		}
		while(firstHead != null) {
			if(visitedB.get(firstHead.hashCode()) != null) {
				return firstHead;
			}
			visitedA.put(firstHead.hashCode(), true);
			firstHead = firstHead.next;
		}
		while(secondHead != null) {
			if(visitedA.get(secondHead.hashCode()) != null) {
				return secondHead;
			}
			visitedB.put(secondHead.hashCode(), true);
			secondHead = secondHead.next;
		}
		return null;
    }

	//delete node without head
	public static void deleteNode(ListNode node) {
        ListNode next=node.next;
        node.val=next.val;
        node.next=next.next;
        next.next=null;
    }

	//delete nodes with val present in nums array O(n+m)
	public static ListNode modifiedList(int[] nums, ListNode head) {
        HashSet<Integer> numSet=new HashSet<>();
        for(int i:nums) {
        	numSet.add(i);
        }
        ListNode cur=head;
        ListNode prev=null;
        while(cur!=null) {
        	boolean isDeleted=false;
        	ListNode node=cur;
        	if(cur==head && numSet.contains(cur.val)) {
        		head=cur.next;
        		isDeleted=true;
        	} else if(numSet.contains(cur.val)) {
        		prev.next=cur.next;
        		isDeleted=true;
        	} else {
        		prev=cur;
        	}
        	cur=cur.next;
        	if(isDeleted) {
        		node.next=null;
        	}
        }
        return head;
    }
	
}
