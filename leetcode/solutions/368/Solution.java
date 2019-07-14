/**
 * 0368: LargestDivisibleSubset
 *
 * Performance: speed=64%, memory=98%
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public List<Integer> largestDivisibleSubset_S5M5(int[] nums) {
		if (nums.length == 0) return new ArrayList<>();
		int n = nums.length;
		Arrays.sort(nums);
		int[][] dp = new int[n][n+1];
		int[][] nx = new int[n][n+1];

		for (int i = 0; i < n; ++i) {
			dp[n-1][i] = (nums[n-1] % nums[i] == 0 ? 1 : 0);
			nx[n-1][i] = (nums[n-1] % nums[i] == 0 ? 1 : 0);
		}
		dp[n-1][n] = 1;
		nx[n-1][n] = 1;
		for (int i = n-2; i >= 0; --i) {
			for (int j = 0; j <= n; ++j) {
				dp[i][j] = dp[i+1][j];
				if (dp[i][j]<1+dp[i+1][i] && (j==n || nums[i]%nums[j]==0)) dp[i][j] = dp[i+1][i] + 1;
				nx[i][j] = (dp[i][j] == dp[i+1][j] ? 0 : 1);
			}
		}
		System.out.printf("dp[0][n]=%d\n", dp[0][n]);

		List<Integer> list = new ArrayList<>(dp[0][n]);
		int j = n;
		for (int i = 0; i < n; ++i) {
			if (nx[i][j] == 1) {
				list.add(nums[i]);
				j = i;
			}
		}
		return list;
	}

	public List<Integer> largestDivisibleSubset(int[] nums) {
		if (nums.length == 0) return new ArrayList<>();
		int n = nums.length;
		Arrays.sort(nums);
		int[] dp = new int[n];
		int[] pr = new int[n];

		dp[0] = 1;
		pr[0] = -1;
		for (int i = 1; i < n; ++i) {
			dp[i] = 1;
			pr[i] = -1;
			for (int j = 0; j < i; ++j) {
				if (nums[i]%nums[j]==0 && dp[j]+1 > dp[i]) {
					dp[i] = dp[j] + 1;
					pr[i] = j;
				}
			}
		}

		int max=0, end=-1;
		for (int i = 0; i < n; ++i) {
			if (dp[i] > max) {
				max = dp[i];
				end = i;
			}
		}
		System.out.printf("max=%d, end=%d\n", max, end);

		List<Integer> list = new ArrayList<>(max);
		while (end != -1) {
			list.add(nums[end]);
			end = pr[end];
		}
		return list;
	}

	public static void main(String args[]) {
		//List<Integer> list1 = new Solution().largestDivisibleSubset(new int[]{2, 3, 6, 9, 18, 36, 4, 8, 16, 32, 72, 144});
		//System.out.printf("list1: %s\n", list1.toString());

		List<Integer> list2 = new Solution().largestDivisibleSubset(new int[]{1});
		System.out.printf("list2: %s\n", list2.toString());
	}
}

