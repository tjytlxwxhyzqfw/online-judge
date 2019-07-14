/**
 * 801: MinimumSwapsToMakeSequencesIncreasing
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int minSwap(int[] A, int[] B) {
		int n = A.length;
		if (n == 0 || n == 1) return 0;

		int[] dp = new int[n+1];
		dp[0] = dp[1] = 0;
		System.out.printf("i=%2d, a=%2d, b=%2d, dp=%d\n", 1, A[0], B[0], dp[1]);
		for (int i = 2; i <= n; ++i) {
			dp[i] = dp[i-1];
			if (A[i-1] <= A[i-2] || B[i-1] <= B[i-2]) dp[i] = dp[i-2] + 1;
			System.out.printf("i=%2d, a=%2d, b=%2d, dp=%d\n", i, A[i-1], B[i-1], dp[i]);
		}

		return dp[n];
	}

	public static void main(String args[]) {
		int r1 = new Solution().minSwap(
			new int[]{0, 7, 8, 10, 10, 11, 12, 13, 19, 18},
			new int[]{4, 4, 5,  7, 11, 14, 15, 16, 17, 20});
		System.out.printf("r1=%d\n", r1);
	}
}

