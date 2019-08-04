/**
 * 698: PartitionToKEqualSumSubsets
 * Performance: speed=%, memory=%
 *
 * case: 1, 2, 2, 3, 3, 4, target=5
 * if you pick 1+2+2=5, then you will never split 3, 3, 4 to 5+5
 * so you must pick {1, 4}, {2, 3}, {2, 3}
 *
 * case: 10, 10, 10, 7, 7, 7, 7, 7, 7, 6, 6, 6, sum=90, target=30
 * if you pick {10, 10, 10}, then you never get 30 by {7, 7, ..., 6, 6, 6} 
 * you must piek 3 * {10, 7, 7, 6}
 *
 * to solve this problem, you cannot just pick a target (such as {1, 2, 2} for 5),
 * and then pick another target with the remaining elements (such as {3, 3, 4} for 5)
 * instead, you must take all selection orders into count, that is, if there are 
 * 3 elements, you must consider 012, 021, 102, 120, 210, 201, and if there are n
 * elements, you must consider all n! situations
 *
 * todo: read Liu's book for backtracking and dfs
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	private int K;
	public boolean canPartitionKSubsets(int[] nums, int k) {
		if (k == 1) return true;

		int sum = 0; for (int i = 0; i < nums.length; ++i) sum += nums[i];
		if (sum % k != 0) return false;
		int[] sums = new int[k-1];

		Arrays.sort(nums);
		for (int i = 0, j = nums.length-1; i < j; ++i, --j) {
			int t = nums[i];
			nums[i] = nums[j];
			nums[j] = t;
		}

		K = k;
		return dfs(0, sums, nums, sum / k);
	}

	private boolean dfs(int i, int[] sums, int[] nums, int target) {
		if (i == nums.length) {
			for (int k = 0; k < K-1; ++k) if (sums[k] != target) return false;
			return true;
		}
		for (int k = 0; k < K-1; ++k) {
			if (sums[k] + nums[i] <= target) {
				sums[k] += nums[i];
				if (dfs(i+1, sums, nums, target)) return true;
				sums[k] -= nums[i];
			}
		}
		return dfs(i+1, sums, nums, target);
	}

	public static void main(String args[]) {
		boolean r1 = new Solution().canPartitionKSubsets(new int[]{1, 1, 1, 1}, 2);
		System.out.printf("r1=%s\n", r1);
	}
}

