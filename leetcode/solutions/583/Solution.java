/**
 * 583 Delete Operation for Two Strings 45.8% Medium 783/21
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public int minDistance(String s, String t) {
		int[][] dp = new int[s.length()+1][t.length()+1];
		for (int i = 0; i <= s.length(); ++i) {
			for (int j = 0; j <= t.length(); ++j) {
				if (i == 0) { dp[i][j] = j; continue; }
				if (j == 0) { dp[i][j] = i; continue; }

				if (s.charAt(i-1) == t.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
				else {
					dp[i][j] = 2 + dp[i-1][j-1];
					dp[i][j] = Math.min(dp[i][j], 1 + dp[i][j-1]);
					dp[i][j] = Math.min(dp[i][j], 1 + dp[i-1][j]);
				}
			}
		}
		return dp[s.length()][t.length()];
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.minDistance("", "") == 0;
		assert s.minDistance("abc", "") == 3;
		assert s.minDistance("", "xyzw") == 4;
		assert s.minDistance("sea", "eat") == 2;
                System.out.println("done");
	}
}

