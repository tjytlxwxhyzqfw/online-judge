/**
 * 588 KightProbabilityInChessboard
 * Performance: speed=11%, memory=19%
 *
 * todo: why is my solution slower than others ?
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public double knightProbability(int n, int k, int r, int c) {
		double[][][] dp = new double[k+1][n][n];
		// int[][] nxt = new int[8][2];

		for (int y = 0; y < n; ++y) for (int x = 0; x < n; ++x) dp[0][y][x] = 1;
		for (int i = 1; i <= k; ++i) {
			for (int y = 0; y < n; ++y) {
				for (int x = 0; x < n; ++x) {
					if (y-2 >= 0) {
						if (x-1>=0) dp[i][y][x] += dp[i-1][y-2][x-1];
						if (x+1<n) dp[i][y][x] += dp[i-1][y-2][x+1];
					}
					if (y+2 < n) {
						if (x-1>=0) dp[i][y][x] += dp[i-1][y+2][x-1];
						if (x+1<n) dp[i][y][x] += dp[i-1][y+2][x+1];
					}
					if (x-2 >= 0) {
						if (y-1>=0) dp[i][y][x] += dp[i-1][y-1][x-2];
						if (y+1<n) dp[i][y][x] += dp[i-1][y+1][x-2];
					}
					if (x+2 < n) {
						if (y-1>=0) dp[i][y][x] += dp[i-1][y-1][x+2];
						if (y+1<n) dp[i][y][x] += dp[i-1][y+1][x+2];
					}
					dp[i][y][x] *= .125;
				}
			}
		}

		return dp[k][r][c];
	}

	private void move(int y, int x, int[][] nxt) {
		// ^<, ^>
		{ nxt[0][0] = y-2; nxt[0][1] = x-1; }
		{ nxt[1][0] = y-2; nxt[1][1] = x+1; }
		// <^, <v
		{ nxt[2][0] = y-1; nxt[2][1] = x-2; }
		{ nxt[3][0] = y+1; nxt[3][1] = x-2; }
		// >^, >v
		{ nxt[4][0] = y-1; nxt[4][1] = x+2; }
		{ nxt[5][0] = y+1; nxt[5][1] = x+2; }
		// v<, v>
		{ nxt[6][0] = y+2; nxt[6][1] = x-1; }
		{ nxt[7][0] = y+2; nxt[7][1] = x+1; }
	}

	public static void main(String args[]) {
		double r1 = new Solution().knightProbability(3, 3, 0, 0);
		System.out.printf("r1: %f\n", r1);
	}
}

