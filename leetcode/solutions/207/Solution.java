/**
 * 201
 * 58%, 92%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> graph = new HashMap<>();
		for (int i = 0; i < prerequisites.length; ++i) {
			int to = prerequisites[i][0];
			int from = prerequisites[i][1];
			if (!graph.containsKey(from)) {
				graph.put(from, new ArrayList<>());
			}
			graph.get(from).add(to);
		}
		Map<Integer, Boolean> results = new HashMap<>();
		for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
			if (circle(entry.getKey(), graph, results)) {
				return false;
			}
		}
		return true;
	}

	private boolean circle(int from, Map<Integer, List<Integer>> g, Map<Integer, Boolean> r) {
		System.out.printf("search: %d\n", from);
		if (r.containsKey(from)) {
			Boolean result = r.get(from);
			if (result == null) {
				return true;
			}
			return result;
		}
		r.put(from, null);
		if (!g.containsKey(from)) {
			r.put(from, false);
			return false;
		}
		for (int to : g.get(from)) {
			if (circle(to, g, r)) {
				r.put(from, true);
				return true;
			}
		}
		r.put(from, false);
		return false;
	}

	public static void main(String args[]) {
		int[][] arr1 = {
			{2, 1},
			{3, 2},
			{4, 3},
			{5, 4},
			{1, 5}
		};
		boolean result1 = new Solution().canFinish(6, arr1);
		System.out.printf("result1: %s\n", result1);

		int[][] arr2 = {
			{2, 3},
			{4, 5},
			{6, 7}
		};
		boolean result2 = new Solution().canFinish(8, arr2);
		System.out.printf("result2: %s\n", result2);

		int[][] arr3 = {};
		boolean result3 = new Solution().canFinish(100, arr3);
		System.out.printf("result3: %s\n", result3);

		int[][] arr4 = {
			{1, 0},
			{2, 0},
			{3, 0},
			{4, 0},
			{5, 1},
			{6, 1},
			{7, 1},
			{8, 1}
		};
		boolean result4 = new Solution().canFinish(9, arr4);
		System.out.printf("result4: %s\n", result4);

		int[][] arr5 = {
			{0, 1},
			{0, 2},
			{0, 3},
			{0, 4},
			{4, 5},
			{4, 6},
			{4, 7},
			{7, 1}
		};
		boolean result5 = new Solution().canFinish(8, arr5);
		System.out.printf("result5: %s\n", result5);
	}
}

