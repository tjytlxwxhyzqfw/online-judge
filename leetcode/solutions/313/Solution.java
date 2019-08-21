/**
 * 313 Super Ugly Number
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.PriorityQueue;

public class Solution {
	public int nthSuperUglyNumber(int n, int[] primes) {
		int[] a = new int[n];
		int[] p = new int[primes.length];

		int k = 0;
		a[0] = 1;

		Queue<Node> q = new PriorityQueue<>();
		for (int i = 0; i < primes.length; ++i) q.offer(new Node(primes[i], i));

		while (k < n-1) {
			Node next = q.poll();
			System.out.printf("poll: val=%d, idx=%d, prime=%d\n", next.val, next.idx, primes[next.idx]);
			if (next.val > a[k]) a[++k] = next.val;
			next.val = a[++p[next.idx]] * primes[next.idx];
			q.offer(next);
		}

		return a[n-1];
	}

	public static void main(String args[]) {
		int r1 = new Solution().nthSuperUglyNumber(177, new int[]{2, 3, 5, 7, 11});
		System.out.printf("%d\n", r1);
	}
}

class Node implements Comparable<Node> {
	int val, idx;
	Node(int v, int i) {
		val = v;
		idx = i;
	}

	@Override
	public int compareTo(Node another) {
		return val - another.val;
	}
}

