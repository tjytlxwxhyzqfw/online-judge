/*
 * leetcode 093 - Restore IP Address
 *
 * 递归: restore(s, nsegs, bgn) - 返回所有从字符串s[bgn:]中提取的由nsegs段构成的IP地址
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public List<String> restoreIpAddresses(String s) {
		if (s == null)
			return null;
		List<String> result = restore(s, 4, 0);
		return result;
	}

	private List<String> restore(String s, int nsegs, int bgn) {
		List<String> empty = new ArrayList<String>();

		if (bgn >= s.length())
			return empty;

		if (nsegs == 1) {
			String sufix = s.substring(bgn, s.length());
			/* 解析整型的时候可能会出错, 注意一下 */
			try {
				if (Integer.parseInt(sufix) > 255)
					return empty;
			} catch (NumberFormatException e) {
				return empty;
			}

			/* 合法的IP字段前面不能以0开始, 注意一下 */
			if (sufix.length() > 1 && sufix.startsWith("0"))
				return empty;
			return Arrays.asList(sufix);
		}

		List<String> result = empty;

		String prefix = s.substring(bgn, bgn+1);
		List<String> subres = restore(s, nsegs-1, bgn+1);
		for (String sufix : subres)
			result.add(prefix + "." + sufix);

		if (bgn+2 <= s.length()) {
			prefix = s.substring(bgn, bgn+2);
			if (!prefix.startsWith("0")) {
				subres = restore(s, nsegs-1, bgn+2);
				for (String sufix : subres)
					result.add(prefix + "." + sufix);
			}
		}

		if (bgn+3 <= s.length()) {
			prefix = s.substring(bgn, bgn+3);
			/* 是"<=255", 这个地方粗心写成"<255", 不应该 */
			if (!prefix.startsWith("0") && Integer.parseInt(prefix) <= 255) {
				subres = restore(s, nsegs-1, bgn+3);
				for (String sufix : subres)
					result.add(prefix + "." + sufix);
			}
		}

		return result;
	}

	public static void main(String args[]) {
		String seq = "010010";
		List<String> ips = new Solution().restoreIpAddresses(seq);
		System.out.printf("%s\n", ips.toString());
	}
}
