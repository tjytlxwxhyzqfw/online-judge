/**
 * 934 Shortest Bridge
 * Performance: speed=69%, memory=73%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	static int[][] ds = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int h, w;

	public int shortestBridge(int[][] g) {
                h = g.length;
                w = g[0].length;
		boolean[][] v = new boolean[h][w];
                
                int y0 = -1, x0 = -1; 
                for (int i = 0; i < h; ++i) {
                        for (int j = 0; j < w; ++j) if (g[i][j] == 1) { y0 = i; x0 = j; break; }
                        if (y0 != -1) break;
                }
                dfs(y0, x0, g);

		Queue<Item> q = new LinkedList<>();
		for (int i = 0; i < h; ++i) {
			for (int j = 0; j < w; ++j) if (g[i][j] == 2) { q.offer(new Item(i, j, 0)); v[i][j] = true; }
		}
		while (!q.isEmpty()) {
			Item curr = q.poll();
                        for (int[] d : ds) {
                                int y = curr.y + d[0], x = curr.x + d[1]; // fool: y = i + d[0] ????
                                if (0 <= y && y < h && 0 <= x && x < w && !v[y][x]) {
					if (g[y][x] == 1) return curr.d;
					else if (g[y][x] == 0) { q.offer(new Item(y, x, curr.d+1)); v[y][x] = true; }
				}
			}
		}
		return Integer.MAX_VALUE;
	}


	public int shortestBridge_S9M9(int[][] g) {
		h = g.length;
		w = g[0].length;
		boolean[][] v = new boolean[h][w];

		int y = -1, x = -1;
		for (int i = 0; i < h; ++i) {
			for (int j = 0; j < w; ++j) if (g[i][j] == 1) { y = i; x = j; break; }
			if (y != -1) break;
		}
		dfs(y, x, g);

		int d = Integer.MAX_VALUE;
		for (int i = 0; i < h; ++i) for (int j = 0; j < w; ++j) if (g[i][j] == 2) d = Math.min(d, bfs(i, j, g));
		return d;
	}

	void dfs(int i, int j, int[][] g) {
		g[i][j] = 2;
		for (int[] d : ds) {
			int y = i + d[0], x = j + d[1]; // fool: x = i + d[1] ???
			if (0 <= y && y < h && 0 <= x && x < w && g[y][x] == 1) dfs(y, x, g);
		}
	}

	int bfs(int i, int j, int[][] g) {
		boolean[][] v = new boolean[h][w];
		for (int y = 0; y < h; ++y) for (int x = 0; x < w; ++x) v[y][x] = false;
		Queue<Item> q = new LinkedList<>();
		q.offer(new Item(i, j, 0));
		while (!q.isEmpty()) {
			Item curr = q.poll();
			for (int[] d : ds) {
				int y = curr.y + d[0], x = curr.x + d[1];  // fool: y = i + d[0] ????
				if (0 <= y && y < h && 0 <= x && x < w && !v[y][x]) {
					if (g[y][x] == 1) return curr.d;
					if (g[y][x] == 0) {
						v[y][x] = true;
						q.offer(new Item(y, x, curr.d+1));
					}
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	// attention: dont use dfs to solve shortest path problems !!!
	int dfs_bad(int i, int j, int[][] g) {
		if (g[i][j] == 1) return 0;
		System.out.printf("i=%d, j=%d, g=%d\n", i, j, g[i][j]);
		int ori = g[i][j], dist = Integer.MAX_VALUE;
		g[i][j] = 3;
		for (int[] d : ds) {
			int y = i + d[0], x = j + d[1];  // fool: x = i + d[1] ???
			// fool: g[y][x] == 1 must be adde as a condition !!!! (g[y][x] == 0) is not enough !!!!
			if (0 <= y && y < h && 0 <= x && x < w && (g[y][x] == 0 || g[y][x] == 1)) dist = Math.min(dist, dfs_bad(y, x, g));
		}
		g[i][j] = ori;

		System.out.printf("i=%d, j=%d, d=%d\n", i, j, dist);
		return dist == Integer.MAX_VALUE ? dist : 1 + dist;
	}

	public static void main(String args[]) {
	}
}

class Item {
	int y, x;
	int d;

	Item(int y, int x, int d) {
		this.y = y;
		this.x = x;
		this.d = d;
	}
}

