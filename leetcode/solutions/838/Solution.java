/**
 * 838: PushDominoes
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	private static int INF = 1000000;
	public String pushDominoes(String s) {
		int[] left = new int[s.length()];
		for (int i = s.length()-1; i >= 0; --i) {
			if (s.charAt(i) == 'L') left[i] = 0;
			else {
				// attention: s.charAt(i) == 'R' is very important
				if (i == s.length()-1 || s.charAt(i) == 'R') left[i] = INF;
				else left[i] = Math.min(left[i+1]+1, INF);
			}
		}
		int[] rght = new int[s.length()];
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) == 'R') rght[i] = 0;
			else {
				if (i == 0 || s.charAt(i) == 'L') rght[i] = INF;
				else rght[i] = Math.min(rght[i-1]+1, INF);
			}
		}

		StringBuilder b = new StringBuilder(s.length());
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) != '.') b.append(s.charAt(i));
			else if (left[i] < rght[i]) b.append('L');
			else if (left[i] > rght[i]) b.append('R');
			else b.append('.');
		}

		return b.toString();
	}
	
	public static void main(String args[]) {
		String s1 = new Solution().pushDominoes("R..L");
		System.out.printf("s1: %s\n", s1);
	}
}

