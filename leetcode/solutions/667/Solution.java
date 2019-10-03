/**
 * 667 Beautiful Arrangement II 52.6% Medium
 * Performance: speed=88%, memory=25%
 */

/*

i dont konw how do i come up with this idea.

i asume than i can always make the k deltas be 1, 2, .., k (i dont know why)

first, i try arrange n number to get n-1 deltas

5->4: 1 5 2 4 3
6->5: 1 6 2 5 3 4
7->6: 1 7 2 6 3 5 4

then, i arrange n numbers to get n-2 deltas
            
5->3: 1 4 2 3 5
5->2: 1 3 2 4 5
5->1: 1 2 3 4 5

i noticed that int result of 5->3, 1,2,3,4 occupied the first 4 blanks.
then, other seq has the same feature: n->k, then 1, 2, ..., k+1 occupied the first k+1 blanks
then i already know how to arange n numbers to get n-1 deltas ...
            
7->5: 1 6 2 5 3 4 7
7->3: 1 4 2 3 5 7 6
7->2: 1 3 2 4 6 5 7

*/

/*
there is a good explaination: https://leetcode.com/problems/beautiful-arrangement-ii/discuss/106948/C%2B%2B-Java-Clean-Code-4-liner
*/

import java.util.*;

public class Solution {
	public int[] constructArray(int n, int k) {
		int[] a = new int[n];
		++k;
		for (int i = 0; i < n; i += k) arrange(a, i, Math.min(i+k, n));
		printA(a);
		return a;
	}

	void arrange(int[] a, int begin, int end) {
		// System.out.printf("arrange: [%d, %d)\n", begin, end);
		int n = end - begin;
		for (int i = begin; i < end; ++i) {
			int off = i - begin;
			a[i] = begin + (off % 2 == 0 ? off/2+1 : n-off/2);
		}
	}

	void printA(int a[]) {
		for (int i = 0; i < a.length; ++i) System.out.printf("%d, ", a[i]);
		System.out.println();
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		s.constructArray(17, 5);
                System.out.println("done");
	}
}

