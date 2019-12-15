/**
 * 885 Spiral Matrix III 66.4% 137 200
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public int[][] spiralMatrixIII(int h, int w, int y, int x) {
		int[][] m = new int[h][w];
		int[][] r = new int[h*w][2];
		// 0=left, 1=top, 2=right, 3=down
		int d = 2;
		int[][] ddelta = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
		int[] nextYXD = new int[3];
		boolean start = true;

		for (int i = 1; i <= h*w; ++i) {
			m[y][x] = i;
			r[i-1][0] = y;
			r[i-1][1] = x;
			System.out.printf("--- after placed %d ---\n", i);
			printM(m);

			if (i == h*w) break;

			if (start) {
				start = false;
				y += ddelta[d][0];
				x += ddelta[d][1];
				if (!inM(y, x, m)) {
					nextYXD = next(y, x, m);
					y = nextYXD[0];
					x = nextYXD[1];
					d = nextYXD[2];
				}
				continue;
			}

			if (d == 0 && m[y-1][x] == 0) d = 1;
			else if (d == 1 && m[y][x+1] == 0) d = 2;
			else if (d == 2 && (y+1 >= m.length || m[y+1][x] == 0)) d = 3;
			else if (d == 3 && m[y][x-1] == 0) d = 0;

			y += ddelta[d][0];
			x += ddelta[d][1];
			if (!inM(y, x, m)) {
				nextYXD = next(y, x, m);
				y = nextYXD[0];
				x = nextYXD[1];
				d = nextYXD[2];
			}
		}

		return r;
	}

	boolean inM(int y, int x, int[][] m) {
		return 0 <= x && x < m[0].length && 0 <= y && y < m.length;
	}

	int[] next(int y, int x, int[][] m) {
		int n;
		if (x < 0) {
			if ((n = lEdge(y, m)) != -1) return new int[]{n, 0, 2};
			if ((n = tEdge(x, m)) != -1) return new int[]{0, n, 3};
			if ((n = rEdge(y, m)) != -1) return new int[]{n, m[0].length-1, 0};
		} else if (y < 0) {
			if ((n = tEdge(x, m)) != -1) return new int[]{0, n, 3};
			if ((n = rEdge(y, m)) != -1) return new int[]{n, m[0].length-1, 0};
			if ((n = bEdge(x, m)) != -1) return new int[]{m.length-1, n, 1};
		} else if (x >= m[0].length) {
			if ((n = rEdge(y, m)) != -1) return new int[]{n, m[0].length-1, 0};
			if ((n = bEdge(x, m)) != -1) return new int[]{m.length-1, n, 1};
			if ((n = lEdge(y, m)) != -1) return new int[]{n, 0, 2};
		} else if (y >= m.length) {
			if ((n = bEdge(x, m)) != -1) return new int[]{m.length-1, n, 1};
			if ((n = lEdge(y, m)) != -1) return new int[]{n, 0, 2};
			if ((n = tEdge(x, m)) != -1) return new int[]{0, n, 3};
		}
		return null;
	}

	int lEdge(int from, int[][] m) {
		for (int y = from-1; y >= 0; --y) if (m[y][0] == 0) return y;
		return -1;
	}

	int tEdge(int from, int[][] m) {
		for (int x = from+1; x < m[0].length; ++x) if (m[0][x] == 0) return x;
		return -1;
	}

	int rEdge(int from, int[][] m) {
		for (int y = from+1; y < m.length; ++y) if (m[y][m[0].length-1] == 0) return y;
		return -1;
	}

	int bEdge(int from, int[][] m) {
		for (int x = from-1; x >= 0; --x) if (m[m.length-1][x] == 0) return x;
		return -1;
	}

	void printM(int[][] m) {
		for (int i = 0; i < m.length; ++i) {
			for (int j = 0; j < m[0].length; ++j) {
				System.out.printf("%2d ", m[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		s.spiralMatrixIII(2, 2, 0, 0);
		s.spiralMatrixIII(2, 2, 0, 1);
		s.spiralMatrixIII(2, 2, 1, 0);
		s.spiralMatrixIII(2, 2, 1, 1);
		s.spiralMatrixIII(5, 6, 1, 4);
		s.spiralMatrixIII(5, 6, 0, 0);
		s.spiralMatrixIII(5, 6, 0, 5);
		s.spiralMatrixIII(5, 6, 4, 0);
		s.spiralMatrixIII(5, 6, 4, 5);
		s.spiralMatrixIII(1, 4, 0, 0);
		s.spiralMatrixIII(1, 4, 0, 1);
		s.spiralMatrixIII(1, 4, 0, 2);
		s.spiralMatrixIII(1, 4, 0, 3);
		s.spiralMatrixIII(1, 1, 0, 0);

		System.out.println("done");
	}
}

