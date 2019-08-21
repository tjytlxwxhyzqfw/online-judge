/**
 * 307 Range Sum Query - Mutable
 * Performance: speed=79%, memory=81%
 * 1 2 3 4 5 6 7 8
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class NumArray {
	private int tree[];

	public NumArray(int[] nums) {
		int n = 1; while (n < nums.length) n *= 2; n *= 2;
		tree = new int[n]; for (int i = 0; i < nums.length; ++i) tree[n/2+i] = nums[i];
		for (int i = n-1; i >= 1; --i) tree[i/2] += tree[i];
	}

	public void update(int i, int val) {
		int delta = val - tree[tree.length/2+i];
		tree[tree.length/2+i] = val;
		int p = (tree.length/2 + i) / 2;
		while (p != 0) { tree[p] += delta; p /= 2; }
	}

	public int sumRange(int i, int j) {
		return sum(1, tree.length/2, tree.length-1, i+tree.length/2, j+tree.length/2);
	}

	private int sum(int root, int left, int rght, int i, int j) {
		if (rght < i || left > j) return 0;
		if (i <= left && rght <= j) return tree[root];
		int mid = (left + rght) / 2;
		return sum(root*2, left, mid, i, j) + sum(root*2+1, mid+1, rght, i, j);
	}

	private void print(String tag) {
		System.out.printf("%s: ", tag);
		for (int i = 1; i < tree.length; ++i) System.out.printf("%3d ", tree[i]);
		System.out.println();
	}

	public static void main(String args[]) {
		NumArray na = new NumArray(new int[]{1, 3, 5});
		System.out.printf("%d\n", na.sumRange(0, 2));
		na.update(0, 0);
		System.out.printf("%d\n", na.sumRange(0, 1));
		na.update(0, 9);
		System.out.printf("%d\n", na.sumRange(0, 0));
	}
}

