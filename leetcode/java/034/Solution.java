import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public int[] searchRange(int[] nums, int target) {
		int range[] = {-1, -1};

		if (nums == null || nums.length == 0)
			return range;
		
		double left = target - .5;
		int lefti = binarySearch(nums, left);
		double rght = target + .5;
		int rghti = binarySearch(nums, rght);


		if (lefti < nums.length && nums[lefti] == target) {
			// the first 'target' appears at 'nums[lefti]'
			// the first element greater than 'target' appears at 'nums[rghti]'
			range[0] = lefti;
			range[1] = rghti-1;
		}

		return range;
	}

	private int binarySearch(int nums[], double target) {
		int i, j, k;
		int idx;

		i = 0;
		j = nums.length-1;
		idx = nums.length;
		while (i <= j) {
			k = (i+j) / 2;
			if (nums[k] < target) {
				i = k + 1;
			} else if (nums[k] > target) {
				j = k - 1;
				idx = k;
			} else {
				throw new RuntimeException();
			}
		}

		return idx;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int length = scanner.nextInt();
		int target = scanner.nextInt();
		int nums[] = new int[length];
		for (int i = 0; i < length; ++i)
			nums[i] = scanner.nextInt();
		int range[] = new Solution().searchRange(nums, target);
		System.out.printf("-------> [%3d, %3d]\n", range[0], range[1]);
	}
}
