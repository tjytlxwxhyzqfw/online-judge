/**
 * 957 Prison Cells After N Days 37.8% Medium 276/479
 * Performance: speed=99%, memory=97%
 */

import java.util.*;

public class Solution {
	public int[] prisonAfterNDays(int[] cells, int N) {
		int[][] list = new int[15][];
		list[0] = cells;
		for (int i = 1; i < list.length; ++i) list[i] = nextDay(list[i-1]);
		if (N <= 14) return list[N];
		int n = N % 14;
		if (n == 0) n = 14;
		return list[n];
	}

	int[] nextDay(int[] cells) {
		int[] next = new int[cells.length];
		for (int i = 1; i < cells.length-1; ++i) 
			if (cells[i-1] == cells[i+1]) next[i] = 1;
		return next;
	}

	void printCells(int day, int[] cells) {
		System.out.printf("%3d: ", day);

		int n = 0;
		for (int i = 0; i < cells.length; ++i) {
			n <<= 1;
			n += cells[i];
			System.out.printf("%d ", cells[i]);
		}

		System.out.printf(" => %d\n", n);
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		s.prisonAfterNDays(new int[]{0, 0, 0, 0, 0, 0, 0, 0}, 0);
		s.prisonAfterNDays(new int[]{1, 1, 1, 1, 1, 1, 1, 1}, 0);
		s.prisonAfterNDays(new int[]{1, 1, 0, 0, 1, 0, 0, 1}, 0);

		s.printCells(-1, s.prisonAfterNDays(new int[]{1,0,0,1,0,0,1,0}, 1000000000));

		System.out.println("done");
	}
}

