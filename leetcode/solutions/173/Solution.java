/**
 * 173 Binary Search Tree Iterator 
 *
 * 居然这么轻松就A了, 真是出乎我的意料啊!!!
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode {
	int val;
	TreeNode left, right;

	TreeNode(int x) {
		val = x;
	}
}

public class BSTIterator {
	private Stack<TreeNode> stack;

	public BSTIterator(TreeNode root) {
		stack = new Stack<>();
		if (root != null)
			pushUntilLeftmost(root);
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	/** @return the next smallest number */
	public int next() {
		TreeNode top = stack.pop();
		if (top.right != null)
			pushUntilLeftmost(top.right);
		return top.val;
	}

	private void pushUntilLeftmost(TreeNode root) {
		stack.push(root);
		while (stack.peek().left != null)
			stack.push(stack.peek().left);
	}

	public static void main(String args[]) {
	}
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

