/**
 * 165 Compare Version Numbers
 *
 * 题意: 比较两个版本号
 *
 * 把版本号的每个字段都解析成整数, 然后从左到右比较.
 * 注意, 应该忽略掉后缀0
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
	public int compareVersion(String version1, String version2) {
		String splited1[] = version1.split("\\.");
		String splited2[] = version2.split("\\.");

		Stack<Integer> stack1 = new Stack<>(), stack2 = new Stack<>();
		push(splited1, stack1);
		push(splited2, stack2);

		while (!stack1.isEmpty() && !stack2.isEmpty()) {
			Integer left=stack1.pop(), rght=stack2.pop();
			System.out.printf("left=%d, rght=%d\n", left, rght);
			if (left < rght)
				return -1;
			else if (left > rght)
				return 1;
		}

		if (stack1.isEmpty() && stack2.isEmpty())
			return 0;
		return stack1.isEmpty() ? -1 : 1;
	}

	private void push(String subs[], Stack<Integer> stack) {
		int i = subs.length-1;

		// 忽略掉版本号的后缀0
		for (; i >= 0 && Integer.parseInt(subs[i])==0; --i);

		if (i < 0) {
			stack.push(0);
		} else {
			for (; i >=0; --i)
				stack.push(Integer.parseInt(subs[i]));
		}
	}

	public static void main(String args[]) {
		String version1 = "0.0.0.1.0000.1";
		String version2 = "0.0.0.1";
		int ans = new Solution().compareVersion(version1, version2);
		System.out.printf("ans=%d\n", ans);
	}
}

