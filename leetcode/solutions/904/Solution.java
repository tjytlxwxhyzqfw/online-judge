/**
 * 904 Fruit Into Baskets 41.8% Medium 591/915
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public int totalFruit(int[] tree) {
		int i = 0, total = 0, ntypes = 0;
		int[] cnt = new int[tree.length];
		for (int j = 0; j < tree.length; ++j) {
			if (cnt[tree[j]] == 0 && ntypes == 2) {
				int oldI = i;
				while (ntypes == 2) {
					if (--cnt[tree[i++]] == 0) --ntypes;
				}
				System.out.printf("j=%2d, i: %2d -> %2d\n", j, oldI, i);
			}

			// now we can safely put tree[j] into one of our baskets
			if (++cnt[tree[j]] == 1) ++ntypes;
			total = Math.max(total, j-i+1);
		}
		return total;
	}

	public static void main(String args[]) {
		Solution s = new Solution();


		assert s.totalFruit(new int[]{}) == 0;
		assert s.totalFruit(new int[]{0}) == 1;
		assert s.totalFruit(new int[]{0, 1}) == 2;
		assert s.totalFruit(new int[]{1, 2, 1}) == 3;
		assert s.totalFruit(new int[]{0, 1, 2, 2}) == 3;
		assert s.totalFruit(new int[]{1, 2, 3, 2, 2}) == 4;
		assert s.totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}) == 5;
		assert s.totalFruit(new int[]{0, 1, 2, 2, 3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}) == 5;

		System.out.println("done");
	}
}

