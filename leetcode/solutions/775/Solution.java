/**
 * 775 Global and Local Inversions 39.9% Medium 235/126
 * Performance: speed=96%, memory=100%
 */

/*
simplify my code: if (abs(A[i] - i) > 1) return false;
*/

import java.util.*;

public class Solution {
	public boolean isIdealPermutation(int[] a) {
		for (int i = a.length-1; i >= 0; --i) {
			if (a[i] < i) {
				if (!(a[i] == i-1 && a[i-1] == i)) return false;
				--i;
			}
		}
		return true;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

