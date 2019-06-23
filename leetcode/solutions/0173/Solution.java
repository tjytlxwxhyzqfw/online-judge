/**
 * 173 Binary Search Tree Iterator 
 *
 * 题意: 实现一个二叉树迭代器.
 *
 * 居然这么轻松就A了, 真是出乎我的意料啊!!!
 *
 * 2017-08-01
 *
 * 用栈来模拟递归.
 * 栈顶元素始终是树上的最小元素.
 * 所以, 它一定没有左儿子.
 * 如果它有右儿子, 那么就将其右子树上到最小元的路径压栈.
 * 
 * 用栈来模拟递归的时候, 压栈 等同于 函数调用吗?
 * 弹出=函数返回吗?
 * 不是很确定, 将来更加确定的时候再回头思考这个问题.
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

