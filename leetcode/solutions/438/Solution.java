/**
 * 438 Find All Anagrams in a String
 * Performance: speed=77%, memory=100%
 *
 * very similiar to 424
 * todo: read the very short solution: https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92015/ShortestConcise-JAVA-O(n)-Sliding-Window-Solution
 */

import java.util.*;

public class Solution {
	public List<Integer> findAnagrams(String s, String p) {
		int[] a = new int[26], b = new int[26];
		for (int i = 0; i < p.length(); ++i) ++a[p.charAt(i)-(char)97];
		int n = 0, i = 0;
		List<Integer> list = new ArrayList<>();
		for (int j = 0; j < s.length(); ++j) {
			int ch = s.charAt(j) - (char)97;
			if (a[ch] == 0) { i = j+1; if (n > 0) Arrays.fill(b, 0); n = 0; continue; }
			{ ++b[ch]; ++n; }
			if (b[ch] > a[ch]) {
				for (; i < j; ++i) {
					int ch1 = s.charAt(i) - (char)97;
					if (b[ch1] > 0) { --b[ch1]; --n; }
					if (ch1 == ch) { ++i; break; }
				}
				continue;
			}
			if (n == p.length()) {
				list.add(i);
				{ --b[s.charAt(i)-(char)97]; ++i; --n; }
			}
		}
		return list;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.printf("list1: %s\n", s.findAnagrams("cbaebabacd", "abc")); // 0, 6
		System.out.printf("list2: %s\n", s.findAnagrams("aaaaa", "")); // []
		System.out.printf("list3: %s\n", s.findAnagrams("aaaaa", "a")); // 0, 1, 2, 3, 4
		System.out.printf("list4: %s\n", s.findAnagrams("aaaaa", "aa")); // 0, 1, 2, 3
		System.out.printf("list5: %s\n", s.findAnagrams("abacbabc", "abc")); // 1, 2, 3, 5
	}
}

