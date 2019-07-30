/**
 * 542: 01Matrix
 * Performance: speed=%, memory=%
 * todo: discuss: solve this problem with dp: from both top-left to right bottom and the reversed order.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {
	static private int[][] ds = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	public int[][] updateMatrix(int[][] matrix) {
		Queue<int[]> q = new LinkedList<>();
		int h = matrix.length, w = matrix[0].length;
		for (int i = 0; i < h; ++i) {
			for (int j = 0; j < w; ++j) {
				if (matrix[i][j] == 0) q.offer(new int[]{i, j});
				else matrix[i][j] = Integer.MAX_VALUE;
			}
		}
		while (!q.isEmpty()) {
			int[] item = q.poll();
			for (int i = 0; i < 4; ++i) {
				int y = item[0]+ds[i][0], x = item[1]+ds[i][1];
				if (!(0 <= y && y < h && 0 <= x && x < w)) continue;
				int d = matrix[item[0]][item[1]];
				if (matrix[y][x] > d + 1) {
					matrix[y][x] = d + 1;
					q.offer(new int[]{y, x});
				}
			}
		}
		return matrix;
	}

	public static void main(String args[]) {
	}
}

