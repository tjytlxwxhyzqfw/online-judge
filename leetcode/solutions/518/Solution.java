/**
 * 518 Coin Change 2 43.8% Medium
 * Performance: speed=8%, memory=9%
 * todo: read discuss
 */

import java.util.*;

public class Solution {
	public int change(int amount, int[] coins) {
		if (amount == 0) return 1;
		if (coins == null || coins.length == 0) return 0;

		Arrays.sort(coins);
		int[][] dp = new int[amount+1][coins.length];

		for (int i = 0; i <= amount; ++i) {
			dp[i][0] = (i % coins[0] == 0 ? 1 : 0);
			// System.out.printf("(%2d, %2d) => %d\n", i, coins[0], dp[i][0]);
			for (int j = 1; j < coins.length; ++j) {
				dp[i][j] = dp[i][j-1];
				// --optimistic: this for-loop can be replaced by some o(1) statements.
				for (int r = i - coins[j]; r >= 0; r -= coins[j]) dp[i][j] += dp[r][j-1];
				// System.out.printf("(%2d, %2d) => %d\n", i, coins[j], dp[i][j]);
			}
		}

		return dp[amount][coins.length-1];
	}

	public static void main(String args[]) {
		Solution s = new Solution();


		assert s.change(2, new int[]{1, 2}) == 2;
		assert s.change(0, new int[]{}) == 1;
		assert s.change(0, new int[]{1}) == 1;
		assert s.change(1, new int[]{}) == 0;
		assert s.change(1, new int[]{1}) == 1;
		assert s.change(2, new int[]{1}) == 1;
		assert s.change(5, new int[]{1, 2, 5}) == 4;

		System.out.println("all test passed");
	}
}

