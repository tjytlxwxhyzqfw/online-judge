/**
 * 284 Peeking Iterator
 * Performance: speed=14%, memory=100%
 */

import java.util.*;

class PeekingIterator implements Iterator<Integer> {
	boolean hasNext;
	Integer next;
	Iterator<Integer> iterator;
	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		this.iterator = iterator;
		next = (hasNext = iterator.hasNext()) ? iterator.next() : null;
	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		return next;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		Integer current = next;
		next = (hasNext = iterator.hasNext()) ? iterator.next() : null;
		return current;
	}

	@Override
	public boolean hasNext() {
		return hasNext;
	}

	public static void main(String args[]) {
	}
}

