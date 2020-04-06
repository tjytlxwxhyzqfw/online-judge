/**
 * 15 3Sum 5553/672
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);

		for (int i = 0; i < nums.length-2; ++i) {
			if (i >= 1 && nums[i] == nums[i-1]) continue;
			int x = -nums[i];
			int j = i+1, k = nums.length-1;
			while (j < k) {
				int y = nums[j] + nums[k];
				if (y == x) {
					list.add(Arrays.asList(nums[i], nums[j], nums[k]));
					while (k > j && nums[k] == nums[k-1]) --k;
					while (j < k && nums[j] == nums[j+1]) ++j;
					++j;
					--k;
				} else if (y > x) --k;
				else ++j;
			}
		}

		return list;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println(s.threeSum(new int[]{1, 1, -2}));
		System.out.println(s.threeSum(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}));
		System.out.println(s.threeSum(new int[]{
			-1, -1, -1, -2, -2, -2, -3, -3, -3, 2, 2, 2, 4, 4, 4, 6, 6, 6, 3, 3, 3, 5, 5, 5, 4, 4, 4}));
		System.out.println(s.threeSum(new int[]{-4, -4, -2, -2, -2, 0, 0, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6}));
		System.out.println("done");
	}
}

