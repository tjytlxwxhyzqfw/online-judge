/**
 * 658: Find K Closest Elements
 * Performance: speed=5%, memory=5%
 *
 * !!!ATTENTION!!!
 * I am sooooooo stupid, binary search and find to left or right !!!
 * What a waste of time !!!
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		Item[] items = new Item[arr.length];
		for (int i = 0; i < arr.length; ++i) {
			items[i] = new Item(Math.abs(x - arr[i]), arr[i]);
		}
		qsortk(items, 0, items.length, k);

		int values[] = new int[k];
		for (int i = 0; i < k; ++i) values[i] = items[i].value;
		Arrays.sort(values);
	
		List<Integer> result = new ArrayList<>(k);
		for (int i = 0; i < k; ++i) result.add(values[i]);
		return result;
	}

	private void qsortk(Item[] a, int i, int j, int k) {
		if (i < 0 || j > a.length || i >= j) return;

		// for (int h = i; h < j; ++h) System.out.printf("[%d], ", a[h].delta);
		// System.out.printf("\n");
		// System.out.printf("qsortk: [%d, %d), k=%d, i>=k: %s\n", i, j, k, i >= k);

		if (j - i == 1) return;
		if (i >= k) return;

		Item[] b = {a[i], a[i+(j-i)/2], a[j-1]};
		Arrays.sort(b);
		Item m = b[1];
		if (m.compareTo(a[i+(j-i)/2]) == 0) swap(a, i, i+(j-i)/2);
		else if (m.compareTo(a[j-1]) == 0) swap(a, i, j-1);
		// System.out.printf("m.delta=%d\n", m.delta);

		int x = i + 1, y = j - 1;
		while (x < y) {
			while (x < y && a[x].compareTo(a[i]) <= 0) ++x;
			while (a[y].compareTo(a[i]) > 0) --y;
			if (x < y) swap(a, x, y);
		}
		if (a[y].compareTo(a[i]) < 0) swap(a, i, y);

		// for (int h = i; h < j; ++h) System.out.printf("(%d), ", a[h].delta);
		// System.out.printf("\n");

		qsortk(a, i, y, k);
		qsortk(a, y+1, j, k);
	}

	private void swap(Item[] a, int i, int j) {
		Item t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void main(String args[]) {
		new Solution().findClosestElements(
			new int[]{1, 1, 1}, 
			3,
			0);
			
	}
}

class Item implements Comparable<Item> {
	int delta, value;
	Item(int delta, int value) {
		this.delta = delta;
		this.value = value;
	}

	@Override
	public int compareTo(Item that) {
		if (delta == that.delta)
			return value - that.value;
		return delta - that.delta;
	}
}

