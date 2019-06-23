import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//就地标记

public class Solution {

	public int firstMissingPositive(int[] nums) {
		if (nums == null || nums.length == 0)
			return 1;
		for (int i = 0; i < nums.length; ++i)
			if (nums[i] <= 0)
				nums[i] = nums.length+2;

		for (int i = 0; i < nums.length; ++i) {
			int num = Math.abs(nums[i]);
			if (1 <= num && num <= nums.length) {
				int idx = num - 1;
				nums[idx] = -1 * Math.abs(nums[idx]);
			}
		}

		int i;
		for (i = 0; i < nums.length; ++i)
			if (nums[i] > 0)
				break;
		return i+1;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int nnums = scanner.nextInt();
		int nums[] = new int[nnums];
		for (int i = 0; i < nnums; ++i)
			nums[i] = scanner.nextInt();
		int first = new Solution().firstMissingPositive(nums);
		System.out.printf("---> %d\n", first);
	}
}
