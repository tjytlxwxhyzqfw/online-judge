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
		Arrays.sort(nums);
		Integer closet = null;
		for (int i = 0; i < nums.length-2; ++i) {
			int j = i+1, k = nums.length-1;
			while (j < k) {
				int sum = nums[i] + nums[j] + nums[k];
				if (sum == target) return sum;
				if (closet == null || Math.abs(sum-target) < Math.abs(closet-target)) closet = sum;
				if (sum > target) --k; else ++j;
			}
		}
		return closet;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.threeSumClosest(new int[]{0, 0, 0}, 0) == 0;
		assert s.threeSumClosest(new int[]{0, 0, 0}, 1) == 0;
		assert s.threeSumClosest(new int[]{0, 0, 0}, 1000) == 0;
		assert s.threeSumClosest(new int[]{1, 2, 3, 4, 5, 6, 7}, 1000) == 18;
		assert s.threeSumClosest(new int[]{1, 2, 3, 4, 5, 6, 7}, 0) == 6;
		assert s.threeSumClosest(new int[]{1, 2, 20, 30, 40, 100, 200}, 91) == 90;

		System.out.printf("done\n");
	}
}
