/**
 * 958 Check Completeness of a Binary Tree 50.7% Medimu 480/9
 * Performance: speed=100%, memory=100%
 */

// there are many other ways to complete the check in disscuss:
// (1): https://leetcode.com/problems/check-completeness-of-a-binary-tree/discuss/205682/JavaC%2B%2BPython-BFS-Level-Order-Traversal

import java.util.*;

public class Solution {
	public boolean isCompleteTree(TreeNode root) {
		return f(root) >= 0;
	}

	int f(TreeNode root) {
		if (root == null) return 0;
		int left = f(root.left);
		if (left < 0) return -1;

		int leftSize = 1;
		for (int i = 0; i < 31; ++i) {
			if (leftSize-1 >= left) break;
			leftSize <<= 1;
		}
		

		int rightSizeMin = Math.max(leftSize / 2 - 1, 0);
		int rightSizeMax = leftSize - 1;
		if (left < leftSize - 1) rightSizeMax = rightSizeMin;

		int right = f(root.right);
		if (right < rightSizeMin || right > rightSizeMax) return -1;

		return 1 + left + right;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

