/**
 * 220 Contains Duplicate III
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (nums.length < 2) return false;
		for (int i = 0; i < nums.length; ++i) {
			for (int j = i+1; j < nums.length && j <= i+k; ++j) {
				if (Math.abs((long)nums[j] - (long)nums[i]) <= t) return true;
			}
		}
		return false;
	}

	public static void main(String args[]) {
	}
}
