/**
 * 372 Super Pow
 * Performance: speed=84%, memory=100%
 */

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

