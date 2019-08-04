/**
 * 979 Distribute Coins in Binary Tree
 * Performance: speed=63%, memory=99%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public int distributeCoins(TreeNode root) {
		int[] n = new int[1];
		dfs(root, n);
		return n[0];
	}

	int dfs(TreeNode root, int[] n) {
		if (root == null) return 0;
		int left = dfs(root.left, n);
		int rght = dfs(root.right, n);
		int total = left + rght + root.val - 1;
		n[0] += Math.abs(total);
		return total;
	}

	public static void main(String args[]) {
	}
}

