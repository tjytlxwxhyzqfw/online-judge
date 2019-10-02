/**
 * 652 Find Duplicate Subtrees 47.1% Medium 876/169
 * Performance: speed=14%, memory=9%
 */

/*
Raw idea: for each node, cal its pre-order seq and mid-order seq,
put (pre-mid, node) into a map<List<Int>, List<Node>> then you get the answer;

todo: why disscuss solutions use only one type of traversing method ?
*/

import java.util.*;

public class Solution {
	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		Map<TreeNode, String> map = new HashMap<>();
		pres(root, map);
		Map<String, List<TreeNode>> result = new HashMap<>();
		ins(root, map, result);
		List<TreeNode> list = new ArrayList<>();
		for (List<TreeNode> nodes : result.values()) if (nodes.size() > 1) list.add(nodes.get(0));
		return list;
	}

	// PREorderSearch
	String pres(TreeNode root, Map<TreeNode, String> map) {
		if (root == null) return "";
		String value = root.val + " " + pres(root.left, map) + " " + pres(root.right, map);
		map.put(root, value);
		System.out.printf("pres: %d: %s\n", root.val, value);
		return value;
	}

	String ins(TreeNode root, Map<TreeNode, String> map, Map<String, List<TreeNode>> result) {
		if (root == null) return "";
		String inseq = ins(root.left, map, result) + " " + root.val + " " + ins(root.right, map, result);
		System.out.printf("ins: %d: %s\n", root.val, inseq);
		String key = map.get(root) + inseq;
		List<TreeNode> list = result.get(key);
		if (list == null) result.put(key, list = new ArrayList<>());
		list.add(root);
		return inseq;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
                System.out.println("done");
	}
}

