/**
 * 094 - Binary Tree Inorder Traversal
 *
 * 递归和栈真的是密不可分的
 *
 * 很棒: 我从哪儿来的??
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Solution {
	private static Byte D = 0;
	private static Byte U = 3;
	private static Byte RL = 1;
	private static Byte RR = 2;

	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null)
			return result;

		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);

		Byte status = D;
		TreeNode last = null;

		/*
		 * 我们用一个栈模拟递归的过程,
		 * 当我们递归到一个节点上的时候, 我们就把这个节点压栈,
		 * 当我们从一个节点返回的时候, 我们就将这个节点从栈中弹出.
		 * 每个循环开始的时候, 我们通过判断"我从哪儿来的?"来进行决策.
		 * 这很棒!!!!!!!!!!!!!!!!!!!
		 */
		while (!stack.empty()) {
			TreeNode current = stack.peek();
			if (status == U)
				status = (last==current.left ? RL : RR);

			if (status == RL) {
				/*
				 * 如果我们刚刚从left返回到current,
				 * 那么我们就:
				 * 1. append current.val
				 * 2. 若right存在, 就访问right; 否则, 向上返回
				 */
				result.add(current.val);
				if (current.right != null) {
					stack.push(current.right);
					status = D;
				} else {
					last = stack.pop();
					status = U;
				}
			} else if (status == RR) {
				/*
				 * 此时, 我们是从right返回至此的,
				 * 我们继续向上返回
				 */
				last = stack.pop();
				status = U;
			} else {
				/*
				 * 此时我们正在下降,
				 * 若有left, 继续下降到left;
			 	 * 否则, 进入从left返回的状态
				 */
				if (current.left != null) {
					stack.push(current.left);
					status = D;
				} else {
					status = RL;
				}
			}
		}

		return result;
	}

	/*
	 * I am using a stack solution FOR 先根遍历!!!
	 */
	public List<Integer> preorderTraversal(TreeNode root) {
		if (root == null)
			return null;

		List<Integer> result = new ArrayList<>();
		result.add(root.val);

		/*
		 * 我们使用一个栈来模拟递归过程
		 * 可以证明, 栈中从栈顶到栈底的元素, 始终对应树上的一个路径
		 */
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);

		/*
		 * 栈顶元素的状态:
		 * 1. 正在下降, 即栈顶元素是刚刚被压入的
		 * 2. 正在上升, 并且栈顶元素的左儿子刚刚被弹出, 即从左儿子返回
		 * 3. 正在上升, 并且栈顶元素的右儿子刚刚被弹出, 即从右儿子返回
		 */
		Byte status = D;

		/* 刚刚从栈中被弹出的节点 */
		TreeNode last = null;

		while (!stack.empty()) {
			TreeNode left = stack.peek().left;
			TreeNode right = stack.peek().right;

			/* 如果正在上升, 我们用last来判定从左边还是从右边返回 */
			if (status == U)
				status = (last==left ? RL : RR);

			if (status==D && left!=null) {
				/* 如果栈顶是刚刚被压入的, 且其左儿子非空, 那么访问其左儿子 */
				result.add(left.val);
				stack.push(left);
				status = D;
			} else if (status!=RR && right!=null) {
				/* 如果栈顶是刚刚被压入或从做儿子返回的, 且其右儿子非空, 那么访问其右儿子 */
				result.add(right.val);
				stack.push(right);
				status = D;
			} else {
				/* 在其他任意情况下, 我们弹出栈顶元素 */
				last = stack.pop();
				status = U;
			}
		}

		return result;
	}

	/*
	 * This is a trival recursive solution,
	 * and I'm not supposed to use this.
	 */
	private void doInorderTraversal(TreeNode root, List<Integer> list) {
		if (root == null)
			return;
		list.add(root.val);
		doInorderTraversal(root.left, list);
		doInorderTraversal(root.right, list);
	}

	public static void main(String args[]) {
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	public TreeNode(int x) {
		val = x;
	}
}
