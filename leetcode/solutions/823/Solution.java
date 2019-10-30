/**
 * 823 Binary Trees With Factors 33.5% Medium 223/29
 * Performance: speed=55%, memory=100%
 */

import java.util.*;

public class Solution {
	static int M = 1000000007;
	public int numFactoredBinaryTrees(int[] a) {
		Arrays.sort(a);
		Map<Integer, Integer> cnt = new HashMap<>();
		int total = 0;
		for (int i = 0; i < a.length; ++i) {
			int c = 1;
			for (int j = 0; j < i; ++j) {
				if (a[i] % a[j] != 0) continue;
				int x = a[j], y = a[i] / a[j];
				// attention: x must be the smaller one !!!
				if (x > y) break;
				if (cnt.get(y) == null) continue;
				// x and y can be the two children of a[i];
				// attention: be careful that #x * #y can be overflow
				int addent = (int)((1L * cnt.get(x) * cnt.get(y)) % M);
				if (x != y) addent = (addent << 1) % M;
				c = (c + addent) % M;
			}
			cnt.put(a[i], c);
			// System.out.printf("root=%3d, #=%d\n", a[i], c);
			total = (total + c) % M;
		}
		return total;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.numFactoredBinaryTrees(new int[]{}) == 0;
		assert s.numFactoredBinaryTrees(new int[]{2}) == 1;
		assert s.numFactoredBinaryTrees(new int[]{4, 2}) == 3;
		assert s.numFactoredBinaryTrees(new int[]{10, 5, 4, 2}) == 7;

		System.out.println("done");
	}
}

