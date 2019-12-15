/**
 * 866 Prime Palindrome 20.5% Medium 119/339
 * Performance: speed=14%, memory=50%
 */

/*
    this solution makes me look like a fool.
    ----------------------------------------

    public int primePalindrome(int N) {
        if (8 <= N && N <= 11) return 11;
        for (int x = 1; x < 100000; x++) {
            String s = Integer.toString(x), r = new StringBuilder(s).reverse().toString();
            int y = Integer.parseInt(s + r.substring(1));
            if (y >= N && isPrime(y)) return y;
        }
        return -1;
    }

    public Boolean isPrime(int x) {
        if (x < 2 || x % 2 == 0) return x == 2;
        for (int i = 3; i * i <= x; i += 2)
            if (x % i == 0) return false;
        return true;
    }
*/

import java.util.*;

public class Solution {
	static int[] ps;
	static int len;

	public int primePalindrome(int n) {
		if (ps == null) {
			ps = new int[6000]; // there are at most 6000 palidrome prime numbers
			len = palindromePrimes(ps);
			Arrays.sort(ps, 0, len);
		}
		// for (int i = 0; i < 10; ++i) System.out.println(ps[i]);
		for (int i = 0; i < len; ++i) { 
			if (ps[i] >= n) return ps[i];
		}
		return 0;
	}

	int palindromePrimes(int[] ps) {
		int n = 0;
		for (int i = 1; i < 1e4; ++i) {
			String s = "" + i;
			if (s.charAt(0) % 2 == 0) {
				continue;
			}
			List<Long> longs = palindromeNums(s);
			for (long j : longs)
				if (j <= Integer.MAX_VALUE && isPrime((int)j)) ps[n++] = (int)j;
		}
		ps[n++] = 2;
		ps[n++] = 3;
		ps[n++] = 5;
		ps[n++] = 7;
		return n;
	}	

	// attention: this kinda generating algo introduces lots lots lots of duplicate elements !!!
	// use the following one !!!!
	// 
	// String s = Integer.toString(x), r = new StringBuilder(s).reverse().toString();
        // int y = Integer.parseInt(s + r.substring(1));
	List<Long> palindromeNums(String s) {
		String r = new StringBuilder(s).reverse().toString();
		List<Long> list = new ArrayList<>();
		tryAdd(Long.parseLong(s+r), list);
		for (int i = 0; i < 10; ++i) tryAdd(Long.parseLong(s + i + r), list);
		return list;
	}

	void tryAdd(long x, List<Long> list) {
		if (x % 2 == 0) return;
		if (x % 10 == 5) return;
		if (x % 10 == 0) return;
		list.add(x);
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
		assert s.primePalindrome(250893) == 1003001;

		System.out.println("done");
	}
}

