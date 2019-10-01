/**
 * 395	Longest Substring with At Least K Repeating Characters (39.2% Medium 875 78)
 * Performance: speed=61%, memory=100%
 */

/*
discuss:

This might help you to understand the algorithm:
For h=1:26, we are going to use sliding window (left i, right j) to find the "longest window which contains exactly h unique characters and for each character, there are at least K repeating ones".
For example, when h=3, K=5, we are going to find the longest window contains exactly 3 unique characters and each repeating 5 times.

*/

import java.util.*;

public class Solution {
	public int longestSubstring(String s, int k) {
		if (k <= 1) return s.length();
		int max = 0;
		for (int i = 1; i <= 26; ++i) max = Math.max(max, ls(s, i, k));
		return max;
	}

	int ls(String s, int n, int k) {
		int i = 0, j = 0, unq = 0, nok = 0, max = 0;
		int[] c = new int[26];
		while (j < s.length()) {
			// System.out.printf("i=%d, j=%d, unq=%d, nok=%d\n", i, j, unq, nok);
			if (++c[s.charAt(j)-97] == 1) ++unq;
			if (c[s.charAt(j++)-97] == k) ++nok; // attention: must be == k, dont use >= k
			while (unq > n) {
				if (--c[s.charAt(i)-97] == 0) --unq;
				if (c[s.charAt(i++)-97] == k-1) --nok; // attention: must be == k-1, dont use < k
			}
			if (unq == n && nok == n) max = Math.max(max, j-i);
		}
		return max;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.longestSubstring("", 0) == 0;
		assert s.longestSubstring("", 1) == 0;
		assert s.longestSubstring("a", 0) == 1;
		assert s.longestSubstring("a", 1) == 1;
		assert s.longestSubstring("a", 2) == 0;
		assert s.longestSubstring("aa", 0) == 2;
		assert s.longestSubstring("aa", 1) == 2;
		assert s.longestSubstring("aa", 2) == 2;
		assert s.longestSubstring("aa", 3) == 0;
		assert s.longestSubstring("aba", 1) == 3;
		assert s.longestSubstring("aba", 2) == 0;
		assert s.longestSubstring("cbcb", 2) == 4;
		assert s.longestSubstring("xxycbcb", 2) == 4;

		System.out.println("done");
	}
}

