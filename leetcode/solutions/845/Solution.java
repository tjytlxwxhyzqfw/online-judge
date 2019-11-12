/**
 * 845 Longest Mountain in Array 35.1% Medium 400/15
 * Performance: speed=%, memory=%
 */

// inc[i], desc[i]
// inc[i+1] = inc[i] + 1 or 1
// desc[i+1] = {
//	0 if a[i+1] >= a[i]
//	1 + max(inc[i], desc[i]) if a[i+1] < a[i]
// }
// inc: length of increasing seq
// desc: lenght of inc + descreasing seq

import java.util.*;

public class Solution {
	public int longestMountain(int[] a) {
		if (a.length < 3) return 0;
		int inc = 1, desc = 0, max = 0;
		for (int i = 1; i < a.length; ++i) {
			// System.out.printf("i=%2d, e=%2d, inc=%2d, desc=%2d\n", i, a[i], inc, desc);
			int inc1 = inc, desc1 = desc;
			inc = 1 + (a[i] > a[i-1] ? inc1 : 0);
			if (a[i] < a[i-1] && (inc1 > 1 || desc1 > 0)) {
				desc = 1 + Math.max(inc1, desc1);
				max = Math.max(max, desc);
			} else desc = 0; // fool: if we use desc[] then we don't have to set desc = 0 but if we use
					 // desc (scalar), we must set desc = 0 manually !!!
		}
		return max;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.longestMountain(new int[]{1, 2, 0, 2, 0, 2}) == 3;

		assert s.longestMountain(new int[]{}) == 0;
		assert s.longestMountain(new int[]{1, 2, 3}) == 0;
		assert s.longestMountain(new int[]{3, 2, 1}) == 0;
		assert s.longestMountain(new int[]{3, 3, 3}) == 0;
		assert s.longestMountain(new int[]{2, 3, 1}) == 3;
		assert s.longestMountain(new int[]{2, 3, 1, 2, 3, 2, 1}) == 5;
		assert s.longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5}) == 5;
		System.out.println("done");
	}
}

