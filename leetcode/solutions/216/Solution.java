/**
 * 216 Combination Sum III
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	int k, n;
	List<List<Integer>> result;
	public List<List<Integer>> combinationSum3(int k, int n) {
		result = new ArrayList<>();
		this.k = k;
		this.n = n;
		search(0, 0, 0, new boolean[9]);
		return result;
	}

	void search(int i, int used, int sum, boolean[] v) {
		if (used == k && sum == n) { add(v); return; }
		if (i >= 9 || sum > n || used > k) return;
		search(i+1, used, sum, v);
		v[i] = true; search(i+1, used+1, sum+i+1, v); v[i] = false;
	}

	void add(boolean[] v) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < v.length; ++i) if (v[i]) list.add(i+1);
		result.add(list);
	}

	public static void main(String args[]) {
	}
}

