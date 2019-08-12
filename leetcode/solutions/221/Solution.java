/**
 * 221 Maximal Square
 * Performance: speed=%, memory=%
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public int maximalSquare(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
		int h = matrix.length, w = matrix[0].length, max = 0;
		int[][] lef = new int[h][w], top = new int[h][w], dp = new int[h][w];
		for (int i = 0; i < h; ++i) {
			for (int j = 0; j < w; ++j) {
				if (matrix[i][j] == '0') continue;
				lef[i][j] = 1 + (i-1 >= 0 ? lef[i-1][j] : 0);
				top[i][j] = 1 + (j-1 >= 0 ? top[i][j-1] : 0);
				// attention: dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
				dp[i][j] = 1;
				if (i-1 >= 0 && j-1 >= 0) {
					if (lef[i][j] > dp[i-1][j-1] && top[i][j] > dp[i-1][j-1]) dp[i][j] = dp[i-1][j-1]+1;
					else dp[i][j] = Math.min(lef[i][j], top[i][j]);
				}
				max = Math.max(max, dp[i][j]);
			}
		}
		return max * max;
	}

	public static void main(String args[]) {
	}
}

