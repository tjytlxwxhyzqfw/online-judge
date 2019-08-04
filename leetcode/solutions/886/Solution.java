/**
 * 886 Possible Bipartition
 * Performance: speed=58%, memory=90%
 */

/*
 * attention: if ((Boolean)null == c) return false;  ==> null pointer exception
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	private Map<Integer, List<Integer>> g = new HashMap<>();

	public boolean possibleBipartition(int n, int[][] dislikes) {
		for (int i = 0; i < n; ++i) g.put(i, new ArrayList<>());
		for (int[] a : dislikes) { g.get(a[0]-1).add(a[1]-1); g.get(a[1]-1).add(a[0]-1); }
		Boolean[] v = new Boolean[n];
		for (int i = 0; i < n; ++i) if (v[i] == null && !dfs(i, v, true)) return false;
		return true;
	}

	boolean dfs(int u, Boolean[] v, Boolean c) {
		v[u] = c;
		for (int x : g.get(u)) if (v[x] == c || (v[x] == null && !dfs(x, v, !c))) return false;
		return true;
	}

	public static void main(String args[]) {
	}
}

