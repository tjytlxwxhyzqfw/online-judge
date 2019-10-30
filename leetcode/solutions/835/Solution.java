/**
 * 835 Image Overlap
 * Performance: speed=72%, memory=44%
 */

/*
there is a solution worthy read: https://leetcode.com/problems/image-overlap/discuss/130623/C%2B%2BJavaPython-Straight-Forward
*/

import java.util.*;

public class Solution {
	public int largestOverlap(int[][] a, int[][] b) {
		int n = 0;
		for (int i = 0; i < a.length; ++i) {
			for (int j = 0; j < a[0].length; ++j) {
				n = Math.max(n, overlap(a, b, i, j));
			}
		}
		for (int i = 0; i < b.length; ++i) {
			for (int j = 0; j < b[0].length; ++j) {
				n = Math.max(n, overlap(b, a, i, j));
			}
		}
		return n;
	}

	int overlap(int[][] a, int[][] b, int x, int y) {
		int n = 0;
		for (int i = 0; i < a.length; ++i) {
			for (int j = 0; j < a[0].length; ++j) {
				int i1 = i + x, j1 = j + y;
				if (i1 < b.length && j1 < b[0].length && a[i][j] * b[i1][j1] == 1) ++n;
			}
		}
		return n;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

