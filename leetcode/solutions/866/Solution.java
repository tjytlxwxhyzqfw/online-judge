/**
 * 866 Prime Palindrome 20.5% Medium 119/339
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public int primePalindrome(int n) {
		int[] ps = new int[6000]; // there are at most 6000 palidrome prime numbers
		int len = palindromePrimes(ps);
		Arrays.sort(ps, 0, len);
		// for (int i = 0; i < 10; ++i) System.out.println(ps[i]);
		for (int i = 1; i < len; ++i) { // ps[0] is 1 so we start from ps[1]
			if (ps[i] >= n) return ps[i];
		}
		return 0;
	}

	int palindromePrimes(int[] ps) {
		int n = 0;
		for (int i = 1; i < 1e4; ++i) {
			List<Long> longs = palindromeNums(i);
			for (long j : longs)
				if (j <= Integer.MAX_VALUE && isPrime((int)j)) ps[n++] = (int)j;
		}
		return n;
	}	

	List<Long> palindromeNums(int x) {
		String s = "" + x;
		String r = new StringBuilder(s).reverse().toString();
		List<Long> list = new ArrayList<>();
		if (x < 10) list.add((long)x);
		list.add(Long.parseLong(s+r));
		for (int i = 0; i < 10; ++i) list.add(Long.parseLong(s + i + r));
		return list;
	}

	boolean isPrime(int x) {
		for (int i = 2; i*i <= x; ++i) {
			if (x % i == 0) return false;
		} 
		return true;
	}

	

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.primePalindrome(0) == 2;
		assert s.primePalindrome(6) == 7;
		assert s.primePalindrome(8) == 11;
		assert s.primePalindrome(13) == 101;

		System.out.println("done");
	}
}

