/**
 * 0338: CountingBits
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int[] countBits(int num) {
		int bitter = 1;
		int[] bits = new int[num+1];
		bits[0] = 0;
		for (int i = 1; i <=num; ++i) {
			if (i == bitter) {
				bits[i] = 1;
				bitter <<= 1;
			} else {
				bits[i] = 1 + bits[i-bitter/2];
			}
		}
		return bits;
	}
	public static void main(String args[]) {
	}
}

