/**
 * 931: MinimumFallingPathSum
 * Performance: speed=38%, memory=62%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int minFallingPathSum(int[][] a) {
		int height = a.length, width = a[0].length;
		int dp[][] = new int[height][width];
		for (int x = 0; x < width; ++x) dp[0][x] = a[0][x];
		for (int y = 1; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				dp[y][x] = dp[y-1][x];
				if (x-1 >= 0) dp[y][x] = Math.min(dp[y][x], dp[y-1][x-1]);
				if (x+1 < width) dp[y][x] = Math.min(dp[y][x], dp[y-1][x+1]);
				dp[y][x] += a[y][x];
			}
		}

		int min = dp[height-1][0];
		for (int x = 1; x < width; ++x) min = Math.min(min, dp[height-1][x]);
		return min;
	}

	public static void main(String args[]) {
		int r1 = new Solution().minFallingPathSum(new int[][]{
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, -9}});
		System.out.printf("r1=%d\n", r1);
	}
}

