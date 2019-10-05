/**
 * 781 Rabbits in Forest 52.5% Medium 183/234
 * Performance: speed=68%, memory=100%
 */

import java.util.*;

public class Solution {
	public int numRabbits(int[] a) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < a.length; ++i) {
			Integer c = map.get(a[i]+1);
			map.put(a[i]+1, 1 + (c == null ? 0 : c));
		}
		int n = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int k = entry.getKey(), v = entry.getValue();
			n += k * (v / k) + (v % k == 0 ? 0 : k);
		}
		return n;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.numRabbits(new int[]{}) == 0;
		assert s.numRabbits(new int[]{1}) == 2;
		assert s.numRabbits(new int[]{11}) == 12;
		assert s.numRabbits(new int[]{2}) == 3;
		assert s.numRabbits(new int[]{2, 2}) == 3;
		assert s.numRabbits(new int[]{2, 2, 2}) == 3;
		assert s.numRabbits(new int[]{2, 2, 2, 2}) == 6;
		assert s.numRabbits(new int[]{2, 2, 2, 2, 2}) == 6;
		assert s.numRabbits(new int[]{2, 2, 2, 2, 2, 2}) == 6;
		assert s.numRabbits(new int[]{2, 2, 2, 2, 2, 2, 2}) == 9;
		assert s.numRabbits(new int[]{2, 2, 2, 2, 2, 2, 2, 3}) == 13;
		assert s.numRabbits(new int[]{10, 10, 10}) == 11;
		assert s.numRabbits(new int[]{1, 1, 2}) == 5;
		System.out.println("done");
	}
}

