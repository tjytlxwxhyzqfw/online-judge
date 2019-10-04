/**
 * 752 Open the Lock 47.5% Medium 483/26
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	static char[] ds = new char[]{(char)-1, (char)1};

	public int openLock(String[] deadends, String target) {
		Set<String> deadendSet = new HashSet<>(), visited = new HashSet<>();
		for (String s : deadends) deadendSet.add(s);
		visited.addAll(deadendSet);
		int d = dfs("0", deadendSet, visited, target);
		return d == Integer.MAX_VALUE ? -1 : d;
	}

	int dfs(String wheel, Set<String> deadendSet, Set<String> visited, String target) {
		// System.out.printf("dfs: wheel: %s\n", wheel);
		if (deadendSet.contains(wheel)) return Integer.MAX_VALUE;
		if (wheel.equals(target)) return 0;
		visited.add(wheel);
		StringBuilder b = new StringBuilder(wheel);

		int d = Integer.MAX_VALUE;
		for (int i = 0; i < b.length(); ++i) {
			for (int j = 0; j < ds.length; ++j) {
				char next = (char)(wheel.charAt(i) + ds[j]);
				if (next > '9' || next < '0') continue;
				b.setCharAt(i, next);
				if (!visited.contains(b.toString())) {
					int d1 = dfs(b.toString(), deadendSet, visited, target);
					d = Math.min(d, d1);
				}
			}
			b.setCharAt(i, wheel.charAt(i));
		}

		return d < Integer.MAX_VALUE ? ++d : d;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println(s.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"));
		// assert s.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202") == 6;
		assert s.openLock(new String[]{}, "0202") == 4;
		System.out.println("done");
	}
}

