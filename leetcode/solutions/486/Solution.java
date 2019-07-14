/**
 * 0486: PredictTheWinner
 * Performance: speed=49%, memory=99%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public boolean PredictTheWinner(int[] nums) {
		if (nums.length == 0) return true; // not go to happen

		int[] ps = new int[nums.length];
		ps[0] = nums[0]; for (int i = 1; i < nums.length; ++i) ps[i] = ps[i-1] + nums[i];

		int[][] dp = new int[nums.length+1][nums.length+1];

		for (int i = nums.length; i >= 0; --i) {
			dp[i][i] = 0;
			for (int j = i+1; j <= nums.length; ++j) {
				int pickleft = nums[i] + ps[j-1] - ps[i] - dp[i+1][j];
				int pickrght = nums[j-1] - dp[i][j-1];
				if (j-2 >= 0) pickrght += ps[j-2];
				if (i-1 >= 0) pickrght -= ps[i-1];
				dp[i][j] = Math.max(pickleft, pickrght);
				System.out.printf("i=%2d, j=%2d, dp=%d\n", i, j, dp[i][j]);
			}
		}

		return dp[0][nums.length] >= ps[nums.length-1] - dp[0][nums.length];
	}

	public static void main(String args[]) {
		boolean ans = new Solution().PredictTheWinner(new int[]{1, 5, 233, 7});
		System.out.printf("ans=%s\n", ans);
	}
}

