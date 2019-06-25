/**
 * 0213: House Robber II
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int rob(int[] nums) {
		if (nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];
		if (nums.length == 2) return Math.max(nums[0], nums[1]);

		// choosing first
		int[] dp1 = new int[nums.length];
		dp1[0] = nums[0];
		dp1[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < nums.length-1; ++i) {
			dp1[i] = Math.max(dp1[i-2] + nums[i], dp1[i-1]);
		}

		for (int i = 0; i < nums.length; ++i) System.out.printf("%2d: %d\n", i, dp1[i]);
		System.out.printf("\n");
		

		// not choosing first
		int[] dp2 = new int[nums.length];
		dp2[0] = 0;
		dp2[1] = nums[1];
		for (int i = 2; i < nums.length; ++i) {
			dp2[i] = Math.max(dp2[i-2] + nums[i], dp2[i-1]);
		}

		for (int i = 0; i < nums.length; ++i) System.out.printf("%2d: %d\n", i, dp2[i]);
		System.out.printf("\n");
		
		return Math.max(dp1[nums.length-2], dp2[nums.length-1]);
	}
	public static void main(String args[]) {
		new Solution().rob(new int[]{1, 2, 1, 1,});
	}
}

