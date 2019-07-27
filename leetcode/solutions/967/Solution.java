/**
 * 967: NumbersWithSameConsecutiveDifferences
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Solution {
	public int[] numsSameConsecDiff(int n, int k) {
		int[] a = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		int p = 10;
		if (n == 1 && k == 0) return a;

		for (int i = 1; i < n; ++i) {
			int[] b = new int[2048];
			p = 0;
			for (int j = 0; j < a.length; ++j) {
				if (a[j] == 0) continue;
				int x = a[j] % 10;

				// |x-?| = k
				// (1) x-? = k  => ? = x - k
				// (2) x-? = -k => ? = x + k

				// ? - x = k
				int y = x + k;
				if (y < 10) b[p++] = a[j]*10 + y;

				int z = x - k;
				if (z >= 0 && z != y) b[p++] = a[j]*10 + z;
			}
			a = b;
		}

		return Arrays.copyOfRange(a, 0, p);
	}

	public static void main(String args[]) {
		int[] ans = new Solution().numsSameConsecDiff(2, 1);
		for (int i = 0; i < ans.length; ++i) System.out.printf("%d, ", ans[i]);
		System.out.printf("\n");
	}
}

