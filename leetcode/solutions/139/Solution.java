/**
 * 139 Word Break
 */

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
	public boolean wordBreak(String s, List<String> list) {
		Set<String> dict = new TreeSet<>(list);
		Integer n = s.length();
		Boolean dp[] = new Boolean[n+1];

		dp[n] = true;
		for (int i = n-1; i >= 0; --i) {
			Boolean result = false;
			for (int j = i+1; j <= n; ++j) {
				String slice = s.substring(i, j);
				if (dict.contains(slice) && dp[j]) {
					result = true;;
					break;
				}
			}
			dp[i] = result;
			System.out.printf("dp[%2d]=%s\n", i+1, dp[i]);
		}

		return dp[0];
	}

	public static void main(String args[]) {
		String s = "wangchengcheng";
		List<String> list = Arrays.asList("wang", "cheng");
		Boolean result = new Solution().wordBreak(s, list);
	}
}
