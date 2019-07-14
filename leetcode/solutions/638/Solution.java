/**
 * 0638: ShoppingOffers
 * Performance: speed=%, memory=%
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
		int[] p = new int[price.size()]; for (int i = 0; i < price.size(); ++i) p[i] = price.get(i);
		int[] n = new int[needs.size()]; for (int i = 0; i < needs.size(); ++i) n[i] = needs.get(i);
		int[][] s= new int[special.size()][price.size()+1];
		for (int i = 0; i < special.size(); ++i) {
			for (int j = 0; j < special.get(i).size(); ++j) {
				s[i][j] = special.get(i).get(j);
			}
		}

		int[] dp = new int[(1<<(3*n.length))];
		int[] b = new int[p.length];

		// dp[i] = dp[i-?] + price
		int x = 0;
		for (int i = 0; i < n.length; ++i) x |= (n[i]<<3*i);


		for (int i = 1; i <= x; ++i) {
			// buy one
			// b[b.length-1] = 0;
			// for (int j = 0; j < b.length; ++j) {
				// if (j-1 >= 0) b[j-1] = 0;
				// b[j] = 1;
				// int pre = from(i, b, b.length);
				// if (pre != -1 && dp[pre] != -1) dp[i] = Math.min(dp[i], dp[pre]+p[j]);
			// }

			// buy one
			boolean ct = false;
			for (int j = 0; j < n.length; ++j) {
				int k = ((i << (29-3*j))>>>29);
				if (k > n[j]) {ct = true; break;}
				dp[i] += p[j] * k;
			}
			if (ct) continue;

			// buy sale
			for (int j = 0; j < s.length; ++j) {
				int pre = from(i, s[j], s[j].length-1);
				if (pre != -1 && dp[pre] != -1) dp[i] = Math.min(dp[i], dp[pre]+s[j][s[j].length-1]);
			}

			if (dp[i] == Integer.MAX_VALUE) dp[i] = -1;

			// print(i, dp);
		}

		return dp[x];
	}

	private int from(int i, int[] tobuy, int n) {
		int pre = 0;
		for (int j = 0; j  < n; ++j) {
			// [3*j, 3*j+3)
			int k = ((i << (29-3*j))>>>29); // buy k j-th item
			int r = k - tobuy[j]; // remains r j-th item to buy
			// System.out.printf(">>>> j=%2d, k=%2d, b=%2d, r=%2d\n", j, k, tobuy[j], r);
			if (r < 0) return -1;
			pre |= (r << 3*j);
		}
		return pre;
	}

	private void print(int i, int[] dp) {
		System.out.printf("i=%-6d: ", i);
		for (int j = 0; j < 6; ++j) {
			int k = ((i<<(29-3*j))>>>29);
			System.out.printf("%d, ", k);
		}
		System.out.printf(", dp=%d\n", dp[i]);
	}

	public static void main(String args[]) {
		int ans1 = new Solution().shoppingOffers(
			Arrays.asList(2, 5),
			Arrays.asList(
				Arrays.asList(3, 0, 5),
				Arrays.asList(1, 2, 10)),
			Arrays.asList(3, 2));
		System.out.printf("ans1=%d\n", ans1);

		int ans2 = new Solution().shoppingOffers(
                        Arrays.asList(2, 3, 4),
                        Arrays.asList(
                                Arrays.asList(1, 1, 0, 4),
                                Arrays.asList(2, 2, 1, 9)),
                        Arrays.asList(1, 2, 1));
                System.out.printf("ans2=%d\n", ans2);
	}
}

