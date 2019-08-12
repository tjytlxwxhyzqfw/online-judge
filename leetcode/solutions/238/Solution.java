/**
 * 238 Product of Array Except Self
 * Performance: speed=100%, memory=46%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public int[] productExceptSelf(int[] a) {
		int[] b = new int[a.length];
		b[a.length-1] = a[a.length-1];
		for (int i = a.length-2; i >= 0; --i) b[i] = a[i] * b[i+1];
		int p = 1;
		for (int i = 0; i < a.length; ++i) { b[i] = p * (i+1 < a.length ? b[i+1] : 1); p *= a[i]; }
		return b;
	}

	public static void main(String args[]) {
	}
}

