/**
 * 365 Water and Jug Problem
 * Performance: speed=%, memory=%
 */

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

