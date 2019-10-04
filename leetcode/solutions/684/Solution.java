/**
 * 684 Redundant Connection 53.5% Medium 817/202
 * Performance: speed=22%, memory=66%
 */

/*
use disjoint-set to find ring:

for each edge=(u, v):
	if findSet(u) == findSet(v) then a circle will be made if you add edge (u, v)
	else union(u, v)

why ?

before (u, v) is added, if u, v has already be in same set, 
we know that there must a path from u -> v, then after (u, v)
is added, a circle emerges.

*/

import java.util.*;

public class Solution {
	public int[] findRedundantConnection(int[][] edges) {
		Map<Integer, List<Integer[]>> g = new HashMap<>();
		for (int i = 0; i < edges.length; ++i) {
			int u = edges[i][0]-1, v = edges[i][1]-1;
			List<Integer[]> ul = g.get(u);
			if (ul == null) g.put(u, ul = new ArrayList<>());
			ul.add(new Integer[]{v, i});
			List<Integer[]> vl = g.get(v);
			if (vl == null) g.put(v, vl = new ArrayList<>());
			vl.add(new Integer[]{u, i});
		}
		boolean[] visited = new boolean[edges.length];
		boolean[] circle = new boolean[edges.length];
		dfs(-1, 0, visited, circle, g);
		for (int i = edges.length-1; i >= 0; --i) if (circle[i]) return edges[i];
		return null; // not go to happen
	}

	int dfs(int p, int u, boolean[] visited, boolean[] circle, Map<Integer, List<Integer[]>> g) {
		visited[u] = true;
		System.out.printf("visit: %2d (from: %2d)\n", u+1, p+1);
		for (Integer[] pair : g.get(u)) {
			int v = pair[0], e = pair[1];
			if (visited[v]) {
				// a circle start from p has been found
				if   (v != p) {
					System.out.printf("circle found: %d -> %d -> %d\n", v+1, p+1, u+1);
					circle[e] = true;
					return v; // fool: return p ???
				} 
				// else continue
			} else {
				int circleEndAt = dfs(u, v, visited, circle, g);
				if (circleEndAt >= 0) {
					System.out.printf("in circle: %d -> %d\n", u+1, v+1);
					// a circle start from circleEndAt(backward end) has been found
					circle[e] = true;
					return circleEndAt == u ? -2 : circleEndAt;
				} else if (circleEndAt == -2) return -2;
				// else circleEndAt == -1, no circle found from u->v[0], continue
			}
		}
		return -1; // no circle found
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		// int[] e1 = s.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}});
		// System.out.printf("e1: %2d -> %2d\n", e1[0], e1[1]);
		// int[] e2 = s.findRedundantConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}});
		// System.out.printf("e2: %2d -> %2d\n", e2[0], e2[1]);
		int[] e3 = s.findRedundantConnection(new int[][]{{1, 3}, {1, 2}, {1, 4}, {4, 5}, {1, 5}});
		System.out.printf("e3: %2d -> %2d\n", e3[0], e3[1]);
		System.out.println("done");
	}
}

