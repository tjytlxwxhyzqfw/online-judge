/**
 * 388 Longest Absolute File Path
 * Performance: speed=97%, memory=100%
 *
 * todo: discuss: if you use string.split, there is a very very short solution to this problem;
 */

import java.util.*;

public class Solution {
	public int lengthLongestPath(String input) {
		input += "\n";
		Stack<Item> stack = new Stack<>();
		int total = 0, current = 0, max = 0;
		int ident = 0;
		boolean f = false;
		for (int i = 0; i < input.length(); ++i) {
			char ch = input.charAt(i);
			switch (ch) {
			case '\n': 
				// System.out.printf("total=%d, current=%d, ident=%d\n", total, current, ident);
				while (!stack.isEmpty() && stack.peek().ident >= ident) {
					Item top = stack.pop();
					total -= top.len;
				}
				if (!f) current += 1;
				stack.push(new Item(ident, current));
				total += current;
				if (f) max = Math.max(max, total);

				f = false;
				current = ident = 0;
				break;
			case '\t':
				++ident;
				break;
			default:
				++current;
				if (ch == '.') f = true;
			}
		}
		return max;
	}

	public static void main(String args[]) {
		int max1 = new Solution().lengthLongestPath("a\n\tb\n\t\tc.txt\n\t\td.rmvb\n\teeeeeeeeee.txt");
		System.out.printf("max1=%d\n", max1);

		int max2 = new Solution().lengthLongestPath("a\n\tb\n\t\tc\n\td");
		System.out.printf("max2=%d\n", max2);
	}
}

class Item {
	int ident, len;
	Item(int ident, int len) {
		this.ident = ident;
		this.len = len;
	}
}

