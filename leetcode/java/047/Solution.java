import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Solution {

	public List<List<Integer>> permuteUnique(int[] nums) {
		if (nums == null || nums.length == 0)
			return new ArrayList<List<Integer>>();
		Set<List<Integer>> permutes = permuteUnique(nums.length-1, nums);
		return new ArrayList<List<Integer>>(permutes);
	}

	public Set<List<Integer>> permuteUnique(int idx, int nums[]) {
		Set<List<Integer>> permutes = new HashSet<List<Integer>>();

		if (idx == 0) {
			List<Integer> unique = new ArrayList<Integer>();
			unique.add(nums[0]);
			permutes.add(unique);
			return permutes;
		}


		Set<List<Integer>> res = permuteUnique(idx-1, nums);
		for (List<Integer> list : res) {
			int length = list.size();
			for (int i = 0; i <= length; ++i) {
				List<Integer> li = new ArrayList<Integer>(list);
				li.add(i, nums[idx]);
				permutes.add(li);
			}
		}
		return permutes;
	}


	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int nnums = scanner.nextInt();
		int nums[] = new int[nnums];
		for (int i = 0; i < nnums; ++i)
			nums[i] = scanner.nextInt();
		List<List<Integer>> permutes = new Solution().permuteUnique(nums);
		for (List<Integer> permute : permutes)
			System.out.printf("%s\n", permute.toString());
	}
}
