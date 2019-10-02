/**
 * 623 Add One Row to Tree 47.9% Medium 322/110
 * Performance: speed=53%, memory=100%
 */

import java.util.*;

public class Solution {
	public TreeNode addOneRow(TreeNode root, int v, int d) {
		if (d == 1) {
			TreeNode r = new TreeNode(v);
			r.left = root;
			return r;
		}

		Queue<TreeNode> q = new LinkedList<>();
		{ q.offer(root); q.offer(null); }
		int depth = 1;
		while (depth+1 < d) {
			// depth, null, depth+1
			TreeNode tn = q.poll();
			if (tn == null) { if (++depth == d-1) break; q.offer(null); continue; }
			if (tn.left != null) q.offer(tn.left);
			if (tn.right != null) q.offer(tn.right);
		}
		while (!q.isEmpty()) {
			TreeNode p = q.poll();
			if (p == null) continue;
			{ TreeNode left = new TreeNode(v); left.left = p.left; p.left = left; }
			{ TreeNode right = new TreeNode(v); right.right = p.right; p.right = right; }
		}
		return root;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
                System.out.println("done");
	}
}

