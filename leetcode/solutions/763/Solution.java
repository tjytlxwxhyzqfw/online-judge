/**
 * 763 Partition Labels 72.3% Medium 1283/68
 * Performance: speed=36%, memory=96%
 */

/*
 * todo: there is a better solution: use lastest apperance position of each char.
 * to achieve this, you should replace cnt[] with last[] and remove set.
 */

import java.util.*;

public class Solution {
	public List<Integer> partitionLabels(String s) {
		int[] cnt = new int[26];
		for (int i = 0; i < s.length(); ++i) ++cnt[s.charAt(i)-97];

		Set<Integer> set = new HashSet<>();
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < s.length(); ++i) {
			int idx = s.charAt(i) - 97;
			set.add(idx);
			if (--cnt[idx] == 0) {
				set.remove(idx);
				if (set.size() == 0) result.add(i+1);
			}
		}
		for (int i = result.size()-1; i >= 1; --i) result.set(i, result.get(i)-result.get(i-1));
		return result;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

