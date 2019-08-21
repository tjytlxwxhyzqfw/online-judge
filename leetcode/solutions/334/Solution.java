/**
 * 334 Increasing Triplet Subsequence
 * Performance: speed=100%, memory=95%
 */

import java.util.*;

public class Solution {
	public boolean increasingTriplet(int[] nums) {
		int x = Integer.MAX_VALUE, y = Integer.MAX_VALUE;
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

