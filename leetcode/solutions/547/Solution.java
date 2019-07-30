/**
 * 547: FriendCircles
 * Performance: speed=100%, memory=46%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int findCircleNum(int[][] m) {
		int n = 0;
		for (int i = 0; i < m.length; ++i) if (m[i][i]==1) { ++n; dfs(m, i); }
		return n;
	}

	void dfs(int[][] m, int i) {
		// verified by caller
		// (1) i in boundary
		// (2) i is never visited
		m[i][i] = 0;
		for (int j = 0; j < m[0].length; ++j) {
			if (m[i][j] == 1 && m[j][j] == 1) dfs(m, j);
		}
	}

	public static void main(String args[]) {
	}
}

