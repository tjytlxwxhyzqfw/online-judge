import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// i: [i:], used j dups of a[i], transform to i+1: choose from (i+1)th number, sum is k
// (i, j, k) -> (i+1, x, k+j*a[i])
//
// I dont need numbers, but i need all combinations!!!
//
// use dfs(i)
//

public class Solution {

	public List<List<Integer>> ans = new ArrayList<List<Integer>>();

	private int candidates[];
	private int target;

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		this.candidates = candidates;
		this.target = target;

		if (candidates != null && candidates.length != 0)
			dfs(0, 0, new ArrayList<Integer>());
		return ans;
	}

	private void dfs(int i, int sum, List<Integer> path) {
		int cursum = sum;
		List<Integer> curpath = new ArrayList<Integer>(path);

		for (int j = 0;; ++j) {
			//System.out.printf("%3dx%-3d: cursum: %3d, curpath: %s\n", candidates[i], j, cursum, curpath.toString());
			if (cursum < target) {
				if (i < candidates.length-1)
					dfs(i+1, cursum, curpath);
			} else if (cursum == target) {
				ans.add(new ArrayList<Integer>(curpath));
				break;
			} else {
				break;
			}
			cursum += candidates[i];
			curpath.add(candidates[i]);
		}
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int nnums = scanner.nextInt();
		int target = scanner.nextInt();

		int candidates[] = new int[nnums];
		for (int i = 0; i < nnums; ++i)
			candidates[i] = scanner.nextInt();

		List<List<Integer>> ans = new Solution().combinationSum(candidates, target);
		for (List<Integer> list : ans)
			System.out.printf("%s\n", list.toString());
	}
}
