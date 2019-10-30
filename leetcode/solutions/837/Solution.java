/**
 * 837	New 21 Game 32.7% Medium 404/212
 * Performance: speed=%, memory=%
 */

// understanding dp[i] is virtal important to understand my solution.
// dp[i] means what is the probability that i get i points during the game ? 
// e.g. we are in a (n=21, k=17, w=10) game:
// what is the probability that we get point 2 ?
// 	there is only 2 situation leading to points 2: 0+2 and 1+1
// what is the probability that we get point 37 ?
//	there is only 1 situation leading to points 38: 16+21
// can we get point 38 ? no.
// so we have
// dp[i] = {
//      dp[i-1] + dp[i-2] + ... + dp[Math.max(0, i-w)], if i < k
//      dp[k-1] + ... + dp[Math.max(0, i-w)], if i >= k
// }
// and above equation lead us to the final solution as bellow.

import java.util.*;

public class Solution {
	public double new21Game(int n, int k, int w) {
		if (k == 0) return 1;
		if (k == 1) return (double)n / w;

		double[] dp = new double[k+w];
		double[] sum = new double[dp.length];
		dp[0] = sum[0] = 1.;
		for (int i = 1; i < k+w; ++i) {
			double base = i-w > 0 ? sum[i-w-1] : 0;
			if (i < k) {
				dp[i] = sum[i-1] - base;
			} else { // i >= k
				dp[i] = sum[k-1] - base;
			}
			dp[i] /= w;
			System.out.printf("dp[%2d] = %g\n", i, dp[i]);
			sum[i] = sum[i-1] + dp[i];
		}
		double p = sum[n] - sum[k-1];
		System.out.printf("p=%g\n", p);

		return p;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.new21Game(0, 0, 0) == 1;
		assert s.new21Game(6, 1, 10) == .6;
		assert s.new21Game(21, 17, 10) == .73278;

		System.out.println("done");
	}
}

