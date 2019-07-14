/**
 * 0647: PalindromicSubstrings
 * Performance: speed=37%, memory=75%
 *
 * todo: opt
 *
 * my solution: dp
 * ---------------
 * dp[i][j] = dp[i+1][j-1] and s[i]==s[j-1]
 * like the longest palindromic substring problem
 *
 * another solution: extend palindrome:
 * ------------------------------------
 * i is mid, then extend to (i-1, i+1), (i-2, i+2),
 * [i][i+1] is mid, then extend to (i-1, i+2) (i-2, i+3),
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int countSubstrings(String s) {
		if (s == null || s.length() == 0) return 0;
		boolean[][] dp = new boolean[s.length()][s.length()+1];
		for (int i = s.length()-1; i >= 0; --i) {
			dp[i][i+1] = true;
			if (i+2 <= s.length()) dp[i][i+2] = (s.charAt(i) == s.charAt(i+1));
			for (int j = i+3; j <= s.length(); ++j) dp[i][j] = (dp[i+1][j-1] && (s.charAt(i) == s.charAt(j-1)));
		}
		int count = 0;
		for (int y = 0; y < dp.length; ++y) for (int x = 0; x < dp[0].length; ++x) count += (dp[y][x] ? 1 : 0);
		return count;
    	}

	public static void main(String args[]) {
		int r1 = new Solution().countSubstrings("xy");
		System.out.printf("r1: %d\n", r1);
	}
}

