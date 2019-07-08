/**
 * 0467: UniqueSubstringsInWraparoundString
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int findSubstringInWraproundString(String p) {
		int[] counts = new int[26];
		int i = 0, j;
		while (i < p.length()) {
			counts[p.charAt(i)-97] = Math.max(counts[p.charAt(i)-97], 1);
			for (j = i+1; j < p.length(); ++j) {
				int prev = p.charAt(j-1)-97, next = p.charAt(j)-97;
				if ((prev+1)%26 != next) break;
				counts[next] = Math.max((j-i+1), counts[next]);
			}
			i = j;
			//for (int k = 0; k < 26; ++k) System.out.printf("%c: %d\n", (char)(k+97), counts[k]);
			//System.out.printf("i->%d\n", j);
		}

		int ans = 0;
		for (int k = 0; k < 26; ++k) {
			ans += counts[k];
		}
		return ans;
	}

	public static void main(String args[]) {
		int r1 = new Solution().findSubstringInWraproundString("");
		System.out.printf("r1: %d\n", r1);
	}
}

