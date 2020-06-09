/**
 * 145 Binary Tree Postorder Traversal
 * pass=52 ud=1445/74 s= m=
 */

import java.util.*;

public class Solution {
	public List<Integer> postorderTraversal(TreeNode root) {
		if (root == null) return new ArrayList<>();

		Stack<TreeNode> stack = new Stack<>();
		List<Integer> list = new ArrayList<>();

		stack.push(root);
		TreeNode last = new TreeNode(0);
		while (!stack.isEmpty()) {
			TreeNode top = stack.peek();
			// System.out.printf("top: %d\n", top.val);
			if (top.left != null && top.left != last && top.right != last) {
				stack.push(top.left);
				continue;
			}
			if (top.right != null && top.right != last) {
				stack.push(top.right);
				continue;
			}
			list.add((last = stack.pop()).val);
		} 

		return list;
	}

	static void printA(List<Integer> list) {
		for (Integer i : list) System.out.printf("%d,", i);
		System.out.println();
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		TreeNode root = new TreeNode(1);
		printA(s.postorderTraversal(root));

		root.right = new TreeNode(2);
		printA(s.postorderTraversal(root));

		root.right.left = new TreeNode(3);
		printA(s.postorderTraversal(root));

		root.left = new TreeNode(4);
		printA(s.postorderTraversal(root));


		root.left.left = new TreeNode(5);
		root.left.right = new TreeNode(6);
		printA(s.postorderTraversal(root));

		System.out.println("done");
	}
}

