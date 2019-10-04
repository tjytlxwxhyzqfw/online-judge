/**
 * 701 Insert into a Binary Search Tree 77.2% Medium 458/51
 * Performance: speed=100%, memory=100%
 */

import java.util.*;

public class Solution {
	public TreeNode insertIntoBST(TreeNode root, int val) {
		insert(root, val);
		return root;
	}

	void insert(TreeNode root, int val) {
		if (val < root.val) {
			if (root.left == null) root.left = new TreeNode(val);
			else insert(root.left, val);
		} else {
			if (root.right == null) root.right = new TreeNode(val);
			else insert(root.right, val);
		}
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

