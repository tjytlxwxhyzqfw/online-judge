/**
 * 713 Subarray Product Less Than K 37.6% Medium 773/38
 * Performance: speed=98%, memory=100%
 */

import java.util.*;

public class Solution {
	public int numSubarrayProductLessThanK(int[] nums, int k) {
		if (k <= 1) return 0;

		// k >= 2
		int i = 0, j = 0, p = 1, n = 0;
		// we use a loop to sum up the number of valid sub-arrays
		// in each loop, we calcute the number of valid subarrays ending at j,
		// and add the amount into n;
		while (j < nums.length) {
			// in the start of a new loop, we get window [i, j],
			// and we are going to calculate product p of [i, j].
			// (1) if p less than k, we know that all subarrays ended at j from i
			// are ok and there are (j+1-i) of them.
			// (2) if p equal to or greater than k, we move i to the next valid
			// posisiton i' where product of [i', j] is less than k, then
			// we add j-i'+1 into n;
			// according to (1) and (2), we managed to count the number of valid subarrays
			// which ends at j and add the count into total count n;

			if ((p *= nums[j++]) < k) n += j-i;
			else {
				while ((p /= nums[i++]) >= k) {}
				n += j-i;
			}

			// above statements can be re-write:
			// p *= nums[j++]
			// while (p >= k) { move i ... }
			// n += j-i;
		}

		return n;
	}

	// attention: !!! DO NOT stop at a INVALID window !!!
	// this solution fails at [4, 3, 8] and k = 19
	public int numSubarrayProductLessThanK_438k19(int[] nums, int k) {
		if (k <= 1) return 0;

		// k >= 2
		int i = 0, j = 0, p = 1, n = 0;
		while (j < nums.length) {
			if ((p *= nums[j]) >= k) {
				n += j-i;
				while (p >= k) { p /= nums[i++]; }
			}
			++j;
		}
		n += (j-i) * (j-i+1) / 2;

		return n;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.numSubarrayProductLessThanK(new int[]{10,9,10,4,3,8,3,3,6,2,10,10,9,3}, 19) == 18;
		assert s.numSubarrayProductLessThanK(new int[]{4, 3, 8}, 19) == 4;

		assert s.numSubarrayProductLessThanK(new int[]{}, 0) == 0;
		assert s.numSubarrayProductLessThanK(new int[]{}, 1) == 0;
		assert s.numSubarrayProductLessThanK(new int[]{}, 7) == 0;
		assert s.numSubarrayProductLessThanK(new int[]{1}, 0) == 0;
		assert s.numSubarrayProductLessThanK(new int[]{1}, 1) == 0;
		assert s.numSubarrayProductLessThanK(new int[]{1}, 2) == 1;

		assert s.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100) == 8;
		assert s.numSubarrayProductLessThanK(new int[]{10, 5, 2}, 100) == 5;

		System.out.println("done");
	}
}

