import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Solution {

	public List<List<Integer>> combine(int n, int k) {
		if (k+k <= n)
			return do_combine(n, k);

		Set<Integer> all = new TreeSet<Integer>();
		for (int i = 1; i <= n; ++i)
			all.add(i);

		if (k == n) {
			List<List<Integer>> list = new ArrayList<List<Integer>>();
			list.add(new ArrayList<Integer>(all));
			return list;
		}

		k = n - k;
		List<List<Integer>> result1 = do_combine(n, k);
		List<List<Integer>> result2 = new ArrayList<List<Integer>>();
		for (List<Integer> x : result1) {
			Set<Integer> y = new TreeSet<Integer>(all);
			y.removeAll(x);
			result2.add(new ArrayList<Integer>(y));
		}

		return result2;
	}

	private List<List<Integer>> do_combine(int n, int k) {
		if (k == 0) {
			return new ArrayList<List<Integer>>();
		} else if (k == 1) {
			List<List<Integer>> result = new ArrayList<List<Integer>>(n);
			for (int i = 1; i <= n; ++i) {
				List<Integer> li = new ArrayList<Integer>(1);
				li.add(i);
				result.add(li);
			}
			return result;
		}

		List<List<Integer>> list = combine(n, k-1);

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int i = 0; i < list.size(); ++i) {
			List<Integer> outer = list.get(i);
			for (int j = 1; j <= n; ++j) {
				if (outer.get(k-2) < j) {
					List<Integer> li = new ArrayList<Integer>(k);
					li.addAll(outer);
					li.add(j);
					result.add(li);
				}
			}
		}

		System.out.printf("n=%d, k=%d, total=%d\n", n, k, result.size());
		//for (List<Integer> x : result)
			//System.out.printf("\t%s\n", x.toString());

		return result;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		new Solution().combine(n, k);
	}
}
