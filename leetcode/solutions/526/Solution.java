/**
 * 526	Beautiful Arrangement 55.3% Medium 483/116
 * Performance: speed=%, memory=%
 * todo: recursive
 */

/*
	from 1 to 15: 43% 5%
	from 15 to 1: 94% 5%
*/

import java.util.*;

public class Solution {
	public int countArrangement(int n) {
		if (n == 0) return 0;
		boolean[] a = new boolean[n];
		return backtracking(a, n);
	}

	int backtracking(boolean[] a, int k) {
		if (k == 0) return 1;

		int count = 0;
		for (int i = 0; i < a.length; ++i) {
			if (!a[i] && (k % (i+1) == 0 || (i+1) % k == 0))
				{ a[i] = true; count += backtracking(a, k-1); a[i] = false; }
		}

		return count;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.countArrangement(0) == 0;
		assert s.countArrangement(1) == 1;
		assert s.countArrangement(2) == 2;
		assert s.countArrangement(3) == 3;
		assert s.countArrangement(4) == 8;
		assert s.countArrangement(6) == 36;
		assert s.countArrangement(13) == 4237;
		assert s.countArrangement(15) == 24679;
	}
}

