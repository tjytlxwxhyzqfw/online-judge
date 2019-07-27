/**
 * 473: MatchsticksToSquare
 * Performance: speed=55%, memory=89%
 *
 * optimistic: using a sorted array leads to a much faster solution
 * discuss: Sorting the input array DESC will make the DFS process run much faster,
 * Reason behind this is we always try to put the next matchstick in the first subset,
 * If there is no solution, trying a longer matchstick first will get to negative conclusion earlier,
 * Runtime is improved from more than 1000ms to around 40ms, A big improvement.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Solution {
	private int[] a;
	private int[] e = new int[5];  // e[0] >= e[1] >= e[2] >= e[3] >= e[4]

	public boolean makesquare(int[] a) {
		if (a.length < 4) return false;
		this.a = a;
		for (int i = 0; i < a.length; ++i) e[0] += a[i];
		if (e[0] % 4 != 0) return false;
		e[0] /= 4;
		Arrays.sort(a);
		return dfs(a.length-1);
	}

	private boolean dfs(int i) {
		// verified by caller
		if (i == -1) return e[0] == e[1] && e[1] == e[2] && e[2] == e[3];
		for (int k = 1; k <= 4; ++k) {
			if (e[k]+a[i] <= e[0]) {
				e[k] += a[i];
				if (dfs(i-1)) return true;
				e[k] -= a[i];
			}
		}
		return false;
	}

	public static void main(String args[]) {
		boolean r = new Solution().makesquare(new int[]{3, 3, 3, 3, 4});
		System.out.printf("r=%s\n", r);
	}
}

