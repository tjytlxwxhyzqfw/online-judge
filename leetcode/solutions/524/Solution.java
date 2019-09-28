/**
 * 524 Longest Word in Dictionary through Deleting 46.4% Medium 373/191
 * Performance: speed=74%, memory=72%
 */

import java.util.*;

public class Solution {
	public String findLongestWord(String s, List<String> d) {
		String res = "";
		for (String sub : d) {
			if (isSubStr2(sub, s) && (res.length() < sub.length()
					|| res.length() == sub.length() && res.compareTo(sub) > 0)) {
				System.out.printf("%s -> %s\n", res, sub);
				res = sub;
			}
		}
		return res;
	}

	// is s sub-string of t ?
	boolean isSubStr(String s, String t) {
		int k = 0;
		for (int i = 0; i < s.length(); ++i) {
			boolean found = false;
			for (; k < t.length(); ++k) if (found = (t.charAt(k) == s.charAt(i))) break;
			if (!found) return false;
			++k;
		}
		return true;
	}

	// better impl in disscuss
	// private boolean isSubSeq(String s, String p) {
        // 	int i = 0, j = 0;
        // 	while (i < s.length() && j < p.length()) {
        //		if (s.charAt(i) == p.charAt(j)) { i++; j++; }
        //		else i++;
        //	}
        //	return j == p.length();
    	// }

	// another impl in disscuss
	boolean isSubStr2(String s, String t) {
		int j = 0;
		for (int i = 0; i < t.length(); ++i) if (j < s.length() && t.charAt(i) == s.charAt(j)) ++j;
		return j == s.length();
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.findLongestWord("", Arrays.asList("")).equals("");
		assert s.findLongestWord("", Arrays.asList("a", "b", "c")).equals("");
		assert s.findLongestWord("abcdefg", Arrays.asList("")).equals("");
		assert s.findLongestWord("abcde", Arrays.asList("a", "abc", "abcd", "abcde")).equals("abcde");
		assert s.findLongestWord("abpcplea", Arrays.asList("ale","apple","monkey","plea")).equals("apple");
		assert s.findLongestWord("abpcplea", Arrays.asList("c","b","a")).equals("a");
		assert s.findLongestWord("abcd", Arrays.asList("b", "aa", "cd","bc", "a", "ab")).equals("ab");

		System.out.println("all test passed");
	}
}

