/**
 * 11 Container With Most Water 4828/515
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public int maxArea(int[] hs) {
		int i = 0, j = hs.length-1;
		int maxA = 0;
		while (i < j) {
			while (j > i) {
				int a = (j-i) * Math.min(hs[i], hs[j]);
				if (a > maxA) maxA = a;
				if (hs[j] >= hs[i]) break;
				--j;
			}
			int left = hs[i];
			while (i < j && hs[i] <= left) ++i;
		}
		return maxA;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.maxArea(new int[]{1, 1}) == 1;
		assert s.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}) == 49;
		assert s.maxArea(new int[]{7, 3, 8, 4, 5, 2, 6, 8, 1}) == 49;
		assert s.maxArea(new int[]{8, 6, 2, 5, 4, 8, 1, 2}) == 40;
		assert s.maxArea(new int[]{2, 1, 8, 4, 5, 2, 6, 8}) == 40;

		System.out.println("done");
	}
}

