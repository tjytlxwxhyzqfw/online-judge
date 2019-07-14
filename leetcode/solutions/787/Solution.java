/**
 * 787: CheapestFlightsWithinKStops
 * Performance: speed=65%, memory=68%
 */

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	private int D;
	private Integer[][] mem;
	private Map<Integer, List<Integer[]>> g;
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		D = dst;
		mem = new Integer[n][n+1];

		g = new HashMap<Integer, List<Integer[]>>();
		for (int i = 0; i < flights.length; ++i) {
			int u = flights[i][0], v = flights[i][1], w = flights[i][2];
			if (g.get(u) == null) g.put(u, new ArrayList<Integer[]>());
			g.get(u).add(new Integer[]{v, w});
		}

		return fly(src, k);
	}

	private int fly(int u, int k) {
		if (u == D) return 0;
		if (k < 0) return -1;
		if (mem[u][k] != null) return mem[u][k];
		if (g.get(u) == null) return mem[u][k] = -1;

		int minp = Integer.MAX_VALUE;
		for (Integer[] vw : g.get(u)) {
			int v = vw[0], w = vw[1];
			int p = fly(v, k-1);
			if (p != -1) minp = Math.min(minp, p+w);
		}
		if (minp == Integer.MAX_VALUE) minp = -1;
		return mem[u][k] = minp;
	}

	public static void main(String args[]) {
		int r1 = new Solution().findCheapestPrice(
			2, new int[][]{{0, 1, 100}}, 0, 1, 0);
		System.out.printf("r1=%d\n", r1);
	}
}

