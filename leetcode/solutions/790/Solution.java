/**
 * 790: DominoAndTrominoTilling
 * Performance: speed=%, memory=%
 *
 * https://blog.csdn.net/u010829672/article/details/79810697
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	// private static int M = 1000000007;
	public int numTilings(int n) {
		if (n == 0) return 0;
		else if (n == 1) return 1;
		else if (n == 2) return 2;
		else if (n == 3) return 5;

		int M = 1000000007;
		int p0, p1 = 5, p2 = 2, sum = 1;
		for (int i = 4; i <= n; ++i) {
			p0 = (2 + p1 + p2) % M;
			p0 = (p0 + (2 * sum) % M) % M;
			sum = (sum + p2) % M;
			{ p2 = p1; p1 = p0; }
		}

		return p1;
	}

	public static void main(String args[]) {
		int r1 = new Solution().numTilings(589);
		System.out.printf("r1=%d\n", r1);
	}
}
