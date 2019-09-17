/**
 * 390 Elimination Game
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public int lastRemaining(int n) {
		int k = 0; while (n / 2 > 0) { ++k; n /= 2; }
		System.out.printf("k=%d\n", k);
		long x = (1 << k);
		for (int i = 1; i < k; i += 2) x -= (1 << i);
		return (int)x;
	}

	public static void main(String args[]) {
		int x1 = new Solution().lastRemaining(31);
		System.out.printf("x1=%d\n", x1);
	}
}

