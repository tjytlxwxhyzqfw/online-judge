/**
 * 275 H-Index II 
 * Performance: speed=100%, memory=67%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int hIndex(int[] citations) {
		if (citations.length == 0) return 0;

		int max = 0;

		// This line lead to WA: citations=[100]
		// int i = citations[0], j = citations[citations.length-1];

		// copied but forgot to modify this line :<
		// int i = citations[0], j = citations[citations.length-1];

		int i = 0, j = citations[citations.length-1];
		while (i <= j) {
			int k = (i + j) / 2;
			if (check(k, citations)) {
				max = k;
				i = k + 1;
			} else j = k - 1;
		}
		return max;
	}

	private boolean check(int h, int[] citations) {
		int index = lb(h, citations);
		if ((citations.length - index) >= h) {
			return true;
		}
		return false;
	}

	private int lb(int x, int[] a) {
		int i = 0, j = a.length;
		while (i < j) {
			int k = (i + j) / 2;
			if (a[k] < x) i = k + 1;
			else j = k;
		}
		// i == j and a[i-1] < x <= a[i]
		return i;
	}

	public static void main(String args[]) {
		// int[] a1 = {0, 1, 3, 5, 6};
		// int r1 = new Solution().hIndex(a1);
		// System.out.printf("r1=%d\n", r1);

		int[] a2 = {6, 6, 6, 6, 6};
                int r2 = new Solution().hIndex(a2);
                System.out.printf("r2=%d\n", r2);
	}
}

