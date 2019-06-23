/** 
 * 生成Grey码
 *
 * 给定一个数字n, 从0开始, 生成所有的n位二进制grey码
 * (这样表述准确吗?)
 *
 * 从n格雷码生成(n+1)格雷码的方法是这样的:
 *
 * 1.. 生成所有的n位二进制grey码, 放在列表list中
 * 2. 在list中的所有n位格雷码的前面加上0, 
 * 3. 把list倒序, 记为reverse, 将reverse借在list后面
 * 4. 在reverse中的每个结果前面加上1
 * 5. 搞定
 * 
 * 感谢递归, 这真的是一个碉堡的思想
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {

	public List<Integer> grayCode(int n) {
		if (n == 0) {
			List<Integer> result = new ArrayList<Integer>();
			result.add(0);
			return result;
		}
		if (n == 1) {
			List<Integer> result = new ArrayList<Integer>();
			result.add(0);
			result.add(1);
			return result;
		}

		List<Integer> result = grayCode(n-1);

		Stack<Integer> stack = new Stack<Integer>();
		for (Integer i : result)
			stack.push(i);
		while (!stack.empty()) {
			int top = stack.pop();
			int gray = top + (1<<(n-1));
			result.add(gray);
		}

		System.out.printf("%2d: %s\n", n, result.toString());
		return result;
	}

	public static void main(String args[]) {
		new Solution().grayCode(5);
	}
}
