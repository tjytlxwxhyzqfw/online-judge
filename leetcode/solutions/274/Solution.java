/**
 * 274 H-Index
 * Performance: speed=100%, memory=100%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public int hIndex(int[] citations) {
		int i = 0, j = citations.length+1, k, h = 0;
		while (i < j) {
			k = i + (j-i)/2;
			if (check(citations, k)) { h = k; i = k + 1; }
			else j = k;
		}
		return h;
	}

	boolean check(int[] a, int k) {
		int n = 0; for (int i = 0; i < a.length; ++i) if (a[i] >= k) ++n;
		return n >= k;
	}

	public static void main(String args[]) {
	}
}

