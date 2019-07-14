import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

// dfs(i, 0/1, cursum, curpath)
//

public class Solution {

	public List<List<Integer>> ans = new ArrayList<List<Integer>>();

	private int candidates[];
	private int target;

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		this.candidates = candidates;
		this.target = target;

		if (candidates != null && candidates.length != 0) {
			dfs(0, true, 0, new ArrayList<Integer>());
			dfs(0, false, 0, new ArrayList<Integer>());
		}

		Set<List<Integer>> set = new HashSet<List<Integer>>(ans);

		ans.clear();
		ans.addAll(set);

		return ans;
	}

	private void dfs(int i, boolean use, int sum, List<Integer> path) {
		List<Integer> curpath = new ArrayList<Integer>(path);
		int cursum = sum;

		if (use) {
			cursum += candidates[i];
			curpath.add(candidates[i]);
		}

		if (cursum == target) {
			ArrayList<Integer> arr = new ArrayList<Integer>(curpath);
			arr.sort(Comparator.naturalOrder());
			ans.add(arr);
			return;
		} else if (cursum > target) {
			return;
		}

		if (i+1 < candidates.length) {
			dfs(i+1, true, cursum, curpath);
			dfs(i+1, false, cursum, curpath);
		}
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int nnums = scanner.nextInt();
		int target = scanner.nextInt();
		int candidates[] = new int[nnums];
		for (int i = 0; i < nnums; ++i)
			candidates[i] = scanner.nextInt();
		List<List<Integer>> ans = new Solution().combinationSum2(candidates, target);
		for (List<Integer> list : ans)
			System.out.printf("%s\n", list.toString());
	}
}
