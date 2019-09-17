/**
 * 481	Magical String 46.5% Medium
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public int magicalString(int n) {
		int k = 0;
		byte[] b = magic(n);
		for (int i = 0; i < n; ++i) k += (byte)(2 - b[i]);
		return k;
	}

	public byte[] magic(int n) {
		byte[] b = new byte[n+3];
		{ b[0] = 1; b[1] = 2; b[2] = 2; }
		int i = 2, j = 3;
		while (j < n) {
			{ b[j] = (byte)(3 - b[j-1]); ++j; }
			if (b[i] == 2) { b[j] = b[j-1]; ++j; }
			++i;
		}
		return b;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.magicalString(0) == 0;
		assert s.magicalString(1) == 1;
		assert s.magicalString(3) == 1;
		assert s.magicalString(6) == 3;
	}
}

