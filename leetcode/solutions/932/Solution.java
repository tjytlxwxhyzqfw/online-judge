/**
 * 932 Beautiful Array 55.9% 244/351
 * Performance: speed=92%, memory=20%
 */

import java.util.*;

// i read diss first before i write this solution
// 2 -> 1, 1 -> 1, 2
// 3 -> 2, 1 -> 1, 3, 2
// 4 -> 2, 2 -> 1, 3, 2, 4
// 7 -> 4, 3 -> 1, 3, 2, 4, | 1, 3, 2 -> 1, 5, 3, 7, 2, 6, 4

// i know how to opt memory usage

public class Solution {
	public int[] beautifulArray(int n) {
		if (n == 1) {
			return new int[]{1};
		}
		int[] left = beautifulArray((n+1)/2);
		int[] rght = beautifulArray(n/2);
		int[] result = new int[n];
		for (int i = 0; i < left.length; ++i) {
			result[i] = 2 * left[i] - 1;
		}
		for (int i = 0; i < rght.length; ++i) {
			result[left.length+i] = 2 * rght[i];
		}
		return result;
	}

	static void printA(int[] a) {
		for (int i = 0; i < a.length; ++i) System.out.printf("%d, ", a[i]);
		System.out.println();
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		printA(s.beautifulArray(1));
		printA(s.beautifulArray(2));
		printA(s.beautifulArray(3));
		printA(s.beautifulArray(4));
		printA(s.beautifulArray(5));
		printA(s.beautifulArray(6));
		printA(s.beautifulArray(7));
		printA(s.beautifulArray(8));
		printA(s.beautifulArray(9));

		System.out.println("done");
	}
}
