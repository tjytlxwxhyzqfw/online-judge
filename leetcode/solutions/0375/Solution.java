/**
 * 0375: GuessNumberHigherOrLowerII
 *
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int getMoneyAmount(int n) {
		int[] dp = new int[n+1];
		int[] tm = new int[n+1];

		dp[0] = tm[0] = 0;
		dp[1] = tm[1] = 0;
		for (int i = 2; i <= n; ++i) {
			dp[i] = 0;
			for (int k = 1; k <= i; ++k) {
				int d = dp[i-k] + tm[i-k]*k;
				int t = tm[i-k];
				if (d < dp[k-1]) {
					d = dp[k-1];
					t = tm[k-1];
				}
				if (k + d < dp[i]) {
					dp[i] = k + d;
					tm[i] = 1 + t;
				}
			}
			System.out.printf("i=%2d, dp=%3d, tm=%3d\n", i, dp[i], tm[i]);
		}
		return dp[n];
	}

	public static void main(String args[]) {
		new Solution().getMoneyAmount(10);
	}
}

