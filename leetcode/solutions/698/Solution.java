/**
 * 698: PartitionToKEqualSumSubsets
 * Performance: speed=100%, memory=91%
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
 * todo: read Liu's book for backtracking and dfs (done)
 * attention: dfs() gets TLE and search() passed, why ?
 * my immature explanation is that search() is more compact than dfs() because sums[] in dfs() is 
 * very likely to be duplicated in different branch, as a contrast, search() contains a very compace
 * inner proceedure.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Solution {
	private int k;
	private int[] a;
	private int target;

	public boolean canPartitionKSubsets(int[] nums, int k) {
		if (k == 1) return true;

		int sum = 0; for (int i = 0; i < nums.length; ++i) sum += nums[i];
		if (sum % k != 0) return false;

		this.k = k;
		target = sum / k;
		a = nums;

		// int[] sums = new int[k-1];
		// return dfs(0, sums);

		boolean[] v = new boolean[nums.length];
		return search(0, 0, 0, v);
	}

	private boolean dfs(int i, int[] sums) {
		if (i == a.length) {
			for (int j = 0; j < k-1; ++j) if (sums[j] != target) return false;
			return true;
		}

		Set<Integer> set = new HashSet<>();
		for (int j = 0; j < k-1; ++j) {
			if (sums[j] + a[i] <= target) {

				if (set.contains(sums[j])) continue;
				set.add(sums[j]);

				sums[j] += a[i];
				if (dfs(i+1, sums)) return true;
				sums[j] -= a[i];
			}
		}
		return dfs(i+1, sums);
	}

	private boolean search(int i, int sum, int m, boolean[] v) {
		if (sum > target) return false;
		if (sum == target) {
			if (m == k-1) return true;
			return search(0, 0, m+1, v);
		}
		if (i >= a.length) return false;

		if (search(i+1, sum, m, v)) return true;
		if (!v[i]) {
			v[i] = true;
			if (search(i+1, sum+a[i], m, v)) return true;
			v[i] = false;
		}
		return false;
	}

	public static void main(String args[]) {
		boolean r1 = new Solution().canPartitionKSubsets(new int[]{1, 1, 1, 1}, 2);
		System.out.printf("r1=%s\n", r1);
	}
}

