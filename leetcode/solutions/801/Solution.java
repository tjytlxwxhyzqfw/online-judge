/**
 * 801: MinimumSwapsToMakeSequencesIncreasing
 * Performance: speed=64%, memory=75%
 */

/*
50 20 | 30 40
10 60 | 70 80

50 20 | 70 80
10 60 | 30 40
    
you cannot make the right decision with A[:2] and B[:2],
because you do not know that which pair should be switched.

BUT !!! you can dp ALL possibilities:

dpF[i] -> i must have been fixxed
dpS[i] -> i must have been switched


if min(a[i], b[i]) > max(a[i-1], b[i-1]) {
	// you can place a[i], a[i-1], b[i], b[i-1] in any order
	dpF[i] = min(dpS[i-1], dpF[i-1])
	dpS[i] = 1 + dF[i]
} else {
	if (a[i] > a[i-1] && b[i] > b[i-1]) {
		// a[i-1] must be followed by a[i] and so as b[i-1]
		dpF[i] = dpF[i-1];
		dpS[i] = dpS[i-1] + 1;
	} else {
		// a[i-1] must be followed by b[i] and so as b[i-1]
		dpF[i] = dpS[i-1];
		dpS[i] = dpF[i-1] + 1;
	}
}
*/

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int minSwap(int[] a, int[] b) {
		int n = a.length;
		if (n == 0 || n == 1) return 0;

		int[] fixed = new int[n], switched = new int[n];
		{ fixed[0] = 0; switched[0] = 1; }
		for (int i = 1; i < n; ++i) {
			if (Math.min(a[i], b[i]) > Math.max(a[i-1], b[i-1])) {
				fixed[i] = Math.min(fixed[i-1], switched[i-1]);
				switched[i] = 1 + fixed[i];
			} else if (a[i] > a[i-1] && b[i] > b[i-1]) {
				fixed[i] = fixed[i-1];
				switched[i] = 1 + switched[i-1];
			} else {
				fixed[i] = switched[i-1];
				switched[i] = 1 + fixed[i-1];
			}
		}
		return Math.min(fixed[n-1], switched[n-1]);
	}

	public static void main(String args[]) {
		int r1 = new Solution().minSwap(
			new int[]{0, 7, 8, 10, 10, 11, 12, 13, 19, 18},
			new int[]{4, 4, 5,  7, 11, 14, 15, 16, 17, 20});
		System.out.printf("r1=%d\n", r1);
	}
}

