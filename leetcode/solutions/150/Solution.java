/**
 * 150 Evaluate Reverse Polish Notation 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
	public int evalRPN(String tokens[]) {
		Stack<Integer> stack = new Stack<>();
		for (String token : tokens) {
			Integer op = "+-*/".indexOf(token);
			if (op >= 0) {
				Integer rght = stack.pop();
				Integer left = stack.pop();
				Integer result = compute(left, rght, op);
				stack.push(result);
			} else {
				stack.push(Integer.parseInt(token));
			}
		}
		return stack.pop();
	}

	private Integer compute(Integer left, Integer rght, Integer op) {
		System.out.printf("compute: (%d, %d, %d)\n", left, rght, op);
		Integer result;
		switch (op) {
			case 0:
				result = left + rght;
				break;
			case 1:
				result = left - rght;
				break;
			case 2:
				result = left * rght;
				break;
			case 3:
				result = left / rght;
				break;
			default:
				throw new RuntimeException("impossible");
		}
		return result;
	}

	public static void main(String args[]) {
	}
}

