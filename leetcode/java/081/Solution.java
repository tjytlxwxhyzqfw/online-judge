import java.util.Arrays;

public class Solution {
	public boolean search(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return false;
		if (nums.length == 1)
			return nums[0] == target;

		int breaki = nums.length;
		for (int i = 1; i < nums.length; ++i) {
			if (nums[i] < nums[i-1]) {
				breaki = i;
				break;
			}
		}

		int x = Arrays.binarySearch(nums, 0, breaki, target);
		int y = Arrays.binarySearch(nums, breaki, nums.length, target);

		return x >= 0 || y >= 0;
	}

	public static void main(String args[]) {
		boolean result = new Solution().search(new int[]{1}, 1);
		System.out.printf("result: %s\n", result);
	}
}
