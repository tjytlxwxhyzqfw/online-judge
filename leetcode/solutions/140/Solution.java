/**
 * 140 Word Break II
 * pass=30 ud=1582/339 s=16 m=9
 */

import java.util.*;

public class Solution {
	public List<String> wordBreak(String s, List<String> ws) {
		Map<String, Integer> words = new HashMap<>();
		for (int i = 0; i < ws.size(); ++i) words.put(ws.get(i), i);
		List<List<String>> cache = new ArrayList<>(s.length()+1);
		for (int i = 0; i <= s.length(); ++i) cache.add(null);
		work(0, s, words, cache);

		/*
		if (cache.get(0) != null) {
			for (int k = 0; k < cache.get(0).size(); ++k) 
				System.out.printf("%s\n", cache.get(0).get(k));
		}
		*/

		// System.out.printf("result.size(): %d\n", cache.get(0).size());
		return cache.get(0);
	}

	void work(int i, String s, Map<String, Integer> words, List<List<String>> cache) {
		if (cache.get(i) != null) return;
		// System.out.printf("work: i=%2d, s=%s\n", i, s);
		if (i == s.length()) {
			cache.set(i, Arrays.asList(""));
			return;
		}

		cache.set(i, new ArrayList<>());
		int total = 0;
		for (int j = i+1; j <= s.length(); ++j) {
			if (words.get(s.substring(i, j)) == null) continue;
			work(j, s, words, cache);
			// System.out.printf("i=%d, j=%d, cache.get(j).size()=%d\n", i, j, cache.get(j).size());
			for (int k = 0; k < cache.get(j).size(); ++k) {
				String suf = cache.get(j).get(k);
				cache.get(i).add(s.substring(i, j) + (suf.length() == 0 ? "" : " ") + suf);
			}
		}
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		s.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));

		s.wordBreak("a", Arrays.asList("a"));
		s.wordBreak("a", Arrays.asList("b"));
		s.wordBreak("a", Arrays.asList("a", "b", "c"));
		s.wordBreak("aaaaa", Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa"));
		// System.out.printf("last one ...\n");
		s.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"));
		System.out.println("done");
	}
}

