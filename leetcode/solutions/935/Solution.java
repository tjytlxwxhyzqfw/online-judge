/**
 * 935 KnightDialer
 * Performance: speed=44%, memory=18%
 */

/* [ana]
    1=(0,0) 2=(0,1) 3=(0,2)
    4=(1,0) 5=(1,1) 6=(1,2)
    7=(2,0) 8=(2,1) 9=(2,2)
            0=(3,1)
    
    1: 6, 8
    2: 7, 9
    3: 4, 8
    4: 0, 3, 9
    5: ?
    6: 0, 1, 7
    7: 2, 6
    8: 1, 3
    9: 2, 4
    0: 4, 6
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Solution {
	private static HashMap<Integer, Integer[]> From = new HashMap<Integer, Integer[]>() {{
		put(0, new Integer[]{4, 6});
		put(1, new Integer[]{6, 8});
		put(2, new Integer[]{7, 9});
		put(3, new Integer[]{4, 8});
		put(4, new Integer[]{0, 3, 9});
		put(5, new Integer[]{});
		put(6, new Integer[]{0, 1, 7});
		put(7, new Integer[]{2, 6});
		put(8, new Integer[]{1, 3});
		put(9, new Integer[]{2, 4});
	}};
	private static int M = 1000000007;

	public int knightDialer(int n) {
		if (n == 1) return 10;
		int[][] dp = new int[n][10];

		for (int j = 0; j < 10; ++j) dp[0][j] = 1;
		for (int i = 1; i < n; ++i) {
			for (int j = 0; j < 10; ++j) {
				Integer[] from = From.get(j);
				for (int k = 0; k < from.length; ++k) {
					dp[i][j] = (dp[i][j] + dp[i-1][from[k]]) % M;
				}
				// System.out.printf("i=%2d, j=%d, dp=%d\n", i, j, dp[i][j]);
			}
		}

		int sum = 0; for (int j = 0; j < 10; ++j) sum = (sum + dp[n-1][j]) % M;
		return sum;
	}

	public static void main(String args[]) {
		int r1 = new Solution().knightDialer(3);
		System.out.printf("r1=%d\n", r1);
	}
}

