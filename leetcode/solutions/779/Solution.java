/**
 * 779 K-th Symbol in Grammar 37.6% Medium 268/92
 * Performance: speed=100%, memory=16%
 */

import java.util.*;

public class Solution {
	public int kthGrammar(int n, int k) {
		if (k <= 2) return k-1;
		int len = (1 << (n-1));
		int d = k - len / 2;
		if (d <= 0) return kthGrammar(n-1, k);
		else        return 1 - kthGrammar(n, d);
	}
	
	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.kthGrammar(1, 1) == 0;
		assert s.kthGrammar(2, 1) == 0;
		assert s.kthGrammar(2, 2) == 1;
		assert s.kthGrammar(4, 5) == 1;
		assert s.kthGrammar(4, 6) == 0;
		assert s.kthGrammar(6, 9) == 1;
		assert s.kthGrammar(6, 29) == 1;
		assert s.kthGrammar(6, 20) == 1;
		assert s.kthGrammar(6, 21) == 0;
		System.out.println("done");
	}
}

/*
0
01
0110
01101001
0110100110010110
01101001100101101001011001101001

1 -> 1
2 -> 2
3 -> 4
4 -> 8
5 -> 16
6 -> 32
    

4 -> 8 -> 4+4 -> 5 -> 1!
(6, 20)
6 -> 32 -> 16+16 -> !(6, 20-(32/2)) -> 6->4
(3, 4) (3, 4-(4/2)) -> (3, 2) -> 2->2 = 1

(6, 13) -> (5, 13) -> !(5, 5) -> !(4, 5) -> (4, 1) -> (1, 1) = 0
*/
