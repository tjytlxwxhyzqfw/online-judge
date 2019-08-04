/**
 * 785 IsGraphBipartite?
 * bfs_0: Performance: speed=64%, memory=62%
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {
	 public boolean isBipartite(int[][] graph) {
                char[] s = new char[graph.length];
                Queue<Integer> q = new LinkedList<>();
                int n = 0;
		// this loop is better, but a little bit more in-efficient than bfs_0: s=62, m=56
		for (int i = 0; i < s.length; ++i) {
			if (s[i] != 0) continue;
                        { q.offer(i); s[i] = 1; }
                        while (!q.isEmpty()) {
                                int u = q.poll();
                                ++n;
                                for (int v : graph[u]) {
                                        if (s[v] == 0) { s[v] = (char)(3 - s[u]); q.offer(v); }
                                        else if (s[v] == s[u]) return false;
                                }
                        }
                }

                return true;
        }

	public boolean isBipartite_bfs_0(int[][] graph) {
		char[] s = new char[graph.length];
		Queue<Integer> q = new LinkedList<>();
		int n = 0;
		while (true) {
			if (n == graph.length) break;
			for (int i = 0; i < s.length; ++i) if (s[i] == 0) { q.offer(i); s[i] = 1; break; }
			while (!q.isEmpty()) {
				int u = q.poll();
				++n;
				for (int v : graph[u]) {
					if (s[v] == 0) { s[v] = (char)(3 - s[u]); q.offer(v); }
					else if (s[v] == s[u]) return false;
				}
			}
		}

		return true;
	}
	public static void main(String args[]) {
	}
}

