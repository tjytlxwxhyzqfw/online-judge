/**
 * 132 Palindrome Partitioning II
 * pass=29 ud=889/29 s=14 m=8
 */

// aaba -> right: a | aba
//      -> wrong: aa | b | a

import java.util.*;


// axbxaba
// abacc

// pld[i][j] = pld[i+1][j-1] && s[i] == s[j-1]
// cut[i][j] = 1 + min {
//      cut[i][i+1] + cut[i+1][j]
//      cut[i][i+2] + cut[i+2][j] = min {cut[i][i+1] + cut[i+1][i+2]}
//      cut[i][i+3] + cut[i+3][j]
//      ...
// }
//
// above is a good way to deal with cut(i, k) + cut(k, j) structure
// but sadlly it doesn't work at this situation !!!
//
// cut[i] = 0 || min (cut[j] + 1 where s[j...i] is palindrome)
// ****** cut[i] => s[0:i) => the latest cut locates at j then s[j:i) MUUUUUST be palindrome !!! *******
// so cut[i] = 0 || 1 + min {
//        cut[a] where 0 < a < i and s[a:i) is palindrome,
//        cut[b] where 0 < b < a and s[b:i) is palindrome,
//        cut[c] where 0 < c < b and s[c:i) is palindrome,
//        ...
// }
//
// ******************************************************
// DO NOOOOOOT think of the postion of the FIRST CUT !!!!
// think of the position of the LAST CUT !!!!!!!
// ******************************************************
//       
// TODO(20200405): re-solve this problem with the O(n^2) approach !!!
public class Solution {
	public int minCut(String s) {
		if (s.length() == 0) return 0;
		int[][] cut = new int[s.length()][1+s.length()];
		// for (int i = 0; i < s.length(); ++i) cut[i][i] = cut[i][i+1] = 0;
		for (int n = 2; n <= s.length(); ++n) {
			for (int i = 0; i+n <= s.length(); ++i) {
				if (!(cut[i+1][i+n-1] == 0 && s.charAt(i) == s.charAt(i+n-1))) {
					int min = n;
					for (int k = i+1; k < i+n; ++k) min = Math.min(cut[i][k]+cut[k][i+n], min);
					cut[i][i+n] = 1 + min;
				}
				// System.out.printf("cut: %s: [%d, %d): %d\n", s, i, i+n, cut[i][i+n]);
			}
		}
		return cut[0][s.length()];
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.minCut("") == 0;
		assert s.minCut("a") == 0;
		assert s.minCut("aa") == 0;
		assert s.minCut("aaba") == 1;
		assert s.minCut("aabc") == 2;
		assert s.minCut("adbc") == 3;
		assert s.minCut("aab") == 1;
		assert s.minCut("eegiicgaeadbcfacfhifdbiehbgejcaeggcgbahfcajfhjjdgj") == 42;
		assert s.minCut("fifgbeajcacehiicccfecbfhhgfiiecdcjjffbghdidbhbdbfbfjccgbbdcjheccfbhafehieabbdfeigbiaggchaeghaijfbjhi") == 75;
		System.out.println("done");
	}
}

