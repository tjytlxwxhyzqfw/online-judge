/**
 * 809 Expressive Words 45% Medium 134/426
 * Performance: speed=74%, memory=100%
 */

import java.util.*;

public class Solution {
	public int expressiveWords(String s, String[] words) {
		if (words == null || words.length == 0) return 0;
		int n = 0;
		for (String w : words) if (isStretchy(w, s)) ++n;
		return n;
	}

	// is s a stretchy word of t ?
	boolean isStretchy(String s, String t) {
		int i = 0, j = 0, p = 0, q = 0;
		while (true) {
			if (i == s.length()) return (j == t.length() && j >= i);
			if (j == t.length()) return (i == s.length() && j >= i);
			if (s.charAt(i) != t.charAt(j)) return false;

			while (i == p || (i < s.length() && s.charAt(i) == s.charAt(i-1))) ++i;
			while (j == q || (j < t.length() && t.charAt(j) == t.charAt(j-1))) ++j;
			int ns = i - p, nt = j - q;
			if (ns != nt && nt < 3) return false;

			p = i;
			q = j;
		}
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.expressiveWords("", new String[]{}) == 0;
		assert s.expressiveWords("", new String[]{"a", "", ""}) == 2;
		assert s.expressiveWords("a", new String[]{}) == 0;

		assert s.expressiveWords("a", new String[]{"a"}) == 1;
		assert s.expressiveWords("aa", new String[]{"a"}) == 0;
		assert s.expressiveWords("aaa", new String[]{"a", "aa", "aaa"}) == 3;
		assert s.expressiveWords("a", new String[]{"aaa"}) == 0;

		assert s.expressiveWords("abcd", new String[]{"abcd", "abc", "a"}) == 1;

		System.out.println("done");
	}
}

