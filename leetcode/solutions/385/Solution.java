/**
 * 385 Mini Parser
 * Performance: speed=63%, memory=90%
 */

import java.util.*;

public class Solution {
	public NestedInteger deserialize(String s) {
		if (s == null || s.length() == 0) return null;
		if (!s.startsWith("[")) return new NestedInteger(Integer.parseInt(s));

		Stack<NestedInteger> stack = new Stack<>();
		int begin = -1;
		for (int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);
			switch (ch) {
			case '[':
				stack.push(new NestedInteger());
				break;
			case ',':
				if (begin != -1) {
					int n = Integer.parseInt(s.substring(begin, i));
					stack.peek().add(new NestedInteger(n));
					begin = -1;
				}
				break;
			case ']':
				NestedInteger top = stack.pop();
				if (begin != -1) {
					top.add(new NestedInteger(Integer.parseInt(s.substring(begin, i))));
					begin = -1;
				}
				if (!stack.isEmpty()) stack.peek().add(top); else return top;
				break;
			default:
				if (begin == -1) begin = i;
			}
		}

		throw new RuntimeException();
	}

	public static void main(String args[]) {
	}
}

