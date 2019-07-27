/**
 * 491: IncreasingSubsequences
 * Performance: speed=9%, memory=84%
 *
 * important: how to avoid duplicate elements ?????
 * todo: find a more efficent way to de-duplicate
 */

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

public class Solution {
	private int[] a;
	private boolean[] used = new boolean[201];

	public List<List<Integer>> findSubsequences(int[] a) {
		this.a = a;
		List<List<Integer>> all = search(0);
		List<List<Integer>> result = new ArrayList<>();
		for (List<Integer> list : all) if (list.size() > 1) result.add(list);
		return new ArrayList<List<Integer>>(new HashSet<List<Integer>>(result));
	}

	private List<List<Integer>> search(int k) {	
		if (k == a.length) {
			return new ArrayList<>();
		}
		List<List<Integer>> result = search(k+1);
		int n = result.size();
		for (int i = 0; i < n; ++i) {
			if (result.get(i).get(0) >= a[k]) {
				List<Integer> list = new ArrayList<>();
				list.add(a[k]);
				list.addAll(result.get(i));
				result.add(list);
			}
		}
		if (!used[a[k]+100]) {
			List<Integer> self = new ArrayList<>();
			self.add(a[k]);
			result.add(self);
			used[a[k]+100] = true;
		}

		return result;
	}

	public static void main(String args[]) {
	}
}

