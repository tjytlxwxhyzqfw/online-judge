/**
 * 768 (hard) Max Chunks To Make Sorted II 46.8%
 * Performance: speed=71%, memory=71%
 */

/*
todo: reduct time limit to o(n)
my solution is (nlgn) but their is a o(n) solution and it is much easier than mine.
https://leetcode.com/problems/max-chunks-to-make-sorted-ii/discuss/113462/Java-solution-left-max-and-right-min.
*/

import java.util.*;

public class Solution {
	public int maxChunksToSorted(int[] a) {
		int[] b = new int[a.length];
		for (int i = 0; i < a.length; ++i) b[i] = a[i];
		Arrays.sort(b);
		Map<Integer, Integer> numLessThan = new HashMap<>();
		for (int i = 0; i < b.length; ++i) {
			if (i > 0 && b[i] == b[i-1]) continue;
			numLessThan.put(b[i], i);
			System.out.printf("# less than %2d: %d\n", b[i], i);
		}

		Map<Integer, Integer> pMap = new HashMap<>();
		int n = 0, end = 0;
		for (int i = 0; i < a.length; ++i) {
			Integer p = pMap.get(a[i]);
			if (p != null) pMap.put(a[i], p+1);
			else pMap.put(a[i], numLessThan.get(a[i]));
			end = Math.max(end, pMap.get(a[i]));
			System.out.printf("i=%2d, p=%2d, end=%2d\n", i, p, end);
			if (i >= end) ++n;
		}

		return n;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.maxChunksToSorted(new int[]{}) == 0;
		assert s.maxChunksToSorted(new int[]{1}) == 1;
		assert s.maxChunksToSorted(new int[]{7, 7, 7, 7, 7, 7, 7}) == 7;
		assert s.maxChunksToSorted(new int[]{4, 1, 4, 2, 4, 3, 4, 4, 4}) == 4;
		assert s.maxChunksToSorted(new int[]{5, 4, 3, 2, 1}) == 1;
		assert s.maxChunksToSorted(new int[]{2, 1, 3, 4, 4}) == 4;
		System.out.println("done");
	}
}

