/**
 * 797 All Paths From Source to Target 71.9% Medium 484/41
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public List<List<Integer>> allPathsSourceTarget(int[][] edges) {
		Map<Integer, List<Integer>> g = new HashMap<>();
		for (int i = 0; i < edges.length; ++i) {
			List<Integer> list = new ArrayList<>();
			for (int j = 0; j < edges[i].length; ++j) list.add(edges[i][j]);
			g.put(i, list);
		}
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

