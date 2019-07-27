/**
 * 337: HouseRobberIII
 * Performance: speed=83%, memory=100%
 *
 * Immutable: use hashCode() and a map to memorize the result, s=31%, m=5%
 * Mutable: use -value to memorize the result
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int x) { val = x; } }

public class Solution {
	public int rob(TreeNode root) {
		return dfs(root);
	}

	private int dfs(TreeNode root) {
		if (root == null) return 0;
		if (root.val < 0) return -root.val;

		int max1 = root.val;
		if (root.left != null) max1 += dfs(root.left.left) + dfs(root.left.right);
		if (root.right != null) max1 += dfs(root.right.left) + dfs(root.right.right);

		int max2 = dfs(root.left) + dfs(root.right);
		root.val = -Math.max(max1, max2);
		return -root.val;
	}

	public static void main(String args[]) {
	}
}

