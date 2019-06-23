import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// 动态规划
// i: 从本地最少跳几步能到到终点
// i转移到状态j: i -> (i+1, i+2, i+a[i])
// 
// 根之前的题目一样, 尝试使用1维的数组,
//
// +线段树优化

public class Solution {

	private int nums[], dp[];
	private int nnums;
	private SegTree segTree = new SegTree();

	private class SegTree {
		public int length, round, size;
		public int rawdata[];
		public SegNode[] seg;

		public void build(int arr[]) {
			rawdata = arr;
			length = arr.length;
			round = calRound(length);
			size = 2 * round;
			seg = new SegNode[size];
			for (int i = 0; i < size; ++i)
				seg[i] = new SegNode();

			// 这个地方老是忘, arr会越解!!
			for (int i = round; i < size; ++i) {
				int idx = i -round;
				int val = idx < length ? arr[idx] : Integer.MAX_VALUE;
				seg[i].create(idx, val);
			}

			for (int i = round-1; i > 0; --i)
				seg[i].update(seg[i*2], seg[i*2+1]);
		}

		public void updated(int i) {
			int segidx = i + round;
			seg[segidx].create(i, rawdata[i]);
			//System.out.printf("updated: %3d ---> %d\n", i, rawdata[i]);
			for (int j = segidx/2; j > 0; j /= 2)
				seg[j].update(seg[j*2], seg[j*2+1]);
		}

		public int min(int root, int bgn, int end) {
			if (seg[root].bgn == bgn && seg[root].end == end)
				return seg[root].val;

			int leftBgn = seg[root*2].bgn, leftEnd = seg[root*2].end;
			int rghtBgn = seg[root*2+1].bgn, rghtEnd = seg[root*2+1].end;

			int bgn1 = Math.max(leftBgn, bgn), end1 = Math.min(leftEnd, end);
			int bgn2 = Math.max(rghtBgn, bgn), end2 = Math.min(rghtEnd, end);

			int minLeft = Integer.MAX_VALUE, minRght = Integer.MAX_VALUE;

			if (bgn1 <= end1)
				minLeft = min(root*2, bgn1, end1);
			if (bgn2 <= end2)
				minRght = min(root*2+1, bgn2, end2);

			return Math.min(minLeft, minRght);
		}

		public void print() {
			for (int i = 1; i < round; ++i)
				System.out.printf("\t%s\n", seg[i].toString());
			for (int i = round; i < size; ++i)
				System.out.printf("%d\t%s\n", i-round, seg[i].toString());
		}

		private int calRound(int n) {
			int power = 1;
			for (; n != 0; n >>= 1)
				power <<= 1;
			return power;
		}
	}

	private class SegNode {
		public int bgn, end;
		public int val;

		public void create(int idx, int val) {
			bgn = idx;
			end = idx;
			this.val = val;
		}

		public void update(SegNode left, SegNode rght) {
			bgn = left.bgn;
			end = rght.end;
			val = Math.min(left.val, rght.val);
		}

		public String toString() {
			return String.format("[%3d, %3d]: %d", bgn, end, val);
		}
	}


	public int jump(int[] nums) {
		this.nums = nums;
		this.nnums = nums.length;

		dp = new int[nnums];
		Arrays.fill(dp, Integer.MAX_VALUE);

		segTree.build(dp);
		//segTree.print();

		if (nums == null || nums.length == 0)
			return 0;

		for (int i = nnums-1; i >= 0; --i) {
			dpFill(i);
			//dpPrint(i);
			segTree.updated(i);
			//segTree.print();
		}

		return dp[0];
	}

	private void dpFill(int i) {
		//System.out.printf("----------------> dpFill: %d\n", i);
		if (i == nnums-1) {
			dp[i] = 0;
			return;
		}

		int njumps_min = Integer.MAX_VALUE;
		if (nums[i] > 0) {
			int bgn = i+1, end = Math.min(i+nums[i], nnums-1);
			njumps_min = segTree.min(1, bgn, end);
			//System.out.printf("min(%3d, %3d): %d\n", bgn, end, njumps_min);
		}
		dp[i] = (njumps_min == Integer.MAX_VALUE ? Integer.MAX_VALUE : njumps_min+1);
	}

	private void dpPrint(int i) {
		System.out.printf("%3d: %d\n", i, dp[i]);
	}

	public static void main(String args[]) {
		System.out.printf("begin\n");
		Scanner scanner = new Scanner(System.in);
		int nnums = scanner.nextInt();
		int nums[] = new int[nnums];
		for (int i = 0; i < nnums; ++i)
			nums[i] = scanner.nextInt();
		int ans = new Solution().jump(nums);
		System.out.printf("---> %d\n", ans);
	}
}
