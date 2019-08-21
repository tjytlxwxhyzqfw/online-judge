/**
 * 319 Bulb Switcher
 * Performance: speed=100%, memory=33%
 *
 * factor count of k is odd if and only if k is a perfect square
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public int bulbSwitch(int n) {
		// int i; for (i = 1; i * i <= n; ++i); return i-1;
		return (int)Math.sqrt(n);
    	}
	public static void main(String args[]) {
	}
}

