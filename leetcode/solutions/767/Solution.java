/**
 * 767 Reorganize String 44.1% Medium 706/36
 * Performance: speed=92%, memory=85%
 * todo: use a heap to generate the final string
 */

import java.util.*;

public class Solution {
	public String reorganizeString(String s) {
		int[] cnt = new int[26];
		int maxCnt = 0, maxDup = 0;
		for (int i = 0; i < s.length(); ++i) {
			int idx = s.charAt(i) - 97;
			++cnt[idx];
			if (cnt[idx] > maxCnt) {
				maxCnt = cnt[idx];
				maxDup = 1;
			} else if (cnt[idx] == maxCnt) {
				++maxDup;
			}
		}
		// maxDup: how many chars whose cnt == maxCnt
		// if maxDup > 1, there must be a way to arrange all chars
		if (maxDup == 1 && (maxCnt-1) > (s.length() - maxCnt)) return "";

		StringBuilder b = new StringBuilder();
		while (true) {
			// put a group of chars with max count
			for (int i = 0; i < cnt.length; ++i) if (cnt[i] == maxCnt) { b.append((char)(i+97)); }
			// put exactly one other char
			for (int i = 0; i < cnt.length; ++i) {
				if (0 < cnt[i] && cnt[i] < maxCnt) { b.append((char)(i+97)); --cnt[i]; break; }
			}
			if (--maxCnt == 0) break;
			for (int i = 0; i < cnt.length; ++i) { if (cnt[i] > maxCnt) --cnt[i]; }
		}
		return b.toString();
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

