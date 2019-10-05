/**
 * 764 Largest Plus Sign 44.5% Medium 307/69
 * Performance: speed=64%, memory=25%
 * todo: use only one matrix
 */

import java.util.*;

public class Solution {
	public int orderOfLargestPlusSign(int n, int[][] mines) {
		int[][] a = new int[n][n], b = new int[n][n], c = new int[n][n], d = new int[n][n];
		for (int i = 0; i < mines.length; ++i) {
			int y = mines[i][0], x = mines[i][1];
			a[y][x] = b[y][x] = c[y][x] = d[y][x] = -1;
		}

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				++a[i][j];
				if (j >= 1 && a[i][j] != 0) a[i][j] += a[i][j-1];
				++b[i][j];
				if (i >= 1 && b[i][j] != 0) b[i][j] += b[i-1][j];
			}
		}
		for (int i = n-1; i >= 0; --i) {
			for (int j = n-1; j >= 0; --j) {
				++c[i][j];
				if (j < n-1 && c[i][j] != 0) c[i][j] += c[i][j+1];
				++d[i][j];
				if (i < n-1 && d[i][j] != 0) d[i][j] += d[i+1][j];
			}
		}

		int k = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (a[i][j] == 0) continue;
				int left = a[i][j];
				int top = b[i][j];
				int right = c[i][j];
				int bottom = d[i][j];
				System.out.printf("[%2d, %2d]: (left=%d, right=%d, top=%d, bottom=%d)\n",
					i, j, left, right, top, bottom);
				k = Math.max(k, Math.min(Math.min(left, right), Math.min(top, bottom)));
			}
		}

		return k;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

