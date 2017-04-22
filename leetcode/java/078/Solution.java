import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {

	public List<List<Integer>> subsets(int nums[]) {
		if (nums == null)
			return null;

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		result.add(new ArrayList<Integer>());

		if (nums.length == 0) {
			//System.out.printf("%s\n", result.toString());
			return result;
		}

		List<List<List<Integer>>> subsetks = new ArrayList<List<List<Integer>>>();
		for (int i = 0; i < nums.length; ++i)
			subsetks.add(new ArrayList<List<Integer>>());

		for (int i = 1; i < nums.length; ++i) {
			int j = nums.length - i;
			if (subsetks.get(j).size() != 0)
				genSupSets(subsetks.get(j), nums, subsetks.get(i));
			else
				genNextSets(subsetks.get(i-1), nums, subsetks.get(i));
		}


		for (List<List<Integer>> listList : subsetks) {
			for (List<Integer> list : listList)
				result.add(list);
		}

		List<Integer> all = new ArrayList<Integer>();
		for (Integer integer : nums)
			all.add(integer);
		result.add(all);

		//for (List<Integer> list : result)
			//System.out.printf("%s\n", list.toString());

		return result;
	}

	private void genNextSets(List<List<Integer>> current, int nums[], List<List<Integer>> next) {
		if (current == null || current.size() == 0) {
			for (int i = 0; i < nums.length; ++i) {
				List<Integer> list = new ArrayList<Integer>();
				list.add(nums[i]);
				next.add(list);
			}
		}

		for (List<Integer> list : current) {
			for (Integer integer : nums) {
				if (integer > list.get(list.size()-1)) {
					List<Integer> listInner = new ArrayList<Integer>();
					listInner.addAll(list);
					listInner.add(integer);
					next.add(listInner);
				}
			}
		}
	}

	private void genSupSets(List<List<Integer>> current, int nums[], List<List<Integer>> next) {
		for (List<Integer> list : current) {
			Set<Integer> set = new TreeSet<Integer>(list);
			List<Integer> listSup = new ArrayList<Integer>();
			for (Integer integer : nums) {
				if (!set.contains(integer))
					listSup.add(integer);
			}
			next.add(listSup);
		}
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int nnums = scanner.nextInt();
		int nums[] = new int[nnums];
		for (int i = 0; i < nnums; ++i)
			nums[i] = i+1;
		new Solution().subsets(nums);
	}
}
