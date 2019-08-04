/**
 * 863 All Nodes Distance K in Binary Tree
 * Performance: speed=100%, memory=95%
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {
	public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
		List<Integer> list = new ArrayList<>();
		dfs(root, target, k, list);
		return list;
	}

	int dfs(TreeNode root, TreeNode target, int k, List<Integer> nodes) {
		if (root == null) return -1;

		if (root.val == target.val) {
			getDistK(root, k, nodes);
			return 0;
		}

		TreeNode another = null;
		int d = -1;
		if ((d = dfs(root.left, target, k, nodes)) != -1) {
			another = root.right;
		} else if ((d = dfs(root.right, target, k, nodes)) != -1) {
			another = root.left;
		} else return -1;

		++d;  // fool: forget to ++d
		if (d == k) nodes.add(root.val);
		else if (d < k) getDistK(another, k-d-1, nodes); // fool: it's k-d-1 ! not d-k-1

		return d;
	}

	void getDistK(TreeNode root, int k, List<Integer> list) {
		if (root == null) return;

		int d = 0;
		Queue<TreeNode> q = new LinkedList<>();
		q.offer(root); q.offer(null);
		while (!q.isEmpty()) {
			TreeNode v = q.poll();
			if (v == null){ ++d; if (!q.isEmpty()) q.offer(null); continue; }
			if (d == k) list.add(v.val);
			else {
				if (v.left != null) q.offer(v.left);
				if (v.right != null) q.offer(v.right);
			}
		}
	}

	public static void main(String args[]) {
	}
}

