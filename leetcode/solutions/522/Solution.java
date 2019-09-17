/**
 * 522	Longest Uncommon Subsequence II 33.1% Medium 119/505
 * Performance: speed=99%, memory=100%
 */

import java.util.*;

public class Solution {
	public int findLUSlength(String[] strs) {
		int n = -1;
		for (int i = 0; i < strs.length; ++i) {
			boolean ss = false;
			for (int j = 0; j < strs.length; ++j) {
				if (j == i) continue;
				if (subseq(strs[j], strs[i])) { ss = true; break; }
			}
			if (!ss && strs[i].length() > n) n = strs[i].length();
		}
		return n;
	}

	boolean subseq(String s, String t) {
		if (t.length() == 0) return true;
		if (s.length() < t.length()) return false;
		int k = 0;
		for (int i = 0; i < t.length(); ++i) {
			boolean found = false;
			for (; k < s.length(); ++k) if (found = (s.charAt(k) == t.charAt(i))) break;
			if (!found) return false;
			++k;  // fool: forget to add k after target found
		}
		return true;
	}
 
	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.findLUSlength(new String[]{}) == -1;
		assert s.findLUSlength(new String[]{""}) == 0;
		assert s.findLUSlength(new String[]{"abc", "abd", "abe"}) == 3;
		assert s.findLUSlength(new String[]{"aaa", "aaa", "aa"}) == -1;
		assert s.findLUSlength(new String[]{"abcde", "abcde", "ace"}) == -1;
		assert s.findLUSlength(new String[]{"abcde", "abcdf", "ace"}) == 5;
		assert s.findLUSlength(new String[]{"aabbcc", "aabbcc","cb","abc"}) == 2;
		assert s.findLUSlength(new String[]{"abcabc","abcabc","abcabc","abc","abc","cca"}) == 3;

		System.out.println("all test passed");
	}
}

