import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
	private int height, width, dp[][];

	public int minPathSum(int grid[][]) {
		if (grid.length == 0)
			return 0;
		if (grid[0].length == 0)
			return 0;

		height = grid.length;
		width = grid[0].length;
		dp = new int[height][width];

		dp[height-1][width-1] = grid[height-1][width-1];
		for (int y = height-1; y >= 0; --y) {
			for (int x = width-1; x >= 0; --x) {
				if (y == height-1 && x == width-1)
					continue;

				int sum1 = (inGrid(y+1, x) ? dp[y+1][x] : Integer.MAX_VALUE);
				int sum2 = (inGrid(y, x+1) ? dp[y][x+1] : Integer.MAX_VALUE);
				dp[y][x] = grid[y][x] + Math.min(sum1, sum2);
			}
		}

		return dp[0][0];
	}

	private boolean inGrid(int y, int x) {
		if (!(0 <= y && y < height))
			return false;
		if (!(0 <= x && x < width))
			return false;
		return true;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
	}
}
