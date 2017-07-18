/**
 * 199 Binary Tree Right Side View 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public List<Integer> rightSideView(TreeNode root) {
		int h = height(root);

		Integer result[] = new Integer[h];
		rightSideView(root, result);

		List<Integer> resultList = new ArrayList<>();
		for (Integer i : result)
			resultList.add(i);
		return resultList;
	}

	private void rightSideView(TreeNode root, Integer view[]) {
		if (root == null)
			return;

		int h = height(root);
		Integer left[] = new Integer[h-1];
		Integer rght[] = new Integer[h-1];
		Arrays.fill(left, null);
		Arrays.fill(rght, null);

		rightSideView(root.left, left);
		rightSideView(root.right, rght);

		view[0] = root.val;
		for (int i = 0; i < h-1; ++i)
			view[1+i] = (rght[i]==null ? left[i] : rght[i]);

		System.out.printf("%d: ", root.val);
		for (int i = 0; i < view.length; ++i)
			System.out.printf("%s, ", view[i]==null ? "null" : view[i].toString());
		System.out.println();
	}

	private int height(TreeNode root) {
		if (root == null)
			return 0;
		return 1+Math.max(height(root.left), height(root.right));
	}

	public static void main(String args[]) {
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}
