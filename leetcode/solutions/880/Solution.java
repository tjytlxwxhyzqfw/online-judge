/**
 * 880 Decoded String at Index 23.6% Medium 316/65
 * Performance: speed=%, memory=%
 *
 * todo: recursion is not necessary
 */

// xxx6y6z66 k = 900
// 3*6*6*6
// (((1+1+1)*6+1)*6+1)*6*6
// xxx6y6z66
// ..........
// (100)
// 27
//
// (n)a => k
// if #a == k return a
// 
// (m)n, k (n*m >= k) ===> (m), k % m
// (n)x, k => (n)k
// (n)m, k => (n), k % n
// (m)n => m
//
// explains the above inference
//
// (m) => a string with length m
// (m)n => (m) with a tail digit n, (m) repeats n times
// (m)x => (m) with a tail char x

import java.util.*;

public class Solution {
	public String decodeAtIndex(String s, int k) {
		long n = 0;
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) n *= (c-48);
			else ++n;
		}
		return "" + decode(s, n, k-1);
	}

	char decode(String s, long n, int k) {
		char last = s.charAt(s.length()-1);
		if (Character.isDigit(last)) {
			long m = n / (last-48);
			String t = s.substring(0, s.length()-1);
			return decode(t, m, (int)((long)k % m)); 
		} else {
			if (k == n-1) return last;
			String t = s.substring(0, s.length()-1);
			return decode(t, n-1, k);
		}
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.decodeAtIndex("a", 1).equals("a");
		assert s.decodeAtIndex("a2", 2).equals("a");
		assert s.decodeAtIndex("ab2", 4).equals("b");
		assert s.decodeAtIndex("a8888888888", 169763).equals("a");

		assert s.decodeAtIndex("leet2code3", 10).equals("o");
		assert s.decodeAtIndex("ha22", 5).equals("h");
		assert s.decodeAtIndex("a2345678999999999999999", 1).equals("a");
		assert s.decodeAtIndex("a2345678999999999999999", 13).equals("a");

		System.out.println("done");
	}
}

