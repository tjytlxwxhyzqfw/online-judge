/**
 * 386 Lexicographical Numbers
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public List<Integer> lexicalOrder(int n) {
		List<Integer> list = new ArrayList<>(n);
		for (int i = 1; i < 10; ++i) if (i <= n) search(i, n, list);
		return list;
	}

	void search(int prefix, int n, List<Integer> list) {
		list.add(prefix);
		System.out.printf("%d\n", prefix);
		int p = prefix * 10;
		for (int i = 0; i < 10; ++i) if (p+i <= n) search(p+i, n, list);
	}

	public static void main(String args[]) {
		new Solution().lexicalOrder(1);
	}
}

