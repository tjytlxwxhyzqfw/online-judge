/**
 * 310 Minimum Height Trees
 * Performance: speed=%, memory=%
 *
 * TLE: dfs() from every leaves and compute the max dist from
 * every inner node to a leaf node then we know that nodes with
 * min dist are the answer
 *
 * Another BAD Idea: bfs() to compute the min dist from every inner node to a 
 * leaf node, then for some node u, the longest path is 1+max(d[v]) (!!!WRONG!!!)
 * for every neighbor node v of u; 
 *
 * idea one: delete leaves, delete leaves and delete leaves again
 * idea two: diameter of a tree
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		List<List<Integer>> g = new ArrayList<>();
		for (int i = 0; i < n; ++i) g.add(new ArrayList<>());
		int[] d = new int[n];
		for (int i = 0; i < n-1; ++i) {
			int u = edges[i][0], v = edges[i][1];
			{ g.get(u).add(v); g.get(v).add(u); }
			{ ++d[u]; ++d[v]; }
		}

		g.add(new ArrayList<>());
		for (int i = 0; i < d.length; ++i) if (n > 2 && d[i] == 1) { g.get(n).add(i); ++d[i]; }

		int k = n;
		Queue<Integer> q = new LinkedList<>();
		{ q.offer(n); q.offer(null); }
		boolean[] visited = new boolean[n];

		while (true) {
			Integer u = q.poll();
			if (u == null) { if (k <= 2) break; else { q.offer(null); continue; } }
			for (Integer v : g.get(u)) if (--d[v] == 1) { q.offer(v); --k; visited[v] = true; }
		}

		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < n; ++i) if (!visited[i]) result.add(i);
		return result;
	}

	public static void main(String args[]) {
		List<Integer> r1 = new Solution().findMinHeightTrees(6, new int[][]{{0, 1}, {0, 2}, {0, 3}, {3, 4}, {4, 5}});
		System.out.printf("r1: %s\n", r1);
	}
}

