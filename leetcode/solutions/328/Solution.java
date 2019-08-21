/**
 * 328 Odd Even Linked List
 * Performance: speed=100%, memory=100%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public ListNode oddEvenList(ListNode head) {
		if (head == null || head.next == null) return head;
		ListNode evenH = head.next, oddT = head, evenT = head.next;

		boolean odd = true;
		ListNode curr = evenT.next;
		while (curr != null) {
			ListNode tail = (odd ? oddT : evenT);
			System.out.printf("curr=%d, tail=%d\n", curr.val, tail.val);
			tail.next = curr;
			curr = curr.next;
	
			if (odd) oddT = tail.next; else evenT = tail.next;

			odd = !odd;
		}

		oddT.next = evenH;
		evenT.next = null;

		return head;
	}

	public static void main(String args[]) {
	}
}

