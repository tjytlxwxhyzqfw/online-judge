/**
 * 813: LargestSumOfAverages
 * Performance: speed=21%, memory=100%
 * (speed could be improved to 37% by using `double[] ps`)
 *
 * hard: it is not easy to cal dp[i] with dp[i-1];
 *
 * how to indicate the dp equation ? 
 * (1) use dfs; (2) (this one) use brute force.
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public double largestSumOfAverages(int[] a, int k) {
		if (a.length == 0) return 0;

		int[] ps = new int[a.length+1]; for (int i = 1; i <= a.length; ++i) ps[i] = ps[i-1] + a[i-1];
		double[][] dp = new double[a.length][k];
		dp[0][0] = a[0];
		for (int i = 1; i < a.length; ++i) {
			dp[i][0] = (dp[i-1][0] * i + a[i]) / (i+1);
			System.out.printf("i=%2d, j=%2d, dp=%f\n", i, 0, dp[i][0]);
			for (int j = 1; j < k && j <= i; ++j) {
				for (int m = 1; m <= i; ++m) {
					// fool: missing `1.0 *` before cal `()/m`
					dp[i][j] = Math.max(dp[i][j], dp[i-m][j-1] + 1.*(ps[i+1]-ps[i-m+1])/m);
				}
				System.out.printf("i=%2d, j=%2d, dp=%f\n", i, j, dp[i][j]);
			}
		}

		return dp[a.length-1][k-1];
	}

	public static void main(String args[]) {
		double r1 = new Solution().largestSumOfAverages(new int[]{4, 1, 7, 5, 6, 3}, 4);
		System.out.printf("%f\n", r1);
	}
}

