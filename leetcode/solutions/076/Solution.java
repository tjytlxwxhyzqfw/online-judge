import java.util.*;

// 3229/239 s=70 m=98

public class Solution {

	public String minWindow(String s, String t) {
		if (s == null || s.length() == 0 || t == null || t.length() == 0) return "";

		int[] b = new int[128], a = new int[128];
		for (int i = 0; i < t.length(); ++i) ++b[t.charAt(i)];

		int start = 0, end = Integer.MAX_VALUE;
		int i = 0, j = -1;
		while (j+1 < s.length()) {
			++a[s.charAt(++j)];
			while (contains(a, b)) {
				if (j - i < end - start) {
					start = i;
					end = j;
				}
				--a[s.charAt(i++)];
			}
			// [i, j] is not valid
		}
		return end == Integer.MAX_VALUE ? "" : s.substring(start, end+1);
	}

	boolean contains(int[] a, int[] b) {
		for (int i = 0; i < a.length; ++i) {
			if (a[i] < b[i]) return false;
		}
		return true;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.minWindow("", "").equals("");
		assert s.minWindow("", "123").equals("");
		assert s.minWindow("abc", "").equals("");

		assert s.minWindow("xyz", "x").equals("x");
		assert s.minWindow("xyz", "y").equals("y");
		assert s.minWindow("xyz", "z").equals("z");
		assert s.minWindow("xyz", "xz").equals("xyz");

		assert s.minWindow("xyzaaaxzbbb", "xz").equals("xz");
		assert s.minWindow("ADOBECODEBANC", "ABC").equals("BANC");
		assert s.minWindow("ADOBECODEBANC", "XYZ").equals("");

		System.out.println("done");
	}
}
