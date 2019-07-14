/**
 * 300 Longest Increasing Subsqueue 
 * Performance: speed=100%, memory=78%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
        public int lengthOfLIS(int[] nums) {
		if (nums.length == 0) return 0;
		int[] tails = new int[nums.length];
		int j = 0;
		for (int i = 0; i < nums.length; ++i) {
			// for (int k = 0; k < j; ++k) System.out.printf("%d, ", tails[k]);
			// System.out.printf("#=%d\n", j);
			if (i == 0) tails[j++] = nums[i];
			// System.out.printf("i=%d, nums[i]=%d\n", i, nums[i]);
			if (nums[i] > tails[j-1]) {
				tails[j++] = nums[i];
			} else {
				int p = ub(nums[i], tails, j);

				// if nums[i] in tails, we do nothing
				// case: {4, 10, 4, 3, 8, 9}
				// tails = [4, 10] -> nums[i]=4, p = 1 => tails=[4, 4]
				if (p > 0 && tails[p-1] == nums[i]) continue;
				if (p < nums.length) tails[p] = nums[i];
				
			}
		}
		return j;
	}

	private int ub(int x, int[] a, int n) {
		int i = 0, j = n;
		while (i < j) {
			int k = (i + j) / 2;
			if (a[k] <= x) i = k + 1;
			else j = k;
		}
		// i = j && a[i-1] <= x < a[j]
		return i;
	}

	public static void main(String args[]) {
		int[] a1 = {10, 9, 2, 5, 3, 7, 101, 18, 200};
		int r1 = new Solution().lengthOfLIS(a1);
		System.out.printf("r1: %d\n", r1);

		int[] a2 = {1};
		int r2 = new Solution().lengthOfLIS(a2);
		System.out.printf("r2: %d\n", r2);

		int[] a3 = {4, 10, 4, 3, 8, 9};
		int r3 = new Solution().lengthOfLIS(a3);
		System.out.printf("r3: %d\n", r3);
	}
}

