/**
 * 445 Add Two Numbers II
 * Performance: speed=99%, memory=73%
 * todo: use stack !!!
 */

import java.util.*;

public class Solution {
	public ListNode addTwoNumbers(ListNode x, ListNode y) {
		ListNode i = x, j = y;
		while (i.next != null && j.next != null) { i = i.next; j = j.next; }
		if (i.next == null) {
			{ ListNode t = x; x = y; y = t; }
			{ ListNode t = i; i = j; j = t; }
		}
		// x is longer than y and j.next == null
		j = x;
		while (i.next != null) { j = j.next; i = i.next; }
		// System.out.printf("align: longer=%d\n", j.val);

		ListNode head = new ListNode(0);

		// copy prefix of x into head
		i = x;
		while (i != j) {
			ListNode n = new ListNode(i.val);
			n.next = head.next;
			head.next = n;
			i = i.next;
		}

		// copy sum of x.suffix and y into head
		j = y;
		// System.out.printf("align: longer=%d, shotter=%d\n", i.val, j.val);
		while (j != null) {
			ListNode n = new ListNode(i.val + j.val);
			{ n.next = head.next; head.next = n; }
			{ i = i.next; j = j.next; }
			// System.out.printf("\tnew node: %d\n", n.val);
		}

		// System.out.printf("carry on from rght to left\n");
		// carry on
		i = head;
		while (i != null) {
			// System.out.printf("\tadding: i.val %d -> %d\n", i.val, i.val % 10);
			if (i.val >= 10) {
				if (i.next == null) i.next = new ListNode(0);
				i.next.val += i.val / 10;
				i.val %= 10;
			}
			i = i.next;
		}

		// reverse
		{ i = head.next; head.next = null; }
		while (i != null) {
			// System.out.printf("reversing: i.val=%d\n", i.val);
			ListNode next = i.next;
			i.next = head.next;
			head.next = i;
			i = next;
		}

		return head.next;
	}

	public static void main(String args[]) {
	}
}

