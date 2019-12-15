/**
 * 881 Boats to Save People 44.7% Medium 349/25
 * Performance: speed=81%, memory=100%
 */

import java.util.*;

public class Solution {
	public int numRescueBoats(int[] p, int limit) {
		Arrays.sort(p);
		int i = 0, j = p.length-1;
		int n = 0;
		while (i < j) {
			++n;
			if (p[i] + p[j] <= limit) {++i; --j;}
			else --j;
		}
		if (i == j) ++n;
		return n;
	}

	public int numRescueBoats_S41M20(int[] people, int limit) {
		int half = (people.length + 1) / 2;
		int[] r = new int[half];
		Arrays.sort(people);
		for (int i = 0; i < half; ++i) r[i] = limit - people[i];
		int n = half, next = 0;
		for (int i = people.length-1; i >= half; --i) {
			int curr = people[i];
			if (curr <= r[next]) ++next;
			else ++n;
		}
		for (int i = half-1; i >= 0 && next < i; --i) {
			int curr = people[i];
			if (curr <= r[next]) {
				--n;
				++next;
			}
		}

		return n;
	} 

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.numRescueBoats(new int[]{1}, 1) == 1;
		assert s.numRescueBoats(new int[]{1, 2}, 3) == 1;
		assert s.numRescueBoats(new int[]{2, 3, 2, 3}, 5) == 2;
		assert s.numRescueBoats(new int[]{3, 2, 2, 1}, 3) == 3;
		assert s.numRescueBoats(new int[]{3, 5, 3, 4}, 5) == 4;
		assert s.numRescueBoats(new int[]{1, 3, 7}, 7) == 2;

		System.out.println("done");
	}
}

