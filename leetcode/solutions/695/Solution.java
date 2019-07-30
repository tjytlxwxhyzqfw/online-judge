/**
 * 695: MaxAreaOfIsland
 * Performance: speed=52%, memory=37%
 * might be same as solutions in discuss
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	private static int[][] ds = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	public int maxAreaOfIsland(int[][] grid) {
		if (grid.length == 0 || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
		int maxA = 0;
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				if (grid[i][j] == 1) maxA = Math.max(maxA, dfs(i, j, grid));
			}
		}
		return maxA;
	}

	private int dfs(int y, int x, int[][] g) {
		// verified by caller
		//  (1) (y, x) in boundary
		//  (2) visited[y][x] = false
		g[y][x] = 2;
		int a = 1;
		for (int k = 0; k < 4; ++k) {
			int i = y + ds[k][0], j = x + ds[k][1];
			if (!(0 <= i && i < g.length && 0 <= j && j < g[0].length)) continue;
			if (g[i][j] == 1) a += dfs(i, j, g);
		}
		return a;
	}

	public static void main(String args[]) {
	}
}

