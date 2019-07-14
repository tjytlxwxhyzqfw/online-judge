/**
 * 0413: ArithmeticSlices
 *
 * Performance: speed=100%, memory=100%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int numberOfArithmeticSlices(int[] A) {
		if (A.length < 3) return 0;
		int last = (A[2]-A[1] == A[1]-A[0] ? 1 : 0);
		int max = last;
		for (int i = 3; i < A.length; ++i) max += (last = (A[i]-A[i-1] == A[i-1]-A[i-2]) ? last+1 : 0);
		return max;
	}


	public static void main(String args[]) {
		int max = new Solution().numberOfArithmeticSlices(new int[]{1, 2, 3, 4});
		System.out.printf("max=%d\n", max);
	}
}

