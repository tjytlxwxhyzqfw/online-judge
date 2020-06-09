/**
 * 154 Find Minimum in Rotated Sorted Array II
 * pass=40 ud=717/195 s=8 m=6
 */

import java.util.*;

public class Solution {
	public int findMin(int[] a) {
		if (a.length == 0) return 0;
		int i = 0, j = a.length-1;
		while (i < j) {
			if (a[i] < a[j]) return a[i];
			int k = i + (j-i) / 2;
			// System.out.printf("i=%2d, j=%2d, k=%2d, a[k]=%d\n", i, j, k, a[k]);
			if (a[k] > a[i]) {
				i = k + 1;
			} else if (a[k] < a[i]) {
				j = k;
			} else {
				++i;
			}
		}
		return a[i];
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.findMin(new int[]{3, 1, 3, 3}) == 1;
		assert s.findMin(new int[]{2, 2, 2, 0, 1}) == 0;
		assert s.findMin(new int[]{}) == 0;
		assert s.findMin(new int[]{5}) == 5;
		assert s.findMin(new int[]{5, 5, 5}) == 5;
		assert s.findMin(new int[]{1, 2}) == 1;
		assert s.findMin(new int[]{1, 2, 3}) == 1;
		assert s.findMin(new int[]{1, 2, 3, 4}) == 1;
		assert s.findMin(new int[]{4, 4, 4, 3, 4, 4, 4}) == 3;
		assert s.findMin(new int[]{2, 2, 2, 3, 3, 3}) == 2;

		System.out.println("done");
	}
}

