/**
 * 659 Split Array into Consecutive Subsequences 41.6% Medium 624/249
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	// see: https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/106495/Java-O(n)-time-and-O(1)-space-solution-greedily-extending-shorter-subsequence
	public boolean isPossible(int[] a) {
		int pre = 0, cur, c, p1 = 0, p2 = 0, p3 = 0, c1 = 0, c2 = 0, c3 = 0;
		int i = 0;
		while (true) {
			{ c = 1; cur = a[i]; }
			while (i+1 < a.length && a[i] == a[i+1]) { ++i; ++c; }
			// System.out.printf("i=%2d, a[i]=%2d, c=%2d, p1=%d, p2=%d, p3=%d\n", i, a[i], c, p1, p2, p3);
			if (cur != a[0]) {
				if (pre+1 != cur) {
					if ((p1 != 0) || (p2 != 0)) return false;
					c1 = c;
					c2 = c3 = 0;
				} else {
					if (c < p1 + p2) return false;
					c2 = p1;
					c3 = p2 + Math.min(p3, c-p1-p2);
					c1 = Math.max(c-p1-p2-p3, 0);
				}
			} else {
				c1 = c;
				c2 = c3 = 0;
			}

			{ p1 = c1; p2 = c2; p3 = c3; pre = cur; }
			if (++i == a.length) break;
		}
		return p1 == 0 && p2 == 0;
	}

	public boolean isPossible_raw_WA(int[] nums) {
		if (nums.length < 3) return false;

		Map<Integer, Integer> cnt = new HashMap<>();
		for (int i = 0; i < nums.length; ++i) cnt.put(nums[i], 1+cnt.getOrDefault(nums[i], 0));

		Map<Integer, Integer> tails = new HashMap<>();
		for (int i = 0; i < nums.length; ++i) {
			int c0 = cnt.get(nums[i]);
			if (c0 == 0) continue;
			System.out.printf("i=%2d, val=%d\n", i, nums[i]);
			cnt.put(nums[i], --c0);
			int c1 = cnt.getOrDefault(nums[i]+1, 0), c2 = cnt.getOrDefault(nums[i]+2, 0);
			if (c1 == 0 || c2 == 0) {
				int ct = tails.getOrDefault(nums[i]-1, 0);
				System.out.printf("\ttry to append to tail: %2d, cnt=%d\n", nums[i]-1, ct);
				if (ct == 0) return false;
				tails.put(nums[i]-1, --ct);
				tails.put(nums[i], 1 + tails.getOrDefault(nums[i], 0));
			} else {
				System.out.printf("\tmake consecutive seq: c1=%2d, c2=%2d\n", c1, c2);
				tails.put(nums[i]+2, 1 + tails.getOrDefault(nums[i]+2, 0));
				cnt.put(nums[i]+2, --c2);
				cnt.put(nums[i]+1, --c1);
			}
		}
		return true;
	}

	public boolean isPossible2(int[] nums) {
		Map<Integer, Integer> freq = new HashMap<>(), appendfreq = new HashMap<>();
		for (int i : nums) freq.put(i, freq.getOrDefault(i,0) + 1);
		for (int i : nums) {
			if (freq.get(i) == 0) continue;
			else if (appendfreq.getOrDefault(i,0) > 0) {
				appendfreq.put(i, appendfreq.get(i) - 1);
				appendfreq.put(i+1, appendfreq.getOrDefault(i+1,0) + 1);
        		} else if (freq.getOrDefault(i+1,0) > 0 && freq.getOrDefault(i+2,0) > 0) {
				freq.put(i+1, freq.get(i+1) - 1);
				freq.put(i+2, freq.get(i+2) - 1);
				appendfreq.put(i+3, appendfreq.getOrDefault(i+3,0) + 1);
			} else return false;
        		freq.put(i, freq.get(i) - 1);
    		}
		return true;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.isPossible(new int[]{1, 2, 3, 5, 5}) == false;

		assert s.isPossible(new int[]{1, 2, 3});

		// !!! THIS CASE HACKS MY SOLUTION !!!
		assert s.isPossible(new int[]{1, 2, 3, 4, 5, 5, 6, 7});
		assert s.isPossible(new int[]{1, 2, 2, 3, 3, 4});
		assert s.isPossible(new int[]{1, 2, 2, 3, 3, 4, 5});
		assert s.isPossible(new int[]{1, 2, 2, 3, 3, 4, 5, 6});

		int[] a = new int[27];
		int n = 0;
		while (true) {
			s.randArr(a);
			if (s.isPossible2(a) != s.isPossible(a)) break;
			++n;
			if (n % 10000 == 0) System.out.printf("%d\r", n);
		}
		for (int i = 0; i < a.length; ++i) System.out.printf("%d, ", a[i]);
                System.out.println("done");
	}

	void randArr(int[] a) {
		Random r = new Random();
		for (int i = 0; i < a.length; ++i) a[i] = 1+Math.abs(r.nextInt()) % a.length;
		Arrays.sort(a);
	}
}

