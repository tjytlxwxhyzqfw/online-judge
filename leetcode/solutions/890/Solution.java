/**
 * 890 Find and Replace Pattern 72.0% Medium 489/51
 * Performance: speed=100%, memory=100%
 */

// abac -> 1111
// abcd -> 1111

import java.util.*;

public class Solution {
	public List<String> findAndReplacePattern(String[] words, String pattern) {
		List<String> result = new ArrayList<>();
		int[] p = new int[pattern.length()], w = new int[pattern.length()];
		doMap(pattern, p);
		printA(p);
		for (int i = 0; i < words.length; ++i) {
			doMap(words[i], w);
			printA(w);
			if (equal(p, w)) result.add(words[i]);
		}
		return result;
	}

	void printA(int[] a) {
		for (int i = 0; i < a.length; ++i) System.out.printf("%d,", a[i]);
		System.out.println();
	}

	void doMap(String s, int[] out) {
		int next = 1;
		int[] used = new int[128];
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (used[c] == 0) used[c] = next++;
			out[i] = used[c];
		}
	} 

	boolean equal(int[] p, int[] w) {
		for (int i = 0; i < p.length; ++i) if (p[i] != w[i]) return false;
		return true;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		s.findAndReplacePattern(new String[]{"abc","4((","mee","aqq","dkd","ccc"}, "*&&");
		System.out.println("done");
	}
}

