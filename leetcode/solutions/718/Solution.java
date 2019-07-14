/**
 * 0718: MaximumLengthOfRepeatedSubarray
 * Performance: speed=44%, memory=76%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int findLength(int[] a, int[] b) {
		if (a.length == 0 || b.length == 0) return 0;
		int[][] dp = new int[a.length+1][b.length+1];
		for (int i = 0; i <= a.length; ++i) dp[i][0] = 0;
		for (int j = 1; j <= b.length; ++j) dp[0][j] = 0;
		int max = 0;
		for (int i = 1; i <= a.length; ++i) {
			for (int j = 1; j <= b.length; ++j) {
				if (a[i-1] == b[j-1]) dp[i][j] = dp[i-1][j-1] + 1;
				max = Math.max(max, dp[i][j]);
			}
		}
		return max;
	}

	public static void main(String args[]) {
		int r1 = new Solution().findLength(
			new int[]{2, 99, 1},
			new int[]{3, 2, 1});
		System.out.printf("r1=%d\n", r1);
	}
}

