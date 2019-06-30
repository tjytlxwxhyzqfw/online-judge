/**
 * 0309: Best Time To Buy And Sell Stock With Cooldown
 * Performance: speed=86%, memory=100%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int maxProfit_S5M5(int[] prices) {
		int n = prices.length;
		if (n == 0) return 0;
		int[][] dp = new int[n][n+1];
		for (int i = 0; i < n; ++i) for (int j = 0; j <= n; ++j) dp[i][j] = Integer.MIN_VALUE;
		dp[0][n] = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < i; ++j) {
				if (dp[i][j] == Integer.MIN_VALUE) continue;
				System.out.printf("d=%2d, s=%2d: %d\n", i, j, dp[i][j]);
				//sell
				if (i+2<n) dp[i+2][n] = Math.max(dp[i+2][n], dp[i][j]+prices[i]);
				//wait
				if (i+1<n) dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j]);
			}
			System.out.printf("d=%2d, s=%2d: %d\n", i, -1, dp[i][n]);
			// buy
			if (i+1<n) {
				dp[i+1][i] = Math.max(dp[i+1][i], dp[i][n]-prices[i]);
				dp[i+1][n] = Math.max(dp[i+1][n], dp[i][n]);
			}
		}

		int max = dp[n-1][n];
		for (int j = 0; j < n; ++j) max = Math.max(max, dp[n-1][j]+prices[n-1]);
		if (n-2>=0) {
			for (int j = 0; j < n; ++j) max = Math.max(max, dp[n-2][j]+prices[n-2]);
		}
		return max;
	}

	public int maxProfit(int[] prices) {
		int n =prices.length;
		if (n == 0) return 0;
		int[] sel = new int[n];
		int[] buy = new int[n];

		sel[0] = 0;
		if (n >= 2) sel[1] = 0;
		buy[0] = 0 - prices[0];
		for (int i = 1; i < n; ++i) {
			buy[i] = Math.max(buy[i-1], sel[Math.max(0, i-2)] - prices[i]);
			sel[i] = Math.max(buy[i-1] + prices[i], sel[i-1]);
			System.out.printf("i=%d, b=%d, s=%d\n", i, buy[i], sel[i]);
		}
		return sel[n-1];
	}

	public static void main(String args[]) {
		int ans = new Solution().maxProfit(new int[]{1, 2, 3, 0, 2});
		System.out.printf("======> ans: %d\n", ans);

		int ans1 = new Solution().maxProfit(new int[]{2, 1, 4});
		System.out.printf("======> ans1: %d\n", ans1);

		int ans2 = new Solution().maxProfit(new int[]{1, 3, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1});
		System.out.printf("======> ans2: %d\n", ans2);
	}
}

