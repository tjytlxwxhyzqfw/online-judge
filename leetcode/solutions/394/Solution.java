/**
 * 394: DecodeString
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public String decodeString(String s) {
		String s1 = "1[" + s + "]";
		return dfs(s1, 0, new int[1]);
	}

	private String dfs(String s, int i, int[] next) {
		// s.charAt(i) must be a number
		int[] a = getN(s, i);
		int[] out = new int[1];

		StringBuilder b = new StringBuilder();
		int k = a[1] + 1;
		while (k < s.length() && s.charAt(k) != ']') {
			if ('0' <= s.charAt(k) && s.charAt(k) <= '9') {
				b.append(dfs(s, k, out));
				k = out[0];
			} else {
				b.append(s.charAt(k));
				++k;
			}
		}

		StringBuilder b1 = new StringBuilder();
		while (a[0]-- > 0) b1.append(b.toString());

		next[0] = k + 1;
		return b1.toString();
	}

	private int[] getN(String s, int i) {
		int n = 0, j = i;
		while ('0' <= s.charAt(j) && s.charAt(j) <= '9') {
			n = n*10+s.charAt(j)-'0';
			++j;
		}
		return new int[]{n, j};
	}

	public static void main(String args[]) {
		String r1 = new Solution().decodeString("10[a]");
		System.out.printf("r1=%s\n", r1);
	}
}

