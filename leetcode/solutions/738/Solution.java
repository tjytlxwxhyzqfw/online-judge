/**
 * 738 Monotone Increasing Digits 42.6% Medium 295/47
 * Performance: speed=94%, memory=20%
 */

import java.util.*;

/*
this solution can help to simplify the my solution: https://leetcode.com/problems/monotone-increasing-digits/discuss/109794/Simple-Python-solution-w-Explanation

simplification: you first find the highest digit that bigger than its left(higher) digit after decreasing then change all right (lower) digits to 9.
*/

public class Solution {
	public int monotoneIncreasingDigits(int n) {
		if (n < 10) return n;
		byte[] a = new byte[10];
		int p = 0;
		while (n != 0) {
			a[p++] = (byte)(n % 10);
			n /= 10;
		}
		int firstDesc = -1;
		for (int i = p-2; i >= 0; --i) if (a[i] < a[i+1]) { firstDesc = i; break; }
		for (int i = 0; i <= firstDesc; ++i) a[i] = 9;
		if (firstDesc != -1) {
			for (int i = firstDesc+1; i < p; ++i) {
				--a[i];
				if (i == p-1 || a[i] >= a[i+1]) break;
				a[i] = 9;
			}
		}
		for (int i = p-1; i >= 0; --i) n = n * 10 + a[i];
		return n;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.monotoneIncreasingDigits(0) == 0;
		assert s.monotoneIncreasingDigits(1) == 1;
		assert s.monotoneIncreasingDigits(9) == 9;
		assert s.monotoneIncreasingDigits(332) == 299;
		assert s.monotoneIncreasingDigits(25089) == 24999;
		assert s.monotoneIncreasingDigits(22089) == 19999;
		assert s.monotoneIncreasingDigits(123) == 123;
		System.out.println("done");
	}
}

