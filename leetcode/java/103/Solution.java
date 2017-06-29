/**
 * 103 - Binary Tree Zigzag Level Order Traversal
 * 
 * =102
 *
 * =102
 *
 * java 小知识: Arrays.asList(...) 可能会返回一个不支持 .clear() 方法的列表.
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = getZigzagLevelOrder(root);

		for (int i = 0; i < result.size(); ++i) {
			if (i%2 == 1) {
				List<Integer> level = result.get(i);

				Stack<Integer> stack = new Stack<>();
				for (Integer val : level)
					stack.push(val);

				List<Integer> reversed = new ArrayList<>();
				while (!stack.empty())
					reversed.add(stack.pop());

				result.set(i, reversed);
			}
		}

		return result;
	}

	private List<List<Integer>> getZigzagLevelOrder(TreeNode root) {
		if (root == null)
			return new ArrayList<List<Integer>>();

		List<List<Integer>> left = getZigzagLevelOrder(root.left);
		List<List<Integer>> right = getZigzagLevelOrder(root.right);

		List<List<Integer>> result = new ArrayList<>();
		result.add(Arrays.asList(root.val));

		Integer deepth = 0;

		for (; deepth<left.size() && deepth<right.size(); ++deepth) {
			List<Integer> level = new ArrayList<>();
			level.addAll(left.get(deepth));
			level.addAll(right.get(deepth));
			result.add(level);
		}

		for (; deepth < left.size(); ++deepth)
			result.add(left.get(deepth));

		for (; deepth < right.size(); ++deepth)
			result.add(right.get(deepth));

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
