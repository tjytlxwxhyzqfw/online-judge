/**
 * 870 Advantage Shuffle 43.4% 324/22
 * Performance: speed=62%, memory=100%
 */

import java.util.*;

public class Solution {
	public int[] advantageCount(int[] a, int[] b) {
		Arrays.sort(a);
		Node[] nodes = new Node[b.length];
		for (int i = 0; i < b.length; ++i) nodes[i] = new Node(i, b[i]);
		Arrays.sort(nodes, (x, y) -> { return x.val - y.val; });

		Node[] perm = new Node[a.length];
		// fool: end is not necessary !!!
		int begin = 0, end = perm.length, bEnd = nodes.length;
		int j = 0;
		for (int i = 0; i < nodes.length; ++i) {
			// System.out.printf("minV=%d\n", nodes[i].val);
			while (j < a.length && a[j] <= nodes[i].val) {
				// System.out.printf("\tback: %d\n", a[j]);
				// attention: i use nodes[i].idx, end instead of bEnd here
				perm[--end] = new Node(nodes[--bEnd].idx, a[j]); 
				++j;
			}
			if (j < a.length) {
				// System.out.printf("\tfront: %d\n", a[j]);
				perm[begin++] = new Node(nodes[i].idx, a[j]);
				++j;
			}
		}

		Arrays.sort(perm, (x, y) -> { return x.idx - y.idx; });
		for (int i = 0; i < perm.length; ++i) a[i] = perm[i].val;

		return a;
	}

	static void printA(int[] a) {
		for (int i = 0; i < a.length; ++i) System.out.printf("%d, ", a[i]);
		System.out.println();
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		printA(s.advantageCount(new int[]{12, 24, 8, 32}, new int[]{13, 25, 32, 11}));

		// printA(s.advantageCount(new int[]{8, 12, 24, 32}, new int[]{11, 13, 25, 32}));

		// printA(s.advantageCount(new int[]{}, new int[]{}));
		// printA(s.advantageCount(new int[]{2}, new int[]{1}));
		// printA(s.advantageCount(new int[]{1}, new int[]{2}));
		// printA(s.advantageCount(new int[]{2, 7, 11, 15}, new int[]{1, 10, 4, 11}));
		// printA(s.advantageCount(new int[]{1, 2, 3, 4}, new int[]{5, 6, 7, 8}));
	
		System.out.println("done");
	}
}

class Node {
	int idx;
	int val;
	Node(int idx, int val) {
		this.idx = idx;
		this.val = val;
	}
}

