import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
	private String pat, txt;
	private int lenpat, lentxt;
	private boolean dp[][];

	public boolean isMatch(String s, String p) {

		if (s == null || p == null) {
			return s == null && p == null;
		} else if (s.length() == 0) {
			boolean ok1 = p.length() == 0;
			boolean ok2 = true;
			for (int i = 0; i < p.length(); ++i)
				ok2 = (ok2 && (p.charAt(i) == '*'));
			return ok1 || ok2;
		} else if (p.length() == 0) {
			return s.length() == 0;
		}

		this.txt = s;
		this.lentxt = s.length();

		this.pat = p;
		this.lenpat = p.length();

		dp = new boolean[lenpat][lentxt];

		for (int i = lenpat-1; i >= 0; --i) {
			for (int j = lentxt-1; j >= 0; --j) {
				dpFill(i, j);
				//dpPrint(i, j);
			}
		}

		return dp[0][0];
	}

	private void dpFill(int i, int j) {
		char pchar = pat.charAt(i);
		char tchar = txt.charAt(j);

		if (i == lenpat-1) {
			boolean ok1 = (pchar == '*');
			boolean ok2 = (j == (lentxt-1) && (pchar == '?' || pchar == tchar));
			dp[i][j] = ok1 || ok2;
			return;
		}

		if (pchar != '*') {
			// p[i]是'?'就一定能成功
			// p[i]不是'?', 那么p[i]==t[j], 也能匹配成功
			// 在当前元素匹配成功的前提下, 检查dp[i+1][j+1]	
			boolean cur = (pchar == '?' || pchar == tchar);
			boolean fut =  (j+1 >= lentxt ? false : dp[i+1][j+1]);
			if (j+1 < lentxt) {
				//如果j+1存在, 直接检查
				fut = dp[i+1][j+1];
			} else {
				// 如果j+1不存在, 
				// 说明文本字符串已经被用完了, 但是模式字符串还在
				// 检查模式字符串是不是全由'*'构成,
				// 如果是的话, 我们依然能够匹配成功 
				fut = true;
				for (int k = i+1; k < lenpat; ++k)
					fut = (fut && (pat.charAt(k) == '*'));
			}
			dp[i][j] = (cur && fut);
			return;
		}

		// 当前的模版字符是个'*'	
		for (int k = 0; k+j < lentxt; ++k)
			dp[i][j] = dp[i][j] || dp[i+1][j+k];
	}

	private void dpPrint(int i, int j) {
		System.out.printf("[%3d, %3d]: %6s\n", i, j, dp[i][j]);
	}
	

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		String txt = scanner.next();
		String pat = scanner.next();
		boolean ans = new Solution().isMatch(txt, pat);
		System.out.printf("---> %s\n", ans);
	}
}
