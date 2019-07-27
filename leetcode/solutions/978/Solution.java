/**
 * 978 LongestTurbulentSubarray
 * Performance: speed=99%, memory=92%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int maxTurbulenceSize(int[] a) {
		int max = 1, pos = 1, neg = 1, pos1;
		for (int i = 1; i < a.length; ++i) {
			pos1 = pos;
			pos = 1; if (a[i] > a[i-1]) max = Math.max(max, pos += neg);
			neg = 1; if (a[i] < a[i-1]) max = Math.max(max, neg += pos1);
		}

		return max;
	}
	public static void main(String args[]) {
		int r1 = new Solution().maxTurbulenceSize(new int[]{1, 2, 1, 1, 2, 1, 2});
		System.out.printf("r1=%d\n", r1);
	}
}
