/**
 * 10 Regular Expression Matching 3541/626
 * Performance: speed=93%, memory=47%
 */

import java.util.*;

public class Solution {
	public boolean isMatch(String s, String p) {
		/// int[] everMatched = new int[p.length()+1];
		// Arrays.fill(everMatched, -1);
		// everMatched[0] = 0;

		boolean[][] dp = new boolean[s.length()+1][p.length()+1];
		dp[0][0] = true;
		for (int i = 1; i <= s.length(); ++i) dp[i][0] = false;
		for (int j = 1; j <= p.length(); ++j) {
			if (!dp[0][j-1]) {
				dp[0][j] = false;
				continue;
			}
			if (p.charAt(j-1) != '*' && (j >= p.length() || j < p.length() && p.charAt(j) != '*')) {
				dp[0][j] = false;
				continue;
			}
			// System.out.printf("dp[0][%d]=true\n", j);
			dp[0][j] = true;
			// everMatched[j] = 0;
		}

		int n = 1;
		for (int i = 1; i <= s.length(); ++i) {
			char sc = s.charAt(i-1);
			n = (i-2 >= 0 && s.charAt(i-2) == sc ? n+1 : 1);
			for (int j = 1; j <= p.length(); ++j) {
				char pc = p.charAt(j-1);
				if (pc == '*') {
					assert j >= 2; // there must be sth preceeding a '*';

					// * > 0, p=xaaaa, s=xa*
					// case: s=aa, p=a******** => impossible, must no continus *
					char ppc = p.charAt(j-2);
					// if (ppc == '.') dp[i][j] = everMatched[j-2] >= 0;
					// if (ppc == sc) dp[i][j] = everMatched[j-2]  >= i-n;
					if (ppc == sc || ppc == '.') dp[i][j] = dp[i-1][j];

					// * = 0
					if(dp[i][j-2]) { dp[i][j] = true; }
				} else if (pc == '.' || pc == sc) dp[i][j] = dp[i-1][j-1];
				// if (dp[i][j]) everMatched[j] = i;
				// System.out.printf("dp[%d][%d]=%s\n", i, j, dp[i][j]);
				// System.out.printf("em[%d]=%d\n", j, everMatched[j]);
			}
		}

		return dp[s.length()][p.length()];
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		// true
		assert s.isMatch("", "");
		assert s.isMatch("", "c*");
		assert s.isMatch("", ".*");
		assert s.isMatch("", "c*b*.*a*x*");
		assert s.isMatch("", "c*b*.*a*x*");
		assert !s.isMatch("", "a");
		assert !s.isMatch("", "a*x");
		assert !s.isMatch("", "a*xb*");

		assert !s.isMatch("aa", "a");
		assert !s.isMatch("a", "aa");
		assert s.isMatch("aa", "a*");
		assert s.isMatch("ab", "abc*");
		assert s.isMatch("abc", ".*");
		assert s.isMatch("abc", "a.*");
		assert !s.isMatch("abc", "x.*");
		assert s.isMatch("aab", "c*a*b");
		assert !s.isMatch("mississippi", "mis*is*p*.");

		// wa case 1: s=aaa, p=ab*a*c*a
		assert s.isMatch("aa", "c*a*");
		assert s.isMatch("a", "ab*a*");
		assert s.isMatch("aa", "ab*a*");
		assert s.isMatch("aaa", "ab*a*");
		assert s.isMatch("aaaaaaa", "ab*a*");
		assert s.isMatch("aa", "ab*a*c*");
		assert s.isMatch("aaa", "ab*a*c*a");

		System.out.println("done");
	}
}
