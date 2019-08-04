/**
 * 851: LoudAndRich
 * Performance: speed=40%, memory=30%
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class Solution {
	public int[] loudAndRich(int[][] richer, int[] quiet) {
		Map<Integer, List<Integer>> g = new HashMap<>();
		for (int[] v : richer) {
			if (g.get(v[1]) == null) g.put(v[1], new ArrayList<>());
			g.get(v[1]).add(v[0]);
		}
		int[] answer = new int[quiet.length]; Arrays.fill(answer, -1);
		for (int i = 0; i < quiet.length; ++i) dfs(i, g, quiet, answer);
		return answer;
	}

	int dfs(int u, Map<Integer, List<Integer>> g, int[] quiet, int[] answer) {
		if (answer[u] != -1) return answer[u];
		if (g.get(u) == null || g.get(u).size() == 0) { return answer[u] = u; }
		answer[u] = u;
		for (int v : g.get(u)) {
			int x = dfs(v, g, quiet, answer);
			if (quiet[x] < quiet[answer[u]]) answer[u] = x;
		}
		return answer[u];
	}

	public static void main(String args[]) {
	}
}
