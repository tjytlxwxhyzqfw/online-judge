/**
 * 0304: Range Sum Query 2D - Immutable
 * Performance: speed=98%, memory=100%
 */

import java.util.ArrayList;
import java.util.List;

public class NumMatrix {
	private int[][] mat;
	private int[][] sums;
	private int width=0, height=0;
	
	public NumMatrix(int[][] matrix, boolean oldone) {
		mat = matrix;
		if (matrix.length == 0) {
			return;
		}
		height = matrix.length;
		width = matrix[0].length;
		sums = new int[height][width];
		sums[0][0] = matrix[0][0];
		for (int x = 1; x < width; ++x) sums[0][x] = mat[0][x] + sums[0][x-1];
		for (int y = 1; y < height; ++y) sums[y][0] = mat[y][0] + sums[y-1][0];
		for (int y = 1; y < height; ++y) {
			for (int x = 1; x < width; ++x) {
				sums[y][x] = mat[y][x] - sums[y-1][x-1] + sums[y-1][x] + sums[y][x-1];
			}
		}
	}

	public NumMatrix(int[][] matrix) {
                mat = matrix;
                if (matrix.length == 0) {
                        return;
                }
                height = matrix.length;
                width = matrix[0].length;
                sums = new int[height+1][width+1];
		/// !!! IMPORTANT !!!
		for (int y = 1; y <= height; ++y)
			for (int x = 1; x <= width; ++x)
				sums[y][x] = mat[y-1][x-1] - sums[y-1][x-1] + sums[y-1][x] + sums[y][x-1];
        }
    
	public int sumRegion(int y1, int x1, int y2, int x2) {
		// int ans = sums[y2][x2];
		// if (x1-1 >= 0) ans -= sums[y2][x1-1];
		// if (y1-1 >= 0) ans -= sums[y1-1][x2];
		// if (y1-1 >= 0 && x1-1>=0) ans += sums[y1-1][x1-1];
		// return ans;

		return sums[y2+1][x2+1] - sums[y1][x2+1] - sums[y2+1][x1] + sums[y1][x1];
	}

	public static void main(String args[]) {
	}
}

