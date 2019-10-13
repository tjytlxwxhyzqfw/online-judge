/**
 * 807 Max Increase to Keep City Skyline 82.1% Medium 627/167
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public int maxIncreaseKeepingSkyline(int[][] grid) {
		int[] h = new int[grid.length], v = new int[grid[0].length];
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				h[i] = Math.max(h[i], grid[i][j]);
				v[j] = Math.max(v[j], grid[i][j]);
			}
		}
		int sum = 0;
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				sum += Math.min(h[i], v[j]) - grid[i][j];
			}
		}

		return sum;
	}
	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

