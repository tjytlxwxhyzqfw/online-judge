/**
 * 162 - Find Peak Element 
 *
 * 题意: 给定一个纯数字数组, 找到这样的元素:
 *	(1) 它左边的元素比它小
 *	(2) 它右边的元素也比它小
 *
 * 解法:
 * (1) 第一个元素一定是升上去的. (否则, 返回第一个元素)
 * (2) 最后一个元素一定是降下来的. (否则, 返回最后一个元素)
 * (3) 任取元素k
 * 		(3.1) 若 / left &lt; k &lt; rght: 则 [k:] 必有峰值
 *		(3.2) 若 \ left &gt; k &gt; rght; 则 [:k] 必有峰值
 *		(3.3) 如果 /\ left &lt; k &gt; rght; 则 k就是峰值
 *		(3.4) 如果 \/ 那么k的两边都有峰值, 注意, 此时一定要用 k+1或者k-1来更新边界值,
 *			否则会有BUG, 可能会一直停留在边界上: i==j.
 *
 * (3.4) 具体对应什么BUG还不清楚,
 * 但是用 i=k/j=k更新的时候, 真的会出很多问题, 一定要格外仔细
 * 有可能, 我说有可能, while循环应该写成 while(i'<'j), 并且里面检查i+1==j的情况.
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

