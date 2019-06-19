/**
 * 151 - Reverse words in a String
 *
 * 题意: 给你一个句子字符串, 倒序输出里面所有的单词. (单词本身是正序的)
 *
 * 小技巧: 如何截断一个 StringBuilder ?
 *
 * / 不允许多个空格, 所以这个答案不行
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
	public String reverseWords(String s) {
		if (s == null || s.length() == 0)
			return s;

		Stack<String> stack = new Stack<>();
		char last=0, current=0;
		StringBuilder word = new StringBuilder();
		for (int i = 0; i < s.length(); ++i) {
			current = s.charAt(i);
			System.out.printf("current: %c\n", current);
			if (current==' ' && last!=0 && last!=' ') {
				System.out.printf("word: %s\n", word.toString());
				stack.push(word.toString());
				word.setLength(0);
			} else if (current!=' ' && last==' ') {
				word.setLength(0);
			}

			word.append(current);
			last = current;
		}
		if (word.length()!=0 && current!=' ')
			stack.push(word.toString());

		StringBuilder builder = new StringBuilder();
		while (!stack.isEmpty())
			builder.append(stack.pop()).append(" ");
		if (builder.length()>0)
			builder.setLength(builder.length()-1);

		return builder.toString();
	}

	public static void main(String args[]) {
		//String s = "   ni hao  wo   shi yi er san    ";
		//String s = "      ";
		String s = "yiersan";
		String result = new Solution().reverseWords(s);
		System.out.printf("---> [%s]\n", result);
	}
}

