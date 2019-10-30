/**
 * 833	Find And Replace in String 47.8% Medium 208/281
 * Performance: speed=70%, memory=91%
 */

// trick: you can do it from right to left because "we are modifying the the original string S.
// so if we start from left, the index does not match the original index of S"

import java.util.*;

public class Solution {
	public String findReplaceString(String s, int[] indexes, String[] sources, String[] targets) {
		Node[] nodes = new Node[indexes.length];
		for (int i = 0; i < nodes.length; ++i) {
			nodes[i] = new Node(indexes[i], sources[i], targets[i]);
		}
		Arrays.sort(nodes);

		StringBuilder b = new StringBuilder();
		int i = 0, j = 0;
		while (i < s.length()) {
			if (j < nodes.length && i == nodes[j].start) {
				// System.out.printf("i=%d, start=%d, src=%s ==> ", i, nodes[j].start, nodes[j].src);
				if (match(s, nodes[j].start, nodes[j].src)) {
					// System.out.print("ok\n");
					b.append(nodes[j].repl);
					i += nodes[j].src.length();
				} else {
					// System.out.print("failed\n");
					b.append(s.charAt(i));
					++i;
				}
				++j;
			} else {
				// System.out.printf("i=%d, append: %c\n", i, s.charAt(i));
				b.append(s.charAt(i));
				++i;
			}
		}

		return b.toString();
	}

	boolean match(String s, int start, String t) {
		for (int i = 0; i < t.length(); ++i) {
			if (s.charAt(start+i) != t.charAt(i)) return false;
		}
		return true;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.findReplaceString(
			"",
			new int[]{0, 2},
			new String[]{"a", "cd"},
			new String[]{"eee", "ffff"}).equals("");

		assert s.findReplaceString(
			"abcd",
			new int[]{},
			new String[]{},
			new String[]{}).equals("abcd");

		assert s.findReplaceString(
			"abcd",
			new int[]{0, 2},
			new String[]{"a", "cd"},
			new String[]{"eee", "ffff"}).equals("eeebffff");
		assert s.findReplaceString(
			"abcd",
			new int[]{0, 2},
			new String[]{"ab", "ec"},
			new String[]{"eee", "ffff"}).equals("eeecd");
	
		System.out.println("done");
	}
}

class Node implements Comparable<Node> {
	int start;
	String src, repl;

	Node(int start, String src, String repl) {
		this.start = start;
		this.src = src;
		this.repl = repl;
	}

	@Override
	public int compareTo(Node that) {
		return this.start - that.start;
	}
}
