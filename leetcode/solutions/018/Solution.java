/**
 * 18
 *
 * 四个数字之和为target
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Solution {
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> answer = new ArrayList<List<Integer>>();
		Set<List<Integer>> result = new HashSet<List<Integer>>();

		List<Integer> quadruplet;
		int i, j, k, l;
		int base, expect;

		if (nums.length < 4) return answer;

		Arrays.sort(nums);
		for (i = 0; i < nums.length; ++i) {
			for (j = i+1; j < nums.length; ++j) {
				for (k = j+1; k < nums.length; ++k) {
					base = nums[i] + nums[j] + nums[k];
					expect = target - base;
					l = Arrays.binarySearch(nums, k+1, nums.length, expect);
					if (l < 0) continue;
					quadruplet = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
					result.add(quadruplet);
				}
			}
		}

		for (List<Integer> quad : result) answer.add(quad);
		return answer;
	}

	private static void printlist(List<Integer> list, String tag) {
		System.out.printf("%s: %s\n", tag, list.toString());
		//System.out.printf("%2d %2d %2d %2d\n", list.get(0), list.get(1), list.get(2), list.get(3));
	}

	public static void main(String args[]) {
		int nums[] = {1, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1, -1, -1};
		int target = 0;
		Solution solution = new Solution();
		List<List<Integer>> result;

		result = solution.fourSum(nums, target);
		for (List<Integer> quad : result) printlist(quad, "result");
	}
}
