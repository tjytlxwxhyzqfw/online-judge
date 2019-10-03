/**
 * 676 Implement Magic Dictionary 52.3% Medium 411/105
 * Performance: speed=5%, memory=5%
 */

import java.util.*;

class MagicDictionary {

	Trie trie;
	/** Initialize your data structure here. */
	public MagicDictionary() { trie = new Trie(); }
	
	/** Build a dictionary through a list of words */
	public void buildDict(String[] dict) {
		for (String s : dict) {
			for (String t : cands(s)) trie.insert(t);
		}
	}
	
	/** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
	public boolean search(String word) { return trie.contains(word); }

	String[] cands(String s) {
		String[] a = new String[25 * s.length()];
		int p = 0;
		StringBuilder b = new StringBuilder(s);
		for (int i = 0; i < s.length(); ++i) {
			int idx = s.charAt(i) - 97;
			for (int j = 1; j < 26; ++j) {
				b.setCharAt(i, (char)(97 + (idx+j) % 26));
				a[p++] = b.toString();
				b.setCharAt(i, s.charAt(i));
			}
		}
		return a;
	}

	public static void main(String args[]) {
		MagicDictionary d = new MagicDictionary();
		d.buildDict(new String[]{"aaaa"});
		System.out.println("done");
	}
}

class Trie {
	Node root;

	Trie() {
		root = new Node();
	}

	void insert(String s) {
		// System.out.printf("insert: %s\n", s);
		Node p = root;
		for (int i = 0; i < s.length(); ++i) {
			int idx = s.charAt(i) - 97;
			if (p.ways[idx] == null) p.ways[idx] = new Node();
			p = p.ways[idx];
		}
		p.end = true;
	}

	boolean contains(String s) {
		Node p = root;
		for (int i = 0; i < s.length(); ++i) {
			int idx = s.charAt(i) - 97;
			if (p.ways[idx] == null) return false;
			p = p.ways[idx];
		}
		return p.end;
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
