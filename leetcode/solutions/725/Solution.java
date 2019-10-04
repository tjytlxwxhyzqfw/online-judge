/**
 * 725 Split Linked List in Parts 49.8% Medium 416/85
 * Performance: speed=100%, memory=100%
 */

import java.util.*;

public class Solution {
	public ListNode[] splitListToParts(ListNode root, int k) {
		ListNode[] result = new ListNode[k];
		if (root == null) return result;
		int n = 0;
		for (ListNode p = root; p != null; p = p.next) ++n;
		int avg = n / k, r = n - k * (n / k);
		for (int i = 0; i < k; ++i) {
			result[i] = root;
			root = firstN(root, avg + (r-- > 0 ? 1 : 0));
		}
		return result;
	}

	ListNode firstN(ListNode node, int n) {
		if (n <= 0) return node;
		ListNode p = node;
		while (p != null && --n > 0) {
			p = p.next;
		}
		ListNode start = null;
		if (p != null) {
			start = p.next;
			p.next = null;
		}
		return start;
	}	

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

