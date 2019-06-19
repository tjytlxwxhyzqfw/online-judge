/**
 * 147 - Insertion Sort List 
 *
 * 题意: 链表插入排序
 *
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public ListNode insertionSortList(ListNode first) {
		ListNode head = new ListNode(0);
		head.next = first;

		ListNode sorted = new ListNode(0);
		while (head.next != null) {
			ListNode current = removeFirst(head);
			System.out.printf("current: %d\n", current.val);
			insert(current, sorted);
			print(sorted);
		}

		return sorted.next;
	}

	private ListNode removeFirst(ListNode head) {
		if (head.next == null)
			return null;
		ListNode first = head.next;
		head.next = first.next;
		first.next = null;
		return first;
	}

	private void insert(ListNode current, ListNode head) {
		ListNode pre = head;
		for (; pre.next != null && pre.next.val < current.val; pre = pre.next);
		ListNode next = pre.next;
		pre.next = current;
		current.next = next;
	}

	private void print(ListNode head) {
		for (ListNode c=head.next; c!=null; c=c.next)
			System.out.printf("%d, ", c.val);
		System.out.println();
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

