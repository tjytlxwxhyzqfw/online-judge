/**
 * 199 Binary Tree Right Side View 
 *
 * 2017-08-01
 *
 * 这个是典型的递归的解法.
 * 先返回右儿子的右视图, 再返回左儿子的右视图
 * 然后用右儿子的右视图替换左儿子的相同长度的前缀即可.
 *
 * 其实我这到题的数组实现并不优雅,
 * 可以直接使用List, 并且不计算高度的.
 * 然后用右儿子覆盖左儿子前缀, 若右儿子更长, 则改为add()
 *
 * 真的是, 凡是跟二叉树有关的, 就是递归的呀
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
