/**
 * 648 Replace Words 53.3% Medium 494/113
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public String replaceWords(List<String> dict, String s) {
		Trie trie = new Trie();
		for (String root : dict) trie.insert(root);

		StringBuilder b = new StringBuilder();
		int begin = 0;
		for (int end = 1; end <= s.length(); ++end) {
			if (end == s.length() || s.charAt(end) == ' ') {
				int prevEndAt = trie.prefixOf(s, begin, end);
				System.out.printf("raw=%s, prefix=%s\n",
					s.substring(begin, end), prevEndAt == -1 ? null : s.substring(begin, prevEndAt));
				if (prevEndAt == -1) prevEndAt = end;
				b.append(s.substring(begin, prevEndAt));
				if (end != s.length()) b.append(' ');

				begin = end + 1;
			}
		}

		return b.toString();
	}

	public static void main(String args[]) {
		Solution s = new Solution();
	
		String s1 = s.replaceWords(Arrays.asList("c"), "the cattle was rattled by the battery");
		System.out.println(s1);

                System.out.println("done");
	}
}

class Node {
	boolean end;
	Node[] ways;

	Node() {
		end = false;
		ways = new Node[26];
	}
}

class Trie {
	Node root;

	Trie() { root = new Node(); }

	void insert(String s) {
		Node p = root;
		for (int i = 0; i < s.length(); ++i) {
			// p is root, i is next char to be inserted
			char idx = (char)(s.charAt(i) - 97);
			if (p.ways[idx] == null) p.ways[idx] = new Node();
			p = p.ways[idx];

			// disscuss: One optimization is when building the trie,
			// if a shorter root is already found, we can stop adding it to the trie.
			// e.g. "a", "aa", "aaa", if "a" is already in the trie,
			// we don't need to go beyond first character of "aa" and "aaa".
			if (p.end) return;
		}
		p.end = true;
	}

	int prefixOf(String s, int begin, int end) {
		Node p = root;
		if (p.end) return begin;
		for (int i = begin; i < end; ++i) {
			// p is root, i is next char to be looked for
			char idx = (char)(s.charAt(i)-97);
			if (p.ways[idx] == null) return -1;
			if ((p = p.ways[idx]).end) return ++i;
		}
		return -1;
	}
}
