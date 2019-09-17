/**
 * 470 Implement Rand10() Using Rand7() 45.3% Medium	
 * Performance: speed=%, memory=%
 * better solution in disscuss: use 7*7 = 49, read discuss for more details
 */

import java.util.*;

public class Solution extends SolBase {
	public int rand10() {
		int[] a = new int[10];
		for (int i = 0; i < 10; ++i) a[i] = i + 1;
		for (int i = 0; i < 10; ++i) {
			int j = (i + rand7() - 1 + 10) % 10;
			{ int t = a[i]; a[i] = a[j]; a[j] = t; }
		}
		return a[rand7()-1]; // i don't know why ???
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		int[] a = new int[10];
		for (int i = 0; i < 10000; ++i) ++a[s.rand10()%10];
		for (int i = 0; i < 10; ++i) System.out.printf("%2d: %d\n", i, a[i]);
	}
}

