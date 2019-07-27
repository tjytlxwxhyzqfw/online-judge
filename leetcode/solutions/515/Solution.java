/**
 * 515: FindLargestValueInEachTreeRow
 * Performance: speed=6%, memory=92%
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {
	public List<Integer> largestValues(TreeNode root) {
		return bfs(root);
	}

	public List<Integer> bfs(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) return result;

		Queue<Item> q = new LinkedList<>();
		q.offer(new Item(root, 0));
		while (!q.isEmpty()) {
			Item x = q.poll();
			if (result.size() == x.d) result.add(Integer.MIN_VALUE);
			if (result.get(x.d) < x.n.val) result.set(x.d, x.n.val);
			if (x.n.left != null) q.offer(new Item(x.n.left, x.d+1));
			if (x.n.right != null) q.offer(new Item(x.n.right, x.d+1));
		}

		return result;
	}
	public static void main(String args[]) {
	}
}

class Item {
	TreeNode n;
	int d;

	Item(TreeNode n, int d) {
		this.n = n;
		this.d = d;
	}
}

