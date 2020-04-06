/**
 * 128 Longest Consecutive Sequence
 * pass=44 ud=2767/161 s=91 m=20
 *
 * (diss) time complexity of searching in hash set is considered as a O(1) operation, fuck!
 */

import java.util.*;

public class Solution {
	public int longestConsecutive(int[] nums) {
		Set<Integer> set = new HashSet<>();
		int n = 0;
		for (int i = 0; i < nums.length; ++i) set.add(nums[i]);
		for (int x : set) {
			if (set.contains(x-1)) continue;
			int y = x+1;
			while (set.contains(y)) ++y;
			n = Math.max(n, y-x);
		}
		return n;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.longestConsecutive(new int[]{}) == 0;
		assert s.longestConsecutive(new int[]{1}) == 1;
		assert s.longestConsecutive(new int[]{1, 3, 5}) == 1;
		assert s.longestConsecutive(new int[]{302, 303, 301}) == 3;
		assert s.longestConsecutive(new int[]{200, 4, 100, 2, 3, 1}) == 4;
		assert s.longestConsecutive(new int[]{200, 4, 100, 2, 3, 1, 205, 203, 202, 204, 201}) == 6;
		System.out.println("done");
	}
}

