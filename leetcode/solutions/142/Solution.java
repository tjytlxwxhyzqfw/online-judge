/**
 * 142 Linked List Circle II
 *
 * 讲真, 我也不知道
 */

public class Solution {
	public ListNode detectCycle(ListNode first) {
		// 错大了!!!!!!!!!!!
		//if (first==null || first.next==null || first.next==first)
			//return first;

		if (first==null || first.next==null)
			return null;
		if (first.next==first)
			return first;

		ListNode fast=first.next.next, slow=first.next;
		while (true) {
			if (fast==slow)
				break;
			if (fast==null || fast.next==null)
				break;
			fast=fast.next.next;
			slow=slow.next;
		}

		if (fast==null || fast.next==null)
			return null;

		fast = first;
		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}

		return fast;
	}

	public static void main(String args[]) {
	}
}

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
		next = null;
	}
}
