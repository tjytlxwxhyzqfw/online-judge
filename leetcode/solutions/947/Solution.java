/**
 * 947 Most Stones Removed with Same Row or Column
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	private int[][] s;
	private Map<Integer, List<Integer>> rows, cols;
	boolean[] v;

	// i have tried to reduce the dfs() line by just searching the two stones exactly adjecent to the current one
	// but i think it's a wrong direction and there is no solution there.
	public int removeStones_WA(int[][] stones) {
		int[] rowF = new int[10000], colF = new int[10000];
		int[] rowN = new int[stones.length], colN = new int[stones.length];
		Arrays.fill(rowF, -1);
		Arrays.fill(colF, -1);
		Arrays.fill(rowN, -1);
		Arrays.fill(colN, -1);

		for (int i = 0; i < stones.length; ++i) {
			int r = rowF[stones[i][0]], c = colF[stones[i][1]];
			rowF[stones[i][0]] = colF[stones[i][1]] = i;
			rowN[i] = r;
			colN[i] = c;
			System.out.printf("i=%d, y=%d, x=%d\n", i, stones[i][0], stones[i][1]);
			System.out.printf("row: %d -> %d, col: %d -> %d\n", i, r, i, c);
		}

		int n = 0;
		boolean[] v = new boolean[stones.length];
		// for (int i = 0; i < stones.length; ++i) if (!v[i]) { ++n; dfs(i, v, rowN, colN); }
		for (int i = 0; i < rowF.length; ++i) if (rowF[i] != -1 && !v[rowF[i]]) { ++n; dfs(rowF[i], v, rowN, colN); }
		for (int i = 0; i < colF.length; ++i) if (colF[i] != -1 && !v[colF[i]]) { ++n; dfs(colF[i], v, rowN, colN); }
		return stones.length - n;
	}

	void dfs(int i, boolean[] v, int[] rowN, int[] colN) {
		System.out.printf("dfs: i=%d\n", i);
		v[i] = true;
		if (rowN[i] == -1) System.out.printf("rowN[%d]=-1\n", i);
		if (colN[i] == -1) System.out.printf("colN[%d]=-1\n", i);
		if (rowN[i] != -1 && !v[rowN[i]]) dfs(rowN[i], v, rowN, colN);
		if (colN[i] != -1 && !v[colN[i]]) dfs(colN[i], v, rowN, colN);
	}

	public int removeStones_S56M40(int[][] stones) {
		s = stones;
		rows = new HashMap<>();
		cols = new HashMap<>();
		v = new boolean[stones.length];

		for (int i = 0; i < stones.length; ++i) {
			if (rows.get(stones[i][0]) == null) rows.put(stones[i][0], new ArrayList<>());
			if (cols.get(stones[i][1]) == null) cols.put(stones[i][1], new ArrayList<>());
			rows.get(stones[i][0]).add(i);
			cols.get(stones[i][1]).add(i);
		}

		int n = 0;
		for (int i = 0; i < stones.length; ++i) if (!v[i]) { ++n; dfs(i); }
		return stones.length - n;
	}

	void dfs(int i) {
		v[i] = true;
		if (rows.get(s[i][0]) != null) for (int j : rows.get(s[i][0])) if (!v[j]) dfs(j);
		if (cols.get(s[i][1]) != null) for (int j : cols.get(s[i][1])) if (!v[j]) dfs(j);
	}

	public static void main(String args[]) {
		new Solution().removeStones(new int[][]{{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}});
	}
}

