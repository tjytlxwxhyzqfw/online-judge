/**
 * 241 Different Ways to Add Parentheses
 * Performance: speed=75%, memory=75%
 *
 * todo: do not use search() just recurse diffWaysToComput() even without override
 */

import java.util.*;

public class Solution {
	public List<Integer> diffWaysToCompute(String input) {
		if (input == null || input.length() == 0) return new ArrayList<>();

		List<Item> list = new ArrayList<>();
		int n = 0;
		for (int i = 0; i < input.length(); ++i) {
			char ch = input.charAt(i);
			if (ch == '*' || ch == '-' || ch == '+') {
				list.add(new Item(n, 0));
				n = 0;
				list.add(new Item(ch, 1));
			} else if (Character.isDigit(ch)) {
				n = n * 10 + ch - 48;
			}
		}
		list.add(new Item(n, 0));

		return search(list, 0, list.size());
	}

	List<Integer> search(List<Item> list, int begin, int end) {
		if (begin >= end) return null;
		if (begin+1 == end) return Arrays.asList(list.get(begin).val);

		List<Integer> result = new ArrayList<>();
		for (int i = begin; i < end; ++i) {
			if (list.get(i).type == 1) {
				List<Integer> left = search(list, begin, i);
				List<Integer> rght = search(list, i+1, end);
				addToResult(result, left, rght, list.get(i).val);
			}
		}

		return result;
	}

	void addToResult(List<Integer> result, List<Integer> left, List<Integer> rght, int op) {
		if (left == null || rght == null) return;
		for (Integer i : left) {
			for (Integer j : rght) {
				switch (op) {
					case (int)'*': result.add(i*j); break;
					case (int)'+': result.add(i+j); break;
					case (int)'-': result.add(i-j); break;
				}
			}
		}
	}

	public static void main(String args[]) {
		List<Integer> ans = new Solution().diffWaysToCompute("1-2+3*4+5-6");
		System.out.printf("ans: %s\n", ans);
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

