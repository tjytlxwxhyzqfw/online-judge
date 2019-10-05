/**
 * 769 Max Chunks To Make Sorted 52.7% Medium 533/89
 * Performance: speed=100%, memory=100%
 */

import java.util.*;

public class Solution {
	public int maxChunksToSorted(int[] a) {
		int n = 0, endAt = 0;
		for (int i = 0; i < a.length; ++i) {
			endAt = Math.max(endAt, a[i]);
			if (i == endAt) ++n;
		}
		return n;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.maxChunksToSorted(new int[]{}) == 0;
		assert s.maxChunksToSorted(new int[]{0}) == 1;
		assert s.maxChunksToSorted(new int[]{0, 1, 2, 3, 4}) == 5;
		assert s.maxChunksToSorted(new int[]{2, 1, 0, 3, 4}) == 3;
		assert s.maxChunksToSorted(new int[]{2, 1, 0, 4, 3}) == 2;
		System.out.println("done");
	}
}

