/**
 * 398 Random Pick Index
 * Performance: speed=60%, memory=94%
 *
 * i read disscussions before i solve this problem
 * there is tow ways that i can come up with:
 * (1) use map to map value to index array
 * (2) traverse on nums in pick
 * (3) (diss) (2) + reservoir sampling
 */

import java.util.*;

public class Solution {
	int[] nums;
	Random r;

	public Solution(int[] nums) {
		this.nums = nums;
		r = new Random(47);
	}
    
	public int pick(int target) {
		int n = 0, idx = -1;
		for (int i = 0; i < nums.length; ++i) {
			if (nums[i] != target) continue;
			idx = r.nextInt(++n) == 0 ? i : idx;
		}
		return idx;
	}

	public static void main(String args[]) {
	}
}
