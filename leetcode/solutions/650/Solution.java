/**
 * 0650: 2KeysKeyboard
 * Performance: speed=49%, memory=5%
 * 
 * todo: greedy ???
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int minSteps_DP(int n) {
		int[] dp = new int[n+1];
		for (int i = 2; i <= n; ++i) {
			dp[i] = i;
			if ((i&1) == 0) dp[i] = Math.min(dp[i], dp[i/2]+2);
			for (int j = i/2; j >= 2; --j) if (i%j==0) {dp[i] = Math.min(dp[i], dp[j] + i/j); break;}
		}
		return dp[n];
	}
	public static void main(String args[]) {
	}
}

