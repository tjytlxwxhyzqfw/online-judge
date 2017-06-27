/**
 * 102 - Binary Tree Level Order Traversal
 *
 * 递归, 应该成为一种意识!意识!!!
 *
 * 如果这个题不是这么给的, 而是: 从上到下, 从左到右, 输出一颗二叉树的全部节点, 返回List<Integer>
 * 那么, 你能想到这个办法吗?
 * 很多时候, 递归过程并不是直接生成题目要求结果的呀!
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
	public List<List<Integer>> levelOrder(TreeNode root) {
		if (root == null)
			return new ArrayList<List<Integer>>();

		List<List<Integer>> left = levelOrder(root.left);
		List<List<Integer>> rght = levelOrder(root.right);

		List<List<Integer>> result = new ArrayList<>();
		result.add(Arrays.asList(root.val));

		Integer deepth;
		for (deepth = 0; deepth<left.size() && deepth<rght.size(); ++deepth) {
			List<Integer> level = new ArrayList<>();
			level.addAll(left.get(deepth));
			level.addAll(rght.get(deepth));
			result.add(level);
		}

		for (; deepth < left.size(); ++deepth) {
			result.add(left.get(deepth));
		}

		for (; deepth < rght.size(); ++deepth)
			result.add(rght.get(deepth));

		return result;
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
