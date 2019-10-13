/**
 * 797 All Paths From Source to Target 71.9% Medium 484/41
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public List<List<Integer>> allPathsSourceTarget(int[][] edges) {
		Map<Integer, List<Integer>> g = new HashMap<>();
		for (int i = 0; i < edges.length; ++i) {
			for (int j = 0; j < edges[i].length; ++j) {
				int v = edges[i][j];
				List<Integer> list = g.get(v);
				if (list == null) g.put(v, list = new ArrayList<>());
				list.add(i);
			}
		}

		boolean visited[] = new boolean[edges.length];
		return dfs(edges.length-1, visited, g);
	}

	// attention: actually this is backtracking rather than dfs.
	List<List<Integer>> dfs(int i, boolean[] visited, Map<Integer, List<Integer>> g) {
		if (i == 0) {
			List<Integer> list = new ArrayList<>();
			list.add(0);
			List<List<Integer>> result = new ArrayList<>();
			result.add(list);
			return result;
		}
		
		List<Integer> list = g.get(i);
		if (list == null) return null;

		List<List<Integer>> result = new ArrayList<>();
		visited[i] = true;
		for (Integer j : list) {
			if (visited[j]) continue;
			List<List<Integer>> paths = dfs(j, visited, g);
			if (paths != null) for (List<Integer> path : paths) { path.add(i); result.add(path);}
		}
		visited[i] = false;
		return result;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

