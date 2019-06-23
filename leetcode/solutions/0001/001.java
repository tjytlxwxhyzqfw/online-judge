class Solution {
    public int[] twoSum(int[] nums, int target) {
        int i = 0, j = 0;
	outer:
        for (i = 0; i < nums.length; ++i)
            for (j = i+1; j < nums.length; ++j)
                if (nums[i]+nums[j] == target) break outer;
        int ans[] = new int[2];
        ans[0] = i;
	ans[1] = j;
        return ans;
    }
}

public class P1 {
	public static void main(String args[]) {
		int array[] = {3, 2, 4};
		Solution solution = new Solution();
		int[] ans = solution.twoSum(array, 6);
		System.out.printf("%d, %d\n", ans[0], ans[1]);
	}
}
