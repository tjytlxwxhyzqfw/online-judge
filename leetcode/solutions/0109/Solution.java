/**
 * 109 - Convert Sorted List to Binary Search Tree
 *
 * 只能把单链表转化为数组之后, 然后解之,
 * 更好的方法是想不出来的.
 */

import java.util.ArrayList;

public class Solution {
	private ArrayList<Integer> vals;
	public TreeNode sortedListToBST(ListNode first) {
		if (first == null)
			return null;

		vals = new ArrayList<>();
		for (ListNode cur = first; cur != null; cur=cur.next)
			vals.add(cur.val);
		TreeNode root = toBST(0, vals.size());
		return root;
	}

	private TreeNode toBST(int bgn, int end) {
		if (bgn>=end)
			return null;

		int rootidx = (bgn+end-1)/2;
		int rootval = vals.get(rootidx);

		TreeNode root = new TreeNode(rootval);
		root.left = toBST(bgn, rootidx);
		root.right = toBST(rootidx+1, end);
		return root;
	}

	public static void main(String args[]) {
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode (int x) {
		val = x;
	}
}

class TreeNode {
	int val;
	TreeNode left, right;

	TreeNode (int x) {
		val = x;
	}
}
