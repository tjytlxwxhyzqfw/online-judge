/**
 * 0712: MinimumASCIIDeleteSumForTwoStrings
 *
 * Performance: speed=%, memory=%
 *
 * todo: try to opt space
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {
	public int minimumDeleteSum(String s1, String s2) {
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		dp[0][0] = 0;
		for (int i = 1; i <= s1.length(); ++i) dp[i][0] = (int)s1.charAt(i-1) + dp[i-1][0];
		for (int j = 1; j <= s2.length(); ++j) dp[0][j] = (int)s2.charAt(j-1) + dp[0][j-1];
		for (int i = 1; i <= s1.length(); ++i) {
			for (int j = 1; j <= s2.length(); ++j) {
				dp[i][j] = dp[i-1][j-1];
				if (s1.charAt(i-1) != s2.charAt(j-1)) {
					dp[i][j] = Math.min(dp[i-1][j]+(int)s1.charAt(i-1), dp[i][j-1]+(int)s2.charAt(j-1));
				}
			}
		}

		return dp[s1.length()][s2.length()];
	}
	public static void main(String args[]) {
		int r1 = new Solution().minimumDeleteSum("delete", "leet");
		System.out.printf("r1=%d\n", r1);
	}
}

