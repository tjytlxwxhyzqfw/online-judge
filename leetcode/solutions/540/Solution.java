/**
 * 540	Single Element in a Sorted Array 57.4% Medium
 * Performance: speed=100%, memory=100%
 */

import java.util.*;

public class Solution {
	public int singleNonDuplicate(int[] nums) {
		int i = 0, j = nums.length;
		int k, left, rght;
		while (j-i >= 3) {
			k = i + (j-i) / 2;
			// System.out.printf("i=%d, j=%d, k=%d\n", i, j, k);
			if (nums[k] == nums[k-1]) { if (((k-1-i) & 1) == 0) i = k + 1; else j = k - 1; }
			else if (nums[k] == nums[k+1]) { if (((k-i) & 1) == 0) i = k + 2; else j = k; }
			else return nums[k];
		}
		return nums[i];
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.singleNonDuplicate(new int[]{1}) == 1;
		assert s.singleNonDuplicate(new int[]{1, 1, 2}) == 2;
		assert s.singleNonDuplicate(new int[]{1, 2, 2}) == 1;
		assert s.singleNonDuplicate(new int[]{1, 1, 2, 3, 3}) == 2;

		assert s.singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}) == 2; // leetcode
		assert s.singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}) == 10; // leetcode
	}
}

