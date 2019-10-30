/**
 * 822 Card Flipping Game 41.2% Medium 58/378
 * Performance: speed=100%, memory=33%
 */

import java.util.*;

public class Solution {
	public int flipgame(int[] fronts, int[] backs) {
		boolean[] bad = new boolean[2001];
		for (int i = 0; i < fronts.length; ++i) if (fronts[i] == backs[i]) bad[backs[i]] = true;

		int good = Integer.MAX_VALUE;
		for (int i = 0; i < fronts.length; ++i) {
			if (!bad[fronts[i]] && fronts[i] < good) good = fronts[i];
			if (!bad[backs[i]] && backs[i] < good) good = backs[i];
		}
		if (good == Integer.MAX_VALUE) good = 0;

		return good;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.flipgame(new int[]{}, new int[]{}) == 0;
		assert s.flipgame(new int[]{1}, new int[]{1}) == 0;
		assert s.flipgame(new int[]{1}, new int[]{2}) == 1;
		assert s.flipgame(new int[]{1, 2}, new int[]{2, 1}) == 1;
		assert s.flipgame(new int[]{1, 2}, new int[]{1, 2}) == 0;
		assert s.flipgame(new int[]{1, 1}, new int[]{1, 2}) == 2;

		assert s.flipgame(new int[]{1, 2, 4, 4, 7}, new int[]{1, 3, 4, 1, 3}) == 2;

		System.out.println("done");
	}
}

