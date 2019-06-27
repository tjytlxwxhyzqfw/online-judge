/**
 * 322 Coin Change
 * Performance: speed=53%, memory=99%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int coinChange(int[] coins, int amount) {
		int[] dp = new int[amount+1];
		for (int i = 0; i < dp.length; ++i) dp[i] = -1;
		dp[amount] = 0;
		for (int i = amount; i >= 0; --i) {
			System.out.printf("S -> %3d: %d\n", i, dp[i]);
			if (dp[i] == -1) continue;  // !!!IMPORTANT!!!
			for (int j = 0; j < coins.length; ++j) {
				int k = i - coins[j];
				if (k >= 0) {
					if (dp[k] == -1) dp[k] = Integer.MAX_VALUE;  // !!!ATTENTION!!!
					dp[k] = Math.min(dp[k], 1+dp[i]);
				}
				//System.out.printf("\t: update %3d\n", k);
			}
		}
		return dp[0];
	}
	public static void main(String args[]) {
		new Solution().coinChange(new int[]{2, 3}, 11);
	}
}

