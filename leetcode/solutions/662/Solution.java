/**
 * 662 Maximum Width of Binary Tree 39.5% Medium 757/155
 * Performance: speed=100%, memory=83%
 */

import java.util.*;

public class Solution {
	public int widthOfBinaryTree(TreeNode root) {
		if (root == null) return 0;

		long width = 0;
		Queue<Node> q = new LinkedList<>();
		Node lastNode, firstNode;
		q.offer(lastNode = firstNode = new Node(root, 1, 0));
		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (curr.depth != lastNode.depth) {
				width = Math.max(width, 1 + lastNode.idx - firstNode.idx);
				firstNode = curr;
			} 

			if (curr.node.left != null) q.offer(new Node(curr.node.left, curr.idx * 2, curr.depth+1));
			if (curr.node.right != null) q.offer(new Node(curr.node.right, curr.idx * 2 + 1, curr.depth+1));

			lastNode = curr;
		}
		return (int)Math.max(width, 1+lastNode.idx-firstNode.idx);
	}

	public static void main(String args[]) {
		Solution s = new Solution();
                System.out.println("done");
	}
}

class Node {
	TreeNode node;
	long idx;
	int depth;

	Node(TreeNode node, long idx, int depth) {
		this.node = node;
		this.idx = idx;
		this.depth = depth;
	}
}
