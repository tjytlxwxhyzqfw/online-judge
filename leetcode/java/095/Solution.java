/**
 * 095 - Unique Binary Search Trees II
 *
 * 就是那种标准的思考问题的方式:
 * 如果我能解决问题n-1, 那么我能不能解决问题n?
 * 只不过, 这个题的思考方式是: 如果我能解决问题[1:n-1], 那么我们不能解决问题n?
 *
 * 如果我有了使用1-5个节点的所有二叉树, 那么我们能组装出一颗使用6个节点的二叉树吗? 当然可以.
 * 新建一个树根, 然后:
 * - 所有的5节点树当做树根的左子树
 * - 所有的5节点树当做树根的右子树
 * - 一个4节点树当左子树, 一个1节点树当右子树
 * - 1+4
 * - 2+3, 3+2
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
	public List<TreeNode> generateTrees(int n) {
		List<List<TreeNode>> table = new ArrayList<>(n+1);
		table.add(new ArrayList<>());

		/* i denotes number of child nodes, i.e. number of all nodes - 1(root) */
		for (int i = 0; i < n; ++i) {
			/* we are generating trees with i+1 nodes */
			System.out.printf("i+1=%d\n", i+1);
			List<TreeNode> trees = new ArrayList<>();
			for (int leftSize = 0; leftSize <= i; ++leftSize) {
				int rghtSize = i-leftSize;
				System.out.printf("\tleftSize=%d, rgthSize=%d\n", leftSize, rghtSize);
				List<TreeNode> leftChildren = table.get(leftSize);
				List<TreeNode> rghtChildren = table.get(rghtSize);
				List<TreeNode> combinations = assemble(leftChildren, rghtChildren);
				System.out.printf("\tcombinations.size()=%d\n", combinations.size());
				trees.addAll(combinations);
			}
			System.out.printf("trees.size()=%d\n", trees.size());
			table.add(trees);
		}

		List<TreeNode> result = new ArrayList<>();
		List<TreeNode> templates = table.get(n);
		for (TreeNode root : templates)
			result.add(copyAndFill(root, new Integer[]{1}));

		return result;
	}	

	private List<TreeNode> assemble(List<TreeNode> leftChildren, List<TreeNode> rghtChildren) {
		List<TreeNode> result = new ArrayList<>();

		if (leftChildren.size()==0 && rghtChildren.size()==0) {
			result.add(new TreeNode(0));
			return result;
		}

		if (leftChildren.size() == 0) {
			for (TreeNode rghtRoot : rghtChildren) {
				TreeNode root = new TreeNode(0);
				root.right = rghtRoot;
				result.add(root);
			}
			return result;
		}

		if (rghtChildren.size() == 0) {
			for (TreeNode leftRoot : leftChildren) {
				TreeNode root = new TreeNode(0);
				root.left = leftRoot;
				result.add(root);
			}
			return result;
		}

		/* neither leftChildren nor rghtChildren is empty */
		for (TreeNode leftRoot : leftChildren) {
			for (TreeNode rghtRoot : rghtChildren) {
				TreeNode root = new TreeNode(0);
				root.left = leftRoot;
				root.right = rghtRoot;
				result.add(root);
			}
		}
		return result;
	}

	private TreeNode copyAndFill(TreeNode root, Integer start[]) {
		TreeNode newRoot = new TreeNode(0);
		if (root.left != null)
			newRoot.left = copyAndFill(root.left, start);
		newRoot.val = start[0]++;
		if (root.right != null)
			newRoot.right = copyAndFill(root.right, start);
		return newRoot;
	}

	public static void main(String args[]) {
	}
}

class TreeNode {
	int val;
	TreeNode left, right;

	TreeNode (int x) {
		val = x;
	}
}
