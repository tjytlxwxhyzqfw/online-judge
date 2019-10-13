/**
 * 814 Binary Tree Pruning 72.0% Medium 673/23
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public TreeNode pruneTree(TreeNode root) {
		if (root == null) return null;
		root.left = pruneTree(root.left);
		root.right = pruneTree(root.right);
		if (root.left == null && root.right == null) {
			return root.val == 0 ? null : root;
		} 
		return root;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

