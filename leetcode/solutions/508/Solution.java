/**
 * 508 Most Frequent Subtree Sum 55.2% Medium
 * Performance: speed=84%, memory=85%
 */

import java.util.*;

public class Solution {
	public int[] findFrequentTreeSum(TreeNode root) {
		if (root == null) return new int[];

		Map<Integer, Integer> freq = new HashMap<>();
		search(root, freq);

		int maxfreq = 0, n = 0;
		for (Integer f : freq.values()) {
			if (f > maxfreq) { maxfreq = f; n = 1; }
			else if (f == maxfreq) { ++n; }
		}

		int[] a = new int[n];
		int i = 0;
		for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
			if (entry.getValue() == maxfreq) a[i++] = entry.getKey();
		}
		return a;
	}

	int search(TreeNode root, Map<Integer, Integer> freq) {
		int sum = root.val;
		if (root.left != null) {
			sum += search(root.left, freq);
		}
		if (root.right != null) {
			sum += search(root.right, freq);
		}
		Integer f = freq.get(sum);
		freq.put(sum, f == null ? 1 : ++f);
		return sum;
	}

	public static void main(String args[]) {
	}
}

