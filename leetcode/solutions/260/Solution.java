/**
 * 260 Single Number III
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public int[] singleNumber(int[] nums) {
		int xor = 0; for (int i = 0; i < nums.length; ++i) xor ^= nums[i];
		int b = 0; for (int i = 0; i < 32; ++i) if ((xor & (1 << i)) != 0) { b = (1<<i); break; }
		int x = 0, y = 0;
		for (int i = 0; i < nums.length; ++i) if ((nums[i] & b) != 0) x ^= nums[i]; else y ^= nums[i];
		return new int[]{x, y};
	}

	public static void main(String args[]) {
	}
}

