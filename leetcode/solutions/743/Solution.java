/**
 * 743: NetworkDelayTime
 * heap: Performance: speed=98%, memory=73%
 * easy: Performance: speed=99%, memory=74%
 */

/* fake code solution
 --------------------
    q.offer(k)
    while (!q.isEmpty()) {
        u = q.poll()
        color[u] = 2
        for v in neighbors of u {
            if color[v] == 2 continue
            d = min(d, d[u] + w[u][v])
        }
        y, mind = -1, Inf
        for x in nodes {
            if color[x] == 0 and d[x] < Inf {
                y, mind = x, d[x]
            }
        }
        q.offer(y);
        max = math.max(max, d[y])
        color[y] = 1;
    }
 --------------------
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Solution {
	public int networkDelayTime(int[][] times, int n, int k) {
		return networkDelayTime_heap(times, n, k);
	}

	public int networkDelayTime_easy(int[][] times, int n, int k) {
		int[] d = new int[n+1];
		boolean[] s = new boolean[n];
                Arrays.fill(d, Integer.MAX_VALUE);
                d[k-1] = 0;
                
                int[][] g = new int[n][n];
                for (int i = 0; i < g.length; ++i) Arrays.fill(g[i], -1);
                for (int i = 0; i < times.length; ++i) g[times[i][0]-1][times[i][1]-1] = times[i][2];

		int max = Integer.MIN_VALUE;
                int visited = 0;

		while (true) {
			int u = n; for (int j = 0; j < n; ++j) if (!s[j] && d[u] > d[j]) u = j; if (u == n) break;
			s[u] = true; ++visited; max = Math.max(max, d[u]);
			for (int v = 0; v < n; ++v) if (g[u][v] != -1 && !s[v]) d[v] = Math.min(d[v], d[u]+g[u][v]);
		}

		return visited == n ? max : -1;
	}

	public int networkDelayTime_heap(int[][] times, int n, int k) {
		int[] d = new int[n], s = new int[n];
		Arrays.fill(d, Integer.MAX_VALUE);
		d[k-1] = 0;

		int[][] g = new int[n][n];
		for (int i = 0; i < g.length; ++i) Arrays.fill(g[i], -1);
		for (int i = 0; i < times.length; ++i) g[times[i][0]-1][times[i][1]-1] = times[i][2];

		int max = Integer.MIN_VALUE;
		int visited = 0;

		Queue<Integer> q = new PriorityQueue<>(new MyComparator(d));
		q.offer(k-1);
		while (!q.isEmpty()) {
			int u = q.poll();
			s[u] = 2;

			max = Math.max(max, d[u]);
			++visited;

			for (int v = 0; v < n; ++v) if (g[u][v] != -1 && s[v] != 2) d[v] = Math.min(d[v], d[u] + g[u][v]);
			// for (int i = 0; i < n; ++i) if (s[i] == 0 && d[i] != Integer.MAX_VALUE) { s[i] = 1; q.offer(i); }

			int mind = Integer.MAX_VALUE;
			for (int i = 0; i < n; ++i) if (s[i] == 0) mind = Math.min(mind, d[i]);
			if (mind == Integer.MAX_VALUE) continue;
			// fool: i forget to add contition: s[i] == 0
			for (int i = 0; i < n; ++i) if (s[i] == 0 && d[i] == mind) { s[i] = 1; q.offer(i); }
		}

		return visited == n ? max : -1;
	}

	public static void main(String args[]) {
	}
}

class MyComparator implements Comparator<Integer> {
	private int[] d;
	MyComparator(int[] d) {
		this.d = d;
	}
	@Override
	public int compare(Integer left, Integer rght) {
		return d[left] - d[rght];
	}
}

