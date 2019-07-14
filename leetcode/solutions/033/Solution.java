import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0)
			return -1;

		int brk;
		for (brk = 1; brk < nums.length; ++brk)
			if (nums[brk] < nums[brk-1])
				break;

		//System.out.printf("brk: %d\n", brk);
		
		int x = Arrays.binarySearch(nums, 0, brk, target);
		int y = Arrays.binarySearch(nums, brk, nums.length, target);

		if (x >= 0)
			return x;
		else if (y >= 0)
			return y;
		else
			return -1;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int target = scanner.nextInt();
		int length = scanner.nextInt();
		int nums[] = new int[length];
		for (int i = 0; i < length; ++i)
			nums[i] = scanner.nextInt();
		int idx = new Solution().search(nums, target);
		System.out.printf("-------> %d\n", idx);
	}
}
