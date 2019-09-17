/**
 * 503 Next Greater Element II 51.8% Medium
 * Performance: speed=81%, memory=100%
 * todo: use one for-statement to implement double loop
 */

import java.util.*;

public class Solution {
	public int[] nextGreaterElements(int[] a) {
		int[] b = new int[a.length];
		boolean[] c = new boolean[a.length];

		Stack<Integer> stack = new Stack();

		for (int i = 0; i < a.length; ++i) {
			while (!stack.isEmpty() && a[stack.peek()] < a[i]) {
				int top = stack.pop();
				b[top] = a[i];
				c[top] = true;
			}
			stack.push(i);
		}

		for (int i = 0; i < a.length; ++i) {
			while (!stack.isEmpty() && a[stack.peek()] < a[i]) {
				int top = stack.pop();
				b[top] = a[i];
				c[top] = true;
			}
			if (!c[i]) stack.push(i);
		}

		while (!stack.isEmpty()) b[stack.pop()] = -1;

		return b;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		print(s.nextGreaterElements(new int[]{}));
		print(s.nextGreaterElements(new int[]{0}));
		print(s.nextGreaterElements(new int[]{1, 2, 3, 4, 5}));
		print(s.nextGreaterElements(new int[]{5, 4, 3, 2 ,1}));
		print(s.nextGreaterElements(new int[]{1, 2, 3, 4, 5, 5 ,4, 3, 2, 1}));
		print(s.nextGreaterElements(new int[]{1, 3, 2, 1, 2, 1, 3, 1, 2, 4, 5, 4, 3}));
		System.out.println("all test passed");
	}

	static void print(int[] a) {
		for (int i = 0; i < a.length; ++i) System.out.printf("%d, ", a[i]);
		System.out.println();
	}
}

