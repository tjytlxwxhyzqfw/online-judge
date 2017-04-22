import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
	private int height, width;
	private int dp[][];
	
	public int uniquePaths(int m, int n) {
		if (m <= 0 || n <= 0)
			return 0;

		height = m;
		width = n;
		dp = new int[m][n];

		dp[m-1][n-1] = 1;
		for (int y = m-1; y >= 0; --y) {
			for (int x = n-1; x >= 0; --x) {
				if (y == m-1 && x == n-1)
					continue;
				int inc1 = (inMap(y+1, x) ? dp[y+1][x] : 0);
				int inc2 = (inMap(y, x+1) ? dp[y][x+1] : 0);
				dp[y][x] = inc1 + inc2;
			}
		}

		return dp[0][0];
	}

	private boolean inMap(int y, int x) {
		if (!(0 <= y && y < height))
			return false;
		if (!(0 <= x && x < width))
			return false;
		return true;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int height = scanner.nextInt();
		int width = scanner.nextInt();
		int total = new Solution().uniquePaths(height, width);
		System.out.printf("---> %d\n", total);
	}
}
