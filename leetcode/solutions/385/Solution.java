/**
 * 385 Mini Parser
 * Performance: speed=63%, memory=90%
 */

// review logs
// ----------
// Description: parse a string like "[123,[456,[789]]]" into NestedInteger
// NestedInteger: this is an integer or a list of NestedInteger.

import java.util.*;

public class Solution {
	public NestedInteger deserialize(String s) {
		if (s == null || s.length() == 0) return null;
		if (!s.startsWith("[")) return new NestedInteger(Integer.parseInt(s));

		Stack<NestedInteger> stack = new Stack<>();
		int begin = -1; // 一个整数数字的起点
		for (int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);
			switch (ch) {
			// 如果我遇到了左括号, 我知道我需要创建一个新的NestedInteger
			case '[':
				stack.push(new NestedInteger());
				break;
			// 如果我遇到了逗号, 那么一定是一个数字结束了或者一个List结束了, 总之有个NestedInteger结束了
			case ',':
				if (begin != -1) {
					// 因为begin!=-1, 所以我一定是遇到了一个整数数字
					// 栈中的元素必然是一个List, 所以我把这个数字加入到栈顶的List中去
					int n = Integer.parseInt(s.substring(begin, i));
					stack.peek().add(new NestedInteger(n));
					begin = -1;
				}
				// 否则, 那就是一个list结束了. 我不用管. 因为我在case']'的情况中,
				// 已经处理过list结束的情形了.
				break;
			// 如果我遇到了], 说明要么是一个list结束了, 要么是一个整数结束了.
			// 无论如何, 我都会把栈顶的list弹出, 然后作为新元素添加到新栈顶元素(一定是个List)中
			case ']':
				NestedInteger top = stack.pop();
				if (begin != -1) {
					top.add(new NestedInteger(Integer.parseInt(s.substring(begin, i))));
					begin = -1;
				}
				if (!stack.isEmpty()) stack.peek().add(top); else return top;
				break;
			// 遇到了不是括号, 不是逗号, 那么就是一个整数数字, 设置这个整数的起点是begin
			default:
				if (begin == -1) begin = i;
			}
		}

		throw new RuntimeException();
	}

	public static void main(String args[]) {
	}
}

