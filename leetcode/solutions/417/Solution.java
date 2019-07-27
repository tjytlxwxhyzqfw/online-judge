/**
 * 417: PacificAtlanticWaterFlow
 * Performance: speed=87%, memory=100%
 *
 * flow forward (bad)  :  top[y][x] = top[y-1][x] || top[y][x-1] || top[y+1][x] || top[y][x+1]
 * flow backward (good): top[y][x] = canFlowBackwardFromTopBorder(y, x)
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Solution {
	private int[][] directions = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	private int[][] matrix;
	private int height, width;
	public List<List<Integer>> pacificAtlantic(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null) return new ArrayList<>();

		this.matrix = matrix;
		height = matrix.length;
		width = matrix[0].length;

		boolean[][] pac = new boolean[height][width], alt = new boolean[height][width];
		for (int i = 0; i < height; ++i) { dfs(i, 0, pac); dfs(i, width-1, alt); }
		for (int j = 0; j < width; ++j) { dfs(0, j, pac); dfs(height-1, j, alt); }
		
		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				if (pac[i][j] && alt[i][j]) result.add(Arrays.asList(i, j));
			}
		}
		return result;
	}

	private void dfs(int y, int x, boolean[][] v) {
		// verified by caller
		v[y][x] = true;
		for (int i = 0; i < 4; ++i) {
			int y1 = y+directions[i][0], x1 = x+directions[i][1];
			if (0 <= y1 && y1 < height && 0 <= x1 && x1 < width)
				if  (!v[y1][x1] && matrix[y][x] <= matrix[y1][x1]) dfs(y1, x1, v);
		}
	}

	public static void main(String args[]) {
		int[][] matrix = new int[][]{{0}};
		List<List<Integer>> r = new Solution().pacificAtlantic(matrix);
		for (List<Integer> list : r)
			System.out.printf("(%2d, %2d)=%2d\n", list.get(0), list.get(1), matrix[list.get(0)][list.get(1)]);
	}
}

