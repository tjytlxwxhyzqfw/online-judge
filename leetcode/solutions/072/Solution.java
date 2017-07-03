import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
	private int height, width;

	public int minDistance(String s, String t) {
		if (s == null || s.length() == 0)
			return (t == null ? 0 : t.length());
		if (t == null || t.length() == 0)
			return s.length();

		int m = s.length(), n = t.length();
		height = m+1;
		width = n+1;
		int dp[][] = new int[height][width];

		for (int i = 0; i <= m; ++i)
			dp[i][n] = m-i;
		for (int j = 0; j <= n; ++j)
			dp[m][j] = n-j;

		for (int i = m-1; i >= 0; --i) {
			for (int j = n-1; j >= 0; --j) {
				int rght = (inGrid(i, j+1) ? dp[i][j+1]+1 : Integer.MAX_VALUE);
				int down = (inGrid(i+1, j) ? dp[i+1][j]+1 : Integer.MAX_VALUE);
				int rgdo = (inGrid(i+1, j+1) ? dp[i+1][j+1] : Integer.MAX_VALUE);
				if (s.charAt(i) != t.charAt(j))
					if (rgdo != Integer.MAX_VALUE)
						++rgdo;

				dp[i][j] = Math.min(Math.min(rght, down), rgdo);
				System.out.printf("dp[%2d][%2d]: %2d\n", i+1, j+1, dp[i][j]);
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
		String s = scanner.next();
		String t = scanner.next();
		int total = new Solution().minDistance(s, t);
		System.out.printf("total: %d\n", total);
	}
}
