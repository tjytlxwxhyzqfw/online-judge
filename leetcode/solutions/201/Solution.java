/**
 * 
 * 0101
 * 0110
 * 0111
 *
 * 111011
 * 010101
 *
 * xx1x xxxx
 * xx0x xxxx
 * xx1x xxxx
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int rangeBitwiseAnd(int m, int n) {
		int result = 0;
		int one = 1;
		for (int i = 0; i < 32; ++i) {
			int bitter = (one << i);
			// System.out.printf("bitter=%d\n", bitter);
			if ((bitter & m) == 0 || (bitter & n) == 0) {
				continue;
			}

			// thus bitter & m == 1 && bitter & n == 1
			int medium = n & (~bitter);
			if (m > medium) {
				result |= bitter;
			}
		}

		return result;
	}

	public static void main(String args[]) {
		int n1 = (new Solution()).rangeBitwiseAnd(5, 7);
		System.out.printf("n1=%d\n\n", n1);

		int n2 = (new Solution()).rangeBitwiseAnd(5, 5);
		System.out.printf("n2=%d\n", n2);

		int n3 = (new Solution()).rangeBitwiseAnd(Integer.MAX_VALUE, Integer.MAX_VALUE);
		System.out.printf("n3=%d\n", n3);

		int n4 = (new Solution()).rangeBitwiseAnd(21, 87);
		System.out.printf("n4=%d\n", n4);
	}
}

