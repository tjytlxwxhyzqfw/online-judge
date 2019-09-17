/**
 * 427 Construct Quad Tree
 * Performance: speed=99%, memory=100%
 */

import java.util.*;

public class Solution {
	public Node construct(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return null;
		return construct(grid, 0, 0, grid[0].length-1, grid.length-1);
	}

	Node construct(int[][] grid, int left, int top, int right, int bottom) {
		if (left == right && top == bottom) {
			return new Node(grid[top][left] != 0, true, null, null, null, null);
		}
		int m1 = (left+right) / 2, m2 = (top+bottom) / 2;
		Node n1 = construct(grid, left, top, m1, m2);
		Node n2 = construct(grid, m1+1, top, right, m2);
		Node n3 = construct(grid, left, m2+1, m1, bottom);
		Node n4 = construct(grid, m1+1, m2+1, right, bottom);
		if ((n1.isLeaf && n2.isLeaf && n3.isLeaf && n4.isLeaf) &&
				(n1.val == n2.val && n2.val == n3.val && n3.val == n4.val)) {
			return n1;
		}
		return new Node(true, false, n1, n2, n3, n4);
	}

	public static void main(String args[]) {
	}
}

