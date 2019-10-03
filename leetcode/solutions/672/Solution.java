/**
 * 672	Bulb Switcher II 50.3% Medium 104/749
 * Performance: speed=100%, memory=33%
 */

import java.util.*;

public class Solution {
	public int flipLights(int n, int m) {
		if (n == 0) return 0;

		if (m == 0) return 1;

		// init: o
		// m=1: o (flip even), x (flip odd)
		if (n == 1) return 2;

		// init: oo
		// m=1: xo, ox, xx
		// m=2: ox, xo, oo, xx
		if (n == 2) return m == 1 ? 3 : 4;

		// init: ooo
		// m=1: xox, oxo, xxx, xoo
		// m=2: oxo, xox, ooo, oxx (flip all)
		//      xxx, ooo, xox, xxo (flip odd=13)
		//	xxx, ooo, xox, xxo (flip even=2)
		//      oox, xxo, oxx, ooo (flip 3n+1=1)
		//      total: 8
		// n == 3 is same with n >= 4

		switch(m) {
			case 1: return 4;
			case 2: return 7;
			default: return 8;
		}
	}

	// numbers can be divided into 4 groups:
	// A: 1, 7, 13, 19, ...
	// B: 4, 10, 16, ...
	// C: 2, 6, 8, 12, ...
	// D: 3, 5, 9, 11, ...
	// and lights in same group must be at same state all the time.
	// so:
	// 	flip all  = flip ABCD = 1111
	//	flip odd  = flip AD   = 1001
	//	flip even = flip BC   = 0110
	// 	flip 3n+1 = flip AB   = 1100
	// and i use this function to find how many states at all amoung all possible operations.
	void test() {
		boolean[] s = new boolean[16];
		byte[] masks = new byte[]{15 /*1111*/, 9 /*1001*/, 6 /*0110*/, 12 /*1100*/};
		Queue<Byte> q = new LinkedList<>();
		s[0] = true;
		q.offer((byte)0);
		while (!q.isEmpty()) {
			byte x = q.poll();
			System.out.printf("get x: %d\n", x);
			for (int i = 0; i < masks.length; ++i) {
				byte y = (byte)((~x & masks[i]) | (x & ~masks[i]));
				if (!s[y]) {
					s[y] = true;
					q.offer(y);
					System.out.printf("\t=> y: %d\n", y);
				}
			}
		}
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		s.flipLights(0, 0);
                System.out.println("done");
	}
}

