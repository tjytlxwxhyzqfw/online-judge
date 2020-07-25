/**
 * 0377: CombinationSumIV
 * Performance: speed=87%, memory=93%
 */

// review logs
// -----------
// Description: 给定一个正整数集合s, 和一个目标数t, 从s中选出若干个数使其和为t,
//   考虑顺序, 问有多少种选择方法? 
// 20200724: 我能独立的想到dp[t][#s]的方法, 但是想不出答案中的方法.

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int combinationSum4(int[] nums, int target) {
		return careOrder(nums, target);
	}

	public int careOrder(int[] nums, int target) {
		if (nums.length == 0) return 0;
		if (target <= 0) return 0;
		int n = nums.length;

		// dp[i]表示用数组中的数字组合出i有多少种组合方法
		// nums中的数字对i的组合方式可以被**等价划分**成以下集合:
		//    {最后加nums[0]}, {最后加nums[1]}, ..., {最后加nums[n-1]} (20200724)
		int dp[] = new int[target+1];
		for (int i = 1; i <= target; ++i) {
			for (int j = 0; j < n; ++j) {
				if (i == nums[j]) dp[i] += 1;
				else if (i > nums[j]) dp[i] += dp[i-nums[j]];
			}
			System.out.printf("v=%2d, dp=%2d\n", i, dp[i]);
		}

		return dp[target];
	}

	public int dontCareOrder(int[] nums, int target) {
		if (nums.length == 0) return 0;
                if (target <= 0) return 0;
                int n = nums.length;

		// dp[i][j]: nums of ways that we sum to j with nums whose index <= i
		// dp[i][j] = dp[i-1][j-nums[i]] + dp[i-1][j]
		// 1, 2, 3, 
		// t=1: 1
		// t=2: 1+1, 2
		// t=3: 1+2, 1+1+1, 3
		// t=4: 1+1+1+1, 1+1+2, 1+3, 2+2
	}

	// 应该是之前的傻逼解法用的这个函数, 傻逼解法删掉了, 这个函数忘记删除了.
	private int comb(int n, int k) {
		System.out.printf("c(%d, %d)=", n, k);
		int ans = 1;
		for (int i = n; i >= n-k+1; --i) ans *= i;
		for (int i = 2; i <= k; ++i) ans /= i;
		System.out.printf("%d\n", ans);
		return ans;
	}

	public static void main(String args[]) {
		new Solution().combinationSum4(new int[]{1, 2, 3}, 4);
	}
}

