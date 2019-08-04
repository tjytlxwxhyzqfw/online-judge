/**
 * 988 Smallest String Starting From Leaf
 * Performance: speed=61%, memory=100%
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	private char[] smallest = new char[8501];
	private int n = smallest.length;

	public String smallestFromLeaf(TreeNode root) {
		if (root == null) return "";
		Arrays.fill(smallest, (char)26);
		char[] path = new char[8501];
		dfs(root, path, 0);
		StringBuilder b = new StringBuilder();
		for (int i = n-1; i >= 0; --i) b.append((char)(97+smallest[i]));
		return b.toString();
	}

	private void dfs(TreeNode root, char[] path, int i) {
		// root is not null
		path[i++] = (char)root.val;
		if (root.left == null && root.right == null) {
			System.out.printf("leaf: %c, n=%d\n", (char)(97+root.val), n);
			if (smaller(path, i, smallest, n)) { n = i; for (int k = 0; k < n; ++k) smallest[k] = path[k]; }
		}
		if (root.left != null) dfs(root.left, path, i);
		if (root.right != null) dfs(root.right, path, i);
	}

	private boolean smaller(char[] left, int i, char[] rght, int j) {
		for (int p = i-1, q = j-1; p >= 0 && q >= 0; --p, --q) if (left[p] != rght[q]) return left[p] < rght[q];
		return i < j;
	}

	public static void main(String args[]) {
	}
}

