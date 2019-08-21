/**
 * 318 Maximum Product of Word Lengths
 * Performance: speed=62%, memory=78%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public int maxProduct(String[] words) {
		int[] a = new int[words.length];
		for (int i = 0; i < words.length; ++i) for (int j = 0; j < words[i].length(); ++j) a[i] |= (1 << (words[i].charAt(j) - 'a'));
		int maxP = 0;
		for (int i = 0; i < a.length; ++i) {
			for (int j = i+1; j < a.length; ++j) {
				if ((a[i] & a[j]) == 0) maxP = Math.max(maxP, words[i].length() * words[j].length());
			}
		}
		return maxP;
	}

	public static void main(String args[]) {
	}
}

