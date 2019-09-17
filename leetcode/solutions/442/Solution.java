/**
 * 442 Find All Duplicates in an Array
 * Performance: speed=%, memory=%
 *
 * disscuss (better than mine): when find a number i, flip the number at position i-1 to negative,
 * if the number at position i-1 is already negative, i is the number that occurs twice.
 */

import java.util.*;

public class Solution {
	public List<Integer> findDuplicates(int[] a) {
		for (int i = 0; i < a.length; ++i) --a[i];
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < a.length; ++i) {
			if (a[i] == -1) continue;
			while (a[i] != i && a[i] != -1) {
				if (a[a[i]] == a[i]) { list.add(a[i]+1); a[i] = a[a[i]] = -1; break; }
				{ int t = a[i]; a[i] = a[t]; a[t] = t; }
			}
		}
		return list;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.printf("%s\n", s.findDuplicates(new int[]{}));
		System.out.printf("%s\n", s.findDuplicates(new int[]{1}));
		System.out.printf("%s\n", s.findDuplicates(new int[]{1, 1}));
		System.out.printf("%s\n", s.findDuplicates(new int[]{1, 2, 3}));
		System.out.printf("%s\n", s.findDuplicates(new int[]{3, 2, 1}));
		System.out.printf("%s\n", s.findDuplicates(new int[]{3, 2, 1, 2}));
		System.out.printf("%s\n", s.findDuplicates(new int[]{1, 1, 2, 2, 3, 3}));
	}
}

