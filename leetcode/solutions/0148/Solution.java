/**
 * 148 - Sort List 
 *
 * 题意: 链表排序
 *
 * 快慢指针截断链表, 然后归并排序
 * 这题, 在实现上, 我没有完全遵守o(1)空间复杂度的约定
 * 但是, 题目本身的递归解也是需要o(logn)栈空间, 也没有遵守约定
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public ListNode sortList(ListNode first) {
		if (first == null)
			return null;
		return mergesort(first);
	}

	private ListNode mergesort(ListNode first) {
		if (first.next == null)
			return first;

		// 控制快慢指针位置的小技巧 
		ListNode slow=first, fast=first.next;
		while (fast!=null && fast.next!=null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		ListNode middle = slow.next;
		slow.next = null;

		ListNode left = new ListNode(0), rght = new ListNode(0);
		left.next = mergesort(middle);
		rght.next = mergesort(first);

		ListNode merged = new ListNode(0);

		ListNode current = merged;
		while (left.next != null && rght.next != null) {
			current.next = (left.next.val < rght.next.val ? removeFirst(left) : removeFirst(rght));
			current = current.next;
		}
		while (left.next != null) {
			current.next = removeFirst(left);
			current = current.next;
		}
		while (rght.next != null) {
			current.next = removeFirst(rght);
			current = current.next;
		}

		return merged.next;
	}

	private ListNode removeFirst(ListNode head) {
		if (head.next == null)
			return null;
		ListNode first = head.next;
		head.next = first.next;
		first.next = null;
		return first;
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

