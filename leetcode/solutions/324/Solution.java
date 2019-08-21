/**
 * 324 Wiggle Sort II
 * Performance: speed=100%, memory=100%
 */

import java.util.*;

public class Solution {
	public void wiggleSort(int[] nums) {
		if (nums == null || nums.length <= 1) return;

		int k = (nums.length-1) / 2;
		kth(k, nums, 0, nums.length);
		int median = nums[k];
		// System.out.printf("median=%d\n", median);

		int end = nums.length - 2 + (nums.length & 1);
		int i = 1, j = end; 
		int p = 1;
		// System.out.printf("j=%d\n", j);
		while (((p & 1) == 1) || p <= j) {
			// System.out.printf("p=%d, v=%d, i=%d, j=%d => ", p, nums[p], i, j);
			// printArr(nums);

			if (nums[p] > median) { swap(nums, p, i); i += 2; p += 2; }
			else if (nums[p] < median) { swap(nums, p, j); j -= 2; }
			else p += 2;
			if (p >= nums.length) if ((p & 1) == 1) p = 0; else break;

		}

		// printArr(nums);
	}

	private void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	void kth(int k, int[] a, int begin, int end) {
		if (end - begin <= 1) return;
		if (end - begin == 2) {
			if (a[begin] > a[end-1]) { int t = a[begin]; a[begin] = a[end-1]; a[end-1] = t; }
			return;
		}

		int mid = begin + (end-begin)/2;
		if (a[begin] > a[mid]) { int t = a[begin]; a[begin] = a[mid]; a[mid] = t; }
		if (a[begin] > a[end-1]) { int t = a[begin]; a[begin] = a[end-1]; a[end-1] = t; }
		if (a[mid] > a[end-1]) { int t = a[mid]; a[mid] = a[end-1]; a[end-1] = t; }

		int p = a[mid]; a[mid] = a[end-2]; a[end-2] = p;
		int i = begin, j = end - 2;
		while (true) {
			while (a[++i] < p);
			while (a[--j] > p);
			if (i < j) {int t = a[i]; a[i] = a[j]; a[j] = t; }
			else break;
		}
		a[end-2] = a[i]; a[i] = p;

		if (k < i) kth(k, a, begin, i); // fool: use i !!!!!! dont use mid !!!!!
		else if (k >= i+1) kth(k, a, i+1, end); // fool: use i !!!!!! dont use mid !!!!!
	}

	public static void printArr(int[] a) {
		for (int i = 0; i < a.length; ++i) System.out.printf("%d, ", a[i]);
		System.out.println();
	}

	public static void main(String args[]) {
		new Solution().wiggleSort(new int[]{5});
		new Solution().wiggleSort(new int[]{2, 5});
		new Solution().wiggleSort(new int[]{5, 2});
		new Solution().wiggleSort(new int[]{2, 2, 2, 3, 3, 1, 1});
		new Solution().wiggleSort(new int[]{4, 5, 5, 6});
		new Solution().wiggleSort(new int[]{1, 5, 1, 1, 6, 4});
	}
}

