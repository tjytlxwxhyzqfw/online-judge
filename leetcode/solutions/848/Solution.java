/**
 * 848 Shifting Letters 42.5% 191/39
 * Performance: speed=43%, memory=100%
 */

// we don't need b[] if we construct the answer in reverse order
// even if in my current solution, long array is not necessary because sh[i] = (sh[i] % 26 + sh[i+1] % 26) % 26

import java.util.*;

public class Solution {
	public String shiftingLetters(String s, int[] sh) {
		if (sh.length == 0) return s;

		long[] b = new long[sh.length];
		b[b.length-1] = sh[b.length-1];
		for (int i = b.length-2; i >= 0; --i) b[i] = sh[i] + b[i+1];

		StringBuilder t = new StringBuilder(b.length);
		for (int i = 0; i < sh.length; ++i) {
			long index = 97 + (s.charAt(i) - 97 + b[i] % 26) % 26;
			t.append((char)index);
		}
		return t.toString();
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.shiftingLetters("", new int[]{}).equals("");
		assert s.shiftingLetters("a", new int[]{1}).equals("b");
		assert s.shiftingLetters("a", new int[]{26}).equals("a");
		assert s.shiftingLetters("a", new int[]{28}).equals("c");

		assert s.shiftingLetters("abc", new int[]{3, 5, 9}).equals("rpl");

		System.out.println("done");
	}
}

