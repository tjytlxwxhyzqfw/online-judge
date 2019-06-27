/**
 * 0264: Ugly Number II
 *
 * Performance: speed=50%, memory=29%
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;

public class Solution {
	public int nthUglyNumber(int n) {
		int[] dp = new int[n];
		dp[0] = 1;
		int j2 = 0, j3 = 0, j5 = 0;
		for (int i = 1; i < n; ++i) {
			int min = Math.min(dp[j2] * 2, Math.min(dp[j3] * 3, dp[j5] * 5));
			if (min / 2 == dp[j2]) ++j2;
			if (min / 3 == dp[j3]) ++j3;
			if (min / 5 == dp[j5]) ++j5;
			dp[i] = min;
		}
		return dp[n-1];
	}

	public static void main(String args[]) {
		// int y = new Solution().nthUglyNumber(100);
		// System.out.printf("=====> %d\n", y);
		int x = new Solution().nthUglyNumber(1690);
		System.out.printf("=====> %d\n", x);
	}
}

