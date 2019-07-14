/**
 * 0039: Combination Sum
 * performance: speed=45%, memory=14%
 * dp is useless
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
	private int[][] dp;
	private int[] nums;
	public List<List<Integer>> combinationSum(int[] nums, int target) {
		int n = nums.length;
		if (n == 0) return new ArrayList<>();

		int[][] dp = new int[n][target+1];

		for (int j = 1; j <= target; ++j) if (j%nums[0] == 0) dp[0][j] = 1;

		for (int i = 1; i < n; ++i) {
			for (int j = 1; j <= target; ++j) {
				dp[i][j] = (j % nums[i] == 0 ? 1 : 0);
				for (int k = 0; nums[i]*k < j; ++k) dp[i][j] += dp[i-1][j-nums[i]*k];
				System.out.printf("i=%2d, v=%2d, j=%2d, dp=%2d\n", i, nums[i], j, dp[i][j]);
			}
		}
		System.out.printf("max: %d\n", dp[n-1][target]);

		this.dp = dp;
		this.nums = nums;
	

		return print(n-1, target);
	}

	private List<List<Integer>> print(int i, int j) {
		List<List<Integer>> results = new ArrayList<>();
		if (j % nums[i] == 0) {
			List<Integer> ints = new ArrayList<>();
			addk(ints, nums[i], j / nums[i]);
			results.add(ints);
		}

		if (i == 0) return results;

		for (int k = 0; nums[i]*k < j; ++k) {
			List<List<Integer>> subresults = print(i-1, j-nums[i]*k);
			for (List<Integer> list : subresults) {
				if (list.size() != 0) {
					addk(list, nums[i], k);
					results.add(list);
				}
			}
		}
		return results;
	}

	private void addk(List<Integer> ints, int x, int k) {
		for (int i = 0; i < k; ++i) ints.add(x);
	}

	public static void main(String args[]) {
		List<List<Integer>> results = new Solution().combinationSum(new int[]{1, 2, 3, 4, 5}, 4);
		for (int i = 0; i < results.size(); ++i) System.out.printf("%s\n", results.get(i).toString());
	}
}
