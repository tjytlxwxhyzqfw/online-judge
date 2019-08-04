/**
 * 841: KeysAndRooms
 * Performance: speed=100%, memory=97%
 * tips: no need to covert rooms into a 2-d array (s=23%)
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		boolean[] v = new boolean[rooms.size()];
		dfs(0, v, rooms);
		for (int i = 0; i < v.length; ++i) if (!v[i]) return false;
		return true;
	}

	void dfs(int i, boolean[] v, List<List<Integer>> rooms) {
		v[i] = true;
		for (int j : rooms.get(i)) if (!v[j]) dfs(j, v, rooms);
	}

	public static void main(String args[]) {
	}
}

