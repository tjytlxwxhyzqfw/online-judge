/**
 * 525	Contiguous Array 43.2% Medium 790/40
 * Performance: speed=%, memory=%
 */

/*
	use map to find delta
	we at i and delete(#1-#0) is d then we found smallest -d in [0, i)
 */

import java.util.*;

public class Solution {
	public int findMaxLength(int[] a) {
		Map<Integer, Integer> map = new HashMap<>();
		int n = 0, delta = 0;
		for (int i = 0; i < a.length; ++i) {
			delta += (a[i] == 1 ? 1 : -1);
			if (delta != 0) {
				Integer j = map.get(delta);
				// System.out.printf("i=%d, j=%s, delta=%d\n", i, j, delta);
				if (j == null) map.put(delta, i);
				else n = Math.max(n, i - j);
			} else n = i+1;
		}
		return n;
	}

	public static void main(String args[]) {
		Solution s = new Solution();


		assert s.findMaxLength(new int[]{}) == 0;
		assert s.findMaxLength(new int[]{0}) == 0;
		assert s.findMaxLength(new int[]{0, 1}) == 2;
		assert s.findMaxLength(new int[]{0, 1, 0}) == 2;
		assert s.findMaxLength(new int[]{1, 0, 1}) == 2;
		assert s.findMaxLength(new int[]{1, 1, 1}) == 0;
		assert s.findMaxLength(new int[]{0, 0, 0, 1, 1}) == 4;
		assert s.findMaxLength(new int[]{0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1}) == 14;
		assert s.findMaxLength(new int[]{0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1}) == 14;

		assert s.findMaxLength(new int[]{0, 0, 1, 0, 0, 0, 1, 1}) == 6;

		System.out.println("all test passed");

	}
}

