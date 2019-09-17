/**
 * 498 Diagonal Traverse 45.7% Medium 402/250
 * Performance: speed=35%, memory=100%
 */

/*

0 1 5 6
2 4 7 10
3 8 9 11

*/

import java.util.*;

public class Solution {
	public int[] findDiagonalOrder(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null) return new int[]{};

		int h = matrix.length, w = matrix[0].length;
		int y = 0, x = 0, dy = -1, dx = 1, i = 0;
		int n = h * w;
		int[] a = new int[n];

		while (true) {
			a[i++] = matrix[y][x];
			{ y += dy; x += dx; }
			if (i == n) break;

			if (y < 0 || y >= h || x < 0 || x >= w) { dy *= -1; dx *= -1; }

			if (y >= h) { y = h - 1; x += 2; continue; }
			if (x >= w) { x = w - 1; y += 2; continue; }
			if (y < 0) { y = 0; continue; }
			if (x < 0) { x = 0; continue; }
		}

		return a;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		print(s.findDiagonalOrder(new int[][]{}));
		print(s.findDiagonalOrder(new int[][]{{0}}));
		print(s.findDiagonalOrder(new int[][]{{0, 1}, {2, 3}}));
		print(s.findDiagonalOrder(new int[][]{{0, 1, 5}, {2, 4, 6}, {3, 7, 8}}));
		print(s.findDiagonalOrder(new int[][]{{0, 1, 5, 6}, {2, 4, 7, 10}, {3, 8, 9, 11}}));
		print(s.findDiagonalOrder(new int[][]{{0, 1, 2, 3, 4, 5}}));
		print(s.findDiagonalOrder(new int[][]{{0}, {1}, {2}, {3}, {4}, {5}}));
	}

	public static void print(int[] a) {
		for (int i = 0; i < a.length; ++i) System.out.printf("%d, ", a[i]);
		System.out.println();
	}
}

