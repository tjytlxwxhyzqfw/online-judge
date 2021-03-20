/**
 * 813 redo 2021-03-20 
 */

import java.util.*;

public class Solution {
	public double largestSumOfAverages(int[] a, int k) {
		double dp[][] = new double[a.length][k];
		double sum[][] = new double[a.length][k];
		int cnt[][] = new int[a.length][k];

		dp[0][0] = sum[0][0] = a[0];
		cnt[0][0] = 1;
		for (int i = 1; i < a.length; ++i) {
			sum[i][0] = sum[i-1][0] + a[i];
			cnt[i][0] = i+1;
			dp[i][0] = sum[i][0] / cnt[i][0];

			for (int j = 1; j < Math.min(i+1, k); ++j) {
				sum[i][j] = a[i];
				cnt[i][j] = 1;
				dp[i][j] = dp[i-1][j-1] + sum[i][j] / cnt[i][j];

				double oldVal = sum[i-1][j]/cnt[i-1][j];
				double newVal = (sum[i-1][j]+a[i]) / (cnt[i-1][j] + 1);
				double newSum = dp[i-1][j] - oldVal + newVal;
				if (newSum > dp[i][j]) {
					dp[i][j] = newSum;
					sum[i][j] = sum[i-1][j] + a[i];
					cnt[i][j] = cnt[i-1][j] + 1;
				}

				System.out.printf("(%2d, %2d): dp=%g, sum=%g, cnt=%d\n", i, j, dp[i][j], sum[i][j], cnt[i][j]);
			}
		}

		double result = 0;
		for (int i = 0; i < k; ++i) {
			result = Math.max(result, dp[a.length-1][i]);
		}
		return result;

	}

	public static void main(String args[]) {
		Solution s = new Solution();
		double result = s.largestSumOfAverages(new int[]{3, 1, 4, 1, 5, 9}, 4);
		System.out.println(result);
		System.out.println("done");

		/*
		int a[] = new int[]{2561,9087,398,8137,7838,7669,8731,2460,1166,619};
		double x = 0, y = 0, z = 0;
		for (int i = 0; i < a.length-2; ++i) {
			x += a[i];
			y = 0;
			for (int j = i+1; j < a.length-1; ++j) {
				y += a[j];
				z = 0;
				for (int k = j+1; k < a.length; ++k) {
					z += a[k];
				}
				double r = x/(i+1) + y/(j-i) + z/(a.length-j-1);
				System.out.printf("%2d, %2d: %g (x=%g (%d), y=%g (%d), z=%g (%d))\n",
					i, j, r, x, i+1, y, j-i, z, a.length-j-1); 
			}
		}*/
	}
}

