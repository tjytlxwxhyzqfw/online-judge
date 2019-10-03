/**
 * 654	Maximum Binary Tree 77.1% Medium 1265/168
 * Performance: speed=5%(15ms), memory=87%
 * (segtree) speed=12(8ms)
 */

import java.util.*;

public class Solution {
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		if (nums == null || nums.length == 0) return null;

		Stack<TreeNode> stack = new Stack<>();
		for (int i = 0; i < nums.length; ++i) {
			TreeNode n = new TreeNode(nums[i]);
			TreeNode m = null;
			while (!stack.isEmpty() && stack.peek().val < n.val) m = stack.pop();
			if (!stack.isEmpty()) stack.peek().right = n;
			stack.push(n);
			n.left = m;
		}
		
		TreeNode root = null;
		while (!stack.isEmpty()) root = stack.pop();
		return root;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		s.constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
                System.out.println("done");
	}
}
