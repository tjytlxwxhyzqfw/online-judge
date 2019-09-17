/**
 * 435 Non-overlapping Intervals
 * Performance: speed=88%, memory=62%
 *
 * todo: re-write the solution without LinkedList or any other support data structures.
 */

import java.util.*;

public class Solution {
	public int eraseOverlapIntervals(int[][] intervals) {
		I[] itvs = new I[intervals.length];
		for (int i = 0; i < intervals.length; ++i) itvs[i] = new I(intervals[i][0], intervals[i][1]);
		Arrays.sort(itvs);
		LinkedList<I> list = new LinkedList<>();
		for (I i : itvs) list.addLast(i);

		int n = 0;
		while (list.size() > 1) {
			I prev = list.removeFirst(), curr = list.getFirst();
			if (curr.left < prev.rght) { ++n; if (curr.rght > prev.rght) list.set(0, prev); }
		}
		return n;
	}

	public static void main(String args[]) {
		int n1 = new Solution().eraseOverlapIntervals(new int[][]{{1,100},{11,22},{1,11},{2,12}});
		System.out.printf("n1=%d\n", n1);
	}
}

class I implements Comparable<I> {
	int left, rght;
	I(int left, int rght) {
		this.left = left;
		this.rght = rght;
	}

	@Override
	public int compareTo(I i) {
		return left - i.left;
	}
}

