/**
 * 392: Is Subsequence
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public boolean isSubsequence(String s, String t) {
		if (s.equals(t)) return true;

		int j = 0;
		for (int i = 0; i < s.length(); ++i) {
			j = firstFrom(s.charAt(i), j, t) + 1;
		}

		// if you plus one to j, you must be careful with the boundaries
		return j <= t.length();
	}

	private int firstFrom(char x, int from, String s) {
		for (int i = from; i < s.length(); ++i)
			if (s.charAt(i) == x) return i;
		return s.length();
	}

	public static void main(String args[]) {
	}
}

