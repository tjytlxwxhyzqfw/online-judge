/**
 * 396 Rotate Function
 * Performance: speed=100%, memory=100%
 */

import java.util.*;

public class Solution {
	public int maxRotateFunction(int[] a) {
		int sum = 0, base = 0;
		for (int i = 0; i < a.length; ++i) { sum += i * a[i]; base += a[i]; }
		int max = sum;
		for (int i = a.length-1; i > 0; --i) { sum = sum + base - a.length * a[i]; max = Math.max(max, sum); }
		return max;
	}

	public static void main(String args[]) {
	}
}

