/**
 * 179 - Largest Number 
 *
 * 我的思路是, 按照如下规则比较两个字符串s和t的大小:
 * 1. 若 s.length() == t.length(), 返回其字典序
 * 2. 否则, 设s长度较小, 比较s与t[i:i+s.length()], i从0增加到 t.length()-s.length(), 直到比出大小为止
 * 3. 如果第(2)步一直都比不出, 那就判定为相等
 *
 * 应该不会出现循环
 * 3, 33, 333, 300, 304, 340
 *
 * 上面的思路跪在了这个例子上: 12 121
 *
 * 事实上:
 * 1. 如果二者长度相等, 返回其字典序, 这个没错
 * 2. 如果二者长度不相等, |s|小于|t|那么分一下两种情况:
 * 	2.1 如果 s 不是 t 的前缀, 那么二者在前|s|个字符上就能比出大小, 直接比较
 *	2.2 否则, s=A, t=AB, 那么 二者的组合为  AAB 或者 ABA, 只要比较AB和BA的大小就好了
 *
 * 2017-08-01
 * 题意: 给定一串数字, 把他们拼起来, 拼出一个最大的数字.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	public String largestNumber(int[] nums) {
		if (nums==null || nums.length==0)
			return "0";

		String strs[] = new String[nums.length];
		for (int i = 0; i < nums.length; ++i)
			strs[i] = String.valueOf(nums[i]).toString();

		Arrays.sort(strs, (left, rght) -> {
			// 如果两个字符串的长度是相等的, 直接返回其字典序
			if (left.length() == rght.length())
				return left.compareTo(rght);

			System.out.printf("-- comparing: %s vs. %s\n", left, rght);

			String shorter, longer;
			int sign;

			if (left.length() < rght.length()) {
				shorter = left;
				longer = rght;
				sign = 1;
			} else {
				shorter = rght;
				longer = left;
				sign = -1;
			}

			int prefixDiff = shorter.compareTo(longer.substring(0, shorter.length()));
			if (prefixDiff == 0) {
				String lasthalf = longer.substring(shorter.length());
				String reversed = lasthalf+shorter;
				return sign*longer.compareTo(reversed);
			} else {
				// 如果短的字符串不是长字符串的前缀,
				// 那么我们直接返回 [长字符串前缀]与短字符串的字典序
				return sign*prefixDiff;
			}
		});

		for (int i = 0; i < strs.length; ++i)
			System.out.printf("%s\n", strs[i]);

		StringBuilder builder = new StringBuilder();
		for (int i = strs.length-1; i >= 0; --i)
			builder.append(strs[i]);

		// 处理全0的情况
		int firstNonZero = 0;
		while (firstNonZero<builder.length() && builder.charAt(firstNonZero)=='0')
			++firstNonZero;
		return firstNonZero==builder.length() ? "0" : builder.substring(firstNonZero);
	}

	private boolean consistsOfSingleCh(String s) {
		char ch = s.charAt(0);
		for (int i = 0; i < s.length(); ++i)
			if (ch != s.charAt(i))
				return false;
		return true;
	};

	private int firstNot(char ch, String s) {
		for (int i = 0; i < s.length(); ++i)
			if (s.charAt(i) != ch)
				return i;
		return s.length();
	}

	public static void main(String args[]) {
		//int nums[] = {3, 33, 333, 300, 303, 330, 343, 334, 304};
		//int nums[] = {12, 121};
		int nums[] = {0, 0, 0};
		String result = new Solution().largestNumber(nums);
		System.out.printf("result: %s\n", result);
	}
}

