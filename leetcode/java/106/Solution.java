/**
 * 106 - Construct Binary Tree from Inorder and Postorder Traversal
 *
 * =105
 */

public class Solution {
	private int inorder[], postorder[];

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		this.inorder = inorder;
		this.postorder = postorder;

		TreeNode root = doBuildTree(0, postorder.length, 0, inorder.length);
		return root;
	}

	private TreeNode doBuildTree(int a, int b, int c, int d) {
		if (a>=b || c>=d)
			return null;

		Integer rootval = postorder[b-1];
		TreeNode root = new TreeNode(rootval);

		Integer rootidx = c;
		for (; rootidx < d; ++rootidx)
			if (inorder[rootidx] == rootval)
				break;

		int leftsize = rootidx-c;
		root.left = doBuildTree(a, a+leftsize, c, rootidx);
		root.right = doBuildTree(a+leftsize, b-1, rootidx+1, d);

		return root;
	}

	public static void main(String args[]) {
	}
}

class TreeNode {
	int val;
	TreeNode left, right;

	public TreeNode(int x) {
		this.val = x;
	}
}
