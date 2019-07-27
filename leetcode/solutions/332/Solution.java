/**
 * 332: ReconstructItinerary
 * Performance: speed=27%, memory=63%
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;

public class Solution {
	public List<String> findItinerary(List<List<String>> tickets) {
		Map<String, List<String>> ds = new HashMap<>();
		Map<String, Integer> count = new HashMap<>();
		for (List<String> t : tickets) {
			String from = t.get(0), to = t.get(1);
			if (ds.get(from) == null) ds.put(from, new ArrayList<>());
			ds.get(from).add(to);
			String key = from + to;
			int val = count.getOrDefault(key, 0);
			count.put(key, val+1);
		}

		for (Map.Entry<String, List<String>> entry : ds.entrySet()) {
			entry.getValue().sort(Comparator.naturalOrder());
		}

		return dfs("JFK", ds, count);
	}

	List<String> dfs(String from, Map<String, List<String>> ds, Map<String, Integer> count) {
		List<String> result = new ArrayList<>(), last = new ArrayList<>();
		result.add(from);

		List<String> tos = ds.get(from);
		if (tos == null) {
			return result;
		}
		for (String to : tos) {
			int c = count.getOrDefault(from+to, 0);
			if (c == 0) continue;
			count.put(from+to, c-1);
			List<String> result1 = dfs(to, ds, count);
			if (!result1.get(result1.size()-1).equals(from)) last = result1;
			else result.addAll(result1);
		}
		result.addAll(last);
		return result;
	}

	public static void main(String args[]) {
		List<String> r1 = new Solution().findItinerary(
			Arrays.asList(
				Arrays.asList("JFK", "A"),
				Arrays.asList("A", "B"),
				Arrays.asList("B", "A"),
				Arrays.asList("A", "C"),
				Arrays.asList("C", "JFK")
			));
		for (String s : r1) System.out.printf("%s, ", s);
		System.out.printf("\n");
	}
}

