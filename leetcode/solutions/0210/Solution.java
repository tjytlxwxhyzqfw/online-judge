/**
 * 210: Course Schedule II
 *
 * Performance: speed=64%, memory=92%
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class Solution {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] depends = new int[numCourses];
		for (int i = 0; i < depends.length; ++i) depends[i] = 0;

		Map<Integer, List<Integer>> g = new HashMap<>();
		for (int i = 0; i < prerequisites.length; ++i) {
			depends[prerequisites[i][0]] += 1;
			if (g.get(prerequisites[i][1]) == null) g.put(prerequisites[i][1], new ArrayList<>());
			g.get(prerequisites[i][1]).add(prerequisites[i][0]);
		}

		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < depends.length; ++i) if (depends[i] == 0) q.offer(i);

		int[] order = new int[numCourses];
		int pointer = 0;
		while (q.size() != 0) {
			int head = q.poll();
			order[pointer++] = head;
			List<Integer> arr = g.get(head);
			if (arr == null) continue;
			for (Integer x : arr) if (--depends[x] == 0) q.offer(x);
		}

		if (pointer != numCourses) return new int[0];
		return order;
	}

	public static void main(String args[]) {
	}
}

