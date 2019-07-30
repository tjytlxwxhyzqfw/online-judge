/**
 * 721: AccountsMerge
 * Performance: speed=16%, memory=5%
 *
 * todo: is all union find problem can be solved (unefficiently) by dfs ?
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		return accountsMerge_dfs(accounts);
	}

	public List<List<String>> accountsMerge_dfs(List<List<String>> accounts) {
		Map<String, Set<String>> g = new HashMap<>();
		Map<String, String> toName = new HashMap<>();
		for (List<String> list : accounts) {
			int i = 0;
			String name = list.get(0);
			List<String> emails = list.subList(1, list.size());
			for (String s : emails) {
				if (g.get(s) == null) g.put(s, new HashSet<>());
				g.get(s).addAll(emails);
				toName.put(s, name);
				System.out.printf("%s -> %s\n", s, name);
			}
		}
		List<Set<String>> result = new ArrayList<>();
		Set<String> v = new HashSet<>();
		for (Map.Entry<String, Set<String>> entry : g.entrySet()) {
			if (!v.contains(entry.getKey())) {
				Set<String> emails = new TreeSet<>();
				dfs(entry.getKey(), emails, v, g);
				System.out.printf("#emails: %d\n", emails.size());
				result.add(emails);
			}
		}
		System.out.printf("#result: %d\n", result.size());

		List<List<String>> result1 = new ArrayList<>();
		for (Set<String> s : result) {
			List<String> list = new ArrayList<>();
			for (String e : s) { list.add(toName.get(e)); break; }  // fool: toName.get(s)
			list.addAll(s);
			result1.add(list);  // fool: forget this statement !!!!
		}
		return result1;
	}

	void dfs(String s, Set<String> emails, Set<String> v, Map<String, Set<String>> g) {
		// s is not visited
		// System.out.printf("dfs: %s\n", s);
		v.add(s);
		emails.add(s);
		Set<String> neibs = g.get(s);
		if (neibs == null) return;
		for (String n : neibs) if (!v.contains(n)) dfs(n, emails, v, g);
	}

	// todo: union find solution
	public List<List<String>> accountsMerge_unionFind(List<List<String>> accounts) {
		return null;
	}

	public static void main(String args[]) {
	}
}

