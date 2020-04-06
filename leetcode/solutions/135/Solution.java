/**
 * 135 Candy
 * pass=30 ud=762/150 s=31 m=100
 */

import java.util.*;

public class Solution {
	public int candy(int[] r) {
		if (r.length == 0) return 0;
		int sum = 1, d = 1;
		int[] c = new int[r.length];
		c[0] = 1;
		for (int i = 1; i < r.length; ++i) {
			if (r[i] < r[i-1]) {
				++d;
				c[i] = 1;
				sum += d-1;
				int start = i-d+1;
				if (d-1 == c[start]) {
					++c[start];
					++sum;
				}
			} else if (r[i] > r[i-1]) {
				d = 1;
				sum += (c[i] = c[i-1] + 1);
			} else {
				d = 1;
				sum += (c[i] = 1);
			}
		}
		return sum;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		
		assert s.candy(new int[]{}) == 0;
		assert s.candy(new int[]{10}) == 1;
		assert s.candy(new int[]{1, 1, 1, 1}) == 4;
		assert s.candy(new int[]{1, 2, 3}) == 6;
		assert s.candy(new int[]{1, 2, 3, 3, 3}) == 8;
		assert s.candy(new int[]{1, 2, 9, 8, 7, 6, 5}) == 18;
		assert s.candy(new int[]{1, 0, 2}) == 5;
		assert s.candy(new int[]{1, 2, 2}) == 4;
		assert s.candy(new int[]{1, 2, 9, 9, 8, 7, 6, 5, 5, 4, 3, 4, 5, 6, 7, 8, 9}) == 54;

		System.out.println("done");
	}
}

