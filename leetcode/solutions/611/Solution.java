/**
 * 611 Valid Triangle Number 46.4% Medium 709/81
 * Performance: speed=%, memory=%
 */

/*
I am supposed to use a o(n^2) solution, big pity :(
todo: o(n^2) solution, 3 pointers
*/

import java.util.*;

public class Solution {
	public int triangleNumber(int[] a) {
		int n = 0;
		Arrays.sort(a);
		for (int i = ub(0, a, 0); i < a.length; ++i) {
			for (int j = i+1; j < a.length; ++j) {
				int p = lb(Math.abs(a[i]-a[j])+1, a, j+1); // fool: abs(i-j+1) ??? sb ???
				int q = ub(a[i]+a[j]-1, a, j+1); // fool: ub(i+j-1) ??? sb ???
				n += q - p;
				if (p <= i && i < q) --n;
				if (p <= j && j < q) --n;
				// System.out.printf("i=%d, j=%d, p=%d, q=%d, n=%d\n", i, j, p, q, n);
			}
		}
		return n;
	}

	int lb(int x, int[] a, int begin) {
		int i = begin, j = a.length;
		while (i < j) {
			int k = i + (j-i)/2;
			if (a[k] < x) i = k+1;
			else j = k;
		}
		return i;
	}

	int ub(int x, int[] a, int begin) {
		int i = begin, j = a.length;
		while (i < j) {
			int k = i + (j-i)/2;
			if (a[k] <= x) i = k+1;
			else j = k;
		}
		return i;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.triangleNumber(new int[]{}) == 0;
		assert s.triangleNumber(new int[]{1}) == 0;
		assert s.triangleNumber(new int[]{1, 1}) == 0;
		assert s.triangleNumber(new int[]{1, 1, 1}) == 1;
		assert s.triangleNumber(new int[]{1, 1, 1, 1}) == 4;
		assert s.triangleNumber(new int[]{2, 2, 3, 4}) == 3;
		assert s.triangleNumber(new int[]{0, 0, 0}) == 0;

                System.out.println("done");
	}
}

