/**
 * 861 Score After Flipping Matrix 70.8% 325/84
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	int height, width;
	public int matrixScore(int[][] a) {
		if (a == null || a.length == 0 || a[0] == null || a[0].length == 0) return 0;
		height = a.length;
		width = a[0].length;
		return dfs(0, 0, a);
	}

	int dfs(int top, int left, int[][] a) {
		if (top == height && left == width) return score(a);
		int v = 0;
		if (0 <= top && top < height) {
			v = Math.max(v, dfs(top+1, left, a));
			flip(top, -1, a);
			v = Math.max(v, dfs(top+1, left, a));
			flip(top, -1, a);
		} else if (0 <= left && left < width) {
			v = Math.max(v, dfs(top, left+1, a));
			flip(-1, left, a);
			v = Math.max(v, dfs(top, left+1, a));
			flip(-1, left, a);
		} else {
			v = score(a);
		}
		return v;
	}

	void flip(int row, int col, int[][] a) {
		if (0 <= row && row < height) {
			for (int i = 0; i < width; ++i) a[row][i] = 1 - a[row][i];
		}
		if (0 <= col && col < width) {
			for (int i = 0; i < height; ++i) a[i][col] = 1 - a[i][col];
		}
	}

	int score(int[][] a) {
		int score = 0;
		for (int i = 0; i < a.length; ++i) {
			int bit = 1;
			for (int j = a[0].length-1; j >= 0; --j) {
				if (a[i][j] == 1) score += bit;
				bit <<= 1;
			}
		}
		return score;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.matrixScore(new int[][]{{0}}) == 1;
		assert s.matrixScore(new int[][]{{0, 0}, {0, 0}}) == 6;
		assert s.matrixScore(new int[][]{{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}}) == 39;
		assert s.matrixScore(new int[][]{{0, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 0, 0}}) == 42;
		
		System.out.println("done");
	}
}

