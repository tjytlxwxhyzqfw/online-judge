/**
 * 592 Fraction Addition and Subtraction 47.5% Medium 131/250
 * Performance: speed=74%, memory=100%
 */

import java.util.*;

public class Solution {
	public String fractionAddition(String exp) {
		boolean neg = exp.charAt(0) == '-';
		int i = neg ? 1 : 0;
		long num = 0, den = 1;
		for (int j = i+1; j <= exp.length(); ++j) {
			if (j == exp.length() || exp.charAt(j) == '+' || exp.charAt(j) == '-') {
				String[] frac = exp.substring(i, j).split("/");
				int y = Integer.parseInt(frac[1]);
				num = num * y + Integer.parseInt(frac[0]) * den * (neg ? -1 : 1);
				den *= y;
				{ i = j + 1; if (j < exp.length()) neg = exp.charAt(j) == '-'; }
			}
		}

		StringBuilder b = new StringBuilder();
		if (num < 0) b.append('-');
		num = Math.abs(num);
		long g = gcd(num, den);
		b.append(num/g + "/" + den/g);
		return b.toString();
	}

	// a > b
	long gcd(long a, long b) {
		return a % b == 0 ? b : gcd(b, a % b);
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.fractionAddition("1/3").equals("1/3");
		assert s.fractionAddition("-1/2").equals("-1/2");
		assert s.fractionAddition("2/1").equals("2/1");
		assert s.fractionAddition("1/3-1/2").equals("-1/6");
		assert s.fractionAddition("1/3-1/2+1/6").equals("0/1");
		assert s.fractionAddition("1/3+5/3").equals("2/1");
		assert s.fractionAddition("1/3+1/6").equals("1/2");
                System.out.println("done");
	}
}

