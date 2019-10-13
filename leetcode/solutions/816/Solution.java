/**
 * 816 Ambiguous Coordinates 45.0% Medium 87/178
 * Performance: speed=35%, memory=100%
 */

import java.util.*;

public class Solution {
	public List<String> ambiguousCoordinates(String s) {
		List<String> list = new ArrayList<>();
		s = s.substring(1, s.length()-1);
		for (int i = 1; i < s.length(); ++i) {
			List<String> left = nums(s.substring(0, i));
			List<String> rght = nums(s.substring(i));
			for (String x : left) for (String y : rght) list.add("("+x+", "+y+")");
		}
		return list;
	}

	List<String> nums(String s) {
		List<String> list = new ArrayList<>();
		if (s == null || s.length() == 0) return list;

		if (s.charAt(0) == '0') {
			if (s.length() == 1) list.add(s);
			else {
				String suffix = s.substring(1);
				if (Integer.parseInt(suffix) != 0 && !suffix.endsWith("0")) list.add("0." + suffix);
			}
		} else {
			list.add(s);
			for (int i = 1; i < s.length(); ++i) {
				String suffix = s.substring(i);
				if (Integer.parseInt(suffix) != 0 && !suffix.endsWith("0")) {
					list.add(s.substring(0, i) + "." + suffix);
				}
				else break;
			}
		}
		return list;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		System.out.println(s.nums("0"));
		System.out.println(s.nums("1"));
		System.out.println(s.nums("001"));
		System.out.println(s.nums("100"));
		System.out.println(s.nums("010"));
		System.out.println(s.nums("123"));

		System.out.println(s.ambiguousCoordinates("(0101)"));
		System.out.println(s.ambiguousCoordinates("(123)"));
		System.out.println(s.ambiguousCoordinates("(00011)"));
		System.out.println(s.ambiguousCoordinates("(0123)"));
		System.out.println(s.ambiguousCoordinates("(100)"));


		System.out.println("done");
	}
}

