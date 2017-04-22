import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public int searchInsert(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return 0;
		int idx = Arrays.binarySearch(nums, 0, nums.length, target);
		if (idx < 0)
			idx = -1 * idx - 1;
		return idx;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int length = scanner.nextInt();
		int target = scanner.nextInt();
		int nums[] = new int[length];
		for (int i = 0; i < length; ++i)
			nums[i] = scanner.nextInt();
		int idx = new Solution().searchInsert(nums, target);
		System.out.printf("---> %d\n", idx);
	}
}
