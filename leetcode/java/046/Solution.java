import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public List<List<Integer>> permute(int[] nums) {
		if (nums == null || nums.length == 0)
			return new ArrayList<List<Integer>>();
		return permute(nums.length-1, nums);
	}

	private List<List<Integer>> permute(int idx, int nums[]) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();

		if (idx == 0) {
			List<Integer> list = new ArrayList<Integer>();
			list.add(nums[0]);
			res.add(list);
			return res;
		}

		List<List<Integer>> subres = permute(idx-1, nums);

		for (List<Integer> list : subres) {
			int length = list.size();
			for (int i = 0; i <= list.size(); ++i) {
				List<Integer> li = new ArrayList<Integer>(list);
				li.add(i, nums[idx]);
				res.add(li);
			}
		}
		return res;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int nnums = scanner.nextInt();
		int nums[] = new int[nnums];
		for (int i = 0; i < nnums; ++i)
			nums[i] = scanner.nextInt();
		List<List<Integer>> res = new Solution().permute(nums);
		for (List<Integer> list : res)
			System.out.printf("%s\n", list.toString());
		System.out.printf("total: %d\n", res.size());
	}
}
