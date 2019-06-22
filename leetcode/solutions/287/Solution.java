/**
 * 287 Find the duplicate number
 * Performance: speed=%, memory=%
 *
 * bit ops is not good for this problem:
 *
 * 111
 * 10101101
 * 10101011
 * 00000110
 * 10101101
 * 10101011
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int findDuplicate(int[] nums) {
		int i = 1, j = nums.length;
		while (i < j) {
			int k = (i + j) / 2;

			// [i, k)
			int c = count(i, k, nums);
			if (c > k - i) {
				// the duplicate number is located in [i, k)
				j = k;
			} else {
				// the duplicate number is located in [k, j)
				i = k;
			}

			// this line eliminates an infinate while-loop
			if (i + 1 == j) return i;
		}
		return i;
	}

	private int count(int left, int rght, int[] a) {
		if (left >= rght) return 0;

		int count = 0;
		for (int i = 0; i < a.length; ++i) {
			if (left <= a[i] && a[i] < rght) {
				++count;
			}
		}

		return count;
	}

	public static void main(String args[]) {
		int[] a1 = {1, 3, 4, 2, 2};
		int r1 = new Solution().findDuplicate(a1);
		System.out.printf("r1=%d\n", r1);

		int[] a2 = {3, 1, 3, 4, 2};
		int r2 = new Solution().findDuplicate(a2);
		System.out.printf("r2=%d\n", r2);
	}
}

