/**
 * 236 Lowest Common Ancestor of a Binary Tree
 * Performance: speed=100%, memory=5%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		TreeNode[] lca = new TreeNode[1];
		search(root, p, q, lca);
		return lca[0];
	}

	// returns: 0 - none, 1 - only p, 2 - only q, 3 - both
	int search(TreeNode root, TreeNode p, TreeNode q, TreeNode[] lca) {
		if (root == null) return 0;
		int left = search(root.left, p, q, lca);
		if (left == 3) return left;
		int rght = search(root.right, p, q, lca); // fool: search(root.right, q, q, lca)
		if (rght == 3) return rght;

		boolean foundP = (left == 1 || left == 3 || rght == 1 || rght == 3 || root.val == p.val);
		boolean foundQ = (left == 2 || left == 3 || rght == 2 || rght == 3 || root.val == q.val);
		if (foundP && foundQ) { lca[0] = root; return 3; }
		if (foundP) return 1;
		if (foundQ) return 2;
		return 0;
	}

	public static void main(String args[]) {
	}
}

