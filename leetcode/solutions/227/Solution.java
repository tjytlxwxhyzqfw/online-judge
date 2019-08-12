/**
 * 227 Basic Calculator II
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
	public int calculate(String s) {
	}

	public int calculate_complicated(String s) {
		Stack<Character> stack = new Stack<Character>();
		Map<Character, Integer> opM = new HashMap<>();
		opM.put('+', 1);
		opM.put('-', 1);
		opM.put('*', 0);
		opM.put('/', 0);

		List<Item> list = new ArrayList<>();
		
		int n = 0;
		for (int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);
			Integer opN = opM.get(ch);
			if (opN != null) {
				list.add(new Item(0, n)); n = 0;
				while (!stack.isEmpty()) {
					char top = stack.peek();
					if (opM.get(top) <= opN) list.add(new Item(1, stack.pop()));
					else break;
				}
				stack.push(ch);
			} else if ('0' <= ch && ch <= '9') {
				n = n * 10 + ch - 48;
			}
		}
		list.add(new Item(0, n));
		while (!stack.isEmpty()) list.add(new Item(1, stack.pop()));

		for (Item i : list) {
			if (i.type == 0) System.out.printf("%d ", i.toInt());
			else System.out.printf("%c ", i.toCh());
		}
		System.out.printf("\n");
		

		Stack<Integer> values = new Stack<>();
		for (Item x : list) {
			if (x.type == 0) values.push(x.toInt());
			else cal(values, x.toCh());
		}
		return values.pop();
	}

	void cal(Stack<Integer> s, char op) {
		int rght = s.pop(), left = s.pop();
		switch (op) {
		case '+':
			s.push(left + rght);
			break;
		case '-':
			s.push(left - rght);
			break;
		case '*':
			s.push(left * rght);
			break;
		case '/':
			s.push(left / rght);
			break;
		}
	}

	public static void main(String args[]) {
		int r1 = new Solution().calculate("3    +    5    / 2 + 2 * 2 * 2 + 6 / 7");
		System.out.printf("r1=%d\n", r1);
	}
}

class Item {
	int type; // 0: int, 1: op
	Object val;
	Item(int type, Object val) {
		this.type = type;
		this.val = val;
	}

	int toInt() {
		return (Integer)val;
	}

	char toCh() {
		return (Character)val;
	}
}

