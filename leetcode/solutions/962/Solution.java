/**
 * 962 Maximum Width Ramp 42.9% Medium 391/11
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public int maxWidthRamp(int[] a) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < a.length; ++i) {
			if (stack.isEmpty() || a[i] < a[stack.peek()]) stack.push(i);
		}
		int max = 0;
		for (int i = a.length-1; !stack.isEmpty() && i >= 0; --i) {
			while (!stack.isEmpty() && a[i] >= a[stack.peek()]) max = Math.max(max, i-stack.pop());
		}
		return max;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.maxWidthRamp(new int[]{}) == 0;
		assert s.maxWidthRamp(new int[]{1}) == 0;
		assert s.maxWidthRamp(new int[]{3, 2, 1}) == 0;
		assert s.maxWidthRamp(new int[]{1, 2, 3}) == 2;
		assert s.maxWidthRamp(new int[]{3, 2, 1, 2, 3, 4}) == 5;
		assert s.maxWidthRamp(new int[]{30, 20, 10, 25, 15, 30, 15}) == 5;

		assert s.maxWidthRamp(new int[]{6, 0, 8, 2, 1, 5}) == 4;
		assert s.maxWidthRamp(new int[]{9, 8, 1, 0, 1, 9, 4, 0, 4, 1}) == 7;

		System.out.println("done");
	}
}

