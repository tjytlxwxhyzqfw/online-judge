/**
 * 907 Sum of Subarray Minimums 29.3% Medium 720/54
 * Performance: speed=%, memory=%
 */

import java.util.*;

// idea: use a stack to solve this problem
// let current index is i and the j is the max index where j < i && a[j] <= a[i]
// so we have:
// sum of min(end at i) = sum of min(end at [j]) + (i-j) * a[i]
// the problem is that how to find j ?
// use a stack<value, index>
// while top.value > current.value => pop !!! ===> this is a good good idea !!!

public class Solution {
	public int sumSubarrayMins(int[] a) {
		int[] b = new int[a.length];
		int total = 0, M = (int)(1e9+7);
		Stack<Node> stack = new Stack<>();
		for (int i = 0; i < a.length; ++i) {
			while (!stack.isEmpty() && stack.peek().val > a[i]) {
				stack.pop();
			}
			int j = -1;
			if (!stack.isEmpty()) {
				j = stack.peek().index;	
				b[i] += b[j];
			}
			b[i] += ((i-j) * a[i]) % M;
			stack.push(new Node(a[i], i));
			System.out.printf("i=%2d, v=%2d, j=%2d, b=%d\n", i, a[i], j, b[i]);
			total = (total + b[i]) % M;
		}
		return total;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.sumSubarrayMins(new int[]{}) == 0;
		assert s.sumSubarrayMins(new int[]{1}) == 1;
		assert s.sumSubarrayMins(new int[]{3, 1, 2, 4}) == 17;

		System.out.println("done");
	}
}

class Node {
	int val, index;
	Node(int val, int index) {
		this.val = val;
		this.index = index;
	}
}
