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

// review logs
// -----------
// Description: 给定两个有序数组left, rght; 求k个对(x in left, y in rght), 并且(x, y)是和最小的那k个.
// 20200723: 今天的独立想法: 最小的肯定是left[0]+rght[0], 把这个数入堆. 以后, 如果我们从堆中取出了left[i]+rght[j], 
//   那么把left[i]+rght[j+1]和left[i+1]+rght[j]这两个数入堆. 
//   而我在Solution中的解法是: 把left x rght的那个二维数组的第一行入堆. 然后每取出一个, 就把取出这个数的下面的那个数入堆.
//   这个题我之前居然做出来了, 有点怀疑自己. 是不是当时不会, 然后强行看答案, 然后再一次过的啊...

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

