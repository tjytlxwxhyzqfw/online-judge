/**
 * 126 Word Ladder II
 * pass=20 ud=1537/225 s=7 m=5
 *
 * todo: why is my solution so slow ?
 */

import java.util.*;

public class Solution {
	public List<List<String>> findLadders(String begin, String end, List<String> wordList) {
		List<String> list = new ArrayList<>();
		list.addAll(wordList);
		list.add(begin);

		List<Set<Integer>> from = new ArrayList<>();
		Map<String, Integer> index = new HashMap<>();
		for (int i = 0; i < list.size(); ++i) {
			from.add(new HashSet<>());
			index.put(list.get(i), i);
		}
		List<List<String>> result = new ArrayList<>();
		if (index.get(end) == null) return result;

		// prepare to apply bfs
		Queue<Integer> q = new LinkedList<>();
		q.offer(list.size()-1);
		q.offer(null);
		int depth = 0;
		int[] dist = new int[list.size()];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[list.size()-1] = depth;

		while (!q.isEmpty()) {
			Integer i = q.poll();
			if (i == null) {
				if (q.isEmpty()) break;
				q.offer(null);
				++depth;
				continue;
			}
			String x = list.get(i);
			if (x.equals(end)) {
				break;
			}
			for (String s : cands(x)) {
				Integer si = index.get(s);
				if (si == null || dist[si] < dist[i]+1) continue;
				// so s in the word list and s is first reached
				q.offer(si);
				dist[si] = dist[i] + 1;
				from.get(si).add(i);
				// System.out.printf("%s(%d) -> %s(%d)\n", x, dist[i], s, dist[si]);
			}
		}

		buildPath(begin, index.get(end), from, list, result);

		// System.out.printf("#paths: %d\n", result.size());
		// for (List<String> path : result) printA(path);

		return result;
	}

	private int buildPath(String begin, int end, List<Set<Integer>> from, List<String> list, List<List<String>> result) {
		if (end == list.size()-1) {
			List<String> path = new ArrayList<>();
			path.add(begin);
			result.add(path);
			return 1;
		};
		int total = 0;
		for (int j : from.get(end)) {
			int offset = result.size();
			int npaths = buildPath(begin, j, from, list, result);
			for (int i = 0; i < npaths; ++i) result.get(offset+i).add(list.get(end));
			total += npaths;
		}
		return total;
	}

	private void printA(List<String> list) {
		for (String s : list) System.out.printf("%s, ", s);
		System.out.println();
	}

	private List<String> cands(String s) {
		List<String> list = new ArrayList<>(s.length() * 26);
		StringBuilder b = new StringBuilder(s);
		for (int i = 0; i < s.length(); ++i) {
			char ori = s.charAt(i);
			for (char c = 'a'; c <= 'z'; ++c) {
				b.setCharAt(i, c);
				list.add(b.toString());
			}
			b.setCharAt(i, ori);
		}
		return list;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		s.findLadders("hit", "hot", Arrays.asList("hot"));
		s.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
		s.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log"));
		s.findLadders("hit", "xxx", Arrays.asList("hot", "dot", "dog", "lot", "log", "xxx"));
		s.findLadders("aaa", "xxx", Arrays.asList("aab", "aac", "yab", "yac", "yyb", "yyc", "yyy", "tyy", "tyx", "txx", "xxx"));
		s.findLadders("aaa", "xxx", Arrays.asList("aab", "aac", "yab", "yac", "yay", "xay", "xxy", "xxx"));

		System.out.println("done");
	}
}
