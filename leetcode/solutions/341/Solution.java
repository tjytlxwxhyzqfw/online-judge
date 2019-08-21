/**
 * 341 Flatten Nested List Iterator
 * Performance: speed=69%, memory=100%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class NestedIterator implements Iterator<Integer> {
	List<NestedInteger> list;
	Stack<Iterator<NestedInteger>> stack;
	Integer nextInt;

	public NestedIterator(List<NestedInteger> nestedList) {
		list = nestedList;
		stack = new Stack<>();
		stack.push(list.iterator());
	}

	@Override
	public Integer next() {
		return nextInt;
	}

	@Override
	public boolean hasNext() {
		while (!stack.isEmpty()) {
			Iterator<NestedInteger> top = stack.peek();
			if (top.hasNext()) {
				NestedInteger ni = top.next();
				if (ni.isInteger()) { nextInt = ni.getInteger(); return true; }
				else stack.push(ni.getList().iterator());
			} else stack.pop();
		}
		return false;
	}

	public static void main(String[] args) {}
}
