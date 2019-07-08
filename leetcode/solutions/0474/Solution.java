/**
 * 0474: OnesAndZeros
 * Performance: speed=45%, memory=100%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int findMaxForm(String[] strs, int m, int n) {
		if (strs == null || strs.length == 0) return 0;

		int[][] last = new int[m+1][n+1];
		int[] zs = new int[strs.length];
		for (int i = 0; i < strs.length; ++i) {
			for (int p = 0; p < strs[i].length(); ++p) if (strs[i].charAt(p) == 48) ++zs[i];
		}

		int x = zs[0], y = strs[0].length() - zs[0];
		for (int j = x; j <= m; ++j) for (int k = y; k <= n; ++k) last[j-x][k-y] = 1;
		for (int i = 1; i < strs.length; ++i) {
			x = zs[i]; y = strs[i].length() - zs[i];
			for (int j = 0; j <= m; ++j) {
				for (int k = 0; k <= n; ++k) {
					if (j+x <= m && k+y <= n) last[j][k] = Math.max(last[j][k], 1+last[j+x][k+y]);
				}
			}
		}

		int max = 0;
		for (int j = 0; j <= m; ++j) for (int k = 0; k <= n; ++k) if (last[j][k] > max) max = last[j][k];
		return max;
	}

	public static void main(String args[]) {
	}
}

