/**
 * 894 All Possible Full Binary Trees 72.0% Medium 570/57
 * Performance: speed=9%, memory=15%
 */

// i can add a cache to make it running faster
// in practice, we need deep copy

import java.util.*;

public class Solution {
	public List<TreeNode> allPossibleFBT(int n) {
		if (n == 1) return Arrays.asList(new TreeNode(0));
		List<TreeNode> list = new ArrayList<>();
		for (int i = 1; i < n; i += 2) {
			List<TreeNode> left = allPossibleFBT(i);
			List<TreeNode> rght = allPossibleFBT(n-1-i);
			for (TreeNode leftR : left) {
				for (TreeNode rghtR : rght) {
					TreeNode root = new TreeNode(0);
					root.left = leftR;
					root.right = rghtR;
					list.add(root);
				}
			}
		}
		return list;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

