/**
 * 241 Different Ways to Add Parentheses
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Solution {
	Stack<Item> stack;
	public List<Integer> diffWaysToCompute(String input) {
		List<Item> list = new ArrayList<>();
		stack = new Stack<>();

		int n = 0;
		for (int i = 0; i < input.length(); ++i) {
			int ch = input.charAt(i);
			if (ch == '+' || ch == '-' || ch == '*') {
				{ list.add(new Item(n, 0)); n = 0; }
				list.add(new Item(ch, 1));
			} else if (Character.isDigit(ch)) {
				n = n * 10 + ch - 48;
			}
		}
		list.add(new Item(n, 0));

		Item[] items = new Item[list.size()];
		for (int i = 0; i < list.size(); ++i) {
			items[i] = list.get(i);
			if (items[i].type == 0) System.out.printf("%d\n", items[i].val);
			else System.out.printf("%c\n", (char)items[i].val);
		}

		List<Integer> result = new ArrayList<>();
		dfs(new int[items.length], 0, items, new boolean[items.length], 0, 0, result);
		for (int x : result) System.out.printf("result=%d\n", x);

		return null;
	}

	void dfs(int[] suffix, int n, Item[] items, boolean[] v, int vN, int opN, List<Integer> out) {
		if (n == items.length) {
			printSufArr(suffix, items);
			for (int k = 0; k < suffix.length; ++k) {
				int i = suffix[k];
				if (items[i].type == 0) stack.push(items[i]);
				else {
					Item rght = stack.pop(), left = stack.pop();
					switch (items[i].val) {
						case (int)'+': stack.push(new Item(left.val+rght.val, 0)); break;
						case (int)'-': stack.push(new Item(left.val-rght.val, 0)); break;
						case (int)'*': stack.push(new Item(left.val*rght.val, 0)); break;
					}
				}
			}
			out.add(stack.pop().val);
			if (!stack.isEmpty()) throw new RuntimeException("invalid input");
			return;
		}

		int p = -1;
		for (int i = 0; i < items.length; ++i) if (items[i].type == 0 && !v[i]) {p = i; break;}
		if (p != -1) { v[p] = true; suffix[n] = p; dfs(suffix, n+1, items, v, vN+1, opN, out); v[p] = false; }

		if ((vN-opN) < 2) return;
		p = -1;  // index of first un-visited operator
		for (int i = 0; i < items.length; ++i) if (items[i].type == 1 && !v[i]) {p = i; break; }
		if (p != -1) { v[p] = true; suffix[n] = p; dfs(suffix, n+1, items, v, vN, opN+1, out); v[p] = false; }
	}

	void printItemArr(Item[] items) {
		for (int i = 0; i < items.length; ++i) {
			if (items[i].type == 0) System.out.printf("%d ", items[i].val);
                        else System.out.printf("%c ", (char)items[i].val);
		}
		System.out.printf("\n");
	}

	void printSufArr(int[] suf, Item[] items) {
		for (int k = 0; k < items.length; ++k) {
			int i = suf[k];
			if (items[i].type == 0) System.out.printf("%d ", items[i].val);
                        else System.out.printf("%c ", (char)items[i].val);
		}
		System.out.printf("\n");
	}

	public static void main(String args[]) {
		new Solution().diffWaysToCompute("2*3-4*5");
	}
}

class Item {
	int val;
	int type;
	Item(int val, int type) {
		this.val = val;
		this.type = type;
	}
}

