/**
 * 386 Lexicographical Numbers
 * Performance: speed=%, memory=%
 */

// review logs
// -----------
// Description: 给定一个整数比如13, 按照字典序输出[1, 13]内的所有数字: 1, 10, 11, 12, 13, 2, 3, 4, ..., 9
//
// 20200725: 今天我的想法是, 可以从1开始数, 以另外一种方式数过去.
//   比如, 1的下一个是10, 10的下一个是100(太大, 放弃),
//   10->11->110(不对)->12->120(不对)->13->130(不对)->14(不对)
//   删掉最后一位数, 剩下的数字+1:
//   14->2->20(不对)->3->30(不对)->4 ... -> 9 -> ""(没了) -> 算法结束
//   (9就删掉9, 剩下的数字+1, 如果剩下的还是9, 就继续删, 如果删没了, 就表示算法结束.
//

import java.util.*;

public class Solution {
	public List<Integer> lexicalOrder(int n) {
		List<Integer> list = new ArrayList<>(n);
		// 依次把1开头的所有数有序排好放入list,
		//     把2开头的所有数有序排好放入list,
		//     ...
		//     把9开头的所有数有序排好放入list,
		for (int i = 1; i < 10; ++i) if (i <= n) search(i, n, list);
		return list;
	}

	// search: 将所有的以prefix开头且小于n的数字按照字典顺序排好加入到list里面.
	void search(int prefix, int n, List<Integer> list) {
		list.add(prefix);
		System.out.printf("%d\n", prefix);
		int p = prefix * 10;
		for (int i = 0; i < 10; ++i) if (p+i <= n) search(p+i, n, list);
	}

	public static void main(String args[]) {
		new Solution().lexicalOrder(1);
	}
}

