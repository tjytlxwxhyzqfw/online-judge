/**
 * 381 Insert Delete GetRandom O(1) - Duplicates allowed
 * pass=34 ud=821/70 s=99 m=99
 */

import java.util.*;

public class RandomizedCollection {

	HashMap<Integer, MyArrayList> val2idxs;
	List<List<Integer>> list;
	int next;
	Random rand;

	/** Initialize your data structure here. */
	public RandomizedCollection() {
		val2idxs = new HashMap<>();
		list = new ArrayList<>();
		rand = new Random(47);
		next = 0;
	}
	
	/** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
	public boolean insert(int val) {
		MyArrayList idxs = val2idxs.get(val);
		if (idxs == null) idxs = new MyArrayList();
		idxs.add(next);
		if (idxs.size() == 1) val2idxs.put(val, idxs);

		List<Integer> elem = Arrays.asList(val, idxs.size()-1);
		if (list.size() <= next) {
			list.add(elem);
		} else {
			list.set(next, elem);
		}
		next++;
		// System.out.printf("insert: val=%d, next=%d, list.size=%d\n", val, next, list.size());
		return idxs.size() == 1;
	}
	
	/** Removes a value from the collection. Returns true if the collection contained the specified element. */
	public boolean remove(int val) {
		MyArrayList idxs = val2idxs.get(val);
		if (idxs == null || idxs.size() == 0) {
			return false;
		}
		int idx = idxs.removeLast();
		// System.out.printf("remove: %d, idx=%d, list.size()=%d, next=%d\n", val, idx, list.size(), next);
		next--;
		if (next > 0) {
			List<Integer> last = list.get(next);
			list.set(idx, last);
			val2idxs.get(last.get(0)).set(last.get(1), idx);
		}
		return true;
	}
	
	/** Get a random element from the collection. */
	public int getRandom() {
		return list.get(rand.nextInt(next)).get(0);
	}

	public static void main(String args[]) {
		RandomizedCollection s = new RandomizedCollection();
		System.out.println("done");
	}
}

class MyArrayList {
	int next;
	ArrayList<Integer> list;

	MyArrayList() {
		next = 0;
		list = new ArrayList<>();
	}

	void add(int val) {
		if (list.size() <= next) {
			list.add(val);
		} else {
			list.set(next, val);
		}
		next++;
	}

	int size() { return next; }
	void set(int idx, int val) { list.set(idx, val); }

	int removeLast() {
		next--;
		return list.get(next);
	}
}

