/**
 * 722 Remove Comments 31.9% Medium 192/617
 * Performance: speed=100%, memory=100%
 */

import java.util.*;

public class Solution {
	String specialCases = "[//**/], [a/*ab//cde]";

	public List<String> removeComments(String[] source) {
		List<String> list = new ArrayList<>();
		boolean inComment = false;
		StringBuilder b = new StringBuilder();

		for (String line : source) {
			for (int i = 0; i < line.length(); ++i) {
				char ch = line.charAt(i);
				switch (ch) {
					case '/':
						if (!inComment) {
							if (i+1 < line.length() && line.charAt(i+1) == '*') {
								inComment = true;
								++i;
							} else if (i+1 < line.length() && line.charAt(i+1) == '/') {
								i = line.length(); // just skip the line immediately
							} else {
								b.append(ch);
							}
						}
						break;
					case '*':
						if (!inComment) {
							b.append(ch);
						} else {
							if (i+1 < line.length() && line.charAt(i+1) == '/') {
								inComment =false;
								++i;
							}
						}
						break;
					default:
						if (!inComment) b.append(ch);
				}
			}
			if (b.length() > 0 && !inComment) {
				list.add(b.toString());
				b = new StringBuilder();
			}
		}

		return list;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

