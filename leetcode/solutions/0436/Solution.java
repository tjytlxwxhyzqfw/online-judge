/**
 * 436 Find Right Interval
 * Performance: speed=98%, memory=84%
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public int[] findRightInterval(int[][] intervals) {
		if (intervals.length == 0) {
			return new int[0];
		}

		Interval[] inters = new Interval[intervals.length];
		for (int i = 0; i < inters.length; ++i) {
			inters[i] = new Interval(i, intervals[i][0], intervals[i][1]);
		}

		int[] result = new int[inters.length];

		Arrays.sort(inters);
		for (int i = 0; i < inters.length; ++i) {
			System.out.printf("left=%d(#%d), ", inters[i].left, inters[i].index);
		}
		System.out.printf("\n");

		for (int i = 0; i < inters.length; ++i) {
			Interval t = lb(intervals[i][1], inters);
			result[i] = (t == null ? -1 : t.index);
		}

		return result;
	
	}

	private Interval lb(int x, Interval[] inters) {
		int i = 0, j = inters.length;
		while (i < j) {
			int k = i + (j - i) / 2;
			if (inters[k].left < x) {
				i = k + 1;
			} else {
				j = k;
			}
		}
		// i = j && inter[i-1].left  < x <= inters[i].left
		return i == inters.length ? null : inters[i];
	}

	public static void main(String args[]) {
		int[][] a1 = new int[][]{{1, 4}, {2, 3}, {3, 4}};
		int[] r1 = new Solution().findRightInterval(a1);
		for (int i = 0; i < r1.length; ++i)
			System.out.printf("%d, ", r1[i]);
		System.out.printf("\n");

		int[][] a2 = new int[][]{{3, 4}, {2, 3}, {1, 2}};
                int[] r2 = new Solution().findRightInterval(a2);
                for (int i = 0; i < r2.length; ++i)
                        System.out.printf("%d, ", r2[i]);
                System.out.printf("\n");
	}
}

class Interval implements Comparable<Interval> {
	int index, left, rght;
	Interval(int index, int left, int rght) {
		this.index = index;
		this.left = left;
		this.rght = rght;
	}
	@Override
	public int compareTo(Interval that) {
		return left - that.left;
	}
}

