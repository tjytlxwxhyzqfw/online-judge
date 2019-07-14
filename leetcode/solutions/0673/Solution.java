/**
 * 0673: NumOfLIS
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int findNumberOfLIS_1_S15M27(int[] nums) {
		if (nums == null || nums.length == 0) return 0;
		int[] dp = new int[nums.length], cnt = new int[nums.length];  // attention: cnt is necessary
		int maxlen=1, count=0;
		for (int i = 0; i < nums.length; ++i) {
			dp[i] = 1;
			for (int j = 0; j < i; ++j) if (nums[i]>nums[j]) dp[i] = Math.max(dp[i], dp[j]+1);
			// System.out.printf("i=%2d, v=%2d, dp=%2d\n", i, nums[i], dp[i]);
			for (int j = 0; j < i; ++j) if(nums[i]>nums[j] && dp[j]+1==dp[i]) cnt[i] += cnt[j];  // not ++cnt[i]
			if (cnt[i]==0) cnt[i] = 1;
			// System.out.printf("\tcnti=%2d, maxlen=%2d, count=%2d\n", cnt[i], maxlen, count);
			if (dp[i] == maxlen) count += cnt[i];
			else if (dp[i] > maxlen) {maxlen=dp[i]; count = cnt[i];}  // fool: forget to update maxlen
		}
		return count;
	}

	public int findNumberOfLIS_2_S33M15(int[] nums) {
                if (nums == null || nums.length == 0) return 0;
                int[] dp = new int[nums.length], cnt = new int[nums.length];
                int maxlen=1, count=0;
                for (int i = 0; i < nums.length; ++i) {
                        dp[i] = 1;
                        for (int j = 0; j < i; ++j) {
				if (nums[i]>nums[j]) {
					if (dp[i] == dp[j]+1) cnt[i] += cnt[j];
					else if (dp[i] < dp[j]+1) {dp[i] = dp[j]+1; cnt[i] = cnt[j];}
				}
			}
			if (cnt[i] == 0) cnt[i] = 1;
			if (dp[i] > maxlen) maxlen = dp[i];
                }
		for (int i = 0; i < nums.length; ++i) if (dp[i] == maxlen) count += cnt[i];
		return count;
        }


	public static void main(String args[]) {
		int r1 = new Solution().findNumberOfLIS(new int[]{1, 2, 4, 3, 5, 4, 7, 2});
		// int r1 = new Solution().findNumberOfLIS(new int[]{1, 1, 3, 2, 4});
		System.out.printf("r1=%d\n", r1);
	}
}

