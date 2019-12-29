/**
 * 946 Validate Stack Sequences 58.5% Medium 397/12
 * Performance: speed=49%, memory=50%
 */

import java.util.*;

public class Solution {
	public boolean validateStackSequences(int[] pushed, int[] popped) {
		Stack<Integer> stack = new Stack<>();
		int j = 0;
		for (int i = 0; i < popped.length; ++i) {
			while (stack.isEmpty() || stack.peek() != popped[i]) {
				if (j < pushed.length) stack.push(pushed[j++]);
				else return false;
			}
			stack.pop();
		}
		return true;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.validateStackSequences(new int[]{}, new int[]{});
		assert s.validateStackSequences(new int[]{1, 2, 3}, new int[]{1, 2, 3});
		assert s.validateStackSequences(new int[]{1, 2, 3}, new int[]{1, 3, 2});
		assert s.validateStackSequences(new int[]{1, 2, 3}, new int[]{3, 2, 1});
		assert s.validateStackSequences(new int[]{1, 2, 3}, new int[]{3, 1, 2}) == false;
		assert s.validateStackSequences(new int[]{1, 2, 3}, new int[]{2, 1, 3});
		assert s.validateStackSequences(new int[]{1, 2, 3}, new int[]{2, 3, 1});

		assert s.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1});
		assert s.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}) == false;


		System.out.println("done");
	}
}

