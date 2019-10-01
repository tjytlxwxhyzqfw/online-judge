/**
 * 556 Next Greater Element III 30.3% Medium
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public int nextGreaterElement(int n) {
		int[] a = new int[10];
		int len = 0;
		while (n != 0) {
			a[len++] = n % 10;
			n /= 10;
		}
		if (len == 0) len = 1;

		int p = a.length, q = a.length;
		for (int i = 0; i < len; ++i) {
			for (int j = i+1; j < len; ++j) {
				if (a[i] > a[j] && j < p || (j == p && a[i] < a[q])) { p = j; q = i; }
			}
		}

		if (p == a.length) return -1;

		// for (int i = 0; i < len; ++i) System.out.printf("%d,", a[i]);
		// System.out.printf(" => p=%d, q=%d\n", p, q);

		{ int t = a[p]; a[p] = a[q]; a[q] = t; }
		// Arrays.sort(a, 0, p);
		long m = 0;
		for (int i = len-1; i >= p; --i) m = m * 10 + a[i];
		while (true) {
			int j = -1;
			for (int i = 0; i < p; ++i) if (j == -1 || a[i] < a[j]) j = i;
			if (j == -1 || a[j] == Integer.MAX_VALUE) break;
			m = m * 10 + a[j];
			a[j] = Integer.MAX_VALUE;
		}
		return m <= Integer.MAX_VALUE ? (int)m : -1;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.nextGreaterElement(0) == -1;
		assert s.nextGreaterElement(1) == -1;
		assert s.nextGreaterElement(21) == -1;
		assert s.nextGreaterElement(12) == 21;
		assert s.nextGreaterElement(Integer.MAX_VALUE) == -1;
		assert s.nextGreaterElement(132) == 213;
		assert s.nextGreaterElement(214786) == 214867;
		assert s.nextGreaterElement(100) == -1;
		assert s.nextGreaterElement(1200) == 2001;

		System.out.println("done");
	}
}
