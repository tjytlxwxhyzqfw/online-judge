/**
 * 791 Custom Sort String 63.3% Medium 470/144
 * Performance: speed=60%, memory=100%
 */

import java.util.*;

public class Solution {
	public String customSortString(String s, String t) {
		int[] cnt = new int[26];
		for (int i = 0; i < t.length(); ++i) ++cnt[t.charAt(i)-97];
		StringBuilder b = new StringBuilder();
		boolean[] ins = new boolean[26]; // optimistic: ins is not necessary, you can continue use cnt[]
		for (int i = 0; i < s.length(); ++i) {
			int idx = s.charAt(i) - 97;
			ins[idx] = true;
			while (cnt[idx] -- > 0) b.append((char)(idx+97));
		}
		for (int i = 0; i < t.length(); ++i) {
			if (!ins[t.charAt(i)-97]) b.append(t.charAt(i));
		}
		return b.toString();
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.customSortString("", "").equals("");
		assert s.customSortString("", "iloveyou").equals("iloveyou");
		assert s.customSortString("abc", "").equals("");
		assert s.customSortString("cba", "abcd").equals("cbad");
		assert s.customSortString("cba", "abcdabc").equals("ccbbaad");
		System.out.println("done");
	}
}

