/**
 * 400 Nth Digit
 * Performance: speed=100%, memory=10%
 */

import java.util.*;

public class Solution {
	public int findNthDigit(int n) {
		long i = 1, j = (long)Integer.MAX_VALUE + 1;
		System.out.printf("j=%d\n", j);
		while (i < j) {
			int k = (int)(i + (j - i) / 2);
			long d = ndigits(k);
			System.out.printf("=> k=%d, d=%d\n", k, d);
			if (d < n) i = k + 1;
			else j = k;
		}
		long d = ndigits((int)i);

		while (d != n) {
			i /= 10;
			--d;
		}
		return (int)(i % 10);
	}

	long ndigits(int n) {
		int k = 0, orig = n;
		while (n != 0) {
			n /= 10;
			++k;
		}

		long s = 0, i = 1;
		for (int m = 1; m <= k; ++m) { s += m * (i * 10 - i); i *= 10; }

		s -= (i - 1 - orig) * k;
		return s;
	}

	public static void main(String args[]) {
		new Solution().findNthDigit(90);
	}
}

