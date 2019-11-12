/**
 * 853 Car Fleet 41.0% Medium 312/163	
 * Performance: speed=%, memory=%
 */

// target = 12
// position = 10, 8, 0, 5, 3
// speed =    2,  4, 1, 1, 3
// delta      2   4  12 7  9
// arrived    1   1  12 7  3
//
// target = 100
// position = 0,   10,  20
// speed    = 100  200  10

import java.util.*;

public class Solution {
        public int carFleet(int t, int[] p, int[] s) {
		Node[] nodes = new Node[p.length];
		for (int i = 0; i < nodes.length; ++i) {
			double a = (t - p[i]) / s[i];
			nodes[i] = new Node(a, p[i]);
		}

		// order the cars by their distances from the init places to the target
		Arrays.sort(nodes);

		int n = 0;
		double earlist = -1;
		for (int i = 0; i < nodes.length; ++i) {
			System.out.printf("i=%2d, p0=%2d, arrivedAt=%6.3f\n", i, nodes[i].position, nodes[i].arrivedAt);
			if (nodes[i].arrivedAt > earlist) {
				++n;
				earlist = nodes[i].arrivedAt;
				System.out.printf("n -> %2d, earlist  -> %6.3f\n", n, earlist);
			}
		}

		return n;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.carFleet(1, new int[]{}, new int[]{}) == 0;
		assert s.carFleet(1, new int[]{1}, new int[]{1}) == 1;
		assert s.carFleet(100, new int[]{5, 4, 3, 2, 1}, new int[]{1, 9, 9, 9, 9}) == 1;
		assert s.carFleet(100, new int[]{5, 4, 3, 2, 1}, new int[]{5, 4, 3, 2, 1}) == 5;

		assert s.carFleet(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3}) == 3;

		System.out.println("done");
	}
}

class Node implements Comparable<Node> {
	double arrivedAt;
	int position;
	Node(double a, int p) {
		arrivedAt = a;
		position = p;
	}

	@Override
	public int compareTo(Node that) {
		return that.position - position;
	}
}
