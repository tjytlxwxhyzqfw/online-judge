/**
 * 16
 *
 * 三数置和最接近给定的数
 */

import java.util.Arrays;

public class Solution {
	private int best, mindelta;
	private int target;

	public int threeSumClosest(int[] nums, int target) {
		int i, j, k;
		int expect, base;

		if (nums.length < 3) throw new RuntimeException();

		Arrays.sort(nums);

		mindelta = Integer.MAX_VALUE;
		this.target = target;
		for (i = 0; i < nums.length; ++i) {
			for (j = i+1; j < nums.length; ++j) {
				base = nums[i] + nums[j];
				expect = target - base;
				k = Arrays.binarySearch(nums, j+1, nums.length, expect);
				if (k >= 0) return target;
				k = -1 - k;
				if (k-1 >= j+1) refresh(base + nums[k-1]);
				if (k < nums.length) refresh(base + nums[k]); 
			}
		}

		return best;
	}

	private void refresh(int value) {
		int delta;

		delta = Math.abs(value - target);
		if (delta < mindelta) {
			mindelta = delta;
			best = value;
		}
	}

	public static void main(String args[]) {
		int nums[] = {0, 0, 0};
		int target = -3, ans;

		Solution solution = new Solution();
		ans = solution.threeSumClosest(nums, target);
		System.out.printf("result: %d\n", ans);
	}
}
