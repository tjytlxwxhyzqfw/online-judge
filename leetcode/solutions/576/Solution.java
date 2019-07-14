/**
 * 0576: OutOfBoundaryPaths
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.List;

// dp[i][j][k] = dp[i+1][j][k-1] + dp[i-1][j][k-1] + dp[i][j-1][k-1] + dp[i][j+1][k+1] + n
// dp[i][y][x]
// 
// o o o o
// o o o o
// o o o o

public class Solution {
	private static int M = 1000000007;

	public int findPaths(int height, int width, int n, int by, int bx) {
		int[][] dp = new int[height][width];
		int[] up = new int[width], curr = new int[width];
		int left;

		for (int k = 1; k <= n; ++k) {
			for (int y = 0; y < height; ++y) {
				left = 0;
				for (int j = 0; j < width; ++j) curr[j] = dp[y][j];
				for (int x = 0; x < width; ++x) {
					int sum = (x-1 < 0 ? 1 : left);
					// System.out.printf("1: %d\n", sum);
					sum = (sum + (x+1 >= width ? 1 : dp[y][x+1])) % M;
					// System.out.printf("2: %d\n", sum);
					sum = (sum + (y-1 < 0 ? 1 : up[x])) % M;
					// System.out.printf("3: %d\n", sum);
					sum = (sum + (y+1 >= height ? 1 : dp[y+1][x])) % M;
					// System.out.printf("4: %d\n", sum);
					left = dp[y][x];
					dp[y][x] = sum;

					System.out.printf("k=%2d, y=%2d, x=%2d, dp=%2d\n", k, y, x, dp[y][x]);
				}
				up = curr;
			}
		}

		return dp[by][bx];
	}

	public static void main(String args[]) {
		// new Solution().findPaths(1, 3, 3, 0, 1);
		// new Solution().findPaths(2, 2, 2, 0, 0);
		new Solution().findPaths(1, 1, 100, 0, 0);
	}
}

