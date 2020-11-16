/**
 * 378 Kth Smallest Element in a Sorted Matrix
 * Performance: speed=34%, memory=22%
 *
 * find count <= k not < k !!!!!
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int kthSmallest(int[][] matrix, int t) {
		if (matrix.length == 0) return 0;
		if (matrix[0].length == 0) return 0;

		int i = matrix[0][0], j = matrix[matrix.length-1][matrix[0].length-1];
		while (i < j) {
			int k = (i + j) / 2;  // overflow
			int count = 0, min=matrix[matrix.length-1][matrix[0].length-1];
			int dup = 0;
			System.out.printf("k=%d\n", k);
			for (int y = 0; y < matrix.length; ++y) {
				if (matrix[y][0] > k) {
					if (matrix[y][0] < min) min = matrix[y][0];
					break;
				}
				int x = 0;
				for (; x < matrix.length; ++x) {
					if (matrix[y][x] == k) ++dup;
					if (matrix[y][x] > k) {
						if (matrix[y][x] < min) min = matrix[y][x];
						break;
					}
				}
				count += x;
			}
			System.out.printf("k=%d, count=%d, dup=%s\n", k, count, dup);
			if (dup > 0 && (count-dup+1) <= t && t <= count) return k;
			if (count + 1 == t) {
				return min;
			} else if (count < t - 1) {
				i = k + 1;
			} else {
				j = k;
			}
		}

		// WA @ case [[0]]
		// throw new RuntimeException("unexpected");

		// i == j, there is only one candidate in the search space
		return i;
	}

	public static void main(String args[]) {
	}
}

