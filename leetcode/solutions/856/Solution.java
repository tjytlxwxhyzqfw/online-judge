/**
 * 856 Score of Parentheses 57.7% Medium 713/29
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public int scoreOfParentheses(String s) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) == '(') stack.push(0);
			else {
				int sum = 0;
				while (stack.peek() != 0) sum += stack.pop();
				stack.pop();
				stack.push(sum == 0 ? 1 : 2 * sum);
			}
		} 
		int score = 0;
		while (!stack.isEmpty()) score += stack.pop();
		return score;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.scoreOfParentheses("") == 0;
		assert s.scoreOfParentheses("()") == 1;
		assert s.scoreOfParentheses("((((()))))") == 16;
		assert s.scoreOfParentheses("()()") == 2;
		assert s.scoreOfParentheses("((((()))))()") == 17;
		assert s.scoreOfParentheses("((())())") == 6;

		System.out.println("done");
	}
}

