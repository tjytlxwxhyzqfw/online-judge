/**
 * 593	Valid Square 40.9% Medium 151/304
 * Performance: speed=81%, memory=75%
 */

import java.util.*;

public class Solution {
	public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
		int d12 = dst(p1, p2), d13 = dst(p1, p3), d14 = dst(p1, p4);
		if (d12 == 0 || d13 == 0 || d14 == 0) return false;
		if (d12 == d13) { return d14 == 2 * d12 && dst(p2, p4) == d12 && dst(p3, p4) == d12 && dst(p2, p3) == d14; }
		else if (d12 == d14) { return d13 == 2 * d12 && dst(p2, p3) == d12 && dst(p4, p3) == d12 && dst(p2, p4) == d13; }
		else { return d13  == d14 && d13 == dst(p2, p4) && d13 == dst(p2, p3) && 2 * d13 == d12 && dst(p3, p4) == d12; }
	}

	int dst(int[] p, int[] q) {
		return (p[0]-q[0]) * (p[0]-q[0]) + (p[1]-q[1]) * (p[1]-q[1]);
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.validSquare(new int[]{0, 0}, new int[]{1, 1}, new int[]{1, 0}, new int[]{0, 1}) == true;
		assert s.validSquare(new int[]{0, 0}, new int[]{1, 1}, new int[]{1, 0}, new int[]{0, 2}) == false;
		assert s.validSquare(new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0}) == false;
		assert s.validSquare(new int[]{0, 0}, new int[]{1, 0}, new int[]{1, 0}, new int[]{1, 1}) == false;
                System.out.println("done");
	}
}

