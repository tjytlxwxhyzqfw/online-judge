/**
 * 1011: Capacity To Ship Packages Within D Days
 * Performance: speed=97%, memory=77%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int shipWithinDays(int[] weights, int D) {
		int min = Integer.MAX_VALUE;
		int i = 1, j = Integer.MAX_VALUE;
		while (i < j) {
			int k = i + (j - i) / 2;
			System.out.printf("k=%d\n", k);
			if (days(weights, k) <= D) min = j = k;
			else i = k + 1;
		}
		return min;
	}

	private int days(int[] a, int c) {
		int d = 0;
		int current = 0;
		for (int i = 0; i < a.length; ++i) {

			// !!!WA!!! if this line commented
			// case: weights = [1,2,3,1,1], D = 4
			if (a[i] > c) return Integer.MAX_VALUE;

			current += a[i];
			if (current > c) {
				++d;
				current = a[i];
			}
		}
		System.out.printf("days(c=%d): %d\n", c, d + 1);
		return ++d;
	}

	public static void main(String args[]) {
		new Solution().shipWithinDays(new int[]{1, 2, 3, 1, 1}, 4);
	}
}

