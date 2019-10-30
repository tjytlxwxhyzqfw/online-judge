/**
 * 842 Split Array into Fibonacci Sequence 35.3% Medium 313/107
 * Performance: speed=%, memory=%
 */

// there is a backtracking solution and my solution is kind of too heavy for this problem.
// yes, the backtracking solution is better than mine;

import java.util.*;

public class Solution {
	public List<Integer> splitIntoFibonacci(String s) {
		if (s == null || s.length() == 0) return new ArrayList<>();

		for (int i = 1; i < s.length(); ++i) {
			for (int j = i+1; j < s.length(); ++j) {
				List<Integer> list = isFib(s, i, j);
				if (list != null) return list;
			}
		}

		return new ArrayList<>();
	}

	boolean valid(String s) {
		if (s.length() == 0 || s.length() > 10) return false;
		if (s.length() > 1 && s.charAt(0) == 48) return false;
		return true;
	}

	List<Integer> isFib(String s, int p, int q) {
		String pStr = s.substring(0, p), qStr = s.substring(p, q);
		if (!valid(pStr) || !valid(qStr)) return null;

		long first = Long.parseLong(pStr), second = Long.parseLong(qStr);
		if (first > Integer.MAX_VALUE || second > Integer.MAX_VALUE) return null;

		List<Integer> list = new ArrayList<>();
		list.add((int)first);
		list.add((int)second);
		
		StringBuilder b = new StringBuilder(s.substring(0, q));
		while (b.length() < s.length()) {
			long third = first + second;
			if (third > Integer.MAX_VALUE) return null;
			list.add((int)third);
			b.append(third);
			first = second;
			second = third;
		}

		if (!b.toString().equals(s)) {
			return null;
		}
		return list;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		System.out.println(s.splitIntoFibonacci("123456579"));
		System.out.println(s.splitIntoFibonacci("11235813"));
		System.out.println(s.splitIntoFibonacci("112358130"));
		System.out.println(s.splitIntoFibonacci("0123"));
		System.out.println(s.splitIntoFibonacci("1101111"));
		System.out.println(s.splitIntoFibonacci("214748364721474836422147483641"));

		System.out.println("done");
	}
}

