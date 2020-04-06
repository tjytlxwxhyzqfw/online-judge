/**
 * 
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public String longestPalindrome(String s) {
		if (s.length() == 0) return "";

		int endAt = 0;
		int maxLen = 1;
		int[] dp = new int[s.length()];
		dp[0] = 1;
		for (int i = 1; i < s.length(); ++i) {
			dp[i] = 1;
			if (s.charAt(i) == s.charAt(i-1)) dp[i] = 2;
			if (i>=2 && s.charAt(i-2) == s.charAt(i)) dp[i] = 3;
			int j = i - dp[i-1] - 1;
			if (j >= 0 && s.charAt(j) == s.charAt(i)) {
				// System.out.printf("j=%d, i=%d, %c=%c\n", j, i, s.charAt(j), s.charAt(i));
				dp[i] = dp[i-1] + 2;
			}
			if (dp[i] > maxLen) {
				maxLen = dp[i];
				endAt = i;
			}
			// System.out.printf("dp[%2d]=%2d, maxLen=%2d, endAt=%2d\n", i, dp[i], maxLen, endAt);
		}

		String s1 = s.substring(endAt-maxLen+1, endAt+1);
		String s2 = maxSameSubstr(s);
		return s1.length() > s2.length() ? s1 : s2;
	}

	String maxSameSubstr(String s) {
		int maxLen = 1, n = 1;
		int endAt = 0;
		for (int i = 1; i < s.length(); ++i) {
			if (s.charAt(i) == s.charAt(i-1)) {
				if (++n > maxLen) { maxLen = n; endAt = i; }
			} else {
				n = 1;
			}
		}
		return s.substring(endAt-maxLen+1, endAt+1);
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		System.out.println(s.longestPalindrome("ababababababa")); // this solution failed at this case
		System.out.println(s.longestPalindrome("aaabaaaa"));
		System.out.println(s.longestPalindrome("a"));
		System.out.println(s.longestPalindrome("aaa"));
		System.out.println(s.longestPalindrome("bananas"));
		System.out.println(s.longestPalindrome("aaaa"));
		System.out.println(s.longestPalindrome("aaabbb"));
		System.out.println(s.longestPalindrome("ksdds"));
		System.out.println(s.longestPalindrome("hdsfsdh"));
		System.out.println("done");
	}
}

