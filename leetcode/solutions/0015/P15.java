/*
 * 3Sum
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class P15 {
	public List<List<Integer>> threeSum(int[] nums) {
		if (nums.length == 0) return new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		int left = nums[0];
		int right = nums[nums.length-1];
		int[] count = new int[right-left+1];
		Arrays.fill(count, 0);
		for (int n : nums) ++count[n-left];
		Set<List<Integer>> res = new HashSet<List<Integer>>();
		for (int i = 0; i < count.length; ++i) {
			if (count[i] == 0) continue;
			int j = (count[i] == 1 ? i+1 : i);
			for (; j < nums.length; ++j) {
				if (count[j] == 1) continue;
				int k = 0 - i - j - 3 * left;
				int r = ((k==i || k==j) ? 2 : 1);
				if (k >= 0 && k < count.length && count[k] >= r) {
					Integer[] array = {i+left, j+left, k+left};
					Arrays.sort(array);
					List<Integer> tuple = Arrays.asList(array);
					res.add(tuple);
				}
			}
		}
		List<List<Integer>> ans = new ArrayList<List<Integer>>(res.size());
		Iterator<List<Integer>> iterator = res.iterator();
		while (iterator.hasNext()) ans.add(iterator.next());
		if (left <= 0 && count[-1 * left] < 3) ans.remove(Arrays.asList(0, 0, 0));
		return ans;
	}

	public static void main(String args[]) {
		P15 solution = new P15();
		int[] nums = {-4,-2,-2,-2,0,0,0,1,2,2,2,3,3,4,4,6,6};
		List<List<Integer>> ans = solution.threeSum(nums);
		for (int i = 0; i < ans.size(); ++i)
			printthree(ans.get(i));
	}

	private static void printthree(List<Integer> list) {
			System.out.printf("[%2d, %2d, %2d]\n", list.get(0), list.get(1), list.get(2));
	}
		
}
