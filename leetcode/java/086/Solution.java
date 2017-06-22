/**
 * leetcode 86 - Partition List
 */

public class Solution {
	public ListNode partition(ListNode first, int x) {
		ListNode head1 = new ListNode(0);
		ListNode head2 = new ListNode(1);

		head1.next = head2.next = null;

		ListNode last1 = head1, last2 = head2;
		ListNode current = first;
		while (current != null) {
			if (current.val < x) {
				last1.next = current;
				last1 = current;
			} else {
				last2.next = current;
				last2 = current;
			}
			current = current.next;
		}

		last1.next = head2.next;
		last2.next = null;

		return head1.next;
	}
	public static void main(String args[]) {
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
