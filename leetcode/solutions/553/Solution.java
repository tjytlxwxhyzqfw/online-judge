/**
 * Optimal Division 55.7% Medium 132/996
 * Performance: speed=6%, memory=25%
 * disscuss: there is an o(n) solution to this problem, ans = a1/(a2/a3/a4/a5/a6)
 */

import java.util.*;

public class Solution {
	public String optimalDivision(int[] nums) {
		StringBuilder min = new StringBuilder(), max = new StringBuilder();
		search(nums, 0, nums.length, min, max);
		return max.toString();
	}

	double[] search(int[] a, int i, int j, StringBuilder min, StringBuilder max) {
		if (j - i == 1) { min.append(a[i]); max.append(a[i]); return new double[]{a[i], a[i]}; }

		StringBuilder minL = new StringBuilder();
		StringBuilder minR = new StringBuilder();
		StringBuilder maxL = new StringBuilder();
		StringBuilder maxR = new StringBuilder();
		double minV = Double.MAX_VALUE, maxV = Double.MIN_VALUE;
		for (int k = i+1; k < j; ++k) {
			StringBuilder x = new StringBuilder();
			StringBuilder y = new StringBuilder();
			StringBuilder z = new StringBuilder();
			StringBuilder w = new StringBuilder();
			double[] left = search(a, i, k, x, y);
			double[] rght = search(a, k, j, z, w);
			if (left[0] / rght[1] < minV) { minV = left[0] / rght[1]; minL = x; minR = w; }
			if (left[1] / rght[0] > maxV) { maxV = left[1] / rght[0]; maxL = y; maxR = z; }
		}

		min.append(minL.toString()).append("/");
		if (minR.indexOf("/") != -1) { min.append("("); min.append(minR.toString()); min.append(")"); }
		else min.append(minR.toString());

		max.append(maxL.toString()).append("/");
		if (maxR.indexOf("/") != -1) { max.append("("); max.append(maxR.toString()); max.append(")"); }
		else max.append(maxR.toString());

		// System.out.printf("i=%2d, j=%2d, min=%g, max=%g\n", i, j, minV, maxV);
		return new double[]{minV, maxV};
	}
 
	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.optimalDivision(new int[]{7}).equals("7");
		assert s.optimalDivision(new int[]{10, 2}).equals("10/2");
		assert s.optimalDivision(new int[]{1000, 100, 10, 2}).equals("1000/(100/10/2)");
		assert s.optimalDivision(new int[]{1, 2, 2}).equals("1/(2/2)");
	}
}

