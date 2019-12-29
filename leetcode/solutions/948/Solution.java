/**
 * 948 Bag of Tokens 39.6% Medium 127/123
 * Performance: speed=11%, memory=100%
 */

import java.util.*;

public class Solution {
	public int bagOfTokensScore(int[] tokens, int p) {
		Arrays.sort(tokens);
		int i = 0, j = tokens.length-1;
		int max = buyAll(p, i, j, tokens);
		while (i < j && p > tokens[i]) {
			p = p - tokens[i++] + tokens[j--];
			max = Math.max(max, buyAll(p, i, j, tokens));
		}
		return max;
	}

	// i can by my self opt this routine with binary-search and prefix sum
	int buyAll(int p, int i, int j, int[] tokens) {
		for (int k = i; k <= j; ++k) {
			if (p - tokens[k] >= 0) {
				p -= tokens[k];
			} else {
				return  k - i;
			}
		}
		return j - i + 1;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.bagOfTokensScore(new int[]{71, 55, 82}, 54) == 0;

		assert s.bagOfTokensScore(new int[]{}, 0) == 0;
		assert s.bagOfTokensScore(new int[]{}, 10000) == 0;
		assert s.bagOfTokensScore(new int[]{1}, 10000) == 1;
		assert s.bagOfTokensScore(new int[]{1}, 0) == 0;
		assert s.bagOfTokensScore(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10000) == 10;
		assert s.bagOfTokensScore(new int[]{10, 20}, 15) == 1;
		assert s.bagOfTokensScore(new int[]{10, 20}, 5) == 0;
		assert s.bagOfTokensScore(new int[]{10, 20, 30, 40}, 20) == 2;


		System.out.println("done");
	}
}
