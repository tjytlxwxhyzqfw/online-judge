/**
 * leetcode 091 - Decode Ways
 *
 * 动态规划
 * 
 * 思路是这样的
 * (i): 从第i个字符开始向后, 总共有几种编码方式?
 * 如果s[i] == 0, 则[i]=0
 * 否则, 查看s[i]能否和s[i+1]组合, 如果能组合, 那么[i]=[i+1]+[i+2]
 * 如果不能组合, 那就是[i]=[i+1]
 *
 * 这里有个小trick, 就是事先判断s是不是个不合法的字符创
 * 也不知道管用没管用
 */

public class Solution {
	public int numDecodings(String s) {
		if (s == null || s.length() == 0)
			return 0;

		/* s可能是个"不合法"的字符串, 即不能由'ABC'编码而来 */
		if (s.charAt(0) == '0')
			return 0;
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) == '0') {
				char before = s.charAt(i-1);
				if (before != '1' && before != '2')
					return 0;
			}
		}

		int dp[] = new int[s.length()];
		/*
		 * 从最后一个字符开始的编码个数未必是1
		 * 如果最后一个字符是0的话, 就是0
		 */
		dp[s.length()-1] = s.charAt(s.length()-1) == '0' ? 0 : 1;
		for (int i = s.length()-2; i >= 0; --i) {
			char first = s.charAt(i);
			char second = s.charAt(i+1);
			if (first == '0')
				dp[i] = 0;
			else if (first=='1' || (first=='2' && '0' <= second && second <= '6'))
				dp[i] = dp[i+1] + ((i+2)<s.length() ? dp[i+2] : 1);
			else
				dp[i] = dp[i+1];
			System.out.printf("dp[%2d]: %d\n", i+1, dp[i]);
		}
		return dp[0];
	}

	public static void main(String args[]) {
		new Solution().numDecodings("101");
	}
}
