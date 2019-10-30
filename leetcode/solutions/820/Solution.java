/**
 * 820 Short Encoding of Words 48.4% Medium 181/48
 * Performance: speed=92%, memory=50%
 */

import java.util.*;

public class Solution {
	public int minimumLengthEncoding(String[] words) {
		Trie t = new Trie();
		for (String w : words) t.insert(w);
		int[] count = new int[1];
		dfs(t.root, 0, count);
		if (count[0] == 1) count[0] = 0;
		return count[0];
	}
	
	void dfs(Node root, int deepth, int[] count) {
		boolean isLeaf = true;
		for (int i = 0; i < root.ways.length; ++i) {
			if (root.ways[i] != null) {
				// System.out.printf("dfs goes to %c (d=%d)\n", (char)(i+97), deepth);
				isLeaf = false;
				dfs(root.ways[i], 1+deepth, count);
			}
		}
		if (isLeaf) count[0] += deepth+1;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.minimumLengthEncoding(new String[]{}) == 0;
		assert s.minimumLengthEncoding(new String[]{"a"}) == 2;
		assert s.minimumLengthEncoding(new String[]{"time", "tim", "ti", "t"}) == 14;
		assert s.minimumLengthEncoding(new String[]{"time", "ime", "me", "e"}) == 5;
		assert s.minimumLengthEncoding(new String[]{"time", "bell"}) == 10;

		System.out.println("done");
	}
}

class Node {
	Node[] ways;
	Node() { ways = new Node[26]; }
}

class Trie {
	Node root;

	Trie() { root = new Node(); }

	void insert(String s) {
		Node p = root;
		for (int i = s.length()-1; i >= 0; --i) {
			// p is root, i is next char to be inserted
			char idx = (char)(s.charAt(i) - 97);
			if (p.ways[idx] == null) p.ways[idx] = new Node();
			p = p.ways[idx];
		}
	}
}

