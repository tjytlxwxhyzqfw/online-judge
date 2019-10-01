/**
 * 390 Elimination Game
 * Performance: speed=21%, memory=33% 272/241
 *
 * discuss: similiar idea with most-votes solution, but mine is a little bit worse in impl
 */

import java.util.*;

public class Solution {
	public int lastRemaining(int n) {
		int k = 0, first = 1, last = n;
		while (first != last) {
			// System.out.printf("k=%d, first=%d, last=%d\n", k, first, last);
			// new Scanner(System.in).next();
			int intv = (1 << k++);
			if (k % 2 == 1) {
				if (((last-first)/intv) % 2 == 0) last -= intv;
				first += intv;
			} else {
				if (((last-first)/intv) % 2 == 0) first += intv;
				last -= intv; 
			}
		}
		return first;
	}

	// public int lastRemaining(int n) {
		// int k = 0; while (n / 2 > 0) { ++k; n /= 2; }
		// System.out.printf("k=%d\n", k);
		// long x = (1 << k);
		// for (int i = 1; i < k; i += 2) x -= (1 << i);
		// return (int)x;
	// }

	public static void main(String args[]) {
		assert new Solution().lastRemaining(1) == 1;
		assert new Solution().lastRemaining(31) == 16;
		assert new Solution().lastRemaining(32) == 22;
		assert new Solution().lastRemaining(33) == 22;
		assert new Solution().lastRemaining(34) == 24;
		assert new Solution().lastRemaining(2147483647) == 1073741824;
		assert new Solution().lastRemaining(2137473639) == 1064785784;

		System.out.println("done");
	}
}

