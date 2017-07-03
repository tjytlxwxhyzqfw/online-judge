/**
 * 120 - trangle
 *
 * 我考虑了一些额外的因素:
 *	1. 题目给定的列表可能是 LinkedList
 *	2. 路径之和可能大于Integer.MaX_VALUE
 *
 *
 * 我用了正向DP
 */

import java.util.List;

public class Solution {
	public int minimumTotal(List<List<Integer>> trangle) {
		if (trangle==null || trangle.isEmpty() || trangle.get(0)==null || trangle.get(0).isEmpty())
			return Integer.MIN_VALUE;

		Long current[] = new Long[trangle.size()];
		Long last[] = new Long[trangle.size()];

		last[0] = new Long(trangle.get(0).get(0));

		Integer y = 0;
		for (List<Integer> row : trangle) {
			if (y == 0) {
				++y;
				continue;
			}

			Integer x = 0;
			for (Integer val : row) {
				// 只能由两个元素转化而来啊!!!
				Long left = (x-1 < 0 ? Long.MAX_VALUE : last[x-1]);
				Long rght = (x >= y ? Long.MAX_VALUE : last[x]);
				Long minval = Math.min(left, rght);
				current[x] = minval+val;;
				++x;
			}

			Long temp[] = current;
			current = last;
			last = temp;

			++y;
		}

		Long minval = Long.MAX_VALUE;
		for (Long val : last) {
			if (minval > val)
				minval = val;
		}

		return (minval > Integer.MAX_VALUE) ? null : minval.intValue();
	}

	public static void main(String args[]) {
	}
}
