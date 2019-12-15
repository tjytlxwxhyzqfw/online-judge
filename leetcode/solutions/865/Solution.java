/**
 * 865 Smallest Subtree with all the Deepest Nodes 57.8% Medium 484/145
 * Performance: speed=62%, memory=100%
 */

import java.util.*;

public class Solution {
	public TreeNode subtreeWithAllDeepest(TreeNode root) {
		if (root == null) return null;

		Map<Integer, Integer> height = new HashMap<>();
		heightOf(root, height);
		return dfs(root, height);
	}

	// input para `root` cannot be null
	TreeNode dfs(TreeNode root, Map<Integer, Integer> height) {
		int left = -1, right = -1;
		if (root.left != null) left = height.get(root.left.val);
		if (root.right != null) right = height.get(root.right.val);

		if (left > right) return dfs(root.left, height);
		else if (right > left) return dfs(root.right, height);
		else return root;
	}

	int heightOf(TreeNode root, Map<Integer, Integer> height) {
		if (root == null) return -1;
		int left = heightOf(root.left, height);
		int right = heightOf(root.right, height);
		int h = 1 + Math.max(left, right);
		height.put(root.val, h);
		return h;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		// 0
		TreeNode root = new TreeNode(0);
		assert s.subtreeWithAllDeepest(root) == root;

		//   0
		//  /
		// 1
		root.left = new TreeNode(1);
		assert s.subtreeWithAllDeepest(root) == root.left;

		//     0
		//    / \
		//   1   2
		root.right = new TreeNode(2);
		assert s.subtreeWithAllDeepest(root) == root;

		//      0
		//     / \
		//    1   2
		//   /
		//  3
		root.left.left = new TreeNode(3);
		assert s.subtreeWithAllDeepest(root) == root.left.left;


		//      0
		//     / \
		//    1   2
		//   / \
		//  3   4
		root.left.right = new TreeNode(4);
		assert s.subtreeWithAllDeepest(root) == root.left;

		//       0
		//     /   \
		//    1     2
		//   / \   /
		//  3   4 5
		root.right.left = new TreeNode(5);
		assert s.subtreeWithAllDeepest(root) == root;

		System.out.println("done");
	}
}

