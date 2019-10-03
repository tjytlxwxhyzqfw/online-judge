/**
 * 670 Maximum Swap 40.8% Medium 645/49
 * Performance: speed=%, memory=%
 */

/*
disscuss:

Very similar to LeetCode 31. Next Permutation

Search from the left end and find the position where the reversed list was not satisfied. (example. in number 54367, we will find the position of 6.) Then we cut the original digits array to two parts, the first part was reversely sorted which means no swap needed within it and the second part.

Find and record the max value and max value position in the second part. ( example, digit 7 in 54367)

Swap the max value in the second part to the first number in first part that smaller than that max value. (Example, swap 7 with 5 in 54367)
Time Complexity is O(n)
*/

import java.util.*;

public class Solution {
	public int maximumSwap(int num) {
		if (num == 0) return 0;
		byte[] a = new byte[10], b = new byte[10];
		byte p = 0, i = -1;
		while (num != 0) {
			a[p] = (byte)(num % 10);
			b[p] = (byte)Math.max(a[p], p == 0 ? (byte)-1 : b[p-1]);
			if (a[p] != b[p]) i = p;
			++p;
			num /= 10;
		}
		if (i != -1) {
			System.out.printf("i=%d, a[i]=%d, b[i]=%d\n", i, a[i], b[i]);
			for (int k = 0; k < i; ++k) {
				if (a[k] == b[i]) {
					System.out.printf("swap: %d <->%d\n", i, k);
					byte t = a[k];
					a[k] = a[i];
					a[i] = t;
					break;
				}
			}
		}
		for (int k = p-1; k >= 0; --k) {
			num = num * 10 + a[k];
			System.out.printf("k=%d, a[k]=%d, num=%d\n", k, a[k], num);
		}
		return num;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.maximumSwap(0) == 0;
		assert s.maximumSwap(1) == 1;
		assert s.maximumSwap(110) == 110;
		assert s.maximumSwap(12345) == 52341;
		assert s.maximumSwap(321) == 321;
		assert s.maximumSwap(62255432) == 65252432;
                System.out.println("done");
	}
}

