/**
 * 334 Increasing Triplet Subsequence
 * Performance: speed=100%, memory=95%
 *
 * review logs
 * -----------
 * 20200610: 下面的想法确实不太好想. 但是我有另一个想法. 对每个元素, 找到其左边最小值和右边最大值. 
 * 然后对于每个元素, 检查一下左右就能解决问题. 一共遍历3次数组(或2次)
 */

import java.util.*;

public class Solution {
	public boolean increasingTriplet(int[] nums) {
		int x = Integer.MAX_VALUE, y = Integer.MAX_VALUE;
		// (20200610) x=最小, y比x大并且y的左边必有一个比y小的数.
		// 因此只要新元素比y大, 那么必有递增3元组的存在.
		// 那么是不是所有递增3元组都能被下面这个循环捕捉到呢? 是的
		// x ... y ... z (x < y < z)
		// 设首先遇到x=最小. 从x到y的过程中, x只会继续减小. y怎么样随便.
		// 当到达y的时候, y必然有值.
		// 在到达z的过程中, x, y随便变化, 但只会减小不会增大.
		// 当到达z的时候, 必然有z>y, 三元组(x, y, z)被捕捉到.
		for (int i = 0; i < nums.length; ++i) {
			if (nums[i] <= x) x = nums[i];
			else if (nums[i] <= y) y = nums[i];
			else return true;
		}
		return false;
	}

	public static void main(String args[]) {
	}
}

