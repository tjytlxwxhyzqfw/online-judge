/**
 * 971 Flip Binary Tree To Match Preorder Traversal
 * Performance: speed=54%, memory=100%
 */

/*
 better solution in disscuss
 ---------------------------

Explanation:
Global integer i indicates next index in voyage v.
If current node == null, it's fine, we return true
If current node.val != v[i], doesn't match wanted value, return false
If left child exist but don't have wanted value, flip it with right child.

Java:

    List<Integer> res = new ArrayList<>();
    int i = 0;
    public List<Integer> flipMatchVoyage(TreeNode root, int[] v) {
        return dfs(root, v) ? res : Arrays.asList(-1);
    }

    public Boolean dfs(TreeNode node, int[] v) {
        if (node == null) return true;
        if (node.val != v[i++]) return false;
        if (node.left != null && node.left.val != v[i]) {
            res.add(node.val);
            return dfs(node.right, v) && dfs(node.left, v);
        }
        return dfs(node.left, v) && dfs(node.right, v);
    }

---------------------------- */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
		List<Integer> list = new ArrayList<>();
		int n = dfs(root, 0, voyage, list);
		return n == voyage.length ? list : Arrays.asList(-1);
	}

	int dfs(TreeNode root, int i, int[] voyage, List<Integer> list) {
		if (root == null) return 0;
		if (i >= voyage.length) return -1;
		if (root.val != voyage[i]) return -1;

		if (i+1 >= voyage.length) return (root.left == null && root.right == null) ? 1 : -1;

		boolean swap = ((root.left != null && root.left.val != voyage[i+1]) ||
			(root.left == null && root.right != null && root.right.val != voyage[i+1]));

		if (swap) {
			TreeNode p = root.left;
			root.left = root.right;
			root.right = p;
			list.add(root.val);
		}

		int leftS = dfs(root.left, i+1, voyage, list);
		if (leftS == -1) return -1;

		int rghtS = dfs(root.right, i+leftS+1, voyage, list);
		return rghtS == -1 ? -1 : leftS + rghtS + 1;
	}

	public static void main(String args[]) {
	}
}

