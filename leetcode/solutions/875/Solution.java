/**
 * 875: Koko eating bananas:w
 * Performance: speed=84%, memory=98%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int minEatingSpeed(int[] piles, int H) {

		// !!!WA!!!: k might be zero
		// case: [312884470] 968709470
		// int i = 0, j = Integer.MAX_VALUE;

		int i = 1, j = Integer.MAX_VALUE;
		int min = Integer.MAX_VALUE;
		while (i < j) {
			int k = i + (j - i) / 2;
			if (eat(piles, k, H)) {
				System.out.printf("fine: k=%d\n", k);
				if (min > k) min = k;
				j = k;
			} else i = k + 1;
		}
		return min;
	}

	private boolean eat(int[] piles, int speed, int maxh) {
		int h = 0;
		for (int i = 0; i < piles.length; ++i) {
			h += piles[i] / speed;
			if (piles[i] % speed != 0) ++h;
			if (h > maxh) return false;
		}
		return true;
	}

	public static void main(String args[]) {
		new Solution().minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6);
		new Solution().minEatingSpeed(new int[]{}, 0);
	}
}

