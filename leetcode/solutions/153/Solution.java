/**
 * 153 Find Minimum in Rotated Sorted Array
 *
 * 我没有第一时间想到二分, 看了答案才知道的
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int findMin(int[] nums) {
		if (nums==null || nums.length==0)
			throw new RuntimeException("?");

		int begin=0, end=nums.length-1;
		while (begin <= end) {
			System.out.printf("begin=%d, end=%d\n", begin, end);
			if (nums[begin] <= nums[end])
				return nums[begin];

			int mid = (begin+end)/2;
			if (mid == begin || mid == end)
				return Math.min(nums[begin], nums[end]);

			// 这个地方不要用 mid+1/mid-1来更新, 会有很多特殊情况的
			// 比如mid指向1: 900, 1, 3, 5, [begin]=900, [end]=5
			// 直接用mid 更新边界会保险一点
			if (nums[mid] > nums[begin]) {
				begin = mid;
			} else if (nums[mid] < nums[end]) {
				end = mid;
			} else {
				throw new RuntimeException("impossible");
			}
		}
		throw new RuntimeException("impossible");
	}

	public static void main(String args[]) {
		int nums[] = {50, 70, 90, 110, 1, 3, 5};
		int ans = new Solution().findMin(nums);
		System.out.printf("ans=%d\n", ans);
	}
}

