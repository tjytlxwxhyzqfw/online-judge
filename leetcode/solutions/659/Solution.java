/**
 * 659 Split Array into Consecutive Subsequences 41.6% Medium 624/249
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public boolean isPossible(int[] nums) {
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
        }   
        else if (freq.getOrDefault(i+1,0) > 0 && freq.getOrDefault(i+2,0) > 0) {
            freq.put(i+1, freq.get(i+1) - 1);
            freq.put(i+2, freq.get(i+2) - 1);
            appendfreq.put(i+3, appendfreq.getOrDefault(i+3,0) + 1);
        }
        else return false;
        freq.put(i, freq.get(i) - 1);
    }
    return true;
}

	public static void main(String args[]) {
		Solution s = new Solution();

		// !!! THIS CASE HACKS MY SOLUTION !!!
		assert s.isPossible(new int[]{1, 2, 3, 4, 5, 5, 6, 7});

		int[] a = new int[17];
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

