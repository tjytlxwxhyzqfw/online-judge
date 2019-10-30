/**
 * 826 Most Profit Assigning Work 36.8% Medium 254/46
 * Performance: speed=89%, memory=77%
 */

import java.util.*;

public class Solution {
	public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
		Node[] nodes = new Node[profit.length];
		for (int i = 0; i < nodes.length; ++i) nodes[i] = new Node(difficulty[i], profit[i]);
		Arrays.sort(nodes);
		Arrays.sort(worker);

		int total = 0, max = 0, j = 0;
		for (int i = 0; i < worker.length; ++i) {
			for (; j < nodes.length && nodes[j].difficulty <= worker[i]; ++j) {
				max = Math.max(max, nodes[j].profit);
			}
			total += max;
		}

		return total;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.maxProfitAssignment(new int[]{}, new int[]{}, new int[]{}) == 0;
		assert s.maxProfitAssignment(new int[]{}, new int[]{}, new int[]{100, 200}) == 0;
		assert s.maxProfitAssignment(new int[]{1}, new int[]{1}, new int[]{}) == 0;
		assert s.maxProfitAssignment(new int[]{10, 20, 30}, new int[]{10, 20, 30}, new int[]{9, 9, 9, 9, 9}) == 0;
		assert s.maxProfitAssignment(new int[]{10, 20, 30}, new int[]{10, 20, 30}, new int[]{10, 9, 9, 9}) == 10;
		assert s.maxProfitAssignment(new int[]{10, 20, 30}, new int[]{10, 20, 30}, new int[]{30, 9, 9, 9}) == 30;
		assert s.maxProfitAssignment(new int[]{10, 20, 30}, new int[]{10, 20, 30}, new int[]{30, 30, 30}) == 90;
		assert s.maxProfitAssignment(new int[]{10, 20, 30}, new int[]{10, 20, 30}, new int[]{10, 20, 30}) == 60;
		assert s.maxProfitAssignment(new int[]{10, 20, 30}, new int[]{30, 20, 10}, new int[]{30, 30, 30, 30}) == 120;
		assert s.maxProfitAssignment(
			new int[]{2, 4, 6, 8, 10}, new int[]{10, 20, 30, 40, 50}, new int[]{4, 5, 6, 7}) == 100;

		System.out.println("done");
	}
}

class Node implements Comparable<Node> {
	int difficulty, profit;
	Node(int d, int p) {
		difficulty = d;
		profit = p;
	}

	@Override
	public int compareTo(Node that) {
		return this.difficulty - that.difficulty;
	}
}
