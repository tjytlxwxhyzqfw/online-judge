/**
 * 384 Shuffle an Array
 * Performance: speed=20%, memory=61%
 */

import java.util.*;

public class Solution {
	int[] nums;
	Random r;

	public Solution(int[] nums) {
		this.nums = nums.clone();
		r = new Random(47);
	}
	
	/** Resets the array to its original configuration and return it. */
	public int[] reset() {
		return nums.clone();
	}
	
	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		int[] a = nums.clone();
		int t;
		for (int i = a.length-1; i > 0; --i) {
			int j = r.nextInt(i+1);
			{ t = a[i]; a[i] = a[j]; a[j] = t; }
		}
		return a;
	}

	public static void main(String args[]) {
	}
}

