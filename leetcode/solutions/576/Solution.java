/**
 * 0576: OutOfBoundaryPaths
 * Performance: speed=%, memory=%
 * todo: discuss: solve this problem with dp
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	private static int[][] ds = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	private static int M = 1000000007;

	private int height, width;
	private Integer[][][] mem;

	public int findPaths(int height, int width, int n, int by, int bx) {
		this.height = height;
		this.width = width;
		mem = new Integer[n+1][height][width];
		return n >= 1 ? dfs(n, by, bx) : 0;
	}

	private int dfs(int k, int y, int x) {
		// verified by caller
		// 1. (y, x) in boudary
		// 2. (k >= 1)
		if (mem[k][y][x] != null) return mem[k][y][x];
		int n = 0;
		for (int i = 0; i < 4; ++i) {
			int p = y + ds[i][0], q = x + ds[i][1];
			if (p < 0 || p >= height || q < 0 || q >= width) ++n;
			else if (k > 1) n = (dfs(k-1, p, q) + n) % M;
		}
		return mem[k][y][x] = n;
	}

	public static void main(String args[]) {
		// new Solution().findPaths(1, 3, 3, 0, 1);
		// new Solution().findPaths(2, 2, 2, 0, 0);
		new Solution().findPaths(1, 1, 100, 0, 0);
	}
}

