/**
 * 208
 * 27%, 54%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Trie {
	private Trie.Node root;
	/** Initialize your data structure here. */
	public Trie() {
		root = new Trie.Node();
	}
	
	/** Inserts a word into the trie. */
	public void insert(String word) {
		Trie.Node current = root;
		for (int i = 0; i < word.length(); ++i) {
			char ch = word.charAt(i);
			if (!current.branches.containsKey(ch)) {
				current.branches.put(ch, new Trie.Node());
			}
			current = current.branches.get(ch);
		}
		current.end = true;
	}

	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		Trie.Node node = find(word);
		if (node == null || !node.end) {
			return false;
		}
		return true;
	}
	
	/** Returns if there is any word in the trie that starts with the given prefix. */
	public boolean startsWith(String prefix) {
		return find(prefix) != null;
	}

	private Trie.Node find(String word) {
		Trie.Node current = root;
		for (int i = 0; i < word.length(); ++i) {
			char ch = word.charAt(i);
			System.out.printf("\ti=%d, ch=%s, current.end=%s\n", i, ch, current.end);
			if (!current.branches.containsKey(ch)) {
				return null;
			}
			current = current.branches.get(ch);
		}
		return current;
	}

	public static void main(String args[]) {
		Trie trie = new Trie();

		Scanner in = new Scanner(System.in);
		while (true) {
			String line = in.nextLine();
			String[] cmd = line.split(" ");
			System.out.printf("cmd[0]: %s\n", cmd[0]);
			if (cmd[0].equals("i")) {
				System.out.printf("insert: %s\n", cmd[1]);
				trie.insert(cmd[1]);
			} else if (cmd[0].equals("s")) {
				boolean r = trie.search(cmd[1]);
				System.out.printf("search: %s => %s\n", cmd[1], r);
			} else if (cmd[0].equals("p")) {
				boolean r = trie.startsWith(cmd[1]);
				System.out.printf("prefix: %s => %s\n", cmd[1], r);
			} else {
				System.out.printf("ignored: %s\n", line);
			}
		}
	}

	static class Node {
		boolean end;
		Map<Character, Trie.Node> branches;

		public Node() {
			branches = new HashMap<>();
		}
	}
}

