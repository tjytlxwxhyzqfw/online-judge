/**
 * 795	Number of Subarrays with Bounded Maximum 44.2% Medium
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public int numSubarrayBoundedMax(int[] a, int left, int rght) {
		int n = 0;
		for (int i = 0; i < a.length; ++i) {
			if (a[i] <= rght) {
				int j;
				for (j = i + 1; j < a.length && a[j] <= rght; ++j) {}
				n += count(a, i, j, left);
				i = j;
			}
		}
		return n;
	}

	// elements in a[begin:end) are <= rght
	int count(int[] a, int begin, int end, int left) {
		System.out.printf("find in [%d, %d)\n", begin, end);
		int lastGELeft = -1, n = 0;
		for (int i = begin; i < end; ++i) {
			if (a[i] < left)  {
				if (lastGELeft != -1) n += lastGELeft-begin+1;
			} else {
				lastGELeft = i;
				n += i-begin+1;
			}
			System.out.printf("i=%2d, lastGELeft=%2d, n=%2d\n", i, lastGELeft, n);
		}
		return n;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		// my solution failed in this case, because i use numOfGELeft
		// at 9, i only count [55 -> 9], [36->9], [55->9] and i missed [5->9]
		// then i change numOfGELeft to lastGELeft and i fixed this problem in my solution.
		assert s.numSubarrayBoundedMax(new int[]{73,55,36,5,55,14,9,7,72,52}, 32, 69) == 22;

		assert s.numSubarrayBoundedMax(new int[]{2}, 2, 3) == 1;
		assert s.numSubarrayBoundedMax(new int[]{2}, 3, 3) == 0;
		assert s.numSubarrayBoundedMax(new int[]{}, 2, 3) == 0;
		assert s.numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 2, 3) == 3;
		assert s.numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 1, 4) == 10;
		assert s.numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 1, 1) == 1;
		assert s.numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 2, 2) == 2;
		assert s.numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 3, 3) == 1;
		assert s.numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 5, 8) == 0;
		System.out.println("done");
	}
}

