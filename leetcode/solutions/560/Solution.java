/**
 * 560	Subarray Sum Equals K 42.7% Medium 2508/70
 * Performance: speed=%, memory=%
 */

/*
A better implementation from disscuss:
------
public class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        
        return result;
    }
}
------
*/

import java.util.*;

public class Solution {
	public int subarraySum(int[] a, int k) {
		if (a == null || a.length == 0) return 0;

		int n = 0, sum = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < a.length; ++i) {
			sum += a[i];

			if (sum == k) ++n; // do not map.put(0, 1) because it fails when k = 0
			int delta =sum - k;
			Integer c1 = map.get(delta);
			n += (c1 == null ? 0 : c1);

			Integer c2 = map.get(sum);
                        c2 = (c2 == null ? 1 : c2+1);
                        map.put(sum, c2);
		}
		return n;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.subarraySum(new int[]{}, 100) == 0;
		assert s.subarraySum(new int[]{1}, 1) == 1;
		assert s.subarraySum(new int[]{1, 1, 1}, 1) == 3;
		assert s.subarraySum(new int[]{1, 1, 1}, 2) == 2;
		assert s.subarraySum(new int[]{1, 1, 1}, 3) == 1;
		assert s.subarraySum(new int[]{1, 1, 1}, 4) == 0;
		assert s.subarraySum(new int[]{1, 1, 1, 0, 1, 1}, 2) == 6;
		assert s.subarraySum(new int[]{1}, 0) == 0;

		System.out.println("done");
	}
}

