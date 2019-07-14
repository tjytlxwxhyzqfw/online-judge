/**
 * 114 - Flatten Binary Tree to Linked List
 *
 * 把一颗二叉树展平
 *
 */

public class Solution {
	public void flatten(TreeNode root) {
			if (root == null)
				return;
			doFlatten(root);
			root.left = null;
	}

	private TreeNode doFlatten(TreeNode root) {
		if (root==null)
			return null;
		if (root.left==null && root.right==null) {
			root.left = root;
			return root;
		}

		TreeNode left = doFlatten(root.left);
		TreeNode right = doFlatten(root.right);

		TreeNode leftLast=null, rightLast=null;
		if (left != null) {
			leftLast = left.left;
			left.left = null;
		}
		if (right != null) {
			rightLast = right.left;
			right.left = null;
		}

		if (left!=null && right!=null) {
			root.right = left;
			leftLast.right = right;
			root.left = rightLast;
		} else if (left != null) {
			root.right = left;
			root.left = leftLast;
		} else if (right != null) {
			root.right = right;
			root.left = rightLast;
		} else {
			throw new RuntimeException("impossible");
		}

		return root;
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
