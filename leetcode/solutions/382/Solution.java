/**
 * 382 Linked List Random Node
 * Performance: speed=13%, memory=100%
 */

// review logs
// -----------
// Description: there is a stream that you have no idea of it's length.
//   you are supposed to pick an element from that stream randomly and
//   the result should be an uniform distribution (均匀分布).

import java.util.*;

public class Solution {
	ListNode head;
	Random rand;

	/** @param head The linked list's head.
	Note that the head is guaranteed to be not null, so it contains at least one node. */
	public Solution(ListNode head) {
		this.head = head;
		rand = new Random(47);
	}

	/** Returns a random node's value. */
	public int getRandom() {
		ListNode selected = head;

		int n = 2;
		ListNode p = head.next;
		while (p != null) {
			if (rand.nextInt(n) < 1) selected = p;
			p = p.next;
			++n;
		}

		return selected.val;
	}

	public static void main(String args[]) {
	}
}

