/**
 * 0516 LongestPalindromicSubsequence
 * Performance: speed=97%, memory=100%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int longestPalindromeSubseq(String s) {
		if (s == null || s.length() == 0) return 0;
		int[] dp = new int[s.length()+1];

		for (int i = s.length(); i >= 0; --i) {
			int leftbot = 0;
			for (int j = i+1; j <= s.length(); ++j) {
				int leftbot1 = dp[j];
				if (s.charAt(i) == s.charAt(j-1)) dp[j] = (i==j-1?1:2) + leftbot;
				else dp[j] = Math.max(dp[j], dp[j-1]);
				leftbot = leftbot1;
				System.out.printf("i=%2d, j=%2d, dp=%2d\n", i, j, dp[j]);
			}
		}
		return dp[s.length()];
	}
	public static void main(String args[]) {
		int ans = new Solution().longestPalindromeSubseq("axaya");
		System.out.printf("ans=%d\n", ans);
	}
}

