/**
 * 740: DeleteAndEarn
 * Performance: speed=100%, memory=39%
 *
 * todo: is `delete nums[i]+1` not used in this problem ???
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Solution {

	public int deleteAndEarn(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		int n = 0; for (int i = 0; i < nums.length; ++i) n = Math.max(n, nums[i]);
		// int n = IntStream.of(nums).max().getAsInt();
		int[] counts = new int[n+1], dp = new int[n+1];
		for (int i = 0; i < nums.length; ++i) ++counts[nums[i]];
		dp[0] = 0;
		dp[1] = counts[1];
		for (int i = 2; i <= n; ++i) dp[i] = Math.max(dp[i-1], dp[i-2]+counts[i]*i);
		return dp[n];
	}

	public static void main(String args[]) {
		int r1 = new Solution().deleteAndEarn(new int[]{1, 2, 3, 3, 4});
		System.out.printf("r1=%d\n", r1);
	}
}

