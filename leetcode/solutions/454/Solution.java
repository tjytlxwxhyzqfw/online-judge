/**
 * 454 4Sum II 
 * Performance: speed=7%, memory=99%
 *
 * !!!ATTENTION!!!
 * Use hash map rather than binary search
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		int[] left = new int[A.length * B.length];
		int[] rght = new int[C.length * D.length];

		int k = 0;
		for (int i = 0; i < A.length; ++i) {
			for (int j = 0; j < B.length; ++j) {
				left[k++] = A[i] + B[j];
			}
		}

		k = 0;
		for (int i = 0; i < C.length; ++i) {
			for (int j = 0; j < D.length; ++j) {
				rght[k++] = C[i] + D[j];
			}
		}

		Arrays.sort(rght);

		int count = 0;
		for (int i = 0; i < left.length; ++i) {
			int t = 0 - left[i];
			count += ub(t, rght) - lb(t, rght);
			System.out.printf("t=%d, count -> %d\n", t, count);
		}

		return count;
	}

	private int ub(int x, int[] a) {
		int i = 0, j = a.length;
		while (i < j) {
			int k = i + (j - i) / 2;
			if (a[k] <= x)
				i = k + 1;
			else
				j = k;
		}
		// i = j && a[i-1] <= x < a[i]
		return i;
	}

	private int lb(int x, int[] a) {
		int i = 0, j = a.length;
		while (i < j) {
			int k = i + (j - i) / 2;
			if (a[k] < x)
				i = k + 1;
			else
				j = k;
		}
		// i = j && a[i-] < x <= a[i]
		return i;
	}

	public static void main(String args[]) {
		int r1 = new Solution().fourSumCount(
			new int[]{1, 2, 1},
			new int[]{-2, -1, 2},
			new int[]{-1, 2, -1},
			new int[]{0, 2, -1});
		System.out.printf("r1=%d\n", r1);
	}
}

