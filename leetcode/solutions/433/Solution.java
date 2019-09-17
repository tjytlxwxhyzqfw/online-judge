/**
 * 433 Minimum Genetic Mutation
 * Performance: speed=75%, memory=100%
 */

import java.util.*;

public class Solution {
	private static char[] points = new char[]{'A', 'T', 'C', 'G'};

	public int minMutation(String start, String end, String[] bankL) {
		Set<String> bank = new HashSet<>();
		for (String s : bankL) bank.add(s);
		if (!bank.contains(end)) return -1;

		Queue<Item> q = new LinkedList<>();	
		q.offer(new Item(start, 0));
		while (!q.isEmpty()) {
			Item curr = q.poll();
			if (curr.gene.equals(end)) return curr.deepth;
			StringBuilder b = new StringBuilder(curr.gene);
			for (int i = 0; i < b.length(); ++i) {
				char orig = b.charAt(i);
				for (char p : points) {
					if (p == orig) continue;
					b.setCharAt(i, p);
					if (bank.contains(b.toString())) {
						q.offer(new Item(b.toString(), curr.deepth+1));
						bank.remove(b.toString());
					}
				}
				b.setCharAt(i, orig);
			}
		}
		return -1;
	}

	public static void main(String args[]) {
	}
}

class Item {
	String gene;
	int deepth;
	Item(String g, int d) {
		gene = g;
		deepth = d;
	}
}

