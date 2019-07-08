/**
 * 0523: ContinuousSubarraySum
 * Performance: speed=%, memory=%
 *
 * Prefix Sum !!!
 *
 * Discuss: We iterate through the input array exactly once,
 * keeping track of the running sum mod k of the elements in
 * the process, If we find that a running sum value at index
 * j has been previously seen before in some earlier index i
 * in the array, then we know that the sub-array (i,j]
 * contains a desired sum.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
	public boolean checkSubarraySum(int[] nums, int k) {
		if (nums.length <= 1) return false; 
		if (k == 0) {
			for (int i = 1; i < nums.length; ++i) if (nums[i]==0 && nums[i-1]==0) return true;
			return false;
		}
		k = Math.abs(k);

		Set<Integer> set = new HashSet<>();
		set.add(nums[0]%k);
		int sum = nums[0];
		for (int i = 1; i < nums.length; ++i) {
			if (nums[i] % k == 0) {
				if (nums[i-1] % k == 0) return true;
				else continue;
			}
			sum += nums[i];
			if (sum%k==0 || set.contains(sum%k)) return true;
			set.add(sum%k);
			System.out.printf("add: %d\n", sum%k);
		}
		return false;
	}

	public static void main(String args[]) {
		boolean ans = new Solution().checkSubarraySum(new int[]{1, 2, 12}, 6);
		System.out.printf("ans=%s\n", ans);
	}
}

