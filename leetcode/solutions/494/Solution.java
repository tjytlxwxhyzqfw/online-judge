/**
 * 494: TargetSum
 * Performance: speed=66%, memory=42%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	// dp[i][j] = dp[i-1][j-nums[i]] + dp[i-1][j+nums[i]]
	public int findTargetSumWays(int[] nums, int S) {
		if (nums.length == 0) return 0;
		int sum = 0; for (int i = 0; i < nums.length; ++i) sum += nums[i];

		if (sum < 0) sum = 0 - sum;  // !!!ATTENTION!!!
		if (S > sum || S < 0-sum) return 0; // !!!ATTENTION!!!

		int[] last = new int[2*sum+1];
		int[] curr = new int[2*sum+1];
		int[] t;

		last[0-nums[0]+sum] = 1;
		last[nums[0]+sum] += 1;
		for (int i = 1; i < nums.length; ++i) {
			for (int j = 0-sum; j <= sum; ++j) {
				curr[j+sum] = 0;
				if (j-nums[i]+sum >= 0 && j-nums[i]+sum <= 2*sum) curr[j+sum] += last[j-nums[i]+sum];
				if (j+nums[i]+sum >= 0 && j+nums[i]+sum <= 2*sum) curr[j+sum] += last[j+nums[i]+sum];
				System.out.printf("i=%2d, v=%2d, j=%2d, dp=%2d\n", i, nums[i], j, curr[j+sum]);
			}
			{t=last; last=curr; curr=t;}
		}

		return last[S+sum];
	}
 
	public static void main(String args[]) {
		int ans = new Solution().findTargetSumWays(new int[]{-1, -1, -1, -1, -1}, -3);
		System.out.printf("ans=%d\n", ans);
	}
}

