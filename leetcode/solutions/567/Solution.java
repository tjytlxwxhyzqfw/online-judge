/**
 * 567 Permutation in String 39.3% Medium 788/42
 * Performance: speed=59%, memory=100%
 * Optimistic: dont check 26 times in each loop
 */

import java.util.*;

public class Solution {
	public boolean checkInclusion(String s1, String s2) {
		if (s1.length() > s2.length()) return false;

		int[] a = new int[26];
		for (int k = 0; k < s1.length(); ++k) ++a[s1.charAt(k)-97];
		for (int k = 0; k < s1.length(); ++k) --a[s2.charAt(k)-97];
		for (int i = 0; i + s1.length() < s2.length(); ++i) {
			if (clear(a)) return true;
			++a[s2.charAt(i)-97];
			--a[s2.charAt(i+s1.length())-97]; // fool: --a[s2.charAt(i+s1.length()-97)]
		}
		return clear(a);
	}

	boolean clear(int[] a) {
		for (int i = 0; i < a.length; ++i) if (a[i] != 0) return false;
		return true;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.checkInclusion("", "") == true;
		assert s.checkInclusion("", "a") == true;
		assert s.checkInclusion("a", "") == false;
		assert s.checkInclusion("ab", "whitezzz") == false;
		assert s.checkInclusion("whi", "whitezzz") == true;
		assert s.checkInclusion("zzz", "whitezzz") == true;
		assert s.checkInclusion("whitezzz", "whitezzz") == true;
		assert s.checkInclusion("tx", "whitezzz") == false;
                System.out.println("done");
	}
}

