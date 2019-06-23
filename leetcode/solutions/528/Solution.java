/**
 * 528 Random Pick with Weight
 * Performance: speed=93%, memory=63%
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solution {
	private Random rand = new Random(47);
	private int[] psums;

	public Solution(int[] w) {
		psums = new int[w.length];
		for (int i = 0; i < w.length; ++i) {
			psums[i] = w[i];
			if (i > 0) {
				psums[i] += psums[i-1];
			}
		}
	}

	public int pickIndex() {
		int r = 1 + rand.nextInt(psums[psums.length-1]);
		return lb(r, psums);  // WA: use lb not ub
	}

	private int lb(int x, int[] a) {
		int i = 0, j = a.length;
		while (i < j) {
			int k = i + (j - i) / 2;
			if (a[k] < x)
				i = k + 1;
			else
				j = k;
		}
		return i;
	}

	public static void main(String args[]) {
	}
}

