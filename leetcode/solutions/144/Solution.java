/**
 * 144 - Binary Tree Preorder Traversal
 *
 * 用一个栈来代替递归.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
	private static final int DOWN = 1;
	private static final int RET = -1;


	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();

		if (root==null)
			return result;

		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		int direction = DOWN;
		TreeNode last = null;
		while (!stack.isEmpty()) {
			// 用这种方法访问栈顶元素
			TreeNode current = stack.peek();
			System.out.printf("current: %d\n", current.val);

			if (direction == RET) {
				// 只有从左儿子返回, 且右儿子非空的时候, 我们才会继续遍历
				// 在其他任何情况下, 我们直接返回
				if (last == current.left && current.right != null) {
					direction = DOWN;
					stack.push(current.right);
				} else {
					last = stack.pop();
				}
			} else if (direction == DOWN) {
				result.add(current.val);
				// 我们下降到一个节点,
				// 如果左儿子非空, 就访问左儿子
				// 如果右儿子非空, 就访问右儿子
				// 如果两个儿子儿子都是空的, 直接返回
				if (current.left != null) {
					stack.push(current.left);
				} else if (current.right != null) {
					stack.push(current.right);
				} else {
					direction = RET;
					last = stack.pop();
				}
			} else {
				throw new RuntimeException("impossible!");
			}
		}

		return result;
	}

	public static void main(String args[]) {
		TreeNode root = new TreeNode(0);
		TreeNode left = new TreeNode(1);
		TreeNode right = new TreeNode(2);
		TreeNode leftleft = new TreeNode(3);

		root.left = left;
		root.right = right;
		root.left.left = leftleft;

		List result = new Solution().preorderTraversal(root);
		System.out.printf("result: %s\n", result.toString());
	}
}

class TreeNode {
	int val;
	TreeNode left, right;
	TreeNode(int x) {
		val = x;
	}
}
