/**
 * 945 Minimum Increment to Make Array Unique 43.8% Medium 259/12
 * Performance: speed=%, memory=%
 */

import java.util.*;

// my idea is a little different from the one in disscuss
// my solution is Max(K, N)logN, but solution is NlogN, 
// anyway, mine is worse

public class Solution {
	public int minIncrementForUnique(int[] a) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < a.length; ++i) {
			int cnt = map.getOrDefault(a[i], 0);
			map.put(a[i], ++cnt);
		}

		int result = 0, n = 0;
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; ; ++i) {
			int cnt = map.getOrDefault(i, 0);
			n += cnt;
			while (cnt > 1) { q.offer(i); --cnt; }
			if (cnt == 0 && !q.isEmpty() && i > q.peek()) {
				// System.out.printf("%d -> %d\n", q.peek(), i);
				result += i - q.poll();
			}

			// System.out.printf("n=%d, q.size()=%d\n", n, q.size());
			if (n >= a.length && q.isEmpty()) break;
		}

		return result;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.minIncrementForUnique(new int[]{}) == 0;
		assert s.minIncrementForUnique(new int[]{0}) == 0;
		assert s.minIncrementForUnique(new int[]{1}) == 0;
		assert s.minIncrementForUnique(new int[]{1, 2, 3}) == 0;
		assert s.minIncrementForUnique(new int[]{1, 1, 1}) == 3;
		assert s.minIncrementForUnique(new int[]{40000, 40000, 40000}) == 3;
		assert s.minIncrementForUnique(new int[]{4, 5, 8, 5, 4, 9, 0, 3, 0}) == 5;
		
		System.out.println("done");
	}
}

