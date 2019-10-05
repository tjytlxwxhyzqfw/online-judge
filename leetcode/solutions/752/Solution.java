/**
 * 752 Open the Lock 47.5% Medium 483/26
 * Performance: speed=89%, memory=51%
 */

/*
if you use dfs, you are trying use dfs to search all paths not all nodes.
if you search all nodes, you spend o(n) time. but if you seach all path, you have to be very careful.

assume that our target is "99" and we modify the largest digit on each wheel and show the num of dfs() calls:

max digit    # of dfs() calls
2            23
3            511
4            32821
5            6382879 (about 0.5 second)
6            N/A (almost endless)

in fact we are counting the diffierent ways from 00 -> 99 and as shown above the num of ways increase in
a index exploding speed (about o(2^n)).

dfs:
(1) traverse on the graph
(2) search for a ring
(3) search for a connected sub-graph
(4) search for paths (#vertices <= 20, rarely used)

**BFS is better than DFS in shortest path problems.**

*/

/* my blog: foryoundsc */

/*
00 -> 22

block: 11
00 -> 01 -> 02 -> 12 -> 22

bock: 11, 12
00 -> 01 -> 02 -> 03 -> 13 -> 23 -> 22
00 -> 10 -> 20 -> 21 -> 22

block, 11, 10, 12, 13, 24
00 -> 01 -> 02 -> 03 -> 04 -> 14 -> 15 -> 25 -> 35 --> 32 -> 22
*/

import java.util.*;


public class Solution {
	static int[] ds = new int[]{-1, 1};
	Scanner scanner = new Scanner(System.in);
	int numDFSCalls = 0;

	public int openLock(String[] deadends, String target) {
		// dfs("00", new HashSet<>(), new HashSet<>(), "11", 0);
		// System.out.printf("numDFSCalls=%d\n", numDFSCalls);
		return bfs(deadends, target);
	}

	int bfs(String[] deadends, String target) {
		Set<String> visited = new HashSet<>();
		Queue<String> q = new LinkedList<>();
		q.offer("0000");
		for (String s : deadends) visited.add(s);
		if (visited.contains("0000")) return -1;
		visited.add("0000");


		int depth = 0;
		while (!q.isEmpty()) {
			// all vertices in q are from depth `depth`
			int n = q.size();
			for (int i = 0; i < n; ++i) {
				String w = q.poll();
				if (w.equals(target)) return depth;
				// System.out.printf("current wheel: %s\n", w);
				for (String next : nextState(w)) {
					// System.out.printf("/t->%s\n", next);
					if (!visited.contains(next)) {
						q.offer(next);
						visited.add(next);
					}
				}
			}
			++depth;
			// new Scanner(System.in).nextLine();
		}


		return -1;
	}

	// attention: DO NOT USE DFS !!!
	int dfs(String wheel, Set<String> deadendSet, Set<String> visited, String target, int depth) {
		// System.out.printf("dfs visit: %2s (depth: %d)\n", wheel, depth);
		++numDFSCalls;
		// scanner.nextLine();

		if (deadendSet.contains(wheel)) return Integer.MAX_VALUE;
		if (wheel.equals(target)) return 0;

		visited.add(wheel);
		int d = Integer.MAX_VALUE;
		for (String next : nextState(wheel)) {
			if (!visited.contains(next)) {
				int d1 = dfs(next, deadendSet, visited, target, depth+1);
				d = Math.min(d, d1);
			}
		}
		visited.remove(wheel);

		return d < Integer.MAX_VALUE ? ++d : d;
	}

	// nextState
	List<String> nextState(String wheel) {
		StringBuilder b = new StringBuilder(wheel);
		List<String> list = new ArrayList<>();
		for (int i = 0; i < b.length(); ++i) {
			int digit = wheel.charAt(i) - 48;
                        for (int j = 0; j < ds.length; ++j) {
                                int next = (digit + ds[j] + 10) % 10 + 48;
                                b.setCharAt(i, (char)next);
				list.add(b.toString());
                        }
                        b.setCharAt(i, wheel.charAt(i));
                }
		return list;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		// System.out.println(s.openLock(new String[]{}, "99"));
		// System.out.println(s.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"));
		assert s.openLock(new String[]{}, "0001") == 1;
		assert s.openLock(new String[]{"0000"}, "0001") == -1;
		assert s.openLock(new String[]{"0001"}, "0001") == -1;
		assert s.openLock(new String[]{}, "0009") == 1;
		assert s.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202") == 6;
		assert s.openLock(new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"}, "8888") == -1;
		System.out.println("done");
	}
}

