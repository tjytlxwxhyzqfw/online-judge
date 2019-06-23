/**
 * 222 Count Complete Tree Nodes 
 * Performance: 100%, 61%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int countNodes(TreeNode root) {
		if (root == null) return 0;

		int leftmost = 1;
		TreeNode current = root;
		while (current.left != null) {
			leftmost <<= 1;
			current = current.left;
		}

		int count = 0;
		int i = leftmost, j = (leftmost - 1) + leftmost;
		while (i <= j) {
			int k = (i + j) / 2;
			if (find(root, k)) {
				count = k;
				i = k + 1;
			} else {
				j = k - 1;
			}
		}
		return count;
	}

	public boolean find(TreeNode root, int index) {
		if (root == null) {
			return false;
		}
		if (index == 1) {
			return true;
		}

		int leftmost = 2;
		while (index >= leftmost) {
			leftmost <<= 1;
		}
		leftmost >>= 1;

		long middle = (leftmost + 2 * leftmost - 1) / 2;
		if (index <= middle) {
			return find(root.left, index - (leftmost / 2));
		} else {
			return find(root.right, index - leftmost);
		}
	}

	public static void main(String args[]) {
		TreeNode root = new TreeNode(1);
		int r1 = new Solution().countNodes(root);
		System.out.printf("r1=%d\n", r1);

		root.left = new TreeNode(2);
		int r2 = new Solution().countNodes(root);
		System.out.printf("r2=%d\n", r2);

		root.right = new TreeNode(3);
		int r3 = new Solution().countNodes(root);
		System.out.printf("r3=%d\n", r3);

		root.left.left = new TreeNode(4);
		int r4 = new Solution().countNodes(root);
		System.out.printf("r4=%d\n", r4);

		root.left.right = new TreeNode(5);
		int r5 = new Solution().countNodes(root);
		System.out.printf("r5=%d\n", r5);

		root.right.left = new TreeNode(6);
		int r6 = new Solution().countNodes(root);
		System.out.printf("r6=%d\n", r6);

		root.right.right = new TreeNode(7);
		int r7 = new Solution().countNodes(root);
		System.out.printf("r7=%d\n", r7);
	}
}

class TreeNode {int val; TreeNode left; TreeNode right;TreeNode(int x) { val = x; }}
