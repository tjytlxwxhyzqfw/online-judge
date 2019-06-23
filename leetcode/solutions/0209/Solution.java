/**
 * 209 Minimum Size Subarray Sum
 * Porfmance: 13%, 99%
 *
 * Once a array is converted to a prefix-sum, there
 * is a chance to use binary search.
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	private int bsearch(int x, int[] arr) {
		int i = 0, j = arr.length - 1;
		while (i <= j) {
			int k = (i + j) / 2;
			if (arr[k] < x) {
				i = k + 1;
			} else if (arr[k] > x) {
				j = k - 1;
			} else {
				return k;
			}
		}
		// a[j] < x < a[i]
		return j;
	}

	private int bsearch1(int x, int[] arr) {
		int i = 0, j = arr.length;
		while (i < j) {
			int k = (i + j) / 2;
			if (arr[k] <= x) {
				i = k + 1;
			} else {
				j = k;
			}
		}
		// i=j and a[i-1] <= x < a[i]
		return i;
	}

	public int minSubArrayLen(int s, int[] nums) {
		if (nums.length == 0) {
			return 0;
		}

		int[] sums = new int[nums.length];
		int min = 0, start = nums.length;
		for (int i = 0; i < nums.length; ++i) {
			sums[i] = nums[i];
			if (i > 0) {
				sums[i] += sums[i-1];
			}
			if (sums[i] >= s && i < start) {
				start = i;
				min = start + 1;
			}
		}
		if (min == 0) {
			return 0;
		}

		// ------ use bsearch ------
		// for (int i=start; i < sums.length; ++i) {
			// int x = sums[i] - s;
			// int p = bsearch(x, sums); 
			// System.out.printf("p=%d, i=%d\n", p, i);
			// if (i - p < min) {
				// min = i - p;
			// }
		// }

		// ------ use bsearch1 ------
		for (int i=start; i < sums.length; ++i) {
			int x = sums[i] - s;
			int p = bsearch1(x, sums) - 1;
			if (i - p < min) {
				min = i - p;
			}
		}
		return min;
	}

	public static void main(String args[]) {
		int[] arr1 = {2, 3, 1, 2, 4, 3, 1, 1, 1, 1, 1};
		int r1 = new Solution().minSubArrayLen(7, arr1);
		System.out.printf("r1=%d\n", r1);

		int[] arr2 = {1, 1, 1};
		int r2 = new Solution().minSubArrayLen(7, arr2);
		System.out.printf("r2=%d\n", r2);

		int[] arr3 = {};
		int r3 = new Solution().minSubArrayLen(7, arr3);
		System.out.printf("r3=%d\n", r3);

		// !!!ATTENTION!!!
		// Condition of **positive** is very important,
		// or my solution will failed in the following case.
		int[] arr4 = {0, 0, 0, 0, 0, 7};
		int r4 = new Solution().minSubArrayLen(7, arr4);
		System.out.printf("r4=%d\n", r4);
	}
}

