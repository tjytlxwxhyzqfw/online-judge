/**
 * 0375: GuessNumberHigherOrLowerII
 *
 * Performance: speed=%, memory=%
 */

// review logs
// -----------
// Description: You give me a range [1, n], i picked a number 1 <= x <= n. you guess what i picked.
//   if you guess y and y is a wrong number, you give me y dollars and i tell you weather y is lower
//   or larger than x. how much money you need to guarantee a win ?
//
// 20200724: (WRONG) i come up with a solution with O(n^2) complexity.
//   count: wrong guss number
//   n = 1 => target=1 money=0 count=0
//   n = 2 => target=2 money=1 count=1
//   n = 3 => target=3 money=2 count=1
//   ...
//   n = m => target=x money=y count=z
// 
//   i guss k then the problem is cut into to range [1, k-1], [k+1, m]
//   so money = k + max{money{n=k-1}, money{n=m-k}+count{n=m-k}*k}
//      count = 1 + count{n=k-1} or 1 + count{n=m-k}, it depends with money is larger
//
//  comment: this is a O(n^3) dp problem so i am pretty sure that the solution above is wrong.
//  todo: implement the solution and figure out that why it is wrong.

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int getMoneyAmount(int n) {
		int[][] dp = new int[n+2][n+2];
		// a classic dp loop that fills the dp array by array length
		// it takes O(n^3)
		for (int k = 2; k <= n; ++k) {
			for (int i = 1; i+k <= n+1; ++i) {
				dp[i][i+k] = Integer.MAX_VALUE;
				for (int j = i; j < i+k; ++j) {
					dp[i][i+k] = Math.min(dp[i][i+k], j + Math.max(dp[i][j], dp[j+1][i+k]));
				}
				System.out.printf("[%2d, %2d) = %d\n", i, i+k, dp[i][i+k]);
			}
		}
		return dp[1][n+1];
	}

	public static void main(String args[]) {
		int r1 = new Solution().getMoneyAmount(100);
		System.out.printf("r1=%d\n", r1);
	}
}

