/**
 * 756: PyramidTransitionMatrix
 * Performance: speed=93%, memory=96%
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Solution {
	public boolean pyramidTransition(String bottom, List<String> allowed) {
		char[][] g = new char[bottom.length()][bottom.length()];
		for (int i = 0; i < bottom.length(); ++i) g[0][i] = bottom.charAt(i);
		Map<String, List<Character>> to = new HashMap<>();
		for (String abc : allowed) {
			String key = abc.substring(0, 2);
			if (to.get(key) == null) to.put(key, new ArrayList<>());
			to.get(key).add(abc.charAt(2));
		}
		return dfs(1, 0, g, to);
	}

	// verified by caller:
	boolean dfs(int i, int j, char[][] g, Map<String, List<Character>> to) {
		if (i == g[0].length) return true;
		String key = new String(new char[]{g[i-1][j], g[i-1][j+1]});
		List<Character> cands = to.get(key);
		int y = i, x = j + 1; if (x + y >= g[0].length) { y = i+1; x = 0;}
		if (cands != null) for (char c : cands) { g[i][j] = c; if (dfs(y, x, g, to)) return true; }
		return false;
	}
	

	public static void main(String args[]) {
	}
}

