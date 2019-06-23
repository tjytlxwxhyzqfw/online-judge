/**
 * 117 - Populating Next Right Pointers in Each Node II
 *
 * 坑1: 我先用116题的方法拿过来, 作为一个单独的方法, 叫做preconnect
 * 在此基础上, 左右子树同时下降并连接, 这个错的有点严重, 因为preconnect后, 左右子树是连在一起的
 * 所以, 左子树下降的时候, 很可能就下降到右子树上面去了.
 */

public class Solution {
	public void connect(TreeLinkNode root) {
		if (root != null) {
			connect(root.left);
			connect(root.right);

			TreeLinkNode leftmost=root.right, start=root.left;
			TreeLinkNode rightmost = toRightmost(start);
			while (start != null) {
				/* 先找start, 然后再连接!!! 要不然start就连到右子树上去了!!! */
				start = leftmostInNextLevel(start);
				rightmost.next = leftmost;
				rightmost = toRightmost(start);
				leftmost = leftmostInNextLevel(leftmost);
			}
		}
	}

	/*
	 * current是本层最左边的可能有孩子的节点,
	 * 找到下一层最左边的节点
	 */
	private TreeLinkNode leftmostInNextLevel(TreeLinkNode current) {
		if (current == null)
			return null;
		System.out.printf("leftmostInNextLevel: current=%d, current.next=%d\n", current.val, current.next==null ? -1 : current.next.val);
		if (current.left != null)
			return current.left;
		if (current.right != null)
			return current.right;
		return leftmostInNextLevel(current.next);
	}

	/* leftmost是本层最左边的节点, 找到本层最右边的节点 */
	private TreeLinkNode toRightmost(TreeLinkNode leftmost) {
		if (leftmost == null)
			return null;
		TreeLinkNode rightmost = null;
		for (rightmost = leftmost; rightmost.next != null; rightmost=rightmost.next);
		return rightmost;
	}

	public static void main(String args[]) {
		TreeLinkNode root = new TreeLinkNode(3);
		root.left = new TreeLinkNode(9);
		root.right = new TreeLinkNode(20);
		root.right.left = new TreeLinkNode(15);
		root.right.right = new TreeLinkNode(7);
		new Solution().connect(root);
		System.out.printf("%d->%d\n", root.left.val, root.left.next.val);
	}
}

class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;
	TreeLinkNode (int x) {
		val = x;
	}
}
