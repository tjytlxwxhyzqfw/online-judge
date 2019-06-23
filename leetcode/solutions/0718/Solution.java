/**
 *  718 Maximum Length of repeated subarray
 * Performance: speed=%, memory=%
 *
 *!!!WRONG!!!
 * This is consisdered as a DP problem !!!!!!!
 * (Although there is a bs+rabin-karp solution)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	
	public int findLength(int[] A, int[] B) {
		if (A.length == 0 || B.length == 0) return 0;

		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < A.length; ++i) {
			if (map.get(A[i]) == null) map.put(A[i], new ArrayList<>());
			map.get(A[i]).add(i);
		}

		int max = 0, current = 0, from = -1;
		for (int i = 0; i < B.length; ++i) {
			List<Integer> arr = map.get(B[i]);
			if (arr == null) {
				from = -1;
				current = 0;
				continue;
			}
			int index = ub(from, arr);
			if (index < arr.size() && (from == -1 || arr.get(index) - from == 1)) {
				if (++current > max) max = current;
				System.out.printf("found %d in array A@%d, from: %d -> %d, max=%d\n",
					B[i], arr.get(index), from, arr.get(index), max);
				from = arr.get(index);
			} else {
				from = -1;
				current = 0;
			}
		}
		return max;
	}

	private int ub(int x, List<Integer> a) {
		int i = 0, j = a.size();
		while (i < j) {
			int k = i + (j - i) / 2;
			if (a.get(k) <= x) i = k + 1;
			else j = k;
		}
		return i;
	}
	
	public static void main(String args[]) {
		new Solution().findLength(
			new int[]{3, 2, 1, 4, 7, 2, 3, 2, 1},
			new int[]{1, 2, 3, 2, 1});
	}
}

