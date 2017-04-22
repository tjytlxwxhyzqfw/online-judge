import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//用的是051的代码, 稍作了修改

public class Solution {

	private class Mask {
		private boolean cols[], msts[], subs[];
		private int msti, subi;

		public Mask(int n) {
			cols = new boolean[n];
			msts = new boolean[2*n-1];
			subs = new boolean[2*n-1];
		}

		public boolean check(int x, int y) {
			cal(x, y);
			return !cols[y] && !msts[msti] && !subs[subi];
		}

		public void set(int x, int y, boolean v) {
			cal(x, y);
			cols[y] = v;
			msts[msti] = v;
			subs[subi] = v;
		}

		private void cal(int x, int y) {
			msti = y - x + length - 1;
			subi = y + x;
		}
	}

	private Mask mask;
	private int length;
	public int count = 0;

	public int totalNQueens(int n) {
		if (n <= 0)
			return 0;
		length = n;
		mask = new Mask(n);
		dfs(0);
		return count;
	}

	private void dfs(int i) {
		if (i == length) {
			++count;
			return;
		}

		for (int j = 0; j < length; ++j) {
			if (mask.check(i, j)) {
				mask.set(i, j, true);
				dfs(i+1);
				mask.set(i, j, false);
			}
		}
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int count = new Solution().totalNQueens(3);
		System.out.printf("total: %d\n", count);
	}
}
