/**
 * 825 Friends Of Appropriate Ages 38.7% Medium 216/458
 * Performance: speed=29%, memory=100%
 */

/*
their solutions: use prefix sum. sum[y] - sum[x]
my solutions: IMOS(see 731)
*/

import java.util.*;

public class Solution {
	public int numFriendRequests(int[] a) {
		int[] begin = new int[122], end = new int[122], b = new int[121];
		int active = 0;
		for (int i = 0; i < a.length; ++i) {
			++b[a[i]];
			int x = a[i] / 2 + 8, y = a[i] + 1;
			System.out.printf("%2d -> [%2d, %2d)\n", a[i], x, y);
			if (x < y) {
				// --optimistic: begin[] and end[] can be merged to one array
				++begin[x];
				++end[y];
				++active;
			}
		}

		int total = 0, nreqs = 0;
		for (int i = 1; i < b.length; ++i) {
			nreqs = nreqs + begin[i] - end[i];
			total += b[i] * nreqs;
		}
		total = total - active;

		return total;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.numFriendRequests(new int[]{}) == 0;
		assert s.numFriendRequests(new int[]{1}) == 0;
		assert s.numFriendRequests(new int[]{1, 2, 3, 4, 5, 6, 7, 8}) == 0;

		assert s.numFriendRequests(new int[]{16, 16}) == 2;
		assert s.numFriendRequests(new int[]{16, 16, 16, 16, 16}) == 20;

		assert s.numFriendRequests(new int[]{16, 17, 18}) == 2;
		assert s.numFriendRequests(new int[]{20, 30, 100, 110, 120}) == 3;

		System.out.println("done");
	}
}

