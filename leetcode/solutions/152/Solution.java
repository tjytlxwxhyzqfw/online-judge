/**
 * 152 - Maximum Product Subarray
 *
 * 题意: 最大连续子序列**积**
 * 
 * i号元素开始, 一定使用i号元素, 可以求得从i开始的最大正乘积, 和最小负乘积
 * 如果我们知道了(i+1)号元素的maxpos 和 minneg, 我们就能算出i的 maxpos 和 minneg
 *
 * 有一个**可能的**特殊情况, 就是负数会越界, 但是不影响最终的结果
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	private Integer maxpos[], minneg[];

	public int maxProduct(int nums[]) {
		maxpos = new Integer[nums.length];
		minneg = new Integer[nums.length];

		fill(nums.length-1, nums);
		print(nums.length-1);
		for (int i = nums.length-2; i >= 0; --i) {
			Integer pos = maxpos[i+1], neg = minneg[i+1];
			fill(i, nums);
			int current = nums[i];

			// 这里计算的时候, 要额外注意一个特殊情况
			// 如果maxpos[i+1] = 0, current=1, 
			// 那么最大乘积应该仅仅由 current 构成,
			// 不能去乘maxpos[i+1]
			if (current < 0) {
				if (neg != null) 
					maxpos[i] = current * neg;
				if (pos != null)
					minneg[i] = Math.min(current, current*pos);
			} else if (current > 0) {
				if (neg != null)
					minneg[i] = current * neg;
				if (pos != null)
					maxpos[i] = Math.max(current, current * pos);
			}
			print(i);
		}

		int result = nums[0];
		for (int i = 0; i < nums.length; ++i) {
			if (maxpos[i] != null)
				result = Math.max(result, maxpos[i]);
		}
		return result;
	}

	private void fill(int i, int nums[]) {
		int current = nums[i];
		// 你注意, 一个数可能没有 maxpos 或者 minneg
		if (current > 0) {
			maxpos[i] = current;
			minneg[i] = null;
		} else if (current < 0) {
			maxpos[i] = null;
			minneg[i] = current;
		} else {
			maxpos[i] = minneg[i] = 0;
		}
	}

	private int getMax(int begin, int arr[]) {
		int maxv = arr[begin];
		int prod = maxv;
		for (int i=begin+1; i<arr.length; ++i) {
			prod *= arr[i];
			if (prod > maxv)
				maxv = prod;
		}
		return maxv;
	}

	private void print(int i) {
		System.out.printf("%d: maxpos=%s, minneg=%s\n",
			i+1,
			maxpos[i]==null ? "null" : maxpos[i].toString(), 
			minneg[i]==null ? "null" : minneg[i].toString());
	}

	public static void main(String args[]) {
		int nums[] = {2, 3, -2, 4};
		int result = new Solution().maxProduct(nums);
		System.out.printf("---> [%d]\n", result);
	}

}

