/**
 * 981 Time Based Key-Value Store
 *
 * Performance: speed=99%, memory=97%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeMap {
	private Map<String, ArrayList<Item>> storage;

	public TimeMap() {
		storage = new HashMap<>();
	}

	public void set(String key, String value, int timestamp) {
		if (storage.get(key) == null) storage.put(key, new ArrayList<>());
		storage.get(key).add(new Item(value, timestamp));
	}

	public String get(String key, int timestamp) {
		ArrayList<Item> items = storage.get(key);
		if (items == null) return "";
		int index = ub(timestamp, items);
		if (index == 0) return "";
		return items.get(index-1).value;
	}

	private int ub(int time, ArrayList<Item> items) {
		int i = 0, j = items.size();
		while (i < j) {
			int k = i + (j - i) / 2;
			if (items.get(k).time <= time) i = k + 1;
			else j = k;
		}
		return i;
	}

	public static void main(String args[]) {
	}
}

class Item {
	String value;
	int time;
	Item(String value, int time) {
		this.value = value;
		this.time = time;
	}
}

