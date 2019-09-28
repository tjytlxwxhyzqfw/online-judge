/**
 * 537 Complex Number Multiplication 65.9% Medium
 * Performance: speed=79%, memory=100%
 */

import java.util.*;

public class Solution {
	public String complexNumberMultiply(String left, String rght) {
		String[] leftA = left.split("+"), rghtA = rght.split("+");
		int a = Integer.parseInt(leftA[0]);
		int b = Integer.parseInt(leftA[1].substring(0, leftA[1].length()-1));
		int c = Integer.parseInt(rghtA[0]);
		int d = Integer.parseInt(rghtA[1].substring(0, rghtA[1].length()-1));
		return (a*c-b*d) + "+" + (a*d+b*c) + "i";
	}

	public static void main(String args[]) {
	}
}

