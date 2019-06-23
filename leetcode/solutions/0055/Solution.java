import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public boolean canJumpTLE(int[] nums) {

		if (nums == null || nums.length == 0)
			return false;

		Stack<Integer> stk = new Stack<Integer>();
		int color[] = new int[nums.length];

		stk.push(0);
		color[0] = 1;

		while (!stk.empty()) {
			int point = stk.pop();
			color[point] = 2;

			//System.out.printf("point: %d\n", point+1);

			if (point == nums.length-1)
				return true;

			int njumps = nums[point];
			// 这个循环太浪费时间, 要想办法优化!!!
			// 我当然可以用线段树, 此时问题泛化为leetcode 045
			// 我也可以用链表+复杂的数据解构进行优化
			// 这样一来, 我更加好奇, 到底leetcode045的BFS解法是如何做到的???
			for (int i = 1; i <= njumps; ++i) {
				int nxt = point + i;
				//!!! 一定要检查越界!!!!!!!
				if (nxt >= nums.length)
					break;
				if (color[nxt] == 0) {
					stk.push(nxt);
					color[nxt] = 1;
				}
			}
		}

		return false;
	}

	// 惭愧!!!
	// 维护一个"远方"(remote), 表示我当前所能到达的最远距离
	// 设我当前栈在第i个格子上
	// 如果i > remote, 说明我已经走到远方之外了, 我已经不可能到达终点了, 返回false
	// 如果i+a[i]大于远方, 说明我可以到达更元的地方, 更新我的远方
	// 否则, 继续走到i+1
	public boolean canJump(int[] nums) {
		if (nums == null || nums.length == 0)	
			return false;

		int remote = 0;
		for (int i = 0; i < nums.length; ++i) {
			//System.out.printf("%3d: %3d\n", i, remote);
			if (i > remote)
				return false;
			remote = Math.max(remote, nums[i]+i);
		}

		return true;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int nnums = scanner.nextInt();
		int nums[] = new int[nnums];
		for (int i = 0; i < nnums; ++i)
			nums[i] = scanner.nextInt();
		boolean res = new Solution().canJump(nums);
		System.out.printf("---> %s\n", res);
	}
}
