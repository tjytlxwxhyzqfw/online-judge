/**
 * 677  Map Sum Pairs 52.3% Medium 340/64
 * Performance: speed=50%, memory=100%
 */

import java.util.*;

class MapSum {

	Trie trie;

	/** Initialize your data structure here. */
	public MapSum() { trie = new Trie(); }
	
	public void insert(String key, int val) {
		int oldVal = trie.val(key);
		trie.insert(key, val-oldVal);
	}

	public int sum(String prefix) { return trie.sum(prefix); }

	public static void main(String args[]) {
		MapSum s = new MapSum();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String cmd = scanner.next();
			if (cmd.equals("i")) {
				String key = scanner.next();
				int val = scanner.nextInt();
				s.insert(key, val);
				int newVal = s.trie.val(key);
				int newSum = s.sum(key);
				System.out.printf("key: %s, val: %s, val -> %d, sum -> %d\n", key, val, newVal, newSum);
			} else if (cmd.equals("s")) {
				String key = scanner.next();
				System.out.printf("sum: %s=%d\n", key, s.sum(key));
			} else if (cmd.equals("v")) {
				String key = scanner.next();
				System.out.printf("val: %s=%d\n", key, s.trie.val(key));
			} else {
				System.out.printf("unknown cmd: %s, quit\n", cmd);
				break;
			}
		}
		System.out.println("done");
	}
}

class Trie {
	Node root;

	Trie() {
		root = new Node();
	}

	void insert(String s, int delta) {
		// System.out.printf("insert: %s\n", s);
		Node p = root;
		p.sum += delta;
		for (int i = 0; i < s.length(); ++i) {
			int idx = s.charAt(i);
			if (p.ways[idx] == null) p.ways[idx] = new Node();
			p = p.ways[idx];
			p.sum += delta;
		}
		p.end = true;
		p.val += delta;
	}

	int sum(String s) {
		Node p = root;
		for (int i = 0; i < s.length(); ++i) {
			int idx = s.charAt(i);
			if (p.ways[idx] == null) return 0;
			p = p.ways[idx];
		}
		return p.sum;
	}

	int val(String s) {
		Node p = root;
		for (int i = 0; i < s.length(); ++i) {
			int idx = s.charAt(i);
			if (p.ways[idx] == null) return 0;
			p = p.ways[idx];
		}
		return p.val;
	}
}

class Node {
	boolean end;
	Node[] ways;
	int sum, val;
	Node() {
		end = false;
		ways = new Node[128];
		sum = val = 0;
	}
}

