/**
 * 655	Print Binary Tree 52.5% Medium 253/616
 * Performance: speed=9%, memory=100%
 */

/*
once you get height, you can just built the final matrix.
then all you need to do is fill the matrix and return the matrix as a list<list>
*/

import java.util.*;

public class Solution {
	public List<List<String>> printTree(TreeNode root) {
		if (root == null) return new ArrayList<>();
		return printTree(root, getHeight(root));
	}

	List<List<String>> printTree(TreeNode root, int height) {
		int width = (1<<height) - 1;
		List<List<String>> paint = new ArrayList<>();
		if (root == null) {
			// root's height >= 1
			for (int i = 0; i < height; ++i) paint.add(padLayer(Arrays.asList(""), width)); 
			return paint;
		}
		paint.add(padLayer(Arrays.asList(""+root.val), width));
		if (root.left == null && root.right == null) {
			for (int i = 0; i < height-1; ++i) paint.add(padLayer(Arrays.asList(""), width));
			return paint;
		}

		List<List<String>> leftPaint = printTree(root.left, height-1);
		List<List<String>> rightPaint = printTree(root.right, height-1);
		for (int i = 0; i < height-1; ++i) {
			List<String> layer = new ArrayList<>();
			layer.addAll(leftPaint.get(i));
			layer.add("");
			layer.addAll(rightPaint.get(i));
			paint.add(layer);
		}

		return paint;
	}

	int getHeight(TreeNode root) {
		if (root == null) return 0;
		return 1 + Math.max(getHeight(root.left), getHeight(root.right));
	}

	// extend layer.size to width by padding "" at left and right side
	List<String> padLayer(List<String> layer, int width) {
		if (layer.size() == width) return layer;

		int n = (width - layer.size()) / 2;
		List<String> layer1 = new ArrayList<>(width);
		for (int i = 0; i < n; ++i) layer1.add("");
		layer1.addAll(layer);
		for (int i = 0; i < n; ++i) layer1.add("");

		return layer1;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
                System.out.println("done");
	}
}

