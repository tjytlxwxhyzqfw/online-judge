/**
 * 240 Search a 2D Matrix II
 * Performance: 6.79%, 98.56%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	private int ub(int x, int[] a) {
		int i = 0, j = a.length;
		while (i < j) {
			int k = (i + j) / 2;
			if (a[k] <= x) i = k + 1;
			else j = k;
		}
		// i = j && a[i-1] <= x < a[i]
		return i;
	}

	private int lb(int x, int[] a) {
		int i =0, j = a.length;
		while (i < j) {
			int k = (i + j) / 2;
			if (a[k] < x) i = k + 1;
			else j = k;
		}
		// i = j && a[i-1] < x <= a[i]
		return i;
	}
	
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix.length == 0) return false;
		if (matrix[0].length == 0) return false;

		int[] r = {0, matrix[0].length, 0, matrix.length};
		while (true) {
			System.out.printf("r=%s\n", r);
			r = search(matrix, target, r[0], r[1], r[2], r[3]);
			if (r.length == 0) return false;

			// This line leads to WA
			// case: [[4,6,9,10,15],[9,12,13,15,16]], target=14
			// if ((r[1] - r[0] == 1) && (r[3] - r[2] == 1)) return true;

			// There might be duplicated elements in the array, try [[1, 1]], 1
			if (matrix[r[2]][r[0]] == target) return true;  // !!! Important !!!

			// If the target shows up exactly in the boundary, the boundary won't be
			// changed any more then a TLE occurs.
			if (matrix[r[2]][r[1]-1] == target) return true;
			if (matrix[r[3]-1][r[0]] == target) return true;
		}
	}

	public int[] search(int[][] matrix, int target, int left, int rght, int top, int bottom) {
		int width = rght - left, height = bottom - top;

		int[] a1 = new int[width];
                for (int i = left; i < rght; ++i) a1[i-left] = matrix[top][i];
                int rght1 = ub(target, a1) + left;
		System.out.printf("rght1=%d\n", rght1);
                if (rght1 == left) return new int[]{};

                int[] a2 = new int[height];
                for (int i = top; i < bottom; ++i) a2[i-top] = matrix[i][left];
                int bottom1 = ub(target, a2) + top;
		System.out.printf("bottom1=%d\n", bottom1);
                if (bottom1 == top) return new int[]{};

                int[] a3 = new int[rght1 - left];
                for (int i = left; i < rght1; ++i) a3[i-left] = matrix[bottom1-1][i];
                int left1 = lb(target, a3) + left;
		System.out.printf("left1=%d\n", left1);
                if (left1 == rght1) return new int[]{};

                int[] a4 = new int[bottom1 - top];
                for (int i = top; i < bottom1; ++i) a4[i-top] = matrix[i][rght1-1];
                int top1 = lb(target, a4) + top;
		System.out.printf("top1=%d\n", top1);
                if (top1 == bottom1) return new int[]{};

		return new int[]{left1, rght1, top1, bottom1};
	}

	public static void main(String args[]) {
	}
}

