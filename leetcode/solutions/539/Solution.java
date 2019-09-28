/**
 * 539	Minimum Time Difference 48.7% Medium
 * Performance: speed=49%, memory=60%
 */

/*
discuss: using sort is not a good idea, it will push the complexity to O(nlogn). this can be accomplished using O(n)
discuss: There is only 24 * 60 = 1440 possible time points. Just create a boolean array, each element stands for if we see that time point or not. Then things become simple...
*/

import java.util.*;

public class Solution {
	public int findMinDifference(List<String> times) {
		int[] ts = new int[times.size()];
		int p = 0;
		for (String t : times) {
			String[] hm = t.split(":");
			ts[p++] = Integer.parseInt(hm[0]) * 60 + Integer.parseInt(hm[1]);
		}
		Arrays.sort(ts);
		int min = ts[0] + 1440 - ts[ts.length-1];
		for (int i = 1; i < ts.length; ++i) min = Math.min(min, ts[i] - ts[i-1]);
		return min;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.findMinDifference(Arrays.asList("00:00", "00:00")) == 0;
		assert s.findMinDifference(Arrays.asList("00:00", "23:59")) == 1;
		assert s.findMinDifference(Arrays.asList("00:00", "12:00")) == 720;
		assert s.findMinDifference(Arrays.asList("00:00", "13:00")) == 660;
	}
}

