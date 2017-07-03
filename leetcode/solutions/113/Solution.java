/**
 * 113 Path Sum II
 *
 * 递归!递归!递归!
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Solution {
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> result = doPathSum(root, sum);
		if (result != null) {
			for (List<Integer> list : result)
				reverse(list);
		}
		return result;
	}

	private List<List<Integer>> doPathSum(TreeNode root, int sum) {
		List<List<Integer>> result = new ArrayList<>();

		if (root == null)
			return result;

		if (root.left == null && root.right == null) {
			if (root.val == sum) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(root.val);
				result.add(list);
			}
			return result;
		}
			
		Integer rootval = root.val;
		List<List<Integer>> left = doPathSum(root.left, sum-rootval);
		List<List<Integer>> right = doPathSum(root.right, sum-rootval);

		for (List<Integer> list : left) {
			list.add(rootval);
			result.add(list);
		}
		for (List<Integer> list : right) {
			list.add(rootval);
			result.add(list);
		}

		return result;
	}

	private void reverse(List<Integer> list) {
		Stack<Integer> stack = new Stack<>();
		for (Integer i : list)
			stack.push(i);
		list.clear();
		while (!stack.empty())
			list.add(stack.pop());
	}


	public static void main(String args[]) {
	}
}

class TreeNode {
	int val;
	TreeNode left, right;

	TreeNode (int x) {
		val = x;
	}
}


