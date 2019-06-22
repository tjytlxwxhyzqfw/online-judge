/**
 * 230 Kth smallest element in a BST
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
	public int kthSmallest(TreeNode root, int k) {
		PriorityQueue<Integer> q = new PriorityQueue<>();
		build(root, q);

		int value = 0;
		for (; k > 0; --k) {
			value = q.poll();
		}

		return value;
	}

	public void build(TreeNode root, PriorityQueue<Integer> q) {
		if (root == null) return;
		q.offer(root.val);
		build(root.left, q);
		build(root.right, q);
	}

	public static void main(String args[]) {
	}
}

class TreeNode {int val; TreeNode left; TreeNode right;TreeNode(int x) { val = x; }}
