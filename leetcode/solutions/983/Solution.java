/**
 * 0983 Minimum Cost For Tickets
 *
 * Performance: speed=6.7%, memory=41%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int mincostTickets(int[] days, int[] costs) {
		int[][] dp = new int[365][31];
		for (int i = 0; i < dp.length; ++i) for (int j = 0; j < dp[0].length; ++j) dp[i][j] = -1;

		dp[0][0] = 0;
		dp[0][1] = costs[0];
		dp[0][7] = costs[1];
		dp[0][30] = costs[2];
		for (int i = 0; i < days.length-1; ++i) {
			for (int j = 1; j <= 30; ++j) {
				if (dp[i][j] == -1) continue;
				System.out.printf("i=%2d, d=%2d, t=%2d, c=%3d\n", i, days[i], j, dp[i][j]);
				int d = days[i+1] - days[i];

				// buy nothing
				int r = Math.max(0, j-d);// tickets remaining
				init(dp, i+1, r);
				dp[i+1][r] = Math.min(dp[i+1][r], dp[i][j]);
				System.out.printf("\ti=%2d, d=%2d, t=%2d, c -> %2d\n", i+1, days[i+1], r, dp[i+1][r]);

				init(dp, i+1, 1);
				dp[i+1][1] = Math.min(dp[i+1][1], dp[i][j]+costs[0]);
				System.out.printf("\ti=%2d, d=%2d, t=%2d, c -> %2d\n", i+1, days[i+1], 1, dp[i+1][1]);

				init(dp, i+1, 7);
				dp[i+1][7] = Math.min(dp[i+1][7], dp[i][j]+costs[1]);
				System.out.printf("\ti=%2d, d=%2d, t=%2d, c -> %2d\n", i+1, days[i+1], 7, dp[i+1][7]);

				init(dp, i+1, 30);
				dp[i+1][30] = Math.min(dp[i+1][30], dp[i][j]+costs[2]);
				System.out.printf("\ti=%2d, d=%2d, t=%2d, c -> %2d\n", i+1, days[i+1], 30, dp[i+1][30]);

				// buy a one-day-ticket
				r = Math.max(0, 1-d);
				init(dp, i+1, r);
				dp[i+1][r] = Math.min(dp[i+1][r], dp[i][j]+costs[0]);
				System.out.printf("\ti=%2d, d=%2d, t=%2d, c -> %2d\n", i+1, days[i+1], r, dp[i+1][r]);

				// buy a 7-day-ticket
				r = Math.max(0, 7-d);
				init(dp, i+1, r);
				dp[i+1][r] = Math.min(dp[i+1][r], dp[i][j]+costs[1]);
				System.out.printf("\ti=%2d, d=%2d, t=%2d, c -> %2d\n", i+1, days[i+1], r, dp[i+1][r]);

				// buy a 30-day-ticket
				r = Math.max(0, 30-d);
				init(dp, i+1, r);
				dp[i+1][r] = Math.min(dp[i+1][r], dp[i][j]+costs[2]);
				System.out.printf("\ti=%2d, d=%2d, t=%2d, c -> %2d\n", i+1, days[i+1], r, dp[i+1][r]);
			}
		}

		int minc = Integer.MAX_VALUE;
		for (int j = 1; j <= 30; ++j) if (dp[days.length-1][j] != -1) minc = Math.min(minc, dp[days.length-1][j]);
		System.out.printf("===> minc: %d\n", minc);
		return minc;
	}

	private void init(int[][] dp, int i, int j) {
		if (dp[i][j] == -1) dp[i][j] = Integer.MAX_VALUE;
	}

	public static void main(String args[]) {
		//System.out.printf("-------------------\n");
		//new Solution().mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{2, 7, 15});

		// System.out.printf("-------------------\n");
		// new Solution().mincostTickets(new int[]{1, 31}, new int[]{2, 7, 15});

		//System.out.printf("-------------------\n");
		//new Solution().mincostTickets(new int[]{1, 31}, new int[]{2, 1, 1});

		System.out.printf("-------------------\n");
		new Solution().mincostTickets(new int[]{1,4,6,9,10,11,12,13,14,15,16,17,18,20,21,22,23,27,28}, new int[]{3,13,45});
	}
}

