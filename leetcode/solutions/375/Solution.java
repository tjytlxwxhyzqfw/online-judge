/**
 * 0375: GuessNumberHigherOrLowerII
 *
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int getMoneyAmount(int n) {
		int[][] dp = new int[n+2][n+2];
		for (int k = 2; k <= n; ++k) {
			for (int i = 1; i+k <= n+1; ++i) {
				dp[i][i+k] = Integer.MAX_VALUE;
				for (int j = i; j < i+k; ++j) {
					dp[i][i+k] = Math.min(dp[i][i+k], j + Math.max(dp[i][j], dp[j+1][i+k]));
				}
				System.out.printf("[%2d, %2d) = %d\n", i, i+k, dp[i][i+k]);
			}
		}
		return dp[1][n+1];
	}

	public static void main(String args[]) {
		int r1 = new Solution().getMoneyAmount(100);
		System.out.printf("r1=%d\n", r1);
	}
}

