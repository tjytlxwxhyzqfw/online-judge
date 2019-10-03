/**
 * 678 Valid Parenthesis String 33.3% Medium 767/25
 * Performance: speed=45%, memory=100%
 */

/*
(*
(((***
(***((**))

***)))

(*

((**)
*/

import java.util.*;

public class Solution {
	public boolean checkValidString(String s) {
		Stack<Character> stack = new Stack<>();
		int nstars = 0;
		for (int i = 0; i < s.length(); ++i) {
			switch (s.charAt(i)) {
				case '(': 
					stack.push(s.charAt(i));
					break;
				case ')': 
					if (stack.isEmpty()) {
						if (nstars-- == 0) return false;
					} else stack.pop();
					break;
				case '*':
					++nstars;
					if (!stack.isEmpty()) { stack.pop(); ++nstars; }
					break;
				default:
			}
		}
		return stack.isEmpty();
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.checkValidString("");
		assert s.checkValidString("*)");
		assert s.checkValidString("**)");
		assert s.checkValidString("(((***");
		assert s.checkValidString("((**");
		assert s.checkValidString("((**)");
		assert s.checkValidString("((**))");
		assert s.checkValidString("((**)))");
		assert s.checkValidString("((**))))");
		assert s.checkValidString("((**)))))") == false;
		assert s.checkValidString("())**") == false;
		assert s.checkValidString("******");

		System.out.println("done");
	}
}

