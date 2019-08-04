/**
 * 802 FindEventualSafeStates
 * Performance: speed=100%, memory=97%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<Integer> eventualSafeNodes(int[][] graph) {
		boolean[] s = new boolean[graph.length], v = new boolean[graph.length];
		for (int i = 0; i < graph.length; ++i) if (!v[i]) dfs(i, graph, v, s);
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < s.length; ++i) if (s[i]) list.add(i);
		return list;
	}

	boolean dfs(int i, int[][] g, boolean[] v, boolean[] s) {
		System.out.printf("i=%d\n", i);
		v[i] = true;
		boolean safe = true;
		for (int j : g[i]) if (!(v[j] ? s[j] : dfs(j, g, v, s))) { safe = false; break; }
		System.out.printf("s[%d]=%s\n", i, safe);
		return s[i] = safe;
	}

	public static void main(String args[]) {
	}
}

