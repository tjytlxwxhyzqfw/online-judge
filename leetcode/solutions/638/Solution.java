/**
 * 0638: ShoppingOffers
 * Performance: speed=%, memory=%
 * dp:  232ms, 56.5MB
 * dfs: might be more easy than dp
 * todo: analysise solutions in discuss, dfs(back-tracking) is much more slow than dp, why ?
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	Map<List<Integer>, Integer> dfsMem = new HashMap<>();

	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
		int[] p = new int[price.size()]; for (int i = 0; i < price.size(); ++i) p[i] = price.get(i);
		int[] n = new int[needs.size()]; for (int i = 0; i < needs.size(); ++i) n[i] = needs.get(i);
		int[][] s= new int[special.size()][price.size()+1];
		for (int i = 0; i < special.size(); ++i) {
			for (int j = 0; j < special.get(i).size(); ++j) {
				s[i][j] = special.get(i).get(j);
			}
		}
		return shoppingOffers_dfs(p, s, n);
	}

	public int shoppingOffers_dfs(int[] p, int[][] s, int[] n) {
		int[] currN = new int[n.length];
		Arrays.sort(s, new Comparator<int[]>() {
			@Override
			public int compare(int[] left, int[] rght) {
				int lefts = 0, rghts = 0;
				for (int i = 0; i < left.length-1; ++i) {lefts += left[i]; rghts += rght[i];}
				return rghts - lefts;
			}
		});
		return dfs(currN, p, s, n);
	}

	private int dfs(int[] currN, int [] p, int[][] s, int[] n) {
		boolean done = true; for (int i = 0; i < n.length; ++i) if (currN[i] != n[i]) { done = false; break; }
		if (done) return 0;

		List<Integer> list = new ArrayList<>(currN.length);	
		for (int i = 0; i < currN.length; ++i) list.add(currN[i]);
		System.out.printf("dfs: %s\n", list);
		if (dfsMem.get(list) != null) return dfsMem.get(list);

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n.length; ++i) {
			if (currN[i] + 1 <= n[i]) { currN[i] += 1; min=Math.min(min, p[i]+ dfs(currN,p,s,n)); currN[i] -= 1;}
		}
		for (int i = 0; i < s.length; ++i) {
			boolean ok = true; for (int j = 0; j < n.length; ++j) if (currN[j]+s[i][j] > n[j]) { ok = false; break; }
			if (!ok) continue;
			for (int j = 0; j < n.length; ++j) currN[j] += s[i][j];
			min = Math.min(min, s[i][n.length] + dfs(currN, p, s, n));
			for (int j = 0; j < n.length; ++j) currN[j] -= s[i][j];
		}

		dfsMem.put(list, min);
		return min;
	}

	public int shoppingOffers_dp(int[] p, int[][] s, int[] n) {
		int[] dp = new int[(1<<(3*n.length))];
		int[] b = new int[p.length];

		// dp[i] = dp[i-?] + price
		// we owns (i, j, k) for items (a, b, c), so how do we get this state ?
		// dp[(i, j, k)] = dp[(i, j, k) - some special] + special.price
		// for 6 items, we can use 3 bits to represent a state.
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

