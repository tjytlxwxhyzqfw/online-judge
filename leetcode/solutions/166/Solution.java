/**
 * 166 - Fraction to Recurring Decimal
 *
 * 题意: 将分数表示成无线循环小数
 *
 * 我曾经跪在这些例子上:
 *
 * -50/8 : 一开始没考虑负数
 * 7/-12 : 我用 top/bottom 与0 比较来检查负数, 忘了, 结果取整之后就是0了
 * -1/-2147483648 最小负整数问题, 所以我用了 long 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
	public String fractionToDecimal(int topInt, int bottomInt) {
		long top = (long)topInt;
		long bottom = (long)bottomInt;

		if (top % bottom == 0)
			return "" + top / bottom;

		boolean negetive = ((top<0&&bottom>0) || (top>0&&bottom<0));
		top = Math.abs(top);
		bottom = Math.abs(bottom);

		StringBuilder solution = new StringBuilder();
		Map<Long, Integer> tops = new TreeMap<>();

		// 先把整数部分处理了, 整数部分自带小数点
		// 这事因为我们已经把整除的部分处理完了
		if (negetive)
			solution.append("-");
		solution.append(top/bottom).append(".");
		top = (top/bottom == 0 ? top*10 : (top%bottom)*10);

		System.out.printf("starts with solution='%s', top=%d\n", solution.toString(), top);

		int idx = solution.length();
		while (true) {
			if (top == 0)
				break;
			if (tops.containsKey(top))
				break;

			tops.put(top, idx++);

			long q = top / bottom;
			long r = top % bottom;

			//System.out.printf("top: %d, q=%d, r=%d\n", top, q, r);

			solution.append(q);

			// 下一次迭代的时候,
			// 如果本次迭代商的是0, 那么分子原地乘10
			// 否则, 分子直接就是 余数*10
			top = (q == 0 ? top*10 : r*10);
		}

		// 如果 top=0, 那么我们此时已经完成了任务,
		// 可以直接返回结果了
		if (top == 0)
			return solution.toString();

		// 此时, top!=0, 发生了循环
		int begin = tops.get(top);
		solution.insert(begin, "(");
		solution.append(")");

		return solution.toString();
	}

	public static void main(String args[]) {
		int top = 200;
		int bottom = 3;
		String ans = new Solution().fractionToDecimal(top, bottom);
		System.out.printf("ans: %s\n", ans);
		System.out.printf("number: %f\n", 1.0*top/bottom);
	}
}

