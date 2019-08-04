/**
 * 464: CanIWin
 * Performance: speed=83%, memory=89%
 *
 * attention: get TLE or MLE with boolean mem[][], but accepted if i use map(int, bool) mem.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;

public class Solution {
	private int N;
	Map<Integer, Boolean> mem = new HashMap<>();
	public boolean canIWin(int n, int total) {
		if (total == 0) return true;

		int sum = 0; for (int i = 1; i <= n; ++i) sum += i;
		if (sum < total) return false;

		N = n;
		return dfs(0, total);
	}

	boolean dfs(int s, int k) {
		// the last picker has won
		if (k <= 0) return false;
		if (mem.get(s) != null) return mem.get(s);
		for (int i = N-1; i >= 0; --i)
			if ((s&(1<<i)) == 0 && !dfs(s|(1<<i), k-i-1)) { mem.put(s, true); return true; }
		mem.put(s, false); return false;
	}

	public static void main(String args[]) {
		boolean r1 = new Solution().canIWin(2, 4);
		System.out.printf("r1=%s\n", r1);
	}
}

