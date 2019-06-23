/**
 * 200
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	private char trace = 'x';
	private int height = 0;
	private int width = 0;

	public int numIslands(char[][] grid) {
		height = grid.length;
		if (height == 0) {
			return 0;
		}
		width = grid[0].length;
		if (width == 0) {
			return 0;
		}

		int n = 0;
		for (int j = 0; j < grid.length; ++j) {
			for (int i = 0; i < grid[0].length; ++i) {
				if (walk(i, j, grid)) {
					// System.out.printf("+1 =====> (i=%d, j=%d)\n", i, j);
					++n;
				}
			}
		}

		return n;
    	}

	private boolean walk(int i, int j, char[][] g) {
		if (i < 0 || i >= width) {
			return false;
		}
		if (j < 0 || j >= height) {
			return false;
		}
		// System.out.printf("i=%d, j=%d, (w=%d, h=%d)\n", i, j, width, height);
		// System.out.printf("walk from %d, %d: %s, w=%d, h=%d\n", i, j, g[j][i], width, height);

		if (g[j][i] == trace) {
			return false;
		}
		if (g[j][i] == '0') {
			return false;
		}

		g[j][i] = trace;
		walk(i, j - 1, g);  // walk all four directions !!!
		walk(i, j + 1, g);
		walk(i - 1, j, g);  // walk all four directions !!!
		walk(i + 1, j, g);

		return true;
	}		

	public static void main(String args[]) {
		char[][] g1 = {
			{'1', '1', '0', '0', '0'},
			{'1', '1', '0', '0', '0'},
			{'0', '0', '1', '0', '0'},
			{'0', '0', '0', '1', '1'}};
		int n1 = (new Solution()).numIslands(g1);
		System.out.printf("n1=%d\n", n1);

		char[][] g2 = {
                        {'0', '1', '0', '1', '0'},
                        {'1', '1', '1', '1', '1'},
                        {'0', '1', '0', '1', '0'},
                        {'0', '1', '0', '1', '1'}};
                int n2 = (new Solution()).numIslands(g2);
                System.out.printf("n2=%d\n", n2);

		char[][] g3 = {{'0'}};
                int n3 = (new Solution()).numIslands(g3);
                System.out.printf("n3=%d\n", n3);
	}
}

