/**
 * 909 Snakes and Ladders 35.9% Medium 217/564
 * Performance: speed=96%, memory=83%
 */

import java.util.*;

// dijkstra without weights ? yes

public class Solution {
	public int snakesAndLadders(int[][] board) {
		int n = board.length * board.length;
		int[] graph = new int[n];
		for (int y = 0; y < board.length; ++y) {
			int start = n - board.length * y - 1;
			int inc = -1;
			if (y % 2 != board.length % 2) { // attention: be careful of this !!!
				start -= board.length - 1;
				inc = 1;
			}
			for (int x = 0; x < board.length; ++x) {
				graph[start] = board[y][x];
				if (graph[start] != -1) --graph[start];
				System.out.printf("graph[%2d]=%d\n", start, graph[start]);
				start += inc;
			}
		}
		return bfs(graph);
	}

	int bfs(int[] graph) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0));
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.index == graph.length-1) return node.nsteps;
			for (int i = 0; i < 6; ++i) {
				int target = node.index + i + 1;
				if (target >= graph.length || graph[target] == -2) continue; // already in the queue
				if (graph[target] == -1) {
					q.offer(new Node(target, 1 + node.nsteps));
				} else {
					q.offer(new Node(graph[target], 1 + node.nsteps));
				}
				graph[target] = -2;
			}
		}
		return -1;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.snakesAndLadders(new int[][]{{-1}}) == 0;
		assert s.snakesAndLadders(new int[][]{{-1, -1}, {-1, -1}}) == 1;
		assert s.snakesAndLadders(new int[][]{
			{-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1},
			{-1,35,-1,-1,13,-1},
			{-1,-1,-1,-1,-1,-1},
			{-1,15,-1,-1,-1,-1}}) == 4;
		assert s.snakesAndLadders(new int[][]{
			{-1,-1,19,10,-1},
			{2,-1,-1,6,-1},
			{-1,17,-1,19,-1},
			{25,-1,20,-1,-1},
			{-1,-1,-1,-1,15}}) == 2;

		System.out.println("done");
	}
}

class Node {
	int index, nsteps;
	Node(int index_, int nsteps_) {
		index = index_;
		nsteps = nsteps_;
	}
}

