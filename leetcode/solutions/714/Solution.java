/**
 * 0714: BestTimeToBuyAndSellStockWithTransactionFee
 * Performance: speed=40%, memory=100%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int maxProfit(int[] p, int fee) {
		if (p.length <= 1) return 0;
		int[] buy = new int[p.length], sel = new int[p.length];
		buy[0] = -p[0];
		int i = 0;
		// System.out.printf("i=%d, v=%d, buy=%2d, sel=%2d\n", i, p[i], buy[i], sel[i]);
		for (i = 1; i < p.length; ++i) {
			buy[i] = Math.max(buy[i-1], sel[i-1]-p[i]);
			sel[i] = Math.max(sel[i-1], buy[i-1]+p[i]-fee);
			// System.out.printf("i=%d, v=%d, buy=%2d, sel=%2d\n", i, p[i], buy[i], sel[i]);
		}
		return sel[p.length-1];
	}

	public static void main(String args[]) {
		int r1 = new Solution().maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2);
		System.out.printf("r1=%d\n", r1);
	}
}

