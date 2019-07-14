/**
 * 698: PartitionToKEqualSumSubsets
 * Performance: speed=%, memory=%
 *
 * case: 1, 2, 2, 3, 3, 4, target=5
 * if you pick 1+2+2=5, then you will never split 3, 3, 4 to 5+5
 * so you must pick {1, 4}, {2, 3}, {2, 3}
 *
 * case: 10, 10, 10, 7, 7, 7, 7, 7, 7, 6, 6, 6, sum=90, target=30
 * if you pick {10, 10, 10}, then you never get 30 by {7, 7, ..., 6, 6, 6} 
 * you must piek 3 * {10, 7, 7, 6}
 *
 * to solve this problem, you cannot just pick a target (such as {1, 2, 2} for 5),
 * and then pick another target with the remaining elements (such as {3, 3, 4} for 5)
 * instead, you must take all selection orders into count, that is, if there are 
 * 3 elements, you must consider 012, 021, 102, 120, 210, 201, and if there are n
 * elements, you must consider all n! situations
 *
 * todo: read Liu's book for backtracking and dfs
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	private Boolean[][] dp;
	private int[] nums;
	public boolean canPartitionKSubsets_MINE(int[] nums, int k) {
		int sum = 0;
		for (int i = 0; i < nums.length; ++i) sum += nums[i];
		if (sum % k != 0) return false;

		dp = new Boolean[sum/k+1][1<<nums.length];
		this.nums = nums;
		int[] state = new int[1];
		int target = sum / k;
		while (state[0] != (1<<nums.length)-1) {
			if (!find(target, state)) return false;
			System.out.printf("found: state -> %s\n", Integer.toBinaryString(state[0]));
			for (int i = 0; i < nums.length; ++i) {
				if (((1<<i)&state[0]) == 0) {
					state[0] |= (1<<i);
					target = sum / k - nums[i];
					System.out.printf("next: i=%d, v=%d, target=%d\n", i, nums[i], target);
					break;
				}
			}
		}
		return true;
	}

	private boolean find(int target, int[] state) {
		if (target < 0) return false;
		if (target == 0) return true;
		if (dp[target][state[0]] != null) return dp[target][state[0]];

		for (int i = 0; i < nums.length; ++i) {
			if (((1<<i)&state[0]) == 0) {
				state[0] |= (1<<i);
				if (find(target-nums[i], state)) return (dp[target][state[0]] = true);
				state[0] &= (~(1<<i));
			}
		}
		return (dp[target][state[0]] = false);
	}

	// ----------------------------------------------------------------------------------------- //
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		for(int num:nums)sum += num;
		if(k <= 0 || sum%k != 0)return false;
		int[] visited = new int[nums.length];
			return canPartition(nums, visited, 0, k, 0, 0, sum/k);
		}
	
	public boolean canPartition(int[] nums, int[] visited, int start_index, int k, int cur_sum, int cur_num, int target){
		System.out.printf("cp: start_index=%d, k=%d, cur_sum=%d, cur_num=%d, target=%d\n",
			start_index, k, cur_sum, cur_num, target);
		for (int i = 0; i < visited.length; ++i) System.out.printf("\ti=%d, v=%d, visited=%d\n", i, nums[i], visited[i]);

		if(k==1) {
			System.out.printf("=====> return true because = 1\n");
			return true;
		}

		if(cur_sum == target && cur_num>0) {
			System.out.printf("======> recursive\n");
			return canPartition(nums, visited, 0, k-1, 0, 0, target);
		}
		System.out.printf("======> basic\n");
		for(int i = start_index; i<nums.length; i++){
			if(visited[i] == 0){
				visited[i] = 1;
				if(canPartition(nums, visited, i+1, k, cur_sum + nums[i], cur_num++, target))return true;
				visited[i] = 0;
			}
		}
		return false;
	}
	// ------------------------------------------------------------------------------------------ //

	public static void main(String args[]) {
		boolean r1 = new Solution().canPartitionKSubsets(new int[]{1, 2, 2, 3, 3, 4, 5}, 4);
		System.out.printf("r1=%s\n", r1);
	}
}

