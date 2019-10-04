/**
 * 739	Daily Temperatures 60.8% Medium	1629/47
 * Performance: speed=52%, memory=91%
 */

import java.util.*;

public class Solution {
	public int[] dailyTemperatures(int[] ts) {
		Stack<Integer> stack = new Stack<>();
		int[] r = new int[ts.length];
		for (int i = 0; i < ts.length; ++i) {
			while (!stack.isEmpty() && ts[stack.peek()] < ts[i]) {
				int popped = stack.pop();
				r[popped] = i - popped;
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) r[stack.pop()] = 0;
		return r;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}
