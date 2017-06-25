/**
 * 096 - Unique Binary Search Trees
 *
 * Same as 095
 */

public class Solution {
	public int numTrees(int n) {
		Integer nums[] = new Integer[n+1];
		nums[0] = 1;

		for (int i = 1; i <= n; ++i) {
			/* we are generating trees with i nodes */
			int ntrees = 0;
			for (int j = 0, nchs=i-1; j <= nchs; ++j) 
				ntrees += nums[j]*nums[nchs-j];
			nums[i] = ntrees;
		}

		return nums[n];
	}

	public static void main(String args[]) {
	}
}
