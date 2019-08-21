/**
 * 306 Additive Number
 * Performance: speed=15%, memory=71%
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class Solution {
	public boolean isAdditiveNumber(String s) {
		for (int i = 2; i < s.length(); ++i) {
			int maxJ = (s.charAt(0) == '0' ? 2 : i);
			for (int j = 1; j < maxJ; ++j) {
				if (s.charAt(j) == '0' && i-j != 1) continue;
				if (Math.max(j, i-j) > s.length()-i) continue;
				BigInteger x = new BigInteger(s.substring(0, j)), y = new BigInteger(s.substring(j, i));
				String suf = s.substring(i);
				StringBuilder res = new StringBuilder();
				System.out.printf("x=%s, y=%s\n", x, y);
				while (true) {
					BigInteger z = x.add(y);
					res.append(z.toString());
					System.out.printf("\t%s\n", res.toString());
					if (res.length() >= suf.length()) break;
					{ x = y; y = z; }
				}
				if (suf.equals(res.toString())) return true;
			}
		}
		return false;
	}

	public static void main(String args[]) {
		boolean r1 = new Solution().isAdditiveNumber("0099100199");
		System.out.printf("r1: %s\n", r1);
	}
}

