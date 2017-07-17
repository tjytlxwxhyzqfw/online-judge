/**
 * 162 - Find Peak Element 
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int findPeakElement(int nums[]) {
		if (nums==null || nums.length==0)
			throw new IllegalArgumentException();
		if (nums.length == 1)
			return 0;
		if (nums[0] > nums[1])
			return 0;
		if (nums[nums.length-1] > nums[nums.length-2])
			return nums.length-1;

		int i=1, j=nums.length-2;
		while (i <= j) {
			// 如果你是按照 i=k/j=k 更新的, 一定要处理这种情况!!!!
			if (i+1 == j) {
				return nums[i] < nums[j] ? j : i;
			}

			int k = (i+j)/2;
			int current=nums[k], left=nums[k-1], rght=nums[k+1];
			if (left < current && current > rght) {
				return k; // /\
			} else if (left < current && current < rght) {
				i = k; // /
			} else if (left > current && current > rght) {
				j = k; // \
			} else if (left > current && current < rght) {
				if (k-1 >= i)
					j = k-1;
				else if (k+1 <= j)
					i = k+1;
				else
					return k; // 应该是只有初始情况会发生 
			} else {
				throw new IllegalArgumentException();
			}
		}

		throw new IllegalArgumentException();
	}

	public static void main(String args[]) {
		int nums[] = {3, 2, 1, 0};
		int ans = new Solution().findPeakElement(nums);
		System.out.printf("ans=%d\n", ans);
	}
}

