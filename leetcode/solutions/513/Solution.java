/**
 * 513: FindBottomLeftTreeValue
 * Performance: speed=70%, memory=82%
 * bfs: S=19, M=100
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {
	public int findBottomLeftValue(TreeNode root) {
		if (root == null) return 0;

		// return dfs(root, new int[1]).val;
		return bfs(root).val;
	}

	public TreeNode dfs(TreeNode root, int[] depth) {
		if (root == null) {
			depth[0] = -1;
			return null;
		}
		if (root.left == null && root.right == null) {
			depth[0] = 0;
			return root;
		}
		int[] leftd = new int[1], rghtd = new int[1];
		TreeNode left = dfs(root.left, leftd);
		TreeNode rght = dfs(root.right, rghtd);
		if (leftd[0] >= rghtd[0]) {
			depth[0] = leftd[0] + 1;
			return left;
		} else {
			depth[0] = rghtd[0] + 1;
			return rght;
		}
	}

	// disscuss: instead of offer(null), you can offer(node, depth)
	private TreeNode bfs(TreeNode root) {
		TreeNode ans = root;
		int maxdepth = 0, depth = 0;
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root);
		q.offer(null);
		while (!q.isEmpty()) {
			TreeNode curr = q.poll();
			if (curr == null) { ++depth; if (!q.isEmpty()) q.offer(null); continue; }
			if (depth > maxdepth) {
				maxdepth = depth;
				ans = curr;
			}
			if (curr.left != null) q.offer(curr.left);
			if (curr.right != null) q.offer(curr.right);
		}
		return ans;
	}

	public static void main(String args[]) {
	}
}

