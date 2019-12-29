/**
 * 939 Minimum Area Rectangle 51.3% Medium 432/83
 * Performance: speed=87%, memory=60%
 */

import java.util.*;

// my idea: traverse pair (x1, y1) & (x2, y2), treat them as diglog p
// then you know the other two p are (x1, y2) (x2, y1), if you found
// them you cal the area.

public class Solution {
	public int minAreaRect(int[][] p) {
		int minArea = Integer.MAX_VALUE;
		Set<Long> set = new HashSet<>();
		for (int[] xy : p) set.add(xy[0]*100000L + xy[1]);
		for (int i = 0; i < p.length; ++i) {
			for (int j = i+1; j < p.length; ++j) {
				if (p[i][0] == p[j][0] || p[i][1] == p[j][1]) continue;
				long q1 = p[i][0] * 100000L + p[j][1];
				long q2 = p[j][0] * 100000L + p[i][1];
				if (set.contains(q1) && set.contains(q2)) {
					int a = Math.abs((p[i][0] - p[j][0]) * (p[i][1] - p[j][1]));
					minArea = Math.min(minArea, a);
				}
			}
		}
		return minArea == Integer.MAX_VALUE ? 0 : minArea;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.minAreaRect(new int[][]{}) == 0;
		assert s.minAreaRect(new int[][]{{0, 0}}) == 0;
		assert s.minAreaRect(new int[][]{{0, 0}, {0, 1}}) == 0;
		assert s.minAreaRect(new int[][]{{0, 0}, {1, 1}}) == 0;
		assert s.minAreaRect(new int[][]{{0, 0}, {1, 1}, {0, 1}}) == 0;
		assert s.minAreaRect(new int[][]{{0, 0}, {1, 1}, {0, 1}, {1, 0}}) == 1;
		assert s.minAreaRect(new int[][]{{1, 1}, {1, 3}, {3, 1}, {3, 3}, {2, 2}}) == 4;
		assert s.minAreaRect(new int[][]{{1, 1}, {1, 3}, {3, 1}, {3, 3}, {4, 1}, {4, 3}}) == 2;

		System.out.println("done");
	}
}
