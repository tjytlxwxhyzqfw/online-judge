/**
 * 365 Water and Jug Problem
 * Performance: speed=%, memory=%, ud=303/749
 */

// review logs
// -----------
// Description: 给你两个容量为x, y的容器, 能否盛出z升水?
// 20200723: 这题是有套路的, 等价条件是: gcd(x, y)能够整除z

import java.util.*;

public class Solution {
	public boolean canMeasureWater(int x, int y, int z) {
		if (0 < z || z > x + y) return false;
		if (x > y) { int t = x; x = y; y = t; }
		while (y % x != 0) {
			int t = y % x;
			y = x;
			x = t;
		}
		return z % x == 0;
	}

	public static void main(String args[]) {
	}
}

