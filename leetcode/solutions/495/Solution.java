/**
 * 495 Teemo Attacking 52.6% Medium 264/563
 * Performance: speed=%, memory=%
 */

/*
    // a better solution found in disscuss

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries.length == 0 || duration == 0) return 0; 
        int cnt = duration;
        for (int i = 1; i < timeSeries.length; i++)
                cnt += Math.min(timeSeries[i] - timeSeries[i - 1], duration);
        return cnt; 
    }

 */

import java.util.*;

public class Solution {
	public int findPoisonedDuration(int[] ts, int duration) {
		int end = -1, sum = 0;
		for (int i = 0; i < ts.length; ++i) {
			int end1 = ts[i] + duration - 1;
			if (ts[i] > end) sum += duration;
			else sum += end1 - end;
			end = end1;
		}
		return sum;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.findPoisonedDuration(new int[]{}, 0) == 0;
		assert s.findPoisonedDuration(new int[]{1}, 2) == 2;
		assert s.findPoisonedDuration(new int[]{1, 2}, 2) == 3;
		assert s.findPoisonedDuration(new int[]{1, 4}, 2) == 4;
		assert s.findPoisonedDuration(new int[]{1, 2, 3}, 100) == 102;
		assert s.findPoisonedDuration(new int[]{1, 2, 3}, 0) == 0;

		System.out.println("all test passed");
	}
}
