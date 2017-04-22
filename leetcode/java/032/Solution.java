import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Solution {

	public int longestValidParentheses(String s) {
		int length = s.length();
		ArrayList<Boolean> brk = new ArrayList<Boolean>(Collections.nCopies(length, false));
	
		Stack<Integer> stk = new Stack<Integer>();
		for (int i = 0; i < length; ++i) {
			char ch = s.charAt(i);

			if (ch == '(') {
				stk.push(i);
			} else if (ch == ')') {
				if (stk.empty())
					brk.set(i, true);
				else
					stk.pop();
			} else {
				throw new RuntimeException();
			}

		}

		while (!stk.empty()) {
			int i = stk.pop();
			brk.set(i, true);
		}

		int maxvalids = 0;
		int nvalids = 0;
		for (int i = 0; i < length; ++i) {
			if (brk.get(i)) {
				//System.out.printf("break: %d\n", i);
				nvalids = 0;
			} else {
				++nvalids;
				if (nvalids > maxvalids)
					maxvalids = nvalids;
			}
		}

		return maxvalids;
	}
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
		int ans = new Solution().longestValidParentheses(s);
		System.out.printf("-------> %d\n", ans);
	}
}
