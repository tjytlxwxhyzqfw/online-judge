/**
 * 0646: Maximum Length of Pair Chain
 * Performance: speed=%, memory=%
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Solution {
	// 1. sort by first number in desc order
	//	[a, b] [c, d] [e, f]  => e < c < d,
	//	cant be [c, d]->[e, f], could be [c, d]->[a, b]
	// 2. dp[i] = max{dp[i-?] + 1}
	public int findLongestChain(int[][] pairs) {
		if (pairs.length == 0) return 0;
		Arrays.sort(pairs, (a, b) -> (b[0]-a[0]));

		int[] dp = new int[pairs.length];
		dp[0] = 1;
		int max = 1;
		for (int i = 1; i < pairs.length; ++i) {
			for (int j = 0; j < i; ++j) {
				if (pairs[i][1] < pairs[j][0]) dp[i] = Math.max(dp[i], 1 + dp[j]);
			}
			System.out.printf("i=%2d, v=(%2d, %2d), dp=%d\n", i, pairs[i][0], pairs[i][1], dp[i]);
			max = Math.max(max, dp[i]);
		}

		return max;
	}

	public static void main(String args[]) {
		int r1 = new Solution().findLongestChain(new int[][]{{1, 2}, {2, 3}, {3, 4}, {5, 60}, {7, 8}, {9, 10}});
		System.out.printf("r1=%d\n", r1);
	}
}
