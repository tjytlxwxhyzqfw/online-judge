/**
 * 373 Find K Pairs with Smallest Sums
 * Performance: speed=99%, memory=100%
 *
 * PriorityQueue<int[]> que = new PriorityQueue<>((a,b)->a[0]+a[1]-b[0]-b[1]);
 *
 * xxxxxx
 * xxxxxx
 * xxxxxx
 * xxxxxx
 */

import java.util.*;

public class Solution {
	public List<List<Integer>> kSmallestPairs(int[] left, int[] rght, int k) {
		List<List<Integer>> list = new ArrayList<>(k);
		if (k == 0 || left.length == 0 || rght.length == 0) return list;
	

		PriorityQueue<Item> q = new PriorityQueue<>();
		for (int i = 0; i < left.length; ++i) q.offer(new Item(left[i]+rght[0], i, 0));

		while (list.size() < k && !q.isEmpty()) {
			Item top = q.poll();
			System.out.printf("pop: (%d, %d)\n", left[top.y], rght[top.x]);
			list.add(Arrays.asList(left[top.y], rght[top.x]));
			if (top.x + 1 < rght.length) {
				++top.x;
				top.val = left[top.y] + rght[top.x];
				q.offer(top);
				System.out.printf("push: (%d, %d)\n", left[top.y], rght[top.x]);
			}
		}

		return list;
	}

	public static void main(String args[]) {
	}
}

class Item implements Comparable<Item> {
	int val, y, x;
	Item(int val, int y, int x) {
		this.val = val;
		this.y = y;
		this.x = x;
	}

	@Override
	public int compareTo(Item another) {
		return val - another.val;
	}
}

