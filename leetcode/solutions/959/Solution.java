/**
 * 959: Regions Cut By Slashes
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	private int[][] ds = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
	public int regionsBySlashes(String[] grid) {
		boolean[][] g = new boolean[grid.length * 3][grid[0].length() * 3];
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length(); ++j) {
				if (grid[i].charAt(j) == '/') g[i*3][j*3+2] = g[i*3+1][j*3+1] = g[i*3+2][j*3]= true;
				else if (grid[i].charAt(j) == '\\') g[i*3][j*3] = g[i*3+1][j*3+1] = g[i*3+2][j*3+2] = true;
			}
		}

		int n = 0;
		for (int i = 0; i < g.length; ++i) for (int j = 0; j < g[0].length; ++j) if (!g[i][j]) { ++n; dfs(i, j, g); }
		return n;
	}

	void dfs(int i, int j, boolean[][] g) {
		g[i][j] = true;
		for (int[] d : ds) {
			int y = i + d[0], x = j + d[1];
			if (0 <= y && y < g.length && 0 <= x && x < g[0].length && !g[y][x]) dfs(y, x, g);
		}
	}

	void printG(boolean[][] g) {
		for (int i = 0; i < g.length; ++i) {
			for (int j = 0; j < g[0].length; ++j) {
				System.out.printf("%c", g[i][j] ? 'x' : 'o');
			}
			System.out.printf("\n");
		}
	}

	public static void main(String args[]) {
	}
}

