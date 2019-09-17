/**
 * 380: Insert Delete GetRandom O(1)
 * Performance: speed=76%, memory=98%
 */

import java.util.*;

class RandomizedSet {
	HashMap<Integer, Integer> map;
	ArrayList<Integer> list;
	int next;
	Random rand;

	/** Initialize your data structure here. */
	public RandomizedSet() {
		map = new HashMap<>();
		list = new ArrayList<>();
		rand = new Random(47);
	}
	
	/** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	public boolean insert(int val) {
		if (map.containsKey(val)) return false;
		if (next >= list.size()) list.add(val); else list.set(next, val);
		map.put(val, next);
		++next;
		return true;
	}
	
	/** Removes a value from the set. Returns true if the set contained the specified element. */
	public boolean remove(int val) {
		Integer rmI = map.remove(val);
		if (rmI == null) return false;
		int lastE = list.get(--next);
		if (lastE != val) {
			list.set(rmI, lastE);
			map.put(lastE, rmI);
		}
		return true;
	}
	
	/** Get a random element from the set. */
	public int getRandom() {
		if (next == 0) return 0;
		return list.get(rand.nextInt(next));
	}

	public static void main(String args[]) {
	}
}

