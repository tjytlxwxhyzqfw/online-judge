/**
 * 869	Reordered Power of 2 51.3% Medium 157/74
 * Performance: speed=97%, memory=25%
 */

// think about the following counter:
//    public long counter(int N) {
//        long res = 0;
//        for (; N > 0; N /= 10) res += (int)Math.pow(10, N % 10);
//        return res;
//    }

import java.util.*;

public class Solution {
	public boolean reorderedPowerOf2(int n) {
		int[] a = new int[10], b = new int[10];
		count(n, a);
		for (int i = 1; i < 1e9; i <<= 1) {
			count(i, b);
			boolean eq = true;
			for (int j = 0; j < a.length; ++j) {
				if (a[j] != b[j]) {
					eq = false;
					break;
				}
			}
			if (eq) return true;
		}
		return false;
	}

	void count(int n, int[] c) {
		for (int i = 0; i < c.length; ++i) c[i] = 0;
		while (n != 0) {
			++c[n%10];
			n /= 10;
		}
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.reorderedPowerOf2(1);
		assert s.reorderedPowerOf2(10) == false;
		assert s.reorderedPowerOf2(16);
		assert s.reorderedPowerOf2(61);
		assert s.reorderedPowerOf2(31) == false;
		assert s.reorderedPowerOf2(6094);
		assert s.reorderedPowerOf2(4690);
		
		System.out.println("done");
	}
}

