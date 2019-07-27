/**
 * 877: StoneGame
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public boolean stoneGame(int[] p) {
		int[] s = new int[p.length+1];
		s[0] = 0; for (int i = 0; i < p.length; ++i) s[i+1] = s[i] + p[i];
		int[][] dp = new int[p.length][p.length];

		for (int i = p.length-1; i >= 0; --i) {
			dp[i][i] = p[i];
			for (int j = i+1; j < p.length; ++j) {
				dp[i][j] = Math.max(
					p[i]-dp[i+1][j]+s[j+1]-s[i+1], 
					p[j]-dp[i][j-1]+s[j]-s[i]);
			}
		}

		System.out.printf("max=%d\n", dp[0][p.length-1]);
		return dp[0][p.length-1] >= s[p.length]/2+1;
	}
	public static void main(String args[]) {
		new Solution().stoneGame(new int[]{3, 10, 2});
	}
}

