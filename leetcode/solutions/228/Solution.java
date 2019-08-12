/**
 * 228 Summary Ranges
 * Performance: speed=100%, memory=100%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public List<String> summaryRanges(int[] nums) {
		List<String> result = new ArrayList<>();
		if (nums == null || nums.length == 0) return result;

		int start = nums[0];
		for (int i = 1; i < nums.length; ++i) {
			if (nums[i] - nums[i-1] != 1) {
				if (start == nums[i-1]) result.add(""+start);
				else result.add(start+"->"+nums[i-1]);
				start = nums[i];
			}
		}
		if (start != nums[nums.length-1]) result.add(start+"->"+nums[nums.length-1]);
		else result.add(""+start);
		return result;
	}

	public static void main(String args[]) {
		List<String> result = new Solution().summaryRanges(new int[]{1, 2, 3, 5, 6, 8, 10, 12, 14, 15});
		for (String s : result) System.out.printf("%s ", s);
		System.out.println();
	}
}

