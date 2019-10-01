/**
 * 565 Array Nesting 53.7% Medium 637/83
 * Performance: speed=64%, memory=16%
 */

import java.util.*;

public class Solution {
	public int arrayNesting(int[] a) {
		int[] depth = new int[a.length];
		int max = 0;
		for (int i = 0; i < a.length; ++i) if (depth[i] == 0) max = Math.max(max, dfs(a, i, depth));
		return max;
	}

	int dfs(int a[], int i, int[] depth) {
		if (i == a[i]) return depth[i] = 1;
		if (depth[a[i]] > 0) return depth[i] = 1 + depth[a[i]];
		else if (depth[a[i]] < 0) return depth[i] = 1;
		else { depth[i] = -1; return depth[i] = 1 + dfs(a, a[i], depth); }
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.arrayNesting(new int[]{}) == 0;
		assert s.arrayNesting(new int[]{0}) == 1;
		assert s.arrayNesting(new int[]{0, 1}) == 1;
		assert s.arrayNesting(new int[]{1, 0}) == 2;
		assert s.arrayNesting(new int[]{0, 2, 1}) == 2;
		assert s.arrayNesting(new int[]{5, 4, 0, 3, 1, 6, 2}) == 4;
                System.out.println("done");
	}
}

/*
todo: do NOT use extra space !!!
*/

