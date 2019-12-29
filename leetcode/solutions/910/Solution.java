/**
 * 910 Smallest Range II 25.3% Medium 232/137
 * Performance: speed=%, memory=%
 */

import java.util.*;

// if k > max - min, consider the following situations:
// (1) max+k, min+k or max-k, min-k => the final answer remains max-min
// (2) max+k, min-k => pass
// (3) max-k, min+k => min+k-(max-k) = min-max + 2k > min-max + 2*(max-min) = max-min, pass
// so if k > max -min, just return max-min
//
// so we just consider the situation of k <= max-min
// we assume that if x < (min+max)/2, we must have x+k, else we have x-k
// todo: prove that we can have x-k when x < (min+max)/2


// min+k-max+k = 2k-(max-min)
// 1 2 8 8 k= => 2 7

public class Solution {
	public int smallestRangeII(int[] a, int k) {
		// a.length >= 1
		Arrays.sort(a);
		int delta = 0;
		while (true) {
			if (k > 
		}
	} 

	public static void main(String args[]) {
		Solution s = new Solution();
		
		assert s.smallestRangeII(new int[]{1}, 0) == 0;
		assert s.smallestRangeII(new int[]{1, 2, 8}, 7) == 7;
		assert s.smallestRangeII(new int[]{0, 10}, 2) == 6;
		assert s.smallestRangeII(new int[]{0, 10}, 20) == 10;
		assert s.smallestRangeII(new int[]{1, 3, 6}, 3) == 3;
		assert s.smallestRangeII(new int[]{1, 3, 6}, 5) == 5;

		System.out.println("done");
	}
}

