/**
 * 132 Pattern 27.5% Medium (746/45)
 * Performance: speed=73%, memory=100%
 */

import java.util.*;

/*
(1) read disscuss
(2) why we solve this problem in reversing order ? for 132 pattern, 32 is continus and 13 is not continus;
    i think we can solve 123 problem in both order.
*/

public class Solution {
	public boolean find132pattern(int[] a) {
		Stack<Integer> stack = new Stack<Integer>();
		int mid = Integer.MIN_VALUE;
		for (int i = a.length-1; i >= 0; --i) {
			if (a[i] < mid) return true;
			while (!stack.isEmpty() && a[i] > stack.peek()) mid = Math.max(mid, stack.pop());
			stack.push(a[i]);
		}
		return false;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.find132pattern(new int[]{}) == false;
		assert s.find132pattern(new int[]{1}) == false;
		assert s.find132pattern(new int[]{1, 3}) == false;
		assert s.find132pattern(new int[]{1, 3, 2}) == true;
		assert s.find132pattern(new int[]{1, 3, 5}) == false;
		assert s.find132pattern(new int[]{20, 30, 10, 25}) == true;
		assert s.find132pattern(new int[]{20, 30, 10, 15, 12}) == true;

		System.out.println("done");
	}
}

