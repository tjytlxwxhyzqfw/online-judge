/**
 * 808 Soup Servings 38.3% Medium 111/389
 * Performance: speed=6%, memory=16%
 */

import java.util.*;

public class Solution {
	public double soupServings(int n) {
		if (n > 5000) return 1;
		double[][] dp = new double[n+1][n+1];
		dp[0][0] = .5;
		for (int i = 1; i <= n; ++i) dp[i][0] = 0;
		for (int j = 1; j <= n; ++j) dp[0][j] = 1;
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= n; ++j) {
				dp[i][j] = .25 * (
					dp[Math.max(i-100, 0)][j] +
					dp[Math.max(i-75, 0)][Math.max(j-25, 0)] +
					dp[Math.max(i-50, 0)][Math.max(j-50, 0)] +
					dp[Math.max(i-25, 0)][Math.max(j-75, 0)]);
			}
		}
		return dp[n][n];
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.soupServings(50) == .625;
		assert s.soupServings(0) == .5;
		assert s.soupServings(1) == .625;
		System.out.println("done");
	}
}

