/**
 * 372 Super Pow
 * Performance: speed=84%, memory=100%
 */

// review logs
// -----------
// Description: 计算a^b, a是个整数, b是个数组, 表示一个大整数, 如2^[1, 0] = 1024
// 20200723: 没必要借助快速幂算法. 比如, 计算a^1337, 四位数上的基分别是: a^1, a^10, a^100, a^1000,
//   所以a^1336 = (a^1)^7 * (a^10)^3 * (a^100)^3 * (a^1000)^1, 且a^1000 = (a^100)^10, 计算是很方便的.

import java.util.*;

public class Solution {
	public int superPow(int a, int[] b) {
		a %= 1337;
		int p = 1;
		for (int i = 0; i < b.length; ++i) {
			p = qpow(p, 10);
			p = (p * qpow(a, b[i])) % 1337;
		}
		return p;
	}

	int qpow(int a, int n) {
		int bit = (1 << 3);
		int p = 1;
		while (bit > 0) {
			p = (p * p) % 1337;
			if ((n & bit) != 0) p = (p * a) % 1337;
			bit >>= 1;
		}
		return p;
	}

	public static void main(String args[]) {
	}
}

