/**
 * 397 Integer Replacement
 * Performance: speed=50%, memory=10%
 *
 * todo: dis: use bit (i have thought about this way but too comlicate and then i turned to continue
 * using dp or dfs)
 */

import java.util.*;

public class Solution {

	public int integerReplacement(int n) {
		if (n == 0) return 1;
		return search(n);
	}

	int search(long n) {
		if (n == 1) return 0;
		if (n % 2 == 0) return 1 + search(n / 2);
		return 2 + Math.min(search((n+1)/2), search((n-1)/2));
	}
	
	public static void main(String args[]) {
	}
}

