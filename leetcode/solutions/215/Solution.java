/**
 * 215 KthLargestElementInAnArray
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public int findKthLargest(int[] nums, int k) {
		qsort(nums, 0, nums.length-1, nums.length-k);
		return nums[nums.length-k];
	}

	void qsort(int[] a, int i, int j, int h) {
		if (i >= j) return;
		if (i+1 == j) { if (a[i] > a[j]) { int t = a[i]; a[i] = a[j]; a[j] = t; } return; }

		// median 3
		int t, k = i+(j-i)/2;
		if (a[i] > a[k]) { t = a[i]; a[i] = a[k]; a[k] = t; }
		if (a[i] > a[j]) { t = a[i]; a[i] = a[j]; a[j] = t; }
		if (a[k] > a[j]) { t = a[k]; a[k] = a[j]; a[j] = t; }

		int p = a[k]; a[k] = a[j-1]; a[j-1] = p;
		System.out.printf("p=%d\n", p);

		int left = i, rght = j--;
		while (true) {
			while (a[++i] < p);
			while (a[--j] > p);
			if (i < j) { t = a[i]; a[i] = a[j]; a[j] = t; }
			else break;
		}

		System.out.printf("a:, i=%d, j=%d\n", i, j);
		print(a, left, rght);

		{ t = a[i]; a[i] = a[rght-1]; a[rght-1] = t; }

		if (left <= h && h <= i-1) qsort(a, left, i-1, h);
		else if (i+1 <= h && h <= rght) qsort(a, i+1, rght, h);
	}

	static void print(int[] a) { print(a, 0, a.length-1); }
	static void print(int[] a, int i, int j) {
		for (int k = 0; k <= j; ++k) System.out.printf("%d, ", a[k]);
		System.out.println();
	}

	public static void main(String args[]) {
	}
}

