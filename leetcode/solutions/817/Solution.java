/**
 * 817 Linked List Components 55.5% Medium 255/649
 * Performance: speed=82%, memory=92%
 */

import java.util.*;

public class Solution {
	public int numComponents(ListNode head, int[] g) {
		if (g == null || g.length == 0) return 0;

		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < g.length; ++i) set.add(g[i]);
		int n = 0;
		boolean c = false;
		ListNode p = head;
		while (p != null) {
			if (set.contains(p.val)) {
				if (c == false) ++n;
				c = true;
			} else {
				c = false;
			}
			p = p.next;
		}
		return n;
		
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

