/**
 * 116 - Populating Next Right Pointers in Each Node
 */

public class Solution {
	public void connect(TreeLinkNode root) {
		if (root != null) {
			connect(root.left);
			connect(root.right);

			TreeLinkNode leftRight=root.left, rightLeft = root.right;
			while (leftRight!=null) {
				leftRight.next = rightLeft;
				leftRight = leftRight.right;
				rightLeft = (rightLeft == null ? null : rightLeft.left);
			}
		}
	}

	public static void main(String args[]) {
	}
}

class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;
	TreeLinkNode (int x) {
		val = x;
	}
}
