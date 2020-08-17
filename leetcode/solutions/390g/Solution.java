/**
 * 390 Elimination Game
 * Performance: speed=21%, memory=33% 272/241
 *
 * discuss: similiar idea with most-votes solution, but mine is a little bit worse in impl
 */

// review logs
// -----------
// Description: 1 2 3 4 5 6 -> 从左到右隔一个删一个 -> 2 4 6 -> 从右向左, 隔一个删一个 -> 4
//   问, 最后剩下的数字是多少?
//
// 20200725: 今天没有想法. 答案的思路是基于这样一个观察结论: 每删除一轮(不管是从左向右还是从右向左),
//   剩下的数之间, 相邻两个数的间隔增大一倍. 因此我们总是能够知道: 当前轮数, 剩下的数字的数目, 数字间间隔.
//   再进一步, 我们还总是能够计算出第一个数字是多少: 如果第一个数字被删了, 那么新的第一个数字=原第一个数字+当前间隔.
//   这样问题就变得非常简单, 当仅剩一个数字时, 第一个数字就是答案.
//
//   至于我的解法, 我自己也忘了当初是怎么想出来的, 估计是苦想了很久, 真的有点难为我自己了.

import java.util.*;

public class Solution {
	public int lastRemaining(int n) {
		int k = 0, first = 1, last = n;
		while (first != last) {
			// System.out.printf("k=%d, first=%d, last=%d\n", k, first, last);
			// new Scanner(System.in).next();
			int intv = (1 << k++);
			if (k % 2 == 1) {
				if (((last-first)/intv) % 2 == 0) last -= intv;
				first += intv;
			} else {
				if (((last-first)/intv) % 2 == 0) first += intv;
				last -= intv; 
			}
		}
		return first;
	}

	// public int lastRemaining(int n) {
		// int k = 0; while (n / 2 > 0) { ++k; n /= 2; }
		// System.out.printf("k=%d\n", k);
		// long x = (1 << k);
		// for (int i = 1; i < k; i += 2) x -= (1 << i);
		// return (int)x;
	// }

	public static void main(String args[]) {
		assert new Solution().lastRemaining(1) == 1;
		assert new Solution().lastRemaining(31) == 16;
		assert new Solution().lastRemaining(32) == 22;
		assert new Solution().lastRemaining(33) == 22;
		assert new Solution().lastRemaining(34) == 24;
		assert new Solution().lastRemaining(2147483647) == 1073741824;
		assert new Solution().lastRemaining(2137473639) == 1064785784;

		System.out.println("done");
	}
}

