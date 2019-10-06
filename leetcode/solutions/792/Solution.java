/**
 * 792	Number of Matching Subsequences 44.9% Medium 562/44
 * Performance: speed=95%, memory=100%
 */

import java.util.*;

public class Solution {
	public int numMatchingSubseq(String s, String[] ws) {
		int[][] table = new int[26][ws.length];
		int[] len = new int[26];
		for (int i = 0; i < ws.length; ++i) {
			int k = ws[i].charAt(0) - 97;
			table[k][len[k]++] = i;
		}

		int n = 0;
		int[] p = new int[ws.length];
		for (int i = 0; i < s.length(); ++i) {
			int k = s.charAt(i) - 97;
			// System.out.printf("char=%c, len=%d\n", (char)(k+'a'),  len[k]);
			int size = len[k];
			len[k] = 0;
			for (int j = 0; j < size; ++j) {
				int id = table[k][j];
				// System.out.printf("\t=> %s\n", ws[id]);
				++p[id];
				if (p[id] == ws[id].length()) ++n;
				else {
					int k1 = ws[id].charAt(p[id]) - 97;
					table[k1][len[k1]++] = id;
				}
			}
		}

		return n;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.numMatchingSubseq("abc", new String[]{"ac", "ab", "bc", "abc"}) == 4;

		assert s.numMatchingSubseq("a", new String[]{"a"}) == 1;
		assert s.numMatchingSubseq("a", new String[]{"b"}) == 0;
		assert s.numMatchingSubseq("a", new String[]{"b", "a", "c"}) == 1;
		assert s.numMatchingSubseq("abc", new String[]{"b", "a", "c"}) == 3;
		assert s.numMatchingSubseq("abc", new String[]{"ac"}) == 1;
		assert s.numMatchingSubseq("abc", new String[]{"aac", "acc", "ba", "ca", "bbc", "abb", "aab"}) == 0;
		assert s.numMatchingSubseq("abcde", new String[]{"a", "bb", "acd", "ace"}) == 3;
		System.out.println("done");
	}
}
