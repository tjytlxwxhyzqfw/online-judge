/**
 * 105 - Construct Binary Tree from Preorder and Inorder Traversal
 *
 * 还是递归!!还是递归!!!
 * 通过解决一个更小的问题, 来解决整个问题!!!
 * 好的思想!!!!!!
 *
 * 第一个想法是失败的:
 * 在中序遍历时, 找到根以后, 根左边的元素统统属于左子树, 这这时, 需要在西安序遍历数组中将左子树的元素去掉.
 * 怎么做呢? 我的第一个想法是维持一个标记数组. 
 * 想小了, 好的方法是直接统中序结果中根左边元素的个数就好了.
 */

public class Solution {
	private int preorder[];
	private int inorder[];

	public TreeNode buildTree(int preorder[], int inorder[]) {
		this.preorder = preorder;
		this.inorder = inorder;

		TreeNode root = doBuildTree(0, preorder.length, 0, inorder.length);
		return root;
	}

	/* 给定先序结果preorder[a:b], 中序结果inorder[c:d]来还原二叉树. */
	private TreeNode doBuildTree(int a, int b, int c, int d) {
		if (a>=b || c>=d)
			return null;

		/* 先序结果的第一个元素一定是根 */
		Integer rootval = preorder[a];
		TreeNode root = new TreeNode(rootval);

		
		/* 
		 * 我们在中序结果中找到根,
		 * 根左边的数组, 就是左子树的中序遍历结果;
		 * 根右边的数座, 就是右子树的中序遍历结果;
		 */
		Integer rootidx = c;
		for (; rootidx < d; ++rootidx)
			if (inorder[rootidx]==rootval)
				break;

		/*
		 * 通过在中序结果中计算根左边元素的位置, 可以计算出左子树的大小
		 * 在先序遍历数组中, 从第一个元素, 也就根元素开始, 有且仅有接下来的leftsize个节点是左子树的节点
		 * 并且这些节点是左子树的中先序遍历结果.
		 * 右子树同理.
		 */
		int leftsize = rootidx-c;
		root.left = doBuildTree(a+1, a+1+leftsize, c, rootidx);
		root.right = doBuildTree(a+1+leftsize, b, rootidx+1, d);

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
