/**
 * 464: CanIWin
 * Performance: speed=%, memory=%
 *
 * 1111 -> false
 * 0111
 * 1011
 * 1101
 * 1110
 * 0011 false
 * 0101 false
 * 0110 false
 * 1001 false
 * 1010 false
 * 1100 false
 * 0001
 * 0010
 * 0100
 * 1000
 * 0000 true
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class Solution {
	public boolean canIWin(int n, int total) {
		int state = 0; // choose n: state |= 1 << (n-1),  state & (1 << n-1) != 0: n-choosed
		boolean dp[] = new boolean[1<<n];
		
		if (n >= total) return true;
		
		for (int s = (1<<n)-1; s >= 0; --s) {
			
		}
		for (int s = (1<<n)-1; s >= 0; --s) {
			print(s, dp[s]);
			if (dp[s]) continue;
			for (int i = 0; i < n; ++i) {
				if ((s&(1<<i))==0) continue;
				dp[s&(~(1<<i))] = true;
			}
		}

		return dp[0];
	}

	private void print(int s, boolean x) {
		for (int i = 20; i >= 0; --i) System.out.printf("%d", (s&(1<<i)) == 0 ? 0 : 1);
		System.out.printf(" %s\n", x);
	}


	public static void main(String args[]) {
		new Solution().canIWin(4, 6);
	}
}

