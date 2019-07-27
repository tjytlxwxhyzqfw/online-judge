/**
 * 873: LengthOfLongestFibonacciSubsequence
 * Performance: speed=78%, memory=5% // with map
 * Performance: speed=27%, memory=31%  // with bs
 *
 * 1) there is a better (i think) in the discuss,
 *    check pair: for i, j in a: while map.contains(dp[i]+d[j]) { j = i, i = index(dp[i]+dp[j]) };
 *
 * 2) todo: when i reverse the loop and break on d GE a[j], answer becomes wrong, why ?
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int lenLongestFibSubseq(int[] a) {
		if (a.length <= 2) return 0;
		int[][] dp = new int[a.length][a.length];
		dp[0][1] = 2;

		int max = 0;
		for (int i = 2; i < a.length; ++i) {
			for (int j = 0; j < i; ++j) {
				dp[j][i] = 2;
				int d = a[i] - a[j];
				if (d < a[j]) {
					int p = lb(d, a, 0, i);
					if (p != i && a[p] == d) dp[j][i] = Math.max(dp[j][i], dp[p][j] + 1);
				};
				max = Math.max(max, dp[j][i]);
			}
		}

		return max > 2 ? max : 0;
	}

	private int lb(int x, int[] a, int begin, int end) {
		int i = begin, j = end;
		while (i < j) {
			int k = i + (j-i) / 2;
			if (a[k] < x) i = k+1;
			else j = k;
		}
		// end: a[i-1] < x <= a[i] || i == end
		return i;
	}

	public static void main(String args[]) {
		int r1 = new Solution().lenLongestFibSubseq(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
		System.out.printf("r1=%d\n", r1);
	}
}

