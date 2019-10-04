/**
 * 735 Asteroid Collision 39.1% Medium 553/70
 * Performance: speed=29%, memory=50%
 */

import java.util.*;

public class Solution {
	public int[] asteroidCollision(int[] a) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < a.length; ++i) {
			int curr = a[i];
			// top curr
			// ->  -> ok
			// -> <- crush
			// <- -> ok
			// <- <- ok
			while (!stack.isEmpty() && stack.peek() > 0 && curr < 0) {
				int top = stack.pop();
				if (top > -curr) curr = top;
				else if (top == -curr) curr = 0;
				// else top is crushed by curr and go on
			}
			if (curr != 0) stack.push(curr);
		}
		int[] b = new int[stack.size()];
		for (int i = b.length-1; i >= 0; --i) b[i] = stack.pop();
		return b;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

