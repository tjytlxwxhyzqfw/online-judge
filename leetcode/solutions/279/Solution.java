/**
 * 0279 Perfect Squares
 *
 * Performance: speed=41%, memory=16%
 * Performance: speed=98%, memory=24% (Use Static Tricks)
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	static private int[] g = new int[]{0};
	static private int gi = 1;
	public int numSquares(int n) {
		if (n >= g.length) {
			int[] g1 = new int[n+1];
			for (int i = 0; i < gi; ++i) g1[i] = g[i];
			g = g1;
		}
		for (; gi <= n; ++gi) {
			g[gi] = Integer.MAX_VALUE;
			for (int j = 1; j * j <= gi; ++j) {
				g[gi] = Math.min(g[gi], g[gi-j*j] + 1);
			}
		}
		return g[n];
	}
	public static void main(String args[]) {
		int x = new Solution().numSquares(13);
		System.out.printf("x=%d\n", x);
	}
}

