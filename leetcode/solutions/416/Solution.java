/**
 * 0416: PartitionEqualSubsetSum
 * Performance: speed=85%, memory=94%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public boolean canPartition_S16M96(int[] nums) {
		if (nums == null || nums.length < 2) return false;
		int sum = 0;
		for (int i = 0; i < nums.length; ++i) sum += nums[i];
		// if you use only one array(last), then:
		// in loop-i: last[1] = true, last[2]=true because last[1] = true, WA!!!
		// the status updated in loop-i is immediately used in loop i, this lead to wrong answer !!!
		// case: [1, 2, 5], when i = 1 (v=2), 1-8 is all true
		boolean[] last = new boolean[sum+1];
		boolean[] curr = new boolean[sum+1];
		boolean[] t;
		last[nums[0]] = true;
		// for (int k = 0; k <=sum; ++k) if (last[k]) System.out.printf("init: true: %2d\n", k);
		for (int i = 1; i < nums.length; ++i) {
			for (int j = 1; j <= sum; ++j) {
				if (last[j] || (j>nums[i] && last[j-nums[i]])) curr[j] = true;
			}
			{ t = last; last = curr; curr = t; }
			// for (int k = 0; k <=sum; ++k) if (last[k]) System.out.printf("i=%2d, v=%2d, true: %2d\n", i, nums[i], k);
		}
		for (int j = 1; j <= sum; ++j) if (last[j] && sum-j==j) return true;
		return false;
	}

	public boolean canPartition(int[] nums) {
                if (nums == null || nums.length < 2) return false;
                int sum = 0;
                for (int i = 0; i < nums.length; ++i) sum += nums[i];

		// it is impossible to devide a odd-sum array into two
		if (sum % 2 != 0) return false;
		int half = sum / 2;

		// this line make the speed down from 5ms -> 6ms
		//for (int i = 0; i < nums.length; ++i) if (nums[i] >= half) return nums[i] == half;

		// sum/2 is enough !!!
                boolean[] last = new boolean[half+1];
                if (nums[0] <= half) last[nums[0]] = true;
                for (int i = 1; i < nums.length; ++i) {
			// Important !!! traverse j from right to left !!!
                        for (int j = half; j > nums[i]; --j) if (last[j-nums[i]]) last[j] = true;
			if (last[half]) return true;
                }

                return false;
        }


	public static void main(String args[]) {
		new Solution().canPartition(new int[]{1, 2, 5});
	}
}

