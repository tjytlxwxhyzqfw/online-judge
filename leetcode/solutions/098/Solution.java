/**
 * 098 - Validate Binary Search Tree
 *
 * 第一次提交失败: 左右儿子不能和父亲相等
 * 第二次提交失败: 右子树的最小儿子可能比根要大
 * 第三次提交失败: 对Integer.MAX_VALUE, Integer.MIN_VALUE考虑不全面.
 * 	- 在这种情况下, 最简单的方式, 就是用Long, BigDecimal, ...
 *	- 我的方式是用null表示无界
 */

public class Solution {
	public boolean isValidBST(TreeNode root) {
		return valid(root, null, null);
	}

	/* 所有节点的值都在区间(min, max), 并且是一个合法的BST */
	private boolean valid(TreeNode root, Integer min, Integer max) {
		if (root == null)
			return true;

		if ((max!=null && root.val>=max) || (min!=null && root.val<=min))
			return false;
		if (root.left!=null && root.left.val>=root.val)
			return false;
		if (root.right!=null && root.right.val<=root.val)
			return false;

		Integer leftMax = root.val;
		Integer rghtMin = root.val;

		return valid(root.left, min, leftMax) && valid(root.right, rghtMin, max);
	}

	public static void main(String args[]) {
	}
}

class TreeNode {
	int val;
	TreeNode left, right;

	TreeNode(int x) {
		val = x;
	}
}
