import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
	private int path[];
	public List<List<String>> results = new ArrayList<List<String>>();

	public List<List<String>> solveNQueens(int n) {
		if (n <= 0)
			return results;
		length = n;
		mask = new Mask(n);
		path = new int[n];
		dfs(0);
		return results;
	}

	private void dfs(int i) {
		if (i == length) {
			char board[][] = new char[length][length];
			for (int x = 0; x < length; ++x)
				for (int y = 0; y < length; ++y)
					board[x][y] = (y == path[x] ? 'Q' : '.');
			List<String> result = new ArrayList<String>();
			for (int x = length-1; x >= 0; --x)
				result.add(new String(board[x]));
			results.add(result);

			//System.out.printf("--- result ---\n");
			//for (String ln : result)
				//System.out.printf("%s\n", ln);

			return;
		}

		//printPath(i);
		//System.out.printf("%d\n", i);

		for (int j = 0; j < length; ++j) {
			if (mask.check(i, j)) {
				mask.set(i, j, true);
				path[i] = j;
				dfs(i+1);
				mask.set(i, j, false);
			}
		}
	}

	private void  printPath(int i) {
		System.out.printf("path: - ");
		for (int k = 0; k < i; ++k)
			System.out.printf("(%3d, %3d) - ", k, path[k]);
		System.out.printf("\n");
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		List<List<String>> results = new Solution().solveNQueens(0);
		for (List<String> result : results) {
			System.out.printf("------------------------------\n");
			for (String ln : result)
				System.out.printf("%s\n", ln);
		}

		System.out.printf("total: %d\n", results.size());
	}
}
