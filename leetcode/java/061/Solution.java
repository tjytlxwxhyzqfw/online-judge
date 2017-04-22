import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class Solution {

	public ListNode rotateRight(ListNode first, int k) {
		ListNode head = new ListNode(0);
		head.next = first;

		int n = 0;
		ListNode current = head;
		while (current.next != null) {
			current = current.next;
			++n;
		}
		if (n == 0)
			return first;
		k %= n;

		ListNode x = head;
		for (int i = 0; i < k; ++i)
			x = x.next;

		ListNode y = head;
		while (x.next != null) {
			x = x.next;
			y = y.next;
		}

		x.next = first;
		first = y.next;
		y.next = null;

		return first;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		ListNode head = new ListNode(0);

		ListNode current = head;
		for (int i = 1; i < 10; ++i) {
			current.next = new ListNode(i);
			current = current.next;
		}
		current.next = null;

		Solution solution = new Solution();
		head.next = solution.rotateRight(head.next, 0);

		current = head.next;
		while (current != null) {
			System.out.printf("%d ", current.val);
			current = current.next;
		}
		System.out.printf("\n");
	}
}
