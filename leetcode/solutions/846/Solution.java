/**
 * 846 Hand of Straights 50.2% Medium 350 52
 * Performance: speed=80%, memory=100%
 */

import java.util.*;

public class Solution {
	public boolean isNStraightHand(int[] hand, int w) {
		if (hand.length % w != 0) return false;
		if (w == 1) return true;

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < hand.length; ++i) {
			int cnt = map.getOrDefault(hand[i], 0);
			map.put(hand[i], 1 + cnt);
		}

		Arrays.sort(hand);

		for (int i = 0; i < hand.length; ++i) {
			int cnt = map.get(hand[i]);
			if (cnt == 0) continue;
			map.put(hand[i], 0);
			for (int j = 1; j < w; ++j) {
				int cnt1 = map.getOrDefault(hand[i]+j, 0);
				if (cnt1 < cnt) return false;
				map.put(hand[i]+j, cnt1-cnt);
			}
		}

		return true;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.isNStraightHand(new int[]{5, 1}, 2) == false;

		assert s.isNStraightHand(new int[]{1, 2, 3}, 3);
		assert s.isNStraightHand(new int[]{1, 2, 3}, 1);
		assert s.isNStraightHand(new int[]{1}, 1);
		assert s.isNStraightHand(new int[]{1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4}, 3);
		assert s.isNStraightHand(new int[]{1, 2, 3, 4, 5}, 5);
		assert s.isNStraightHand(new int[]{1, 2, 3, 4, 5}, 4) == false;

		System.out.println("done");
	}
}
