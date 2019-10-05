/**
 * 789 Escape The Ghosts 55.9% Medium 141/279
 * Performance: speed=%, memory=%
 */

/*
attention: sooooo fool of you !
you run bfs() from target, and if you reach 0,0 first, you win else ghost win
*/

import java.util.*;

public class Solution {
	public boolean escapeGhosts(int[][] ghosts, int[] target) {
		int[][] drcs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

                String t = target[0] + "," + target[1];
                Set<String> g = new HashSet<>();
                for (int i = 0; i < ghosts.length; ++i) g.add(ghosts[i][0]+","+ghosts[i][1]);

                if (g.contains(t)) return false;
                if (g.contains("0,0")) return false;
                if (t.equals("0,0")) return true;

		Set<String> v = new HashSet<>();
                Queue<String> q = new LinkedList<>();
                q.offer(t);
                v.add(t); 
		while (!q.isEmpty()) {
			int n = q.size();
                        for (int i = 0; i < n; ++i) {
                                String[] p = q.poll().split(",");
                                System.out.printf("current: %s,%s\n", p[0], p[1]);
                                int y = Integer.parseInt(p[0]), x = Integer.parseInt(p[1]);
                                for (int j = 0; j < drcs.length; ++j) {
                                        String next = (y+drcs[j][0])+","+(x+drcs[j][1]);
                                        if (!v.contains(next)) {
						if (g.contains(next)) return false;
						if (next.equals("0,0")) return true;
                                                System.out.printf("\t->next: %s\n", next);
                                                q.offer(next); // fool: forgot to offer next to q !!!
                                                v.add(next);
                                        }
                                }
                        }
		}
		return false;
	}

	// attention: sooooo fool of you !
	// you run bfs() from target, and if you reach 0,0 first, you win else ghost win
	public boolean escapeGhosts_TLE(int[][] ghosts, int[] target) { // of course you get tle
		int[][] drcs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

		String targetS = target[0] + "," + target[1];
		Set<String> v = new HashSet<>();
		for (int i = 0; i < ghosts.length; ++i) v.add(ghosts[i][0]+","+ghosts[i][1]);

		if (v.contains(targetS)) return false;
		if (v.contains("0,0")) return false;
		if (targetS.equals("0,0")) return true;

		Queue<String> q = new LinkedList<>();
		q.offer("0,0");
		v.add("0,0"); // fool: forgot to add start point to v !!!
		int depth = 0;
		while (!q.isEmpty()) {
			// ghosts moves first
			for (int i = 0; i < ghosts.length; ++i) {
				int y = ghosts[i][0], x = ghosts[i][1];
				for (int j = 0; j < drcs.length; ++j)
					v.add((y+drcs[j][0]*(depth+1)) + "," + (x+drcs[j][1]*(depth+1)));
			}
			if (v.contains(targetS)) return false;
			int n = q.size();
			for (int i = 0; i < n; ++i) {
				String[] p = q.poll().split(",");
				System.out.printf("current: %s,%s\n", p[0], p[1]);
				int y = Integer.parseInt(p[0]), x = Integer.parseInt(p[1]);
				for (int j = 0; j < drcs.length; ++j) {
					String next = (y+drcs[j][0])+","+(x+drcs[j][1]);
					if (!v.contains(next)) {
						if (next.equals(targetS)) return true;
						System.out.printf("\t->next: %s\n", next);
						q.offer(next); // fool: forgot to offer next to q !!!
						v.add(next);
					}
				}
			}
			++depth;
		}
		return false;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		// failes on this because i forgot to add next into q !!!!!!
		assert s.escapeGhosts(new int[][]{{-1,2},{0,1},{-2,3},{0,1},{-5,0}}, new int[]{-2,0}); 

		// my first solution got tle on this case
		assert s.escapeGhosts(new int[][]{{5,0}, {-10,-2},{0,-5},{-2,-2},{-7,1}}, new int[]{7,7}) == false;

		assert s.escapeGhosts(new int[][]{{0, 0}}, new int[]{1, 1}) == false;
		assert s.escapeGhosts(new int[][]{{1, 1}}, new int[]{1, 1}) == false;
		assert s.escapeGhosts(new int[][]{{0, 0}}, new int[]{0, 0}) == false;
		assert s.escapeGhosts(new int[][]{{-1, 0}}, new int[]{0, 1});
		assert s.escapeGhosts(new int[][]{{1, 0}, {0, 3}}, new int[]{0, 1});
		assert s.escapeGhosts(new int[][]{{1, 0}}, new int[]{2, 0}) == false;
		assert s.escapeGhosts(new int[][]{{2, 0}}, new int[]{1, 0}) == false;


		System.out.println("done");
	}
}

